package tourguide.lightidea.com.tourguide.activity;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
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
import tourguide.lightidea.com.tourguide.model.HotelModel.FacModel;

public  class MyHotelSingleRecyclerAdapter extends RecyclerView.Adapter<MyHotelSingleRecyclerAdapter.MyViewHolder> {

    private List<Boolean> mList =new ArrayList<>();
    private List<Integer> imageList =new ArrayList<>();
    private List<Integer> disimageList =new ArrayList<>();
    private List<String>  mListToast =new ArrayList<>();
    private Context context;
    private CoordinatorLayout coor;


    public MyHotelSingleRecyclerAdapter(HotelSingleActivity hotelSingleActivity, List<Boolean> mList, List<Integer> imageList,
                                        List<Integer> disimageList, List<String> toastList, CoordinatorLayout coor) {
        this.mList=mList;
        this.imageList=imageList;
        this.disimageList=disimageList;
        context=hotelSingleActivity;
        mListToast=toastList;
        this.coor=coor;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_single_recyclerview_single_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        if(mList.get(position)){
            Glide.with(context).load(imageList.get(position)).into(holder.mImageview);
        }
        else{
            Glide.with(context).load(disimageList.get(position)).into(holder.mImageview);
            holder.mImageview.setEnabled(false);
        }
        holder.mImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(coor, mListToast.get(position), Snackbar.LENGTH_LONG);

                View sbView = snackbar.getView();
                TextView textView =sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextSize(17);
                textView.setTextColor(Color.parseColor("#ffffff"));
                snackbar.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageview;
        public MyViewHolder(View itemView) {
            super(itemView);
            mImageview=itemView.findViewById(R.id.hotel_single_recyclerview_imageview);
        }
    }
}
