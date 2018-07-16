package tourguide.lightidea.com.tourguide.adapter.HotelAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import tourguide.lightidea.com.tourguide.R;
import tourguide.lightidea.com.tourguide.model.HotelModel.HotelRoomModel;

public class FlipperAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<HotelRoomModel> mList=new ArrayList<>();
    public FlipperAdapter(Context context,ArrayList<HotelRoomModel> mList){
        this.context=context;
        this.mList=mList;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View mView = inflater.inflate(R.layout.hotel_single_flipper_layout,null);
        ImageView imageView = mView.findViewById(R.id.hotel_single_flipper_imageview);
        Glide.with(context).load(mList.get(i).getUrl()).into(imageView);
        return mView;
    }
}
