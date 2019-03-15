package saiyi.com.aircleanerformwz_2018_12_19.MyContact;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * Created by 陈姣姣 on 2017/11/30.
 */

public class StringUtils {

    public static final int ONE=0x01;
    public static final int TWO=0x02;
    public static final int THREE=0x03;
    public static final int FOUR=0x04;


    // 服务器地址
    public static  String HTTP_SERVICE ="http://172.16.10.68:8952/WaterPurifier/app/";
    // 图片
    public static  String HTTP_SERVICE_IMAGE ="http://172.16.10.68:8952/image/WaterPurifier/";

    //注册
    //发送邮件的验证码
    //登录
    //添加设备
    //查询设备
    // 删除设备
    //设备重命名
    //提醒记录
    //删除提醒
    //修改名字
    //跟换滤芯
    //查询设备详情
    //设置额定净水量
    //查询用户信息
    //查看历史




    public  static  void showImage(Context context, String url, int erro , int loadpic, ImageView imageView){
        Glide.with(context).load(url).asBitmap().fitCenter().placeholder(loadpic).error(erro).dontAnimate().into(imageView);
    }
}
