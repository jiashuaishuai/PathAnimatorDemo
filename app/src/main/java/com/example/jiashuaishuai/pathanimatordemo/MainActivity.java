package com.example.jiashuaishuai.pathanimatordemo;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private ImageView feiji;
    private Path path;
    private PathMeasure mPathMeasure;
    private float[] indexXY = {100, 800};
    private ValueAnimator valueAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        feiji = (ImageView) findViewById(R.id.feiji);
        /**
         * 路径
         */
        path = new Path();
        /**
         * 贝塞尔曲线
         */
        path.moveTo(100, 800);
        path.quadTo(500, 400, 200, 300);
        path.quadTo(200, 50, 400, 0);
        setImgXY(indexXY);
        /**
         * 获取路径测量器
         */
        mPathMeasure = new PathMeasure(path, false);
        valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
        valueAnimator.setDuration(2000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                /**
                 * 核心方法
                 */
                mPathMeasure.getPosTan(value, indexXY, null);
                setImgXY(indexXY);
            }
        });


    }

    private void setImgXY(float[] xy) {
        feiji.setX(xy[0]);
        feiji.setY(xy[1]);

    }

    public void myClick(View v) {
        valueAnimator.start();
    }

    public void click(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    /*
* 设置控件所在的位置YY，并且不改变宽高，
* XY为绝对位置
*/
    public static void setLayout(View view, int x, int y) {
        ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(view.getLayoutParams());
        margin.setMargins(x, y, x + margin.width, y + margin.height);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
        view.setLayoutParams(layoutParams);
    }
}
