package com.rlzz.uwinmes.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.rlzz.uwinmes.R;
import com.rlzz.uwinmes.common.base.BaseActivity;
import com.rlzz.uwinmes.widget.dialog.DatePickerDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 检验任务
 */
public class TestTaskActivity extends BaseActivity {
    @BindView(R.id.tv_startTime)
    TextView tvStartTime;
    @BindView(R.id.tv_endTime)
    TextView tvEndTime;
    @BindView(R.id.et_invoiceNo)
    EditText etInvoiceNo;
    @BindView(R.id.et_productionNo)
    EditText etProductionNo;
    @BindView(R.id.et_material)
    EditText etMaterial;
    @BindView(R.id.et_operator)
    EditText etOperator;
    @BindView(R.id.sp_testStatus)
    Spinner spTestStatus;
    @BindView(R.id.btn_search)
    Button btnSearch;

    public static void GoToActivity(Context context) {
        context.startActivity(new Intent(context, TestTaskActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {

    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_test_task;
    }

    @OnClick(R.id.tv_startTime)
    public void onTvStartTimeClicked() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this);
        datePickerDialog.show();

    }

    @OnClick(R.id.tv_endTime)
    public void onTvEndTimeClicked() {
    }


    @OnClick(R.id.btn_search)
    public void onBtnSearchClicked() {
    }
}
