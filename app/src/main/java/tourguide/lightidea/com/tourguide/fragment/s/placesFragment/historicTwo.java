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
import tourguide.lightidea.com.tourguide.model.placesfragmetModel.PagodaModel;


public class historicTwo extends Fragment {


    public historicTwo() {

    }

    private View view;

    private  FirestoreRecyclerAdapter<PagodaModel,HistoricHolder> adapter ;

    private RecyclerView mRecyclerview;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_historic_two, container, false);
        mRecyclerview = view.findViewById(R.id.historic_two_recyclerview);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerview.setHasFixedSize(true);

        Query query = FirebaseFirestore.getInstance().collection("Place").document("historic").collection("data");

        FirestoreRecyclerOptions<PagodaModel> options =new FirestoreRecyclerOptions
                                             .Builder<PagodaModel>()
                                             .setQuery(query,PagodaModel.class)
                                              .build();


       adapter = new FirestoreRecyclerAdapter<PagodaModel,HistoricHolder>(options){

            @NonNull
            @Override
            public HistoricHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.historic_two_recycler_layout,parent,false);
                return new HistoricHolder(v);
            }

            @Override
            protected void onBindViewHolder(@NonNull HistoricHolder holder, int position, @NonNull final PagodaModel model) {
                holder.mTextViiew.setText(model.getName());
                Glide.with(getActivity()).load(model.getUrl()).into(holder.mImageView);


                holder.historic_two_cardview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(),PlaceSingleActivity.class);
                        intent.putExtra("name",model.getName());
                        intent.putExtra("data",model.getData());
                        intent.putExtra("position","historic_about");
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

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    public class HistoricHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        TextView mTextViiew;
        CardView historic_two_cardview;
        public HistoricHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.historic_two_imageview);
            mTextViiew = itemView.findViewById(R.id.historic_two_textview);
            historic_two_cardview=itemView.findViewById(R.id.historic_two_cardview);
        }
    }
}
