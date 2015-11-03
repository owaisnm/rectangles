package com.andrd.owais.rectangles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by owais on 11/1/15.
 */
public class DrawView extends View {

    private Paint paint = new Paint();
    private Rect rect1, rect2;

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setParameters(Rect r1, Rect r2) {
        rect1 = new Rect(r1.left, r1.top, r1.right, r1.bottom);
        rect2 = new Rect(r2.left, r2.top, r2.right, r2.bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint = new Paint();

        // draw rect1
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        paint.setAlpha(150);
        canvas.drawRect(rect1, paint);

        // draw rect 2
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setAlpha(150);
        canvas.drawRect(rect2, paint);

    }



}
