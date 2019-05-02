package com.fourninetyfour.makeplans;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.database.*;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class PlanAdapter extends
RecyclerView.Adapter<PlanAdapter.ViewHolder> {

    List<Plan> plans;
    private Context context;
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    CollectionReference userRef = database.collection("plans");
    DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference("plans");


    public PlanAdapter(List<Plan> plans, Context context) {
        this.plans = plans;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title, date, shortDescription, creatorType, location;
        public ImageView photo;
        public ImageButton delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.textViewTitle);
            date = (TextView) itemView.findViewById(R.id.textViewDate);
            shortDescription = (TextView) itemView.findViewById(R.id.textViewShortDesc);
            creatorType = (TextView) itemView.findViewById(R.id.textViewCreator_Type);
            photo = (ImageView) itemView.findViewById(R.id.imageView);
            delete = (ImageButton) itemView.findViewById(R.id.deleteBtn);
            location = (TextView) itemView.findViewById(R.id.textViewLocation);
        }
    }
    @NonNull
    @Override
    public PlanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_plan, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanAdapter.ViewHolder viewHolder, final int i) {
        Plan plan = plans.get(i);
        //String uid = plan.getUserID();
        //plansRef.whereEqualTo("userID", uid).getE
        viewHolder.title.setText(plan.getTitle());
        viewHolder.shortDescription.setText(plan.getDescription());
        viewHolder.date.setText(fixDate(plan.getStart()) + " - \n" + fixDate(plan.getEnd()));
        viewHolder.creatorType.setText(plan.getUserName() + " - ");
        viewHolder.location.setText(plan.getLocation());
        if (plan.getIsHidden().equals("1"))
            viewHolder.creatorType.append("Friends Only");
        else
            viewHolder.creatorType.append("Public");

        /* mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }) */


        switch(plan.getImage()) {
            case "barbecue":
                viewHolder.photo.setImageResource(R.drawable.barbecue);
                break;
            case "birthday":
                viewHolder.photo.setImageResource(R.drawable.birthday);
                break;
        }
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Plan removedPlan = plans.get(i);
                database.collection("plans").document(removedPlan.getDocumentID()).delete();
                plans.remove(i);
                notifyItemRemoved(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return plans.size();
    }

    private String fixDate(String date) {
        String month = date.substring(0,2);
        String day = date.substring(3,5);
        String year = date.substring(6,10);
        String time = date.substring(11);
        String monthTerm = "";

        switch (month) {
            case "01": monthTerm = "January";
                break;
            case "02": monthTerm = "February";
                break;
            case "03": monthTerm = "March";
                break;
            case "04": monthTerm = "April";
                break;
            case "05": monthTerm = "May";
                break;
            case "06": monthTerm = "June";
                break;
            case "07": monthTerm = "July";
                break;
            case "08": monthTerm = "August";
                break;
            case "09": monthTerm = "September";
                break;
            case "10": monthTerm = "October";
                break;
            case "11": monthTerm = "November";
                break;
            case "12": monthTerm = "December";
                break;
        }

        return monthTerm + " " + day + ", " + year + ", " + time;
    }
}
