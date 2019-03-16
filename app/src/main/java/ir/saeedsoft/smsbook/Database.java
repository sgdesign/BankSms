package ir.saeedsoft.smsbook;


import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
/*هر گونه  خرید و فروش سورس های ینیم سافت درغیر سایت رسمی ینیم سافت غیرقانونی بوده ودارای پیگرد می باشد.
www.saeedsoft.ir */
public class Database extends SQLiteOpenHelper {

    public static final String SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath();
    private final Context mycontext;
    public SQLiteDatabase mydb;
    public static final String DBNAME = "database";
    public static final String DBLOCATION =  "/data/data/" + G.context.getApplicationContext().getPackageName() + "/databases/";


    public Database(Context context) {
        super(context, "database", null, 1);
        mycontext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase arg0) {
        new File(DBLOCATION).mkdirs();

    }


    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }


    public void useable() {
        boolean checkdb = checkdb();
        if (checkdb) {

        } else {
            this.getReadableDatabase();
            copyDatabase(mycontext);
        }
    }


    public void open() {
        mydb = SQLiteDatabase.openDatabase(DBLOCATION + DBNAME, null, SQLiteDatabase.OPEN_READWRITE);
    }


    @Override
    public void close() {
        mydb.close();
    }


    public boolean checkdb() {
        SQLiteDatabase db = null;
        try {
            mydb = SQLiteDatabase.openDatabase(DBLOCATION + DBNAME, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLException e) {

        }
        return mydb != null ? true : false;
    }



    public static boolean copyDatabase(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(DBNAME);
            String outFileName = DBLOCATION + DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.i("COPY", "DB copied");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void Fav_update(String table, String id, String value) {
        ContentValues cv = new ContentValues();
        cv.put("fav", value);
        mydb.update(table, cv, "id='" + id + "'", null);

    }

}