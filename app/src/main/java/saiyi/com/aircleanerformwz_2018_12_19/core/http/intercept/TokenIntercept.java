package saiyi.com.aircleanerformwz_2018_12_19.core.http.intercept;

import android.text.TextUtils;

import com.lib.fast.common.http.interceptor.RequestInterfaceInterceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

/**
 * created by siwei on 2018/12/3
 */
public class TokenIntercept extends RequestInterfaceInterceptor {

    private static List<String> sInterceptRequestUrlList = new ArrayList<>();

    static {
//        //对于这些不进行拦截,其余的都进行拦截
//        sInterceptRequestUrlList.add(createHasMatchers("keycabinet/app/user/sendCode"));
//        sInterceptRequestUrlList.add(createEndWithMatchers("keycabinet/app/user/updatePasswordByPhone"));
//        sInterceptRequestUrlList.add(createHasMatchers("keycabinet/app/user/checkPhone"));
//        sInterceptRequestUrlList.add(createEndWithMatchers("keycabinet/app/user/register"));
//        sInterceptRequestUrlList.add(createEndWithMatchers("keycabinet/app/user/login"));
    }

    public TokenIntercept() {
        super(sInterceptRequestUrlList);
        isReverseIntercept(true);
    }

    @Override
    public Response onIntercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!checkTokenOfHead(request)) {

        }
        return chain.proceed(request);
    }

    //检查头里是否有token
    private boolean checkTokenOfHead(Request request) {
        if (request != null) {
            String tokenValue = request.header("token");
            return !TextUtils.isEmpty(tokenValue);
        }
        return false;
    }
}
