package tourguide.lightidea.com.tourguide.adapter.DialogAdapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tourguide.lightidea.com.tourguide.R;
import tourguide.lightidea.com.tourguide.activity.MainActivity;

/**
 * Created by USER on 5/19/2018.
 */

public class DialogAdapter extends RecyclerView.Adapter<DialogAdapter.MyViewHolder> {
    List<String> list = new ArrayList<>();
    Activity activity;
    public DialogAdapter(List<String> list, MainActivity mainActivity) {
        this.list= list;
        activity=mainActivity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_recycler_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
           holder.textview.setText(list.get(position));
           holder.cardview.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(view.getContext(),MainActivity.class);
                   intent.putExtra("city",list.get(position));
                   view.getContext().startActivity(intent);
                   activity.finish();


               }
           });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardview;
        TextView textview;
        public MyViewHolder(View itemView) {
            super(itemView);
            cardview= itemView.findViewById(R.id.dialog_cardview);
            textview = itemView.findViewById(R.id.dialog_textview);
        }
    }
}
