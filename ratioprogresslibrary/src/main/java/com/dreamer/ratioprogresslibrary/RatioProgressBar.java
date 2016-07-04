package com.dreamer.ratioprogresslibrary;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by ysx on 2016/7/2.
 */
public class RatioProgressBar extends View{

    private static final String TAG = RatioProgressBar.class.getSimpleName();

    private Paint mLeftPaint, mRightPaint;

    /** 左、右进度条的宽度 */
    private int mLeftWidthX, mRightWidthX;
    private int mTotalValue, mLeftValue, mRightValue;
    private int mProgressHeight;
    /** 左、右进度条之间的间隔 */
    private int mLeftRightProgressSpacing;
    /** 进度条动画持续时间 */
    private int mProgressAnimDuration;

    private AnimatorSet mSet;

    public enum Direction {
        LEFT, RIGHT;
    }

    public RatioProgressBar(Context context) {
        this(context, null);
    }

    public RatioProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatioProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.RatioProgressBar);
        int leftProgressBg = t.getColor(R.styleable.RatioProgressBar_left_progress_bg,
                getResources().getColor(R.color.bg_left_progress));
        mLeftValue = t.getInt(R.styleable.RatioProgressBar_left_progress_value, 0);
        int rightProgressBg = t.getColor(R.styleable.RatioProgressBar_right_progress_bg,
                getResources().getColor(R.color.bg_right_progress));
        mRightValue = t.getInt(R.styleable.RatioProgressBar_right_progress_value, 0);

        mProgressHeight = t.getDimensionPixelSize(R.styleable.RatioProgressBar_progress_height, 15);
        mLeftRightProgressSpacing = t.getInt(
                R.styleable.RatioProgressBar_left_right_progress_spacing,
                1);
        mProgressAnimDuration = t.getInt(R.styleable.RatioProgressBar_progress_anim_duration, 3000);
        mTotalValue = calculateTotalProgressValue();

        Log.d(TAG, "progressHeight:" + mProgressHeight);
        mLeftPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLeftPaint.setStrokeCap(Paint.Cap.SQUARE);
        mLeftPaint.setColor(leftProgressBg);
        mLeftPaint.setStrokeWidth(mProgressHeight);

        mRightPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRightPaint.setStrokeCap(Paint.Cap.SQUARE);
        mRightPaint.setColor(rightProgressBg);
        mRightPaint.setStrokeWidth(mProgressHeight);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = widthSize;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = mProgressHeight;
        } else {
            height = heightSize;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //float ratio = mLeftValue / (float)mTotalValue;

        ValueAnimator animator1 = startProgressAnim(getLeft(),
                calculateLeftProgressWidth() , Direction.LEFT);
        ValueAnimator animator2 = startProgressAnim(getRight(),
                calculateRightProgressWidth() , Direction.RIGHT);
        mSet = new AnimatorSet();
        mSet.setDuration(mProgressAnimDuration);
        mSet.playTogether(animator1, animator2);
        mSet.start();
        postInvalidate();

        Log.d(TAG, "getLeft():" + getLeft() + ", getRight():" + getRight()
            +", getTop():" + getTop() + ", getBottom():" + getBottom());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mSet.isRunning()) {
            postInvalidate();
        }
        canvas.drawLine(getLeft(), getBottom(), mLeftWidthX, getBottom(), mLeftPaint);
        canvas.drawLine(getRight(), getBottom(), mRightWidthX, getBottom(), mRightPaint);
        Log.d(TAG, "mLeftWidthX:" + mLeftWidthX +", mRightWidthX:" + mRightWidthX);

    }

    private ValueAnimator startProgressAnim(int startValue, int endValue, final Direction direction) {
        ValueAnimator animator = ValueAnimator.ofInt(startValue, endValue);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                switch (direction) {
                    case LEFT:
                        mLeftWidthX = value;
                        break;
                    case RIGHT:
                        mRightWidthX = value;
                        break;
                }
            }
        });
       return animator;
    }

    public void setLeftProgressValue(int progress) {
        mLeftValue += progress;
        resetProgressWidth();
    }

    public void setRightProgressValue(int progress) {
        mRightValue += progress;
        resetProgressWidth();
    }

    public int getLeftProgressValue() {
        return mLeftValue;
    }

    public int getRightProgressValue() {
        return mRightValue;
    }

    private int calculateTotalProgressValue() {
        return mLeftValue + mRightValue;
    }

    /**
     * 计算左进度的宽度
     * @return
     */
    private int calculateLeftProgressWidth() {
        float ratio = mLeftValue / (float)mTotalValue;
        return (int) (getRight() * ratio) - mLeftRightProgressSpacing * mProgressHeight;
    }

    /**
     * 计算右进度的宽度
     * @return
     */
    private int calculateRightProgressWidth() {
        float ratio = mLeftValue / (float)mTotalValue;
        return  (int) (getRight() * ratio) + mLeftRightProgressSpacing * mProgressHeight;
    }

    /**
     * 重新设置左右进度条
     */
    private void resetProgressWidth() {
        mTotalValue = calculateTotalProgressValue();
        mLeftWidthX = calculateLeftProgressWidth();
        mRightWidthX = calculateRightProgressWidth();
        postInvalidate();
    }
}
