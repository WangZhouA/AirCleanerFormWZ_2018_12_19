package saiyi.com.aircleanerformwz_2018_12_19.main;

import android.content.Context;

import com.lib.fast.common.mvp.PresenterImpl;

import saiyi.com.aircleanerformwz_2018_12_19.MainActivity;

/**
 * Created by 陈姣姣 on 2018/12/20.
 */
public class MainP extends PresenterImpl<MainActivity,MainM>{

    public MainP(Context context) {
        super(context);
    }

    @Override
    public MainM initModel(Context context) {

        return new MainM(context);
    }

}
