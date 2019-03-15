package saiyi.com.aircleanerformwz_2018_12_19.user;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import saiyi.com.aircleanerformwz_2018_12_19.R;
import saiyi.com.aircleanerformwz_2018_12_19.core.base.BKMVPActivity;
import saiyi.com.aircleanerformwz_2018_12_19.user.p.UserInfoP;

/**
 * Created by 陈姣姣 on 2018/12/28.
 */
public class UserActivity extends BKMVPActivity<UserInfoP> {

    @BindView(R.id.center_name)
    TextView centerName;
    @BindView(R.id.center_phone)
    TextView centerPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_center);
    }


    @Override
    public UserInfoP initPresenter(Context context) {
        return new UserInfoP(context);
    }


    @Override
    protected void initView() {
        super.initView();
    }


    @Override
    protected void initData() {
        super.initData();

        setTitle("账号");


        if (getPresenter().getUserInfo(getPhone())){

            toast("正在获取用户信息");
        }

    }

    public void showPhone(UserBean data) {
        if (data==null){
            return;
        }
        centerPhone.setText(data.getUserPhone());
        centerName.setText(data.getUserName());
    }

}
