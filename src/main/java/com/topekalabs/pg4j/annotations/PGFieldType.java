/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topekalabs.pg4j.annotations;

import java.lang.annotation.Documented;

/**
 *
 * @author Topeka Labs
 */
@Documented
public @interface PGFieldType
{
    public static final String TYPE_PARAMS = "typeParams";
    public static final String CLAZZ = "clazz";
    
    PGTypeParams[] typeParams();
    Class clazz();
}
