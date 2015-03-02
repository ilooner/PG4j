/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topekalabs.pg4j.parse;

import com.google.common.base.Preconditions;
import japa.parser.ast.Node;
import java.util.List;

/**
 *
 * @author Topeka Labs
 */
class PGClassH
{
    private String classRenameRegex;
    private List<PGTypeParamsH> pgTypeParamsHs;
    
    PGClassH(Node node,
             String classRenameRegex,
             List<PGTypeParamsH> pgTypeParamsHs)
    {
        setClassRenameRegex(classRenameRegex);
        setPGTypeParamsHs(pgTypeParamsHs);
        
        validate(node);
    }
    
    private void validate(Node node)
    {
        Preconditions.checkNotNull(node);
        
    }
    
    private void setClassRenameRegex(String classRenameRegex)
    {
        Preconditions.checkNotNull(classRenameRegex);
        this.classRenameRegex = classRenameRegex;
    }
    
    public String getClassRenameRegex()
    {
        return classRenameRegex;
    }
    
    private void setPGTypeParamsHs(List<PGTypeParamsH> pgTypeParamsHs)
    {
        Preconditions.checkNotNull(pgTypeParamsHs);
        this.pgTypeParamsHs = pgTypeParamsHs;
    }
    
    public List<PGTypeParamsH> getPGTypeParamsHs()
    {
        return pgTypeParamsHs;
    }
}
