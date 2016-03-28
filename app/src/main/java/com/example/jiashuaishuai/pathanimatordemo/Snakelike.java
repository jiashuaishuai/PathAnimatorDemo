package com.example.jiashuaishuai.pathanimatordemo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by jiashuaishuai on 2016/3/28.
 */
public class Snakelike extends View {
    private Path path;
    private Paint paint;
    private PathMeasure pathMeasure;
    private float[] indexXY = {100, 100};
    private float index = 0;

    public Snakelike(Context context, AttributeSet attrs) {
        super(context, attrs);
        path = new Path();
        paint = new Paint();
        initClass();
    }

    private void initClass() {
        paint.setAntiAlias(true);//去除锯齿
        paint.setColor(Color.parseColor("#000000"));//画笔颜色黑色
        paint.setStyle(Paint.Style.STROKE);//空心
        paint.setStrokeWidth(3);//外框宽度
        path.moveTo(100, 100);
        path.quadTo(100, 400, 300, 300);
//        path.moveTo(300,300);
        path.quadTo(100, 450, 400, 400);
//        path.moveTo(400,400);
        path.quadTo(450, 500, 600, 600);
        pathMeasure = new PathMeasure(path, false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
        paint.setStrokeWidth(0);
        paint.setTextSize(30);
        canvas.drawTextOnPath("这是一条文字", path, index, 0, paint);
    }
    /**
     * 启动动画
     */
    public void startPathAnim() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, pathMeasure.getLength()-200);
        valueAnimator.setDuration(5000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                index = value;
                // 获取当前点坐标封装到mCurrentPosition
//                mPathMeasure.getPosTan(value, mCurrentPosition, null);
                postInvalidate();
            }
        });
        valueAnimator.start();
    }
}
