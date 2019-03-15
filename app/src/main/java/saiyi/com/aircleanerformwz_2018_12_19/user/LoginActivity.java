package saiyi.com.aircleanerformwz_2018_12_19.user;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lib.fast.common.activity.view.NavBar;
import com.lib.fast.common.utils.MyConstaints;
import com.lib.fast.common.utils.SPUtils;

import butterknife.BindView;
import butterknife.OnClick;
import saiyi.com.aircleanerformwz_2018_12_19.MainActivity;
import saiyi.com.aircleanerformwz_2018_12_19.R;
import saiyi.com.aircleanerformwz_2018_12_19.core.base.BKMVPActivity;
import saiyi.com.aircleanerformwz_2018_12_19.user.p.LoginP;
import saiyi.com.aircleanerformwz_2018_12_19.user.tool.BindOnClickListeber;

/**
 * Created by 陈姣姣 on 2018/12/20.
 */
public class LoginActivity extends BKMVPActivity<LoginP> {

    @BindView(R.id.login_account_ed)
    EditText loginAccountEd;
    @BindView(R.id.login_psw_ed)
    EditText loginPswEd;
    @BindView(R.id.login_forget_bt)
    Button loginForgetBt;
    @BindView(R.id.isCheck)
    CheckBox mRememberPwdCk;
    @BindView(R.id.register_tv)
    TextView registerTv;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.activity_login)
    LinearLayout activityLogin;
    @BindView(R.id.remember_pwd_tv)
    TextView mRememberPwdTv;

    private String PHONE_;
    private String PassWord_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_xml);
        getTitleBar().setTitle("登录");
        getTitleBar().setClickListener(new NavBar.NavBarOnClickListener() {
            @Override
            public void onLeftIconClick(View view) {
                finish();
            }

            @Override
            public void onLeftSenIconClick(View view) {

            }

            @Override
            public void onRightIconClick(View view) {

            }

            @Override
            public void onRightTxtClick(View view) {

            }
        });

    }

    @Override
    public LoginP initPresenter(Context context) {
        return new LoginP(context);
    }

    @Override
    protected void initView() {
        super.initView();
        mRememberPwdCk.setOnClickListener(new BindOnClickListeber(mRememberPwdTv, mRememberPwdCk));
    }

    @Override
    protected void initListener() {
        super.initListener();

    }

    @Override
    protected void initData() {
        super.initData();
        PHONE_ = SPUtils.getString(LoginActivity.this, MyConstaints.PHONE_,"");
        PassWord_ = SPUtils.getString(LoginActivity.this, MyConstaints.PASSWORD,"");
        loginPswEd.setText(PassWord_);
        loginAccountEd.setText(PHONE_);
        registerEventBus();




    }


    @OnClick({R.id.login_forget_bt, R.id.isCheck, R.id.register_tv, R.id.btn_login, R.id.activity_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_forget_bt:
                break;
            case R.id.isCheck:
                break;
            case R.id.register_tv:
                openActivity(ReigstActivity.class);
                break;
            case R.id.btn_login:

                String phone = loginAccountEd.getText().toString().trim();
                String pwd = loginPswEd.getText().toString().trim();
                if (getPresenter().login(phone, pwd, mRememberPwdCk.isChecked())) {
                    showCustomLoading("正在登陆");
                }

                break;
            case R.id.activity_login:
                break;
        }
    }

    /**
     * 登陆成功
     */
    public void goMainActivity() {
        dismissProgressDialog();
        openActivity(MainActivity.class);
        finish();
    }

}
