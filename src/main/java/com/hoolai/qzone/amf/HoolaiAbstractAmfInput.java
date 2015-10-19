package com.hoolai.qzone.amf;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import flex.messaging.io.BeanProxy;
import flex.messaging.io.SerializationContext;
import flex.messaging.io.amf.ActionMessageInput;
import flex.messaging.io.amf.ActionMessageOutput;
import flex.messaging.util.XMLUtil;

/**
 * A deserializer of AMF protocol data.
 *
 * @author Peter Farland (pfarland@macromedia.com)
 *
 * @see ActionMessageOutput
 * @exclude
 */
public abstract class HoolaiAbstractAmfInput extends HoolaiAmfIO implements ActionMessageInput
{
    protected BeanProxy beanProxy = new BeanProxy();

    protected DataInputStream in = null;

    /**
     * Construct a deserializer without connecting it to an input stream.
     * @param context serialization parameters.
     */
    public HoolaiAbstractAmfInput(SerializationContext context)
    {
        super(context);
    }

    /**
     * Internal use
     * @exclude
     */
    public void setInputStream(InputStream in)
    {
        this.in = new DataInputStream(in);
    }

    protected Object stringToDocument(String xml)
    {
        // FIXME: Temporary workaround for bug 194815
        if (xml != null && xml.indexOf('<') == -1)
            return xml;

        return XMLUtil.stringToDocument(xml, !(context.legacyXMLNamespaces));
    }
    
    //
    // java.io.ObjectInput IMPLEMENTATIONS
    //

    /** {@inheritDoc} */
    public int available() throws IOException
    {
        return in.available();
    }

    /** {@inheritDoc} */
    public void close() throws IOException
    {
        in.close();
    }

    /** {@inheritDoc} */
    public int read() throws IOException
    {
        return in.read();
    }

    /** {@inheritDoc} */
    public int read(byte[] bytes) throws IOException
    {
        return in.read(bytes);
    }

    /** {@inheritDoc} */
    public int read(byte[] bytes, int offset, int length) throws IOException
    {
        return in.read(bytes, offset, length);
    }

    /** {@inheritDoc} */
    public long skip(long n) throws IOException
    {
        return in.skip(n);
    }

    /** {@inheritDoc} */
    public int skipBytes(int n) throws IOException
    {
        return in.skipBytes(n);
    }

    //
    // java.io.DataInput IMPLEMENTATIONS
    //

    /** {@inheritDoc} */
    public boolean readBoolean() throws IOException
    {
        return in.readBoolean();
    }

    /** {@inheritDoc} */
    public byte readByte() throws IOException
    {
        return in.readByte();
    }

    /** {@inheritDoc} */
    public char readChar() throws IOException
    {
        return in.readChar();
    }

    /** {@inheritDoc} */
    public double readDouble() throws IOException
    {
        return in.readDouble();
    }

    /** {@inheritDoc} */
    public float readFloat() throws IOException
    {
        return in.readFloat();
    }

    /** {@inheritDoc} */
    public void readFully(byte[] bytes) throws IOException
    {
        in.readFully(bytes);
    }

    /** {@inheritDoc} */
    public void readFully(byte[] bytes, int offset, int length) throws IOException
    {
        in.readFully(bytes, offset, length);
    }

    /** {@inheritDoc} */
    public int readInt() throws IOException
    {
        return in.readInt();
    }

    /**
     *  Reads the next line of text from the input stream.
     * @deprecated
     */
    public String readLine() throws IOException
    {
        return in.readLine();
    }

    /** {@inheritDoc} */
    public long readLong() throws IOException
    {
        return in.readLong();
    }

    /** {@inheritDoc} */
    public short readShort() throws IOException
    {
        return in.readShort();
    }

    /** {@inheritDoc} */
    public int readUnsignedByte() throws IOException
    {
        return in.readUnsignedByte();
    }

    /** {@inheritDoc} */
    public int readUnsignedShort() throws IOException
    {
        return in.readUnsignedShort();
    }

    /** {@inheritDoc} */
    public String readUTF() throws IOException
    {
        return in.readUTF();
    }
}
