package my.first.app.minitennisgame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

public class Stick {

    private static final String TAG = Stick.class.getSimpleName();
    private final static int WIDTH = 200;
    private final static int HEIGHT = 60;
    private int y;
    private int x;
    private final int width;
    private final int height;
    private int eventX;
    private int eventY;
    private boolean drag;
    private int dragX;

    public Stick(int width, int height) {
        this.width = width;
        this.height = height;
        this.y = height - 2 * HEIGHT;
        this.x = width / 2 - WIDTH / 2;
    }

    public void doMove(MotionEvent motionEvent) {
        if (motionEvent != null) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                eventX = (int) motionEvent.getX();
                eventY = (int) motionEvent.getY();
            }
            if (eventX >= x && eventX <= x + WIDTH && eventY >= y && eventY <= y + HEIGHT) {
                dragX = eventX - x;
                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    if (((eventX - dragX) >= 0) && ((eventX - dragX) <= (width - WIDTH))) {
                        x = eventX - dragX;
                    }
                }
            }
        }
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawRect(x, y, x + WIDTH, y + HEIGHT, paint);
    }
}
