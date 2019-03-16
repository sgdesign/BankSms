package ir.saeedsoft.smsbook;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import co.ronash.pushe.Pushe;
import com.yalantis.guillotine.animation.GuillotineAnimation;

import java.util.ArrayList;


public class ActivityMain extends EnhancedActivity {
    private static final long RIPPLE_DURATION = 250;


    Toolbar toolbar;
    FrameLayout root;
    View contentHamburger;

    public Database db;
    public RecyclerView recyclerView;
    public RecyclerAdapterCategory adapter;
    public ArrayList<StructData> data = new ArrayList<StructData>();
    public static SQLiteDatabase database;
    private Toolbar toolbar_guillotine;
    private View guillotineMenu;

    @Override
    protected void onResume() {


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(G.preference.getInt("COLOR_STATUS", R.color.status_bar_color_pink)));
        }
        toolbar.setBackgroundColor(Color.parseColor(G.preference.getString("COLOR", "#03A9F4")));
        toolbar_guillotine.setBackgroundColor(Color.parseColor(G.preference.getString("COLOR", "#03A9F4")));
        guillotineMenu.setBackgroundColor(Color.parseColor(G.preference.getString("COLOR", "#03A9F4")));
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Pushe.initialize(this,true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        root = (FrameLayout) findViewById(R.id.root);
        contentHamburger = (View) findViewById(R.id.content_hamburger);

        toolbar.setBackgroundColor(Color.parseColor(G.preference.getString("COLOR", "#03A9F4")));
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(null);

        }

        guillotineMenu = LayoutInflater.from(this).inflate(R.layout.guillotine, null);
        root.addView(guillotineMenu);
        guillotineMenu.setBackgroundColor(Color.parseColor(G.preference.getString("COLOR", "#03A9F4")));
        new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), contentHamburger)

                .setStartDelay(RIPPLE_DURATION)
                .setActionBarViewForAnimation(toolbar)
                .setClosedOnStart(true)
                .build()
        ;

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(G.preference.getInt("COLOR_STATUS", R.color.status_bar_color_pink)));
        }


        toolbar_guillotine = (Toolbar) guillotineMenu.findViewById(R.id.toolbar_guillotine);
        toolbar_guillotine.setBackgroundColor(Color.parseColor(G.preference.getString("COLOR", "#03A9F4")));
        /////////////////////slider menu/////////////////////////////////////////
        LinearLayout message_group = (LinearLayout) guillotineMenu.findViewById(R.id.message_group);
        message_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMain.this, ActivityMain.class);
                ActivityMain.this.startActivity(intent);
                G.currentActivity.finish();
            }
        });

        LinearLayout favorite_group = (LinearLayout) guillotineMenu.findViewById(R.id.favorite_group);
        favorite_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMain.this, ActivityFavorite.class);
                ActivityMain.this.startActivity(intent);
            }
        });


        LinearLayout settings_group = (LinearLayout) guillotineMenu.findViewById(R.id.settings_group);
        settings_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMain.this, ActivitySetting.class);
                ActivityMain.this.startActivity(intent);
            }
        });

        LinearLayout exit_group = (LinearLayout) guillotineMenu.findViewById(R.id.exit_group);
        exit_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);


        final GridLayoutManager manager = new GridLayoutManager(G.context, 4);
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
        adapter = new RecyclerAdapterCategory(data);
        recyclerView.setAdapter(adapter);



        db = new Database(G.context);
        loadAllContent();
        adapter.notifyDataSetChanged();


    }

    public void loadAllContent() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                data.clear();
                database = SQLiteDatabase.openDatabase(Database.DBLOCATION + Database.DBNAME, null, SQLiteDatabase.OPEN_READWRITE);
                Cursor cu = database.rawQuery("select * from tbl_category", null);
                while (cu.moveToNext()) {
                    StructData array = new StructData();
                    array.id = cu.getString(cu.getColumnIndex("id"));
                    array.name = cu.getString(cu.getColumnIndex("name"));
                    array.image = cu.getString(cu.getColumnIndex("image"));
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