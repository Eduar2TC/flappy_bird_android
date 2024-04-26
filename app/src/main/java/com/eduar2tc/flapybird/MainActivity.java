package com.eduar2tc.flapybird;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.eduar2tc.flapybird.engine.Game;

public class MainActivity extends Activity {
    Game game;
    FrameLayout container;
    @Override
    protected void onPause() { //For Handle loop game
        super.onPause();
        this.game.pause(); //pause game
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.game.resume(); //resume game on other thread
        new Thread(this.game).start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        this.container = findViewById(R.id.container);
        this.game = new Game(this);
        this.container.addView( game );

    }
}