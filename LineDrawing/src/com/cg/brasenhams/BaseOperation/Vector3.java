package com.cg.brasenhams.BaseOperation;

public class Vector3 extends VectorBase {

    public Vector3()
    {
        elements = new float[3];
    }

    public Vector3(Vector2Int from)
    {
        this();
        setX(from.getX());
        setY(from.getY());
        setZ(1);
    }

    public float getX()
    {
        return elements[0];
    }

    public float getY()
    {
        return elements[1];
    }

    public float getZ()
    {
        return elements[2];
    }
    public void setX(float value)
    {
        elements[0] = value;
    }

    public void setY(float value)
    {
        elements[1] = value;
    }

    public void setZ(float value)
    {
        elements[2] = value;
    }


    public void copyValuesFrom(Vector3 other)
    {
        System.arraycopy(other.elements, 0, elements, 0, 3);
    }

    public Vector2Int toVector2Int()
    {
        return new Vector2Int((int)elements[0],(int) elements[1]);
    }


    @Override
    public String toString() {
        return "X: "+getX()+" Y: "+getY()+" Z: "+getZ();
    }
}
