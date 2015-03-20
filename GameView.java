package my.first.app.minitennisgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = GameView.class.getSimpleName();

    Paint paint;
    private Ball ball;
    private Stick stick;
    MainThread mainThread;
    MotionEvent motionEvent;

    public GameView(Context context) {
        super(context);
        init();
    }

    public GameView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public GameView(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        init();
    }

    private void init() {
        paint = new Paint();
        motionEvent = null;
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        setFocusable(true);
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        mainThread.setRunning(false);
        boolean retry = true;
        while (retry) {
            try {
                mainThread.join();
                retry = false;
                Log.d(TAG, "mainThread stopped - gameView destroyed");
            } catch (Exception e) {Log.d(TAG, "! mainThread stop exception");}
        }
    }
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height){}
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        int screenW = this.getWidth();
        int screenH = this.getHeight();
        ball = new Ball(screenW, screenH);
        stick = new Stick(screenW, screenH);
        mainThread = new MainThread(surfaceHolder, this);
        mainThread.setRunning(true);
        mainThread.start();
    }

    public void doUpdate() {
        ball.move();
        stick.doMove(motionEvent);
    }

    public void doDraw(Canvas canvas, Paint paint) {
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        ball.draw(canvas, paint);
        paint.setColor(Color.GREEN);
        stick.draw(canvas, paint);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.motionEvent = motionEvent;
        return true;
    }
}
