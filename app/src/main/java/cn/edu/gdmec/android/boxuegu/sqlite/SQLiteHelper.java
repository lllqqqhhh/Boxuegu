package cn.edu.gdmec.android.boxuegu.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by student on 17/12/26.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "bxg.db";
    public static final String U_USERINFO = "userinfo";
    public static final String U_VIDEO_PLAY_LIST = "videoplaylist";

    public SQLiteHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    //创建数据库
    @Override
    public void onCreate(SQLiteDatabase db){
        //创建用户信息表
        db.execSQL("CREATE TABLE IF NOT EXISTS " + U_USERINFO + "( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "userName VARCHAR,"//用户名
                + "nickName VARCHAR," //昵称
                + "sex VARCHAR,"       //性bie
                + "signature VARCHAR"//签名
                + ")" );


        //创建视频播放记录
        db.execSQL("CREATE TABLE IF NOT EXISTS " + U_VIDEO_PLAY_LIST + "( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "userName VARCHAR,"//用户名
                + "chapterId INT," //章节id
                + "videoId INT,"//小节id
                + "videoPath VARCHAR,"
                + "title VARCHAR,"  //章节名字
                + "secondTitle VARCHAR" // 视频名字
                + ")");
    }

    //数据库升级 版本号增加 升级调用此方法

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF NOT EXISTS " + U_USERINFO);
        db.execSQL("DROP TABLE IF NOT EXISTS " + U_VIDEO_PLAY_LIST);
        onCreate(db);

    }
}