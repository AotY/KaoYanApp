package com.qtao.kaoyanknowledge.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.util.Style;
import com.qtao.kaoyanknowledge.App.KaoYanApplication;
import com.qtao.kaoyanknowledge.R;
import com.qtao.kaoyanknowledge.holder.IconTreeItemHolder;
import com.qtao.kaoyanknowledge.models.MajorItem;
import com.qtao.kaoyanknowledge.utils.GetMajors;
import com.qtao.kaoyanknowledge.utils.L;
import com.qtao.kaoyanknowledge.utils.UserInfo;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.List;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by AotY on 2015/7/6.
 * <p/>
 * 让用户选择专业
 */
public class ChooseMajorActivity extends BaseActivity {
    /**
     * 用户生成树形结构
     */
    private AndroidTreeView tView;

    private ViewGroup containerView;

    private ProgressBar progressBar;

    /**
     * 用于当专业列表生成后更新
     */
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            //设置
            tView = new AndroidTreeView(ChooseMajorActivity.this, (TreeNode) msg.obj);
            tView.setDefaultAnimation(true);
            tView.setDefaultContainerStyle(R.style.TreeNodeStyleCustom);
            tView.setDefaultViewHolder(IconTreeItemHolder.class);
            tView.setDefaultNodeClickListener(nodeClickListener);
            containerView.addView(tView.getView());
            containerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_major_layout);
        initViews();
    }

    /**
     * 初始化
     */
    private void initViews() {
        setTitle(R.string.choose_major);
        getTintManager().setStatusBarAlpha(255f);
//        statusBar = (TextView) this.findViewById(R.id.major_status_bar);
        progressBar = (ProgressBar) findViewById(R.id.choose_major_progress);
        containerView = (ViewGroup) this.findViewById(R.id.major_choose_container);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) containerView.getLayoutParams();
        lp.topMargin = actionbarHeight + statusHeight + 50;
        L.e("actionBar == " + getMyActionbar().getHeight());
        L.e("statusHeight == " + statusHeight);
        containerView.setLayoutParams(lp);
        initTreeView();
    }

    /**
     * 初始化树列表
     */
    private void initTreeView() {
        ReadMajorTask readMajorTask = new ReadMajorTask(this, mHandler);
        readMajorTask.run();
    }

    private TreeNode.TreeNodeClickListener nodeClickListener = new TreeNode.TreeNodeClickListener() {
        @Override
        public void onClick(TreeNode node, Object value) {
            final IconTreeItemHolder.IconTreeItem item = (IconTreeItemHolder.IconTreeItem) value;
//            statusBar.setText("Last clicked: " + item.text);
            setTitle(((IconTreeItemHolder.IconTreeItem) value).majorItem.getName());
            if (node.isLeaf()) {
                final MaterialDialog mMaterialDialog = new MaterialDialog(ChooseMajorActivity.this);
                mMaterialDialog.setTitle("注意")
                        .setMessage("您确定选择该专业吗?\n选择后,您可以在设置页面修改您的专业。")
                        .setPositiveButton(getResources().getString(R.string.commit), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();
                                //要保存用户的专业信息，然后返回；主要是专业很多，这么确定呢？
                                //用数据库吧，用数据库来查询用户的专业课程
                                ChooseMajorActivity.this.setResult(RESULT_CANCELED);
                                UserInfo.setUserMajor(ChooseMajorActivity.this, item.majorItem.getName());
                                ChooseMajorActivity.this.finish();
                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.cancel), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();
                            }
                        });

                mMaterialDialog.show();
            }
        }
    };

    /**
     * 用于读取专业文件，生成树形结构
     */
    private static class ReadMajorTask implements Runnable {

        final private Context context;
        final private Handler mHandler;

        public ReadMajorTask(Context context, Handler mHandler) {
            this.context = context;
            this.mHandler = mHandler;
        }

        @Override
        public void run() {
            GetMajors getMajors = new GetMajors(context);
            List<MajorItem> majors = getMajors.getMajorsFromFile();
            //生成树 , 需要递归
            TreeNode root = TreeNode.root();
            for (int i = 0; i < majors.size(); i++) {
                TreeNode majorLine = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_folder, majors.get(i)));
                buildTreeView(majors.get(i).getClassChildren(), majorLine);
                root.addChild(majorLine);
            }
            //发送消息
            Message msg = Message.obtain();
            msg.obj = root;
            mHandler.sendMessage(msg);
        }

        private void buildTreeView(List<MajorItem> items, TreeNode majorLine) {
            if (items == null)
                return;
            if (items.size() > 0) {
                for (int j = 0; j < items.size(); j++) {
                    if (items.get(j).getClassChildren() == null) {
                        TreeNode file = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_drive_file, items.get(j)));
                        majorLine.addChild(file);
                    } else {
                        TreeNode majorLine2 = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_folder, items.get(j)));
//                        buildTreeView(items.get(j).getClassChildren(), majorLine2);
                        for (int k = 0; k < items.get(j).getClassChildren().size(); k++) {
                            TreeNode file = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_drive_file, items.get(j).getClassChildren().get(k)));
                            majorLine2.addChild(file);
                        }
                        majorLine.addChild(majorLine2);
                    }
//                TreeNode majorLine2 = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_folder, item));
//                majorLine.addChild(majorLine2);
//                buildTreeView(item, majorLine2);
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tState", tView.getSaveState());
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            String state = savedInstanceState.getString("tState");
            if (!TextUtils.isEmpty(state)) {
                tView.restoreState(state);
            }
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        SuperToast.create(KaoYanApplication.appContext, "您还没有选择专业", SuperToast.Duration.SHORT,
                Style.getStyle(Style.BLUE, SuperToast.Animations.FADE)).show();
//        ChooseMajorActivity.this.setResult(RESULT_CANCELED);
//        UserInfo.setUserMajor(ChooseMajorActivity.this,null);
//        ChooseMajorActivity.this.finish();
    }
}
