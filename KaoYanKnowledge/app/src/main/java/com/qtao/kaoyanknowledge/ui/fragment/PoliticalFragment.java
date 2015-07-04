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
import com.qtao.kaoyanknowledge.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class PoliticalFragment extends Fragment {


    /**
     *
     */
    private RecyclerView recyclerView;

    /**
     *
     */
    private Activity activity;

    /**
     *
     */
    private EnglishAdapter mAdapter;

    private int actionbarHeight ;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
        ((MainActivity) activity).getFadingActionBarHelper().setActionBarAlpha(255);
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
        initView();
    }

    private void initView() {
        actionbarHeight = activity.getActionBar().getHeight() ;

        recyclerView = (RecyclerView) activity.findViewById(R.id.political_recycle_view);
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
        mAdapter = new EnglishAdapter(activity, contacts);

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
