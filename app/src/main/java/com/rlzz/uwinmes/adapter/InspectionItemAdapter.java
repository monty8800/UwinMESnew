package com.rlzz.uwinmes.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.rlzz.uwinmes.R;
import com.rlzz.uwinmes.entity.InspectionItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by monty on 2017/9/5.
 */

public class InspectionItemAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<InspectionItem> mInspectionItems;
    private LayoutInflater mInflater;

    private OnItemSelectedListener mOnItemSelectedListener;

    public InspectionItemAdapter(Context context, List<InspectionItem> inspectionItems) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mInspectionItems = inspectionItems;
    }

    @Override
    public int getGroupCount() {
        return mInspectionItems.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mInspectionItems.get(groupPosition).inspectionItems.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mInspectionItems.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mInspectionItems.get(groupPosition).inspectionItems.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.layout_inspectionitem_group, parent, false);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        groupViewHolder.tvInspectionType.setText(mInspectionItems.get(groupPosition).inspectionType);

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.layout_inspectionitem_child, parent, false);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        final InspectionItem.Item item = mInspectionItems.get(groupPosition).inspectionItems.get(childPosition);

        childViewHolder.tvName.setText(item.name);
        childViewHolder.tvUnitType.setText(item.unitStr);

        if (item.checked) {
            childViewHolder.itemView.setBackgroundColor(Color.parseColor("#00668c"));
        } else {
            childViewHolder.itemView.setBackgroundColor(Color.parseColor("#3aaed7"));
        }

        final ChildViewHolder finalChildViewHolder = childViewHolder;
        childViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetCheckedStatus();
                item.checked = !item.checked;
                if (item.checked) { //  选中
                    mOnItemSelectedListener.onItemSelected(finalChildViewHolder.itemView, groupPosition, childPosition, item);
                }
                notifyDataSetChanged();
            }
        });
        return convertView;
    }


    /**
     * 重置选中状态
     */
    private void resetCheckedStatus() {
        for (int i = 0; i < mInspectionItems.size(); i++) {
            InspectionItem inspectionItem = mInspectionItems.get(i);
            for (int j = 0; j < inspectionItem.inspectionItems.size(); j++) {
                InspectionItem.Item item = inspectionItem.inspectionItems.get(j);
                item.checked = false;
            }
        }
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class GroupViewHolder {
        @BindView(R.id.tv_inspectionType)
        TextView tvInspectionType;

        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ChildViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_unitType)
        TextView tvUnitType;

        View itemView;

        ChildViewHolder(View view) {
            itemView = view;
            ButterKnife.bind(this, view);
        }
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.mOnItemSelectedListener = onItemSelectedListener;
    }

    public interface OnItemSelectedListener {
        void onItemSelected(View view, int groupPosition, int childPosition, InspectionItem.Item item);
    }
}
