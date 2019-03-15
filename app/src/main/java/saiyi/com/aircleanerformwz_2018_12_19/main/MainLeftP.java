package saiyi.com.aircleanerformwz_2018_12_19.main;

import android.content.Context;

import com.lib.fast.common.mvp.PresenterImpl;
import com.lib.fast.common.utils.MyConstaints;
import com.lib.fast.common.utils.SPUtils;

import saiyi.com.aircleanerformwz_2018_12_19.MainLeftFragment;

/**
 * Created by 陈姣姣 on 2018/12/20.
 */
public class MainLeftP extends PresenterImpl<MainLeftFragment,MainLeftModel> {



    public MainLeftP(Context context) {
        super(context);
    }

    @Override
    public MainLeftModel initModel(Context context) {
        return new MainLeftModel(context);
    }



    // 已登录被点击的提示

    long mTimefirst;
    public  void   LoginToast(){
        long currentTimeMillis = System.currentTimeMillis();//点击时间
        if (currentTimeMillis - mTimefirst <= 2000) {
            //退出
            exitUser();
        } else {
            getView().toast("已登录,再点退出");
            //进入用户详细列表
            mTimefirst = currentTimeMillis;



        }
    }




    public  void exitUser(){
        getView().showText();
        SPUtils.remove(getView().getActivity(), MyConstaints.PHONE_);
        SPUtils.remove(getView().getActivity(), MyConstaints.PASSWORD);
    }
}
