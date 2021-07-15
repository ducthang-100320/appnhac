package com.example.btl_music4b.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_music4b.Activity.DanhsachbaihatActivity;
import com.example.btl_music4b.Activity.MainActivity;
import com.example.btl_music4b.Activity.PlayNhacActivity;
import com.example.btl_music4b.Fragment.Fragment_ThuVien_BaiHat;
import com.example.btl_music4b.Model.BaiHat;
import com.example.btl_music4b.Model.ThuVienBaiHat;
import com.example.btl_music4b.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ThuVienBaiHatAdapter extends RecyclerView.Adapter<ThuVienBaiHatAdapter.ViewHolder> {
    private Fragment_ThuVien_BaiHat thuvien = new Fragment_ThuVien_BaiHat();
    Context context;
    ArrayList<ThuVienBaiHat> thuvienbaihat;
    View view;

    public ThuVienBaiHatAdapter(Context context, ArrayList<ThuVienBaiHat> thuvienbaihat) {
        this.context = context;
        this.thuvienbaihat = thuvienbaihat;

    }

//    public ThuVienBaiHatAdapter(Fragment_ThuVien_BaiHat thuvien, Context context, ArrayList<ThuVienBaiHat> thuvienbaihat, View view) {
//        this.thuvien = thuvien;
//        this.context = context;
//        this.thuvienbaihat = thuvienbaihat;
//        this.view = view;
//    }

    @NonNull
    @Override
    public ThuVienBaiHatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.dong_thuvien_baihat,parent, false);
        return new ThuVienBaiHatAdapter.ViewHolder(view);
    }




    public void onBindViewHolder(@NonNull final ThuVienBaiHatAdapter.ViewHolder holder, final int position) {
        final ThuVienBaiHat thuVienBaiHat = thuvienbaihat.get(position);
        holder.txtbaihat.setText(thuVienBaiHat.getTenBaiHat());
        Picasso.with(context).load(thuVienBaiHat.getHinhBaiHat()).into(holder.imghinhbaihat);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PlayNhacActivity.class);
                intent.putExtra("intentthuvien", thuvienbaihat.get(position));
                context.startActivity(intent);
            }
        });
        holder.layout_deletebaihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDelete(thuVienBaiHat.getTenBaiHat(),thuVienBaiHat.getID());
            }
        });
    }
    private void confirmDelete(String TenBaiHat, final int ID) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Cảnh báo");
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setMessage("Bạn có muốn xóa"+TenBaiHat+" không");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                thuvien.deleteItem(ID);
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();


    }



    @Override
    public int getItemCount() {
        if(thuvienbaihat != null) {
            return thuvienbaihat.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        de.hdodenhof.circleimageview.CircleImageView imghinhbaihat;
        TextView txtbaihat;
        private LinearLayout layout_deletebaihat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinhbaihat = itemView.findViewById(R.id.imageviewthuvienbaihat);
            txtbaihat = itemView.findViewById(R.id.textviewthuvienbaihat);
            layout_deletebaihat = itemView.findViewById(R.id.layout_deletebaihat);
        }
    }
}
