package com.rlzz.uwinmes.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rlzz.library.widget.LoadingView;
import com.rlzz.uwinmes.R;
import com.rlzz.uwinmes.adapter.InspectionItemAdapter;
import com.rlzz.uwinmes.entity.InspectionItem;
import com.rlzz.uwinmes.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * 检验项
 */
public class InspectionItemFragment extends Fragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.elv_inspectionItems)
    ExpandableListView elvInspectionItems;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.contentView)
    LinearLayout contentView;
    @BindView(R.id.loadingView)
    LoadingView loadingView;
    Unbinder unbinder;

    public static InspectionItemFragment newInstance() {
        InspectionItemFragment fragment = new InspectionItemFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inspectionitem, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        loadingView.setSize(50);
        loadingView.setColor(ContextCompat.getColor(getContext(), R.color.main_text_black));
        loadingView.start();

        initInspectionItemsList();
        loadingView.setVisibility(View.GONE);
    }

    private void initInspectionItemsList() {
        List<InspectionItem> inspectionItems = new ArrayList<>();
        inspectionItems.add(new InspectionItem("外观", new InspectionItem.Item("内导外观", "计数"), new InspectionItem.Item("绝缘外观", "计数")));
        inspectionItems.add(new InspectionItem("尺寸", new InspectionItem.Item("內导规格", "计量"), new InspectionItem.Item("內导线径", "计量")));
        InspectionItemAdapter adapter = new InspectionItemAdapter(getContext(), inspectionItems);
        adapter.setOnItemSelectedListener(new InspectionItemAdapter.OnItemSelectedListener() {
            @Override
            public void onItemSelected(View view, int groupPosition, int childPosition, InspectionItem.Item item) {
                ToastUtil.show(item.name+" ********************** "+groupPosition+" - "+childPosition);
            }
        });
        elvInspectionItems.setAdapter(adapter);
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            elvInspectionItems.expandGroup(i);
        }
        elvInspectionItems.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
//                return true; /*不可点击*/
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_save)
    public void onViewClicked() {
    }
}
