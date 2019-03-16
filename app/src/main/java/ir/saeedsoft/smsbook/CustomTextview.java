package ir.saeedsoft.smsbook;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

/*هر گونه  خرید و فروش سورس های ینیم سافت درغیر سایت رسمی ینیم سافت غیرقانونی بوده ودارای پیگرد می باشد.
www.saeedsoft.ir */
public class CustomTextview extends TextView{
    Typeface font = Typeface.createFromAsset(getResources().getAssets(), "fonts/iran.ttf");

    public CustomTextview(Context context) {
        super(context);
        if (!isInEditMode())
            setTypeface(this.font);
    }

    public CustomTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode())
            setTypeface(this.font);
    }

    public CustomTextview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode())
            setTypeface(this.font);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomTextview(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        if (!isInEditMode())
            setTypeface(this.font);
    }



}
