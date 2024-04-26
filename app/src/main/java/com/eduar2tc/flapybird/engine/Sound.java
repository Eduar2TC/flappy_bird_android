package com.eduar2tc.flapybird.engine;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.eduar2tc.flapybird.R;

public class Sound {
    private SoundPool soundPool;
    public static int loose;
    public static int flap;
    public static int increaseScore;
    public static int playAgain;
    public Sound( Context context ){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            this.soundPool = new SoundPool.Builder().setMaxStreams(4).build();
        } else {
            this.soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        }
        flap = this.soundPool.load( context, R.raw.flap, 1 );
        loose = this.soundPool.load( context, R.raw.loose, 1 );
        increaseScore =  this.soundPool.load( context, R.raw.increase_score, 1 );
        playAgain =  this.soundPool.load( context, R.raw.play_again, 1 );
        //increaseScore = this.soundPool.load( context, R.raw.increase_score, 2 );
        //playAgain = this.soundPool.load( context, R.raw.play_again, 1 );
    }
    public void play(int sound){
        this.soundPool.play( sound, 1, 1, 1, 0, 1 );
    }

}
