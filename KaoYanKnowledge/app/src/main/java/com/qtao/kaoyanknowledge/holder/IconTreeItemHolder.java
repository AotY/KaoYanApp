package com.qtao.kaoyanknowledge.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.github.johnkil.print.PrintView;
import com.qtao.kaoyanknowledge.R;
import com.qtao.kaoyanknowledge.models.MajorItem;
import com.unnamed.b.atv.model.TreeNode;

/**
 * Created by Bogdan Melnychuk on 2/12/15.
 */
public class IconTreeItemHolder extends TreeNode.BaseNodeViewHolder<IconTreeItemHolder.IconTreeItem> {

    private TextView nodeTextValue;

    private PrintView arrowView;

    public IconTreeItemHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(final TreeNode node, IconTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.choose_icon_node, null, false);

        nodeTextValue = (TextView) view.findViewById(R.id.choose_node_value);
        nodeTextValue.setText(value.majorItem.getName());

        final PrintView iconView = (PrintView) view.findViewById(R.id.choose_icon);
        iconView.setIconText(context.getResources().getString(value.icon));

        arrowView = (PrintView) view.findViewById(R.id.choose_arrow_icon);
        if (node.isLeaf()) {
            arrowView.setVisibility(View.INVISIBLE);
        }
        return view;
    }

    @Override
    public void toggle(boolean active) {
        arrowView.setIconText(context.getResources().getString(active ? R.string.ic_keyboard_arrow_down : R.string.ic_keyboard_arrow_right));
    }

    public static class IconTreeItem {
        public int icon;
        //        public String text;
        public MajorItem majorItem;

        public IconTreeItem(int icon, MajorItem item) {
            this.icon = icon;
            this.majorItem = item;
        }
    }
}
