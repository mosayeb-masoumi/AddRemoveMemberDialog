package com.example.add_removememberrahabr.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import com.example.add_removememberrahabr.R;
import com.example.add_removememberrahabr.interfaces.RegisterItemInteraction;
import com.example.add_removememberrahabr.viewholder.RegisterMemberViewHolderDialog;
import com.example.add_removememberrahabr.model.Member;

public class RegisterMemberDialogAdapter extends RecyclerView.Adapter<RegisterMemberViewHolderDialog> {

    private List<Member> members;
    private Dialog dialog;
    private Context context;


    public RegisterMemberDialogAdapter(List<Member> members, Dialog dialog, Context context) {
        this.members = members;
        this.dialog = dialog;
        this.context = context;
    }

    @NonNull
    @Override
    public RegisterMemberViewHolderDialog onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_register_member, parent, false);
        return new RegisterMemberViewHolderDialog(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegisterMemberViewHolderDialog holder, int position) {

        Member model = members.get(position);
        holder.bindData(model);
        holder.setOnRegisterHolderListener(listener,model , dialog);


    }

    private RegisterItemInteraction listener = null;
    public void setListener(RegisterItemInteraction listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return members.size();
    }
}
