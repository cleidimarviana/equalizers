package me.ack;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Random;

public class Equalizers extends LinearLayout {

    //private static final int SIZE = 12;

    private AnimatorSet playingSet;
    private AnimatorSet stopSet;
    private Boolean animating = false;

    int foregroundColor;
    int duration;
    int numberBars;


    public Equalizers(Context context) {
        super(context);
        initViews();
    }

    public Equalizers(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttrs(context, attrs);
        initViews();
    }

    public Equalizers(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setAttrs(context, attrs);
        initViews();
    }

    private void setAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.EqualizerView,
                0, 0);

        try {
            foregroundColor = a.getInt(R.styleable.EqualizerView_foregroundColor, Color.RED);
            duration = a.getInt(R.styleable.EqualizerView_animDuration, 3000);

            numberBars = a.getInt(R.styleable.EqualizerView_numberBars, 0);

        } finally {
            a.recycle();
        }
    }

    ArrayList<View> views = new ArrayList<>();


    private void initViews() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_equalizer, this, true);
        LinearLayout lm = (LinearLayout) findViewById(R.id.linear);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,  1.0f);
        params.setMargins(1,1,1,1);

        for(int i = 0; i< numberBars; i++){
            final View musicBar1 =  new View(getContext());

            musicBar1.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    if (musicBar1.getHeight() > 0) {
                        musicBar1.setPivotY(musicBar1.getHeight());
                        if (Build.VERSION.SDK_INT >= 16) {
                            musicBar1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }
                }
            });
            musicBar1.setLayoutParams(params);
            musicBar1.setBackgroundColor(foregroundColor);
            lm.addView(musicBar1);

            views.add(musicBar1);
        }
    }

    public float[] rFloat(){
        float fl[] = new float[26];
        float minX = 0.1f;
        float maxX = 0.9f;
        Random rand = new Random();

        for(int i = 0; i<fl.length; i++){
                fl[i] =  rand.nextFloat() * (maxX - minX) + minX;
        }

        return fl;
    }

    public void startAnimateBars() {
        animating = true;
        if (playingSet == null) {

            playingSet = new AnimatorSet();

            for(int i = 0; i< numberBars; i++){

                ObjectAnimator scaleY = ObjectAnimator.ofFloat(views.get(i), "scaleY",rFloat());
                scaleY.setRepeatCount(ValueAnimator.INFINITE);

                playingSet.playTogether(scaleY);
            }

            playingSet.setDuration(duration);
            playingSet.setInterpolator(new LinearInterpolator());
            playingSet.start();

        } else if (Build.VERSION.SDK_INT < 19) {
            if (!playingSet.isStarted()) {
                playingSet.start();
            }
        } else {
            if (playingSet.isPaused()) {
                playingSet.resume();
            }
        }

    }

    public void stopAnimateBars() {
        animating = false;
        if (playingSet != null && playingSet.isRunning() && playingSet.isStarted()) {
            if (Build.VERSION.SDK_INT < 19) {
                playingSet.end();
            } else {
                playingSet.pause();
            }
        }

        if (stopSet == null) {
            // Animate stopping bars
            stopSet = new AnimatorSet();

            for(int i=0; i < numberBars; i++){
                stopSet.playTogether(ObjectAnimator.ofFloat(views.get(i), "scaleY", 0.1f));
            }

            stopSet.setDuration(200);
            stopSet.start();
        } else if (!stopSet.isStarted()) {
            stopSet.start();
        }
    }

    public Boolean isAnimating() {
        return animating;
    }
}