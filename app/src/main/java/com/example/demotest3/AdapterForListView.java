package com.example.demotest3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterForListView extends BaseAdapter {

    ArrayList<DienThoai> listDienThoai;

    public AdapterForListView(ArrayList<DienThoai> listDienThoai){
        this.listDienThoai= listDienThoai;
    }


    @Override
    public int getCount() {
        return listDienThoai.size();
    }

    @Override
    public Object getItem(int position) {
        return listDienThoai.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_for_listview,parent,false);

        TextView tvNhanHieu = root.findViewById(R.id.tvNhanHieu);
        TextView tvGia = root.findViewById(R.id.tvGia);
        TextView tvSoluong = root.findViewById(R.id.tvSoluong);

        DienThoai dienThoai = (DienThoai) getItem(position);

        tvNhanHieu.setText(dienThoai.getNhanHieu());
        tvGia.setText(String.valueOf(dienThoai.getGia()));
        tvSoluong.setText(String.valueOf(dienThoai.getSoLuong()));

        return root;
    }
}
