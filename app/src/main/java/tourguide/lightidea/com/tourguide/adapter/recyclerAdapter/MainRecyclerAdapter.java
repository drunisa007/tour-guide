package tourguide.lightidea.com.tourguide.adapter.recyclerAdapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import tourguide.lightidea.com.tourguide.R;
import tourguide.lightidea.com.tourguide.activity.Currency.CurrencyActivity;
import tourguide.lightidea.com.tourguide.activity.Festival.FestivalActivity;
import tourguide.lightidea.com.tourguide.activity.Hotel.HotelActivity;
import tourguide.lightidea.com.tourguide.activity.Place.PlaceActiviy;
import tourguide.lightidea.com.tourguide.activity.Restaurant.RestaurantActivity;
import tourguide.lightidea.com.tourguide.model.MainList;


public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MyViewHolder> {

    private Activity context;
    private List<MainList> mList;

    private List mObjectList = new ArrayList();
    private String button;

    public MainRecyclerAdapter(Activity applicationContext, List<MainList> list, String button){
        context=applicationContext;
        mList=list;
        this.button = button;
        mObjectList.add(PlaceActiviy.class);
        mObjectList.add(FestivalActivity.class);
        mObjectList.add(HotelActivity.class);
        mObjectList.add(RestaurantActivity.class);
        mObjectList.add(CurrencyActivity.class);
        mObjectList.add(CurrencyActivity.class);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mainreyclerview_layout,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
         holder.textview.setText(mList.get(position).getName());
        Glide.with(context).load(mList.get(position).getUrl()).into(holder.imageview);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent intent = new Intent(context, (Class<?>) mObjectList.get(position));
               String data ="bur";
               if(button.equals("0")){
                   data = "bur";
               }
               else if(button.equals("1")){
                   data = "eng";
               }
               else if(button.equals("2")){
                   data = "chi";
               }
               intent.putExtra("data",data);
               context.startActivity(intent);
            }


        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textview;
        ImageView imageview;
        CardView cardView;
        View view;
        public MyViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            imageview =itemView.findViewById(R.id.main_recycler_image);
            textview = itemView.findViewById(R.id.main_recycler_text);
            cardView= itemView.findViewById(R.id.cardView);

        }

    }
}
