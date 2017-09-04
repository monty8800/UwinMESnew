package com.rlzz.uwinmes.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.rlzz.uwinmes.R;
import com.rlzz.uwinmes.utils.LogUtil;

/**
 * Created by monty on 2017/8/24.
 */

public class RefreshLayout extends SwipeRefreshLayout implements AbsListView.OnScrollListener {
    private ListView mListView;
    /**
     * 按下时的y坐标
     */
    private int mYDown;
    /**
     * 移动时的y坐标, 与mYDown一起用于滑动到底部时判断是上拉还是下拉
     */
    private int mLastY;
    /**
     * 是否在加载中 ( 上拉加载更多 )
     */
    private boolean isLoading = false;

    /**
     * 滑动到最下面时的上拉操作
     */

    private int mTouchSlop;
    /**
     * 上拉监听器, 到了最底部的上拉加载操作
     */
    private OnLoadListener mOnLoadListener;

    /**
     * ListView的加载中footer
     */
    private View mListViewFooter;

    public RefreshLayout(Context context) {
        super(context);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LogUtil.d("onFinishInflate~~~~~~~~~~~~~~~~");
        findListView(this);

        if (mListView == null) {
            throw new RuntimeException("You must have to add a ListView");
        }
        /*监听ListView的滑动事件，避免RefreshLayout与ListView滑动时间冲突*/
//        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                boolean enable = false;
//                LogUtil.d("**************************________________________");
//                if (view != null && view.getChildCount() > 0) {
//                    boolean firstItemVisible = view.getFirstVisiblePosition() == 0;
//                    boolean topOfFirstItemVisible = view.getChildAt(0).getTop() == 0;
//                    LogUtil.d("refreshLayout - firstItemVisible -> %1$s ----- topOfFirstItemVisible -> %2$s",firstItemVisible,topOfFirstItemVisible);
//                    enable = firstItemVisible && topOfFirstItemVisible;
//                }
//                setEnabled(enable);
//            }
//        });
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        mListViewFooter = inflate(getContext(), R.layout.loadmore_footer, null);
    }

    @Override
    public boolean canChildScrollUp() {
        return super.canChildScrollUp();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // 按下
                mYDown = (int) event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                // 移动
                mLastY = (int) event.getRawY();
                // 抬起
                if (canLoad()) {
                    loadData();
                }
                break;

            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        if (isLoading) {
            return;
        }
        super.setRefreshing(refreshing);
    }

    /**
     * 如果到了最底部,而且是上拉操作.那么执行onLoad方法
     */
    private void loadData() {
        if (mOnLoadListener != null) {
            // 设置是否正在上拉加载状态
            setLoading(true);
            //
            mOnLoadListener.onLoading();
        }
    }

    /**
     * 是否可以加载更多, 条件是到了最底部, listview不在加载中, 且为上拉操作.
     *
     * @return
     */
    private boolean canLoad() {
        return isBottom() && !isLoading && isPullUp();
    }

    /**
     * 是否滑动到了ListView的最下方
     *
     * @return
     */
    private boolean isBottom() {
        if (mListView.getAdapter() == null) {
            throw new RuntimeException("You must add an Adapter for the ListView");
        }
        return mListView.getLastVisiblePosition() == mListView.getAdapter().getCount() - 1;
    }

    /**
     * 是否是上拉操作
     *
     * @return
     */
    private boolean isPullUp() {
        return (mYDown - mLastY) >= mTouchSlop;
    }

    /**
     * @param loading
     */
    public void setLoading(boolean loading) {
        if (isRefreshing()) {
            return;
        }
        isLoading = loading;
        if (isLoading) {
            mListView.addFooterView(mListViewFooter);

        } else {
            mListView.removeFooterView(mListViewFooter);
            mYDown = 0;
            mLastY = 0;
        }
    }


    private void findListView(View view) {
        LogUtil.d("getListView ********* " + view.getClass().getName());

        if (view instanceof ViewGroup) {
            if (view instanceof ListView) {
                mListView = (ListView) view;
                LogUtil.d("getListView - ---------- " + (mListView == null));
                return;
            } else {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    View v = viewGroup.getChildAt(i);
                    findListView(v);
                }
            }
        }
    }

    /**
     * 设置上拉加载监听
     *
     * @param loadListener
     */
    public void setOnLoadListener(OnLoadListener loadListener) {
        mOnLoadListener = loadListener;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                         int totalItemCount) {
        // 滚动时到了最底部也可以加载更多
        if (canLoad()) {
            loadData();
        }
    }

    /**
     * 加载更多的监听器
     *
     * @author mrsimple
     */
    public interface OnLoadListener {
        void onLoading();
    }


}
