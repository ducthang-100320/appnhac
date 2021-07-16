package com.example.btl_music4b.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
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
import com.example.btl_music4b.Fragment.Fragment_Thu_Vien;
import com.example.btl_music4b.Fragment.Fragment_Tim_Kiem;
import com.example.btl_music4b.Fragment.Fragment_Trang_Chu;
import com.example.btl_music4b.Fragment.LoadingDialog;
import com.example.btl_music4b.Model.ThuVienBaiHat;
import com.example.btl_music4b.R;
import com.example.btl_music4b.Service.APIService;
import com.example.btl_music4b.Service.Dataservice;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class HomeActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    private HomeActivity context;

    ThuVienBaiHatAdapter thuVienBaiHatAdapter;
    RecyclerView recyclerviewnthuvienbaihat;





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
                            Toast.makeText(HomeActivity.this, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                            GetData();
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




}