package ir.saeedsoft.smsbook;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapterSms extends RecyclerView.Adapter<RecyclerAdapterSms.RecyclerViewHolder> {
    public Database db;

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        protected TextView txtName;
        protected ImageView imgLogo;
        protected ImageView imgFav;
        protected CardView crd;
        protected ViewGroup layoutRoot;
        protected ViewGroup lnrCopy;
        protected ViewGroup lnrShare;
        protected ViewGroup lnrSms;
        protected ViewGroup lnrFav;


        public RecyclerViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            imgLogo = (ImageView) itemView.findViewById(R.id.imgLogo);
            layoutRoot = (ViewGroup) itemView.findViewById(R.id.layoutRoot);
            crd = (CardView) itemView.findViewById(R.id.crd);
            ////////////////////////////////////////////////////////////////////
            lnrCopy = (ViewGroup) itemView.findViewById(R.id.lnrCopy);
            lnrShare = (ViewGroup) itemView.findViewById(R.id.lnrShare);
            lnrSms = (ViewGroup) itemView.findViewById(R.id.lnrSms);
            lnrFav = (ViewGroup) itemView.findViewById(R.id.lnrFav);
            imgFav = (ImageView) itemView.findViewById(R.id.imgFav);
        }
    }

    private ArrayList<StructData> names = new ArrayList<StructData>();

    public RecyclerAdapterSms(ArrayList<StructData> names) {
        this.names = names;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = G.inflater.from(viewGroup.getContext()).inflate(R.layout.item_layout_sms, viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder RecyclerViewHolder, final int i) {
        final StructData name = names.get(i);
        RecyclerViewHolder.txtName.setText(name.name);
        Typeface tff = Typeface.createFromAsset(G.context.getAssets(), "fonts/" + G.preference.getString("FONT", "iransans.ttf"));
        RecyclerViewHolder.txtName.setTypeface(tff);
        RecyclerViewHolder.txtName.setTextSize(G.preference.getInt("SLIDER_SIZE", 19));

        RecyclerViewHolder.lnrCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) G.context.getSystemService(G.context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", name.name);
                clipboard.setPrimaryClip(clip);
                Snackbar.make(v, "پیامک مورد نظر ذخیره شد.", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });

        RecyclerViewHolder.lnrShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, name.name);
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject");
                G.currentActivity.startActivity(Intent.createChooser(sharingIntent, "Share using"));
            }
        });



        if (name.fav.equals("1")) {
            RecyclerViewHolder.imgFav.setImageResource(R.drawable.fav_on);
        } else {
            RecyclerViewHolder.imgFav.setImageResource(R.drawable.fav_off);
        }
        RecyclerViewHolder.lnrFav.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                G.selectedApplication = name;

                G.db.open();
                if (name.fav.equals("1")) {
                    RecyclerViewHolder.imgFav.setImageResource(R.drawable.fav_off);
                    G.db.Fav_update("tbl_sms", name.id, "0");

                    name.fav = "0";
                    Snackbar.make(v, "آیتم مورد نظر از علاقه مندی ها حذف شد", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                } else {
                    RecyclerViewHolder.imgFav.setImageResource(R.drawable.fav_on);
                    G.db.Fav_update("tbl_sms", name.id, "1");

                    name.fav = "1";
                    Snackbar.make(v, "آیتم مورد نظر به علاقه مندی ها اضافه شد", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }

                G.db.close();

            }
        });

    }

    @Override
    public int getItemCount() {

        return names.size();
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
/*هر گونه  خرید و فروش سورس های ینیم سافت درغیر سایت رسمی ینیم سافت غیرقانونی بوده ودارای پیگرد می باشد.
www.saeedsoft.ir */