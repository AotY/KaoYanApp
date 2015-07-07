package com.qtao.kaoyanknowledge.ui;

import android.os.Bundle;
import android.view.View;

import com.kenumir.materialsettings.MaterialSettingsActivity;
import com.kenumir.materialsettings.items.CheckboxItem;
import com.kenumir.materialsettings.items.DividerItem;
import com.kenumir.materialsettings.items.HeaderItem;
import com.kenumir.materialsettings.items.SwitcherItem;
import com.kenumir.materialsettings.items.TextItem;
import com.kenumir.materialsettings.storage.PreferencesStorageInterface;
import com.kenumir.materialsettings.storage.StorageInterface;
import com.qtao.kaoyanknowledge.R;
import com.qtao.kaoyanknowledge.utils.UserInfo;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by AotY on 2015/7/5.
 */
public class SettignActivity extends MaterialSettingsActivity {

    private TextItem majorTextItem;

    private CheckboxItem isAlwayesChooseItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.setting));

        addItem(new HeaderItem(getFragment()).setTitle(getString(R.string.general)));
       majorTextItem = new TextItem(getFragment(), "key1").setTitle("您当前专业: "+ UserInfo.getUserMajor(this)).setOnclick(new TextItem.OnClickListener() {
            @Override
            public void onClick(TextItem v) {
                final MaterialDialog mMaterialDialog = new MaterialDialog(SettignActivity.this);
                mMaterialDialog.setTitle("注意")
                        .setMessage("您确重新选择专业吗?")
                        .setPositiveButton(getResources().getString(R.string.commit), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();
                                majorTextItem.setSubtitle("您当前没有选择专业");
                                UserInfo.clearUserMajor(SettignActivity.this);
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
        });
        addItem(majorTextItem);

        addItem(new DividerItem(getFragment()));

        isAlwayesChooseItem = new CheckboxItem(getFragment(), "key2").setTitle("是否每次进入专业页面\n都重新选择专业").setOnCheckedChangeListener(new CheckboxItem.OnCheckedChangeListener() {
            @Override
            public void onCheckedChange(CheckboxItem cbi, boolean isChecked) {
                UserInfo.setUserChooseEnterMajor(SettignActivity.this, isChecked);
            }
        });
        isAlwayesChooseItem.setDefaultValue(UserInfo.getUserChooseEnterMajor(this));
        addItem(isAlwayesChooseItem);

        addItem(new DividerItem(getFragment()));
        addItem(new SwitcherItem(getFragment(), "key1a").setTitle("Switcher item 3 - no subtitle"));
        addItem(new DividerItem(getFragment()));
        addItem(new SwitcherItem(getFragment(), "key1b").setTitle("Switcher item 3").setSubtitle("With subtitle"));
        addItem(new DividerItem(getFragment()));
        addItem(new CheckboxItem(getFragment(), "key1c").setTitle("Checkbox item 3 - no subtitle"));
        addItem(new DividerItem(getFragment()));
        addItem(new HeaderItem(getFragment()).setTitle("Sample title 2"));
        addItem(new TextItem(getFragment(), "key4").setTitle("Simple text item 2").setSubtitle("Subtitle of simple text item 2").setOnclick(new TextItem.OnClickListener() {
            @Override
            public void onClick(TextItem v) {

            }
        }));
        addItem(new DividerItem(getFragment()));
        addItem(new TextItem(getFragment(), "key5").setTitle("Simple text item 3 - no subtitle"));
        addItem(new DividerItem(getFragment()));
        addItem(new TextItem(getFragment(), "key5a").setTitle("Simple text item with icon - no subtitle").setIcon(R.drawable.ic_check_circle_grey600_24dp));
        addItem(new DividerItem(getFragment()));
        addItem(new TextItem(getFragment(), "key5b").setTitle("Simple text item with icon - no subtitle").setSubtitle("Subtitle of item with icon").setIcon(R.drawable.ic_check_circle_grey600_24dp));
        addItem(new HeaderItem(getFragment()).setTitle("Same usage with dialogs"));
        addItem(new TextItem(getFragment(), "key6").setTitle("Simple message dialog").setSubtitle("Clck to show message and change subtext").setOnclick(new TextItem.OnClickListener() {
            @Override
            public void onClick(TextItem item) {

            }
        }));
    }

    @Override
    public StorageInterface initStorageInterface() {
        return new PreferencesStorageInterface(this);
    }

}