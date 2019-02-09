package com.cg.brasenhams.Plot;

import com.cg.brasenhams.BaseOperation.Vector2Int;

import java.awt.image.WritableRaster;

public class Plotter {

     public static void plotLine(Vector2Int first, Vector2Int second, WritableRaster raster ) {
        int[] pixel = {255,255,255};

        //Slope < 1
        if(Math.abs(second.getY()-first.getY())<Math.abs((second.getX()-first.getX())))
        {

            if(first.getX()<second.getX())
            {
                DrawLineLessThan1(first,second,raster,pixel);
            }
            else
            {
                DrawLineLessThan1(second,first,raster,pixel);
            }
        }
        else
        {
            if(first.getY()<second.getY())
            {
                DrawLineMoreThan1(first,second,raster,pixel);
            }
            else
            {
                DrawLineMoreThan1(second,first,raster,pixel);
            }
        }
    }

    private static void DrawLineLessThan1(Vector2Int first, Vector2Int second, WritableRaster raster, int[] pixel) {
        int difY = second.getY() - first.getY();
        int difX = second.getX() - first.getX();
        //Checking if we go down or up
        int yStep = difY<0?-1:1;

        //Making change positive
        difY *= yStep;
        int xi = first.getX();
        int yi = first.getY();

        //Decision variable
        int pi = 2*difY - difX;

        do
        {

            if(raster.getBounds().contains(xi,yi))
            {
                raster.setPixel(xi,yi,pixel);
            }


            xi++;
            if(pi>0)
            {
                yi+=yStep;
                pi+=(2*difY-2*difX);
            }
            else
                pi+=2*difY;


        }while(xi!=second.getX());

        if(!raster.getBounds().contains(second.getX(),second.getY()))
        {
            return;
        }
        raster.setPixel(second.getX(),second.getY(),pixel);
    }


    //I have literally copied above function and changed y to x
    private static void DrawLineMoreThan1(Vector2Int first, Vector2Int second, WritableRaster raster, int[] pixel) {
        int difY = second.getY() - first.getY();
        int difX = second.getX() - first.getX();
        int xStep = difX<0?-1:1;
        difX *= xStep;
        int xi = first.getX();
        int yi = first.getY();
        int pi = 2*difX - difY;
        do
        {




            if(raster.getBounds().contains(xi,yi))
            {
                raster.setPixel(xi,yi,pixel);
            }
            yi++;
            if(pi>0)
            {
                xi+=xStep;
                pi+=(2*difX-2*difY);
            }
            else
                pi+=2*difX;

            if(!raster.getBounds().contains(xi,yi))
            {
                continue;
            }

        }while(yi!=second.getY());

        if(!raster.getBounds().contains(second.getX(),second.getY()))
        {
            return;
        }

        raster.setPixel(second.getX(),second.getY(),pixel);
    }
}
