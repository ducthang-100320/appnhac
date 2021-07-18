package com.example.btl_music4b.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.btl_music4b.Activity.AddBaiHatActivity;
import com.example.btl_music4b.Activity.HomeActivity;
import com.example.btl_music4b.Activity.PlayNhacActivity;
import com.example.btl_music4b.Model.BaiHat;
import com.example.btl_music4b.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TimKiemAdapter extends RecyclerView.Adapter<TimKiemAdapter.ViewHolder>{

    private HomeActivity context;
    private int layout;
    ArrayList<BaiHat> mangbaihat;
    TextView textViewtencasitimkiem,textViewtentimkiem;
    ImageView imageViewhinhtimkiem;
    TextView txttentimkiem, txtcasitimkiem;
    ImageView imganhtimkiem;

    public TimKiemAdapter(HomeActivity context, ArrayList<BaiHat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dong_tim_kiem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final BaiHat baiHat = mangbaihat.get(position);
        holder.txttentimkiem.setText(baiHat.getTenBaiHat());
        holder.txtcasitimkiem.setText(baiHat.getTenCaSi());
        Picasso.with(context).load(baiHat.getHinhBaiHat()).into(holder.imganhtimkiem);

//        holder.edtTenCaSi.setText(baiHat.getTenBaiHat());
//        holder.edtHinhBaiHat.setText(baiHat.getHinhBaiHat());
//        holder.edtLinkBaiHat.setText(baiHat.getLinkBaiHat());
//        holder.edtTenCaSi.setText(baiHat.getTenCaSi());
        holder.imageViewtimtimkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,AddBaiHatActivity.class);
                intent.putExtra("addBaiHat",baiHat);
                context.startActivity(intent);

            }
        });

    }
    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder holder = null;
//        if(convertView == null){
//            holder = new ViewHolder(convertView);
//            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = layoutInflater.inflate(layout,null);
//            holder.edtTenCaSi = convertView.findViewById(R.id.edtTenCaSi);
//            holder.edtTenBaiHat = convertView.findViewById(R.id.edtTenBaiHat);
//            holder.edtHinhBaiHat = convertView.findViewById(R.id.edtHinhBaiHat);
//            holder.edtLinkBaiHat = convertView.findViewById(R.id.edtLinkBaiHat);
//            holder.imageViewtimtimkiem = convertView.findViewById(R.id.imageViewtimtimkiem);
//            convertView.setTag(holder);
//
//
//        }
//        else {
//            holder = (ViewHolder)convertView.getTag();
//
//        }
//        final BaiHat baiHat = mangbaihat.get(position);
//        holder.edtTenCaSi.setText(baiHat.getTenCaSi());
//        holder.edtTenBaiHat.setText(baiHat.getTenBaiHat());
//        holder.edtHinhBaiHat.setText(baiHat.getHinhBaiHat());
//        holder.edtLinkBaiHat.setText(baiHat.getLinkBaiHat());
//        holder.imageViewtimtimkiem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context,AddBaiHatActivity.class);
//                intent.putExtra("studentEdit",baiHat);
//                context.startActivity(intent);
//
//            }
//        });
//        return convertView;
//    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txttentimkiem, txtcasitimkiem;
        ImageView imganhtimkiem,imageViewtimtimkiem;
        EditText edtTenBaiHat,edtHinhBaiHat,edtLinkBaiHat,edtTenCaSi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttentimkiem = itemView.findViewById(R.id.textViewtentimkiem);
            txtcasitimkiem = itemView.findViewById(R.id.textViewtencasitimkiem);
            imganhtimkiem = itemView.findViewById(R.id.imageViewhinhtimkiem);
            imageViewtimtimkiem = itemView.findViewById(R.id.imageViewtimtimkiem);
            edtTenBaiHat = itemView.findViewById(R.id.edtTenBaiHat);
            edtHinhBaiHat = itemView.findViewById(R.id.edtHinhBaiHat);
            edtLinkBaiHat = itemView.findViewById(R.id.edtLinkBaiHat);
            edtTenCaSi = itemView.findViewById(R.id.edtTenCaSi);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                     intent.putExtra("cakhuc", mangbaihat.get(getPosition()));
                     context.startActivity(intent);
                }
            });

        }
    }

}
