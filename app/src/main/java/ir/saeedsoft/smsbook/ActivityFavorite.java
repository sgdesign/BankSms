package ir.saeedsoft.smsbook;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ActivityFavorite extends EnhancedActivity {
    private String id = null;
    String name = null;


    public Database db;
    public RecyclerView recyclerView;
    public static RecyclerAdapterFavorite adapter;
    public static ArrayList<StructData> data = new ArrayList<StructData>();
    public static SQLiteDatabase database;

    public LinearLayout toolbar_category;

    @Override
    protected void onResume() {


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(G.preference.getInt("COLOR_STATUS", R.color.status_bar_color_pink)));
        }
        toolbar_category.setBackgroundColor(Color.parseColor(G.preference.getString("COLOR", "#03A9F4")));
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);


        toolbar_category = (LinearLayout) findViewById(R.id.toolbar_category);
        toolbar_category.setBackgroundColor(Color.parseColor(G.preference.getString("COLOR", "#03A9F4")));

        ImageView imgMenu = (ImageView) toolbar_category.findViewById(R.id.imgMenu);
        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        CustomTextview txtName=(CustomTextview)toolbar_category.findViewById(R.id.txtName);
        txtName.setText(G.context.getResources().getText(R.string.favorite));

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager manager = new GridLayoutManager(G.context, 2);
        manager.setSpanSizeLookup(
                new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        //Stagger every other row
                        return (position % 2 == 0 ? 2 : 2);
                    }
                });
        manager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new RecyclerAdapterFavorite(data);
        recyclerView.setAdapter(adapter);

        db = new Database(G.context);
        loadAllContent();
        adapter.notifyDataSetChanged();


    }

    public static void loadAllContent() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                data.clear();
                database = SQLiteDatabase.openDatabase(Database.DBLOCATION + Database.DBNAME, null, SQLiteDatabase.OPEN_READWRITE);
                Cursor cu = database.rawQuery("select * from tbl_sms where fav=1", null);
                while (cu.moveToNext()) {
                    StructData array = new StructData();
                    array.id = cu.getString(cu.getColumnIndex("id"));
                    array.name = cu.getString(cu.getColumnIndex("matn"));
                    array.fav = cu.getString(cu.getColumnIndex("fav"));
                    array.read = cu.getString(cu.getColumnIndex("read"));

                    Log.i("LOG", "name:" + array.name);

                    data.add(array);


                }
                cu.close();


                G.HANDLER.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });


            }

        });
        thread.start();

    }


}
/*هر گونه  خرید و فروش سورس های ینیم سافت درغیر سایت رسمی ینیم سافت غیرقانونی بوده ودارای پیگرد می باشد.
www.saeedsoft.ir */