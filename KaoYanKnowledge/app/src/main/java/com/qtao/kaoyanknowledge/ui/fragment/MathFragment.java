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
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.achep.header2actionbar.HeaderFragment;
import com.qtao.kaoyanknowledge.R;
import com.qtao.kaoyanknowledge.adapter.MathAdapter;
import com.qtao.kaoyanknowledge.models.MathItem;
import com.qtao.kaoyanknowledge.ui.MainActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem on 06.12.13.
 */
public class MathFragment extends HeaderFragment {

    private ListView mListView;

    private boolean mLoaded;

    private List<MathItem> mDatas;

    private MathAdapter mathAdapter;

    private AsyncLoadSomething mAsyncLoadSomething;
    private FrameLayout mContentOverlay;

    /**
     * 加载一次
     */
//    private boolean isOnce ;
    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        setHeaderBackgroundScrollMode(HEADER_BACKGROUND_SCROLL_PARALLAX);
        setOnHeaderScrollChangedListener(new OnHeaderScrollChangedListener() {
            @Override
            public void onHeaderScrollChanged(float progress, int height, int scroll) {
//                height -= getActivity().getActionBar().getHeight();

                height -= ((MainActivity) activity).getMyActionbar().getHeight();
                progress = (float) scroll / height;
                if (progress > 1f) progress = 1f;

                // *
                // `*
                // ```*
                // ``````*
                // ````````*
                // `````````*
                progress = (1 - (float) Math.cos(progress * Math.PI)) * 0.5f;

                ((MainActivity) activity)
                        .getFadingActionBarHelper()
                        .setActionBarAlpha((int) (255 * progress));
            }
        });

//        if(!isOnce) {
        cancelAsyncTask(mAsyncLoadSomething);
        mAsyncLoadSomething = new AsyncLoadSomething(this);
        mAsyncLoadSomething.execute();
//            isOnce = true ;
//        }
    }

    @Override
    public void onDetach() {
        cancelAsyncTask(mAsyncLoadSomething);
        super.onDetach();
    }

    @Override
    public View onCreateHeaderView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_header, container, false);
    }

    @Override
    public View onCreateContentView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_listview, container, false);
        mListView = (ListView) view.findViewById(R.id.math_list_view);
        if (mLoaded) setListViewContent(mDatas);
        return view;
    }

    @Override
    public View onCreateContentOverlayView(LayoutInflater inflater, ViewGroup container) {
//        ProgressBar progressBar = new ProgressBar(getActivity());
        //加载进度圈
        View progressBar = LayoutInflater.from(getActivity()).inflate(R.layout.progress_layout, null, false);
        mContentOverlay = new FrameLayout(getActivity());
        mContentOverlay.addView(progressBar, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        if (mLoaded) mContentOverlay.setVisibility(View.GONE);
        return mContentOverlay;
    }

    private void setListViewContent(List<MathItem> datas) {
        mLoaded = true;
        this.mDatas = datas;
        if (mListView == null) return;
        mListView.setVisibility(View.VISIBLE);
        //设置适配器 为什么不能有那种效果 呢？？？
        mathAdapter = new MathAdapter(getActivity(), datas);
//        mListView.setAdapter(mathAdapter);
        setListViewAdapter(mListView, mathAdapter);
    }

    private void cancelAsyncTask(AsyncTask task) {
        if (task != null) task.cancel(false);
    }


    private static class AsyncLoadSomething extends AsyncTask<Void, Void, List<MathItem>> {

        private static final String TAG = "AsyncLoadSomething";

        final WeakReference<MathFragment> weakFragment;

        public AsyncLoadSomething(MathFragment fragment) {
            //软
            this.weakFragment = new WeakReference<MathFragment>(fragment);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            final MathFragment audioFragment = weakFragment.get();
            if (audioFragment.mListView != null)
                audioFragment.mListView.setVisibility(View.INVISIBLE);
            if (audioFragment.mContentOverlay != null)
                audioFragment.mContentOverlay.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<MathItem> doInBackground(Void... voids) {
            List<MathItem> datas = new ArrayList<MathItem>();
            // Emulate long downloading
            MathItem item1 = new MathItem(1, " 考研数学一", "高等数学 概率论 线性代数", R.drawable.math_item2);
            MathItem item2 = new MathItem(1, " 考研数学二", "高等数学 概率论 线性代数", R.drawable.math_item2);
            MathItem item3 = new MathItem(1, " 考研数学三", "高等数学 概率论 线性代数", R.drawable.math_item2);
            MathItem item4 = new MathItem(1, " 考研数学三", "高等数学 概率论 线性代数", R.drawable.math_item2);
            MathItem item5 = new MathItem(1, " 考研数学三", "高等数学 概率论 线性代数", R.drawable.math_item2);
            MathItem item6 = new MathItem(1, " 考研数学三", "高等数学 概率论 线性代数", R.drawable.math_item2);
            MathItem item7 = new MathItem(1, " 考研数学三", "高等数学 概率论 线性代数", R.drawable.math_item2);
            datas.add(item1);
            datas.add(item2);
            datas.add(item3);
            datas.add(item4);
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            return datas;
        }

        @Override
        protected void onPostExecute(List<MathItem> datas) {
            super.onPostExecute(datas);
            final MathFragment audioFragment = weakFragment.get();
            if (audioFragment == null) {
//                if (Project.DEBUG) Log.d(TAG, "Skipping.., because there is no fragment anymore.");
                return;
            }
            if (audioFragment.mContentOverlay != null)
                audioFragment.mContentOverlay.setVisibility(View.GONE);
            audioFragment.setListViewContent(datas);
        }
    }
}
