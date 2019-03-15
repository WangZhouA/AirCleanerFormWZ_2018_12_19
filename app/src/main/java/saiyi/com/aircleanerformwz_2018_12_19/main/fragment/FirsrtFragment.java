package saiyi.com.aircleanerformwz_2018_12_19.main.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.elvishew.xlog.XLog;
import com.pascalwelsch.holocircularprogressbar.HoloCircularProgressBar;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import saiyi.com.aircleanerformwz_2018_12_19.R;
import saiyi.com.aircleanerformwz_2018_12_19.action.Action;
import saiyi.com.aircleanerformwz_2018_12_19.core.base.BKMVPFragment;
import saiyi.com.aircleanerformwz_2018_12_19.map.WeatherBean;
import saiyi.com.aircleanerformwz_2018_12_19.view.FloatBackground;
import saiyi.com.aircleanerformwz_2018_12_19.view.FloatCircle;
import saiyi.com.aircleanerformwz_2018_12_19.view.MusicProgressBar;

/**
 * Created by 陈姣姣 on 2018/12/25.
 */
public class FirsrtFragment extends BKMVPFragment<FirstPresenter> {

    View viewFirst;
    @BindView(R.id.no_sense)
    TextView noSense;
    @BindView(R.id.tv_status)
    TextView mTv_status;
    @BindView(R.id.holoCircularProgressBar)
    HoloCircularProgressBar mHoloCircularProgressBar;

    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.activity_main)
    RelativeLayout root;
    @BindView(R.id.city_tv)
    TextView cityTv;
    @BindView(R.id.district_tv)
    TextView districtTv;
    @BindView(R.id.image_refresh)
    ImageView imageRefresh;
    @BindView(R.id.seafloor)
    TextView seafloor;
    @BindView(R.id.fly)
    TextView mFly;
    @BindView(R.id.contaminate)
    TextView mContaminate;
    @BindView(R.id.hf_cond)
    ImageView hfCond;
    @BindView(R.id.temperature)
    TextView mTemperature;
    @BindView(R.id.info_ll)
    LinearLayout infoLl;
    @BindView(R.id.cleanair2)
    Button cleanair2;
    Unbinder unbinder;

    @BindView( R.id.rl_pm_view)
    FloatBackground startBtn;

    @BindView(R.id.roundProgress)
    MusicProgressBar roundProgress;

    public List<WeatherBean.HeWeather5Bean> getHeWeather; // 请求接口返回的 集合对象
    private int mProgress=0;


    private boolean isFirst = true ;

    private Handler mHandler =new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){

                case 1:
                    XLog.e("想拿到的mProgress值=="+mProgress);
                    XLog.e("想拿到的PM25值=="+mPm25);
                    roundProgress.setProgress(mProgress);

                    break;
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewFirst = inflater.inflate(R.layout.fragment_first, null);
        Log.e("---->FirsrtFragment", "启动");
        IntentFilter intentFilter = new IntentFilter(Action.TRANSFERLOCATIONINFORMATION);
        getActivity().registerReceiver(broadcastReceiver, intentFilter);

        return viewFirst;

    }
    @Override
    protected void initView(View view) {
        super.initView(view);


        //启动定时器
        try {
            timer.schedule(new RequestTimerTask(), 10000, 60000);

        } catch (Exception e) {

        }

        if (isFirst){
            change(level);
            isFirst =false;
        }


    }

    /**
     *  定时器
     * */
    Timer timer = new Timer();
    class RequestTimerTask extends TimerTask {
        public void run() {
            Message msg =mHandler.obtainMessage();
            msg.what = 1;
            mHandler.sendMessage(msg);
        }
    }



    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) { // 不在最前端显示 相当于调用了onPause();
            //做一些事情 你懂得
            Log.e("---->FirsrtFragment", "FirsrtFragment隐藏");
            return;
        } else { // 在最前端显示 相当于调用了onResume();
            //数据刷新做一些自己的事情--你懂得
            Log.e("---->FirsrtFragment", "FirsrtFragment显示");


        }

    }

    @Override
    public FirstPresenter initPresenter(Context context) {
        return new FirstPresenter(context);
    }

    String mCity;    // 市
    String getDistrict; // 区
    String mPm25;  // pm25
    String fly;  // 什么风向,几级风
    String contaminate;
    int level=1;
    int vis; // 雾霾情况
    String temperature;
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            // 实时传送地址信息过来
            if (action.contains(Action.TRANSFERLOCATIONINFORMATION)) {
//                mCity = intent.getStringExtra(Action.CITY);
                mCity = intent.getStringExtra(Action.CITY);
                XLog.e("mCity"+mCity);
                getDistrict = intent.getStringExtra(Action.DISTRICT);
                XLog.e("getDistrict"+getDistrict);
                cityTv.setText(mCity);
                districtTv.setText(getDistrict);
                getPresenter().getWeatherInfo(getDistrict);




            }
        }
    };



    public void  gsonResultLocation(WeatherBean weatherBean){

        getHeWeather =  weatherBean.getHeWeather5();
        mPm25= getHeWeather.get(0).getAqi().getCity().getPm25();
        fly = getHeWeather.get(0).getNow().getWind().getDir() + getHeWeather.get(0).getNow().getWind().getSc() + "级";
        String path = "https://cdn.heweather.com/cond_icon/" + getHeWeather.get(0).getNow().getCond().getCode() + ".png";
        Glide.with(getActivity()).load(path).error(R.mipmap.ic_launcher).into(hfCond);
        vis = Integer.parseInt(getHeWeather.get(0).getNow().getVis());
        temperature = getHeWeather.get(0).getNow().getTmp();

        setContaminateResult(vis);
        mContaminate.setText(contaminate);
        mTemperature.setText(temperature + "℃");
        mFly.setText(fly);
        // 设置圆心中的值
        setQuality(mPm25);
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.image_refresh)
    public void onViewClicked() {
    }


    //刷新 雾霾情况

    private void setContaminateResult(int vis ){
        if (vis > 5 && vis <= 10) {
            contaminate = "轻度霾";

            level = 2;

            toast(""+level);

        } else if (vis > 2 && vis <= 5) {
            contaminate = "中度霾";
            level = 4;
            toast(""+level);
        } else if (vis <= 2) {
            contaminate = "重度霾";
            level = 6;
            toast(""+level);
        } else {
            contaminate = "无霾";
            level = 1;
            toast(""+level);
        }

    }

    //刷UI 圆心中点的 天气污染情况
    private void setQuality(String pm25) {
        String quality = getQuality(Integer.parseInt(pm25));
        if (quality.trim().length() != 1) {
            mTv_status.setTextSize(TypedValue.COMPLEX_UNIT_SP, 70);
            //设置字体加粗
        } else {
            mTv_status.setTextSize(TypedValue.COMPLEX_UNIT_SP, 100);

        }
        mTv_status.setText(quality);
    }

    private String getQuality(Integer pm25) {
        /**
         * 优：0-35
         * 良 35-75
         * 轻度污染 75-115
         * 中度污染 115-150
         * 重度污染 150-250
         * 严重污染 大于250
         */
        if (pm25 > 250) {
            mProgress = 100;
            // TODO: 2017/7/10 设置holobar颜色，字体颜色
            mHoloCircularProgressBar.setProgressColor(Color.parseColor("#7c191e"));
            mTv_status.setTextColor(Color.parseColor("#7c191e"));
            return "严重污染";
        } else if (pm25 > 150) {
            mProgress = 90;
            mHoloCircularProgressBar.setProgressColor(Color.parseColor("#733c93"));
            mTv_status.setTextColor(Color.parseColor("#733c93"));
            return "重度污染";
        } else if (pm25 > 115) {
            mProgress = 70;
            mHoloCircularProgressBar.setProgressColor(Color.parseColor("#e60012"));
            mTv_status.setTextColor(Color.parseColor("#e60012"));
            return "中度污染";
        } else if (pm25 > 75) {
            mProgress = 50;
            mHoloCircularProgressBar.setProgressColor(Color.parseColor("#f08437"));
            mTv_status.setTextColor(Color.parseColor("#f08437"));
            return "轻度污染";
        } else if (pm25 > 35) {
            mProgress = 30;
            mHoloCircularProgressBar.setProgressColor(Color.parseColor("#ffe100"));
            mTv_status.setTextColor(Color.parseColor("#ffe100"));
            return "良";
        } else {
            mProgress = 10;
            mHoloCircularProgressBar.setProgressColor(Color.parseColor("#009944"));
            mTv_status.setTextColor(Color.parseColor("#009944"));
            return "优";
        }
    }


    private void change(int level ) {
        if (level==0){
            return;
        }

        for (int i =0; i<level;i++) {
            startBtn.addFloatView(new FloatCircle(0.6f, 0.4f, Color.DKGRAY, 8, 0));//圆圈
            startBtn.addFloatView(new FloatCircle(0.8f, 0.5f, Color.BLACK, 6, 1));//黑点
            startBtn.addFloatView(new FloatCircle(0.5f, 0.6f, Color.YELLOW, 6, 1));//黄点
            startBtn.addFloatView(new FloatCircle(0.4f, 0.7f, Color.BLUE, 6, 1));//蓝点
            startBtn.addFloatView(new FloatCircle(0.3f, 0.8f, Color.GREEN, 6, 1));//绿点

        }
          new Handler().postDelayed(new Runnable() {
              @Override
              public void run() {
                  startBtn.startFloat();
              }
          },3000);

    }
}
