package com.example.btl_music4b.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.btl_music4b.Activity.PlayNhacActivity;
import com.example.btl_music4b.Model.BaiHat;
import com.example.btl_music4b.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TimKiemAdapter extends RecyclerView.Adapter<TimKiemAdapter.ViewHolder>{

    Context context;
    ArrayList<BaiHat> mangbaihat;
    TextView textViewtencasitimkiem,textViewtentimkiem;
    ImageView imageViewhinhtimkiem;
    TextView txttentimkiem, txtcasitimkiem;
    ImageView imganhtimkiem;

    public TimKiemAdapter(Context context, ArrayList<BaiHat> mangbaihat) {
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
        BaiHat baiHat = mangbaihat.get(position);
        holder.txttentimkiem.setText(baiHat.getTenBaiHat());
        holder.txtcasitimkiem.setText(baiHat.getTenCaSi());
        Picasso.with(context).load(baiHat.getHinhBaiHat()).into(holder.imganhtimkiem);
        holder.imageViewtimtimkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem(v);
                Toast.makeText(context, "Đây là nút thêm vào thư viện", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public  void  addItem(View v){
        String TenBaiHat = txttentimkiem.getText().toString().trim();
//        String HinhBaiHat = Picasso.with(context).load(imganhtimkiem.getText().toString().trim());
        String LinkBaiHat = textViewtentimkiem.getText().toString().trim();
        String TenCaSi = txtcasitimkiem.getText().toString().trim();

//       if(TenBaiHat.equals("")||HinhBaiHat.equals("")||TenCaSi.equals("")){
//            Toast.makeText(context, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
//           if(TenBaiHat.equals("")){
//               textViewtentimkiem.requestFocus();
//            }
//            else if(HinhBaiHat.equals("")){
//               textViewtencasitimkiem.requestFocus();
//            }
//           else if(LinkBaiHat.equals("")){
//               textViewtentimkiem.requestFocus();
//           }else {
//               textViewtencasitimkiem.requestFocus();
//            }
//        }
//        else {
            insertStudent("https://appnhacdinhcao.000webhostapp.com/Server/insertthuvienbaihat.php");
//        }
    }
    private void insertStudent(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.equals("Success")){
                            Toast.makeText(context, ""+response+"Thêm mới thành công", Toast.LENGTH_SHORT).show();


                        }
                        else{
                            Toast.makeText(context, response+"Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error+"Thêm thất bại", Toast.LENGTH_SHORT).show();

                    }
                }
        ) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("TenBaiHat",txttentimkiem.getText().toString());
                map.put("HinhBaiHat",textViewtencasitimkiem.getText().toString());
                map.put("LinkBaiHat",textViewtentimkiem.getText().toString());
                map.put("TenCaSi",txtcasitimkiem.getText().toString());
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txttentimkiem, txtcasitimkiem;
        ImageView imganhtimkiem,imageViewtimtimkiem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttentimkiem = itemView.findViewById(R.id.textViewtentimkiem);
            txtcasitimkiem = itemView.findViewById(R.id.textViewtencasitimkiem);
            imganhtimkiem = itemView.findViewById(R.id.imageViewhinhtimkiem);
            imageViewtimtimkiem = itemView.findViewById(R.id.imageViewtimtimkiem);

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
