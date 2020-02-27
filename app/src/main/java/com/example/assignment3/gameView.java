package com.example.assignment3;
//Joseph Carbone
//46146768
//jrcarbon 36
//EECS 40 Spring 2019
//5-9-19
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;


public class gameView extends SurfaceView implements SurfaceHolder.Callback {
    private mainThread thread;
    private Piece piece;
    private Piece nextPiece;
    private Board Tetris;
    int cont = 1;
    int whichSpeed;
    boolean firstGo = true;
    boolean endGame= false;
    float clickX, clickY;
    int[][] shape = new int[4][3];
    boolean didPieceCollide = true;// starts as true to generate a random piece

    //initialize gameView
    public gameView(Context context){
        super(context);
        getHolder().addCallback(this);
        thread = new mainThread(getHolder(), this);
        setFocusable(true);
    }

    //Listen for touches, so we can know if button clicked
    @Override
    public boolean onTouchEvent(MotionEvent event){
        float tt = event.getX();
        float ty = event.getY();
        if((tt>420)&&(tt<680)&&(ty>1500)&&(ty<1750)) {
            if(event.getAction()==0){
                whichSpeed = 0;
                clickX = tt;
                clickY = ty;
            }
            if(event.getAction()==1){
                whichSpeed = 1;
            }
        }
        if(cont%2==0) {
                clickX = event.getX();
                clickY = event.getY();
        }
        cont++;
        return true;
    }

