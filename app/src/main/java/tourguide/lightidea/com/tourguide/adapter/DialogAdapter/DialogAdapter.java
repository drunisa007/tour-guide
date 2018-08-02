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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tourguide.lightidea.com.tourguide.R;
import tourguide.lightidea.com.tourguide.activity.MainAndOther.MainActivity;
import tourguide.lightidea.com.tourguide.activity.Restaurant.RestaurantSingleActivity;

/**
 * Created by USER on 5/19/2018.
 */

public class DialogAdapter extends RecyclerView.Adapter<DialogAdapter.MyViewHolder> {
    List<String> list = new ArrayList<>();
    Activity activity;
    String data,title,language;

    int act ;
    List mList = new ArrayList();
    public DialogAdapter(List<String> list, Activity context, int act, List mList, String data, String title,String language) {
        this.list= list;
        activity=context;
        this.act = act;
        this.mList = mList;
        this.data=data;
        this.title = title;
        this.language=language;

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
                   if(act==1){
                       if(list.get(position).equals("Mandalay")){
                           Intent intent = new Intent(view.getContext(),MainActivity.class);
                           intent.putExtra("city",list.get(position));
                           view.getContext().startActivity(intent);
                           activity.finish();
                       }
                       else{
                           Toast.makeText(activity, "Comming Soon.", Toast.LENGTH_SHORT).show();
                           activity.recreate();

                       }

                   }
                   else{
                       Intent intent = new Intent(view.getContext(),RestaurantSingleActivity.class);
                       intent.putExtra("data",data);
                       intent.putExtra("title",title);
                       intent.putExtra("all",list.get(position).toLowerCase());
                       intent.putExtra("language",language);
                       view.getContext().startActivity(intent);
                       activity.finish();
                   }



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
