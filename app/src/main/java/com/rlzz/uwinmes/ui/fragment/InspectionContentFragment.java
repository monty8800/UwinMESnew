package com.rlzz.uwinmes.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rlzz.uwinmes.R;
import com.rlzz.uwinmes.widget.LoadingView;


/**
 * 检验内容
 */
public class InspectionContentFragment extends Fragment {
    public static InspectionContentFragment newInstance() {
        InspectionContentFragment fragment = new InspectionContentFragment();
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

    LoadingView loadingview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inspection_content, container, false);
//        loadingview = (LoadingView) view.findViewById(R.id.loadingView);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        /*loadingview.setSize(50);
        loadingview.setColor(ContextCompat.getColor(getContext(), R.color.main_text_black));
        loadingview.start();*/
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
