package com.cg.brasenhams.BaseOperation;

public abstract class VectorBase {
    public float[] elements;

    public float dot(VectorBase other)
    {
        float result = 0;
        for (int i=0;i<elements.length;i++)
        {
            result+=other.elements[i]*elements[i];
        }
        return  result;
    }
}
