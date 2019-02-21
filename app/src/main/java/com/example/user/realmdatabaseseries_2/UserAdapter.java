package com.example.user.realmdatabaseseries_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends BaseAdapter {

    List<UserEntity> userEntityList;
    Context context;

    public UserAdapter(List<UserEntity> userEntityList, Context context) {
        this.userEntityList = userEntityList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return userEntityList.size();
    }

    @Override
    public Object getItem(int position) {
        return userEntityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.kisi_listeesi, null);
        TextView kullaniciAdi = convertView.findViewById(R.id.txtPassword);
        TextView password = convertView.findViewById(R.id.txtKulaniciAdi);
        TextView eMail = convertView.findViewById(R.id.txtEmail);
        kullaniciAdi.setText(userEntityList.get(position).getKullaniciAdi());
        password.setText(userEntityList.get(position).getPassword());
        eMail.setText(userEntityList.get(position).geteMail());
        return convertView;
    }
}
