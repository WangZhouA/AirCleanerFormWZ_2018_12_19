package saiyi.com.aircleanerformwz_2018_12_19;

import android.support.annotation.NonNull;

import com.lib.fast.common.activity.BaseApplication;
import com.lib.fast.common.config.IBuildConfig;

import okhttp3.Interceptor;
import saiyi.com.aircleanerformwz_2018_12_19.core.app.SmartKeyCabinetClientBuildConfig;
import saiyi.com.aircleanerformwz_2018_12_19.core.http.intercept.TokenIntercept;

/**
 * created by siwei on 2018/11/3
 */
public class AirCleanerApplication extends BaseApplication {
    @NonNull
    @Override
    public IBuildConfig getBuildConfig() {
        return new SmartKeyCabinetClientBuildConfig();
    }

    @Override
    protected Interceptor[] addOkHttpIntercept() {
        return new Interceptor[]{new TokenIntercept()};
    }
}
