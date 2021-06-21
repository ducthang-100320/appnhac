package com.example.btl_music4b.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_music4b.Adapter.ThuVienBaiHatAdapter;
import com.example.btl_music4b.Adapter.ThuVienNgheSiAdapter;
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
    View view;
    ThuVienBaiHatAdapter thuVienBaiHatAdapter;
    RecyclerView recyclerviewnthuvienbaihat;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thuvien_baihat, container, false);
        recyclerviewnthuvienbaihat = view.findViewById(R.id.recyclerviewnthuvienbaihat);
        GetData();
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
