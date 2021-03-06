package com.example.add_removememberrahabr.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.add_removememberrahabr.R;
import com.example.add_removememberrahabr.model.RegisterMemberEditModel;

import java.util.List;

public class RegisterMemberEditAdapter extends RecyclerView.Adapter<RegisterMemberEditAdapter.ViewHolderEdit> {

    List<RegisterMemberEditModel> editMemberList;
    Context context;

    public RegisterMemberEditAdapter(List<RegisterMemberEditModel> editMemberList, Context context) {
        this.editMemberList = editMemberList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderEdit onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_register_member_edit, parent, false);
        return new ViewHolderEdit(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderEdit holder, int position) {
        RegisterMemberEditModel model  = editMemberList.get(position);
        holder.txt_name.setText(model.txt_name);
        holder.img_delete.setOnClickListener(view -> {
            editMemberList.remove(position);
            notifyItemRemoved(position);
            notifyDataSetChanged();
        });

    }

    @Override
    public int getItemCount() {
        return editMemberList.size();
    }

    public class ViewHolderEdit extends RecyclerView.ViewHolder {
        TextView txt_name;
        ImageView img_delete;
        public ViewHolderEdit(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.txt_name_edited);
            img_delete = itemView.findViewById(R.id.img_delete_edited);
        }
    }
}
