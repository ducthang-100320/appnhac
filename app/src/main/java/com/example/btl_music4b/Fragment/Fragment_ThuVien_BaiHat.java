package com.example.btl_music4b.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_music4b.Activity.DanhsachbaihatActivity;
import com.example.btl_music4b.Activity.MainActivity;
import com.example.btl_music4b.Adapter.ThuVienBaiHatAdapter;
import com.example.btl_music4b.Adapter.ThuVienNgheSiAdapter;
import com.example.btl_music4b.Adapter.ViewPagerThuVien;
import com.example.btl_music4b.Adapter.ViewPagerThuVienBaihat;
import com.example.btl_music4b.AddBaiHat;
import com.example.btl_music4b.Model.BaiHat;
import com.example.btl_music4b.Model.NgheSi;
import com.example.btl_music4b.R;
import com.example.btl_music4b.Service.APIService;
import com.example.btl_music4b.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ThuVien_BaiHat extends Fragment {
    private Fragment_ThuVien_BaiHat context;
    View view;
    Menu mnuAddBaiHat;
    Button btnAddBaiHat;
    ThuVienBaiHatAdapter thuVienBaiHatAdapter;
    RecyclerView recyclerviewnthuvienbaihat;
//    public Fragment_ThuVien_BaiHat(Fragment_ThuVien_BaiHat context) {
//        this.context = context;
//
//    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thuvien_baihat, container, false);
        recyclerviewnthuvienbaihat = view.findViewById(R.id.recyclerviewnthuvienbaihat);
        btnAddBaiHat = view.findViewById(R.id.btnAddBaiHat);
        GetData();
//        btnAddBaiHat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, AddBaiHat.class);
//                context.startActivity(intent);
//
//            }
//        });
        //init();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhsachbaihat();
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> mangbaihat = (ArrayList<BaiHat>) response.body();
                thuVienBaiHatAdapter = new ThuVienBaiHatAdapter(getActivity(), mangbaihat);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerviewnthuvienbaihat.setLayoutManager(linearLayoutManager);
                recyclerviewnthuvienbaihat.setAdapter(thuVienBaiHatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }

        });
    }


}
