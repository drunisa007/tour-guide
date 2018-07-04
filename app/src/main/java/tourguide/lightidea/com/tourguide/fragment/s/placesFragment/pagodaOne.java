package tourguide.lightidea.com.tourguide.fragment.s.placesFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
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
import tourguide.lightidea.com.tourguide.model.placesfragmetModel.PagodaModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class pagodaOne extends Fragment {


    private View v;
    private RecyclerView mRecyclerview;

    private FirestoreRecyclerAdapter adapter;


    public pagodaOne() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         v=inflater.inflate(R.layout.fragment_pagoda_one, container, false);

         givingId(v);

        return  v;
    }

    private void givingId(View view) {

        mRecyclerview =v.findViewById(R.id.pagodaone_recyclerview);
        mRecyclerview.setLayoutManager(new GridLayoutManager(getActivity(),2));

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Query query = FirebaseFirestore
                .getInstance().collection("Place").document("pagoda").collection("data");


        FirestoreRecyclerOptions<PagodaModel> options =new FirestoreRecyclerOptions
                .Builder<PagodaModel>()
                .setQuery(query,PagodaModel.class)
                .build();
         adapter = new FirestoreRecyclerAdapter<PagodaModel, PagodaHolder>(options) {
            @NonNull
            @Override
            public PagodaHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.pagodaone_recyclerlayout,parent,false);
                return new PagodaHolder(view1);
            }

            @Override
            protected void onBindViewHolder(@NonNull PagodaHolder holder, final int position, @NonNull final PagodaModel model) {
                holder.textView.setText(model.getName());
                Glide.with(getActivity()).load(model.getUrl()).into(holder.imageView);


                holder.pagodaone_cardview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(),PlaceSingleActivity.class);
                        intent.putExtra("name",model.getName());
                        intent.putExtra("data",model.getData());
                        intent.putExtra("position","about");
                        intent.putExtra("pos","0");
                        intent.putExtra("lag",model.getLag());
                        intent.putExtra("log",model.getLog());
                         getActivity().startActivity(intent);
                    }
                });
            }
        };

         adapter.notifyDataSetChanged();

        mRecyclerview.setAdapter(adapter);



    }

    public class PagodaHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        CardView pagodaone_cardview;
        public PagodaHolder(View itemView) {
            super(itemView);

             textView = itemView.findViewById(R.id.pagodaone_textview);
             imageView = itemView.findViewById(R.id.pagodaone_imageview);
             pagodaone_cardview = itemView.findViewById(R.id.pagodaone_cardview);
        }
    }



    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
}
