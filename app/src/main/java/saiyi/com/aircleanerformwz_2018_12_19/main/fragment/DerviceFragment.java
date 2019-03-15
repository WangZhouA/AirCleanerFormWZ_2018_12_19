package saiyi.com.aircleanerformwz_2018_12_19.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lib.fast.common.mvp.PresenterImpl;

import saiyi.com.aircleanerformwz_2018_12_19.R;
import saiyi.com.aircleanerformwz_2018_12_19.core.base.BKMVPFragment;

/**
 * Created by 陈姣姣 on 2018/12/25.
 */
public class DerviceFragment extends BKMVPFragment {

    View viewDevice ;
    @Override
    public PresenterImpl initPresenter(Context context) {
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        viewDevice = inflater.inflate(R.layout.fragment_device,null);
        Log.e("---->DerviceFragment","启动");
        return viewDevice;

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) { // 不在最前端显示 相当于调用了onPause();

            //做一些事情 你懂得
            Log.e("---->DerviceFragment","DerviceFragment隐藏");
            return;
        }else { // 在最前端显示 相当于调用了onResume();
//数据刷新做一些自己的事情--你懂得
            Log.e("---->DerviceFragment", "DerviceFragment显示");

        }
    }
}
