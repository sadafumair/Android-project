


package com.sadaf.customviews;

import java.util.ArrayList;

import android.app.Service;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.sadaf.resizablerectangle.R;

import static android.view.WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;

public class DrawView extends View {

    WindowManager windowManager; // to hold our image on screen
    Context ctx; // context so in case i use it somewhere.
    GestureDetector gestureDetector; // to detect some listener on the image.
    WindowManager.LayoutParams params; // layoutParams where i set the image height/width and other.




    public void destroy(){
        if(windowManager != null){ // if the image still exists on the WindowManager release it.
            windowManager.removeView(this); // remove the ImageView
        }
    }


    Point point1, point3;
    Point point2, point4;
    Point point5, point6, point7;
    Point startMovePoint;

    /**
     * point1 and point 3 are of same group and same as point 2 and point4
     */
    int groupId = 2;
    private ArrayList<ColorBall> colorballs;
    // array that holds the balls
    private int balID = 0;
    // variable to know what ball is being dragged

    Paint paint;

    Canvas canvas;

    public DrawView(Context context) {
        super(context);
        init(context);
        this.ctx = context;

        windowManager = (WindowManager) ctx.getSystemService("window"); // ini the windowManager
        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,

                PixelFormat.TRANSLUCENT); // assigning height/width to the imageView
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR
                | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM; // assigning some flags to the layout
        params.gravity = Gravity.TOP | Gravity.LEFT; // setting the gravity of the imageView
        params.windowAnimations = android.R.style.Animation_Toast; // adding an animation to it.
        params.x = 0; // horizontal location of imageView
        params.y = 100; // vertical location of imageView
        params.height = 500; // given it a fixed height in case of large image
        params.width = 500; // given it a fixed width in case of large image
        params.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN;
        windowManager.addView(this, params); // adding the imageView & the  params to the WindowsManger.
    }

    public DrawView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint();
        setFocusable(true); // necessary for getting the touch events
        canvas = new Canvas();
        // setting the start point for the balls
        point1 = new Point();
        point1.x = 100;
        point1.y = 40;

        point2 = new Point();
        point2.x = 300;
        point2.y = 40;

        point3 = new Point();
        point3.x = 300;
        point3.y = 240;

        point4 = new Point();
        point4.x = 100;
        point4.y = 240;


       /* point6 = new Point();
        point6.x = 300;
        point6.y = 40;*/
        point6 = new Point();

        point6.x = (point1.x + point2.x) / 2;
        Log.i("xxxxxxxxxxxxxxxx", "xxxxxxxxxxxxxx" + point6.x);
        point6.y = (point1.y + point2.y) / 2;
        Log.i("yyyyyyyyyyyyyyyyy", "yyyyyyyyyyyy" + point6.y);

        point7 = new Point();

        point7.x = (point3.x + point4.x) / 2;
        Log.i("xxxxxxxxxxxxxxxx", "xxxxxxxxxxxxxx" + point7.x);
        point7.y = (point3.y + point4.y) / 2;
        Log.i("yyyyyyyyyyyyyyyyy", "yyyyyyyyyyyy" + point7.y);

        point5 = new Point();

        point5.x = (point1.x + point3.x) / 2;
        Log.i("xxxxxxxxxxxxxxxx", "xxxxxxxxxxxxxx" + point7.x);
        point5.y = (point1.y + point3.y) / 2;
        Log.i("yyyyyyyyyyyyyyyyy", "yyyyyyyyyyyy" + point7.y);




        // declare each ball with the ColorBall class
        colorballs = new ArrayList<ColorBall>();
        colorballs.add(0, new ColorBall(context, R.drawable.red, point1, 0));
        colorballs.add(1, new ColorBall(context, R.drawable.trans, point2, 1));
        colorballs.add(2, new ColorBall(context, R.drawable.black, point3, 2));
        colorballs.add(3, new ColorBall(context, R.drawable.trans, point4, 3));
        colorballs.add(4, new ColorBall(context, R.drawable.green, point5, 4));
        colorballs.add(5,new ColorBall(context, R.drawable.blue, point6,5));
        colorballs.add(6,new ColorBall(context, R.drawable.blue, point7,6));
    }

    // the method that draws the balls
    @Override
    protected void onDraw(Canvas canvas) {
        // canvas.drawColor(0xFFCCCCCC); //if you want another background color

        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(Color.parseColor("#55000000"));
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);

        paint.setStrokeJoin(Paint.Join.ROUND);
