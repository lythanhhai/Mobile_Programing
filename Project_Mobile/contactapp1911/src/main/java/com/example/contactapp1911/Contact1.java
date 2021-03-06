package com.example.contactapp1911;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact1 {
    @PrimaryKey (autoGenerate = true)
    private int id;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String mobile;
    @ColumnInfo
    private String email;
    @ColumnInfo
    private String url;

    public Contact1(String name, String mobile, String email, String url) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
