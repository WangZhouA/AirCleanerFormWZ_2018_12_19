package saiyi.com.aircleanerformwz_2018_12_19.view;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by DeanGuo on 1/7/17.
 */

public class FloatCircle extends FloatObject {
    private int r;
    private int style;

    public FloatCircle(float posX, float posY, int color, int r, int style) {
        super(posX, posY);
        setAlpha(120);
        setColor(color);//
        this.r = r;
        this.style = style;
        //设置画笔空心实心
        //方法   半径根据变量赋值
        //使用随机数生成位置？  固定模式固定数量固定位置？
    }

    @Override
    public void drawFloatObject(Canvas canvas, float x, float y, Paint paint) {
        if (style == 0)
            paint.setStyle(Paint.Style.STROKE); //空心圆
        else
            paint.setStyle(Paint.Style.FILL);//实心
        canvas.drawCircle(x, y, r, paint);
    }
}
