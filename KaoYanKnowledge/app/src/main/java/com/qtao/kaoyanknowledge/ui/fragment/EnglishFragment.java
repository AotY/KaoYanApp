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
import android.widget.RelativeLayout;

import com.qtao.kaoyanknowledge.R;
import com.qtao.kaoyanknowledge.adapter.EnglishAdapter;
import com.qtao.kaoyanknowledge.models.EnglishItem;
import com.qtao.kaoyanknowledge.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * onAttach方法：Fragment和Activity建立关联的时候调用。
 * onCreateView方法：为Fragment加载布局时调用。
 * onActivityCreated方法：当Activity中的onCreate方法执行完后调用。
 * onDestroyView方法：Fragment中的布局被移除时调用。
 * onDetach方法：Fragment和Activity解除关联的时候调用。
 */
public class EnglishFragment extends Fragment {

    /**
     *
     */
    private RecyclerView recyclerView;

    /**
     *
     */
    private EnglishAdapter mAdapter;

    private int actionbarHeight ;

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
        ((BaseActivity) getActivity()).getFadingActionBarHelper().setActionBarAlpha(255);
        initView();
    }

    private void initView() {
        actionbarHeight = ((BaseActivity) getActivity()).getMyActionbar().getHeight() ;

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.english_recycle_view);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) recyclerView.getLayoutParams();
        lp.topMargin = actionbarHeight;
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
//        rvContacts.setLayoutManager(layout);

        // get data
        List<EnglishItem> contacts = getContacts();

        // Create an adapter
        mAdapter = new EnglishAdapter(getActivity(), contacts);

        // Bind adapter to list
        recyclerView.setAdapter(mAdapter);
    }
    private List<EnglishItem> getContacts() {
        List<EnglishItem> contacts = new ArrayList<>();
        contacts.add(new EnglishItem(1, "Adam", R.drawable.photo, "4153508881"));
        contacts.add(new EnglishItem(2, "Sarah", R.drawable.photo, "4153508882"));
        contacts.add(new EnglishItem(3, "Bob", R.drawable.photo, "4153508883"));
        contacts.add(new EnglishItem(4, "John", R.drawable.photo, "4153508884"));
        contacts.add(new EnglishItem(5, "Jill", R.drawable.photo, "4153508885"));
        return contacts;
    }


}