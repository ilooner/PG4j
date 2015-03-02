/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topekalabs.pg4j.annotations;

import com.topekalabs.pg4j.parse.ParseUtils;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Topeka Labs
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.PARAMETER})
public @interface PGFieldNG
{
   public static final String NAME = ParseUtils.uqClassName(PGFieldNG.class);
   public static final String TYPE_NAME = "typeName";
    
   String typeName(); 
}
