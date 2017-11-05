package com.example.busroute.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.example.busroute.R;
import com.example.busroute.data.remote.RouteModel;


/**
 * Created by Dasari on 05/11/2017.
 */
public class Route extends View {
    private Paint paint = new Paint();
    private float yStart, factor;
    private int currentIndex = 0;
    private float smallCircleRadius, bigCircleRadius, selectedTextSize, normalTextSize;
    private int stopsCount =0;
    private RouteModel.Stops [] stops;
    public Route(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setSaveEnabled(true);

        //read xml attributes
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Route, 0, 0);
        smallCircleRadius = ta.getDimensionPixelSize(R.styleable.Route_smallCircleRadius, 0);
        bigCircleRadius = ta.getDimensionPixelSize(R.styleable.Route_bigCircleRadius, 0);
        selectedTextSize = ta.getDimensionPixelSize(R.styleable.Route_selectedTextSize, 0);
        normalTextSize = ta.getDimensionPixelSize(R.styleable.Route_normalTextSize, 0);
    }

    public void initialise(RouteModel.Stops [] stops){
        stopsCount = stops.length;
        this.stops = stops;
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2);
        drawTimerIndicators(canvas);
    }


    private void drawTimerIndicators(Canvas canvas) {

        float areaHeight = getHeight() - getPaddingTop() - getPaddingBottom();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        factor = areaHeight / 4;
        yStart = getPaddingLeft();
        float cx = getWidth() / 4;
        float cy = getPaddingTop();
        Rect rect = new Rect();
        String text;
        for (int i = 0; i < stopsCount; i++) {
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setTextSize(normalTextSize);
            paint.setStrokeWidth(2);
            paint.setColor(getContext().getResources().getColor(R.color.line_color));
            canvas.drawCircle(cx, cy, bigCircleRadius, paint);
            text = stops[i].getName();
            paint.setColor(Color.BLACK);
            paint.getTextBounds(text, 0, text.length(), rect);
            canvas.drawText(text, cx + (rect.width() / 2) + 20, cy - (rect.top + rect.bottom)/2, paint);
            paint.setColor(getContext().getResources().getColor(R.color.line_color));
            paint.setStrokeWidth(10);
            if(i < stopsCount -1)
                canvas.drawLine(cx, cy, cx, cy + factor, paint);
            cy = cy + factor;
        }

    }

    private void drawStrokeCircle(Canvas canvas, float x, float y) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(getResources().getColor(R.color.line_color));
        paint.setTextSize(selectedTextSize);
        Rect rect = new Rect();
        String text = String.format("%02d", currentIndex + 1);
        paint.getTextBounds(text, 0, text.length(), rect);
        canvas.drawCircle(x, (y * 3) - (rect.height() / 2), bigCircleRadius, paint);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(x, (y * 3) - (rect.height() / 2), bigCircleRadius - 2, paint);
        paint.setColor(getResources().getColor(R.color.line_color));
        canvas.drawText(text, x - ((rect.left + rect.right) / 2), y * 3, paint);
        paint.setStrokeWidth(5);
        canvas.drawLine(x, y + smallCircleRadius, x, ((y * 3) - (rect.height() / 2)) - bigCircleRadius, paint);
    }

}

