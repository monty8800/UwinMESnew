package com.rlzz.uwinmes.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rlzz.uwinmes.R;
import com.rlzz.uwinmes.entity.TestTask;
import com.rlzz.uwinmes.ui.InspectionActivity;
import com.rlzz.uwinmes.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by monty on 2017/9/1.
 */

public class TestTaskAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private List<TestTask> testTasks;
    private LayoutInflater inflater;

    public TestTaskAdapter(Context context, List<TestTask> testTasks) {
        this.context = context;
        this.testTasks = testTasks;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return testTasks.size();
    }

    @Override
    public Object getItem(int position) {
        return testTasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.test_task_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        TestTask testTask = testTasks.get(position);

        setStyle(viewHolder, testTask);
        setOnClickListener(viewHolder,testTask);

        /*-----------setData start-----------*/
        viewHolder.tvEnterEnable.setText("录入");
        viewHolder.tvOperator.setText(testTask.operator);
        viewHolder.tvArrivalDate.setText(testTask.arrivalDate);
        viewHolder.tvArrivalOrderNumber.setText(testTask.arrivalOrderNumber);
        viewHolder.tvLineNumber.setText(testTask.lineNumber + "");
        viewHolder.tvMaterialNumber.setText(testTask.materialNumber);
        viewHolder.tvMaterialName.setText(testTask.materialName);
        viewHolder.tvSpecification.setText(testTask.specification);
        viewHolder.tvInspectionMethod.setText(testTask.inspectionMethod);
        viewHolder.tvInspectionCount.setText(testTask.inspectionCount + "");
        viewHolder.tvAQL.setText(testTask.AQL);
        viewHolder.tvInspectionTemplate.setText(testTask.inspectionTemplate);
        viewHolder.tvInspectionNumber.setText(testTask.inspectionNumber);
        viewHolder.tvInspector.setText(testTask.inspector);
        /*-----------setData ent-----------*/
        return convertView;
    }

    private void setOnClickListener(ViewHolder viewHolder, TestTask testTask) {
        viewHolder.tvEnterEnable.setTag(testTask);
        viewHolder.tvEnterEnable.setOnClickListener(this);
        viewHolder.tvInspectionNumber.setOnClickListener(this);

    }

    private void setStyle(ViewHolder viewHolder, TestTask testTask) {
        int textColor = testTask.isEnterEnable ? R.color.blue : R.color.gray;
        viewHolder.tvEnterEnable.setTextColor(ContextCompat.getColor(context, textColor));
        viewHolder.tvInspectionNumber.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰
    }

    public void notifyData(List<TestTask> testTasks) {
        this.testTasks = testTasks;
        this.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        TestTask task = (TestTask) v.getTag();
        switch (v.getId()){
            case R.id.tv_enterEnable:
                ToastUtil.show("position -> " + task.lineNumber);
                break;
            case R.id.tv_inspectionNumber:
                InspectionActivity.GoToActivity(context,task);
                break;
        }
    }

    static class ViewHolder {
        @BindView(R.id.tv_enterEnable)
        TextView tvEnterEnable;
        @BindView(R.id.tv_operator)
        TextView tvOperator;
        @BindView(R.id.tv_arrivalDate)
        TextView tvArrivalDate;
        @BindView(R.id.tv_arrivalOrderNumber)
        TextView tvArrivalOrderNumber;
        @BindView(R.id.tv_lineNumber)
        TextView tvLineNumber;
        @BindView(R.id.tv_materialNumber)
        TextView tvMaterialNumber;
        @BindView(R.id.tv_materialName)
        TextView tvMaterialName;
        @BindView(R.id.tv_specification)
        TextView tvSpecification;
        @BindView(R.id.tv_inspectionMethod)
        TextView tvInspectionMethod;
        @BindView(R.id.tv_inspectionCount)
        TextView tvInspectionCount;
        @BindView(R.id.tv_AQL)
        TextView tvAQL;
        @BindView(R.id.tv_inspectionTemplate)
        TextView tvInspectionTemplate;
        @BindView(R.id.tv_inspectionNumber)
        TextView tvInspectionNumber;
        @BindView(R.id.tv_inspector)
        TextView tvInspector;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
