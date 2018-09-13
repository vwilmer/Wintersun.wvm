package me.winter.mytrainingapp.examples.somes;

import android.os.Parcel;
import android.os.Parcelable;

public class SubTitle implements Parcelable {

    private String name;

    // begin here
    private String fecha, desc;

    // constructor
    public SubTitle(String name,
                    String fecha,
                    String desc) {
        this.name = name;
        this.fecha = fecha;
        this.desc = desc;
    }

    protected SubTitle(Parcel in) {
        name = in.readString();
        fecha = in.readString();
        desc = in.readString();
    }

    public static final Creator<SubTitle> CREATOR = new Creator<SubTitle>() {
        @Override
        public SubTitle createFromParcel(Parcel in) {
            return new SubTitle(in);
        }

        @Override
        public SubTitle[] newArray(int size) {
            return new SubTitle[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setNames(String name) {
        this.name = name;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(fecha);
        dest.writeString(desc);
    }

    //    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

//    public SubTitle(String name) {
//        this.name = name;
//    }

    // ---------- default methods generated ----------------
//    public static final Creator<SubTitle> CREATOR = new Creator<SubTitle>() {
//        @Override
//        public SubTitle createFromParcel(Parcel in) {
//            return new SubTitle(in);
//        }
//
//        @Override
//        public SubTitle[] newArray(int size) {
//            return new SubTitle[size];
//        }
//    };
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(name);
//    }
//
//    protected SubTitle(Parcel in) {
//        name = in.readString();
//    }

    // ---------- default methods generated ----------------
}
