package com.qtao.kaoyanknowledge.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.qtao.kaoyanknowledge.R;
import com.qtao.kaoyanknowledge.models.MathItem;
import com.qtao.kaoyanknowledge.utils.DrawableProvider;
import com.qtao.kaoyanknowledge.utils.L;

import java.util.List;

/**
 * Created by AotY on 2015/7/3.
 * <p/>
 * 数学页面中ListView的适配器
 */
public class MathAdapter extends BaseAdapter {

    /**
     * 数据
     */
    private List<MathItem> datas;

    private Context mContext;

    private LayoutInflater inflater;

    /**
     * 颜色生成器
     */
    private final DrawableProvider drawableProvider;

    public MathAdapter(Context context, List<MathItem> datas) {
        this.mContext = context;
        this.datas = datas;
        inflater = LayoutInflater.from(context);
        drawableProvider = new DrawableProvider(context);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.math_item_layout, null, false);
//            holder.circleImageView = (CircleImageView) convertView.findViewById(R.id.math_item_image);
            holder.rootView =  convertView.findViewById(R.id.math_item_container);;
            holder.imageView = (ImageView) convertView.findViewById(R.id.math_item_image);
            holder.nameText = (TextView) convertView.findViewById(R.id.item_math_name);
            holder.contenText = (TextView) convertView.findViewById(R.id.item_math_content);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        final MathItem math = datas.get(position);
        holder.nameText.setText(math.getName());
        holder.contenText.setText(math.getContent());
        TextDrawable imgDrawable = drawableProvider.getRoundWithBorder(math.getBerif());
        holder.imageView.setImageDrawable(imgDrawable);
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onMathItemClick(v, math);
                }
                L.e("holder.rootView.setOnClickListener");
            }
        });
//        Picasso.with(mContext).load(math.getImg()).into(holder.imageView);
        return convertView;
    }

    private class ViewHolder {
        public ViewHolder() {

        }

        View rootView;
        //        CircleImageView circleImageView;
        ImageView imageView;
        TextView nameText;
        TextView contenText;
    }

    private OnMathItemClickListener listener;

    public void setOnMathItemClickListener(OnMathItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnMathItemClickListener {
        public void onMathItemClick(View v, MathItem math);
    }
}
