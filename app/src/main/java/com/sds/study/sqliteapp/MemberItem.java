package com.sds.study.sqliteapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.zip.Inflater;

/**
 *
 */

public class MemberItem extends LinearLayout {
    private Member member;
    TextView member_id;
    TextView txt_id;
    TextView txt_password;

    public MemberItem(Context context,Member member) {
        super(context);
        this.member = member;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.member_item,this);

        member_id = (TextView) findViewById(R.id.txt_member_id);
        txt_id = (TextView) findViewById(R.id.txt_id);
        txt_password =(TextView)findViewById(R.id.txt_password);

       setItem();
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
        setItem();
    }

    public void setItem(){
        member_id.setText(Integer.toString(member.getMember_id()));
        txt_id.setText(member.getId());
        txt_password.setText(member.getPassword());
    }
}
