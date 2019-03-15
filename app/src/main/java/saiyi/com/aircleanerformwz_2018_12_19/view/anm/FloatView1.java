package saiyi.com.aircleanerformwz_2018_12_19.view.anm;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by leixiaoliang on 2016/12/21.
 */
public class  FloatView1 extends View {

    private static final String TAG = "FloatView";
    private static final float[][] STAR_LOCATION = new float[][]{
            {0.8f, 0.2f}, {0.28f, 0.35f}, {0.5f, 0.05f},
            {0.85f, 0.45f}, {0.5f, 0.8f}, {0.15f, 0.8f},
            {0.2f, 0.3f}, {0.77f, 0.4f}, {0.75f, 0.5f},
            {0.8f, 0.55f}, {0.9f, 0.6f}, {0.1f, 0.7f},
            {0.3f, 0.1f}, {0.7f, 0.8f}, {0.25f, 0.6f},
            {0.1f, 0.75f}, {0.4f, 0.2f}, {0.5f, 0.7f}
    };
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int TOP = 2;
    public static final int BOTTOM = 3;
    public static final int FREE_POINT = 4;
    private int mFloatTransLowSpeed;
    private int mFloatTransMidSpeed;
    private int mFloatTransFastSpeed;
    private Resources mResources;
    private int mTotalWidth, mTotalHeight;
    private int mCenterX;
    private int mCenterY;
    private Rect mSrcRect;
    private Rect mDestRect;

    public int getmFloatCount() {
        return mFloatCount;
    }

    public void setmFloatCount(int mFloatCount) {
        this.mFloatCount = mFloatCount;
    }

    private int mFloatCount = 18;

    public List<Integer> getPicSource() {
        return picSource;
    }

    public void setPicSource(List<Integer> picSource) {
        this.picSource = picSource;
    }

    private Paint paint;

    List<StarInfo> mStarInfos = new ArrayList<>();

    private int floatTyep = 100;//默认情况

