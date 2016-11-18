package com.sds.study.sqliteapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    String TAG;
    MyHelper myHelper; //데이터베이스 구축
    static SQLiteDatabase db; //데이터베이스 쿼리문 제어
    EditText txt_id;
    EditText txt_password;
    ListView listView;
    static MyListAdapter myListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getName();
        setContentView(R.layout.activity_main);
        init();
        txt_id = (EditText)findViewById(R.id.txt_id);
        txt_password=(EditText)findViewById(R.id.txt_password);
        listView=(ListView) findViewById(R.id.listView);
        myListAdapter = new MyListAdapter(this);
        listView.setAdapter(myListAdapter);
        listView.setOnItemClickListener(this);
    }
    //데이터베이스 초기화
    public void init(){
        myHelper = new MyHelper(this,"iot.sqlite",null,1);
        db= myHelper.getWritableDatabase();
    }
    public void regist(){
        /*이 App이 보유한 SQLite 데이터베이스에 insert*/
        String id = txt_id.getText().toString();
        String password = txt_password.getText().toString();
        String sql="insert into member(id,password) values(?,?)";

        db.execSQL(sql,new String[]{id,password});
        txt_id.setText("");
        txt_password.setText("");
        Log.d(TAG,"등록완료");

        /*ListView 갱신*/
        myListAdapter.getList();
        myListAdapter.notifyDataSetChanged();
    }

    public void btnClick(View view){
        regist();

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, DetailActivity.class);

        TextView member_id=(TextView)view.findViewById(R.id.txt_member_id);
        TextView id=(TextView)view.findViewById(R.id.txt_id);
        TextView password = (TextView)view.findViewById(R.id.txt_password);

        Member member = new Member();
        member.setMember_id(Integer.parseInt((String) member_id.getText()));
        member.setId(id.getText().toString());
        member.setPassword(password.getText().toString());
        Toast.makeText(this,"id:"+password.getText().toString(),Toast.LENGTH_SHORT).show();
        intent.putExtra("member",member);

        startActivity(intent);
    }
}
