package com.qtao.kaoyanknowledge.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qtao.kaoyanknowledge.R;
import com.qtao.kaoyanknowledge.ui.BaseActivity;
import com.qtao.kaoyanknowledge.ui.ChooseMajorActivity;
import com.qtao.kaoyanknowledge.ui.MainActivity;
import com.qtao.kaoyanknowledge.utils.UserInfo;

/**
 * 专业页面，如果用户是第一次进入，则让用户选择专业
 */
public class MajorFragment extends Fragment {

    /**
     * 用户专业
     */
    private String major ;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        major = UserInfo.getUserMajor(activity);
        if(major == null){
            //跳到选择专业页面
            Intent chooseIntent = new Intent(activity , ChooseMajorActivity.class);
            startActivity(chooseIntent);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.major_layout,
                container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((BaseActivity) getActivity()).getFadingActionBarHelper().setActionBarAlpha(255);
        ((MainActivity) getActivity()).getTintManager().setStatusBarAlpha(255);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}