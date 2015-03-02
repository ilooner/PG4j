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
public @interface PGTypeParams
{
    public static final String TYPE_PARAMS = "typeParams";
    
    PGTypeParam[] typeParams();
}
