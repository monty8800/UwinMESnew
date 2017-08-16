package com.rlzz.uwinmes;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rlzz.uwinmes.common.base.BaseActivity;
import com.rlzz.uwinmes.utils.ToolBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.ll_viewcContainer)
    LinearLayout llViewcContainer;
    @BindView(R.id.btn_addView)
    Button btnAddView;
    @BindView(R.id.btn_removeView)
    Button btnRemoveView;

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initBeforeView() {
        // TODO: 2017/8/11 init presenter
    }

    @Override
    public void initView() {
        ToolBarUtil.hideToolbarNavigation(getToolbar());
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @OnClick(R.id.btn_addView)
    public void onBtnAddViewClicked() {
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setText("textview");
        llViewcContainer.addView(textView,0);
    }

    @OnClick(R.id.btn_removeView)
    public void onBtnRemoveViewClicked() {
        int childCount = llViewcContainer.getChildCount();
        if (childCount != 0) {
            llViewcContainer.removeViewAt(childCount - 1);
        }

    }
}
