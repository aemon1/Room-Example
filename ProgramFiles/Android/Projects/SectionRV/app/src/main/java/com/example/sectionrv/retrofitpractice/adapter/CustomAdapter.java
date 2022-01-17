package com.example.sectionrv.retrofitpractice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sectionrv.R;
import com.example.sectionrv.retrofitpractice.model.Posts;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    Context context;
    List<Posts> postsList;

    public CustomAdapter(Context context, List<Posts> postsList) {
        this.context = context;
        this.postsList = postsList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =   layoutInflater.inflate(R.layout.retrofit_customrow, parent,false);
        return new CustomViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.uid_val.setText(Integer.toString(postsList.get(position).getUserId()));
        holder.id_val.setText(Integer.toString(postsList.get(position).getId()));
        holder.titleID_val.setText(postsList.get(position).getTitle());
        holder.textID_val.setText(postsList.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }


    class CustomViewHolder extends  RecyclerView.ViewHolder{

        TextView  id_val, uid_val, titleID_val, textID_val;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            id_val = itemView.findViewById(R.id.id_val);

            uid_val = itemView.findViewById(R.id.uid_val);

            titleID_val = itemView.findViewById(R.id.titleId_val);

            textID_val = itemView.findViewById(R.id.textId_val);

        }
    }
}
