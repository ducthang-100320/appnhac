package com.example.btl_music4b.Service;

import com.example.btl_music4b.Model.BaiHat;
import com.example.btl_music4b.Model.BangXepHang;
import com.example.btl_music4b.Model.ChuDe;
import com.example.btl_music4b.Model.NgheSi;
import com.example.btl_music4b.Model.PhoBien;
import com.example.btl_music4b.Model.Playlist;
import com.example.btl_music4b.Model.ThinhHanh;
import com.example.btl_music4b.Model.ThuVienBaiHat;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {
    @GET("playlistcurrentday.php")
    Call<List<Playlist>> GetPlaylistCurrentDay();

    @GET("nghesicurrent.php")
    Call<List<NgheSi>> GetNgheSiCurrent();

    @GET("thinhhanhcurrent.php")
    Call<List<ThinhHanh>> GetThinhHanhCurrent();

    @GET("phobiencurrent.php")
    Call<List<PhoBien>> GetPhoBienCurrent();

    @GET("chudecurrent.php")
    Call<List<ChuDe>> GetChuDeCurrent();
    @GET("getDataMusic.php")
    Call<List<BaiHat>> GetDanhsachbaihat();

    @GET("thuvienbaihat.php")
    Call<List<ThuVienBaiHat>> GetThuVienbaihat();

    @GET("bangxephangcurrent.php")
    Call<List<BangXepHang>> GetBangXepHangCurrent();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaihatplaylist(@Field("idPlayList") String id);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaihatnghesi(@Field("idNgheSi") String id);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaihatthinhhanh(@Field("idThinhHanh") String id);
    @FormUrlEncoded
    @POST("getDataMusic.php")
    Call<List<BaiHat>> GetDanhsachbaihat(@Field("IdBaiHat") String id);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaihatphobien(@Field("idPhoBien") String id);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaihatchude(@Field("idChuDe") String id);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaihatbangxephang(@Field("idBangXepHang") String id);

    @FormUrlEncoded
    @POST("timkiembaihat.php")
    Call<List<BaiHat>> GetTimkiembaihat(@Field("tukhoa") String tukhoa);

    ;
}
