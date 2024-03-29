package com.lib.fast.common.activity;

import android.content.Context;
import android.os.Bundle;

import com.lib.fast.common.mvp.IView;
import com.lib.fast.common.mvp.PresenterImpl;
import com.lib.fast.common.utils.MyConstaints;
import com.lib.fast.common.utils.SPUtils;

/**
 * Created by siwei on 2018/3/13.
 */

public abstract class BaseMVPActivity<P extends PresenterImpl> extends BaseActivity implements IView {

    private P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter(this);
        if(mPresenter != null){
            mPresenter.attachView(this);
        }
    }



    public String getPhone(){
        return   SPUtils.getString(this, MyConstaints.PHONE_,"");

    }

    public String getPassWord(){

        return   SPUtils.getString(this, MyConstaints.PASSWORD,"");
    }



    /**初始化Presenter*/
    public abstract P initPresenter(Context context);

    protected P getPresenter(){
        return mPresenter;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null){
            mPresenter.detatchView();
            mPresenter = null;
        }
    }
}
