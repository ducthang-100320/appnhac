package com.example.btl_music4b.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.btl_music4b.Adapter.DanhsachbaihatAdapter;
import com.example.btl_music4b.Adapter.ThuVienBaiHatAdapter;
import com.example.btl_music4b.Model.BaiHat;
import com.example.btl_music4b.Model.BangXepHang;
import com.example.btl_music4b.Model.ChuDe;
import com.example.btl_music4b.Model.NgheSi;
import com.example.btl_music4b.Model.PhoBien;
import com.example.btl_music4b.Model.Playlist;
import com.example.btl_music4b.Model.ThinhHanh;
import com.example.btl_music4b.Model.ThuVienBaiHat;
import com.example.btl_music4b.R;
import com.example.btl_music4b.Service.APIService;
import com.example.btl_music4b.Service.Dataservice;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihatActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    androidx.appcompat.widget.Toolbar toolbar;
    RecyclerView recyclerViewdanhsachbaihat;
    FloatingActionButton floatingActionButton;
    TextView txtcollapsing;
    Playlist playList = null;
    NgheSi ngheSi = null;
    PhoBien phoBien = null;
    ThinhHanh thinhHanh = null;
    ChuDe chuDe = null;
    BangXepHang bangXepHang = null;
    ThuVienBaiHat thuVienBaiHat = null;
    ImageView imgdanhsachcakhuc;
    ArrayList<BaiHat> mangbaihat;
    ArrayList<ThuVienBaiHat> thuvienbaihat;
    DanhsachbaihatAdapter danhsachbaihatAdapter;
    ThuVienBaiHatAdapter thuVienBaiHatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaihat);
        AnhXa();
        floatingActionButton.setEnabled(false);
        DataIntent();
        overridePendingTransition(R.anim.anim_intent_in, R.anim.anim_intent_out);
        if (ngheSi != null && !ngheSi.equals("")){
            setValueInView(ngheSi.getTenNgheSi(), ngheSi.getHinhNgheSi());
            GetDataNgheSi(ngheSi.getIdNgheSi());
            txtcollapsing.setText(ngheSi.getTenNgheSi());
            getSupportActionBar().setTitle(ngheSi.getTenNgheSi());
        }
        if (playList != null && !playList.equals("")){
            setValueInView(playList.getTen(), playList.getHinhPlaylist());
            GetDataPlaylist(playList.getIdPlaylist());
            txtcollapsing.setText(playList.getTen());
            getSupportActionBar().setTitle(playList.getTen());
        }

        if (thinhHanh != null && !thinhHanh.equals("")){
            setValueInView(thinhHanh.getTenThinhHanh(), thinhHanh.getHinhThinhHanh());
            GetDataThinhHanh(thinhHanh.getIdThinhHanh());
            txtcollapsing.setText(thinhHanh.getTenThinhHanh());
            getSupportActionBar().setTitle(thinhHanh.getTenThinhHanh());
        }
        if (phoBien != null && !phoBien.equals("")){
            setValueInView(phoBien.getTenPhoBien(), phoBien.getHinhPhoBien());
            GetDataPhoBien(phoBien.getIdPhoBien());
            txtcollapsing.setText(phoBien.getTenPhoBien());
            getSupportActionBar().setTitle(phoBien.getTenPhoBien());
        }
        if (chuDe != null && !chuDe.equals("")){
            setValueInView(chuDe.getTenChuDe(), chuDe.getHinhChuDe());
            GetDataChuDe(chuDe.getIdChuDe());
            txtcollapsing.setText(chuDe.getTenChuDe());
            getSupportActionBar().setTitle(chuDe.getTenChuDe());
        }
        if (bangXepHang != null && !bangXepHang.equals("")){
            setValueInView(bangXepHang.getTenBangXepHang(), bangXepHang.getHinhBangXepHang());
            GetDataBangXepHang(bangXepHang.getIdBangXepHang());
            txtcollapsing.setText(bangXepHang.getTenBangXepHang());
            getSupportActionBar().setTitle(bangXepHang.getTenBangXepHang());
        }
