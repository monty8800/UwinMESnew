package com.rlzz.uwinmes.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
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

import java.util.Arrays;
import java.util.List;

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
    RefreshLayout swipeRefreshLayout;
    @BindView(R.id.ll_lineNumber)
    LinearLayout llLineNumber;

    private Context mContext;
//    private BaseAdapter mAdapter;

    /*说明：这里只可设置表头高度和行号宽度是因为表头的宽度和行号高度由ContentListView中的Item来决定，由调用者在ListView的Item布局中控制*/
    private int tableHeaderHeight = 30; // 表头高度
    private int lineNumberWidth = 30; // 行号宽度

    private int tableHeaderBackgroundColor = 0xff00bcd4; // 表头背景颜色
    private int tableHeaderTextColor = 0xffffffff; // 表头文本颜色
    private int tableHeaderTextSize = 14; // 表头文本大小

    private int lineNumberBackgroundColor = 0xff00bcd4; // 行号背景颜色
    private int lineNumberTextColor = 0xffffffff; // 行号文本颜色
    private int lineNumberTextSize = 14; // 行号文本大小

    private List<String> tableHeaderTexts;


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
        /* 去掉ListView滑动到头部或底部时的动画 */
        lvLineNumber.setOverScrollMode(View.OVER_SCROLL_NEVER);
        lvContent.setOverScrollMode(View.OVER_SCROLL_NEVER);

        OnScrollListener onScrollListener = new OnScrollListener();
        lvLineNumber.setOnScrollListener(onScrollListener);
        lvContent.setOnScrollListener(onScrollListener);
        /**
         * 解决SwipeRefreshLayout中多层嵌套ListView下拉事件冲突问题
         */
        swipeRefreshLayout.setOnChildScrollUpCallback(new SwipeRefreshLayout.OnChildScrollUpCallback() {
            @Override
            public boolean canChildScrollUp(SwipeRefreshLayout parent, @Nullable View child) {
                return ViewCompat.canScrollVertically(lvContent, -1);
            }
        });
    }

    public RefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
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
     * 设置表头背景颜色
     *
     * @param color
     */
    public PanelListLayout setTableHeaderBackgroundColor(int color) {
        this.tableHeaderBackgroundColor = color;
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
    public PanelListLayout setTableHeaderTexts(String... text) {
        this.tableHeaderTexts = Arrays.asList(text);
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

//        this.mAdapter = adapter;
        this.lvContent.setAdapter(adapter);
        /****************************************
         * 监听Adapter，当ListView中的内容发生改变时重新渲染(主要作用，第一次设置Adapter之后，没有数据的情况下，
         * 从网络获取数据之后刷新Adapter之后需要重新绘制表头)
         ****************************************/
        adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                Log("DataSetObserver ******** onChanged");
                applyView();
            }

            @Override
            public void onInvalidated() {
                Log("DataSetObserver ******** onInvalidated");
                super.onInvalidated();
                applyView();
            }
        });

        applyView();
    }

    /**
     * ************************************核心代码**********************************
     * <p>
     * 填充表格
     */
    private void applyView() {
        /*设置行号宽度*/
        ViewGroup.LayoutParams layoutParams = llLineNumber.getLayoutParams();
        layoutParams.width = lineNumberWidth;

        this.lvContent.post(new Runnable() {
            @Override
            public void run() {
                Log("post******************************");
                View childItem = lvContent.getChildAt(lvContent.getFirstVisiblePosition());
                Log("post**********填充行号*************");
                applyLineNumberListView();
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
                LinearLayout contentItem = (LinearLayout) childItem;
                llTableHeader.removeAllViews();

                for (int i = 0; i < contentItem.getChildCount(); i++) {
                    View v = contentItem.getChildAt(i);
                    TextView textView = new TextView(mContext);
                    //根据ContentListView的Item中列的宽度来动态设置表头宽度
                    textView.setLayoutParams(new LinearLayout.LayoutParams(v.getWidth(), ViewGroup.LayoutParams.MATCH_PARENT));
                    textView.setText(tableHeaderTexts.get(i));
                    textView.setTextColor(tableHeaderTextColor);
                    textView.setTextSize(tableHeaderTextSize);
                    textView.setGravity(Gravity.CENTER);
                    llTableHeader.addView(textView);
                }
            }

            /**
             * 填充行号
             */
            private void applyLineNumberListView() {
                lvLineNumber.setAdapter(new LineNumberAdapter(lvContent.getCount()));
            }

        });
    }

    private class LineNumberAdapter extends BaseAdapter {
        private int mLineNumberCount;
        private String[] mLineNumbers;

        public LineNumberAdapter(int lineNumberCount) {
            this.mLineNumberCount = lineNumberCount;
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
            } else {
                view = convertView;
            }
            View contentItemView = lvContent.getAdapter().getView(position, null, lvContent);
            if (contentItemView != null) { // 动态测量ContentListView中Item的高度，并将行号的高度与其保持一致
                contentItemView.measure(0, 0);
                view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, contentItemView.getMeasuredHeight()));
            }
            TextView textView = (TextView) view;
            textView.setBackgroundColor(lineNumberBackgroundColor);
            textView.setText(this.mLineNumbers[position]);
            textView.setTextColor(lineNumberTextColor);
            textView.setTextSize(lineNumberTextSize);
            textView.setPadding(0, 0, 0, 0);
            textView.setGravity(Gravity.CENTER);
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
        private int scrollState;

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            Log("onScrollStateChanged - scrollState -> " + scrollState);
            this.scrollState = scrollState;
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            /*********************************************
             * 解决无限同步LineNumberListView和ContentListView滚动位置的问题，SCROLL_STATE_IDLE是ListView滚动结束时的标记，
             * 此处判断如果滚动结束了就不再进行同步，避免因为一直处于滚动状态而导致ListView的getView会一直调用的问题。
             *********************************************/
            if (scrollState == SCROLL_STATE_IDLE) {
                return;
            }
            View subView = view.getChildAt(0);
            if (subView != null) {
                int top = subView.getTop();
                if (view == lvContent) {
                    lvLineNumber.setSelectionFromTop(firstVisibleItem, top);
                } else {
                    lvContent.setSelectionFromTop(firstVisibleItem, top);
                }
            }
        }
    }

}
