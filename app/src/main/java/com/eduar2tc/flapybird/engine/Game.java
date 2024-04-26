package com.eduar2tc.flapybird.engine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;


import androidx.core.content.res.ResourcesCompat;

import com.eduar2tc.flapybird.MainActivity;
import com.eduar2tc.flapybird.R;
import com.eduar2tc.flapybird.elements.Bird;
import com.eduar2tc.flapybird.elements.GameOver;
import com.eduar2tc.flapybird.elements.Pipes;
import com.eduar2tc.flapybird.elements.Play;
import com.eduar2tc.flapybird.elements.Score;

import java.util.Objects;

public class Game extends SurfaceView implements Runnable, View.OnTouchListener {
    private boolean isRunning = true;
    private boolean isGameOver = false;
    private final SurfaceHolder holder = getHolder(); // For access to Canvas
    private Canvas canvas;

    private Bird bird;
    private Bitmap background;
    private Bitmap backgroundNight;
    private Bitmap birdBitmap;
    private Bitmap play;
    private Pipes pipes;
    private Display display;
    private Score score;

    private Animation animation;
    private Sound sound;
    private Parallax parallax;
    private Play playBtn;

    public Game(Context context){
        super(context);
        //Set to listener Game view
        this.setOnTouchListener( this );
        //Init elements
        this.initElements();
    }
    private void initElements(){
        this.display = new Display(getContext());
        this.bird = new Bird( this.display );
        this.background = BitmapFactory.decodeResource(this.getResources(), R.drawable.bg);
        this.backgroundNight = BitmapFactory.decodeResource(this.getResources(), R.drawable.bg_night);
        this.play = BitmapFactory.decodeResource(this.getResources(), R.drawable.play);
        //Resize bg on canvas
        this.background = Bitmap.createScaledBitmap( this.background, this.display.getWidth(), display.getHeight(), false);
        this.backgroundNight = Bitmap.createScaledBitmap( this.backgroundNight, this.display.getWidth(), display.getHeight(), false);
        //Score
        this.score = new Score( this.display );
        //Creation of pipes
        this.pipes = new Pipes( this.display, this.score );
        //Animation
        this.animation = new Animation( this.display, this.bird );
        //Sounds
        this.sound = new Sound( this.display.getContext() );
        //Parallax effect
        this.parallax = new Parallax( this.display );
        //Play button
        this.playBtn = new Play(this.display);
    }
    @Override
    public void run() {
        while( isRunning ){
            if( !holder.getSurface().isValid() ){
                continue;
            }
            this.canvas = this.holder.lockCanvas(); //Lock canvas for to paint in this
            if( !this.isGameOver ){
                //---Paint elements
                //Draw background [first]
                if( score.getScore() < 3 ) {
                    //----canvas.drawBitmap(this.background, 0, 0, null);
                    this.parallax.drawBackgroundOnCanvas( canvas );
                }else{
                    this.background = this.backgroundNight;
                    this.canvas.drawBitmap( this.background,0, 0, null );
                    postInvalidate();
                }
                //Draw animation bird
                //this.animation.drawAnimationBirdOnCanvas( this.canvas );
                //Draw Circle object bird
                this.bird.drawOnCanvas( this.canvas );
                this.bird.fallDown(); //Falling down bird
                //Draw Pipes
                this.pipes.drawPipesOnCanvas( this.canvas );
                this.pipes.movePipes();
                //Draw grass
                this.parallax.drawGrassBackground( this.canvas );
                //Draw score
                this.score.drawScoreOnCanvas( this.canvas );

                //Detect collision draw hit else draw bird animation
                if( new Collision( this.bird, this.pipes ).detectCollision() ){
                    //new GameOver(this.display).drawMessageOnCanvas( this.canvas );
                    //new Play(this.display).drawPlayOnCanvas(this.canvas);
                    //Draw hit bird
                    //this.isRunning = this.animation.drawHitOnCanvas( this.canvas );
                    this.animation.drawHitOnCanvas( this.canvas );
                    this.sound.play(Sound.loose);
                    //this.isRunning = false;
                    this.isGameOver = true;

                }else {
                    //Draw animation While Game is running and is not Over
                    this.animation.drawAnimationBirdOnCanvas( this.canvas );
                }
            }else{
                //--State [GAME OVER] On resume Game
                //---Paint elements
                //Draw background [first]
                canvas.drawBitmap(this.background, 0, 0, null);
                //Draw Circle object bird
                this.bird.drawOnCanvas( this.canvas );
                //Draw animation hit
                this.animation.drawHitOnCanvas( this.canvas );
                //Draw Pipes
                this.pipes.drawPipesOnCanvas( this.canvas );
                //Draw score
                this.score.drawScoreOnCanvas( this.canvas );
                new GameOver(this.display).drawBg( this.canvas );
                new GameOver(this.display).drawMessageOnCanvas( this.canvas );
                //new Play(this.display).drawPlayOnCanvas(this.canvas);
                //Control animation play again
                if(!playBtn.playClicked){
                    this.playBtn.drawPlayOnCanvas(this.canvas);
                }else{
                    this.playBtn.drawPlayOnCanvas(this.canvas);
                    this.playBtn.clickAnimation(this.canvas);
                    postInvalidate();
                }
            }
            //Draw elements on canvas!
            this.holder.unlockCanvasAndPost( this.canvas );
        }
    }

    public void pause() {
        this.isRunning = false;
    }

    public void resume() {
        this.isRunning = true;
    }
    //Flap on Tapping screen
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //Is Gaming
        if( !isGameOver ){
            this.bird.flap();
        }
        //Is Game over true
        if( isGameOver ){
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            int action = motionEvent.getAction();
            int marginTop = 80;

            if( action == MotionEvent.ACTION_DOWN ){
                if( ( x >= this.display.getWidth()/2 - this.play.getWidth()/2 && x <= this.display.getWidth()/2 + this.play.getWidth()/2 ) &&
                        ( y >= marginTop + this.display.getHeight()/2 - this.play.getHeight()/2 && y <= marginTop + this.display.getHeight()/2 + this.play.getHeight()/2 )
                ){
                    //Restart game
                    playBtn.playClicked = true;
                    this.sound.play(Sound.playAgain);
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    getContext().startActivity(intent);
                    ((MainActivity)getContext()).finish();
                }
            }
        }
        return false;
    }
}
