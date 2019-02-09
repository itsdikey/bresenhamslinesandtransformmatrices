package com.cg.brasenhams;

import com.cg.brasenhams.BaseOperation.Vector2Int;
import com.cg.brasenhams.Plot.Plotter;
import javafx.scene.input.KeyCode;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    public static BufferedImage gradientSetRaster(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();

        WritableRaster raster = img.getRaster();
        int[] pixel = new int[3]; //RGB

        for (int y = 0; y < height; y++) {
            int val = (int) (y * 255f / height);
            for (int shift = 1; shift < 3; shift++) {
                pixel[shift] = val;
            }

            for (int x = 0; x < width; x++) {
                raster.setPixel(x, y, pixel);

            }
        }

        return img;
    }



    public static void main(String... args) {
        Frame w = new Frame("Raster");  //window
        final int imageWidth = 500;
        final int imageHeight = 500;

        w.setSize(imageWidth,imageHeight);
        w.setLocation(100,100);
        w.setVisible(true);

        Graphics g = w.getGraphics();

        BufferedImage img = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        gradientSetRaster(img);
        g.drawImage(img, 0, 0, (img1, infoflags, x, y, width, height) -> false);  //draw the image. You can think of this as the display method.


        Polygon polygon = new Polygon();


        Queue<Vector2Int> points = new LinkedBlockingQueue<>();

        w.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1) {
                    gradientSetRaster(img);
                    polygon.addPoint(new Vector2Int(e.getX(),e.getY()));
                    polygon.draw(img.getRaster());

                    g.drawImage(img, 0, 0, (img1, infoflags, x, y, width, height) -> false);
                }
            }
        });

        w.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int tx=0,ty=0;
                float sx=1f, sy=1f;
                float angle = 0;
                if (e.getKeyCode() == KeyCode.UP.getCode())
                {
                    ty = -3;
                }
                if (e.getKeyCode() == KeyCode.DOWN.getCode())
                {
                    ty = 3;
                }
                if (e.getKeyCode()== KeyCode.LEFT.getCode())
                {
                    tx = -3;
                }
                if (e.getKeyCode()== KeyCode.RIGHT.getCode())
                {
                    tx = 3;
                }

                if (e.getKeyCode()== KeyCode.RIGHT.getCode())
                {
                    tx = 3;
                }

                if (e.getKeyCode()== KeyCode.S.getCode())
                {
                    sx = 1.1f;
                    sy = 1.1f;
                }

                if (e.getKeyCode()== KeyCode.D.getCode())
                {
                    sx= 1/1.1f;
                    sy = sx;

                }

                if (e.getKeyCode()== KeyCode.R.getCode())
                {
                   angle = -10;
                }

                if (e.getKeyCode()== KeyCode.T.getCode())
                {
                   angle = 10;
                }

                polygon.translate(tx,ty);
                polygon.scale(sx,sy);
                polygon.rotate(angle);
                gradientSetRaster(img);
                polygon.draw(img.getRaster());
                g.drawImage(img, 0, 0, (img1, infoflags, x, y, width, height) -> false);
            }
        });


        w.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                w.dispose();
                g.dispose();
                System.exit(0);
            }
        });
    }




}
