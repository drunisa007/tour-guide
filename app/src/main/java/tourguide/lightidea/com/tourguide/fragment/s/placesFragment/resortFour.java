package tourguide.lightidea.com.tourguide.fragment.s.placesFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import tourguide.lightidea.com.tourguide.R;
import tourguide.lightidea.com.tourguide.activity.Place.PlaceSingleActivity;
import tourguide.lightidea.com.tourguide.model.placesfragmetModel.ResortModel;

public class resortFour extends Fragment {


    public resortFour() {
    }

  private   View view;

    private RecyclerView mRecyclerview;

    private FirestoreRecyclerAdapter<ResortModel,ResortHolder> adapter;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_resort_four, container, false);
        mRecyclerview = view.findViewById(R.id.resortfour_reyclerview);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Query query = FirebaseFirestore.getInstance().collection("Place").document("resort").collection("data");

        FirestoreRecyclerOptions<ResortModel> options = new FirestoreRecyclerOptions
                                .Builder<ResortModel>()
                                 .setQuery(query,ResortModel.class)
                                 .build();

        adapter= new FirestoreRecyclerAdapter<ResortModel, ResortHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ResortHolder holder, int position, @NonNull final ResortModel model) {
                holder.mTextView.setText(model.getName());
                Glide.with(getActivity()).load(model.getUrl()).into(holder.mImageView);
                holder.mRating.setText(model.getRating());
                holder.mCardview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(),PlaceSingleActivity.class);
                        intent.putExtra("name",model.getName());
                        intent.putExtra("data",model.getData());
                        intent.putExtra("position","resort_about");
                        intent.putExtra("pos","0");
                        intent.putExtra("lag",model.getLag());
                        intent.putExtra("log",model.getLog());
                        getActivity().startActivity(intent);
                    }
                });
            }


            @NonNull
            @Override
            public ResortHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.resortfour_recyclerview_layout,parent,false);
                return new ResortHolder(v);
            }
        };
        mRecyclerview.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    private class ResortHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView;
        CardView mCardview;
        TextView mRating;
        public ResortHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.resortfour_imageview);
            mTextView = itemView.findViewById(R.id.resortfour_textview);
            mCardview = itemView.findViewById(R.id.resortfour_cardview);
            mRating = itemView.findViewById(R.id.resortfour_rating);
        }
    }
}
