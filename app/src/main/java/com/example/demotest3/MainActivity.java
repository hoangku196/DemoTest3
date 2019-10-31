package com.example.demotest3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SQLiteForDienThoai sqLiteForDienThoai;

    private AdapterForListView adapterForListView;
    private ArrayList<DienThoai> listDienThoai;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqLiteForDienThoai = new SQLiteForDienThoai(this);

        listDienThoai = new ArrayList<>(sqLiteForDienThoai.listDienThoai());
        adapterForListView = new AdapterForListView(listDienThoai);
        adapterForListView.notifyDataSetChanged();

        Log.e("List", listDienThoai.size()+"");

        listView = findViewById(R.id.lvDienThoai);
        listView.setAdapter(adapterForListView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                xoaDienThoai(position);
            }
        });
    }

    public void addNhanHieu(View view) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_them);
        dialog.setTitle("Thêm");

        final EditText edNhapNhanHieu = dialog.findViewById(R.id.edtNhapNhanHieu);
        final EditText edNhapGia = dialog.findViewById(R.id.edtNhapGia);
        final EditText edNhapSoLuong = dialog.findViewById(R.id.edtNhapSoLuong);

        Button btnSave = dialog.findViewById(R.id.btnSaveDienThoai);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edNhapNhanHieu.getText().toString().equals("")&&edNhapGia.getText().toString().equals("")&&edNhapSoLuong.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"Không được bỏ trống",Toast.LENGTH_SHORT).show();
                }else{
                    if (Float.parseFloat(edNhapGia.getText().toString())<200000){
                        Toast.makeText(MainActivity.this,"Không được nhỏ hơn 200000",Toast.LENGTH_SHORT).show();
                    }else{
                        sqLiteForDienThoai.addDienThoai(new DienThoai(edNhapNhanHieu.getText().toString(),Float.parseFloat(edNhapGia.getText().toString()),Integer.parseInt(edNhapSoLuong.getText().toString())));
                    }
                }
            }
        });
        dialog.show();
    }

    public void editNhanHieu(View view) {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_sua);
        dialog.setTitle("Sửa");

        List<String> listNhanHieu = new ArrayList<>(sqLiteForDienThoai.listNhanHieu());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_item,listNhanHieu);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        final Spinner spnNhanHieu = dialog.findViewById(R.id.spnNhanHieu);
        spnNhanHieu.setAdapter(adapter);

        final EditText edtSuaGia = dialog.findViewById(R.id.edtSuaGia);
        Button btnSuaDienThoai = dialog.findViewById(R.id.btnSuaDienThoai);
        btnSuaDienThoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Float.parseFloat(edtSuaGia.getText().toString())<200000){
                    Toast.makeText(MainActivity.this,"Không được nhỏ hơn 200000",Toast.LENGTH_SHORT).show();
                }else{
                    sqLiteForDienThoai.updateDienThoai(spnNhanHieu.getSelectedItem().toString(),Float.parseFloat(edtSuaGia.getText().toString()));
                }
            }
        });
        dialog.show();
    }

    public void xoaDienThoai(final int position){
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setTitle("Xóa");
        dialog.setContentView(R.layout.dialog_xoa);
        Button btnXoa = dialog.findViewById(R.id.btnXoa);
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DienThoai dienThoai = listDienThoai.get(position);
                sqLiteForDienThoai.deleteDienThoai(dienThoai.getNhanHieu());
            }
        });

        dialog.show();
    }
}
