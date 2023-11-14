package com.example.playlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "shuttle.db";
    private static final String TABLE_NAME = "shuttle_table";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "NAME";
    private static final String COL_3 = "MARKS";

    // 新增用户表
    private static final String USER_TABLE_NAME = "shuttle_user";
    private static final String USER_COL_1 = "ID";
    private static final String USER_COL_2 = "USERNAME";
    private static final String USER_COL_3 = "PASSWORD"; // 存储加密后的密码

    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建原有表
        db.execSQL("CREATE TABLE " + TABLE_NAME + " " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT," +
                "MARKS INTEGER)");

        // 创建用户表
        db.execSQL("CREATE TABLE " + USER_TABLE_NAME + " " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "USERNAME TEXT," +
                "PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 删除旧表并重新创建
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        onCreate(db);
    }

    public static String getMD5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


    public Cursor getAllUserData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + USER_TABLE_NAME, null);
        return res;
    }

    // 插入用户数据，密码使用MD5加密
    public boolean insertUserData(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COL_2, username);
        contentValues.put(USER_COL_3, md5(password)); // 使用md5加密密码
        long result = db.insert(USER_TABLE_NAME, null, contentValues);
        return result != -1;
    }

    // 检查用户是否存在
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {USER_COL_1};
        String selection = USER_COL_2 + " = ? AND " + USER_COL_3 + " = ?";
        String[] selectionArgs = {username, md5(password)};
        Cursor cursor = db.query(USER_TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    // 将字符串使用MD5加密
    private String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            Log.e("DatabaseHelper", "MD5 encryption failed", e);
            return null;
        }
    }
}
