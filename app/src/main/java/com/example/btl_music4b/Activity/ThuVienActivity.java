package com.example.btl_music4b.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.btl_music4b.Adapter.TVBaiHatAdapter;
import com.example.btl_music4b.Model.ThuVienBaiHat;
import com.example.btl_music4b.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ThuVienActivity extends AppCompatActivity {
    //private Fragment_ThuVien_BaiHat thuvien = new Fragment_ThuVien_BaiHat();
    private ThuVienActivity context;
    View view;
    ArrayList<ThuVienBaiHat> thuvienbaihat;
    TVBaiHatAdapter tvBaiHatAdapter = null;
    RecyclerView recyclerviewnthuvienbaihat;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerviewnthuvienbaihat = findViewById(R.id.recyclerviewnthuvienbaihat);
        thuvienbaihat = new ArrayList<>();
        tvBaiHatAdapter = new TVBaiHatAdapter(ThuVienActivity.this,R.layout.dong_thuvien_baihat,thuvienbaihat);
        recyclerviewnthuvienbaihat.setAdapter(tvBaiHatAdapter);
        GetData();

    }
    public void GetData(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url  = "https://appnhacdinhcao.000webhostapp.com/Server/thuvienbaihat.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        thuvienbaihat.clear();
                        for (int i = 0; i<response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                thuvienbaihat.add(new ThuVienBaiHat(jsonObject.getInt("ID"),jsonObject.getString("TenBaiHat"),jsonObject.getString("HinhBaiHat"),jsonObject.getString("LinkBaiHat"),jsonObject.getString("TenCaSi")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        tvBaiHatAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ThuVienActivity.this, ""+error.getMessage() , Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }



    public  void  deleteItem(final int ID){
        String url = "https://appnhacdinhcao.000webhostapp.com/Server/deletethuvienbaihat.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("Success")){
                            Toast.makeText(ThuVienActivity.this, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                            GetData();
                        }
                        else {
                            Toast.makeText(ThuVienActivity.this, "Xóa không Thành Công", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("ID",String.valueOf(ID));
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
