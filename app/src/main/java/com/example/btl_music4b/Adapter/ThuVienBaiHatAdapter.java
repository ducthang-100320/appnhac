package com.example.btl_music4b.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.btl_music4b.Activity.AddBaiHat;
import com.example.btl_music4b.Activity.DanhsachbaihatActivity;
import com.example.btl_music4b.Fragment.Fragment_ThuVien_BaiHat;
import com.example.btl_music4b.Model.BaiHat;
import com.example.btl_music4b.Model.NgheSi;
import com.example.btl_music4b.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ThuVienBaiHatAdapter extends RecyclerView.Adapter<ThuVienBaiHatAdapter.ViewHolder> {

    Context context;
    ArrayList<BaiHat> mangbaihat;
    private ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
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




    public void onBindViewHolder(@NonNull final ThuVienBaiHatAdapter.ViewHolder holder, final int position) {
        BaiHat baiHat = mangbaihat.get(position);
        holder.txtbaihat.setText(baiHat.getTenBaiHat());
        Picasso.with(context).load(baiHat.getHinhBaiHat()).into(holder.imghinhbaihat);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddBaiHat.class);
                intent.putExtra("cacbaihat", mangbaihat.get(position));
                context.startActivity(intent);
            }
        });
        holder.layout_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mangbaihat.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
    }


    @Override
    public int getItemCount() {
        if(mangbaihat != null) {
            return mangbaihat.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        de.hdodenhof.circleimageview.CircleImageView imghinhbaihat;
        TextView txtbaihat;
        private LinearLayout layout_delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinhbaihat = itemView.findViewById(R.id.imageviewthuvienbaihat);
            txtbaihat = itemView.findViewById(R.id.textviewthuvienbaihat);
            layout_delete = itemView.findViewById(R.id.layout_delete);
        }
    }
}
