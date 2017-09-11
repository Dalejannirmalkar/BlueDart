package app.nirmlkar.dalejan.bluedart;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class AdapterItemDetails extends RecyclerView.Adapter<AdapterItemDetails.ListItem> {
    private ItemDetails[] itemDetailsa;

    class ListItem extends RecyclerView.ViewHolder {

        TextView itemname, itemboyname;
        ImageView progressbar;


        ListItem(View itemView) {
            super(itemView);
            itemname = itemView.findViewById(R.id.listitem);
            itemboyname = itemView.findViewById(R.id.listboy);
            progressbar = itemView.findViewById(R.id.progresstasksignal);


        }
    }

    AdapterItemDetails(List<ItemDetails> itemDetails) {
        itemDetailsa = new ItemDetails[itemDetails.toArray().length];
        int i = 0;
        for (ItemDetails i1 : itemDetails) {
            itemDetailsa[i] = i1;
            i++;
        }
    }

    @Override
    public AdapterItemDetails.ListItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task, parent, false);
        return new AdapterItemDetails.ListItem(view);
    }

    @Override
    public void onBindViewHolder(AdapterItemDetails.ListItem holder, int position) {


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
