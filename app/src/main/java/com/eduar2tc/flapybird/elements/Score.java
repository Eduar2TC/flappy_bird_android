package com.eduar2tc.flapybird.elements;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.eduar2tc.flapybird.engine.Colors;
import com.eduar2tc.flapybird.engine.Display;
import com.eduar2tc.flapybird.engine.Sound;

public class Score {
    private int score = 0;
    private final Sound sound;
    private static final Paint paint = Colors.getScoreColor();
    public Score(Display display) {
        this.sound = new Sound( display.getContext() );
    }
    public void increase(){
        score++;
        this.sound.play( Sound.increaseScore );
    }
    public void drawScoreOnCanvas( Canvas canvas ){
        canvas.drawText( String.valueOf( score ), 100, 100, this.paint );
    }
    public int getScore(){
        return this.score;
    }

}
