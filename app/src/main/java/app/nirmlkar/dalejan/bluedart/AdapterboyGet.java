package app.nirmlkar.dalejan.bluedart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class AdapterboyGet extends  RecyclerView.Adapter<AdapterboyGet.Listwork> {
    Context context;

    private ItemDetails[] itemDetailsa;

    class Listwork extends RecyclerView.ViewHolder {


        TextView itemname, itemboyname;
        ImageView progressbar;
         Listwork(final View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  int position= itemView.getId();
                    Log.d("hhhhhhhh",position+"ll");
                    Intent i1=new Intent(context,Confirm.class);
                    i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i1.putExtra("id",itemDetailsa[position+1].getDetails_id());
                    context.startActivity(i1);


                }
            });
             itemname = itemView.findViewById(R.id.listitem);
             itemboyname = itemView.findViewById(R.id.listboy);
             progressbar = itemView.findViewById(R.id.progresstasksignal);
        }
    }

    AdapterboyGet(List<ItemDetails> itemDetails, Context context) {
        this.context=context;
        itemDetailsa = new ItemDetails[itemDetails.toArray().length];
        int i = 0;
        for (ItemDetails i1 : itemDetails) {
            itemDetailsa[i] = i1;
            i++;
        }
    }
    @Override
    public AdapterboyGet.Listwork onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.task, parent, false);
        return new AdapterboyGet.Listwork((view));
    }

    @Override
    public void onBindViewHolder(AdapterboyGet.Listwork holder, int position) {

        holder.itemname.setText(itemDetailsa[position].getItem_name());
        holder.itemboyname.setText(itemDetailsa[position].getBoy_id());
        if (Integer.parseInt(itemDetailsa[position].getFlag()) == 0) {
            holder.progressbar.setImageResource(R.drawable.redsignal);

        } else {
            holder.progressbar.setImageResource(R.drawable.greensignal);
        }


    }

    @Override
    public int getItemCount() {
        return itemDetailsa.length;
    }


}
