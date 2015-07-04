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
import com.qtao.kaoyanknowledge.models.EnglishItem;
import com.squareup.picasso.Picasso;

import java.util.List;

// Provide the underlying view for an individual list item.
public class EnglishAdapter extends RecyclerView.Adapter<EnglishAdapter.VH> {

    private Activity mContext;

    private List<EnglishItem> mContacts;

//    private List<Integer> mHeights;


    public interface onRecyclerViewItemClickListener{
        public void onRecyclerViewItemClick(View view, EnglishItem contact) ;
    }

    private onRecyclerViewItemClickListener listener ;

    public void setOnRecyclerViewItemClickListener(onRecyclerViewItemClickListener listener){
        this.listener = listener ;
    }
    public EnglishAdapter(Activity context, List<EnglishItem> contacts) {
        mContext = context;
        if (contacts == null) {
            throw new IllegalArgumentException("contacts must not be null");
        }
        mContacts = contacts;
//        mHeights = new ArrayList<Integer>();
//        for (int i = 0; i < mContacts.size(); i++)
//        {
//            mHeights.add( (int) (100 + Math.random() * 300));
//        }
    }

    // Inflate the view based on the viewType provided.
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.english_item_contact, parent, false);
        return new VH(itemView, mContext);
    }

    // Display data at the specified position
    @Override
    public void onBindViewHolder(VH holder, int position) {
        EnglishItem english = mContacts.get(position);

//        ViewGroup.LayoutParams lp = holder.ivProfile.getLayoutParams();
//        lp.height = mHeights.get(position);
//        holder.ivProfile.setLayoutParams(lp);

        holder.rootView.setTag(english);
        holder.tvName.setText(english.getName());
        Picasso.with(mContext).load(english.getThumbnailDrawable()).into(holder.ivProfile);

    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    // Provide a reference to the views for each contact item
    public final static class VH extends RecyclerView.ViewHolder {
        final View rootView;
        final ImageView ivProfile;
        final TextView tvName;
        final View vPalette;

        public VH(View itemView, final Context context) {
            super(itemView);
            rootView = itemView;
            ivProfile = (ImageView) itemView.findViewById(R.id.ivProfile);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            vPalette = itemView.findViewById(R.id.vPalette);

            // Navigate to contact details activity on click of card view.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final EnglishItem english = (EnglishItem) v.getTag();
                    if (english != null) {
                        // Fire an intent when a contact is selected
                        // Pass contact object in the bundle and populate details activity.
                    }
                }
            });
        }
    }
}
