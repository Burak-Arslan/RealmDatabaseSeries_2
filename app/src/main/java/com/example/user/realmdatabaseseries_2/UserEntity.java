package com.example.user.realmdatabaseseries_2;

import io.realm.RealmObject;

public class UserEntity extends RealmObject {

    private String kullaniciAdi;
    private String password;
    private String eMail;

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "kullaniciAdi='" + kullaniciAdi + '\'' +
                ", password='" + password + '\'' +
                ", eMail='" + eMail + '\'' +
                '}';
    }
}
