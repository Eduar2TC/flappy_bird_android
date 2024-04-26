package com.eduar2tc.flapybird.engine;

import com.eduar2tc.flapybird.elements.Bird;
import com.eduar2tc.flapybird.elements.Pipes;

public class Collision {
    private final Bird bird;
    private final Pipes pipes;

    public Collision( Bird bird, Pipes pipes ){
        this.bird = bird;
        this.pipes = pipes;
    }
    public boolean detectCollision(){
        return pipes.detectCollisionWith( this.bird );
    }

}
