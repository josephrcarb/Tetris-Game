package com.example.assignment3;
//Joseph Carbone
//46146768
//jrcarbon 36
//EECS 40 Spring 2019
//5-9-19
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class mainThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private gameView game;
    private boolean running;
    public static Canvas canvas;
    private int FPS = 30;
    private double avgFPS;
    boolean gameEnd;

    public mainThread(SurfaceHolder surfaceHolder, gameView game){
        super();
        this.surfaceHolder = surfaceHolder;
        this.game = game;

    }

    @Override
    public void run(){

        while(running){
            canvas = null;
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized(surfaceHolder) {
                    gameEnd = this.game.update();
                    this.game.draw(canvas);
                    if(gameEnd){
                        game.surfaceDestroyed(surfaceHolder);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public void setRunning(boolean isRunning) {
        running = isRunning;
    }

}
