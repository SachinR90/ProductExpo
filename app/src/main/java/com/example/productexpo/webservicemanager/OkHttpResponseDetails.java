package com.example.productexpo.webservicemanager;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Response;

/**
 * Cannot serialize Okhttp3 ResponseObject , even using gson. So, create a new object to hold our Response.
 * Get required fields from the {@link Response} and add it to this class.
 * <p></p><b>Note: This particular line "this.response = response.body().string();" must always be called in on the BackgroundThread.<br>
 * Also Note that the Response here is always in String format to cast it to your liking</b></p><br><br>
 * Created by SachinR on 28/Jan/2017.
 */
public class OkHttpResponseDetails implements Parcelable {
    public static final Parcelable.Creator<OkHttpResponseDetails> CREATOR = new Parcelable.Creator<OkHttpResponseDetails>() {
        @Override
        public OkHttpResponseDetails createFromParcel(Parcel source) {
            return new OkHttpResponseDetails(source);
        }

        @Override
        public OkHttpResponseDetails[] newArray(int size) {
            return new OkHttpResponseDetails[size];
        }
    };
    public String originalRequest;
    public int statusCode;
    public String message;
    public String response;
    public Object parsedObject;

    public OkHttpResponseDetails(Response response, Class clazz) {
        this.originalRequest = new Gson().toJson(response.request());
        this.statusCode = response.code();
        this.message = response.message();
        try {
            this.response = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            this.response = "";
        }
        try {
            this.parsedObject = new Gson().fromJson(this.response, clazz);
        } catch (Exception ex) {

        }
    }

    protected OkHttpResponseDetails(Parcel in) {
        this.originalRequest = in.readString();
        this.statusCode = in.readInt();
        this.message = in.readString();
        this.response = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.originalRequest);
        dest.writeInt(this.statusCode);
        dest.writeString(this.message);
        dest.writeString(this.response);
    }
}
