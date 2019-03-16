package ir.saeedsoft.smsbook;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rey.material.widget.Slider;
import com.rey.material.widget.Spinner;

import java.util.ArrayList;
import java.util.List;



public class ActivitySetting extends EnhancedActivity {
    private TextView txtTest;
    private Spinner sp, sp2;
    private Toolbar toolbar;

    @Override
    protected void onResume() {
        toolbar.setBackgroundColor(Color.parseColor(G.preference.getString("COLOR", "#03A9F4")));

        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        final Slider slider = (Slider) findViewById(R.id.sl);
        txtTest = (TextView) findViewById(R.id.txtTest);
        txtTest.setTextSize(G.preference.getInt("SLIDER_SIZE", 19));
        slider.setValue(G.preference.getInt("SLIDER_SIZE", 19), true);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
       toolbar.setNavigationIcon(R.drawable.ic_back_arrow);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setTitle(G.context.getString(R.string.settings));
        toolbar.setBackgroundColor(Color.parseColor(G.preference.getString("COLOR", "#03A9F4")));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final SharedPreferences.Editor editor = G.preference.edit();

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(G.preference.getInt("COLOR_STATUS", R.color.status_bar_color_pink)));
        }


        slider.setOnPositionChangeListener(new Slider.OnPositionChangeListener() {

            @Override
            public void onPositionChanged(Slider view, boolean fromUser, float oldPos, float newPos, int oldValue, int newValue) {

                txtTest.setTextSize(newValue);
                SharedPreferences.Editor editor2 = G.preference.edit();
                editor2.putInt("SLIDER_SIZE", slider.getValue());
                editor2.commit();
            }
        });

        sp = (Spinner) findViewById(R.id.sp);
        final ArrayAdapter<String> adapter;
        List<String> list;
        final List<String> list2;

        list = new ArrayList<String>();
        list.add("فونت پیش فرض");
        list.add("فونت دست نویس");
        list.add("فونت نازنین");
        list.add("فونت یکان");
        list.add("فونت تیتر");

        list2 = new ArrayList<String>();
        list2.add("iransans.ttf");
        list2.add("dastnavis.otf");
        list2.add("bnazanin.ttf");
        list2.add("yekan.ttf");
        list2.add("btitr.ttf");
        adapter = new ArrayAdapter<String>(G.context, R.layout.spinner_item, list);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(Spinner parent, View view, int position, long id) {

                switch (position) {
                    case 0:

                        editor.putString("FONT", "iransans.ttf");
                        editor.commit();
                        Typeface tff = Typeface.createFromAsset(G.context.getAssets(), "fonts/" + G.preference.getString("FONT", "iransans.ttf"));
                        txtTest.setTypeface(tff);
                        break;
                    case 1:
                        editor.putString("FONT", "dastnavis.otf");
                        editor.commit();
                        Typeface tff2 = Typeface.createFromAsset(G.context.getAssets(), "fonts/" + G.preference.getString("FONT", "iransans.ttf"));
                        txtTest.setTypeface(tff2);
                        break;
                    case 2:
                        editor.putString("FONT", "bnazanin.ttf");
                        editor.commit();
                        Typeface tff3 = Typeface.createFromAsset(G.context.getAssets(), "fonts/" + G.preference.getString("FONT", "iransans.ttf"));
                        txtTest.setTypeface(tff3);
                        break;
                    case 3:
                        editor.putString("FONT", "yekan.ttf");
                        editor.commit();
                        Typeface tff4 = Typeface.createFromAsset(G.context.getAssets(), "fonts/" + G.preference.getString("FONT", "iransans.ttf"));
                        txtTest.setTypeface(tff4);
                        break;
                    case 4:
                        editor.putString("FONT", "btitr.ttf");
                        editor.commit();
                        Typeface tff5 = Typeface.createFromAsset(G.context.getAssets(), "fonts/" + G.preference.getString("FONT", "iransans.ttf"));
                        txtTest.setTypeface(tff5);
                        break;
                }


            }
        });
        String c = G.preference.getString("FONT", "iransans.ttf");
        if (c.equals("iransans.ttf")) {
            sp.setSelection(0);
            Typeface tff = Typeface.createFromAsset(G.context.getAssets(), "fonts/" + c);
            txtTest.setTypeface(tff);
        } else if (c.equals("dastnavis.otf")) {
            sp.setSelection(1);
            Typeface tff = Typeface.createFromAsset(G.context.getAssets(), "fonts/" + c);
            txtTest.setTypeface(tff);
        } else if (c.equals("bnazanin.ttf")) {
            sp.setSelection(2);
            Typeface tff = Typeface.createFromAsset(G.context.getAssets(), "fonts/" + c);
            txtTest.setTypeface(tff);
        } else if (c.equals("yekan.ttf")) {
            sp.setSelection(3);
            Typeface tff = Typeface.createFromAsset(G.context.getAssets(), "fonts/" + c);
            txtTest.setTypeface(tff);
        } else if (c.equals("btitr.ttf")) {

            Typeface tff = Typeface.createFromAsset(G.context.getAssets(), "fonts/" + c);
            txtTest.setTypeface(tff);
            sp.setSelection(4);
        }

        final SharedPreferences.Editor editor3 = G.preference.edit();
        final SharedPreferences.Editor editor4 = G.preference.edit();
        sp2 = (Spinner) findViewById(R.id.sp2);
        final ArrayAdapter<String> adapter2;
        List<String> list3;
        final List<String> list4;

        list3 = new ArrayList<String>();
        list3.add("صورتی");
        list3.add("قرمز");
        list3.add("سبز");
        list3.add("خاکستری");
        list3.add("نارنجی");

        list4 = new ArrayList<String>();
        list4.add("blue");
        list4.add("red");
        list4.add("green");
        list4.add("orange");
        list4.add("yellow");
        adapter2 = new ArrayAdapter<String>(G.context, R.layout.spinner_item, list3);
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);
        sp2.setAdapter(adapter2);

        sp2.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(Spinner parent, View view, int position, long id) {

                switch (position) {
                    case 0:

                        editor3.putString("COLOR", "#03A9F4");//pink
                        editor3.commit();

                        editor4.putInt("COLOR_STATUS", R.color.status_bar_color_pink);
                        editor4.commit();
                        toolbar.setBackgroundColor(Color.parseColor(G.preference.getString("COLOR", "#2196F3")));
                        change_status_color();

                        break;
                    case 1:
                        editor3.putString("COLOR", "#f44336");//red
                        editor3.commit();

                        editor4.putInt("COLOR_STATUS", R.color.status_bar_color_red);
                        editor4.commit();
                        toolbar.setBackgroundColor(Color.parseColor(G.preference.getString("COLOR", "#03A9F4")));
                        change_status_color();
                        break;
                    case 2:
                        editor3.putString("COLOR", "#009688");//teal
                        editor3.commit();

                        editor4.putInt("COLOR_STATUS", R.color.status_bar_color_teal);
                        editor4.commit();
                        toolbar.setBackgroundColor(Color.parseColor(G.preference.getString("COLOR", "#009688")));
                        change_status_color();
                        break;

                    case 3:
                        editor3.putString("COLOR", "#607D8B");//blue grey
                        editor3.commit();

                        editor4.putInt("COLOR_STATUS", R.color.status_bar_color_blue_grey);
                        editor4.commit();
                        toolbar.setBackgroundColor(Color.parseColor(G.preference.getString("COLOR", "#607D8B")));
                        change_status_color();
                        break;
                    case 4:
                        editor3.putString("COLOR", "#FF9800");//orange
                        editor3.commit();

                        editor4.putInt("COLOR_STATUS", R.color.status_bar_color_orange);
                        editor4.commit();
                        toolbar.setBackgroundColor(Color.parseColor(G.preference.getString("COLOR", "#FF9800")));
                        change_status_color();
                        break;
                }


            }
        });
        String c2 = G.preference.getString("COLOR", "#03A9F4");
        if (c2.equals("#2196F3")) {
            sp2.setSelection(0);
        } else if (c2.equals("#f44336")) {
            sp2.setSelection(1);
        } else if (c2.equals("#009688")) {
            sp2.setSelection(2);
        } else if (c2.equals("#607D8B")) {
            sp2.setSelection(3);
        } else if (c2.equals("#FF9800")) {
            sp2.setSelection(4);
        }

    }

    public void change_status_color() {

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(G.preference.getInt("COLOR_STATUS", R.color.status_bar_color_pink)));
        }
    }

}
/*هر گونه  خرید و فروش سورس های ینیم سافت درغیر سایت رسمی ینیم سافت غیرقانونی بوده ودارای پیگرد می باشد.
www.saeedsoft.ir */






