package com.sds.study.sqliteapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 ListView에 보여질 화면이 단일 위젯이 아닌, 2개  이상으로 구성된
 복합위젯일 경우 개발자가 디자인을 정의하므로, 어댑터를 재정의 한다.!!!
 */

public class MyListAdapter extends BaseAdapter {
    String TAG;
    Context context;
    SQLiteDatabase db;
    ArrayList<Member> memberList= new ArrayList<Member>();

    public MyListAdapter(Context context) {
        this.context = context;
        MainActivity mainActivity =(MainActivity)context;
        db = mainActivity.db;
        TAG=getClass().getName();
        getList();
    }
    /*Database로 부터 레코드 가져오기*/
    public void getList(){

        String sql = "select * from member";
        Cursor rs = db.rawQuery(sql,null);
        /*memberList 초기화 하기!!*/
        memberList.removeAll(memberList);
        while(rs.moveToNext()){
            int member_id=rs.getInt(rs.getColumnIndex("member_id"));
            String id=rs.getString(rs.getColumnIndex("id"));
            String password=rs.getString(rs.getColumnIndex("password"));
            Log.d(TAG,"id : "+id);
            Log.d(TAG,"password : "+password);

            Member dto = new Member();
            dto.setMember_id(member_id);
            dto.setId(id);
            dto.setPassword(password);
            memberList.add(dto);
        }

    }
    @Override
    public int getCount() {
        return memberList.size();
    }

    @Override
    public Object getItem(int i) {
        return memberList.get(i);
    }

    @Override
    //시퀀스값 교유값 반환
    public long getItemId(int i) {
        return memberList.get(i).getMember_id();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view=null;
        Member member = memberList.get(i);
        /*해당 index에 아이템이 이미 채워져 있다면...*/
        if(convertView!=null) {
            view=convertView;
            MemberItem item=(MemberItem)view;
            item.setMember(member);
        }else{/*해당 index에 아무것도 없는 상태라면*/
            view = new MemberItem(context, member);
        }
        return view;
    }
}
