package com.app.project.wangyi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import com.app.basecommon.utiles.Logger;
import com.app.project.R;

import androidx.annotation.Nullable;

/**
 * @Auther JiRui
 * @CreateTime 2019/5/21 9:54
 * @Describe
 * 知识点 https://blog.csdn.net/pecke/article/details/41014069
 */
public class CarView extends View {

    private Bitmap carBitmap;//小车的图片

    private Path path;
    private PathMeasure pathMeasure;//路径的计算
    private float diatanceRatio = 0;
    private Paint circlePaint;//圆的画笔
    private Paint carPaint;//车的画笔
    private Matrix carMatrix;//针对car bitmap 图片操作的矩阵

    public CarView(Context context) {
        this(context,null);

    }

    public CarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        carBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.icon_car);
        path = new Path();
        path.addCircle(0,0,200,Path.Direction.CW);
        pathMeasure = new PathMeasure(path,false);
        pathMeasure.getLength();

        circlePaint = new Paint();
        circlePaint.setStrokeWidth(5);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.BLACK);

        carPaint = new Paint();
        carPaint.setColor(Color.DKGRAY);
        carPaint.setStrokeWidth(2);
        carPaint.setStyle(Paint.Style.STROKE);

        carMatrix = new Matrix();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        canvas.translate(width / 2,height / 2);
         carMatrix.reset();
         diatanceRatio += 0.006f;//当前进度
        if (diatanceRatio >= 1){
            diatanceRatio = 0;
        }
        float distance = pathMeasure.getLength() * diatanceRatio;

        float[] pos = new float[2];//记录位置
        float[] tan = new float[2];//记录切点值xy
        pathMeasure.getPosTan(distance,pos,tan);
        Logger.e("---","pos[0]:" + pos[0] + "   pos[1]" + pos[1]);
        //pos[0] 切点的x坐标，pos[1] 切点的y坐标。
        //tan[0] = cos角的值 = x坐标，tan[1] = sin角的值 = y坐标

        float degree = (float) (Math.atan2(tan[1],tan[0]) * 180 / Math.PI);

//        carMatrix.postRotate(degree,carBitmap.getWidth() / 2,carBitmap.getHeight() / 2);
        carMatrix.postTranslate(pos[0] - carBitmap.getWidth() / 2,pos[1] - carBitmap.getHeight() / 2);

        canvas.drawPath(path,circlePaint);//画圆
        canvas.drawBitmap(carBitmap,carMatrix,carPaint);

        invalidate();


    }
}
