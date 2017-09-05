package com.rlzz.uwinmes.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.rlzz.uwinmes.R;
import com.rlzz.uwinmes.common.base.BaseActivity;
import com.rlzz.uwinmes.entity.TestTask;
import com.rlzz.uwinmes.ui.fragment.InspectionContentFragment;
import com.rlzz.uwinmes.ui.fragment.InspectionItemFragment;

import butterknife.BindView;

/**
 * 检验单填写
 */
public class InspectionActivity extends BaseActivity {

    @BindView(R.id.tv_inspectionTemplate)
    TextView tvInspectionTemplate;
    @BindView(R.id.et_materialName)
    EditText etMaterialName;
    @BindView(R.id.et_supplierName)
    EditText etSupplierName;
    @BindView(R.id.et_arrivalOrderNumber)
    EditText etArrivalOrderNumber;
    @BindView(R.id.et_count)
    EditText etCount;
    @BindView(R.id.fl_menu)
    FrameLayout flMenu;
    @BindView(R.id.fl_content)
    FrameLayout flContent;
    private TestTask testTask;

    public static void GoToActivity(Context context, TestTask testTask) {
        Intent intent = new Intent(context, InspectionActivity.class);
        intent.putExtra("testTask", testTask);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // 隐藏ActionBar
        testTask = getIntent().getParcelableExtra("testTask");

        tvInspectionTemplate.setText(testTask.inspectionTemplate);
        etMaterialName.setText(testTask.materialName);
        etSupplierName.setText("供应商名称");
        etArrivalOrderNumber.setText(testTask.arrivalOrderNumber);
        etCount.setText(testTask.inspectionCount + "");

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager.beginTransaction().replace(R.id.fl_menu, InspectionItemFragment.newInstance()).commitAllowingStateLoss();
        supportFragmentManager.beginTransaction().replace(R.id.fl_content, InspectionContentFragment.newInstance()).commitAllowingStateLoss();
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_inspection;
    }
}
