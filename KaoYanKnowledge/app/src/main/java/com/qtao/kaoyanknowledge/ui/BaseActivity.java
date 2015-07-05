package com.qtao.kaoyanknowledge.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

import com.achep.header2actionbar.FadingActionBarHelper;
import com.qtao.kaoyanknowledge.R;
import com.qtao.kaoyanknowledge.utils.L;

/**
 * Created by AotY on 2015/7/5.
 */
public class BaseActivity extends Activity {

    /**
     * Actionbar
     */
    private ActionBar actionBar;


    /**
     * 用于让ActionBar出现渐变的效果
     */
    private FadingActionBarHelper mFadingActionBarHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
    }

    private void initActionBar() {
        actionBar = getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(true);
//        actionBar.setHomeButtonEnabled(true);
//        actionBar.setDisplayShowTitleEnabled(false);
//        actionBar.setDisplayShowCustomEnabled(true);
        L.e("actionBar ==" + actionBar);
        //初始化ActionBar
        initHead2ActionBar();
    }


    /**
     * 初始化Head2ActionBar
     */
    private void initHead2ActionBar() {
        mFadingActionBarHelper = new FadingActionBarHelper(getMyActionbar(),
                getResources().getDrawable(R.drawable.actionbar_bg));
    }

    /**
     * 获取ActionBar
     *
     * @return ActionBar
     */
    public ActionBar getMyActionbar() {
        if (actionBar == null) {
            actionBar = getActionBar();
        }
        return actionBar;
    }

    /**
     * 获取FadingActionBarHelper
     * @return FadingActionBarHelper
     */
    public FadingActionBarHelper getFadingActionBarHelper() {
        return mFadingActionBarHelper;
    }

}
