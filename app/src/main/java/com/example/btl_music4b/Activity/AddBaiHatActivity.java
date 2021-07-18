package com.example.btl_music4b.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.btl_music4b.Adapter.ThuVienBaiHatAdapter;
import com.example.btl_music4b.Model.BaiHat;
import com.example.btl_music4b.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddBaiHatActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnAdd,btnCancel;
    EditText edtTenBaiHat,edtTenCaSi,edtHinhBaiHat,edtLinkBaiHat;
    int idEdit = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addbaihatactivity);
        mapping();
        btnAdd.setOnClickListener( this);
        btnCancel.setOnClickListener(this);
        Intent intent = getIntent();
        BaiHat baiHat = (BaiHat) intent.getParcelableExtra("studentEdit");
        //int ID = baiHat.getIdBaiHat();
        if(baiHat.getTenBaiHat() == null){
            edtTenBaiHat.setText("abc");
        }

        edtTenBaiHat.setText(baiHat.getTenBaiHat());
        Toast.makeText(this, "Bài Hát" +baiHat.getTenBaiHat(), Toast.LENGTH_LONG).show();
        edtHinhBaiHat.setText(baiHat.getHinhBaiHat());
        edtLinkBaiHat.setText(baiHat.getLinkBaiHat());
        edtTenCaSi.setText(baiHat.getTenCaSi());
        idEdit = baiHat.getIdBaiHat();
    }





    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAdd :
                insertBaiHat("https://appnhacdinhcao.000webhostapp.com/Server/insertthuvienbaihat.php");
                break;
            case R.id.btnCancel:
                finish();
                break;
        }
    }

    public void insertBaiHat(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("Success")){


                            Toast.makeText(AddBaiHatActivity.this, ""+response+"Thêm mới thành công", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(AddBaiHatActivity.this,HomeActivity.class));
                        }
                        else{
                            Toast.makeText(AddBaiHatActivity.this, response+"Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }



                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddBaiHatActivity.this, error+"Thêm thất bại", Toast.LENGTH_SHORT).show();

                    }
                }
        ) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("TenBaiHat",edtTenBaiHat.getText().toString());
                map.put("HinhBaiHat",edtHinhBaiHat.getText().toString());
                map.put("LinkBaiHat",edtLinkBaiHat.getText().toString());
                map.put("TenCaSi",edtTenCaSi.getText().toString());
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void mapping() {
        edtTenBaiHat = (EditText)findViewById(R.id.edtTenBaiHat);
        edtTenCaSi = (EditText)findViewById(R.id.edtTenCaSi);
        edtHinhBaiHat = (EditText)findViewById(R.id.edtHinhBaiHat);
        edtLinkBaiHat = (EditText)findViewById(R.id.edtLinkBaiHat);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnCancel = (Button)findViewById(R.id.btnCancel);
    }
}
