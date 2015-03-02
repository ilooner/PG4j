/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topekalabs.pg4j.parse;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.topekalabs.pg4j.annotations.PGType;
import com.topekalabs.pg4j.annotations.PGTypeP;
import japa.parser.ast.Node;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Topeka Labs
 */
public class PGTypeParamH
{
    private String name;
    private List<PGType> pgTypes;
    private Set<PGTypeP> pgTypePs;
    
    public PGTypeParamH(Node node,
                        String name,
                        List<PGType> pgTypes)
    {
        setName(name);
        setPGTypes(pgTypes);
        
        validate(node);
    }
    
    private void validate(Node node)
    {
        pgTypePs = Sets.newHashSet();
        
        for(PGType pgType: pgTypes)
        {
            for(PGTypeP pgTypeP: pgType.getPrimitives())
            {
                if(!pgTypePs.add(pgTypeP))
                {
                    throw new PG4jException(node, "Dublicate type: " + pgTypeP.getName());
                }
            }
        }
    }
    
    private void setName(String name)
    {
        Preconditions.checkNotNull(name);
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
    
    private void setPGTypes(List<PGType> pgTypes)
    {
        Preconditions.checkNotNull(pgTypes);
        this.pgTypes = pgTypes;
    }
    
    public List<PGType> getPGTypes()
    {
        return Collections.unmodifiableList(pgTypes);
    }
    
    public Set<PGTypeP> getPGTypePs()
    {
        return pgTypePs;
    }
}
