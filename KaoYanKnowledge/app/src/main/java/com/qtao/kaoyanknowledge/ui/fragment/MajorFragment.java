package com.qtao.kaoyanknowledge.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.johnkil.print.PrintView;
import com.qtao.kaoyanknowledge.R;
import com.qtao.kaoyanknowledge.ui.BaseActivity;
import com.qtao.kaoyanknowledge.ui.MainActivity;

public class MajorFragment extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

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
        final PrintView iconView = (PrintView) getActivity().findViewById(R.id.icon);
    }
}