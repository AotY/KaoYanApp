package com.qtao.kaoyanknowledge.ui.fragment;

/*
 * Copyright (C) 2013 AChep@xda <artemchep@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.achep.header2actionbar.HeaderFragment;
import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.util.Style;
import com.nhaarman.listviewanimations.swinginadapters.prepared.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.ScaleInAnimationAdapter;
import com.qtao.kaoyanknowledge.App.KaoYanApplication;
import com.qtao.kaoyanknowledge.R;
import com.qtao.kaoyanknowledge.adapter.MathAdapter;
import com.qtao.kaoyanknowledge.models.MathItem;
import com.qtao.kaoyanknowledge.ui.BaseActivity;
import com.qtao.kaoyanknowledge.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Artem on 06.12.13.
 */
public class MathFragment extends HeaderFragment implements MathAdapter.OnMathItemClickListener {

    /**
     * listView
     */
    private ListView mListView;

    /**
     * 用于记录是否已经加载过了
     */
    private boolean mLoaded;

    private List<MathItem> mDatas;

    /**
     * 适配器
     */
    private MathAdapter mathAdapter;


//    private AsyncLoadSomething mAsyncLoadSomething;

//    private FrameLayout mContentOverlay;

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        setHeaderBackgroundScrollMode(HEADER_BACKGROUND_SCROLL_PARALLAX);
        setOnHeaderScrollChangedListener(new OnHeaderScrollChangedListener() {
            @Override
            public void onHeaderScrollChanged(float progress, int height, int scroll) {
//                height -= getActivity().getActionBar().getHeight();
                height -= ((BaseActivity) activity).getMyActionbar().getHeight();
                progress = (float) scroll / height;
                if (progress > 1f) progress = 1f;

                progress = (1 - (float) Math.cos(progress * Math.PI)) * 0.5f;

                ((BaseActivity) activity)
                        .getFadingActionBarHelper()
                        .setActionBarAlpha((int) (255 * progress));
                ((MainActivity) activity).getTintManager().setStatusBarAlpha((255 * progress));
                if(progress == 1f){
                    ((BaseActivity) activity).setTitle(R.string.math);
                }else {
                    ((BaseActivity) activity).setTitle(R.string.app_name);
                }
            }
        });
//        cancelAsyncTask(mAsyncLoadSomething);
//        mAsyncLoadSomething = new AsyncLoadSomething(this);
//        mAsyncLoadSomething.execute();
    }


    /**
     * 当要CreateHeaderView是调用
     *
     * @param inflater
     * @param container
     * @return
     */
    @Override
    public View onCreateHeaderView(LayoutInflater inflater, ViewGroup container) {
//        Picasso.with(mContext).load(math.getImg()).into(holder.imageView);
//        View view = inflater.inflate(R.layout.fragment_header, container, false);
        return inflater.inflate(R.layout.fragment_header, container, false);
    }


    /**
     * 当createContentView时 调用
     *
     * @param inflater
     * @param container
     * @return
     */
    @Override
    public View onCreateContentView(LayoutInflater inflater, ViewGroup container) {
        mListView = (ListView) inflater.inflate(R.layout.fragment_listview, container, false);
        if (mLoaded) setListViewContent(mDatas);
        return mListView;
    }

    @Override
    public View onCreateContentOverlayView(LayoutInflater inflater, ViewGroup container) {
//        ProgressBar progressBar = new ProgressBar(getActivity());
        //加载进度圈
//        View progressBar = LayoutInflater.from(getActivity()).inflate(R.layout.progress_layout, null, false);
//        mContentOverlay = new FrameLayout(getActivity());
//        mContentOverlay.addView(progressBar, new FrameLayout.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
//        if (mLoaded) mContentOverlay.setVisibility(View.GONE);
        return null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListViewContent(getDatas());
    }


    private void setListViewContent(List<MathItem> datas) {
//        mLoaded = true;
//        this.mDatas = datas;
        if (mListView == null)
            return;

        mListView.setVisibility(View.VISIBLE);
        //设置适配器 为什么不能有那种效果 呢？？？ 适配器设置错误
        mathAdapter = new MathAdapter(getActivity(), datas);
        mathAdapter.setOnMathItemClickListener(this);
        ScaleInAnimationAdapter scaleInAnimationAdapter = new ScaleInAnimationAdapter(mathAdapter);
        AlphaInAnimationAdapter animationAdapter = new AlphaInAnimationAdapter(scaleInAnimationAdapter);
        animationAdapter.setAbsListView(mListView);
//        mListView.setAdapter(animationAdapter);
        setListViewAdapter(mListView, animationAdapter);

//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                SuperToast.create(KaoYanApplication.appContext, "" + position, SuperToast.Duration.SHORT,
//                        Style.getStyle(Style.BLUE, SuperToast.Animations.SCALE)).show();
//            }
//        });


    }

    @Override
    public void onMathItemClick(View v, MathItem math) {
        SuperToast.create(KaoYanApplication.appContext, "" + math.getType(), SuperToast.Duration.SHORT,
                Style.getStyle(Style.BLUE, SuperToast.Animations.SCALE)).show();
    }

    /**
     * 取消任务
     */
