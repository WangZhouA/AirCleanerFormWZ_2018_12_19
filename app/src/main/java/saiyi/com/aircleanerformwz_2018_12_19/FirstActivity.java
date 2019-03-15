package saiyi.com.aircleanerformwz_2018_12_19;

import android.Manifest;
import android.os.Bundle;
import android.os.Handler;

import com.lib.fast.common.permission.PermissionHelper;
import com.lib.fast.common.permission.PermissionRationale;

import saiyi.com.aircleanerformwz_2018_12_19.core.base.BKActivity;


/**
 * Created by 陈姣姣 on 2018/12/19.
 */
public class FirstActivity extends BKActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_xml);
    }

    @Override
    protected void initView() {
      super.initView();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

        String[] permission ={ Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_WIFI_STATE}; //定位权限
        PermissionRationale permissionRationale =new PermissionRationale("定位权限",permission);
        PermissionHelper permissionHelper =new PermissionHelper(this,permissionRationale);
        permissionHelper.requestPermission();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                openActivity(MainActivity.class);
                back();
            }
        },3000);

    }
}
