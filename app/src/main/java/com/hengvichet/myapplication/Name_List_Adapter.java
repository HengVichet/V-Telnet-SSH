package com.hengvichet.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Vichet on 07/01/2016.
 */
public class Name_List_Adapter extends RecyclerView.Adapter<Name_List_Adapter.ViewHolder> {

    private List<Name_List> mNameLists;
    public Name_List_Adapter(Context context, List<Name_List> namelist) {
        mNameLists = namelist;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name,ip;
        public Button loadButton;
        public Button deleteButton;
        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            ip = (TextView) itemView.findViewById(R.id.ip);
            loadButton = (Button) itemView.findViewById(R.id.loadButton);
            deleteButton = (Button) itemView.findViewById(R.id.deleteButton);

            loadButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Log.d("Adapter", "position " + position);
                    Name_List namelist = mNameLists.get(position);
                    Log.d("Adapter", "ip " + namelist.getIp());
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Log.d("Adapter", "delete position new " + position);
                    mNameLists.remove(position);
                    notifyItemRemoved(position);
                }
            });
        }
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Name_List namelist = mNameLists.get(position);
        holder.name.setText(namelist.getName());
        holder.ip.setText(namelist.getIp());

    }
    @Override
    public int getItemCount() {
        return mNameLists.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View rowView = inflater.inflate(R.layout.item_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(rowView);
        return viewHolder;
    }
}
