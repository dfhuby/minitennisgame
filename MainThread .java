package my.first.app.minitennisgame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

public class MainThread extends Thread {

    private static final String TAG = MainThread.class.getSimpleName();

    private GameView gameView;
    private SurfaceHolder surfaceHolder;
    private boolean running;
    public void setRunning(boolean running) {
        this.running = running;
    }
    Canvas canvas;
    Paint paint;

    public MainThread(SurfaceHolder surfaceHolder, GameView gameView) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
        this.paint = gameView.paint;
        setRunning(false);
    }

    public void run() {
        super.run();
        while (running) {
            canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    gameView.doUpdate();
                    gameView.doDraw(canvas, paint);
                }
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
Enter file contents here
