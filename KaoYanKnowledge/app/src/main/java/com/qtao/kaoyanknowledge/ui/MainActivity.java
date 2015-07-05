package com.qtao.kaoyanknowledge.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.achep.header2actionbar.FadingActionBarHelper;
import com.qtao.kaoyanknowledge.R;
import com.qtao.kaoyanknowledge.ui.fragment.EnglishFragment;
import com.qtao.kaoyanknowledge.ui.fragment.MajorFragment;
import com.qtao.kaoyanknowledge.ui.fragment.MathFragment;
import com.qtao.kaoyanknowledge.ui.fragment.PoliticalFragment;


public class MainActivity extends Activity implements View.OnClickListener {

    /**
     * 用于展示数学的Fragment
     */
//    private TestFragment mathFragment;
//    private ListViewFragment mathFragment;
    private MathFragment mathFragment;
    /**
     * 用于展示英语的Fragment
     */
    private EnglishFragment englishFragment;

    /**
     * 用于展示政治的Fragment
     * political
     */
    private PoliticalFragment politicalFragment;

    /**
     * 用于展示专业的Fragment
     */
    private MajorFragment majorFragment;

    /**
     * 数学界面布局
     */
    private View mathLayout;

    /**
     * 英语界面布局
     */
    private View englishLayout;

    /**
     * 政治界面布局
     */
    private View politicalLayout;

    /**
     * 专业界面布局
     */
    private View majorLayout;

    /**
     * 在Tab布局上显示数学图标的控件
     */
    private ImageView mathImage;

    /**
     * 在Tab布局上显示英语图标的控件
     */
    private ImageView englishImage;

    /**
     * 在Tab布局上显示政治图标的控件
     */
    private ImageView politicalImage;

    /**
     * 在Tab布局上显示专业图标的控件
     */
    private ImageView majorImage;


    /**
     * 在Tab布局上数学消息标题的控件
     */
    private TextView mathText;

    /**
     * 在Tab布局上显示英语标题的控件
     */
    private TextView englishText;

    /**
     * 在Tab布局上显示政治标题的控件
     */
    private TextView politicalText;

    /**
     * 在Tab布局上显示专业标题的控件
     */
    private TextView majorText;

    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager;

    /**
     * Actionbar
     */
    private ActionBar actionBar;

    /**
     * 文字选中的颜色
     */
    private String textFocusColor = "#00BCD4"; //# 009688

    /**
     * 文字没有被选中的颜色
     */
    private String textCleanColor = "#212121";

    /**
     * 用于让ActionBar出现渐变的效果
     */
    private FadingActionBarHelper mFadingActionBarHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //初始化ActionBar
        initHead2ActionBar();
        // 初始化布局元素
        initViews();
        fragmentManager = getFragmentManager();
        // 第一次启动时选中第0个tab
        setTabSelection(0);
    }

    private void initHead2ActionBar() {
        actionBar = getActionBar();
        Log.i("TAG", "actionBar ==" + actionBar);
        mFadingActionBarHelper = new FadingActionBarHelper(actionBar,
                getResources().getDrawable(R.drawable.actionbar_bg));
    }

    /**
     * 获取ActionBar
     * @return
     */
    public ActionBar getMyActionbar(){
        if(actionBar == null){
            actionBar = getActionBar() ;
        }
        return actionBar ;
    }

    /**
     * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
     */
    private void initViews() {
        mathLayout = findViewById(R.id.math_layout);
        englishLayout = findViewById(R.id.english_layout);
        politicalLayout = findViewById(R.id.political_layout);
        majorLayout = findViewById(R.id.major_layout);
        mathImage = (ImageView) findViewById(R.id.math_image);
        englishImage = (ImageView) findViewById(R.id.english_image);
        politicalImage = (ImageView) findViewById(R.id.political_image);
        majorImage = (ImageView) findViewById(R.id.major_image);
        mathText = (TextView) findViewById(R.id.math_text);
        englishText = (TextView) findViewById(R.id.english_text);
        politicalText = (TextView) findViewById(R.id.political_text);
        majorText = (TextView) findViewById(R.id.major_text);
        mathLayout.setOnClickListener(this);
        englishLayout.setOnClickListener(this);
        politicalLayout.setOnClickListener(this);
        majorLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.math_layout:
                // 当点击了数学tab时，选中第1个tab
                setTabSelection(0);
                break;
            case R.id.english_layout:
                // 当点击了英语tab时，选中第2个tab
                setTabSelection(1);
                break;
            case R.id.political_layout:
                // 当点击了政治tab时，选中第3个tab
                setTabSelection(2);
                break;
            case R.id.major_layout:
                // 当点击了专业tab时，选中第4个tab
                setTabSelection(3);
                break;
            default:
                break;
        }
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index 每个tab页对应的下标。0表示消息，1表示联系人，2表示动态，3表示设置。
     */
    private void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
