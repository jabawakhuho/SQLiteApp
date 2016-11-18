package com.sds.study.sqliteapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by student on 2016-11-18.
 */

public class DetailActivity extends AppCompatActivity{
    ImageView img;
    LinearLayout layout;
    SQLiteDatabase db;
    MyListAdapter myListAdapter;
    Member member;
    TextView member_id;
    EditText id;
    EditText password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //디비
        init();
        //값
        member=getIntent().getParcelableExtra("member");
        member_id = (TextView)findViewById(R.id.txt_member_id);
        id = (EditText)findViewById(R.id.txt_id);
        password = (EditText)findViewById(R.id.txt_password);

        member_id.setText(Integer.toString(member.getMember_id()));
        id.setText(member.getId());
        password.setText(member.getPassword());

        img = (ImageView)findViewById(R.id.img);
        AnimationDrawable drawable=(AnimationDrawable)img.getDrawable();
        drawable.start();

        layout = (LinearLayout)findViewById(R.id.layout);
        AnimationDrawable back = (AnimationDrawable)layout.getBackground();
        back.start();
    }
    public void init(){
       db = MainActivity.db;
        myListAdapter = MainActivity.myListAdapter;
    }
    public void btnClick(View view){
        switch (view.getId()){
            case R.id.bt_back: reflash();finish();break;
            case R.id.bt_update : itemUpdate();break;
            case R.id.bt_delete : itemDelete();break;
        }
    }

    public void itemUpdate(){
        String sql = "update member set  id=?, password=? where member_id=?";
        String txt_id=id.getText().toString();
        String txt_password=password.getText().toString();
        int m_id=Integer.parseInt(member_id.getText().toString());
        db.execSQL(sql,new Object[]{txt_id,txt_password,m_id});
        Toast.makeText(this,"수정되었습니다.",Toast.LENGTH_SHORT).show();

    }

    public void itemDelete(){
        String sql = "delete from member where member_id=?";
        int m_id=Integer.parseInt(member_id.getText().toString());
        db.execSQL(sql,new Integer[]{m_id});
        Toast.makeText(this,"삭제되었습니다.",Toast.LENGTH_SHORT).show();
        reflash();
        finish();
    }

    public void reflash(){
        myListAdapter.getList();
        myListAdapter.notifyDataSetChanged();
    }


}
