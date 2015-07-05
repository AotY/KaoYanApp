package com.qtao.kaoyanknowledge.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qtao.kaoyanknowledge.R;
import com.qtao.kaoyanknowledge.ui.MainActivity;

public class MajorFragment  extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).getFadingActionBarHelper().setActionBarAlpha(255);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View contactsLayout = inflater.inflate(R.layout.major_layout,
                container, false);
        return contactsLayout;
    }


}