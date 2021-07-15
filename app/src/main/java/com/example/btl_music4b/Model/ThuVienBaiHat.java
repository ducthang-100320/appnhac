package com.example.btl_music4b.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ThuVienBaiHat implements Serializable {
    @SerializedName("ID")
    @Expose
    private int ID;
    @SerializedName("TenBaiHat")
    @Expose
    private String TenBaiHat;
    @SerializedName("HinhBaiHat")
    @Expose
    private String HinhBaiHat;
    @SerializedName("LinkBaiHat")
    @Expose
    private String LinkBaiHat;
    @SerializedName("TenCaSi")
    @Expose
    private String TenCaSi;

    public ThuVienBaiHat() {
    }

    public ThuVienBaiHat(int ID, String tenBaiHat, String hinhBaiHat, String linkBaiHat, String tenCaSi) {
        this.ID = ID;
        TenBaiHat = tenBaiHat;
        HinhBaiHat = hinhBaiHat;
        LinkBaiHat = linkBaiHat;
        TenCaSi = tenCaSi;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenBaiHat() {
        return TenBaiHat;
    }

    public void setTenBaiHat(String tenBaiHat) {
        TenBaiHat = tenBaiHat;
    }

    public String getHinhBaiHat() {
        return HinhBaiHat;
    }

    public void setHinhBaiHat(String hinhBaiHat) {
        HinhBaiHat = hinhBaiHat;
    }

    public String getLinkBaiHat() {
        return LinkBaiHat;
    }

    public void setLinkBaiHat(String linkBaiHat) {
        LinkBaiHat = linkBaiHat;
    }

    public String getTenCaSi() {
        return TenCaSi;
    }

    public void setTenCaSi(String tenCaSi) {
        TenCaSi = tenCaSi;
    }
}
