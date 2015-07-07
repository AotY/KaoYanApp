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
import com.qtao.kaoyanknowledge.utils.L;
import com.qtao.kaoyanknowledge.utils.UserInfo;

/**
 * 专业页面，如果用户是第一次进入，则让用户选择专业
 */
public class MajorFragment extends Fragment {

    /**
     * 用户专业
     */
    private String major;

    /**
     * @param activity
     */
    private Activity activity;

    public static final int REQUESTCODE = 0x0001;

    public MajorFragment() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //|| UserInfo.getUserChooseEnterMajor(activity)
        if (UserInfo.getUserMajor(activity) == null ) {
            //跳到选择专业页面
            Intent chooseIntent = new Intent(activity, ChooseMajorActivity.class);
            //如果是调用Fragment中的startActivityForResult方法，返回到Fragment中处理
            //如果是调用getActivity()的startActivityForResult方法，返回到activity中
            getActivity().startActivityForResult(chooseIntent, REQUESTCODE);
            return null;
        }
        return inflater.inflate(R.layout.major_layout,
                container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = getActivity();
        ((BaseActivity) activity).getFadingActionBarHelper().setActionBarAlpha(255);
        ((MainActivity) activity).getTintManager().setStatusBarAlpha(255);
        ((BaseActivity) activity).setTitle(R.string.major);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        L.e("ma onActivityResult");
    }

}