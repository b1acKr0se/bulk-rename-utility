package io.b1ackr0se.bulkrenameutility.data.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
    public String name;
    public boolean isDirectory;
    public boolean canRead;
    public boolean isChecked;

    public Item(String name, boolean isDirectory, boolean canRead) {
        this.name = name;
        this.isDirectory = isDirectory;
        this.canRead = canRead;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeByte(this.isDirectory ? (byte) 1 : (byte) 0);
        dest.writeByte(this.canRead ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
    }

    protected Item(Parcel in) {
        this.name = in.readString();
        this.isDirectory = in.readByte() != 0;
        this.canRead = in.readByte() != 0;
        this.isChecked = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
