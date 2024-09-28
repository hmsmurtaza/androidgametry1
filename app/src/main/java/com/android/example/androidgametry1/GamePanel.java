package com.android.example.androidgametry1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private Paint redPaint = new Paint();
    private SurfaceHolder surfaceHolder;
//    private float x, y;
    private ArrayList<RandomSquare> squares = new ArrayList<>();
    private Random random = new Random();

    public GamePanel(Context context) {
        super(context);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
//        getHolder().addCallback(this);
        redPaint.setColor(Color.RED);
    }

    private void render() {
        Canvas canvas = surfaceHolder.lockCanvas();
        canvas.drawColor(Color.BLACK);
        for (RandomSquare square : squares) {
            square.draw(canvas);
        }
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            PointF pointF = new PointF(motionEvent.getX(), motionEvent.getY());

            int color = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            int size = 25 + random.nextInt(101);

            squares.add(new RandomSquare(pointF, color, size));


            render();
        }
        /*x = motionEvent.getX();
        y = motionEvent.getY();*/
//        System.out.println("User event!");
        return true;
    }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
      /*  Canvas canvas = surfaceHolder.lockCanvas();
        canvas.drawRect(50, 50, 1000, 1000, redPaint);

        surfaceHolder.unlockCanvasAndPost(canvas);*/
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    private class RandomSquare {
        private PointF position;
        private int size;
        private Paint paint;

        public RandomSquare(PointF position, int color, int size) {
            this.position = position;
            this.size = size;
            paint = new Paint();
            paint.setColor(color);
        }

        public void draw(Canvas canvas) {
            canvas.drawRect(position.x, position.y, position.x+size, position.y+size, paint);
        }
    }
}
