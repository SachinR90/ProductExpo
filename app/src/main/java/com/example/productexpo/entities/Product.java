package com.example.productexpo.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product implements Parcelable {


    @SerializedName("productname")
    @Expose
    private String productName;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("vendorname")
    @Expose
    private String vendorName;
    @SerializedName("vendoraddress")
    @Expose
    private String vendorAddress;
    @SerializedName("productImg")
    @Expose
    private String productImg;
    @SerializedName("productGallery")
    @Expose
    private List<String> productGallery = null;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public List<String> getProductGallery() {
        return productGallery;
    }

    public void setProductGallery(List<String> productGallery) {
        this.productGallery = productGallery;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.productName);
        dest.writeString(this.price);
        dest.writeString(this.vendorName);
        dest.writeString(this.vendorAddress);
        dest.writeString(this.productImg);
        dest.writeStringList(this.productGallery);
        dest.writeString(this.phoneNumber);
    }

    public Product() {
    }

    protected Product(Parcel in) {
        this.productName = in.readString();
        this.price = in.readString();
        this.vendorName = in.readString();
        this.vendorAddress = in.readString();
        this.productImg = in.readString();
        this.productGallery = in.createStringArrayList();
        this.phoneNumber = in.readString();
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}