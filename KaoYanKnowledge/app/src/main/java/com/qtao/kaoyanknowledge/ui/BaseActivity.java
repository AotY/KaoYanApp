package com.qtao.kaoyanknowledge.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.achep.header2actionbar.FadingActionBarHelper;
import com.qtao.kaoyanknowledge.R;
import com.qtao.kaoyanknowledge.utils.L;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.lang.reflect.Field;

/**
 * Created by AotY on 2015/7/5.
 */
public class BaseActivity extends Activity {

    /**
     * Actionbar
     */
    private ActionBar actionBar;


    /**
     * getMyActionbar().getHeight()     * 用于让ActionBar出现渐变的效果
     */
    private FadingActionBarHelper mFadingActionBarHelper;

    private SystemBarTintManager tintManager;


    /**
     * 状态栏高度
     */
    public int statusHeight;

    /**
     * ActionBar高度
     */
    public int actionbarHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
        intiStatusBar();
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {

//            getWindow().setStatusBarColor(0x00000000);//windowTranslucentStatus
//            getWindow().setStatusBarColor();
//        }

        getStatusHeight();

//        statusHeight = getResources().getDimensionPixelSize(com.android.internal.R.dimen.status_bar_height);
    }

    private void getStatusHeight() {
        try {
            Class c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            statusHeight = getResources().getDimensionPixelSize(x);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private void intiStatusBar() {
        // 创建状态栏的管理实例
        tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarAlpha(0f);
        tintManager.setStatusBarTintResource(R.color.drak_primary_color_1);
//        tintManager.setTintAlpha(0f);
    }


    /**
     * 获取状态栏管理实例
     */
    public SystemBarTintManager getTintManager() {
        if (tintManager == null) {
            tintManager = new SystemBarTintManager(this);
        }
        return tintManager;
    }


    private void initActionBar() {
        actionBar = getActionBar();
        actionbarHeight = actionBar.getHeight();
        L.e("actionbarHeight ==" + actionbarHeight);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setHomeButtonEnabled(false);
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
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        actionBar.setTitle(title);
    }

    public void setTitle(int title) {
        actionBar.setTitle(title);
    }

    /**
     * 获取FadingActionBarHelper
     *
     * @return FadingActionBarHelper
     */
    public FadingActionBarHelper getFadingActionBarHelper() {
        return mFadingActionBarHelper;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
//
            Intent intent = new Intent(this, SettignActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
