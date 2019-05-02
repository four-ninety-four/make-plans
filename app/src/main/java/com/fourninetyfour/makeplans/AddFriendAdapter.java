package com.fourninetyfour.makeplans;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class AddFriendAdapter extends RecyclerView.Adapter<AddFriendAdapter.ViewHolder> {

        List<Friend> friends;
        private Context context;
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        CollectionReference userRef = database.collection("plans");


public AddFriendAdapter(List<Friend> friends, Context context) {
        this.friends = friends;
        this.context = context;
        }

public class ViewHolder extends RecyclerView.ViewHolder{

    public TextView name, city, state, email;
    public ImageView photo;
    public Button delete, addFriend;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        name = (TextView) itemView.findViewById(R.id.friendNameView);
        city = (TextView) itemView.findViewById(R.id.friendCityView);
        state = (TextView) itemView.findViewById(R.id.friendStateView);
        email = (TextView) itemView.findViewById(R.id.friendEmailView);
        photo = (ImageView) itemView.findViewById(R.id.friendImageView);
        delete = (Button) itemView.findViewById(R.id.deleteButton);
        addFriend = itemView.findViewById(R.id.addFriendButton);
        delete.setClickable(false);
        delete.setVisibility(View.GONE);
    }
}
    @NonNull
    @Override
    public AddFriendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_friend, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AddFriendAdapter.ViewHolder viewHolder, final int i) {
        Friend friend = friends.get(i);
        //String uid = plan.getUserID();
        //plansRef.whereEqualTo("userID", uid).getE
        viewHolder.name.setText(friend.getFriendFirstName() + " " + friend.getFriendLastName());
        viewHolder.email.setText(friend.getFriendEmail());
        viewHolder.state.setText(friend.getFriendState());
        viewHolder.city.setText(friend.getfriendCity());
        viewHolder.photo.setImageResource(R.drawable.ic_user);

        /*viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Plan removedPlan = plans.get(i);
                database.collection("plans").document(removedPlan.getDocumentID()).delete();
                plans.remove(i);
                notifyItemRemoved(i);
            }
        }); */
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }
}
