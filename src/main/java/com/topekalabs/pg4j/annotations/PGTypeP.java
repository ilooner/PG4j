/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topekalabs.pg4j.annotations;

import com.google.common.base.Preconditions;

/**
 *
 * @author Topeka Labs
 */
public enum PGTypeP
{
    /**
     * Represents the boolean primitive type.
     */
    BOOL("Bool"),
    /**
     * Represents the byte primitive type.
     */
    BYTE("Byte"),
    /**
     * Represents the char primitive type.
     */
    CHAR("Char"),
    /**
     * Represents the short primitive type.
     */
    SHORT("Short"),
    /**
     * Represents the int primitive type.
     */
    INT("Int"),
    /**
     * Represents the long primitive type.
     */
    LONG("Long"),
    /**
     * Represents the float primitive type.
     */
    FLOAT("Float"),
    /**
     * Represents the double primitive type.
     */
    DOUBLE("Double");
    
    private String name;
    
    PGTypeP(String name)
    {
        setName(name);
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

    @Override
    public String toString()
    {
        return "PGTypeP{" + "name=" + name + '}';
    }
}
