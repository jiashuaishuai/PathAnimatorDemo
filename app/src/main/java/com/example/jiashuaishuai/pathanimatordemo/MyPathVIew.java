
package com.example.jiashuaishuai.pathanimatordemo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by jiashuaishuai on 2016/3/25.
 */
public class MyPathVIew extends View {
    private Paint paint;
    private Path path;
    private PathMeasure mPathMeasure;
    private float[] mCurrentPosition = {100, 100};

    public MyPathVIew(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        path = new Path();
        initView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    private void initView() {
        paint.setAntiAlias(true);//去除锯齿
        paint.setColor(Color.parseColor("#000000"));//画笔颜色黑色
        paint.setStyle(Paint.Style.STROKE);//空心
        paint.setStrokeWidth(3);//外框宽度
        path.moveTo(100, 100);
        path.quadTo(100, 400, 300, 300);
//        path.moveTo(300,300);
        path.quadTo(200, 250, 400, 400);
//        path.moveTo(400,400);
        path.quadTo(450, 500, 600, 600);
//        path.close();//封闭
        mPathMeasure = new PathMeasure(path, false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


//        path.addCircle(getWidth() / 2, getHeight() / 2, 250, Path.Direction.CW);
//        canvas.drawPath(path, paint);
//        paint.setStrokeWidth(0);//外框宽度
//        paint.setTextSize(30);
//        canvas.drawTextOnPath("一圈儿文ggghjgjkhgjgjgkjk;jk;ljk;l字",path,getWidth(),40, paint);
//        canvas.drawPath(path, paint);

//        paint.setStrokeWidth(0);//外框宽度
//        paint.setTextSize(30);
//        canvas.drawTextOnPath("一圈儿文ggghjgjkhgjgjgkjk;jk;ljk;l字",path,0,0, paint);


        canvas.drawPath(path, paint);
        canvas.drawCircle(mCurrentPosition[0], mCurrentPosition[1], 10, paint);
    }

    /**
     * 启动动画
     */
    public void startPathAnim() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                // 获取当前点坐标封装到mCurrentPosition
                mPathMeasure.getPosTan(value, mCurrentPosition, null);
                postInvalidate();
                Log.i("TAG", "mCurrentPosition" + mCurrentPosition[0] + "mCurrentPosition" + mCurrentPosition[1]);
            }
        });
        valueAnimator.start();
    }
}
