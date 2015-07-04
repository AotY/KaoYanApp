package com.qtao.kaoyanknowledge.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qtao.kaoyanknowledge.R;
import com.qtao.kaoyanknowledge.models.MathItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by AotY on 2015/7/3.
 */
public class MathAdapter extends BaseAdapter {

    private List<MathItem> datas;

    private Context mContext;

    private LayoutInflater inflater ;

    public MathAdapter(Context context , List<MathItem> datas){
        this.mContext = context ;
        this.datas = datas ;
        inflater = LayoutInflater.from(context);
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
        if(convertView == null){
            convertView = inflater.inflate(R.layout.math_item_layout , null , false ) ;
            holder = new ViewHolder() ;
            holder.circleImageView = (CircleImageView) convertView.findViewById(R.id.math_item_image);
            holder.nameText = (TextView) convertView.findViewById(R.id.item_math_name);
            holder.contenText = (TextView) convertView.findViewById(R.id.item_math_content);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();

       MathItem math = datas.get(position);

        holder.nameText.setText(math.getName());
        holder.contenText.setText(math.getContent());
        Picasso.with(mContext).load(math.getImg()).into(holder.circleImageView);
        return convertView;
    }

    private class ViewHolder{
        public ViewHolder(){

        }
        CircleImageView circleImageView;
        TextView nameText;
        TextView contenText;
    }
}
