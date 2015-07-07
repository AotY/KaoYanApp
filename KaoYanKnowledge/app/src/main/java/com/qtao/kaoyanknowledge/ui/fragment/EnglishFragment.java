package com.qtao.kaoyanknowledge.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.RelativeLayout;

import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.util.Style;
import com.qtao.kaoyanknowledge.App.KaoYanApplication;
import com.qtao.kaoyanknowledge.R;
import com.qtao.kaoyanknowledge.adapter.EnglishAdapter;
import com.qtao.kaoyanknowledge.models.EngAndPolItem;
import com.qtao.kaoyanknowledge.ui.BaseActivity;
import com.qtao.kaoyanknowledge.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;

/**
 * onAttach方法：Fragment和Activity建立关联的时候调用。
 * onCreateView方法：为Fragment加载布局时调用。
 * onActivityCreated方法：当Activity中的onCreate方法执行完后调用。
 * onDestroyView方法：Fragment中的布局被移除时调用。
 * onDetach方法：Fragment和Activity解除关联的时候调用。
 */
public class EnglishFragment extends Fragment implements EnglishAdapter.onRecyclerViewItemClickListener {

    /**
     * RecyclerView View
     */
    private RecyclerView recyclerView;

    /**
     *
     */
    private EnglishAdapter mAdapter;


    /**
     * @param activity
     */
    private Activity activity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.english_layout,
                container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = getActivity();
        ((MainActivity) activity).getTintManager().setStatusBarAlpha(255);
        ((BaseActivity) activity).getFadingActionBarHelper().setActionBarAlpha(255);
        ((BaseActivity) activity).setTitle(R.string.english);
        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) activity.findViewById(R.id.english_recycle_view);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) recyclerView.getLayoutParams();
        lp.topMargin = ((BaseActivity) activity).actionbarHeight + ((BaseActivity) activity).statusHeight + 50;
        recyclerView.setLayoutParams(lp);

        // allows for optimizations
        recyclerView.setHasFixedSize(true);

        // Define 2 column grid layout
//        final GridLayoutManager layout = new GridLayoutManager(ContactsActivity.this, 2);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));

        // 设置item动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Unlike ListView, you have to explicitly give a LayoutManager to the RecyclerView to position items on the screen.
        // There are three LayoutManager provided at the moment: GridLayoutManager, StaggeredGridLayoutManager and LinearLayoutManager.

        // get data
        List<EngAndPolItem> contacts = getDatas();

        // Create an adapter
        mAdapter = new EnglishAdapter(activity, contacts);
        mAdapter.setOnRecyclerViewItemClickListener(this);
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(mAdapter);
        alphaAdapter.setDuration(1000);
        alphaAdapter.setInterpolator(new OvershootInterpolator());
        recyclerView.setAdapter(new ScaleInAnimationAdapter(alphaAdapter));
    }

    private List<EngAndPolItem> getDatas() {
        List<EngAndPolItem> datas = new ArrayList<>();
        datas.add(new EngAndPolItem(1, "英语一", "一"));
        datas.add(new EngAndPolItem(2, "英语二", "二"));
        datas.add(new EngAndPolItem(3, "高频单词", "单"));
        datas.add(new EngAndPolItem(4, "高频句型", "句"));
        datas.add(new EngAndPolItem(5, "作文模板", "作"));
        datas.add(new EngAndPolItem(6, " 资料推荐", "料"));
        datas.add(new EngAndPolItem(5, "复习技巧", "巧"));
        return datas;
    }


    /**
     * 点击item
     *
     * @param view
     */
    @Override
    public void onRecyclerViewItemClick(View view, EngAndPolItem engAndPolItem) {
        SuperToast.create(KaoYanApplication.appContext, "" + engAndPolItem.getName(), SuperToast.Duration.SHORT,
                Style.getStyle(Style.BLUE, SuperToast.Animations.SCALE)).show();
    }
}