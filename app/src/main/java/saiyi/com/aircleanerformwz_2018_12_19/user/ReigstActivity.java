package saiyi.com.aircleanerformwz_2018_12_19.user;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lib.fast.common.widgets.text.CountDownView;

import butterknife.BindView;
import butterknife.OnClick;
import saiyi.com.aircleanerformwz_2018_12_19.R;
import saiyi.com.aircleanerformwz_2018_12_19.core.base.BKMVPActivity;
import saiyi.com.aircleanerformwz_2018_12_19.user.p.ReigstP;

/**
 * Created by 陈姣姣 on 2018/12/20.
 */
public class ReigstActivity extends BKMVPActivity<ReigstP> {

    @BindView(R.id.register_ed1)
    EditText registerEd1;
    @BindView(R.id.register_ed2)
    EditText registerEd2;
    @BindView(R.id.btn_obtain_code)
    CountDownView btnObtainCode;
    @BindView(R.id.register_ed3)
    EditText registerEd3;
    @BindView(R.id.register_ed4)
    EditText registerEd4;
    @BindView(R.id.register_button)
    Button registerButton;
    @BindView(R.id.cb_protocol)
    CheckBox cbProtocol;
    @BindView(R.id.agereement_register)
    TextView agereementRegister;
    @BindView(R.id.activity_register)
    LinearLayout activityRegister;


    private final static  int  MAXCOUNTTIME= 60;

    @Override
    public ReigstP initPresenter(Context context) {
        return new ReigstP(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }

    @Override
    protected void initView() {
        super.initView();



    }



    public void onGetCodeSuccess() {
        btnObtainCode.startCountDown(MAXCOUNTTIME, new CountDownView.CountDownCallBack() {
            @Override
            public void onCountDown(int second) {
                btnObtainCode.setText(String.format("剩余%s秒", second));
            }

            @Override
            public void onCountComplate() {
                btnObtainCode.setText("发送验证码");
                btnObtainCode.setEnabled(true);
            }

            @Override
            public void onCountCancled() {
                btnObtainCode.setText("发送验证码");
                btnObtainCode.setEnabled(true);

            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        btnObtainCode.stopContDown();
        btnObtainCode.release();
    }

    @OnClick({R.id.btn_obtain_code, R.id.register_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_obtain_code:

                if (getPresenter().getCode(registerEd1.getText().toString().trim())) {
                    btnObtainCode.setText("正在获取...");
                    btnObtainCode.setEnabled(false);
                }

                break;
            case R.id.register_button:

               getPresenter().toReigst(registerEd1.getText().toString().trim(),registerEd3.getText().toString().trim(),registerEd4.getText().toString().trim(),registerEd2.getText().toString().trim(),cbProtocol.isChecked());

                break;
        }
    }


      public void  onReigstSuccess(){
        toast("注册成功！");
        back();

     }

}