//        // mPaint.setStrokeCap(Paint.Cap.ROUND);
//        paint.setStrokeWidth(5);

        canvas.drawPaint(paint);
        paint.setColor(Color.parseColor("#000000"));

        if (groupId == 1) {
            Log.i("sadaffffffff","groupid 111111");
            canvas.drawRect(point1.x + colorballs.get(0).getWidthOfBall() / 2,
                    point3.y + colorballs.get(2).getWidthOfBall() / 2, point3.x
                            + colorballs.get(2).getWidthOfBall() / 2, point1.y
                            + colorballs.get(0).getWidthOfBall() / 2, paint);
            canvas.drawLine( point1.x + colorballs.get(0).getWidthOfBall() / 2,   point1.y + colorballs.get(0).getWidthOfBall() / 2,   point5.x + colorballs.get(4).getWidthOfBall() / 2,   point5.y + colorballs.get(4).getWidthOfBall() / 2, paint);
            canvas.drawLine( point2.x + colorballs.get(1).getWidthOfBall() / 2,   point2.y + colorballs.get(1).getWidthOfBall() / 2,   point5.x + colorballs.get(4).getWidthOfBall() / 2,   point5.y + colorballs.get(4).getWidthOfBall() / 2, paint);
            canvas.drawLine( point3.x + colorballs.get(2).getWidthOfBall() / 2,   point3.y + colorballs.get(2).getWidthOfBall() / 2,   point5.x + colorballs.get(4).getWidthOfBall() / 2,   point5.y + colorballs.get(4).getWidthOfBall() / 2, paint);
            canvas.drawLine( point4.x + colorballs.get(4).getWidthOfBall() / 2,   point4.y + colorballs.get(3).getWidthOfBall() / 2,   point5.x + colorballs.get(4).getWidthOfBall() / 2,   point5.y + colorballs.get(4).getWidthOfBall() / 2, paint);

            canvas.drawLine( point6.x + colorballs.get(5).getWidthOfBall() / 2,   point6.y + colorballs.get(5).getWidthOfBall() / 2,   point5.x + colorballs.get(4).getWidthOfBall() / 2,   point5.y + colorballs.get(4).getWidthOfBall() / 2, paint);
            canvas.drawLine( point7.x + colorballs.get(6).getWidthOfBall() / 2,   point7.y + colorballs.get(6).getWidthOfBall() / 2,   point5.x + colorballs.get(4).getWidthOfBall() / 2,   point5.y + colorballs.get(4).getWidthOfBall() / 2, paint);


          /*//  canvas.  drawCircle(300,300,300,paint);
            float cx = (50 + 200) / 2f;
            float cy = (200 + 150) / 2f;

            double radius = Math.hypot(200 - 150, 50 - 200) / 2f;
           float rad= Float.valueOf(String.valueOf(radius));
            canvas.   drawCircle(cx + colorballs.get(0).getWidthOfBall() / 2, cy + colorballs.get(0).getWidthOfBall() / 2, rad, paint);
            canvas.drawLine( 200 + colorballs.get(0).getWidthOfBall() / 2,   150 + colorballs.get(0).getWidthOfBall() / 2,   point3.x + colorballs.get(2).getWidthOfBall() / 2,   point3.y + colorballs.get(2).getWidthOfBall() / 2, paint);
*/
        } else {
          canvas.drawRect(point2.x + colorballs.get(1).getWidthOfBall() / 2,
                    point4.y + colorballs.get(3).getWidthOfBall() / 2, point4.x
                            + colorballs.get(3).getWidthOfBall() / 2, point2.y
                            + colorballs.get(1).getWidthOfBall() / 2, paint);
            canvas.drawLine( point2.x + colorballs.get(1).getWidthOfBall() / 2,   point2.y + colorballs.get(1).getWidthOfBall() / 2,   point5.x + colorballs.get(4).getWidthOfBall() / 2,   point5.y + colorballs.get(4).getWidthOfBall() / 2, paint);
            canvas.drawLine( point1.x + colorballs.get(0).getWidthOfBall() / 2,   point1.y + colorballs.get(0).getWidthOfBall() / 2,   point5.x + colorballs.get(4).getWidthOfBall() / 2,   point5.y + colorballs.get(4).getWidthOfBall() / 2, paint);
            canvas.drawLine( point3.x + colorballs.get(2).getWidthOfBall() / 2,   point3.y + colorballs.get(2).getWidthOfBall() / 2,   point5.x + colorballs.get(4).getWidthOfBall() / 2,   point5.y + colorballs.get(4).getWidthOfBall() / 2, paint);
            canvas.drawLine( point4.x + colorballs.get(4).getWidthOfBall() / 2,   point4.y + colorballs.get(3).getWidthOfBall() / 2,   point5.x + colorballs.get(4).getWidthOfBall() / 2,   point5.y + colorballs.get(4).getWidthOfBall() / 2, paint);

            canvas.drawLine( point6.x + colorballs.get(5).getWidthOfBall() / 2,   point6.y + colorballs.get(5).getWidthOfBall() / 2,   point5.x + colorballs.get(4).getWidthOfBall() / 2,   point5.y + colorballs.get(4).getWidthOfBall() / 2, paint);

            canvas.drawLine( point7.x + colorballs.get(6).getWidthOfBall() / 2,   point7.y + colorballs.get(6).getWidthOfBall() / 2,   point5.x + colorballs.get(4).getWidthOfBall() / 2,   point5.y + colorballs.get(4).getWidthOfBall() / 2, paint);

            Log.i("remmmmmmmmmmmmmove2","view");
            Log.i("remmmmmmmmmmmmmove2","view");
            Log.i("remmmmmmmmmmmmmove2","view");
            Log.i("remmmmmmmmmmmmmove2","view");
            // windowManager.removeView(params);

          //  ((WindowManager) ctx.getSystemService(Service.WINDOW_SERVICE)).removeView(this);
/*
            Path path = new Path();
            path.moveTo(point1.x+ colorballs.get(0).getWidthOfBall() / 2, point1.y+ colorballs.get(0).getWidthOfBall() / 2);
            path.lineTo(point2.x+ colorballs.get(1).getWidthOfBall() / 2, point2.y+ colorballs.get(1).getWidthOfBall() / 2);
            path.lineTo(point3.x+ colorballs.get().getWidthOfBall() / 2, point3.y+ colorballs.get(2).getWidthOfBall() / 2);
            path.lineTo(point4.x+ colorballs.get(1).getWidthOfBall() / 2, point4.y+ colorballs.get(3).getWidthOfBall() / 2);
            path.lineTo(point5.x+ colorballs.get(1).getWidthOfBall() / 2, point5.y+ colorballs.get(4).getWidthOfBall() / 2);
            path.close();
            canvas.drawARGB(0, 0, 0, 0);
            //   paint.setColor(Color.parseColor("#BAB399"));
            paint.setColor(Color.parseColor("#e74b3c"));
            paint.setStrokeWidth(10);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path, paint);*/
        }
        BitmapDrawable mBitmap;
        mBitmap = new BitmapDrawable();

        // draw the balls on the canvas
        for (ColorBall ball : colorballs) {
            canvas.drawBitmap(ball.getBitmap(), ball.getX(), ball.getY(),
                    new Paint());
        }
        Log.i("remmmmmmmmmmmmmoveddraw","view");
    }

    // events when touching the screen
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("remmmmmmmmmmmmmovetouch","view");
        int eventaction = event.getAction();

        int X = (int) event.getX();
        int Y = (int) event.getY();
       switch (eventaction) {

        case MotionEvent.ACTION_DOWN: // touch down so check if the finger is on
                                        // a ball
            balID = -1;
            startMovePoint = new Point(X,Y);
            for (ColorBall ball : colorballs) {
                // check if inside the bounds of the ball (circle)
                // get the center for the ball
                int centerX = ball.getX() + ball.getWidthOfBall();
                int centerY = ball.getY() + ball.getHeightOfBall();
                paint.setColor(Color.CYAN);
                // calculate the radius from the touch to the center of the ball
                double radCircle = Math
                        .sqrt((double) (((centerX - X) * (centerX - X)) + (centerY - Y)
                                * (centerY - Y)));

                if (radCircle < ball.getWidthOfBall()) {

                    balID = ball.getID();
                    if (balID == 1 || balID == 3) {
                        groupId = 2;
                        canvas.drawRect(point1.x, point3.y, point3.x, point1.y,
                                paint);
                        canvas.drawLine( point1.x ,   point1.y  ,   point5.x  ,   point5.y  , paint);
                        canvas.drawLine( point6.x  ,   point6.y ,   point5.x  ,   point5.y , paint);

                    } else {
                        groupId = 1;
                        canvas.drawRect(point2.x, point4.y, point4.x, point2.y,
                                paint);

                        canvas.drawLine( point2.x ,   point2.y  ,   point5.x  ,   point5.y  , paint);
                        canvas.drawLine( point7.x  ,   point7.y ,   point5.x  ,   point5.y , paint);

                    }
                    invalidate();
                    break;
                }
                invalidate();
            }

            break;

        case MotionEvent.ACTION_MOVE: // touch drag with the ball
            // move the balls the same as the finger
            if (balID > -1) {
                colorballs.get(balID).setX(X);
                colorballs.get(balID).setY(Y);

                paint.setColor(Color.CYAN);

                if (groupId == 1) {
                    colorballs.get(1).setX(colorballs.get(0).getX());
                    colorballs.get(1).setY(colorballs.get(2).getY());
                    colorballs.get(3).setX(colorballs.get(2).getX());
                    colorballs.get(3).setY(colorballs.get(0).getY());
                    canvas.drawRect(point1.x, point3.y, point3.x, point1.y,
                            paint);
                    canvas.drawLine(point1.x, point1.y, point5.x, point5.y,
                            paint);
                    canvas.drawLine( point6.x  ,   point6.y  ,   point5.x ,   point5.y  , paint);

                } else {
                    colorballs.get(0).setX(colorballs.get(1).getX());
                    colorballs.get(0).setY(colorballs.get(3).getY());
                    colorballs.get(2).setX(colorballs.get(3).getX());
                    colorballs.get(2).setY(colorballs.get(1).getY());
                    canvas.drawRect(point2.x, point4.y, point4.x, point2.y,
                            paint);
                    canvas.drawLine(point2.x, point2.y, point5.x, point5.y,
                            paint);
                    canvas.drawLine( point7.x  ,   point7.y ,   point5.x  ,   point5.y , paint);

                }

                invalidate();
            }else{
                if (startMovePoint!=null) {
                    paint.setColor(Color.CYAN);
                   /* int diffX = X - startMovePoint.x;
                    int diffY = Y - startMovePoint.y;
                    startMovePoint.x = X;
                    startMovePoint.y = Y;
                    colorballs.get(0).addX(diffX);
                    colorballs.get(1).addX(diffX);
                    colorballs.get(2).addX(diffX);
                    colorballs.get(3).addX(diffX);
                    colorballs.get(4).addX(diffX);
                    colorballs.get(5).addX(diffX);
                    colorballs.get(6).addX(diffX);
                    colorballs.get(7).addX(diffX);
                    colorballs.get(0).addY(diffY);
                    colorballs.get(1).addY(diffY);
                    colorballs.get(2).addY(diffY);
                    colorballs.get(3).addY(diffY);
                    colorballs.get(4).addY(diffY);
                    colorballs.get(5).addY(diffY);
                    colorballs.get(6).addY(diffY);
                    colorballs.get(7).addY(diffY);*/
                    if(groupId==1)

                    {
                        canvas.drawRect(point1.x, point3.y, point3.x, point1.y,
                                paint);
                        canvas.drawLine(point1.x, point1.y, point5.x, point5.y,
                                paint);

                        canvas.drawLine( point6.x  ,   point6.y ,   point5.x  ,   point5.y , paint);

                    }
                    else{
                        canvas.drawRect(point2.x, point4.y, point4.x, point2.y,
                                paint);
                        canvas.drawLine(point2.x, point2.y, point5.x, point5.y,
                                paint);
                        canvas.drawLine( point7.x  ,   point7.y ,   point5.x  ,   point5.y , paint);

                    }
                    invalidate();
                }
            }

            break;

        case MotionEvent.ACTION_UP:
            // touch drop - just do things here after dropping

            break;
        }
        // redraw the canvas
        invalidate();
        return true;

    }

    public void shade_region_between_points() {
        canvas.drawRect(point1.x, point3.y, point3.x, point1.y, paint);
    }
}
