package saiyi.com.aircleanerformwz_2018_12_19.main.fragment;

import android.content.Context;

import com.lib.fast.common.mvp.ModelImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import saiyi.com.aircleanerformwz_2018_12_19.map.WeatherApi;

/**
 * Created by 最帅的男人 on 2019/1/7.
 */
public class   FirestModel extends ModelImpl {

    public FirestModel(Context context) {
        super(context);
    }

    /**
    *  @param mCity  城市
     *
    ***/
    public void  getWeather(String mCity,StringCallback myStringCallback){
        OkHttpUtils.get()
                .url(WeatherApi.CITY + mCity + WeatherApi.KEY)
                .build()
                .execute(myStringCallback);
    }

}
