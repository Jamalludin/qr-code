package com.mediatama.fortuna.qrcode.presenter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mediatama.fortuna.qrcode.model.Tamu;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase tamu;
    private static DatabaseAccess access;

    public DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (access == null) {
            access = new DatabaseAccess(context);
        }
        return access;
    }

    public void open() {
        this.tamu = openHelper.getWritableDatabase();
    }

    public void close() {
        if (tamu != null) {
            this.tamu.close();
        }
    }

    public Tamu getUndangan(String code) {
        Tamu tamuUndangan = new Tamu();

        Cursor cursor = tamu.rawQuery("select * from undangan where id = '" + code + "'", new String[]{});
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                tamuUndangan = new Tamu(cursor.getString(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("nik")),
                        cursor.getString(cursor.getColumnIndex("nama")),
                        cursor.getString(cursor.getColumnIndex("alamat")),
                        cursor.getString(cursor.getColumnIndex("hp")),
                        cursor.getString(cursor.getColumnIndex("status")));
            }

        }

        cursor.close();
        return tamuUndangan;
    }

    public void update(String code) {
        tamu.execSQL("update undangan set status = '1' where id = '" + code + "'", new String[]{});
    }
}
