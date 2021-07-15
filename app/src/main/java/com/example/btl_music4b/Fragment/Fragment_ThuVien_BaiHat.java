package com.example.btl_music4b.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.btl_music4b.Activity.HomeActivity;
import com.example.btl_music4b.Adapter.ThuVienBaiHatAdapter;
import com.example.btl_music4b.Model.ThuVienBaiHat;
import com.example.btl_music4b.R;
import com.example.btl_music4b.Service.APIService;
import com.example.btl_music4b.Service.Dataservice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class Fragment_ThuVien_BaiHat extends Fragment {
    private HomeActivity context;
    Context context;
    View view;
    ArrayList<ThuVienBaiHat> thuVienBaiHatList;
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
        Call<List<ThuVienBaiHat>> callback = dataservice.GetThuVienbaihat();
        callback.enqueue(new Callback<List<ThuVienBaiHat>>() {
            @Override
            public void onResponse(Call<List<ThuVienBaiHat>> call, Response<List<ThuVienBaiHat>> response) {
                ArrayList<ThuVienBaiHat> thuvienbaihat = (ArrayList<ThuVienBaiHat>) response.body();
                thuVienBaiHatAdapter = new ThuVienBaiHatAdapter(getActivity(), thuvienbaihat);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
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
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("Success")){
                            Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                            GetData();
                        }
                        else {

                            Toast.makeText(context, "Xóa không Thành Công", Toast.LENGTH_SHORT).show();
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
