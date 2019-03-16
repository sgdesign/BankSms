package ir.saeedsoft.smsbook;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapterCategory extends RecyclerView.Adapter<RecyclerAdapterCategory.RecyclerViewHolder> {
    public Database db;

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        protected TextView txtName;
        protected ImageView imgLogo;
        protected CardView crd;
        protected ViewGroup layoutRoot;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
           imgLogo = (ImageView) itemView.findViewById(R.id.imgLogo);
            layoutRoot = (ViewGroup) itemView.findViewById(R.id.layoutRoot);
            crd = (CardView) itemView.findViewById(R.id.crd);
        }
    }

    private ArrayList<StructData> names = new ArrayList<StructData>();

    public RecyclerAdapterCategory(ArrayList<StructData> names) {
        this.names = names;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = G.inflater.from(viewGroup.getContext()).inflate(R.layout.item_layout_category, viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder RecyclerViewHolder, final int i) {
        final StructData name = names.get(i);
        RecyclerViewHolder.txtName.setText(name.name);
        final int imageId = G.context.getResources().getIdentifier( name.image, "drawable", G.context.getPackageName());
        RecyclerViewHolder.imgLogo.setImageResource(imageId);

        RecyclerViewHolder.crd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                G.selectedApplication = name;
                Intent intent = new Intent(G.currentActivity, ActivitySubCategory.class);
                intent.putExtra("ID", name.id);
                Log.i("LOG", "id:" + name.id);

                intent.putExtra("NAME", name.name);
                Log.i("LOG", "name:" + name.name);

                intent.putExtra("IMAGE", name.image);
                Log.i("LOG", "img:" + name.image);
                G.currentActivity.startActivity(intent);

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