//    private void cancelAsyncTask(AsyncTask task) {
//        if (task != null) task.cancel(false);
//    }
    @Override
    public void onDetach() {
//        cancelAsyncTask(mAsyncLoadSomething);
        super.onDetach();
    }


//    private static class AsyncLoadSomething extends AsyncTask<Void, Void, List<MathItem>> {
//
//        final WeakReference<MathFragment> weakFragment;
//
//        public AsyncLoadSomething(MathFragment fragment) {
//            //软引用
//            this.weakFragment = new WeakReference<MathFragment>(fragment);
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            final MathFragment audioFragment = weakFragment.get();
//            if (audioFragment.mListView != null)
//                audioFragment.mListView.setVisibility(View.INVISIBLE);
//            if (audioFragment.mContentOverlay != null)
//                audioFragment.mContentOverlay.setVisibility(View.VISIBLE);
//        }
//
//        @Override
//        protected List<MathItem> doInBackground(Void... voids) {
//            // Emulate long downloading
//            List<MathItem> datas = getDatas();
////            try {
////                Thread.sleep(100);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
//            return datas;
//        }
//
//        @Override
//        protected void onPostExecute(List<MathItem> datas) {
//            super.onPostExecute(datas);
//            final MathFragment audioFragment = weakFragment.get();
//            if (audioFragment == null) {
////                if (Project.DEBUG) Log.d(TAG, "Skipping.., because there iso fragment anymore.");
//                L.e("Skipping.., because there iso fragment anymore.");
//                return;
//            }
//            if (audioFragment.mContentOverlay != null)
//                audioFragment.mContentOverlay.setVisibility(View.GONE);
//            audioFragment.setListViewContent(datas);
//        }
//    }

    /**
     * 获取数据
     *
     * @param
     */
    private static List<MathItem> getDatas() {
        List<MathItem> datas = new ArrayList<MathItem>();
        MathItem item1 = new MathItem(1, " 数学一", "高等数学 概率论 线性代数", "一");
        MathItem item2 = new MathItem(2, " 数学二", "高等数学 概率论 线性代数", "二");
        MathItem item3 = new MathItem(3, " 数学三", "高等数学 概率论 线性代数", "三");
        MathItem item4 = new MathItem(4, " 常用公式", "好记星不如烂笔头", "式");
        MathItem item5 = new MathItem(5, " 常考试题", "年年都有，就怕不会", "题");
        MathItem item8 = new MathItem(6, " 资料推荐", "站在巨人肩上摘苹果", "料");
        MathItem item6 = new MathItem(7, " 考试技巧", "以无招胜有招", "巧");
        MathItem item7 = new MathItem(8, " 考试注意", "小心驶得万年船", "注");
        datas.add(item1);
        datas.add(item2);
        datas.add(item3);
        datas.add(item4);
        datas.add(item5);
        datas.add(item6);
        datas.add(item7);
        datas.add(item8);
        return datas;
    }
}
