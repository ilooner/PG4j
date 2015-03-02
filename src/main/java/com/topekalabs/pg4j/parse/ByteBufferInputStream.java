/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topekalabs.pg4j.parse;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * Implementation provided here:
 * http://stackoverflow.com/questions/4332264/wrapping-a-bytebuffer-with-an-inputstream
 */
class ByteBufferInputStream extends InputStream
{
    private ByteBuffer bb;
    
    public ByteBufferInputStream(ByteBuffer bb)
    {
        setByteBuffer(bb);
    }
    
    private void setByteBuffer(ByteBuffer bb)
    {
        Preconditions.checkNotNull(bb);
        this.bb = bb;
    }
    
    @Override
    public int read() throws IOException
    {
        if(!bb.hasRemaining())
        {
            return -1;
        }

        return bb.get() & 0xFF;
    }
    
    @Override
    public void close() throws IOException
    {
        bb.position(0);
    }
}
