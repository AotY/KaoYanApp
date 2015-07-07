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
import com.qtao.kaoyanknowledge.adapter.PoliticalAdapter;
import com.qtao.kaoyanknowledge.models.EngAndPolItem;
import com.qtao.kaoyanknowledge.ui.BaseActivity;
import com.qtao.kaoyanknowledge.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;

public class PoliticalFragment extends Fragment implements PoliticalAdapter.onRecyclerViewItemClickListener {

    /**
     *
     */
    private RecyclerView recyclerView;


    /**
     *
     */
    private PoliticalAdapter mAdapter;

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
        View contactsLayout = inflater.inflate(R.layout.political_layout,
                container, false);
        return contactsLayout;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = getActivity();
        ((BaseActivity) activity).getFadingActionBarHelper().setActionBarAlpha(255);
        ((MainActivity) activity).getTintManager().setStatusBarAlpha(255);
        ((BaseActivity) activity).setTitle(R.string.political);
        initView();
    }

    private void initView() {
//        actionbarHeight = ((BaseActivity) activity).getMyActionbar().getHeight();
        recyclerView = (RecyclerView) activity.findViewById(R.id.political_recycle_view);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) recyclerView.getLayoutParams();
        lp.topMargin = ((BaseActivity) activity).actionbarHeight + ((BaseActivity) activity).statusHeight+ 50;
        recyclerView.setLayoutParams(lp);

        recyclerView.setHasFixedSize(true);

        // Define 2 column grid layout
//        final GridLayoutManager layout = new GridLayoutManager(ContactsActivity.this, 2);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));

        // 设置item动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Unlike ListView, you have to explicitly give a LayoutManager to the RecyclerView to position items on the screen.
        // There are three LayoutManager provided at the moment: GridLayoutManager, StaggeredGridLayoutManager and LinearLayoutManager.
//        rvContacts.setLayoutManager(layout);

        // get data
        List<EngAndPolItem> contacts = getDatas();

        // Create an adapter
        mAdapter = new PoliticalAdapter(activity, contacts);
        mAdapter.setOnRecyclerViewItemClickListener(this);
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(mAdapter);
        alphaAdapter.setDuration(1000);
        alphaAdapter.setInterpolator(new OvershootInterpolator());
        recyclerView.setAdapter(new ScaleInAnimationAdapter(alphaAdapter));
    }

    private List<EngAndPolItem> getDatas() {
        List<EngAndPolItem> datas = new ArrayList<>();
        datas.add(new EngAndPolItem(1, "马哲", "马"));
        datas.add(new EngAndPolItem(2, "政经", "经"));
        datas.add(new EngAndPolItem(3, "毛概", "毛"));
        datas.add(new EngAndPolItem(4, "邓三", "三"));
        datas.add(new EngAndPolItem(5, " 资料推荐", "料"));
        datas.add(new EngAndPolItem(6, "复习技巧", "巧"));
        return datas;
    }

    /**
     * @param view
     */
    @Override
    public void onRecyclerViewItemClick(View view, EngAndPolItem engAndPolItem) {
        SuperToast.create(KaoYanApplication.appContext, "" + engAndPolItem.getName(), SuperToast.Duration.SHORT,
                Style.getStyle(Style.BLUE, SuperToast.Animations.SCALE)).show();
    }
}
