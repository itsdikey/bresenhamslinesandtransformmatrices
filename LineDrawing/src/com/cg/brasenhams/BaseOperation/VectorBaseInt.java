package com.cg.brasenhams.BaseOperation;

public abstract class VectorBaseInt {
    public int[] mElements;
    public int dot(VectorBaseInt other)
    {
        int result = 0;
        for (int i=0;i<mElements.length;i++)
        {
            result+=other.mElements[i]*mElements[i];
        }
        return  result;
    }
}
