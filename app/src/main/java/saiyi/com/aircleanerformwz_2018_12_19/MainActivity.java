package saiyi.com.aircleanerformwz_2018_12_19;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.lib.fast.common.activity.view.NavBar;
import com.lib.fast.common.mvp.IView;
import com.lib.fast.common.utils.Util;
import com.nineoldandroids.view.ViewHelper;

import butterknife.BindView;
import butterknife.OnClick;
import saiyi.com.aircleanerformwz_2018_12_19.core.base.BKActivity;
import saiyi.com.aircleanerformwz_2018_12_19.main.fragment.DerviceFragment;
import saiyi.com.aircleanerformwz_2018_12_19.main.fragment.FirsrtFragment;

public class MainActivity extends BKActivity implements DrawerLayout.DrawerListener, IView {


    DrawerLayout mDrawerLayout;
    //左边布局
    View mLeftMenuFragment;

    FirsrtFragment firsrtFragment;
    DerviceFragment derviceFragment;
    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_equipment)
    RadioButton rbEquipment;

    private FragmentManager mFm;
    private String[] mFragmentTagList = {"FirsrtFragment", "DerviceFragment"};
    private Fragment mCurrentFragmen = null; // 记录当前显示的Fragment


    // 添加fragment
    private void addFragment() {

        firsrtFragment = new FirsrtFragment();
        mCurrentFragmen = firsrtFragment;
        derviceFragment = new DerviceFragment();
        Intent intent =new Intent(this,MyService.class);
        startService(intent);

    }

    // 转换Fragment
    void switchFragment(Fragment to, String tag) {
        if (mCurrentFragmen != to) {
            FragmentTransaction transaction = mFm.beginTransaction();
            if (!to.isAdded()) {
                // 没有添加过:
                // 隐藏当前的，添加新的，显示新的
                transaction.hide(mCurrentFragmen).add(R.id.main_fragment_layout, to, tag).show(to);
            } else {
                // 隐藏当前的，显示新的
                transaction.hide(mCurrentFragmen).show(to);
            }
            mCurrentFragmen = to;
            transaction.commitAllowingStateLoss();
        }
    }


    // 重置Fragment
    private void updateFragment(Bundle outState) {
        mFm = getSupportFragmentManager();
        if (outState == null) {
            FragmentTransaction transaction = mFm.beginTransaction();
            mCurrentFragmen = firsrtFragment;
            transaction.add(R.id.main_fragment_layout, firsrtFragment, mFragmentTagList[0]).commitAllowingStateLoss();
        } else {
            // 通过tag找到fragment并重置
            if (firsrtFragment == null)
                firsrtFragment = (FirsrtFragment) mFm.findFragmentByTag(mFragmentTagList[0]);
            derviceFragment = (DerviceFragment) mFm.findFragmentByTag(mFragmentTagList[1]);
            mFm.beginTransaction().show(firsrtFragment).hide(derviceFragment);
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        // 重置Fragment，防止当内存不足时导致Fragment重叠
        updateFragment(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_first_layout);

        registerEventBus();
        getTitleBar().setTitle(getString(R.string.main_first));
        getTitleBar().setLeftIcon(R.mipmap.list);
        getTitleBar().setClickListener(new NavBar.NavBarOnClickListener() {
            @Override
            public void onLeftIconClick(View view) {

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

        addFragment();
        // 初始化首次进入时的Fragment
        mFm = getSupportFragmentManager();
        FragmentTransaction transaction = mFm.beginTransaction();
        transaction.add(R.id.main_fragment_layout, derviceFragment, mFragmentTagList[1]);
        transaction.add(R.id.main_fragment_layout, mCurrentFragmen, mFragmentTagList[0]);
        transaction.commitAllowingStateLoss();

    }

    @Override
    protected void initView() {
        super.initView();
        //侧滑
        mDrawerLayout = findViewById(R.id.unbind_drawer);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
        mDrawerLayout.setDrawerListener(this);
        Util.setDrawerLeftEdgeSize(this, mDrawerLayout, 0.1f);
        mLeftMenuFragment = findViewById(R.id.left_menu);

    }

    @Override
    protected void initListener() {


    }

    @Override
    protected void initData() {


    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        View mContent = mDrawerLayout.getChildAt(0);
        float scale = 1 - slideOffset;
        if (drawerView.getTag().equals("LEFT")) {

            float leftScale = 1 - 0.3f * scale;

            ViewHelper.setScaleX(drawerView, leftScale);
            ViewHelper.setScaleY(drawerView, leftScale);
            ViewHelper.setTranslationX(mContent, drawerView.getMeasuredWidth()
                    * (1 - scale));
            ViewHelper.setPivotX(mContent, 0);
        } else {
            ViewHelper.setTranslationX(mContent, -drawerView.getMeasuredWidth()
                    * slideOffset);
            ViewHelper.setPivotX(mContent, mContent.getMeasuredWidth());
        }
        ViewHelper.setPivotY(mContent, mContent.getMeasuredHeight() / 2);
        mContent.invalidate();
    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
                Gravity.RIGHT);
    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    @OnClick({R.id.rb_home, R.id.rb_equipment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_home:

                switchFragment(firsrtFragment, mFragmentTagList[0]);
                break;
            case R.id.rb_equipment:

                switchFragment(derviceFragment, mFragmentTagList[1]);
                break;
        }
    }


    long mExitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            long currentTimeMillis = System.currentTimeMillis();
            Log.e(TAG, "onKeyDown: ---------------" );
            Log.e(TAG, "onKeyDown:   currentTime:"+currentTimeMillis );
            Log.e(TAG, "onKeyDown:   mExitTime:"+mExitTime );
            if(currentTimeMillis-mExitTime<=2000){
                finish();
                System.exit(0);
            }else{
                mExitTime=currentTimeMillis;
                Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        //返回true表示自己处理back事件
        return super.onKeyDown(keyCode, event);
    }




}
