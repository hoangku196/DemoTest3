package com.example.demotest3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class SQLiteForDienThoai extends SQLiteOpenHelper {
    private Context context;

    public SQLiteForDienThoai(Context context) {
        super(context, "dienthoai.db", null, 1);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE DIENTHOAI(NHANHIEU TEXT PRIMARY KEY, GIA FLOAT, SOLUONG INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addDienThoai(DienThoai dienThoai) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NHANHIEU", dienThoai.getNhanHieu());
        contentValues.put("GIA", dienThoai.getGia());
        contentValues.put("SOLUONG", dienThoai.getSoLuong());

        long check = sqLiteDatabase.insert("DIENTHOAI", null, contentValues);

        if (check>0){
            Toast.makeText(context,"Thành công",Toast.LENGTH_SHORT).show();
        }

        sqLiteDatabase.close();
    }

    public ArrayList<DienThoai> listDienThoai() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM DIENTHOAI", null);

        ArrayList<DienThoai> listDienThoai = new ArrayList<>();

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {
                String nhanhieu = cursor.getString(0);
                float gia = cursor.getFloat(1);
                int soluong = cursor.getInt(2);
                listDienThoai.add(new DienThoai(nhanhieu, gia, soluong));

                cursor.moveToNext();
            }
        }
        cursor.close();
        sqLiteDatabase.close();

        return listDienThoai;
    }

    public ArrayList<String> listNhanHieu(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT NHANHIEU FROM DIENTHOAI", null);

        ArrayList<String> listNhanHieu = new ArrayList<>();

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (cursor.isAfterLast() != false) {
                String nhanhieu = cursor.getString(0);
                listNhanHieu.add(nhanhieu);

                cursor.moveToNext();
            }
        }
        cursor.close();
        sqLiteDatabase.close();

        return listNhanHieu;
    }

    public void updateDienThoai(String nhanHieu, float gia) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("NHANHIEU", nhanHieu);
        contentValues.put("GIA", gia);

        sqLiteDatabase.update("DIENTHOAI", contentValues, "NHANHIEU=?", new String[]{nhanHieu});
        sqLiteDatabase.close();
    }

    public void deleteDienThoai(String nhanHieu) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete("DIENTHOAI", "NHANHIEU=?", new String[]{nhanHieu});
        sqLiteDatabase.close();
    }
}
