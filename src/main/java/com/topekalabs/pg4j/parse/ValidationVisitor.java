/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topekalabs.pg4j.parse;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.topekalabs.pg4j.annotations.PGClass;
import com.topekalabs.pg4j.annotations.PGType;
import com.topekalabs.pg4j.annotations.PGTypeP;
import com.topekalabs.pg4j.annotations.PGTypeParam;
import com.topekalabs.pg4j.annotations.PGTypeParams;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.expr.AnnotationExpr;
import japa.parser.ast.expr.ArrayInitializerExpr;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.FieldAccessExpr;
import japa.parser.ast.expr.MemberValuePair;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.expr.NormalAnnotationExpr;
import japa.parser.ast.expr.StringLiteralExpr;
import japa.parser.ast.visitor.VoidVisitorAdapter;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Topeka Labs
 */
public class ValidationVisitor extends VoidVisitorAdapter<Object>
{
    private static final Logger logger = LoggerFactory.getLogger(ValidationVisitor.class.getName());
    
    private boolean visitorRun = false;
    
    private ValidationPayload validationPayload;
    private Set<TypeParamCombination> typeParamCombinations = Sets.newHashSet();
    private String classNameRegex = PGClass.CLASS_RENAME_REGEX_DEFAULT;
    
    public ValidationVisitor()
    {
    }
    
    public ValidationPayload getValidationPayload()
    {
        if(validationPayload == null)
        {
            throw new UnsupportedOperationException("Must visit a compilation unit before getting a validation payload.");
        }
        
        return validationPayload;
    }
    
    @Override
    public void visit(CompilationUnit cu, Object o)
    {
        if(visitorRun)
        {
            throw new UnsupportedOperationException("Cannot run the visitor twice.");
        }
        
        visitorRun = true;
        
        super.visit(cu, o);
        
        validationPayload = new ValidationPayload(typeParamCombinations,
                                                  classNameRegex);
    }
    
    @Override
    public void visit(FieldDeclaration node, Object o)
    {
        List<AnnotationExpr> ans = node.getAnnotations();
        
        if(ans == null)
        {
            return;
        }
        
        for(AnnotationExpr an: ans)
        {
            logger.debug("Annotation name: {}", an.getName().getName());
        }
    }
    
    @Override
    public void visit(ClassOrInterfaceDeclaration node, Object o)
    {
        List<AnnotationExpr> ans = node.getAnnotations();
        
        if(ans == null)
        {
            return;
        }
        
        boolean classDeclaration = false;
        
        for(AnnotationExpr an: ans)
        {
            if(ParseUtils.matchUQClassName(PGClass.class,
                                           an.getName().getName()))
            {
                if(classDeclaration)
                {
                    throw new UnsupportedOperationException("Primitive generics on files with multiple primitive generic classes is unsupported.");
                }

                classDeclaration = true;
                
                parsePGClass(an);
            }
        }
    }
    
    private void parsePGClass(AnnotationExpr ae)
    {
        NormalAnnotationExpr nae = (NormalAnnotationExpr) ae;
        List<MemberValuePair> mvps = nae.getPairs();
        
        for(MemberValuePair mvp: mvps)
        {
            Expression expr = mvp.getValue();

            if(PGClass.CLASS_RENAME_REGEX.equals(mvp.getName()))
            {
                if(!(expr instanceof StringLiteralExpr))
                {
                    throw new PG4jException(expr, "Not a string literal");
                }
                
                classNameRegex = ((StringLiteralExpr) expr).getValue();
            }
            else if(PGClass.TYPE_PARAMS.equals(mvp.getName()))
            {
                if(!(expr instanceof ArrayInitializerExpr))
                {
                    throw new PG4jException(expr, "Must be an array");
                }
                
                parseTypeParamss((ArrayInitializerExpr) expr);
            }
        }
    }
    
    private List<PGTypeParamsH> parseTypeParamss(ArrayInitializerExpr expr)
    {
        List<Expression> expressions = expr.getValues();
        List<PGTypeParamsH> pgTypeParamsHs = Lists.newArrayList();
        
        for(Expression expression: expressions)
        {
            if(!(expression instanceof AnnotationExpr))
            {
                throw new PG4jException(expression, "Must be an annotation expression.");
            }
            
            PGTypeParamsH pgTypeParamsH = parseTypeParams((AnnotationExpr) expression);
            pgTypeParamsHs.add(pgTypeParamsH);
        }
        
        return pgTypeParamsHs;
    }
    
