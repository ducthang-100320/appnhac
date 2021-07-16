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

import com.example.btl_music4b.Activity.PlayNhacActivity;
import com.example.btl_music4b.Activity.ThuVienActivity;
import com.example.btl_music4b.Model.ThuVienBaiHat;
import com.example.btl_music4b.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TVBaiHatAdapter extends RecyclerView.Adapter<TVBaiHatAdapter.ViewHolder> {
    //private Fragment_ThuVien_BaiHat thuvien;
    private int layout;
    private ThuVienActivity context;
    ArrayList<ThuVienBaiHat> thuvienbaihat;
    View view;
    public TVBaiHatAdapter(ThuVienActivity context,int layout, ArrayList<ThuVienBaiHat> thuvienbaihat) {
        this.context = context;
        this.layout = layout;
        this.thuvienbaihat = thuvienbaihat;

    }
    @NonNull
    @Override
    public TVBaiHatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.dong_thuvien_baihat,parent, false);
        return new TVBaiHatAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TVBaiHatAdapter.ViewHolder holder, final int position) {
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
                context.deleteItem(ID);
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

    public class ViewHolder extends RecyclerView.ViewHolder{
        de.hdodenhof.circleimageview.CircleImageView imghinhbaihat;
        TextView txtbaihat;
        private LinearLayout layout_deletebaihat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinhbaihat = itemView.findViewById(R.id.imageviewthuvienbaihat);
            txtbaihat = itemView.findViewById(R.id.textviewthuvienbaihat);
            layout_deletebaihat = itemView.findViewById(R.id.layout_deletebaihat);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc", thuvienbaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
