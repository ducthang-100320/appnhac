package com.example.btl_music4b.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_music4b.Activity.DanhsachbaihatActivity;
import com.example.btl_music4b.Model.BaiHat;
import com.example.btl_music4b.Model.NgheSi;
import com.example.btl_music4b.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ThuVienBaiHatAdapter extends RecyclerView.Adapter<ThuVienBaiHatAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> mangbaihat;
    View view;

    public ThuVienBaiHatAdapter(Context context, ArrayList<BaiHat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ThuVienBaiHatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.dong_thuvien_baihat,parent, false);
        return new ThuVienBaiHatAdapter.ViewHolder(view);
    }




    public void onBindViewHolder(@NonNull ThuVienBaiHatAdapter.ViewHolder holder, final int position) {
        BaiHat baiHat = mangbaihat.get(position);
        holder.txtbaihat.setText(baiHat.getTenBaiHat());
        Picasso.with(context).load(baiHat.getHinhBaiHat()).into(holder.imghinhbaihat);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                intent.putExtra("cacbaihat", mangbaihat.get(position));
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        de.hdodenhof.circleimageview.CircleImageView imghinhbaihat;
        TextView txtbaihat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinhbaihat = itemView.findViewById(R.id.imageviewthuvienbaihat);
            txtbaihat = itemView.findViewById(R.id.textviewthuvienbaihat);
        }
    }
}
