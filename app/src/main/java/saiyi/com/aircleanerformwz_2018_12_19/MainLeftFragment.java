package saiyi.com.aircleanerformwz_2018_12_19;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lib.fast.common.utils.MyConstaints;
import com.lib.fast.common.utils.SPUtils;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import saiyi.com.aircleanerformwz_2018_12_19.core.base.BKMVPFragment;
import saiyi.com.aircleanerformwz_2018_12_19.main.MainLeftP;
import saiyi.com.aircleanerformwz_2018_12_19.user.LoginActivity;
import saiyi.com.aircleanerformwz_2018_12_19.user.UserActivity;

/**
 * Created by 陈姣姣 on 2018/12/20.
 */
public class MainLeftFragment extends BKMVPFragment<MainLeftP> {


    View viewLayout;
    @BindView(R.id.main_button)
    Button mainButton;
    @BindView(R.id.tv_myself)
    TextView tvMyself;
    @BindView(R.id.tv_exit)
    TextView tvExit;
    @BindView(R.id.dl_left)
    RelativeLayout dlLeft;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewLayout = inflater.inflate(R.layout.fragment_main_left_xml, null);
        return viewLayout;
    }


    @Override
    public void onResume() {
        super.onResume();

       if(TextUtils.isEmpty(SPUtils.getString(getActivity(), MyConstaints.PHONE_,""))){
           mainButton.setText("登录/注册");
       }else {
           mainButton.setText("已登录");
       }
    }


    public  void  showText(){
        mainButton.setText("登录/注册");
        openActivity(LoginActivity.class);
    }


    @Override
    public MainLeftP initPresenter(Context context) {
        return new MainLeftP(context);
    }

    protected void initView(View view) {

        super.initView(view);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.main_button, R.id.tv_myself, R.id.tv_exit, R.id.dl_left})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_button:

                if(TextUtils.isEmpty(SPUtils.getString(getActivity(), MyConstaints.PHONE_,""))){
                    // 如果是登录的用户
                    openActivity(LoginActivity.class);
                }else {
                    getPresenter().LoginToast();

                }

                break;
            case R.id.tv_myself:

                if(TextUtils.isEmpty(SPUtils.getString(getActivity(), MyConstaints.PHONE_,""))){

                    openActivity(LoginActivity.class);
                }else {
                    openActivity(UserActivity.class);

                }

                break;
            case R.id.tv_exit:
                    toast("退出成功");
                    openActivity(LoginActivity.class);


                break;
            case R.id.dl_left:
                break;
        }
    }



}
