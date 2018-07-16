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
import tourguide.lightidea.com.tourguide.model.placesfragmetModel.MarketModel;

public class marketFive extends Fragment {


    public marketFive() {
    }


      private RecyclerView mRecyclerView;
    private FirestoreRecyclerAdapter<MarketModel,MarketHolder> adapter;

    private String language;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_market_five, container, false);
        Bundle bundle = getArguments();
        language = bundle.getString("language");
        mRecyclerView = view.findViewById(R.id.marketfive_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Query query = FirebaseFirestore.getInstance().collection("Place").document("market").collection("data");

        FirestoreRecyclerOptions<MarketModel> options = new FirestoreRecyclerOptions.Builder<MarketModel>()
                .setQuery(query,MarketModel.class).build();

        adapter = new FirestoreRecyclerAdapter<MarketModel, MarketHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MarketHolder holder, int position, @NonNull final MarketModel model) {

                if(language.equals("eng")){
                    holder.mTextView.setText(model.getName());
                }
                else if(language.equals("bur")){
                    holder.mTextView.setText(model.getName_bur());
                }
                else {
                    holder.mTextView.setText(model.getName_chi());
                }
                holder.mTextView_mart_type.setText(model.getType());
                Glide.with(getActivity()).load(model.getUrl()).into(holder.mImageView);


                holder.mCardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(),PlaceSingleActivity.class);
                        intent.putExtra("name",model.getName());
                        intent.putExtra("data",model.getData());
                        intent.putExtra("position","market_about");
                        intent.putExtra("pos","0");
                        intent.putExtra("lag",model.getLag());
                        intent.putExtra("log",model.getLog());
                        intent.putExtra("language",language);
                        getActivity().startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public MarketHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.marketfive_recycler_layout,parent,false);
                return new MarketHolder(view);
            }
        };
        mRecyclerView.setAdapter(adapter);
    }


    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();

    }

    private class MarketHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        TextView mTextView;
        CardView mCardView;
        TextView mTextView_mart_type;

        public MarketHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.marketfive_imageview);
            mTextView = itemView.findViewById(R.id.marketfive_textview);
            mTextView_mart_type = itemView.findViewById(R.id.marketfive_mart_type);
            mCardView =itemView.findViewById(R.id.marketfive_cardview);
        }
    }
}
