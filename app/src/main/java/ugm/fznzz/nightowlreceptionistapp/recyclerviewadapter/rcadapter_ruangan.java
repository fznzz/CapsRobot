package ugm.fznzz.nightowlreceptionistapp.recyclerviewadapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import ugm.fznzz.nightowlreceptionistapp.R;
import ugm.fznzz.nightowlreceptionistapp.dataclass.list_ruangan;

public class rcadapter_ruangan extends RecyclerView.Adapter<> {

    Context mContext;
    List<list_ruangan> itemMenuList;

    public RecyclerViewAdapter(Context mContext, List<list_ruangan> itemMenuList) {
        this.mContext = mContext;
        this.itemMenuList = itemMenuList;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        ItemMenu data = itemMenuList.get(position);
        holder.storename.setText(data.getShopName());
        holder.address.setText(data.getAddress());
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView storename;
        TextView address;
        ConstraintLayout item_contact;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            storename = itemView.findViewById(R.id.tv1ruangan);
            address = itemView.findViewById(R.id.tv1lantai);

            item_contact = itemView.findViewById(R.id.menu_ruangan);
        }
    }
}
