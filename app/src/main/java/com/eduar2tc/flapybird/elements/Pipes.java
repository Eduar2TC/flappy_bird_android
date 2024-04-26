package com.eduar2tc.flapybird.elements;

import android.graphics.Canvas;

import com.eduar2tc.flapybird.engine.Display;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Pipes {
    private static final int NUMBER_PIPES = 5;
    private static final int DISTANCE_BETWEEN_PIPES = 450;
    private List <Pipe> pipes = new ArrayList< Pipe >();
    private Display display;
    //Score
    private final Score score;

    public Pipes(Display display, Score score ){
        int initialPosition = 200;
        this.display = display;
        this.score = score;
        for ( int i = 0; i < NUMBER_PIPES; i++ ){
            initialPosition += DISTANCE_BETWEEN_PIPES;
            this.pipes.add( new Pipe( display, initialPosition ) );
        }
    }

    public void drawPipesOnCanvas(Canvas canvas) {
        for ( Pipe pipe : this.pipes ){
            pipe.drawPipeBottomOnCanvas( canvas);
            pipe.drawPipeTopOnCanvas(canvas);
        }
    }

    public void movePipes() {
        ListIterator< Pipe > iterator = this.pipes.listIterator();
        while(iterator.hasNext() ){
            Pipe pipe = iterator.next();
            pipe.move();

            if( pipe.outOfLefScreen() ){
                score.increase();
                iterator.remove();
                Pipe otherPipe = new Pipe( this.display, this.getPositionLastPipe() + DISTANCE_BETWEEN_PIPES );
                iterator.add( otherPipe );
            }

        }
    }
    public int getPositionLastPipe(){
        int last = 0;
        for( Pipe pipe : this.pipes ){
            last = Math.max( pipe.getPosition(), last );
        }
        return last;
    }

    public boolean detectCollisionWith(Bird bird) {
        for ( Pipe pipe : this.pipes ){
            if( pipe.detectHorizontalCollision( bird ) && pipe.detectVerticalCollision( bird ) ){
                return true;
            }
        }
        return false;
    }
}
