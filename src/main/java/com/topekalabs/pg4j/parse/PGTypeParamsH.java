/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topekalabs.pg4j.parse;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import japa.parser.ast.Node;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Topeka Labs
 */
public class PGTypeParamsH
{
    private List<PGTypeParamH> pgTypeParamHs;
    
    public PGTypeParamsH(Node node,
                         List<PGTypeParamH> pgTypeParamHs)
    {
        setPGTypeParamHs(pgTypeParamHs);
        validate(node);
    }
    
    private void validate(Node node)
    {
        Preconditions.checkNotNull(node);
        
        Set<String> names = Sets.newHashSet();
        
        for(PGTypeParamH pgTypeParamH: pgTypeParamHs)
        {
            if(!names.add(pgTypeParamH.getName()))
            {
                throw new PG4jException(node, "Dublicate type name: " + pgTypeParamH.getName());
            }
        }
    }
    
    private void setPGTypeParamHs(List<PGTypeParamH> pgTypeParamHs)
    {
        Preconditions.checkNotNull(pgTypeParamHs);
        this.pgTypeParamHs = pgTypeParamHs;
    }
    
    public List<PGTypeParamH> getPGTypeParamsHs()
    {
        return Collections.unmodifiableList(pgTypeParamHs);
    }
}
