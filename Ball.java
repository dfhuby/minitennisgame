package my.first.app.minitennisgame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class Ball {

    private static final String TAG = Ball.class.getSimpleName();
    int x = 0;
    int y = 0;
    private int dx = 50;
    private int dy = 50;

    private final int width;
    private final int height;
    private final static int R = 50;

    public Ball(int width, int height) {
        this.width = width;
        this.height = height;
        Log.d(TAG, "$$$ Ball-instance");
    }


    public void move() {
        if (x + dx < 0) { dx = 50; }
        else if (x + dx > width - 2 * R) { dx = -50; }
        else if (y + dy < 0) { dy = 50; }
        else if (y + dy > height - 2 * R) { dy = -50; }
        x += dx;
        y += dy;
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawCircle(x, y, R, paint);
    }
}
