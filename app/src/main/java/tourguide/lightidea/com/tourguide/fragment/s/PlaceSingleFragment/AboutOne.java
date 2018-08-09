package tourguide.lightidea.com.tourguide.fragment.s.PlaceSingleFragment;


import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import tourguide.lightidea.com.tourguide.R;
import tourguide.lightidea.com.tourguide.model.PlaceSingleFragmentModel.AboutOneModel;

public class AboutOne extends Fragment {


    private RecyclerView mRecyclerView;
    private String data;
    private String position;
    private int pos;
    private String language;

    private FirestoreRecyclerAdapter<AboutOneModel,AboutOneViewHolder> adapter;

    private String getData[]={"Place","Festival","Hotel","Restaurant","Taxi"};


    public AboutOne() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_about_one, container, false);
        mRecyclerView = view.findViewById(R.id.about_one_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);

        Bundle bundle = getArguments();
        if(bundle!=null){
            data = bundle.getString("data");
            position=bundle.getString("position");
            pos = Integer.parseInt(bundle.getString("pos"));
            language = bundle.getString("language");
        }
        Log.d("arun",language+"lee");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Query query  = FirebaseFirestore.getInstance().collection(getData[pos]).document(position).collection(data);

        FirestoreRecyclerOptions<AboutOneModel> options= new FirestoreRecyclerOptions.Builder<AboutOneModel>()
                 .setQuery(query,AboutOneModel.class)
                  .build();

        adapter = new FirestoreRecyclerAdapter<AboutOneModel, AboutOneViewHolder>(options) {
             @Override
            protected void onBindViewHolder(@NonNull AboutOneViewHolder holder, int position, @NonNull AboutOneModel model) {

                        if(!TextUtils.isEmpty(model.getTitle())){
                            if(language.equals("eng")){
                                holder.mTextViewTitle.setText(model.getTitle());
                            }
                            else if(language.equals("bur")){
                                holder.mTextViewTitle.setText(model.getTitle_bur());
                            }
                            else {
                                holder.mTextViewTitle.setText(model.getTitle_chi());
                            }

                        }
                        else{
                            LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) holder.mTextViewTitle.getLayoutParams();
                            params.setMargins(0,0,0,0);
                            holder.mTextViewTitle.setTextSize(0);
                            holder.mTextViewTitle.setLayoutParams(params);
                        }


                        if(!TextUtils.isEmpty(model.getBody())){
                            holder.mTextViewBody.setText(Html.fromHtml(model.getBody()));
                            if(language.equals("eng")){
                                holder.mTextViewBody.setText(model.getBody());
                            }
                            else if(language.equals("bur")){
                                holder.mTextViewBody.setText(model.getBody_bur());
                            }
                            else {
                                holder.mTextViewBody.setText(model.getBody_chi());
                            }
                        }




                    if(!TextUtils.isEmpty(model.getImage())){
                        holder.mImageView.requestLayout();
                        holder.mImageView.getLayoutParams().height=(int) getResources().getDimension(R.dimen.heght);
                        holder.mImageView.getLayoutParams().width=ViewGroup.LayoutParams.MATCH_PARENT;
                        Glide.with(getActivity()).load(model.getImage()).into(holder.mImageView);
                    }



            }

            @NonNull
            @Override
            public AboutOneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.about_one_recycler_layout,parent,false);
                return new AboutOneViewHolder(view);
            }
        };


        mRecyclerView.setAdapter(adapter);


    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    private class AboutOneViewHolder extends RecyclerView.ViewHolder {


        ImageView mImageView;
        TextView mTextViewTitle,mTextViewBody;

        public AboutOneViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.about_one_image);
            mTextViewTitle =itemView.findViewById(R.id.about_one_title);
            mTextViewBody = itemView.findViewById(R.id.about_one_body);
        }
    }
}