package com.magaofei.shiyan4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.sqlite.*;
import android.view.View;
import android.database.Cursor;
import android.widget.Toast;
import android.content.ContentValues;
import android.widget.ListView;
import java.util.List;
import java.util.ArrayList;
import android.widget.BaseAdapter;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MyOpenHelper myOpenHelper;
    private List<Student> lists;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        myOpenHelper = new MyOpenHelper(getApplicationContext());
        lists = new ArrayList<Student>();
    }

    public void insert(View v) {
        SQLiteDatabase db = myOpenHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", "张三");
        values.put("phone", "135898877");
        long insert = db.insert("info", null, values);
        if (insert > 0) {
            Toast.makeText(getApplicationContext(), "插入" + insert + "成功", 1).show();
        } else {
            Toast.makeText(getApplicationContext(), "插入失败", 1).show();
        }

        db.close();

    }

    public void delete(View v) {
        SQLiteDatabase db = myOpenHelper.getReadableDatabase();
        int delete = db.delete("info", "name=?", new String[]{"张三"});
        if (delete > 0) {
            Toast.makeText(getApplicationContext(), "删除" + delete + "成功", 1).show();
        } else {
            Toast.makeText(getApplicationContext(), "删除失败", 1).show();
        }
        db.close();
    }

    public void update(View v) {
        SQLiteDatabase db = myOpenHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("phone", "114");
        int update = db.update("info", values, "name = ?", new String[]{"张三"});
        if (update > 0) {
            Toast.makeText(getApplicationContext(), "更新" + update + "成功", 1).show();
        } else {
            Toast.makeText(getApplicationContext(), "更新失败", 1).show();
        }
        db.close();
    }

    public void find(View v) {
        SQLiteDatabase db = myOpenHelper.getReadableDatabase();
        ContentValues values = new ContentValues();

        Cursor cursor = db.query("info", null, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
                Student s = new Student();
                s.setName(name);
                s.setPhone(phone);
                lists.add(s);

            }
            lv.setAdapter(new MyAdapter());
        }
        db.close();

    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            Toast.makeText(getApplicationContext(), lists.size()+"", 1).show();
            return lists.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            View view;
            if (convertView == null) {
                view = View.inflate(getApplicationContext(), R.layout.item, null);
            }else {
                view = convertView;
            }
            TextView nameView = (TextView) view.findViewById(R.id.tv1);
            TextView phoneView = (TextView) view.findViewById(R.id.tv2);
            Student student = lists.get(position);

            nameView.setText(student.getName());
            phoneView.setText(student.getPhone());
            System.out.println(student.getName());
            return view;
        }

    }


}


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        myOpenHelper = new MyOpenHelper(getApplicationContext());
//
//    }
//
//    public void add(View v) {
//
//        //获取数据库对象
//        SQLiteDatabase db = myOpenHelper.getReadableDatabase();
//        //执行增加一条的sql语句
//        ContentValues values = new ContentValues();
//        values.put("name", "王五");
//        values.put("phone", "13888888");
//        long insert = db.insert("myTab", null, values);
//        //关闭数据库
//        db.close();
//        if (insert > 0) {
//            Toast.makeText(getApplicationContext(), "插入成功", 1).show();
//
//        }else {
//            Toast.makeText(getApplicationContext(), "插入失败", 1).show();
//        }
//
//    }
//    public void delete(View v) {
//        //获取数据库对象
//        SQLiteDatabase db = myOpenHelper.getReadableDatabase();
//        //执行增加一条的sql语句
//        int delete = db.delete("myTab", "name = ?", new String[]{"王五"});
//        db.close();
//        if (delete>0) {Toast.makeText(getApplicationContext(), "删除成功"+delete, 1).show();
//
//        }else {
//            Toast.makeText(getApplicationContext(), "删除失败", 1).show();
//        }
//
//    }
//    public void update(View v) {
//        //获取数据库对象
//        SQLiteDatabase db = myOpenHelper.getReadableDatabase();
//        //执行增加一条的sql语句
//        ContentValues values = new ContentValues();
//        values.put("phone", "114");
//        int update = db.update("myTab", values, "name=?", new String[]{"王五"});
//        if (update >0 ) {
//            Toast.makeText(getApplicationContext(), "更新成功"+update, 1).show();
//
//        }else {
//            Toast.makeText(getApplicationContext(), "更新失败", 1).show();
//        }
//        db.close();
//
//    }
//    public void find(View v) {
//        //获取数据库对象
//        SQLiteDatabase db = myOpenHelper.getReadableDatabase();
//        Cursor cursor = db.query("myTab", new String[]{"phone"}, "name = ?", new String[]{"王五"}, null, null, null);
//
//
//        if (cursor != null && cursor.getCount()>0) {
//            while (cursor.moveToNext()) {
//
//                String phone = cursor.getString(0);
//                Toast.makeText(getApplicationContext(), "phone:"+phone, 1).show();;
//            }					}
//
//
//    }



//}
