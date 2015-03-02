/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topekalabs.pg4j.annotations;

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
@Target({ElementType.TYPE})
public @interface PGClass
{
    public static final String CLASS_RENAME_REGEX_DEFAULT = "";
    public static final String CLASS_RENAME_REGEX = "classRenameRegex";
    public static final String TYPE_PARAMS = "typeParams";
    
    String classRenameRegex() default CLASS_RENAME_REGEX_DEFAULT;
    PGTypeParams[] typeParams();
}
