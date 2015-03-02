/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topekalabs.pg4j.annotations;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Topeka Labs
 */
public enum PGType
{
    /**
     * Represents the boolean primitive type.
     */
    BOOL("BOOL", PGTypeP.BOOL),
    /**
     * Represents the byte primitive type.
     */
    BYTE("BYTE", PGTypeP.BYTE),
    /**
     * Represents the char primitive type.
     */
    CHAR("CHAR", PGTypeP.CHAR),
    /**
     * Represents the short primitive type.
     */
    SHORT("SHORT", PGTypeP.SHORT),
    /**
     * Represents the int primitive type.
     */
    INT("INT", PGTypeP.INT),
    /**
     * Represents the long primitive type.
     */
    LONG("LONG", PGTypeP.LONG),
    /**
     * Represents the float primitive type.
     */
    FLOAT("FLOAT", PGTypeP.FLOAT),
    /**
     * Represents the double primitive type.
     */
    DOUBLE("DOUBLE", PGTypeP.DOUBLE),
    /**
     * Represents all primitive types.
     */
    ALL("ALL", PGTypeP.BOOL, PGTypeP.BYTE, PGTypeP.CHAR, PGTypeP.SHORT,
        PGTypeP.INT, PGTypeP.LONG, PGTypeP.FLOAT, PGTypeP.DOUBLE),
    /**
     * Represents all primitive types except for boolean and char.
     */
    NUMBER("NUMBER", PGTypeP.BYTE, PGTypeP.SHORT, PGTypeP.INT,
           PGTypeP.LONG, PGTypeP.FLOAT, PGTypeP.DOUBLE),
    /**
     * Represents all primitive types except for float and double.
     */
    DISCRETE("DISCRETE", PGTypeP.BOOL, PGTypeP.BYTE, PGTypeP.CHAR,
             PGTypeP.SHORT, PGTypeP.INT, PGTypeP.LONG),
    /**
     * Represents the byte, short, int, and long primitive types.
     */
    DISCRETE_NUM("DISCRETE_NUM", PGTypeP.BYTE, PGTypeP.SHORT,
                 PGTypeP.INT, PGTypeP.LONG),
    /**
     * Represents the float and double primitive types.
     */
    SCI("SCI", PGTypeP.FLOAT, PGTypeP.DOUBLE);
    
    public static final Map<String, PGType> NAMES_MAP;
    
    static
    {
        Map<String, PGType> namesMap = Maps.newHashMap();
        
        for(PGType pgType: PGType.values())
        {
            namesMap.put(pgType.getName(), pgType);
        }
        
        NAMES_MAP = Collections.unmodifiableMap(namesMap);
    }
    
    private Set<PGTypeP> primitives;
    private String name;
    
    private PGType(String name,
                   PGTypeP... types)
    {
        this.name = name;
        primitives = Sets.newHashSet(types);
    }
    
    public Set<PGTypeP> getPrimitives()
    {
        return Collections.unmodifiableSet(primitives);
    }
    
    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return "PGType{" + "primitives=" + primitives + '}';
    }
}