    //does nothing
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){
    }

    //on start up
    @Override
    public void surfaceCreated(SurfaceHolder holder){
        int[][] board = new int[25][10];
        Tetris = new Board(board);
        thread.setRunning(true);
        thread.start();
    }

    //destroys the surface
    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    //Main game functionality
    public boolean update() {
        Tetris.updateScore(false);
        /////////////////new piece falls/////////////
        if (didPieceCollide){
            //after a piece collides, before generate new piece, check for tetrises
            while(Tetris.checkForTetris()){
                Tetris.updateScore(true);
                Tetris.deleteLines();
            }

            //generate random type for shape
            Random rand = new Random();
            int type = rand.nextInt(7);//[0-6]
            type += 1;//[1-7]

            //if the piece spawn location already has a piece there, then the game ends
            if(Tetris.getType() != 0){
                endGame = true;
                return true;
            }
            else{
                if(firstGo){ // on first go there is no next piece for piece to become to it needs a piece right away
                    shape = createShape(type);
                    piece = new Piece(shape);
                    type = rand.nextInt(7);
                    type += 1;
                    shape = createShape(type);
                    nextPiece = new Piece(shape);
                    Tetris.updateNextPiece(nextPiece);
                    firstGo = false;
                }else{ //after first go, piece becomes next piece
                    piece = nextPiece;
                    shape = createShape(type);
                    nextPiece = new Piece(shape);
                    Tetris.updateNextPiece(nextPiece);
                }

            }
            didPieceCollide = false;
        }
        Tetris.clearPrevPieceLoc(piece);
        ///////////////////test if clicked on left box////////////////////
        if((clickX>70)&&(clickX<380)&&(clickY>1500)&&(clickY<1750)){//Move Left One space
            if(Tetris.canItMoveLeft(piece)) {
                piece.moveLeft();
            }
            clickX = 0;
            clickY = 0;
        }
        ///////////////////test if clicked on right box////////////////////
        if((clickX>720)&&(clickX<1030)&&(clickY>1500)&&(clickY<1750)){//Move Right One space
            if(Tetris.canItMoveRight(piece)) {
                piece.moveRight();
            }
            clickX = 0;
            clickY = 0;
        }
        ///////////////////test if clicked on rotate box////////////////////
        if((clickX>720)&&(clickX<1030)&&(clickY>1150)&&(clickY<1400)){//Rotate 90
            int[][] tempPieceHolder = piece.getPiece();
            if(Tetris.canItMoveRight(piece)&&Tetris.canItMoveDown(tempPieceHolder)&&Tetris.canItMoveLeft(piece)) {
                piece.rotate();
            }
            clickX = 0;
            clickY = 0;
        }

        ///////////////////test if clicked on down box////////////////////
        if((clickX>420)&&(clickX<680)&&(clickY>1500)&&(clickY<1750)) {
            if(whichSpeed==0){
                piece.speedUp();
            }
            if(whichSpeed==1){
                piece.speedDown();
            }
            clickY = 0;
            clickY = 0;
        }
        didPieceCollide = piece.update(Tetris); // if true on next turn generate new random piece
        Tetris.update(piece);
        return false;
    }

    //this functions creates the shape into an array from the random type
    public int[][] createShape(int type){
        int[][] tempShape = new int[4][3];
        switch(type){
            case(1):
                tempShape[0] = new int[]{0,3,1};
                tempShape[1] = new int[]{0,4,1};
                tempShape[2] = new int[]{0,5,1};
                tempShape[3] = new int[]{0,6,1};
                break;
            case(2):
                tempShape[0] = new int[]{0,3,2};
                tempShape[1] = new int[]{0,4,2};
                tempShape[2] = new int[]{1,4,2};
                tempShape[3] = new int[]{1,5,2};
                break;
            case(3):
                tempShape[0] = new int[]{1,3,3};
                tempShape[1] = new int[]{1,4,3};
                tempShape[2] = new int[]{0,4,3};
                tempShape[3] = new int[]{0,5,3};
                break;
            case(4):
                tempShape[0] = new int[]{0,4,4};
                tempShape[1] = new int[]{0,5,4};
                tempShape[2] = new int[]{1,4,4};
                tempShape[3] = new int[]{1,5,4};
                break;
            case(5):
                tempShape[0] = new int[]{0,3,5};
                tempShape[1] = new int[]{0,4,5};
                tempShape[2] = new int[]{0,5,5};
                tempShape[3] = new int[]{1,5,5};
                break;
            case(6):
                tempShape[0] = new int[]{1,3,6};
                tempShape[1] = new int[]{0,3,6};
                tempShape[2] = new int[]{0,4,6};
                tempShape[3] = new int[]{0,5,6};
                break;
            case(7):
                tempShape[0] = new int[]{0,3,7};
                tempShape[1] = new int[]{0,4,7};
                tempShape[2] = new int[]{0,5,7};
                tempShape[3] = new int[]{1,4,7};
                break;
        }
        return tempShape;
    }
    @Override

    //draws and updates the board
    public void draw(Canvas canvas){
        super.draw(canvas);
        if (canvas != null){
            Rect screen = new Rect(0,0,1000000,1000000);
            Rect lbox = new Rect(70, 1500, 380, 1750);
            Rect rbox = new Rect(720, 1500,1030,1750);
            Rect dbox = new Rect(420,1500,680,1750);
            Rect robox = new Rect(720,1150,1030,1400);
            Paint paint = new Paint();
            paint.setColor(Color.DKGRAY);
            canvas.drawRect(screen, paint);
            paint.setColor(Color.BLACK);
            canvas.drawRect(lbox, paint);
            canvas.drawRect(rbox, paint);
            canvas.drawRect(dbox, paint);
            canvas.drawRect(robox, paint);
            Drawable left = getResources().getDrawable(R.drawable.left, null);
            Drawable right = getResources().getDrawable(R.drawable.right, null);
            Drawable down = getResources().getDrawable(R.drawable.down, null);
            Drawable rotate = getResources().getDrawable(R.drawable.rotate, null);
            Drawable icon = getResources().getDrawable(R.drawable.icon, null);
            paint.setColor(Color.WHITE);
            paint.setTextSize(75);
            canvas.drawText("TETRIS",750,365,paint);
            icon.setBounds( 750,75,1000,300);
            icon.draw(canvas);
            left.setBounds(50,1500,400,1750);
            left.draw(canvas);
            right.setBounds(700,1500,1050,1750);
            right.draw(canvas);
            down.setBounds(400,1500,700,1750);
            down.draw(canvas);
            rotate.setBounds(700,1150,1050,1400);
            rotate.draw(canvas);
            Tetris.draw(canvas);
        }

    }

}
