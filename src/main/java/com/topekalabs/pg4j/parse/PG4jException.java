/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topekalabs.pg4j.parse;

import japa.parser.ast.Node;

/**
 *
 * @author Topeka Labs
 */
public class PG4jException extends RuntimeException
{
    public PG4jException(Node node, String message)
    {
        super("At " + lineNumbers(node) + " there is an error: " + message);
    }
    
    public static String lineNumbers(Node node)
    {
        return "from " + node.getBeginLine() + ":" + node.getBeginColumn() +
               " to " + node.getEndLine() + ":" + node.getEndColumn();
    }
    
    public static void throwEx(boolean throwEx, Node node, String message)
    {
        if(throwEx)
        {
            throw new PG4jException(node, message);
        }
    }
}
