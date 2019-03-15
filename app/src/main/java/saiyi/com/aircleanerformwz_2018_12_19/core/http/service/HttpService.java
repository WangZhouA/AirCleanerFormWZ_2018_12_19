package saiyi.com.aircleanerformwz_2018_12_19.core.http.service;

import com.lib.fast.common.http.BaseResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import saiyi.com.aircleanerformwz_2018_12_19.core.bean.User;
import saiyi.com.aircleanerformwz_2018_12_19.user.UserBean;

/**
 * created by siwei on 2018/12/3
 */
public interface HttpService {

    //===================================用户模块接口================================

    /**
     * 登录
     */
    @POST("user/queryobj")
    Observable<BaseResponse<User>> login(@Body RequestBody requestBody);

    /**
     * 注册
     */
    @POST("user/add")
    Observable<BaseResponse<Void>> register(@Body RequestBody requestBody);

    /**
     * 发送验正码
     */
    @GET("authcode/send/{phone}")
    Observable<BaseResponse<Void>> sendCode( @Path ("phone") String phone);


    /**
     * 查询个人信息
     */
    @POST("user/queryone")
    Observable<BaseResponse<UserBean>> getUserInfo(@Body RequestBody requestBody);


}
