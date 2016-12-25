package com.magaofei.shiyan4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.sqlite.*;
import android.view.View;
import android.database.Cursor;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyOpenHelper myOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myOpenHelper = new MyOpenHelper(getApplicationContext());

    }

    public void add(View v) {

        //获取数据库对象
        SQLiteDatabase db = myOpenHelper.getReadableDatabase();
        //执行增加一条的sql语句
        db.execSQL("insert into myTab(name,phone) values (?,?)", new Object[]{"张三","1235858948"} );
        //关闭数据库
        db.close();

    }
    public void delete(View v) {
        //获取数据库对象
        SQLiteDatabase db = myOpenHelper.getReadableDatabase();
        //执行增加一条的sql语句
        db.execSQL("delete from myTab where name = ?", new Object[]{"wangwu"} );
        db.close();

    }
    public void update(View v) {
        //获取数据库对象
        SQLiteDatabase db = myOpenHelper.getReadableDatabase();
        //执行增加一条的sql语句
        db.execSQL("update myTab set phone=? where name = ?", new Object[]{"1356666666","张三"} );
        db.close();

    }
    public void find(View v) {
        //获取数据库对象
        SQLiteDatabase db = myOpenHelper.getReadableDatabase();
        //执行增加一条的sql语句
        Cursor cursor = db.rawQuery("select * from myTab" , null);

        if (cursor != null && cursor.getCount()>0) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(1);
                String phoneString = cursor.getString(2);
                Toast.makeText(getApplicationContext(), "name"+name + "phone:"+phoneString, 1).show();;
            }

        }


    }



}
