package com.rlzz.uwinmes.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.rlzz.uwinmes.R;
import com.rlzz.uwinmes.common.Constants;
import com.rlzz.uwinmes.common.base.MVPActivity;
import com.rlzz.uwinmes.mvp.login.ILoginView;
import com.rlzz.uwinmes.mvp.login.LoginPresenter;
import com.rlzz.uwinmes.utils.LogUtil;
import com.rlzz.uwinmes.utils.PreferencesManager;
import com.rlzz.uwinmes.utils.StatusBarUtil;
import com.rlzz.uwinmes.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class LoginActivity extends MVPActivity<LoginPresenter> implements ILoginView {

    @BindView(R.id.iv_background)
    ImageView ivBackground;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.btn_clearAccount)
    ImageButton btnClearAccount;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_clearPwd)
    ImageButton btnClearPwd;
    @BindView(R.id.btn_showPwd)
    CheckBox btnShowPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.cb_keepPassword)
    CheckBox cbKeepPassword;

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LogUtil.d("LogingActivity onCreate");
    }

    @Override
    public void initView() {
        StatusBarUtil.setTranslucent(this); //设置状态栏和虚拟菜单栏为全透明

        getSupportActionBar().hide(); // 隐藏ActionBar
        // 将背景图片高斯模糊，提高逼格
        Glide.with(this)
                .load(R.drawable.bg_login)
                //第二个参数是圆角半径，第三个是模糊程度，3-5之间个人感觉比较好。
                .bitmapTransform(new BlurTransformation(this, 25, 3))
                .into(ivBackground);

        initClearButton();
        setEditTextListener();

    }

    @Override
    public void initData() {
        // 先申请权限


        boolean check = PreferencesManager.getInstanceDefault().getBoolean(Constants.KEY_KEEPPASSWORD);
        cbKeepPassword.setChecked(check);
        if(check){
            String account = PreferencesManager.getInstanceUser().getString(Constants.KEY_ACCOUNT);
            String password = PreferencesManager.getInstanceUser().getString(Constants.KEY_PASSWORD);

            etAccount.setText(account);
            etPassword.setText(password);
        }
    }

    private void initClearButton() {
        if (TextUtils.isEmpty(etAccount.getText())) {
            btnClearAccount.setVisibility(View.INVISIBLE);
        } else {
            btnClearAccount.setVisibility(View.VISIBLE);
        }

        if (TextUtils.isEmpty(etPassword.getText())) {
            btnClearPwd.setVisibility(View.INVISIBLE);
            btnShowPwd.setVisibility(View.INVISIBLE);
        } else {
            btnClearPwd.setVisibility(View.VISIBLE);
            btnShowPwd.setVisibility(View.VISIBLE);
        }
    }

    private void setEditTextListener() {
        etAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    btnClearAccount.setVisibility(View.VISIBLE);
                } else {
                    btnClearAccount.setVisibility(View.INVISIBLE);
                }
            }
        });
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                LogUtil.d("beforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                LogUtil.d("onTextChanged");
            }

            @Override
            public void afterTextChanged(Editable s) {
                LogUtil.d("afterTextChanged");
                if (s.length() > 0) {
                    btnClearPwd.setVisibility(View.VISIBLE);
                    btnShowPwd.setVisibility(View.VISIBLE);
                } else {
                    btnClearPwd.setVisibility(View.INVISIBLE);
                    btnShowPwd.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void onLoginSuccess() {
        ToastUtil.show("登录成功");
        MainActivity.GoToActivity(this);
    }

    @Override
    public void onLoginFailure() {
        ToastUtil.show("登录失败");
    }

    @OnClick(R.id.btn_clearAccount)
    public void onBtnClearAccountClicked() {
        etAccount.getText().clear();
    }

    @OnClick(R.id.btn_clearPwd)
    public void onBtnClearPwdClicked() {
        etPassword.getText().clear();
    }

    @OnCheckedChanged(R.id.btn_showPwd)
    public void onBtnShowPwdClicked(boolean checked) {
        if (checked) {
            etPassword.setInputType(EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
        }
        etPassword.setSelection(etPassword.getText().length());
    }

    @OnCheckedChanged(R.id.cb_keepPassword)
    public void onKeepPasswordCheckedChanged(boolean checked) {
        PreferencesManager.getInstanceDefault().putBoolean(Constants.KEY_KEEPPASSWORD, checked);
    }

    @OnClick(R.id.btn_login)
    public void onbtnLoginClicked() {
        presenter.login(etAccount.getText().toString(), etPassword.getText().toString(), cbKeepPassword.isChecked());
    }

    @Override
    public void showLoadingDialog(String msg) {
        showProgressDialog(msg);
    }

    @Override
    public void closeLoadingDialog() {
        dismissProgressDialog();
    }
}
