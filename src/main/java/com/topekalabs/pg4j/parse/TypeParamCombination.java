/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topekalabs.pg4j.parse;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.Set;

/**
 *
 * @author Topeka Labs
 */
public class TypeParamCombination
{
    private final Set<TypeParam> typeParams = Sets.newHashSet();
    
    public TypeParamCombination(TypeParam... typeParams)
    {
        setTypeParams(typeParams);
    }
    
    private void setTypeParams(TypeParam[] typeParams)
    {
        if(typeParams.length == 0)
        {
            throw new IllegalArgumentException("No type params provided.");
        }
        
        for(TypeParam typeParam: typeParams)
        {
            Preconditions.checkNotNull(typeParam);
            
            if(!this.typeParams.add(typeParam))
            {
                throw new IllegalArgumentException("Provided type params contains a duplicate: " + typeParam);
            }
        }
    }
    
    public Set<TypeParam> getTypeParams()
    {
        return Collections.unmodifiableSet(typeParams);
    }
}
