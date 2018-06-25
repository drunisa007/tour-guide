package tourguide.lightidea.com.tourguide.fragment.s.festivalsFragment;


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
import tourguide.lightidea.com.tourguide.model.festivalfragmentModel.ModernTwoModel;


public class ModernTwo extends Fragment {

    private RecyclerView mRecyclerView;

    private FirestoreRecyclerAdapter<ModernTwoModel,ModernTwoViewHolder> adapter;



    public ModernTwo() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_moderm_two, container, false);
        mRecyclerView = view.findViewById(R.id.modern_two_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Query query = FirebaseFirestore.getInstance().collection("Festival").document("modern").collection("data");
        FirestoreRecyclerOptions<ModernTwoModel> options = new FirestoreRecyclerOptions.Builder<ModernTwoModel>()
                 .setQuery(query,ModernTwoModel.class).build();

        adapter =new FirestoreRecyclerAdapter<ModernTwoModel, ModernTwoViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ModernTwoViewHolder holder, int position, @NonNull final ModernTwoModel model) {
                holder.mTextView.setText(model.getName());
                Glide.with(getActivity()).load(model.getUrl()).into(holder.mImageView);


                holder.mCardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(),PlaceSingleActivity.class);
                        intent.putExtra("name",model.getName());
                        intent.putExtra("data",model.getData());
                        intent.putExtra("position","modern_about");
                        intent.putExtra("pos","1");
                        getActivity().startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public ModernTwoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.modern_two_recyclerview_layout,parent,false);
                return new ModernTwoViewHolder(view);
            }
        };
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    private class ModernTwoViewHolder extends RecyclerView.ViewHolder {
        CardView mCardView;
        TextView mTextView;
        ImageView mImageView;

        public ModernTwoViewHolder(View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.modern_two_cardview);
            mTextView = itemView.findViewById(R.id.modern_two_textview);
            mImageView = itemView.findViewById(R.id.modern_two_imageview);
        }
    }
}
