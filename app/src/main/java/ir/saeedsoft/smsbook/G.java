package ir.saeedsoft.smsbook;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.view.LayoutInflater;

import java.util.ArrayList;

/*هر گونه  خرید و فروش سورس های ینیم سافت درغیر سایت رسمی ینیم سافت غیرقانونی بوده ودارای پیگرد می باشد.
www.saeedsoft.ir */
public class G extends Application {
    public static final Handler HANDLER = new Handler();
    public static Context context;
    public static LayoutInflater inflater;
    public static ArrayList<StructData> datas = new ArrayList<StructData>();
    public static ArrayList<StructData> data = new ArrayList<StructData>();
    public static Activity currentActivity;
    public static Database db;
    public static SQLiteDatabase database;
    public static StructData selectedApplication;
    public static SharedPreferences preference;

    public static Integer text_size;
    public static String color;
    public static int color_status;
    @Override
    public void onCreate() {

        super.onCreate();

        context = getApplicationContext();
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        preference = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        text_size = preference.getInt("TEXT_SIZE", 17);
        color = preference.getString("COLOR", "#03A9F4");
        color_status = preference.getInt("COLOR_STATUS", R.color.status_bar_color_pink);




        db = new Database(this);
        db.useable();
        db.open();
    }

}
