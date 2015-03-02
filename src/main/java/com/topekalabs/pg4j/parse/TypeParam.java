/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topekalabs.pg4j.parse;

import com.google.common.base.Preconditions;
import com.topekalabs.pg4j.annotations.PGTypeP;
import java.util.Objects;

/**
 *
 * @author Topeka Labs
 */
public class TypeParam
{
    private String name;
    private PGTypeP type;
    
    public TypeParam(String name, PGTypeP type)
    {
        setName(name);
        setType(type);
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
    
    private void setType(PGTypeP type)
    {
        Preconditions.checkNotNull(type);
        this.type = type;
    }
    
    public PGTypeP getType()
    {
        return type;
    }

    @Override
    public String toString()
    {
        return "TypeParam{" + "name=" + name + ", type=" + type + '}';
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.type);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj == null)
        {
            return false;
        }
        if(getClass() != obj.getClass())
        {
            return false;
        }
        final TypeParam other = (TypeParam) obj;
        if(!Objects.equals(this.name, other.name))
        {
            return false;
        }
        if(this.type != other.type)
        {
            return false;
        }
        return true;
    }
}
