package com.example.assignment3;
//Joseph Carbone
//46146768
//jrcarbon 36
//EECS 40 Spring 2019
//5-9-19
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

//import android.support.design.widget.FloatingActionButton;

public class Game extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new gameView(this));

    }


}
