package com.cg.brasenhams.BaseOperation;

public class Matrix3x3 {
    private float[][] mElements;
    public Matrix3x3()
    {
        mElements = new float[3][3];
    }

    public Matrix3x3 multiply(Matrix3x3 other)
    {
        Matrix3x3 result = new Matrix3x3();
        for(int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                for (int ctr = 0; ctr<3;ctr++)
                {
                    result.mElements[i][j] += mElements[i][ctr]*other.mElements[ctr][j];
                }
            }
        }
        return result;
    }

    public Vector3 multiply(Vector3 vector)
    {
        Vector3 result = new Vector3();
        for(int row=0;row<3;row++)
        {
            for(int col=0;col<3;col++)
            {
                result.elements[row] += vector.elements[col]*mElements[row][col];
            }
        }
        return result;
    }

    public static Matrix3x3 getIdentity()
    {
        Matrix3x3 identity = new Matrix3x3();
        identity.mElements[0][0] = identity.mElements[1][1] = identity.mElements[2][2] = 1;
        return identity;
    }

    public static Matrix3x3 createRotation(float angle)
    {
        Matrix3x3 rotation = getIdentity();
        rotation.mElements[0][0] = (float)Math.cos(angle);
        rotation.mElements[0][1] = -(float)Math.sin(angle);
        rotation.mElements[1][0] =  (float)Math.sin(angle);
        rotation.mElements[1][1] = (float)Math.cos(angle);
        return  rotation;
    }


    public static Matrix3x3 createTranslation(float tx,float ty)
    {
        Matrix3x3 translation = getIdentity();
        translation.mElements[0][2] = tx;
        translation.mElements[1][2] = ty;
        return  translation;
    }

    public static Matrix3x3 createScaling(float sx,float sy)
    {
        Matrix3x3 scaling = getIdentity();
        scaling.mElements[0][0] = sx;
        scaling.mElements[1][1] = sy;
        return  scaling;
    }


}
