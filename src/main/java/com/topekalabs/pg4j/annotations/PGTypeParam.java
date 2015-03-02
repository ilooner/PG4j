/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topekalabs.pg4j.annotations;

/**
 *
 * @author Topeka Labs
 */
public @interface PGTypeParam
{
    public static final String NAME = "name";
    public static final String TYPES = "types";
    
    String name();
    PGType[] types();
}
