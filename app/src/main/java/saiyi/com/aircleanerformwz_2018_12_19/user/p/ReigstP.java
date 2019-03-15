package saiyi.com.aircleanerformwz_2018_12_19.user.p;

import android.content.Context;
import android.text.TextUtils;

import com.lib.fast.common.http.BaseResponseListener;
import com.lib.fast.common.http.exception.ErrorStatus;
import com.lib.fast.common.mvp.PresenterImpl;
import com.lib.fast.common.utils.StringUtils;

import saiyi.com.aircleanerformwz_2018_12_19.user.ReigstActivity;
import saiyi.com.aircleanerformwz_2018_12_19.user.model.ReigstModel;

/**
 * Created by 陈姣姣 on 2018/12/20.
 */
public class ReigstP extends PresenterImpl<ReigstActivity,ReigstModel> {

    public ReigstP(Context context) {
        super(context);
    }

    @Override
    public ReigstModel initModel(Context context) {
        return new ReigstModel(context);
    }

    public boolean getCode(String phone) {

        if (TextUtils.isEmpty(phone) || !StringUtils.isMobileNum(phone)) {
            getView().toast("请输入正确的手机号");
            return false;
        } else {

            getModel().sendCode(phone, new BaseResponseListener<Void>() {
                @Override
                public void onResponse(Void data) {
                    super.onResponse(data);
                    getView().onGetCodeSuccess();

                }

                @Override
                public void onFaild(ErrorStatus e) {
                    super.onFaild(e);
                    getView().toastErrorMsg(e);
                }
            });


        }
        return true;

    }

    public void toReigst(String phone, String password, String name, String code, boolean isCheck) {


        if (!isCheck){
            getView().toast("请阅读用户协议");
        }

        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password) || TextUtils.isEmpty(name) || TextUtils.isEmpty(code)) {

            getView().toast("注册信息请填写完整");

        } else {
            getModel().reigst(phone, password, name, code, new BaseResponseListener<Void>() {
                @Override
                public void onResponse(Void data) {
                    super.onResponse(data);
                    getView().onReigstSuccess();
                }

                @Override
                public void onFaild(ErrorStatus e) {
                    super.onFaild(e);
                    if (e.code==2) {
                        getView().toast("该用户已存在");
                    }else if (e.code==3){
                        getView().toast("验证码过期");
                    }else {
                        getView().toast("注册失败");
                    }
                }
            });
        }
    }
}
