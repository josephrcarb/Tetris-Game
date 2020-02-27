package com.example.assignment3;
//Joseph Carbone
//46146768
//jrcarbon 36
//EECS 40 Spring 2019
//5-9-19
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

 class Board {
    private int height = 25;
    private int width = 10;
    private int[][] board;
    private int[][] nextPieceBoard = new int[4][6];
    private float score = 0;
    private Paint paint = new Paint();

    //////Initialize the tetris board///////
     Board(int[][] boardInput) {
            board = boardInput;
    }


    //returns the type of piece that is at that location///
     int getType(){
        return board[1][4];
    }

    //This function clears the location the piece was just at//
     void clearPrevPieceLoc(Piece piece){
        board[piece.oneX()][piece.oneY()] = 0;
        board[piece.twoX()][piece.twoY()] = 0;
        board[piece.threeX()][piece.threeY()] = 0;
        board[piece.fourX()][piece.fourY()] = 0;
    }

    //This functions lets a piece know if there is a wall or piece to the right of it
     boolean canItMoveRight(Piece piece){
        int[][]shape = piece.getPiece();
        for(int i=0;i<4;i++){
            if((shape[i][1]+1)<10) {
                if (board[shape[i][0]][shape[i][1] + 1] != 0) {  //If the piece next to it is not empty
                    if (!((((shape[i][0]) == shape[0][0]) && (shape[i][1] + 1 == shape[0][1])) || //is piece shapei == shape0?
                            ((shape[i][0] == shape[1][0]) && (shape[i][1] + 1 == shape[1][1])) ||//is piece shapei == shape0?
                            ((shape[i][0] == shape[2][0]) && (shape[i][1] + 1 == shape[2][1])) ||//is piece shapei == shape0?
                            ((shape[i][0] == shape[3][0]) && (shape[i][1] + 1 == shape[3][1])))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //This functions lets a piece know if there is a border or piece below it
     boolean canItMoveDown(int[][] shape){
        for(int i=0;i<4;i++){
            if((shape[i][0] + 1)<25) {
                if (board[shape[i][0]+1][shape[i][1]] != 0){  //If the piece next to it is not empty
                    if(!((((shape[i][0]+1)==shape[0][0])&&(shape[i][1]==shape[0][1])) || //is piece shapei == shape0?
                               ((shape[i][0]+1==shape[1][0])&&(shape[i][1]==shape[1][1])) ||//is piece shapei == shape0?
                               ((shape[i][0]+1==shape[2][0])&&(shape[i][1]==shape[2][1])) ||//is piece shapei == shape0?
                               ((shape[i][0]+1== shape[3][0])&&(shape[i][1]==shape[3][1])))){
                                   return false;
                    }
                }
            }
        }
        for(int i=0;i<4;i++){
            if(shape[i][0]+1==25){
                return false;
            }
        }
        return true;
    }

    //This functions adds to the scoreboard, .05 every frame, 125 for every Tetris
     void updateScore(boolean TetrisHappened){
        if (TetrisHappened){
            score += 125;
        }
        score+= .05;
    }

    //This functions lets a piece know if there is a wall or piece to the left of it
     boolean canItMoveLeft(Piece piece){
        int[][]shape = piece.getPiece();
        for(int i=0;i<4;i++){
            if((shape[i][1]-1)>0) {
                if (board[shape[i][0]][shape[i][1] - 1] != 0) {  //If the piece next to it is not empty
                    if (!((((shape[i][0]) == shape[0][0]) && (shape[i][1] - 1 == shape[0][1])) || //is piece shapei == shape0?
                            ((shape[i][0] == shape[1][0]) && (shape[i][1] - 1 == shape[1][1])) ||//is piece shapei == shape0?
                            ((shape[i][0] == shape[2][0]) && (shape[i][1] - 1 == shape[2][1])) ||//is piece shapei == shape0?
                            ((shape[i][0] == shape[3][0]) && (shape[i][1] - 1 == shape[3][1])))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //This function is for displaying the next piece
     void updateNextPiece(Piece nextPiece){
        for (int j = 0; j <4 ; j++) { // clear the next piece board
            for (int i = 0; i < 6; i++) {
                nextPieceBoard[j][i] = 0;
            }
        }
        switch(nextPiece.Type()){
            case(1):
                nextPieceBoard[2][1] = nextPiece.Type();
                nextPieceBoard[2][2] = nextPiece.Type();
                nextPieceBoard[2][3] = nextPiece.Type();
                nextPieceBoard[2][4] = nextPiece.Type();
                break;
            case(2):
                nextPieceBoard[1][2] = nextPiece.Type();
                nextPieceBoard[1][3] = nextPiece.Type();
                nextPieceBoard[2][3] = nextPiece.Type();
                nextPieceBoard[2][4] = nextPiece.Type();
                break;
            case(3):
                nextPieceBoard[1][3] = nextPiece.Type();
                nextPieceBoard[1][4] = nextPiece.Type();
                nextPieceBoard[2][3] = nextPiece.Type();
                nextPieceBoard[2][2] = nextPiece.Type();
                break;
            case(4):
                nextPieceBoard[1][3] = nextPiece.Type();
                nextPieceBoard[1][4] = nextPiece.Type();
                nextPieceBoard[2][3] = nextPiece.Type();
                nextPieceBoard[2][4] = nextPiece.Type();
                break;
            case(5):
                nextPieceBoard[1][2] = nextPiece.Type();
                nextPieceBoard[1][3] = nextPiece.Type();
                nextPieceBoard[1][4] = nextPiece.Type();
                nextPieceBoard[2][4] = nextPiece.Type();
                break;
            case(6):
                nextPieceBoard[1][2] = nextPiece.Type();
                nextPieceBoard[1][3] = nextPiece.Type();
                nextPieceBoard[1][4] = nextPiece.Type();
                nextPieceBoard[2][2] = nextPiece.Type();
                break;
            case(7):
                nextPieceBoard[1][2] = nextPiece.Type();
                nextPieceBoard[1][3] = nextPiece.Type();
                nextPieceBoard[1][4] = nextPiece.Type();
                nextPieceBoard[2][3] = nextPiece.Type();
                break;
        }
    }

    //after a piece makes a move, the board is then updated to where that piece is
     void update(Piece piece){
        board[piece.oneX()][piece.oneY()] = piece.Type();
        board[piece.twoX()][piece.twoY()] = piece.Type();
        board[piece.threeX()][piece.threeY()] = piece.Type();
        board[piece.fourX()][piece.fourY()] = piece.Type();
    }

    //this function deletes the line after a Tetris occurs, it allows makes the blocks fall after the tetris occurs
     void deleteLines(){
        int lineBlocks = 0;
        int tetrisLine = -1;
        for (int j = 24; j > -1; j--) { // delete the row
            for (int i=9;i>-1;i--){
                if(board[j][i] != 0){
                    lineBlocks++;
                }
                if (lineBlocks == 10){
                    for(int k=0;k<9;k++){
                        board[j][k] = 0;
                    }
                    lineBlocks = 0;
                    tetrisLine = j;
                    break;
                }
            }
            if(tetrisLine != -1){
                break;
            }
        }
        //push everything down
        if (tetrisLine>=0){
            for (int n = tetrisLine; n > -1; n--) {
                for (int m = 9; m > -1; m--) {//starts at bottom right goes up
                    if(n == 0){
                        board[n][m] = 0;
                    }else {
                        board[n][m] = board[n - 1][m];
                    }
                }
            }
        }
    }

    //this function checks if there is still a tetris that needs to be deleted
     boolean checkForTetris(){
        int squares = 0;
        for(int j = 24; j > -1; j--){
            for(int i = 9; i > -1; i--){
                if(board[j][i] != 0){ // check if row is full
                    squares++;
                }
                if(squares == 10) {
                    return true; //There is at least one Tetris
                }
            }
            squares = 0;
        }
        return false;
    }

    //draws the entire board, and the next piece board
     void draw(Canvas canvas){
        Rect scoreBoard = new Rect(720,750,1030,1000 );
        paint.setColor(Color.BLACK);
        canvas.drawRect(scoreBoard,paint);
        int scoreINT = Math.round(score);
        paint.setColor(Color.WHITE);
        String scoreString = Integer.toString(scoreINT);
        paint.setTextSize(100);
        canvas.drawText(scoreString,775, 875,paint);
        paint.setTextSize(75);
        canvas.drawText("Score",775, 950,paint);
        for(int j = 0; j<height;j++){
            for(int i=0; i<width; i++){
                Rect b = new Rect((i*55)+5,(j*55)+5,((i*55)+55)+5, ((j*55)+55)+5);
                Rect r = new Rect((i*55)+10,(j*55)+10,((i*55)+55), ((j*55)+55));
                switch(board[j][i]){
                    case(0):
                        paint.setColor(Color.BLACK);
                        canvas.drawRect(b,paint);
                        paint.setColor(Color.WHITE);
                        canvas.drawRect(r,paint);
                        break;
                    case(1):
                        paint.setColor(Color.BLACK);
                        canvas.drawRect(r,paint);
                        paint.setColor(Color.RED);
                        canvas.drawRect(r,paint);
                        break;
                    case(2):
                        paint.setColor(Color.BLACK);
                        canvas.drawRect(r,paint);
                        paint.setColor(Color.GREEN);
                        canvas.drawRect(r,paint);
                        break;
                    case(3):
                        paint.setColor(Color.BLACK);
                        canvas.drawRect(r,paint);
                        paint.setColor(Color.BLUE);
                        canvas.drawRect(r,paint);
                        break;
                    case(4):
                        paint.setColor(Color.BLACK);
                        canvas.drawRect(r,paint);
                        paint.setColor(Color.CYAN);
                        canvas.drawRect(r,paint);
                        break;
                    case(5):
                        paint.setColor(Color.BLACK);
                        canvas.drawRect(r,paint);
                        paint.setColor(Color.MAGENTA);
                        canvas.drawRect(r,paint);
                        break;
                    case(6):
                        paint.setColor(Color.BLACK);
                        canvas.drawRect(r,paint);
                        paint.setColor(Color.YELLOW);
                        canvas.drawRect(r,paint);
                        break;
                    case(7):
                        paint.setColor(Color.BLACK);
                        canvas.drawRect(r,paint);
                        paint.setColor(Color.GRAY);
                        canvas.drawRect(r,paint);
                        break;
                    default:
                        break;
                }
            }
        }
        for(int j = 0; j<4;j++){
            for(int i=0; i<6; i++){
                Rect b = new Rect((i*55)+705,(j*55)+380,((i*55)+55)+705, ((j*55)+55)+380);
                Rect r = new Rect((i*55)+710,(j*55)+390,((i*55)+55)+700, ((j*55)+55)+375);
                switch(nextPieceBoard[j][i]){
                    case(0):
                        paint.setColor(Color.BLACK);
                        canvas.drawRect(b,paint);
                        paint.setColor(Color.WHITE);
                        canvas.drawRect(r,paint);
                        break;
                    case(1):
                        paint.setColor(Color.BLACK);
                        canvas.drawRect(r,paint);
                        paint.setColor(Color.RED);
                        canvas.drawRect(r,paint);
                        break;
                    case(2):
                        paint.setColor(Color.BLACK);
                        canvas.drawRect(r,paint);
                        paint.setColor(Color.GREEN);
                        canvas.drawRect(r,paint);
                        break;
                    case(3):
                        paint.setColor(Color.BLACK);
                        canvas.drawRect(r,paint);
                        paint.setColor(Color.BLUE);
                        canvas.drawRect(r,paint);
                        break;
                    case(4):
                        paint.setColor(Color.BLACK);
                        canvas.drawRect(r,paint);
                        paint.setColor(Color.CYAN);
                        canvas.drawRect(r,paint);
                        break;
                    case(5):
                        paint.setColor(Color.BLACK);
                        canvas.drawRect(r,paint);
                        paint.setColor(Color.MAGENTA);
                        canvas.drawRect(r,paint);
                        break;
                    case(6):
                        paint.setColor(Color.BLACK);
                        canvas.drawRect(r,paint);
                        paint.setColor(Color.YELLOW);
                        canvas.drawRect(r,paint);
                        break;
                    case(7):
                        paint.setColor(Color.BLACK);
                        canvas.drawRect(r,paint);
                        paint.setColor(Color.GRAY);
                        canvas.drawRect(r,paint);
                        break;
                    default:
                        break;
                }
            }
        }

    }
}