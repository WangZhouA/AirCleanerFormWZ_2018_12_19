package saiyi.com.aircleanerformwz_2018_12_19.core.http.service;

import com.lib.fast.common.http.BaseResponse;
import com.lib.fast.common.http.RetorfitServices;
import com.lib.fast.common.http.params.JsonRequestBodyBuilder;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import saiyi.com.aircleanerformwz_2018_12_19.core.bean.User;
import saiyi.com.aircleanerformwz_2018_12_19.user.UserBean;

/**
 * created by siwei on 2018/12/3
 */
public class HttpServiceApi {

    private static HttpServiceApi sHttpServiceApi;
    private HttpService mHttpService;

    private HttpServiceApi() {
        mHttpService = RetorfitServices.getService(HttpService.class);
    }

    public static HttpServiceApi instance() {
        if (sHttpServiceApi == null) {
            synchronized (HttpServiceApi.class) {
                sHttpServiceApi = new HttpServiceApi();
            }
        }
        return sHttpServiceApi;
    }

    //===================================用户模块接口================================

    /**
     * 登录
     *  @param phone 密码
     * @param pwd   手机号
     */
    public Observable<BaseResponse<User>> login(String phone, String pwd) {
        RequestBody requestBody = JsonRequestBodyBuilder.create()
                .addParam("userPhone", phone)
                .addParam("userPwd", pwd)
                .build();
        return mHttpService.login(requestBody);
    }

    /**
     * 注册
     *
     * @param phone      手机号
     * @param password   密码
     * @param name      昵称
     * @param code       验证码
     */
    public Observable<BaseResponse<Void>> register(String phone, String password, String name, String code) {
        RequestBody requestBody = JsonRequestBodyBuilder.create()
                .addParam("userPhone", phone)
                .addParam("userPwd", password)
                .addParam("userName", name)
                .addParam("authCode", code).build();
        return mHttpService.register(requestBody);
    }


    /**
     * @param  phone 电话号码
     * */

    public Observable<BaseResponse<Void>> sendCode( String phone) {
        return mHttpService.sendCode(phone);
    }


    /**
     * @param  phone 查询个人信息
     * */

    public Observable<BaseResponse<UserBean>> getUserInfo(String phone) {
        RequestBody requestBody = JsonRequestBodyBuilder.create()
                .addParam("userPhone", phone)
                .build();
        return mHttpService.getUserInfo(requestBody);
    }




}
