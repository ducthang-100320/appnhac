package com.example.btl_music4b.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.btl_music4b.Activity.HomeActivity;
import com.example.btl_music4b.Activity.MainActivity;
import com.example.btl_music4b.Adapter.ThuVienBaiHatAdapter;
import com.example.btl_music4b.Adapter.TimKiemAdapter;
import com.example.btl_music4b.Model.BaiHat;
import com.example.btl_music4b.R;
import com.example.btl_music4b.Service.APIService;
import com.example.btl_music4b.Service.Dataservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Tim_Kiem extends Fragment {
    private HomeActivity context;

    View view;
    androidx.appcompat.widget.Toolbar toolbar;
    RecyclerView recyclerViewtim;
    TextView textViewnull;
    TimKiemAdapter timKiemAdapter;
    ArrayList<BaiHat> mangbaihat;
    ImageView imageViewhinhtimkiem;
    TextView textViewtentimkiem,textViewtencasitimkiem;
    ThuVienBaiHatAdapter thuVienBaiHatAdapter;
    RecyclerView recyclerviewnthuvienbaihat;
    ArrayList<BaiHat> baiHats;
//    private  IinsertBaiHat mIinsertBaiHat;
//    public interface IinsertBaiHat{
//        void insertBaiHat(String url);
//    }

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        mIinsertBaiHat = (IinsertBaiHat) getActivity();
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tim_kiem, container, false);
        toolbar = view.findViewById(R.id.toilbartimkiem);
        recyclerViewtim = view.findViewById(R.id.recyclerviewtimkiem);
        textViewnull = view.findViewById(R.id.textviewtimkiemnull);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        return  view;
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.menu_timkiem, menu);
        MenuItem menuItem = menu.findItem(R.id.menutimkiem);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                recyclerViewtim.setBackgroundColor(Color.BLACK);
                TimKiemBaiHat(s);
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void TimKiemBaiHat(String query){
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetTimkiembaihat(query);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                if (mangbaihat.size() > 0){
                    timKiemAdapter = new TimKiemAdapter((HomeActivity) getActivity(), mangbaihat);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerViewtim.setLayoutManager(linearLayoutManager);
                    recyclerViewtim.setAdapter(timKiemAdapter);
                    textViewnull.setVisibility(View.GONE);
                    recyclerViewtim.setVisibility(View.VISIBLE);
                }else {
                    recyclerViewtim.setVisibility(View.GONE);
                    textViewnull.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }



}