    private List<Integer> picSource = new ArrayList<>();//存储图片，大小，样式不同的
    private boolean isRuning;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (isRuning) {
                postInvalidate();
                handler.sendMessageDelayed(Message.obtain(),50);
            }
        }
    };

    public FloatView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);
        initPaint();
    }

    public void startAnimationFloat() {
        isRuning = true;
        handler.sendMessage(Message.obtain());
    }

    public void stopAnimationFloat() {
        isRuning = false;

    }

    public void restartAnimationFloat() {
        startAnimationFloat();
    }

    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 防抖动
        paint.setDither(true);
        // 开启图像过滤
        paint.setFilterBitmap(true);

    }

    public void setFloatType(int floatType) {
        this.floatTyep = floatType;

    }

    //定义三种不同快慢的漂浮速度
    private void initData(Context context) {
        mResources = getResources();
        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();

        mTotalWidth = dm.widthPixels;
        mTotalHeight = dm.heightPixels;
        Log.i(TAG, "mTotalWidth=" + mTotalWidth + "--1--mTotalHeight=" + mTotalHeight);
        //设置三个不同大小的速度值
        mFloatTransLowSpeed = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.5f,
                mResources.getDisplayMetrics());
        mFloatTransMidSpeed = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.75f,
                mResources.getDisplayMetrics());
        mFloatTransFastSpeed = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f,
                mResources.getDisplayMetrics());
    }

    /**
     * 获取星球大小
     */
    private float getStarSize(float start, float end) {
        float nextFloat = (float) Math.random();
        if (start < nextFloat && nextFloat < end) {
            return nextFloat;
        } else {
            // 如果不处于想要的数据段，则再随机一次，因为不断递归有风险
            return (float) Math.random();
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTotalWidth = w;
        mTotalHeight = h;
        mCenterX = mTotalWidth / 2;
        mCenterY = mTotalHeight / 2;
        mSrcRect = new Rect();
        mDestRect = new Rect(0, 0, mTotalWidth, mTotalHeight);
        Log.i(TAG, "mTotalWidth=" + mTotalWidth + "---2-mTotalHeight=" + mTotalHeight);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mTotalWidth = MeasureSpec.getSize(widthMeasureSpec);
        mTotalHeight = MeasureSpec.getSize(heightMeasureSpec);
        initStarInfo();
        setMeasuredDimension(mTotalWidth, mTotalHeight);
        Log.i(TAG, "mTotalWidth=" + mTotalWidth + "---onMeasure----mTotalHeight=" + mTotalHeight);
    }

    /**
     * 不同移动轨迹，除过左右上下，也可以定义其他方向，如对角线，曲线之类的
     * 初始化星球运行方向
     */
    private int getStarDirection() {
        int randomInt;
        Random random = new Random();
        if (floatTyep == 100) {
            randomInt = random.nextInt(3);
        } else {
            randomInt = floatTyep;
        }
        int direction = 0;
        switch (randomInt) {
            case 0:
                direction = LEFT;
                break;
            case 1:
                direction = RIGHT;
                break;
            case 2:
                direction = TOP;
                break;
            case 3:
                direction = BOTTOM;
                break;
            case 4:
                direction = FREE_POINT;
                break;
            default:
                break;
        }
        return direction;
    }

    private void resetStarFloat(StarInfo starInfo) {
        switch (starInfo.direction) {
            case LEFT:
                if (starInfo.xLocation < -20) {
                    starInfo.xLocation = mTotalWidth;
                } else {
                    starInfo.xLocation -= starInfo.speed;
                }

                break;
            case RIGHT:
                if (starInfo.xLocation > mTotalWidth + 20) {
                    starInfo.xLocation = 0;
                } else {
                    starInfo.xLocation += starInfo.speed;
                }

                break;
            case TOP:
                if (starInfo.yLocation < -20) {
                    starInfo.yLocation = mTotalHeight;
                } else {
                    starInfo.yLocation -= starInfo.speed;
                }


                break;
            case BOTTOM:
                if (starInfo.yLocation > mTotalHeight + 30) {
                    starInfo.yLocation = 0;
                } else {
                    starInfo.yLocation += starInfo.speed;
                }
                break;
            case FREE_POINT:

                if (starInfo.yLocation > mTotalHeight + 30) {
                    starInfo.yLocation = 0;
                } else {
                    starInfo.yLocation += starInfo.speed;
                }

                if (starInfo.xLocation < -20) {
                    starInfo.xLocation = mTotalWidth;
                } else {
                    starInfo.xLocation -= starInfo.speed;
                }
                break;

            default:
                break;
        }
    }

    /**
     * 初始化星球信息
     */
    private void initStarInfo() {
        Log.i(TAG, "mFloatCount = " + mFloatCount);
        StarInfo starInfo = null;
        Random random = new Random();
        for (int i = 0; i < mFloatCount; i++) {
            // 获取星球大小比例
            float starSize = getStarSize(0.4f, 0.8f);
            // 初始化星球大小
            float[] starLocation = STAR_LOCATION[i];
            starInfo = new StarInfo();
            starInfo.sizePercent = starSize;
            // 初始化漂浮速度
            int randomSpeed = random.nextInt(3);
            switch (randomSpeed) {
                case 0:
                    starInfo.speed = mFloatTransLowSpeed;
                    break;
                case 1:
                    starInfo.speed = mFloatTransMidSpeed;
                    break;
                case 2:
                    starInfo.speed = mFloatTransFastSpeed;
                    break;
                default:
                    starInfo.speed = mFloatTransMidSpeed;
                    break;
            }
            // 初始化星球透明度
            starInfo.alpha = getStarSize(0.3f, 0.8f);
            // 初始化星球位置
            starInfo.xLocation = (int) (starLocation[0] * mTotalWidth);
            starInfo.yLocation = (int) (starLocation[1] * mTotalHeight);
            Log.i(TAG, "xLocation = " + starInfo.xLocation + "--yLocation = "
                    + starInfo.yLocation);
            Log.i(TAG, "stoneSize = " + starSize + "---stoneAlpha = "
                    + starInfo.alpha);
            // 初始化星球位置
            starInfo.direction = getStarDirection();
            mStarInfos.add(starInfo);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < mStarInfos.size(); i++) {
            StarInfo starInfo = mStarInfos.get(i);
            drawStarDynamic(i, starInfo, canvas, paint);
        }
    }

    private void drawStarDynamic(int count, StarInfo starInfo,
                                 Canvas canvas, Paint paint) {
        resetStarFloat(starInfo);
        float starAlpha = starInfo.alpha;
        int xLocation = starInfo.xLocation;
        int yLocation = starInfo.yLocation;
        float sizePercent = starInfo.sizePercent;

        xLocation = (int) (xLocation / sizePercent);
        yLocation = (int) (yLocation / sizePercent);

        Bitmap bitmap = null;
        Rect srcRect = null;
        Rect destRect = new Rect();

        if (picSource != null && picSource.size() < 1) {
            Log.i(TAG, "picSource is not allowed  empty");
            return;
        }

        //随机取图片资源一个，
        Random random = new Random();
        int picIndex = random.nextInt(picSource.size());

        bitmap = ((BitmapDrawable) mResources.getDrawable(picSource.get(1))).getBitmap();
        int mStarPicWidth = bitmap.getWidth();
        int mStarPicHeight = bitmap.getHeight();
        srcRect = new Rect(0, 0, mStarPicWidth, mStarPicHeight);
        destRect.set(xLocation, yLocation,
                xLocation + mStarPicWidth, yLocation
                        + mStarPicHeight);
        paint.setAlpha((int) (starAlpha * 255));
        canvas.save();
        canvas.scale(sizePercent, sizePercent);
        canvas.drawBitmap(bitmap, srcRect, destRect, paint);
        canvas.restore();

    }

}
