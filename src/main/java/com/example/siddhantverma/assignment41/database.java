package com.example.siddhantverma.assignment41;

/**
 * Created by Siddhant Verma on 09-Nov-16.
 */

import android.database.sqlite.SQLiteOpenHelper;


        import android.content.ContentValues;
        import android.content.Context;
        import android.content.pm.PackageInfo;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteDatabaseLockedException;
        import android.database.sqlite.SQLiteOpenHelper;

import com.example.siddhantverma.assignment41.model.Listitem;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Siddhant Verma on 02-Oct-16.
 */
public class database extends SQLiteOpenHelper {
    public static final String Dbname="toolist.db";
    public static final String Tbname="todo";
    public static final String col1="ID";
    public static final String col2="Title";
    public static final String col3="Text";


    public database(Context context)
    {
        super(context,Dbname,null,1);
        SQLiteDatabase db=this.getWritableDatabase();


    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table "+Tbname+"(ID INTEGER PRIMARY KEY AUTOINCREMENT ,title TEXT, Desc TEXT)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldversion,int newversion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+ Tbname);
        onCreate(db);
    }

    public boolean addData(String id,String uname)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        int i=0;
        contentValues.put(col1,++i);
        contentValues.put(col2,id);
        contentValues.put(col3,uname);

        long output=db.insert(Tbname,null,contentValues);
        if(output==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public ArrayList<Listitem> getAllData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("Select * from "+Tbname,null);
        ArrayList<Listitem> mArrayList = new ArrayList<>();
        for(res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
            // The Cursor is now set to the right position
            Listitem item = new Listitem(UUID.fromString(res.getString(res.getColumnIndex(col1))),
                    res.getString(res.getColumnIndex(col2)),
                    res.getString(res.getColumnIndex(col3)));
            mArrayList.add(item);
        }
        return mArrayList;



    }
//    public boolean update(String id,String uname, String Age, String email)
//    {
//        SQLiteDatabase db=this.getWritableDatabase();
//        ContentValues contentValues=new ContentValues();
//        contentValues.put(col1,id);
//        contentValues.put(col2,uname);
//        contentValues.put(col3,Age);
//        contentValues.put(col4,email);
//        db.update(Tbname,contentValues,"ID= ?", new String[]{id});
//        return true;
//
//    }
//    public Integer delete(String id)
//    {
//        SQLiteDatabase db=this.getWritableDatabase();
//        return  db.delete(Tbname,"ID=?",new String []{id});
//
//    }
}