//        hideFragments(transaction);
        switch (index) {
            case 0:
                // 当点击了消息tab时，改变控件的图片和文字颜色
                mathImage.setImageResource(R.drawable.ic_math_primary_1_48dp);
                mathText.setTextColor(Color.parseColor(textFocusColor));
                if (mathFragment == null) {
//                    mathFragment = new TestFragment();
                    mathFragment = new MathFragment();
//                    mathFragment = new ListViewFragment();
//                    transaction.add(R.id.content, listViewFragment);
                } else {
//                    transaction.show(messageFragment);
                }
                transaction.replace(R.id.content, mathFragment);
                break;
            case 1:
                // 当点击了联系人tab时，改变控件的图片和文字颜色
                englishImage.setImageResource(R.drawable.ic_english_primary_1_48dp);
                englishText.setTextColor(Color.parseColor(textFocusColor));
                if (englishFragment == null) {
//                    // 如果ContactsFragment为空，则创建一个并添加到界面上
                    englishFragment = new EnglishFragment();
//                    transaction.add(R.id.content, contactsFragment);
                } else {
//                    // 如果ContactsFragment不为空，则直接将它显示出来
//                    transaction.show(contactsFragment);
                }
                transaction.replace(R.id.content, englishFragment);
                break;
            case 2:
                // 当点击了动态tab时，改变控件的图片和文字颜色
                politicalImage.setImageResource(R.drawable.ic_political_primary_1_48dp);
                politicalText.setTextColor(Color.parseColor(textFocusColor));
                if (politicalFragment == null) {
                    // 如果NewsFragment为空，则创建一个并添加到界面上
                    politicalFragment = new PoliticalFragment();
//                    transaction.add(R.id.content, newsFragment);
                } else {
                    // 如果NewsFragment不为空，则直接将它显示出来
//                    transaction.show(newsFragment);
                }
                transaction.replace(R.id.content, politicalFragment);
                break;
            case 3:
            default:
                // 当点击了设置tab时，改变控件的图片和文字颜色
                majorImage.setImageResource(R.drawable.ic_major_primary_1_48dp);
                majorText.setTextColor(Color.parseColor(textFocusColor));
                if (majorFragment == null) {
                    // 如果SettingFragment为空，则创建一个并添加到界面上
                    majorFragment = new MajorFragment();
//                    transaction.add(R.id.content, settingFragment);
                } else {
                    // 如果SettingFragment不为空，则直接将它显示出来
//                    transaction.show(settingFragment);
                }
                transaction.replace(R.id.content, majorFragment);
                break;
        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        mathImage.setImageResource(R.drawable.ic_math_gray_400_48dp);
        mathText.setTextColor(Color.parseColor(textCleanColor));

        englishImage.setImageResource(R.drawable.ic_english_gray_400_48dp);
        englishText.setTextColor(Color.parseColor(textCleanColor));

        politicalImage.setImageResource(R.drawable.ic_political_gray_400_48dp);
        politicalText.setTextColor(Color.parseColor(textCleanColor));


        majorImage.setImageResource(R.drawable.ic_major_gray_400_48dp);
        majorText.setTextColor(Color.parseColor(textCleanColor));
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {

        if (mathFragment != null) {
            transaction.hide(mathFragment);
        }
        if (englishFragment != null) {
            transaction.hide(englishFragment);
        }
        if (politicalFragment != null) {
            transaction.hide(politicalFragment);
        }
        if (majorFragment != null) {
            transaction.hide(majorFragment);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public FadingActionBarHelper getFadingActionBarHelper() {
        return mFadingActionBarHelper;
    }

}
