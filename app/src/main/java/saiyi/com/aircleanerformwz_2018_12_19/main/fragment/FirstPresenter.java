package saiyi.com.aircleanerformwz_2018_12_19.main.fragment;

import android.content.Context;

import com.google.gson.Gson;
import com.lib.fast.common.mvp.PresenterImpl;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import saiyi.com.aircleanerformwz_2018_12_19.map.WeatherBean;

/**
 * Created by 最帅的男人 on 2019/1/7.
 */
public class FirstPresenter extends PresenterImpl<FirsrtFragment,FirestModel> {



    WeatherBean weatherBean ;
    public FirstPresenter(Context context) {
        super(context);


    }

    @Override
    public FirestModel initModel(Context context) {
        return  new FirestModel(context) ;
    }



    public  void getWeatherInfo(String mCtiy ){

        if (mCtiy.isEmpty()){
            getView().toast("城市未查询到");

        }else {
            getModel().getWeather(mCtiy, new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int i) {
                    getView().toast("天气网络异常");
                }
                @Override
                public void onResponse(String s, int i) {
                    getView().toast("天气网络获取到了");
                    Gson gson = new Gson();
                    weatherBean= gson.fromJson(s,WeatherBean.class);
                    getView().gsonResultLocation(weatherBean);

                }
            });
        }
    }




}
