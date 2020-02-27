package com.example.assignment3;
//Joseph Carbone
//46146768
//jrcarbon 36
//EECS 40 Spring 2019
//5-9-19
 class Piece {

    private int[][] shape;
    private int count = 0;
    private int speed = 40;
    private int tempSpeed = 40;
    private int[] tempshape0 = new int[3];
    private int[] tempshape1 = new int[3];
    private int[] tempshape2 = new int[3];
    private int[] tempshape3 = new int[3];
    private boolean nextToWall = false;

    // initialize the piece
     Piece (int[][] shapes){
        shape = shapes;
    }

    // returns the array from the piece
     int[][] getPiece(){
        return shape;
    }

    // this function returns the type of shape (color also)
     int Type(){
        return shape[0][2];
    }

    // these functions return the Location X value or Y value of each block in the shape
     int oneX(){
        return shape[0][0];
    }
     int oneY(){
        return shape[0][1];
    }
     int twoX(){
        return shape[1][0];
    }
     int twoY(){
        return shape[1][1];
    }
     int threeX(){
        return shape[2][0];
    }
     int threeY(){
        return shape[2][1];
    }
     int fourX(){
        return shape[3][0];
    }
     int fourY(){
        return shape[3][1];
    }

    //this function rotates the piece
     void rotate (){
        switch(shape[0][2]){
            case(4): // Rotation for Box
                break;
            case(1): // Rotation for I shape
                if(shape[0][1]==shape[1][1]){//standing |
                    if(shape[0][1]<7){
                        shape[1][0] = shape[0][0];
                        shape[1][1] = shape[0][1]+1;
                        shape[2][0] = shape[0][0];
                        shape[2][1] = shape[0][1]+2;
                        shape[3][0] = shape[0][0];
                        shape[3][1] = shape[0][1]+3;
                        break;
                    }
                    else{
                        shape[1][0] = shape[0][0];
                        shape[1][1] = shape[0][1]-1;
                        shape[2][0] = shape[0][0];
                        shape[2][1] = shape[0][1]-2;
                        shape[3][0] = shape[0][0];
                        shape[3][1] = shape[0][1]-3;
                        //make shapes go back in order
                        tempshape0 = shape[0];
                        tempshape1 = shape[1];
                        tempshape2 = shape[2];
                        tempshape3 = shape[3];
                        shape[0] = tempshape3;
                        shape[1] = tempshape2;
                        shape[2] = tempshape1;
                        shape[3] = tempshape0;
                        break;
                    }
                }else if(shape[1][0]==shape[0][0]){//laying down _
                    shape[1][0] = shape[0][0]+1;
                    shape[1][1] = shape[0][1];
                    shape[2][0] = shape[0][0]+2;
                    shape[2][1] = shape[0][1];
                    shape[3][0] = shape[0][0]+3;
                    shape[3][1] = shape[0][1];
                    break;
                }

            case(2): // Rotation for Z shape
                if(shape[0][1]==shape[1][1]){//standing Z
                    if(shape[0][1]<8){
                        shape[1][0] = shape[0][0];
                        shape[1][1] = shape[0][1]+1;
                        shape[2][0] = shape[0][0]+1;
                        shape[2][1] = shape[0][1]+1;
                        shape[3][0] = shape[0][0]+1;
                        shape[3][1] = shape[0][1]+2;
                        break;
                    }
                    else{
                        shape[1][0] = shape[0][0];
                        shape[1][1] = shape[0][1]-1;
                        shape[2][0] = shape[0][0]-1;
                        shape[2][1] = shape[0][1]-1;
                        shape[3][0] = shape[0][0]-1;
                        shape[3][1] = shape[0][1]-2;
                        //make shapes go back in order
                        tempshape0 = shape[0];
                        tempshape1 = shape[1];
                        tempshape2 = shape[2];
                        tempshape3 = shape[3];
                        shape[0] = tempshape3;
                        shape[1] = tempshape2;
                        shape[2] = tempshape1;
                        shape[3] = tempshape0;
                        break;
                    }
                }else if(shape[1][0]==shape[0][0]){//laying down Z
                    shape[0][0] = shape[1][0];
                    shape[0][1] = shape[1][1];
                    shape[1][0] = shape[0][0]+1;
                    shape[1][1] = shape[0][1];
                    shape[2][0] = shape[0][0]+1;
                    shape[2][1] = shape[0][1]-1;
                    shape[3][0] = shape[0][0]+2;
                    shape[3][1] = shape[0][1]-1;
                    break;
                }

            case(3): // Rotation for S shape
                if(shape[0][1]==shape[1][1]){//standing S
                    if(shape[0][1]<8){
                        shape[0][0] = shape[1][0];
                        shape[0][1] = shape[1][1];
                        shape[1][0] = shape[0][0];
                        shape[1][1] = shape[0][1]+1;
                        shape[2][0] = shape[0][0]-1;
                        shape[2][1] = shape[0][1]+1;
                        shape[3][0] = shape[0][0]-1;
                        shape[3][1] = shape[0][1]+2;
                        break;
                    }
                    else{
                        shape[1][0] = shape[0][0];
                        shape[1][1] = shape[0][1]-1;
                        shape[2][0] = shape[0][0]+1;
                        shape[2][1] = shape[0][1]-1;
                        shape[3][0] = shape[0][0]+1;
                        shape[3][1] = shape[0][1]-2;
                        //make shapes go back in order
                        tempshape0 = shape[0];
                        tempshape1 = shape[1];
                        tempshape2 = shape[2];
                        tempshape3 = shape[3];
                        shape[0] = tempshape3;
                        shape[1] = tempshape2;
                        shape[2] = tempshape1;
                        shape[3] = tempshape0;
                        break;
                    }
                }else if(shape[1][0]==shape[0][0]){//laying down Z
                    shape[1][0] = shape[0][0]+1;
                    shape[1][1] = shape[0][1];
                    shape[2][0] = shape[0][0]+1;
                    shape[2][1] = shape[0][1]+1;
                    shape[3][0] = shape[0][0]+2;
                    shape[3][1] = shape[0][1]+1;
                    break;
                }

            case(5): // Rotation for Backwards L shape
                if((shape[0][0]==shape[1][0])&&(shape[0][1]+1==shape[1][1])) {// --,
                    shape[0][0] = shape[1][0];
                    shape[0][1] = shape[1][1];
                    shape[1][0] = shape[0][0]+1;
                    shape[1][1] = shape[0][1];
                    shape[2][0] = shape[0][0]+2;
                    shape[2][1] = shape[0][1];
                    shape[3][0] = shape[0][0]+2;
                    shape[3][1] = shape[0][1]-1;
                    break;
                }
                if((shape[0][0]+2==shape[2][0])&&(shape[0][1]==shape[2][1])) { //backwards _|
                    if(shape[0][1]<8){
                        shape[0][0] = shape[3][0]-1;
                        shape[0][1] = shape[3][1];
                        shape[1][0] = shape[0][0]+1;
                        shape[1][1] = shape[0][1];
                        shape[2][0] = shape[0][0]+1;
                        shape[2][1] = shape[0][1]+1;
                        shape[3][0] = shape[0][0]+1;
                        shape[3][1] = shape[0][1]+2;
                        break;
                    }else{
                        shape[0][0] = shape[0][0]+1;
                        shape[0][1] = shape[0][1];
                        shape[1][0] = shape[0][0];
                        shape[1][1] = shape[0][1]-1;
                        shape[2][0] = shape[0][0];
                        shape[2][1] = shape[0][1]-2;
                        shape[3][0] = shape[0][0]-1;
                        shape[3][1] = shape[0][1]-2;
                        tempshape0 = shape[0];
                        tempshape1 = shape[1];
                        tempshape2 = shape[2];
                        tempshape3 = shape[3];
                        shape[0] = tempshape3;
                        shape[1] = tempshape2;
                        shape[2] = tempshape1;
                        shape[3] = tempshape0;
                        break;

                    }
                }
                if((shape[0][0]+1==shape[2][0])&&(shape[0][1]+1==shape[2][1])) {//'--
                    shape[0][0] = shape[2][0]+1;
                    shape[0][1] = shape[2][1];
                    shape[1][0] = shape[0][0];
                    shape[1][1] = shape[0][1]-1;
                    shape[2][0] = shape[0][0]+1;
                    shape[2][1] = shape[0][1]-1;
                    shape[3][0] = shape[0][0]+2;
                    shape[3][1] = shape[0][1]-1;
                    break;
                }
                if((shape[0][0]==shape[1][0])&&(shape[0][1]-1==shape[1][1])) { //r
                    if(shape[0][1]<2){
                        shape[0][0] = shape[0][0] + 1;
                        shape[0][1] = shape[0][1]+1;
                        shape[1][0] = shape[0][0] - 1;
                        shape[1][1] = shape[0][1];
                        shape[2][0] = shape[0][0] - 1;
                        shape[2][1] = shape[0][1] - 1;
                        shape[3][0] = shape[0][0] - 1;
                        shape[3][1] = shape[0][1] - 2;
                        tempshape0 = shape[0];
                        tempshape1 = shape[1];
                        tempshape2 = shape[2];
                        tempshape3 = shape[3];
                        shape[0] = tempshape3;
                        shape[1] = tempshape2;
                        shape[2] = tempshape1;
                        shape[3] = tempshape0;
                        break;
                    }
                    else {
                        shape[0][0] = shape[0][0] + 1;
                        shape[0][1] = shape[0][1];
                        shape[1][0] = shape[0][0] - 1;
                        shape[1][1] = shape[0][1];
                        shape[2][0] = shape[0][0] - 1;
                        shape[2][1] = shape[0][1] - 1;
                        shape[3][0] = shape[0][0] - 1;
                        shape[3][1] = shape[0][1] - 2;
                        tempshape0 = shape[0];
                        tempshape1 = shape[1];
                        tempshape2 = shape[2];
                        tempshape3 = shape[3];
                        shape[0] = tempshape3;
                        shape[1] = tempshape2;
                        shape[2] = tempshape1;
                        shape[3] = tempshape0;
                        break;
                    }
                }

            case(6): // Rotation for L shape
                if(shape[0][0]-1==shape[1][0]) {// ,--
                    shape[0][0] = shape[1][0];
                    shape[0][1] = shape[1][1];
                    shape[1][0] = shape[0][0];
                    shape[1][1] = shape[0][1]+1;
                    shape[2][0] = shape[0][0]+1;
                    shape[2][1] = shape[0][1]+1;
                    shape[3][0] = shape[0][0]+2;
                    shape[3][1] = shape[0][1]+1;
                    break;
                }
                if(shape[0][1]+1==shape[1][1]) { //backwards r
                    if(shape[0][1]>0){
                        shape[0][0] = shape[1][0];
                        shape[0][1] = shape[1][1];
                        shape[1][0] = shape[0][0]+1;
                        shape[1][1] = shape[0][1];
                        shape[2][0] = shape[0][0]+1;
                        shape[2][1] = shape[0][1]-1;
                        shape[3][0] = shape[0][0]+1;
                        shape[3][1] = shape[0][1]-2;
                        break;
                    }else{
                        shape[0][0] = shape[0][0];
                        shape[0][1] = shape[0][1]+2;
                        shape[1][0] = shape[0][0]+1;
                        shape[1][1] = shape[0][1];
                        shape[2][0] = shape[0][0]+1;
                        shape[2][1] = shape[0][1]-1;
                        shape[3][0] = shape[0][0]+1;
                        shape[3][1] = shape[0][1]-2;
                        break;

                    }
                }
                if(shape[0][0]+1==shape[1][0]) {// --'
                    shape[0][0] = shape[0][0]+2;
                    shape[0][1] = shape[0][1];
                    shape[1][0] = shape[0][0];
                    shape[1][1] = shape[0][1]-1;
                    shape[2][0] = shape[0][0]-1;
                    shape[2][1] = shape[0][1]-1;
                    shape[3][0] = shape[0][0]-2;
                    shape[3][1] = shape[0][1]-1;
                    break;
                }
                if(shape[0][1]-1==shape[1][1]) { //L
                    if(shape[0][1]>8){
                        shape[0][0] = shape[0][0];
                        shape[0][1] = shape[0][1]-2;
                        shape[1][0] = shape[0][0]-1;
                        shape[1][1] = shape[0][1];
                        shape[2][0] = shape[0][0]-1;
                        shape[2][1] = shape[0][1]+1;
                        shape[3][0] = shape[0][0]-1;
                        shape[3][1] = shape[0][1]+2;
                        break;
                    }
                    else {
                        shape[0][0] = shape[1][0];
                        shape[0][1] = shape[1][1];
                        shape[1][0] = shape[0][0]-1;
                        shape[1][1] = shape[0][1];
                        shape[2][0] = shape[0][0]-1;
                        shape[2][1] = shape[0][1]+1;
                        shape[3][0] = shape[0][0]-1;
                        shape[3][1] = shape[0][1]+2;
                        break;
                    }
                }

            case(7): // Rotation for T shape
                if(shape[0][1]+1==shape[1][1]) {// T
                    shape[0][0] = shape[1][0];
                    shape[0][1] = shape[1][1];
                    shape[1][0] = shape[0][0]+1;
                    shape[1][1] = shape[0][1];
                    shape[2][0] = shape[0][0]+2;
                    shape[2][1] = shape[0][1];
                    shape[3][0] = shape[0][0]+1;
                    shape[3][1] = shape[0][1]-1;
                    break;
                }
                if(shape[0][0]+1==shape[1][0]) { //-|
                    if(shape[0][1]>1){
                        shape[0][0] = shape[0][0];
                        shape[0][1] = shape[0][1];
                        shape[1][0] = shape[0][0];
                        shape[1][1] = shape[0][1]-1;
                        shape[2][0] = shape[0][0];
                        shape[2][1] = shape[0][1]-2;
                        shape[3][0] = shape[0][0]-1;
                        shape[3][1] = shape[0][1]-1;
                        break;
                    }else{
                        shape[0][0] = shape[0][0]+1;
                        shape[0][1] = shape[0][1]+1;
                        shape[1][0] = shape[0][0];
                        shape[1][1] = shape[0][1]-1;
                        shape[2][0] = shape[0][0];
                        shape[2][1] = shape[0][1]-2;
                        shape[3][0] = shape[0][0]-1;
                        shape[3][1] = shape[0][1]-1;
                        break;

                    }
                }
                if(shape[0][1]-1==shape[1][1]) {// Upside down T
                    shape[0][0] = shape[1][0];
                    shape[0][1] = shape[1][1];
                    shape[1][0] = shape[0][0]-1;
                    shape[1][1] = shape[0][1];
                    shape[2][0] = shape[0][0]-2;
                    shape[2][1] = shape[0][1];
                    shape[3][0] = shape[0][0]-1;
                    shape[3][1] = shape[0][1]+1;
                    break;
                }
                if(shape[0][0]-1==shape[1][0]) { //  |-
                    if(shape[0][1]<8){
                        shape[0][0] = shape[1][0];
                        shape[0][1] = shape[1][1];
                        shape[1][0] = shape[0][0];
                        shape[1][1] = shape[0][1]+1;
                        shape[2][0] = shape[0][0];
                        shape[2][1] = shape[0][1]+2;
                        shape[3][0] = shape[0][0]+1;
                        shape[3][1] = shape[0][1]+1;
                        break;
                    }
                    else {
                        shape[0][0] = shape[0][0]-1;
                        shape[0][1] = shape[0][1]-1;
                        shape[1][0] = shape[0][0];
                        shape[1][1] = shape[0][1]+1;
                        shape[2][0] = shape[0][0];
                        shape[2][1] = shape[0][1]+2;
                        shape[3][0] = shape[0][0]+1;
                        shape[3][1] = shape[0][1]+1;
                        break;
                    }
                }
        }
    }
    //this function moves the piece to the left one block
     void moveLeft(){
        for (int i=0;i<4;i++){
            if(shape[i][1]==0){
                nextToWall = true;
            }
        }
        if(!nextToWall) {
            int tempX = shape[0][1];
            tempX--;
            shape[0][1] = tempX;
            tempX = shape[1][1];
            tempX--;
            shape[1][1] = tempX;
            tempX = shape[2][1];
            tempX--;
            shape[2][1] = tempX;
            tempX = shape[3][1];
            tempX--;
            shape[3][1] = tempX;
        }
        nextToWall = false;
    }

    //this function moves the piece to the right one block
     void moveRight(){
        for (int i=0;i<4;i++){
            if(shape[i][1]==9){
                nextToWall = true;
            }
        }
        if(!nextToWall){
            int tempX = shape[0][1];
            tempX++;
            shape[0][1] = tempX;
            tempX = shape[1][1];
            tempX++;
            shape[1][1] = tempX;
            tempX = shape[2][1];
            tempX++;
            shape[2][1] = tempX;
            tempX = shape[3][1];
            tempX++;
            shape[3][1] = tempX;
        }
        nextToWall = false;
    }

    //this function speeds up the falling process, when down button pressed
     void speedUp(){
        speed = 2;
    }

    //this functions changes the speed back to normal
     void speedDown(){
        speed = tempSpeed;
    }

    //this function moves the piece down depending on the speed how frequent
     boolean update(Board board){
        if(count%speed==0){
            int tempY = shape[0][0];
            tempY++;
            shape[0][0] = tempY;
            tempY = shape[1][0];
            tempY++;
            shape[1][0] = tempY;
            tempY = shape[2][0];
            tempY++;
            shape[2][0] = tempY;
            tempY = shape[3][0];
            tempY++;
            shape[3][0] = tempY;
        }
        count++;
        if (board.canItMoveDown(shape)){
            return false;
        }
        return true;
    }
}
