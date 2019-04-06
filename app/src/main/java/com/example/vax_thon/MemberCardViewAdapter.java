package com.example.vax_thon;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MemberCardViewAdapter extends RecyclerView.Adapter<MemberCardViewAdapter.MemberCardViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<MemberCardView> productList;

    //getting the context and product list with constructor
    public MemberCardViewAdapter(Context mCtx, List<MemberCardView> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public MemberCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_search_doctor_cardview, null);
        return new MemberCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MemberCardViewHolder holder, final int position) {
        //getting the product of the specified position
        final MemberCardView product = productList.get(position);
//final SearchDoctorCardview product = productList.get(position);
        //binding the data with the viewholder views

        holder.textViewTitle.setText(product.name);
        holder.textViewCatagory.setText(product.email);
        holder.textViewHospital.setText(product.uid);

        //holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mCtx, "You have clicked: " + product.name, Toast.LENGTH_SHORT).show();
                //Intent intent  = new Intent(mCtx,DoctorProfileFromPatient.class);
                //intent.putExtra("doctorEmail",product.email);
                //mCtx.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class MemberCardViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout linearLayout;

        TextView textViewTitle, textViewCatagory, textViewHospital;
        ImageView imageView;

        public MemberCardViewHolder(View itemView) {
            super(itemView);


            textViewTitle = itemView.findViewById(R.id.MemberNameId);
            textViewCatagory = itemView.findViewById(R.id.MemberEmailId);
            imageView = itemView.findViewById(R.id.imageView);
            textViewHospital = itemView.findViewById(R.id.clientuid);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }


    }
}

