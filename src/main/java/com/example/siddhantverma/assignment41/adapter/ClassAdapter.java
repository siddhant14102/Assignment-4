package com.example.siddhantverma.assignment41.adapter;

import android.app.LauncherActivity;
import android.content.ContentValues;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.siddhantverma.assignment41.R;
import com.example.siddhantverma.assignment41.model.Listitem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Siddhant Verma on 06-Nov-16.
 */
public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.holder>{

    private List<Listitem> list;
    private LayoutInflater layoutInflater;
    public static ClassAdapter task;

    private ItemClickCallback itemClickCallback;
    public interface ItemClickCallback {
        void onItemClick(int p);
        void onSecondaryIconClick(int p);
    }

    public void setItemClickCallback(final ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }

    public ClassAdapter(List<Listitem> list, Context c)
    {
        layoutInflater=LayoutInflater.from(c);
        this.list= list;

     //   this.layoutInflater=LayoutInflater.from(c);
    }

//    public ClassAdapter get(Context context)
//    {
//        if(task==null)
//        {
//            task=new ClassAdapter()
//        }
//    }


    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=layoutInflater.inflate(R.layout.listitem, parent,false);
        return new holder(v);
    }
    public void setList(ArrayList<Listitem> exerciseList) {
        this.list.clear();
        this.list.addAll(exerciseList);
    }

    @Override
    public void onBindViewHolder(holder holder, int position) {

        Listitem item=list.get(position);
        holder.img.setImageResource(item.getImgID());
        holder.title.setText(item.getTitle());
        holder.subtitle.setText(item.getSecondary_text());
        if(item.isTemp())
        {
            holder.secimg.setImageResource(R.drawable.ic_star_border_white_24dp);
        }
        else
        {
            holder.secimg.setImageResource(R.drawable.ic_star_black_24dp);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class holder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        private TextView title;
        private ImageView img;
        private View holder;
        private TextView subtitle;

        private ImageView secimg;



        public holder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.text);
            img=(ImageView)itemView.findViewById(R.id.icon);
            holder=itemView.findViewById(R.id.containerid);
            subtitle=(TextView)itemView.findViewById(R.id.subtext);
            secimg=(ImageView)itemView.findViewById(R.id.icon_secondary);
            secimg.setOnClickListener(this);
            holder.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {

            if(view.getId()==R.id.containerid)
            {
                itemClickCallback.onItemClick(getAdapterPosition());

            }
            else
            {
                itemClickCallback.onSecondaryIconClick(getAdapterPosition());
            }

        }
    }
}
