package com.hengvichet.myapplication;

import android.content.Context;
import android.database.Cursor;
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
    private Cursor cursor;
    private Name_List mNameLists;
    private Context context;

    public Name_List_Adapter(Context context){
        this.context = context;
    }

    public void setmNameLists(Name_List nameLists){
        this.mNameLists = mNameLists;
    }
    public void setCursor(Cursor cursor){
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView name,ip,type;
        public Button loadButton;
        public Button deleteButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            ip = (TextView) itemView.findViewById(R.id.ip);
            type = (TextView) itemView.findViewById(R.id.type);
            loadButton = (Button) itemView.findViewById(R.id.loadButton);
            deleteButton = (Button) itemView.findViewById(R.id.deleteButton);

            loadButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    cursor.moveToPosition(position);
                    String nametext = cursor.getString(0);
                    String iptext = cursor.getString(1);
                    String typetext = cursor.getString(2);
                }
            });
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    cursor.moveToPosition(position);
                    String nametext = cursor.getString(0);
                   // Log.d("Adapter","delete position new" + position);
                    //notifyItemRemoved(position);
                    DatabaseHelper.delete(context,nametext);
                    cursor = DatabaseHelper.getAll(context);
                    notifyDataSetChanged();

                }
            });
        }


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View rowView = inflater.inflate(R.layout.item_row, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Name_List_Adapter.ViewHolder holder, int position) {
        Log.d("Database", "position " + position);
        cursor.moveToPosition(position);
        String nametext = cursor.getString(0);
        String iptext = "Port:"+ cursor.getString(1);
        String typetext = " Type:"+ cursor.getString(2);
        holder.name.setText(nametext);
        holder.ip.setText(iptext);
        holder.type.setText(typetext);
    }

    @Override
    public int getItemCount() {
        if (cursor == null) {
            return 0;
        }
        return cursor.getCount();
    }
}
