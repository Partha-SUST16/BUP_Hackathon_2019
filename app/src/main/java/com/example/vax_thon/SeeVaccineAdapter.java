package com.example.vax_thon;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SeeVaccineAdapter extends RecyclerView.Adapter<SeeVaccineAdapter.VaccineHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<SetVaccine> productList;

    //getting the context and product list with constructor
    public SeeVaccineAdapter(Context mCtx, List<SetVaccine> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public SeeVaccineAdapter.VaccineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_see_date, null);
        return new SeeVaccineAdapter.VaccineHolder(view);
    }

    @Override
    public void onBindViewHolder(SeeVaccineAdapter.VaccineHolder holder, final int position) {
        //getting the product of the specified position11
        final SetVaccine product = productList.get(position);
//final SearchDoctorCardview product = productList.get(position);
        //binding the data with the viewholder views

        holder.textViewTitle.setText(product.name);
        holder.textViewCatagory.setText(product.date);
        /*holder.textViewHospital.setText(product.uid);*/

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


    class VaccineHolder extends RecyclerView.ViewHolder {
        public LinearLayout linearLayout;

        TextView textViewTitle, textViewCatagory, textViewHospital;
        ImageView imageView;

        public VaccineHolder(View itemView) {
            super(itemView);


            textViewTitle = itemView.findViewById(R.id.vaccinenameID);
            textViewCatagory = itemView.findViewById(R.id.vdateID);
            imageView = itemView.findViewById(R.id.imageView2);
            /*textViewHospital = itemView.findViewById(R.id.clientuid);*/
            linearLayout = (LinearLayout) itemView.findViewById(R.id.seelinear);
        }


    }
}

