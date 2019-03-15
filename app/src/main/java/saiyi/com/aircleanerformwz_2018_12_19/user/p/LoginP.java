package saiyi.com.aircleanerformwz_2018_12_19.user.p;

import android.content.Context;
import android.text.TextUtils;

import com.lib.fast.common.http.BaseResponseListener;
import com.lib.fast.common.http.exception.ErrorStatus;
import com.lib.fast.common.mvp.PresenterImpl;
import com.lib.fast.common.utils.MyConstaints;
import com.lib.fast.common.utils.SPUtils;
import com.lib.fast.common.utils.StringUtils;

import saiyi.com.aircleanerformwz_2018_12_19.core.bean.User;
import saiyi.com.aircleanerformwz_2018_12_19.user.LoginActivity;
import saiyi.com.aircleanerformwz_2018_12_19.user.model.LoginModel;

/**
 * Created by 陈姣姣 on 2018/12/20.
 */
public class LoginP extends PresenterImpl<LoginActivity,LoginModel> {

    public LoginP(Context context) {
        super(context);
    }

    @Override
    public LoginModel initModel(Context context) {
        return  new LoginModel(context) ;
    }

    /**
     * 登陆
     */
    public boolean login(final String phone, final String pwd, final boolean isRemember) {
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(pwd)) {
            getView().toast("请输入手机号或密码");
        } else if (!StringUtils.isMobileNum(phone)) {
            getView().toast("请输入正确的手机号");
        } else {
            getModel().login(phone, pwd, new BaseResponseListener<User>() {
                @Override
                public void onResponse(User data) {
                    super.onResponse(data);


                    //缓存当前登陆的用户手机号
                    SPUtils.putString(getView(), MyConstaints.PHONE_,phone);
                    if (isRemember) {
                        //记住密码
                        SPUtils.putString(getView(), MyConstaints.PHONE_,phone);
                        SPUtils.putString(getView(), MyConstaints.PASSWORD,pwd);
                    }
                    getView().goMainActivity();
                }

                @Override
                public void onFaild(ErrorStatus e) {
                    super.onFaild(e);
                    getView().toastErrorMsg(e);
                }
            });
            return true;
        }
        return false;
    }
}
