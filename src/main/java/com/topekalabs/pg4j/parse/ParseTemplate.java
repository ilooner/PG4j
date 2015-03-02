/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topekalabs.pg4j.parse;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;

/**
 *
 * @author Topeka Labs
 */
public class ParseTemplate
{
    private ParseTemplate()
    {
        //Do nothing
    }
    
    public static void generateTemplates(File srcFile,
                                         File outputDirectory) throws FileNotFoundException,
                                                                      ParseException,
                                                                      IOException
    {
        CompilationUnit cu;
        
        FileChannel fc = FileChannel.open(srcFile.toPath(), StandardOpenOption.READ);
        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0L, fc.size());
        fc.close();
        ByteBufferInputStream bbis = new ByteBufferInputStream(mbb);
        
        try
        {
            cu = JavaParser.parse(bbis);
        }
        finally
        {
            bbis.close();
        }
        
        new ValidationVisitor().visit(cu, null);
    }
}
