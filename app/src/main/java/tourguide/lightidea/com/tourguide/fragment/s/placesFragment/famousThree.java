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

    private String language;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_famour_three, container, false);
        Bundle bundle = getArguments();
        language = bundle.getString("language");

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

                String name = model.getName();

                if(language.equals("eng")){
                    holder.mTextView.setText(model.getName());
                    holder.mAbout.setText(model.getFamous());
                    holder.famours_textView.setText("Why it is Famous ? ");
                }
                else if(language.equals("bur")){
                    holder.mTextView.setText(model.getName_bur());
                    name = model.getName_bur();
                    holder.famours_textView.setText("Why it is Famous Bur? ");
                    holder.mAbout.setText(model.getFamous_bur());
                }
                else {
                    holder.mTextView.setText(model.getName_chi());
                    name = model.getName_chi();
                    holder.famours_textView.setText("Why it is Famous Chi? ");
                    holder.mAbout.setText(model.getFamous_chi());
                }
                Glide.with(getActivity()).load(model.getUrl()).into(holder.mImageView);
                final String finalName = name;
                holder.mCardview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(),PlaceSingleActivity.class);
                        intent.putExtra("name", finalName);
                        intent.putExtra("data",model.getData());
                        intent.putExtra("position","famous_about");
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
        TextView mAbout;
        CardView mCardview;
        TextView famours_textView;
        public FamousHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.famousthree_imageview);
            mTextView = itemView.findViewById(R.id.famousthree_textview);
            mAbout = itemView.findViewById(R.id.famousthree_textview_why);
            mCardview = itemView.findViewById(R.id.famousthree_cardview);
            famours_textView=itemView.findViewById(R.id.why_famous);

        }
    }
}
