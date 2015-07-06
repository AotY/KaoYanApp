package com.qtao.kaoyanknowledge.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qtao.kaoyanknowledge.R;
import com.qtao.kaoyanknowledge.models.EngAndPolItem;
import com.qtao.kaoyanknowledge.utils.DrawableProvider;

import java.util.List;

// Provide the underlying view for an individual list item.
public class EnglishAdapter extends RecyclerView.Adapter<EnglishAdapter.VH> {

    private Context mContext;

    private List<EngAndPolItem> mDatas;

//    private List<Integer> mHeights;

    /**
     * 监听器
     */
    private static onRecyclerViewItemClickListener listener;


    public EnglishAdapter(Activity context, List<EngAndPolItem> contacts) {
        mContext = context;
        if (contacts == null) {
            throw new IllegalArgumentException("contacts must not be null");
        }
        mDatas = contacts;
//        mHeights = new ArrayList<Integer>();
//        for (int i = 0; i < mDatas.size(); i++)
//        {
//            mHeights.add( (int) (100 + Math.random() * 300));
//        }
    }

    // Inflate the view based on the viewType provided.
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.english_item_layout, parent, false);
        return new VH(itemView, mContext);
    }

    // Display data at the specified position
    @Override
    public void onBindViewHolder(VH holder, int position) {
        final EngAndPolItem english = mDatas.get(position);

//        ViewGroup.LayoutParams lp = holder.ivProfile.getLayoutParams();
//        lp.height = mHeights.get(position);
//        holder.ivProfile.setLayoutParams(lp);
        holder.itemImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final EngAndPolItem english = (EngAndPolItem) v.getTag();
                if (english != null && listener != null) {
                    // Fire an intent when a contact is selected
                    // Pass contact object in the bundle and populate details activity.
                    listener.onRecyclerViewItemClick(v, english);
                }
            }
        });

        holder.rootView.setTag(english);
        holder.itemName.setText(english.getName());
        holder.itemImg.setImageDrawable(new DrawableProvider(mContext).getRoundRectWithBorder(english.getBerif()));
//        Picasso.with(mContext).load(english.getThumbnailDrawable()).into(holder.ivProfile);

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    // Provide a reference to the views for each contact item
    public final static class VH extends RecyclerView.ViewHolder {
        final View rootView;
        final ImageView itemImg;
        final TextView itemName;
        final View vPalette;

        public VH(View itemView, final Context context) {
            super(itemView);
            rootView = itemView;
            itemImg = (ImageView) itemView.findViewById(R.id.english_item_img);
            itemName = (TextView) itemView.findViewById(R.id.english_item_name);
            vPalette = itemView.findViewById(R.id.vPalette);
            // Navigate to contact details activity on click of card view.

        }
    }

    /**
     * 点击事件监听器
     */
    public interface onRecyclerViewItemClickListener {
        public void onRecyclerViewItemClick(View view, EngAndPolItem contact);
    }


    /**
     * 设置监听
     *
     * @param listener
     */
    public void setOnRecyclerViewItemClickListener(onRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }
}
