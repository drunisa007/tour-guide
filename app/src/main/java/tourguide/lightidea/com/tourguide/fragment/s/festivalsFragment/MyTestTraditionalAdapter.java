package tourguide.lightidea.com.tourguide.fragment.s.festivalsFragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tourguide.lightidea.com.tourguide.R;
import tourguide.lightidea.com.tourguide.activity.PlaceSingleActivity;

class MyTestTraditionalAdapter extends RecyclerView.Adapter<MyTestTraditionalAdapter.MyViewHolder> {
    private   List<String> mListDay =new ArrayList<>();
    private   List<String> mListName =new ArrayList<>();
    private List<String> mListData =new ArrayList<>();
    public MyTestTraditionalAdapter(List<String> mListDay, List<String> mListName, List<String> mListData) {
        this.mListDay=mListDay;
        this.mListName=mListName;
        this.mListData = mListData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.day.setText(mListDay.get(position));
        holder.name.setText(mListName.get(position));
        holder.data.setText(mListData.get(position));
        holder.data.setVisibility(View.INVISIBLE);
        holder.onClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PlaceSingleActivity.class);
                intent.putExtra("name",holder.name.getText());
                intent.putExtra("pos","1");
                intent.putExtra("data",holder.data.getText());
                intent.putExtra("position","traditional_about");
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout onClick;
        TextView day,name,data;
        public MyViewHolder(View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.day);
            name =itemView.findViewById(R.id.name);
            data = itemView.findViewById(R.id.data);
            onClick =itemView.findViewById(R.id.onClick);

        }
    }
}