    private PGTypeParamsH parseTypeParams(AnnotationExpr expr)
    {
        NormalAnnotationExpr nae = (NormalAnnotationExpr) expr;
        List<MemberValuePair> mvps = nae.getPairs();
        
        if(mvps.isEmpty())
        {
            throw new PG4jException(expr, "No member value pairs.");
        }
        
        if(mvps.size() > 1)
        {
            throw new PG4jException(expr, "There should only be one member value pair");
        }
        
        MemberValuePair mvp = mvps.get(0);
        
        if(!mvp.getName().equals(PGTypeParams.TYPE_PARAMS))
        {
            throw new PG4jException(expr, "The only parameter should be " + PGTypeParams.TYPE_PARAMS);
        }
        
        if(!(mvp.getValue() instanceof ArrayInitializerExpr))
        {
            throw new PG4jException(expr, "The " +
                                          PGTypeParams.TYPE_PARAMS +
                                          " parameter should be an array.");
        }
        
        List<Expression> expressions = ((ArrayInitializerExpr) mvp.getValue()).getValues();
        List<PGTypeParamH> pgTypeParamHs = Lists.newArrayList();
        
        for(Expression expression: expressions)
        {
            if(!(expression instanceof AnnotationExpr))
            {
                throw new PG4jException(expression, "Must be an annotation expression.");
            }
            
            PGTypeParamH pgTypeParamH = parseTypeParam((AnnotationExpr) expression);
            pgTypeParamHs.add(pgTypeParamH);
        }
        
        return new PGTypeParamsH(expr, pgTypeParamHs);
    }
    
    private PGTypeParamH parseTypeParam(AnnotationExpr expr)
    {
        String aname = expr.getName().getName();

        if(!ParseUtils.matchUQClassName(PGTypeParam.class,
                                        aname))
        {
            throw new PG4jException(expr,
                                    "The annotation name " + aname
                                    + " is not valid. Only the " + ParseUtils.uqClassName(PGClass.class)
                                    + " annotation is allowed here.");
        }
        
        NormalAnnotationExpr nae = (NormalAnnotationExpr) expr;
        List<MemberValuePair> mvps = nae.getPairs();
        String paramName = null;
        
        for(MemberValuePair mvp: mvps)
        {
            String name = mvp.getName();
            
            if(name.equals(PGTypeParam.NAME))
            {
                if(!(mvp.getValue() instanceof StringLiteralExpr))
                {
                    throw new PG4jException(nae, "The value of " + PGTypeParam.NAME +
                                                 " must be a string literal.");
                }
                
                paramName = ((StringLiteralExpr) mvp.getValue()).getValue();
            }
            else if(name.equals(PGTypeParam.TYPES))
            {
                if(!(mvp.getValue() instanceof ArrayInitializerExpr))
                {
                    throw new PG4jException(nae, "The value of " + PGTypeParam.TYPES +
                                                 " must be an array.");
                }
                
                Set<PGType> pgTypes = parsePGTypes((ArrayInitializerExpr) mvp.getValue());
            }
            else
            {
                throw new PG4jException(mvp, name + " is not a valid parameter");
            }
        }
        
        return null;
    }
    
    private Set<PGType> parsePGTypes(ArrayInitializerExpr expr)
    {
        List<Expression> expressions = expr.getValues();
        Set<PGType> pgTypes = Sets.newHashSet();
        Set<PGTypeP> pgTypePs = Sets.newHashSet();
        
        for(Expression expression: expressions)
        {
            if(!(expression instanceof FieldAccessExpr))
            {
                throw new PG4jException(expression, "This must be a definition of " + PGType.class);
            }
            
            FieldAccessExpr fae = (FieldAccessExpr) expression;
            
            if(!ParseUtils.matchUQClassName(PGType.class,
                                           ((NameExpr) fae.getScope()).getName()))
            {
                throw new PG4jException(expression, "Expected a " +
                                                    ParseUtils.uqClassName(PGType.class) +
                                                    " object here.");
            }
            
            PGType pgType = PGType.NAMES_MAP.get(fae.getField());
            
            if(pgType == null)
            {
                throw new PG4jException(expression, fae.getField() +
                                                    " is not a valid " + 
                                                    ParseUtils.uqClassName(PGType.class));
            }
            
            if(!pgTypes.add(pgType))
            {
                throw new PG4jException(expression, fae.getField() + " is defined twice.");
            }
         
            for(PGTypeP pgTypeP : pgType.getPrimitives())
            {
                if(!pgTypePs.add(pgTypeP))
                {
                    throw new PG4jException(expression, fae.getField() +
                                                        " represents a redundant type " +
                                                        pgTypeP.getName());
                }
            }
        }
        
        return pgTypes;
    }
}
