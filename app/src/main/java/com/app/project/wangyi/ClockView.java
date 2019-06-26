package com.app.project.wangyi;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.Nullable;

/**
 * @Auther JiRui
 * @CreateTime 2019/5/21 13:36
 * @Describe
 */
public class ClockView extends View {


    public ClockView(Context context) {
        this(context, null);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Paint mPaint;
    private int mCenterX;//绘制图的中心点x坐标
    private int mCenterY;//绘制图的中心点y坐标
    private int mCenterCircleR;//中心圆的半径

    private int mMonthR;//绘制月份的半径
    private int mDayR;//绘制天数的半径
    private int mWeekR;//绘制周的半径
    private boolean mTagam;//标记早上下午
    private int mHourR;//绘制时的半径
    private int mSecondR;//绘制分的半径
    private int mMinuteR;//绘制秒的半径

    private int mMonth;//月份
    private int mDay;//天数
    private int mMonthDays;//当前月份一共多少天
    private int mWeek;//周
    private int mHour;//时
    private int mMinute;//分
    private int mSecond;//秒

    private MyHandler mHandler = new MyHandler(getContext()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            invalidate();
        }
    };

    private ExecutorService mExecutorService = Executors.newFixedThreadPool(10);

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.CYAN);
        mPaint.setTextSize(40);
    }

    public void reset() {
        month = 0;
        day = 0;
        week = 0;
        hour = 0;
        minute = 0;
        mHandler.sendEmptyMessageDelayed(0, 1000);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        getSystemDate();
        mCenterX = getWidth() / 2;
        mCenterY = getHeight() / 2;
        mCenterCircleR = mCenterX / 15;
        canvas.translate(mCenterX, mCenterY);

        //绘制中心圆和月份
        drawCenterCircleMonth(canvas);

        //绘制中心圆和月份
        drawDay(canvas);

        //绘制当前每周
        drawWeekDay(canvas);

        //绘制小时
        drawHour(canvas);

        //绘制分钟
        drawMinute(canvas);

        //绘制秒
        drawSecond(canvas);

        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {

                if (month < 12) {
                    month++;
                    mHandler.sendEmptyMessageDelayed(0, 50);
                } else if (day < mMonthDays) {
                    day++;
                    mHandler.sendEmptyMessageDelayed(0, 50);
                } else if (week < 7) {
                    week++;
                    mHandler.sendEmptyMessageDelayed(0, 150);
                } else if (hour < 24) {
                    hour++;
                    mHandler.sendEmptyMessageDelayed(0, 50);
                } else if (minute < 60) {
                    minute++;
                    mHandler.sendEmptyMessageDelayed(0, 50);
                } else {
                    mHandler.sendEmptyMessageDelayed(0, 1000);
                }
            }
        });
    }

    private void drawSecond(Canvas canvas) {
        mPaint.setColor(Color.RED);
        canvas.save();
        String second = mSecond + "秒";
        Rect rect = new Rect();
        mPaint.getTextBounds(second, 0, second.length(), rect);
//        这里两种测量方式看着都一样
//        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
//        float height = fontMetrics.descent - fontMetrics.ascent;
        float height = mPaint.descent() - mPaint.ascent();
        float width = mPaint.measureText(second);

        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText(second, -width / 2, height / 3, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.restore();
        mPaint.setColor(Color.CYAN);
    }

    private int minute = 0;

    private void drawMinute(Canvas canvas) {
        canvas.save();
        mSecondR = mHourR + mHourR / 3;
        Path path = new Path();
        canvas.drawCircle(0, 0, mSecondR, mPaint);
        mPaint.setStyle(Paint.Style.FILL);

        for (int i = 0; i < minute; i++) {
            path.lineTo(mSecondR, 0);
            if (i + mMinute >= 60) {
                int s = i + mMinute - 60;
                if (s == 0) {
                    s = 00;
                }
                canvas.drawTextOnPath(s + "分", path, mHourR, 20, mPaint);
            } else {
                canvas.drawTextOnPath(i + mMinute + "分", path, mHourR, 20, mPaint);
            }

            canvas.rotate((float) 360 / 60);
        }

        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(0, 0, mSecondR, mPaint);
        canvas.restore();

    }

    private int hour = 0;

    private void drawHour(Canvas canvas) {
        canvas.save();
        mHourR = mWeekR + mWeekR / 3;
        Path path = new Path();
        canvas.drawCircle(0, 0, mHourR, mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < hour; i++) {
            path.lineTo(mHourR, 0);
            if (i + mHour >= 24) {
                int s = i + mHour - 24;
                if (s == 0) {
                    s = 00;
                }
                canvas.drawTextOnPath(s + "点", path, mWeekR + (mHourR - mWeekR) / 6, 20, mPaint);
            } else {
                canvas.drawTextOnPath(i + mHour + "点", path, mWeekR + (mHourR - mWeekR) / 6, 20, mPaint);
            }

            canvas.rotate((float) 360 / 24);
        }
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(0, 0, mHourR, mPaint);
        canvas.restore();
    }

    private int week = 0;

    private void drawWeekDay(Canvas canvas) {
        canvas.save();
        mWeekR = mDayR + mDayR / 2;
        Path path = new Path();
        canvas.drawCircle(0, 0, mWeekR, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < week; i++) {
            path.lineTo(mWeekR, 0);
            if (i + mWeek >= 7) {
                int s = i + mWeek - 7;
                if (s == 0) {
                    s = 7;
                }
                if (s == 7) {
                    canvas.drawTextOnPath("星期日", path, mDayR, 20, mPaint);
                } else {
                    canvas.drawTextOnPath("星期" + ChineseNumber.getNumToString(s), path, mDayR, 20, mPaint);
                }

            } else {
                canvas.drawTextOnPath("星期" + (ChineseNumber.getNumToString(i + mWeek)), path, mDayR, 20, mPaint);
            }

            canvas.rotate((float) 360 / 7);
        }
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(0, 0, mDayR, mPaint);
        canvas.restore();
    }

    private int day = 0;

    private void drawDay(Canvas canvas) {
        canvas.save();
        mDayR = mMonthR * 2;
        Path path = new Path();
        mPaint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < day; i++) {
            path.lineTo(mDayR, 0);
//            canvas.drawPath(path, mPaint);
            if (i + mDay >= mMonthDays) {
                int s = i + mDay - mMonthDays;
                if (s == 0) {
                    s = mMonthDays;
                }
                canvas.drawTextOnPath(s + "日", path, mMonthR + mMonthR / 4, 20, mPaint);
            } else {
                canvas.drawTextOnPath(i + mDay + "日", path, mMonthR + mMonthR / 4, 20, mPaint);
            }

            canvas.rotate((float) 360 / mMonthDays);
        }
        mPaint.setStyle(Paint.Style.STROKE);

        canvas.drawCircle(0, 0, mDayR, mPaint);
        canvas.restore();
    }

    private int month = 0;

    protected void drawCenterCircleMonth(Canvas canvas) {
        canvas.save();
        mMonthR = mCenterCircleR * 3;
        Path path = new Path();
        canvas.drawCircle(0, 0, mCenterCircleR, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < month; i++) {
            path.lineTo(mMonthR, 0);
//            canvas.drawPath(path, mPaint);
            if (i + mMonth >= 12) {
                int s = i + mMonth - 12;
                if (s == 0) {
                    s = 12;
                }
                canvas.drawTextOnPath(s + "月", path, mCenterCircleR + mCenterCircleR / 3, 20, mPaint);
            } else {
                canvas.drawTextOnPath(i + mMonth + "月", path, mCenterCircleR + mCenterCircleR / 3, 20, mPaint);
            }

            canvas.rotate(360 / 12);
        }
        mPaint.setStyle(Paint.Style.STROKE);

//        canvas.drawCircle(0, 0, mMonthR, mPaint);
        canvas.restore();
    }

    /**
     * 获取系统时间
     */
    private void getSystemDate() {
        //获取系统的日期
        Calendar calendar = Calendar.getInstance();

        //年
        int year = calendar.get(Calendar.YEAR);

        //月
        mMonth = calendar.get(Calendar.MONTH) + 1;

        //日
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mMonthDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        mWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        //小时
        mHour = calendar.get(Calendar.HOUR_OF_DAY);

        //分钟
        mMinute = calendar.get(Calendar.MINUTE);

        //秒
        mSecond = calendar.get(Calendar.SECOND);
    }

    private abstract class MyHandler extends Handler {
        WeakReference<Activity> weakReference;
        Context context;

        public MyHandler(Context context) {
            context = context;
            weakReference = new WeakReference(context);
        }
    }
}
