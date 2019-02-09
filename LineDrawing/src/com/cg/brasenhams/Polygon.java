package com.cg.brasenhams;

import com.cg.brasenhams.BaseOperation.Matrix3x3;
import com.cg.brasenhams.BaseOperation.Vector2Int;
import com.cg.brasenhams.BaseOperation.Vector3;
import com.cg.brasenhams.Plot.Plotter;

import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Polygon {
    List<Vector3> mPoints;
    public Polygon()
    {
        mPoints = new ArrayList<>();
    }

    public void addPoint(Vector2Int point)
    {
        mPoints.add(new Vector3(point));
    }

    public void draw(WritableRaster raster)
    {
        if(mPoints.size()<2)
            return;
        for (int i=0; i<mPoints.size()-1;i++)
        {
            Plotter.plotLine(mPoints.get(i).toVector2Int(),
                    mPoints.get(i+1).toVector2Int(),raster);
        }

        Plotter.plotLine(mPoints.get(0).toVector2Int(),
                mPoints.get(mPoints.size()-1).toVector2Int(),raster);
    }

    public void translate(int x, int y)
    {
        Matrix3x3 translation = Matrix3x3.createTranslation(x,y);
        for (Vector3 mPoint : mPoints) {
       //     System.out.print(mPoint+" - ");
            mPoint.copyValuesFrom(translation.multiply(mPoint));
         //   System.out.println(mPoint);
        }
    }

    public void scale(float sx, float sy)
    {
        Vector3 position = getPos();
        Matrix3x3 scale = Matrix3x3.createScaling(sx,sy);
        Matrix3x3 translationToOrigin = Matrix3x3.createTranslation(-position.getX(),-position.getY());
        Matrix3x3 translationBack = Matrix3x3.createTranslation(position.getX(),position.getY());
        Matrix3x3 transform = translationBack.multiply(scale).multiply(translationToOrigin);//.multiply(translationBack);
        for (Vector3 mPoint : mPoints) {
            System.out.print(mPoint+" - ");
           /* mPoint.copyValuesFrom(translationToOrigin.multiply(mPoint));
            mPoint.copyValuesFrom(scale.multiply(mPoint));*/
            mPoint.copyValuesFrom(transform.multiply(mPoint));
            System.out.println(mPoint);
        }
    }


    public void rotate(float angle)
    {
        Vector3 position = getPos();
        Matrix3x3 rotation = Matrix3x3.createRotation((float)Math.toRadians(angle));
        Matrix3x3 translationToOrigin = Matrix3x3.createTranslation(-position.getX(),-position.getY());
        Matrix3x3 translationBack = Matrix3x3.createTranslation(position.getX(),position.getY());
        Matrix3x3 transform = translationBack.multiply(rotation).multiply(translationToOrigin);//.multiply(translationBack);
        for (Vector3 mPoint : mPoints) {
          //  System.out.print(mPoint+" - ");
            mPoint.copyValuesFrom(transform.multiply(mPoint));

           // System.out.println(mPoint);
        }
    }

    public Vector3 getPos()
    {
        float x=0;
        float y=0;
        for (Vector3 mPoint : mPoints) {
            x+=mPoint.getX();
            y+=mPoint.getY();
        }
        Vector3 result = new Vector3();
        result.setX(x/mPoints.size());
        result.setY(y/mPoints.size());
        result.setZ(1);
        return result;
    }
}


