package com.rlzz.uwinmes.ui;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.rlzz.uwinmes.R;
import com.rlzz.uwinmes.adapter.TestTaskAdapter;
import com.rlzz.uwinmes.common.base.BaseActivity;
import com.rlzz.uwinmes.entity.TestTask;
import com.rlzz.uwinmes.utils.DatePickerUtil;
import com.rlzz.uwinmes.widget.PanelListLayout;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.panelList)
    PanelListLayout panelList;
    private ArrayAdapter<String> mStatusAdapter;

    public static void GoToActivity(Context context) {
        context.startActivity(new Intent(context, TestTaskActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //检验状态适配器
        mStatusAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.test_status));
        TestTaskAdapter testTaskAdapter = new TestTaskAdapter(this, new ArrayList<TestTask>());


        panelList.setLineNumberBackgroundColor(Color.parseColor("#3a3e88"))
                .setLineNumberWidth(50)
                .setTableHeaderText("操作", "处理人", "到货日期", "到货单号", "行号", "物料编码", "物料名称", "规格型号", "质检方式", "质检数量", "AQL", "检验模板", "检验单号", "检验员")
                .setAdapter(testTaskAdapter);

        testTaskAdapter.notifyData(generateTestTaskList());
    }

    private List<TestTask> generateTestTaskList() {
        List<TestTask> testTasks = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            TestTask task = new TestTask();
            task.isEnterEnable = (i % 2 == 0);
            task.operator = "张三";
            task.arrivalDate = "2017-09-01";
            task.arrivalOrderNumber = "ARR20160802003";
            task.lineNumber = i + 1;
            task.materialNumber = "INV001";
            task.materialName = "高品质电容";
            task.specification = "10欧姆";
            task.inspectionMethod = "非破坏性抽检";
            task.inspectionCount = 1000;
            task.AQL = "AQL 0.015\n80（30,31）";
            task.inspectionTemplate = "电缆芯线完工检验方案";
            task.inspectionNumber = "JY20160802001";
            task.inspector = "李四";

            testTasks.add(task);

        }
        return testTasks;

    }


    @Override
    public void initView() {
        getSupportActionBar().hide(); // 隐藏ActionBar
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_test_task;
    }

    @OnClick(R.id.tv_startTime)
    public void onTvStartTimeClicked() {
        DatePickerUtil.showDatePickerDialog(this, tvStartTime.getText().toString().trim(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                tvStartTime.setText(DatePickerUtil.formatDate2String(year, month, dayOfMonth));
            }
        });
    }

    @OnClick(R.id.tv_endTime)
    public void onTvEndTimeClicked() {
        DatePickerUtil.showDatePickerDialog(this, tvEndTime.getText().toString().trim(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                tvEndTime.setText(DatePickerUtil.formatDate2String(year, month, dayOfMonth));
            }
        });
    }


    @OnClick(R.id.btn_search)
    public void onBtnSearchClicked() {
    }
}
