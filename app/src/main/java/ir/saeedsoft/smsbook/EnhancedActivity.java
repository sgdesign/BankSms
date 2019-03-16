package ir.saeedsoft.smsbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class EnhancedActivity extends AppCompatActivity {
    @Override
    protected void onResume() {

        G.currentActivity = this;
        super.onResume();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}

/*هر گونه  خرید و فروش سورس های ینیم سافت درغیر سایت رسمی ینیم سافت غیرقانونی بوده ودارای پیگرد می باشد.
www.saeedsoft.ir */