package com.example.btl_music4b.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.btl_music4b.Adapter.MainViewPagerAdapter;
import com.example.btl_music4b.Adapter.ThuVienBaiHatAdapter;
import com.example.btl_music4b.Fragment.Fragment_ThuVien_BaiHat;
import com.example.btl_music4b.Fragment.Fragment_Thu_Vien;
import com.example.btl_music4b.Fragment.Fragment_Tim_Kiem;
import com.example.btl_music4b.Fragment.Fragment_Trang_Chu;
import com.example.btl_music4b.Fragment.LoadingDialog;
import com.example.btl_music4b.Model.BaiHat;
import com.example.btl_music4b.Model.ThuVienBaiHat;
import com.example.btl_music4b.R;
import com.example.btl_music4b.Service.APIService;
import com.example.btl_music4b.Service.Dataservice;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class HomeActivity extends AppCompatActivity{

    TabLayout tabLayout;
    ViewPager viewPager;
    private HomeActivity context;
    ImageView imageViewhinhtimkiem;
    TextView textViewtentimkiem,textViewtencasitimkiem;
    ThuVienBaiHatAdapter thuVienBaiHatAdapter;
    RecyclerView recyclerviewnthuvienbaihat;
    ArrayList<BaiHat> baiHats;
    int idEdit = -1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final LoadingDialog loadingDialog = new LoadingDialog(HomeActivity.this);
        AnhXa();
        loadingDialog.StartLoadingDialog();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingDialog.dismissDialog();
            }
        }, 7500);
        init();
        overridePendingTransition(R.anim.anim_intent_in_home, R.anim.anim_intent_out);
//        Intent intent = getIntent();
//        BaiHat baiHat = (BaiHat) intent.getSerializableExtra("studentEdit");
//        textViewtentimkiem.setText(baiHat.getTenBaiHat());
//        String HinhBaiHat = "https://appnhacdinhcao.000webhostapp.com/HinhAnh/NgheSi/S%C6%A1n%20T%C3%B9ng.jpg";
//        String LinkBaiHat = "https://appnhacdinhcao.000webhostapp.com/BaiHat/ChungTaKhongThuocVeNhau-SonTungMTP-4528181.mp3";
//        textViewtencasitimkiem.setText(baiHat.getTenCaSi());
//        idEdit = baiHat.getIdBaiHat();
    }

    private void init() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_Trang_Chu(), "");
        mainViewPagerAdapter.addFragment(new Fragment_Tim_Kiem(), "");
        mainViewPagerAdapter.addFragment(new Fragment_Thu_Vien(), "");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.icontimkiem);
        tabLayout.getTabAt(2).setIcon(R.drawable.iconthuvien);
    }

    private void AnhXa() {
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
    }
    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<ThuVienBaiHat>> callback = dataservice.GetThuVienbaihat();
        callback.enqueue(new Callback<List<ThuVienBaiHat>>() {
            @Override
            public void onResponse(Call<List<ThuVienBaiHat>> call, retrofit2.Response<List<ThuVienBaiHat>> response) {
                ArrayList<ThuVienBaiHat> thuvienbaihat = (ArrayList<ThuVienBaiHat>) response.body();
                thuVienBaiHatAdapter = new ThuVienBaiHatAdapter(HomeActivity.this, thuvienbaihat);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeActivity.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerviewnthuvienbaihat.setLayoutManager(linearLayoutManager);
                recyclerviewnthuvienbaihat.setAdapter(thuVienBaiHatAdapter);
            }

            @Override
            public void onFailure(Call<List<ThuVienBaiHat>> call, Throwable t) {

            }

        });
    }
    public  void  deleteItem(final int ID){
        String url = "https://appnhacdinhcao.000webhostapp.com/Server/deletethuvienbaihat.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("Success")){
                            Toast.makeText(HomeActivity.this, "Xóa thành công bài hát khỏi thư viện", Toast.LENGTH_LONG).show();
//                            GetData();
                        }
                        else {

                            Toast.makeText(HomeActivity.this, "Xóa không Thành Công", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
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

//    @Override
//    public void insertBaiHat(String url) {
////        Fragment_ThuVien_BaiHat fragment2 = getSupportFragmentManager().findFragmentById(R.id.fr)
//
//    }
//    public  void  addItem(View v){
//
//
//        String TenBaiHat = textViewtentimkiem.getText().toString().trim();
//        //Picasso.with(context).load(imageViewhinhtimkiem.getHinhBaiHat());
//        String HinhBaiHat = "https://appnhacdinhcao.000webhostapp.com/HinhAnh/NgheSi/S%C6%A1n%20T%C3%B9ng.jpg";
//        String LinkBaiHat = "https://appnhacdinhcao.000webhostapp.com/BaiHat/ChungTaKhongThuocVeNhau-SonTungMTP-4528181.mp3";
//        String TenCaSi = textViewtencasitimkiem.getText().toString().trim();
//
//        if(TenBaiHat.equals("")||HinhBaiHat.equals("")||LinkBaiHat.equals("")||TenCaSi.equals("")){
//            Toast.makeText(context, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
//            if(TenBaiHat.equals("")){
//                textViewtentimkiem.requestFocus();
//            }
//            else if(HinhBaiHat.equals("")){
//                imageViewhinhtimkiem.requestFocus();
//            }
//            else {
//                textViewtencasitimkiem.requestFocus();
//            }
//        }
//        else {
//           insertBaiHat("https://appnhacdinhcao.000webhostapp.com/Server/insertthuvienbaihat.php");
//        }
//    }
//
//    public void insertBaiHat(String url) {
//        RequestQueue requestQueue = Volley.newRequestQueue(context);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new com.android.volley.Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        if (response.equals("Success")){
//                            Toast.makeText(HomeActivity.this, ""+response+"Thêm mới thành công", Toast.LENGTH_SHORT).show();
//                            GetData();
//
//                        }
//                        else{
//                            Toast.makeText(HomeActivity.this, response+"Thêm thất bại", Toast.LENGTH_SHORT).show();
//                        }
//                        startActivity(new Intent(AddBaiHatActivity.this,MainActivity.class));
//
//
//                    }
//                },
//                new com.android.volley.Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(context, error+"Thêm thất bại", Toast.LENGTH_SHORT).show();
//
//                    }
//                }
//        ) {
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> map = new HashMap<>();
//                map.put("TenBaiHat",textViewtentimkiem.getText().toString());
//                map.put("HinhBaiHat","https://appnhacdinhcao.000webhostapp.com/HinhAnh/NgheSi/S%C6%A1n%20T%C3%B9ng.jpg");
//                map.put("LinkBaiHat","https://appnhacdinhcao.000webhostapp.com/BaiHat/ChungTaKhongThuocVeNhau-SonTungMTP-4528181.mp3");
//                map.put("TenCaSi",textViewtencasitimkiem.getText().toString());
//                return map;
//            }
//        };
//        requestQueue.add(stringRequest);
//    }



}