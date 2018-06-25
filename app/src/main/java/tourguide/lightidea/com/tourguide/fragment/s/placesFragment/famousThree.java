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
import tourguide.lightidea.com.tourguide.activity.PlaceSingleActivity;
import tourguide.lightidea.com.tourguide.activity.pagodaOneSingleActivity;
import tourguide.lightidea.com.tourguide.model.placesfragmetModel.FamousModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class famousThree extends Fragment {


    public famousThree() {

    }

    private RecyclerView mRecyclerView;
    private int id=0;

    private FirestoreRecyclerAdapter<FamousModel,FamousHolder> adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_famour_three, container, false);

        mRecyclerView = view.findViewById(R.id.famousthree_recyclerview);
        mRecyclerView.setHasFixedSize(true);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ;
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Query query = FirebaseFirestore.getInstance()
                                        .collection("Place").document("famous").collection("data");


        FirestoreRecyclerOptions<FamousModel> options = new FirestoreRecyclerOptions
                .Builder<FamousModel>()
                .setQuery(query,FamousModel.class)
                .build();

       adapter = new FirestoreRecyclerAdapter<FamousModel, FamousHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final FamousHolder holder, int position, @NonNull final FamousModel model) {
                holder.mTextView.setText(model.getName());
                Glide.with(getActivity()).load(model.getUrl()).into(holder.mImageView);

                holder.mIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(id%2==0){
                            Glide.with(getActivity()).load(R.drawable.like).into(holder.mIcon);
                        }
                        else{
                            Glide.with(getActivity()).load(R.drawable.unlike).into(holder.mIcon);

                        }

                    }
                });
                holder.mCardview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(),PlaceSingleActivity.class);
                        intent.putExtra("name",model.getName());
                        intent.putExtra("data",model.getData());
                        intent.putExtra("position","famous_about");
                        intent.putExtra("pos","0");

                        getActivity().startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public FamousHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.famousthree_recyclerview_layout,parent,false);
                return new FamousHolder(v);
            }
        };

       mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    private class FamousHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView;
        ImageView mIcon;
        TextView mAbout;
        CardView mCardview;
        public FamousHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.famousthree_imageview);
            mTextView = itemView.findViewById(R.id.famousthree_textview);
            mIcon = itemView.findViewById(R.id.famousthree_imageview_like);
            mAbout = itemView.findViewById(R.id.famousthree_textview_why);
            mCardview = itemView.findViewById(R.id.famousthree_cardview);

        }
    }
}
