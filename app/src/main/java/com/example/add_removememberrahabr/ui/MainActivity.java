package com.example.add_removememberrahabr.ui;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.add_removememberrahabr.R;
import com.example.add_removememberrahabr.interfaces.RegisterItemInteraction;
import com.example.add_removememberrahabr.adapter.RegisterMemberDialogAdapter;
import com.example.add_removememberrahabr.adapter.RegisterMemberEditAdapter;
import com.example.add_removememberrahabr.model.RegisterMemberEditModel;

import java.util.ArrayList;

import com.example.add_removememberrahabr.model.Member;

public class MainActivity extends AppCompatActivity implements RegisterItemInteraction {

    RelativeLayout rl_addmember;
    RegisterMemberDialogAdapter adapter_member;
    RegisterMemberEditAdapter adapter_edited;

    ArrayList<Member> members;
    ArrayList<RegisterMemberEditModel> editMembers;

    RecyclerView recyclerEditedMember;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerEditedMember = findViewById(R.id.recycler_edited_members);
        rl_addmember = findViewById(R.id.rl_addmember);

        rl_addmember.setOnClickListener(view -> showAddMemberDialog());
    }


    private void showAddMemberDialog() {

        editMembers = new ArrayList<>();

        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.members_dialog);

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }


        members = new ArrayList<>();
        members.add(new Member("hassan", "1"));
        members.add(new Member("reza", "2"));
        members.add(new Member("nasim", "3"));
        members.add(new Member("nasrin", "4"));


        CheckBox checkBoxAll = dialog.findViewById(R.id.checkbox_all);
        RecyclerView recyclerview_members = dialog.findViewById(R.id.recyclerview_members);
        Button btn_exit_dialog = dialog.findViewById(R.id.btn_exit_dialog);
        ImageView img_close = dialog.findViewById(R.id.img_close);

        recyclerview_members.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter_member = new RegisterMemberDialogAdapter(members, dialog, MainActivity.this);
        adapter_member.setListener(this);  // important or else the app will crashed
        recyclerview_members.setAdapter(adapter_member);


        // to select all members
        checkBoxAll.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                editMembers = new ArrayList<>();

                for (int i = 0; i < members.size(); i++) {
                    editMembers.add(new RegisterMemberEditModel(members.get(i).name, members.get(i).id));
                }

                updateEditMemberList(editMembers);
                dialog.dismiss();
            }
        });

        img_close.setOnClickListener(v -> dialog.dismiss());
        btn_exit_dialog.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

    @Override
    public void onClicked(String name, String id, Dialog dialog, Boolean chkbox) {

        if (chkbox) {
            editMembers.add(new RegisterMemberEditModel(name, id));
        } else {

            if (editMembers.size() > 0) {
                for (int i = 0; i < editMembers.size(); i++) {

                    if (editMembers.get(i).txt_name.equals(name)) {
                        editMembers.remove(i);
                    }

                }
            }
        }
        updateEditMemberList(editMembers);
    }


    private void updateEditMemberList(ArrayList<RegisterMemberEditModel> editMembers) {

        recyclerEditedMember.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
        adapter_edited = new RegisterMemberEditAdapter(editMembers, MainActivity.this);
        recyclerEditedMember.setAdapter(adapter_edited);
    }

}
