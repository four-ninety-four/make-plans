package com.fourninetyfour.makeplans;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PlanAdapter extends
RecyclerView.Adapter<PlanAdapter.ViewHolder> {

    List<Plan> plans;
    private Context context;

    public PlanAdapter(List<Plan> plans, Context context) {
        this.plans = plans;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title, date, shortDescription, creatorType;
        public ImageView photo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.textViewTitle);
            date = (TextView) itemView.findViewById(R.id.textViewDate);
            shortDescription = (TextView) itemView.findViewById(R.id.textViewShortDesc);
            creatorType = (TextView) itemView.findViewById(R.id.textViewCreator_Type);
            photo = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
    @NonNull
    @Override
    public PlanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_plan, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanAdapter.ViewHolder viewHolder, int i) {
        Plan plan = plans.get(i);
        viewHolder.title.setText(plan.getTitle());
        viewHolder.shortDescription.setText(plan.getDescription());
        viewHolder.date.setText(plan.getStartDate() + " - \n" + plan.getEndDate());
        viewHolder.creatorType.setText(plan.getUserid() + " - ");
        if (plan.isHidden() == true)
            viewHolder.creatorType.append("Friends Only");
        else
            viewHolder.creatorType.append("Public");
        switch(plan.getImage()) {
            case "barbecue":
                viewHolder.photo.setImageResource(R.drawable.barbecue);
                break;
            case "birthday":
                viewHolder.photo.setImageResource(R.drawable.birthday);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return plans.size();
    }
}
