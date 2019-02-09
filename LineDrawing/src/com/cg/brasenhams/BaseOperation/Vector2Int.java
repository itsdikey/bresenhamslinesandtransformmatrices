package com.cg.brasenhams.BaseOperation;

public class Vector2Int extends VectorBaseInt {


    public Vector2Int(int x,int y)
    {
        mElements = new int[]{x,y};
    }


    public void setX(int x)
    {
        mElements[0] = x;
    }

    public void setY(int y)
    {
        mElements[1] = y;
    }


    public int getX()
    {
        return mElements[0];
    }

    public int getY()
    {
        return mElements[1];
    }

    @Override
    public String toString() {
        return "x: "+getX()+" y: "+getY();
    }
}


