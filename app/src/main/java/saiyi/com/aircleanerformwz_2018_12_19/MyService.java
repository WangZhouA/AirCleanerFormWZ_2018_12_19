package saiyi.com.aircleanerformwz_2018_12_19;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.elvishew.xlog.XLog;

import saiyi.com.aircleanerformwz_2018_12_19.action.Action;
import saiyi.com.aircleanerformwz_2018_12_19.map.LocationService;

/**
 * Created by 最帅的男人 on 2019/1/4.
 */
public class MyService extends Service {


    //    public static MyService instance;
//    //地图定位类
    LocationService locationService;
    private Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Intent adressIntent;
            switch (msg.what){
                case 0:
                    //不停的发地址过来
                    adressIntent= new Intent(Action.TRANSFERLOCATIONINFORMATION);
                    adressIntent.putExtra(Action.CITY,mCity);
                    adressIntent.putExtra(Action.DISTRICT,mDistrict);
                    sendBroadcast(adressIntent);
                    break;
            }
        }
    };

//
//    public static MyService getInstance() {
//        if (instance == null) {
//            synchronized (MyService.class) {
//                instance = new MyService();
//            }
//        }
//        return instance;
//    }
//
//
//

    @Override
    public void onCreate() {
        super.onCreate();

        locationService = new LocationService(getApplicationContext());
        locationService.registerListener(bA);
        locationService.start();

        XLog.e("Service启动");

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        XLog.e("Service被杀死");

        if (locationService != null) {
            if (bA != null) {
                locationService.unregisterListener(bA);
                locationService.stop();
            }
        }


    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    String mCity;
    String mCityCode;
    String mLalotude;
    String mDistrict;
    String AddrStr;
    private BDAbstractLocationListener bA = new BDAbstractLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {

            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                AddrStr = location.getAddrStr();
                mCity = location.getCity();
                mCityCode = location.getCityCode();
                mLalotude = location.getLatitude() + "," + location.getLongitude();
                mDistrict = location.getDistrict();
                if (!mCity.isEmpty() && !mDistrict.isEmpty()) {

                    XLog.e(mCity);
                    //设置吧收到了地址信息不停的传
                    myHandler.sendEmptyMessage(0);
                }
            }
        }
    };
}