//        if (thuVienBaiHat != null && !thuVienBaiHat.equals("")){
//            setValueInView(thuVienBaiHat.getTenBaiHat(), thuVienBaiHat.getHinhBaiHat());
//            GetDataThuVien();
//            txtcollapsing.setText(thuVienBaiHat.getTenBaiHat());
//            getSupportActionBar().setTitle(thuVienBaiHat.getTenBaiHat());
//        }

        floatActionButtonClick();

    }

    private void setValueInView(String ten, String hinh) {
/*        try {
            URL url = new URL(hinh);
            Bitmap  bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
                collapsingToolbarLayout.setBackground(bitmapDrawable);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        Picasso.with(this).load(hinh).into(imgdanhsachcakhuc);
    }
    private void GetDataPlaylist(String id) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhsachbaihatplaylist(id);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
    private void GetDataBangXepHang(String id) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhsachbaihatbangxephang(id);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
    private void GetDataChuDe(String id) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhsachbaihatchude(id);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
    private void GetDataNgheSi(String id) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhsachbaihatnghesi(id);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
//    private void GetDataThuVien() {
//        Dataservice dataservice = APIService.getService();
//        Call<List<ThuVienBaiHat>> callback = dataservice.GetThuVienbaihat();
//        callback.enqueue(new Callback<List<ThuVienBaiHat>>() {
//            @Override
//            public void onResponse(Call<List<ThuVienBaiHat>> call, Response<List<ThuVienBaiHat>> response) {
//                thuvienbaihat = (ArrayList<ThuVienBaiHat>) response.body();
//                thuVienBaiHatAdapter = new ThuVienBaiHatAdapter(HomeActivity.this, thuvienbaihat);
//                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
//                recyclerViewdanhsachbaihat.setAdapter(thuVienBaiHatAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<List<ThuVienBaiHat>> call, Throwable t) {
//
//            }
//        });
//    }
    private void GetDataPhoBien(String id) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhsachbaihatphobien(id);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
    private void GetDataThinhHanh(String id) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhsachbaihatthinhhanh(id);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void GetDanhsachbaihat(String id) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhsachbaihat(id);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void AnhXa() {
        coordinatorLayout = findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        toolbar = findViewById(R.id.toolbardanhsachbaihat);
        recyclerViewdanhsachbaihat = findViewById(R.id.recyclerviewdanhsachbaihat);
        imgdanhsachcakhuc = findViewById(R.id.imageviewdanhsachcakhuc);
        floatingActionButton = findViewById(R.id.floatingactionbutton);
        txtcollapsing = findViewById(R.id.textViewcollapsing);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent != null){
            if (intent.hasExtra("intentplaylist")){
                playList = (Playlist) intent.getSerializableExtra("intentplaylist");
            }else
            if (intent.hasExtra("intentnghesi")){
                ngheSi = (NgheSi) intent.getSerializableExtra("intentnghesi");
            }else
            if (intent.hasExtra("intentthinhhanh")){
                thinhHanh = (ThinhHanh) intent.getSerializableExtra("intentthinhhanh");
            }else
            if (intent.hasExtra("intentphobien")){
                phoBien = (PhoBien) intent.getSerializableExtra("intentphobien");
            }else
            if (intent.hasExtra("intentchude")){
                chuDe = (ChuDe) intent.getSerializableExtra("intentchude");
            }else
            if (intent.hasExtra("intentbangxephang")){
                bangXepHang = (BangXepHang) intent.getSerializableExtra("intentbangxephang");
            }
            if (intent.hasExtra("intentthuvien")){
                thuVienBaiHat = (ThuVienBaiHat) intent.getSerializableExtra("intentthuvien");
            }
        }
    }
    private void floatActionButtonClick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhsachbaihatActivity.this, PlayNhacActivity.class);
                intent.putExtra("cacbaihat", mangbaihat);
                startActivity(intent);
            }
        });
    }
}