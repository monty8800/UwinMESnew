package com.rlzz.uwinmes.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.rlzz.uwinmes.R;
import com.rlzz.uwinmes.utils.DisplayUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by monty on 2017/8/31.
 */

public class PanelListLayout extends FrameLayout {
    private static final String TAG = PanelListLayout.class.getSimpleName();
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.lv_lineNumber)
    ListView lvLineNumber;
    @BindView(R.id.ll_tableHeader)
    LinearLayout llTableHeader;
    @BindView(R.id.lv_content)
    ListView lvContent;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.ll_lineNumber)
    LinearLayout llLineNumber;

    private Context mContext;
    private BaseAdapter mAdapter;

    /*说明：这里只可设置表头高度和行号宽度是因为表头的宽度和行号高度由ContentListView中的Item来决定，由调用者在ListView的Item布局中控制*/
    private int tableHeaderHeight = 30; // 表头高度
    private int lineNumberWidth = 30; // 行号宽度
    private int tableHeaderBackgroundColor = ContextCompat.getColor(getContext(), R.color.colorPrimary); // 表头背景颜色
    private int lineNumberBackgroundColor = 0x3a3e55; // 行号背景颜色
    private int lineNumberTextColor; // 行号文本颜色
    private int lineNumberTextSize; // 行号文本大小

    private String[] tableHeaderText;


    public PanelListLayout(Context context) {
        this(context, null);
    }

    public PanelListLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PanelListLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        inflate(getContext(), R.layout.panel_list_layout, this);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        initListScrollListener();
    }

    private void initListScrollListener() {
        OnScrollListener onScrollListener = new OnScrollListener();
        lvLineNumber.setOnScrollListener(onScrollListener);
        lvContent.setOnScrollListener(onScrollListener);
    }

    /**
     * 设置表头高度(单位：dp)
     *
     * @param height
     */
    public PanelListLayout setTableHeaderHeight(int height) {
        this.tableHeaderHeight = height;
        return this;
    }

    /**
     * 设置行号宽度(单位：dp)
     *
     * @param width
     */
    public PanelListLayout setLineNumberWidth(int width) {
        this.lineNumberWidth = width;
        return this;
    }

    /**
     * 设置表头高度(单位：dp)
     *
     * @param color
     */
    public PanelListLayout setTableHeaderBackgroundColor(int color) {
        this.tableHeaderBackgroundColor = DisplayUtil.dp2px(color);
        return this;
    }

    /**
     * 设置行号文本颜色
     *
     * @param color
     * @return
     */
    public PanelListLayout setLineNumberTextColor(int color) {
        this.lineNumberTextColor = color;
        return this;
    }

    /**
     * 设置行号文本颜色(单位：dp)
     *
     * @param textSize
     * @return
     */
    public PanelListLayout setLineNumberTextSize(int textSize) {
        this.lineNumberTextSize = DisplayUtil.dp2px(textSize);
        return this;
    }

    /**
     * 设置行号背景颜色
     *
     * @param color
     * @return
     */
    public PanelListLayout setLineNumberBackgroundColor(int color) {
        this.lineNumberBackgroundColor = color;
        return this;
    }

    /**
     * 设置表头文本
     *
     * @param text
     * @return
     */
    public PanelListLayout setTableHeaderText(String... text) {
        this.tableHeaderText = text;
        return this;
    }

    /**
     * 设置内容Adapter
     *
     * @param adapter
     */
    public void setAdapter(BaseAdapter adapter) {
        if (adapter == null) {
            throw new IllegalArgumentException("adapter is not allow to be null");
        }

        this.mAdapter = adapter;
        this.mAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                Log("DataSetObserver ******** onChanged");
                applyView();
            }

            @Override
            public void onInvalidated() {
                Log("DataSetObserver ******** onInvalidated");
                super.onInvalidated();
            }
        });


        applyView();
    }

    /**
     * 填充表格
     */
    private void applyView() {
        /*设置行号宽度*/
        ViewGroup.LayoutParams layoutParams = llLineNumber.getLayoutParams();
        layoutParams.width = lineNumberWidth;

        this.lvContent.setAdapter(mAdapter);
        this.lvContent.post(new Runnable() {
            @Override
            public void run() {
                Log("post******************************");
                View childItem = lvContent.getChildAt(lvContent.getFirstVisiblePosition());
                Log("post**********填充行号*************");
                applyLineNumberListView(childItem.getHeight());
                Log("post**********填充表头*************");
                applyTableHeader(childItem);
            }

            /**
             * 填充表头
             * @param childItem
             */
            private void applyTableHeader(View childItem) {
                llTableHeader.setBackgroundColor(tableHeaderBackgroundColor);
                llTableHeader.getLayoutParams().height = tableHeaderHeight;
                llTableHeader.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE | LinearLayout.SHOW_DIVIDER_BEGINNING);
                llTableHeader.setDividerDrawable(ContextCompat.getDrawable(getContext(), R.drawable.row_item_divider));
                tvTitle.getLayoutParams().height = llTableHeader.getLayoutParams().height;
                LinearLayout viewGroup = (LinearLayout) childItem;
                llTableHeader.removeAllViews();
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    View v = viewGroup.getChildAt(i);
                    TextView textView = new TextView(mContext);
                    textView.setLayoutParams(new LinearLayout.LayoutParams(v.getWidth(), ViewGroup.LayoutParams.MATCH_PARENT));
                    textView.setText(tableHeaderText[i]);
                    textView.setGravity(Gravity.CENTER);
                    llTableHeader.addView(textView);
                }
            }

            /**
             * 填充行号
             * @param lineNumberHeight
             */
            private void applyLineNumberListView(int lineNumberHeight) {
                lvLineNumber.setAdapter(new LineNumberAdapter(mAdapter.getCount(), lineNumberHeight));
            }

        });
    }

    private class LineNumberAdapter extends BaseAdapter {
        private int mLineNumberCount;
        private int mLineNumberHeight;
        private String[] mLineNumbers;

        public LineNumberAdapter(int lineNumberCount, int lineNumberHeight) {
            this.mLineNumberCount = lineNumberCount;
            this.mLineNumberHeight = lineNumberHeight;
            this.mLineNumbers = generateLineNumbers();
        }

        @Override
        public int getCount() {
            return mLineNumbers.length;
        }

        @Nullable
        @Override
        public Object getItem(int position) {
            return mLineNumbers[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view;
            if (convertView == null) {
                view = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, parent, false);
                view.getLayoutParams().height = this.mLineNumberHeight;
            } else {
                view = convertView;
            }
            View childAt = lvContent.getAdapter().getView(position, null, lvContent);
            if (childAt != null) { // 动态测量ContentListView中Item的高度，并将行号的高度与其保持一致
                childAt.measure(0, 0);
                Log("item " + position + " height -> " + childAt.getMeasuredHeight());
                view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, childAt.getMeasuredHeight()));
            }

            view.setBackgroundColor(lineNumberBackgroundColor);
            ((TextView) view).setText(this.mLineNumbers[position]);
            ((TextView) view).setTextSize(15);
            view.setPadding(0, 0, 0, 0);
            ((TextView) view).setGravity(Gravity.CENTER);
            return view;
        }

        private String[] generateLineNumbers() {
            String[] lineNumbers = new String[this.mLineNumberCount];
            for (int i = 0; i < this.mLineNumberCount; i++) {
                lineNumbers[i] = "" + (i + 1);
            }
            return lineNumbers;
        }

    }


    private void Log(String log) {
        Log.d(TAG, log);
    }

    public class OnScrollListener implements AbsListView.OnScrollListener {

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            Log("onScrollStateChanged - scrollState -> " + scrollState);
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            View subView = view.getChildAt(0);
            if (subView != null) {
                int top = subView.getTop();
                Log("onScrollStateChanged - onScroll - subView.top -> " + top);
                if (view == lvContent) {
                    lvLineNumber.setSelectionFromTop(firstVisibleItem, top);
                } else {
                    lvContent.setSelectionFromTop(firstVisibleItem, top);
                }
            }
        }
    }

}
