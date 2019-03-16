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
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerAdapterFavorite extends RecyclerView.Adapter<RecyclerAdapterFavorite.RecyclerViewHolder> {
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

    public RecyclerAdapterFavorite(ArrayList<StructData> names) {
        this.names = names;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = G.inflater.from(viewGroup.getContext()).inflate(R.layout.item_layout_fav, viewGroup, false);
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
                Toast.makeText(G.currentActivity, "متن مورد نظر در کلیپورد ذخیره شد.", Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerViewHolder.lnrShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, name.name);
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                G.currentActivity.startActivity(Intent.createChooser(sharingIntent, "Share using"));
            }
        });


        if (name.fav.equals("1")) {
            RecyclerViewHolder.imgFav.setImageResource(R.drawable.ic_delete);
        } else {
        }
        RecyclerViewHolder.lnrFav.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                G.selectedApplication = name;

                G.db.open();
                if (name.fav.equals("1")) {
                    G.db.Fav_update("tbl_sms", name.id, "0");

                    name.fav = "0";
                    Snackbar.make(v, "پیامک مورد نظر از علاقه مندی ها حذف شد", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    ActivityFavorite.loadAllContent();
                    ActivityFavorite.adapter.notifyDataSetChanged();
                } else {
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