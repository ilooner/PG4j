/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topekalabs.pg4j.parse;

/**
 *
 * @author Topeka Labs
 */
public class ParseUtils
{
    private ParseUtils()
    {
        //Do nothing
    }
    
    public static String uqClassName(Class<?> clazz)
    {
        String fqClassName = clazz.getName();
        int startIndex = fqClassName.lastIndexOf(".");
        
        if(startIndex < 0)
        {
            return fqClassName;
        }
        
        startIndex++;
        
        if(startIndex == fqClassName.length())
        {
            throw new RuntimeException("This should never happend");
        }
        
        return fqClassName.substring(startIndex);
    }
    
    public static boolean matchUQClassName(Class<?> clazz,
                                           String className)
    {
        return uqClassName(clazz).equals(className);
    }
    
    public static boolean matchFQClassName(Class<?> clazz,
                                           String className)
    {
        return clazz.getName().equals(className);
    }
}
