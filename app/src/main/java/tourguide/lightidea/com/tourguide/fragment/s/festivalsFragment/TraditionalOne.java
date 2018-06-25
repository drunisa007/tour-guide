package tourguide.lightidea.com.tourguide.fragment.s.festivalsFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.List;

import tourguide.lightidea.com.tourguide.R;
import tourguide.lightidea.com.tourguide.activity.PlaceSingleActivity;
import tourguide.lightidea.com.tourguide.model.festivalfragmentModel.TraditionExtraModel;
import tourguide.lightidea.com.tourguide.model.festivalfragmentModel.TraditionalOneModel;


public class TraditionalOne extends Fragment {


    public TraditionalOne() {
    }

    private RecyclerView mRecyclerView;

          private   List<String> mListDay =new ArrayList<>();
          private   List<String> mListName =new ArrayList<>();
           private List<String> mListData =new ArrayList<>();

    private FirestoreRecyclerAdapter<TraditionalOneModel,TraditionalOneViewHolder> adapter;
    private int colors[]={R.color.one,R.color.two,R.color.three,R.color.four,R.color.five,R.color.six,R.color.seven,
    R.color.eight,R.color.nine,R.color.ten,R.color.eleven,R.color.twelve
    };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_traditional_one, container, false);
        mRecyclerView = view.findViewById(R.id.traditional_one_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Query query= FirebaseFirestore.getInstance().collection("Festival").document("traditional").collection("data");

        FirestoreRecyclerOptions<TraditionalOneModel> options = new FirestoreRecyclerOptions.Builder<TraditionalOneModel>()
                 .setQuery(query,TraditionalOneModel.class)
                 .build();

     adapter = new FirestoreRecyclerAdapter<TraditionalOneModel, TraditionalOneViewHolder>(options) {
         @Override
         protected void onBindViewHolder(@NonNull TraditionalOneViewHolder holder, int position, @NonNull final TraditionalOneModel model) {

             holder.mTextView.setText(model.getTitle());
             gettingData(model);
             holder.mExtraRecyclerView.setHasFixedSize(true);
             holder.mExtraRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
             workingForExtradRecyclerView(holder.mExtraRecyclerView);


         }

         @NonNull
         @Override
         public TraditionalOneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
             View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.traditon_one_recyclerview_layout,parent,false);
             return new TraditionalOneViewHolder(v);
         }
     };

        mRecyclerView.setAdapter(adapter);



    }

//    private void workingForTextViewColor(String title, TextView mTextView) {
//        String result = title.toLowerCase().trim();
//        Log.e("arun",result+"");
//        int color =R.color.colorPrimary;
//
//        switch (result){
//            case "january":
//                color=R.color.one;
//                mTextView.setTextColor(color);
//                break;
//            case "february":
//                color = R.color.two;
//                mTextView.setTextColor(color);
//                break;
//            case "march":
//                color= R.color.three;
//                mTextView.setTextColor(color);
//
//                break;
//            case "april":
//                color = R.color.four;
//                mTextView.setTextColor(color);
//                break;
//            case "may":
//                color = R.color.five;
//                mTextView.setTextColor(color);
//
//                break;
//            case "june":
//                color= R.color.six;
//                mTextView.setTextColor(color);
//                break;
//            case "july":
//                color= R.color.seven;
//                mTextView.setTextColor(color);
//                break;
//            case "august":
//                color= R.color.eight;
//                mTextView.setTextColor(color);
//                break;
//            case "september":
//                color=R.color.nine;
//                mTextView.setTextColor(color);
//                break;
//            case "october":
//                color=R.color.ten;
//                mTextView.setTextColor(color);
//                break;
//            case "november":
//                color=R.color.eleven;
//                mTextView.setTextColor(color);
//                break;
//            case "december":
//                color=R.color.twelve;
//                mTextView.setTextColor(color);
//                break;
//
//
//        }
//
//
//    }

    private void gettingData(TraditionalOneModel model) {
        if(!mListData.isEmpty()){
            mListData.clear();
            mListDay.clear();
            mListName.clear();
        }

        String day = model.getDay();
        String name = model.getName();
        String data = model.getData();
        if(!TextUtils.isEmpty(day)){
            for(String resultDay:day.split(",")){
                mListDay.add(resultDay);
            }
            for(String resultName:name.split(",")){
                mListName.add(resultName);
            }
            for(String resultData:data.split(",")){
                mListData.add(resultData);
            }
        }





    }

    private void workingForExtradRecyclerView(RecyclerView mExtraRecyclerView) {
        mExtraRecyclerView.setAdapter(new MyTestTraditionalAdapter(mListDay,mListName,mListData));
    }


    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    private class TraditionalOneViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;
        RecyclerView mExtraRecyclerView;
        public TraditionalOneViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.traditional_one_month);
            mExtraRecyclerView =itemView.findViewById(R.id.traditional_one_recyclerview_extra);
        }
    }


}
