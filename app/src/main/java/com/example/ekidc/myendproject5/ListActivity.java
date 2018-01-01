package com.example.ekidc.myendproject5;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private Button btnJump, clear ,btnmap;
    private ListView listMyData;
    private ArrayAdapter<String> myDataAdapter;

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(data!=null){
        switch (resultCode){

            case 0:
                myDataAdapter.add("日期 : "+data.getStringExtra("Date")+'\n'
                                 +"時間 : "+data.getStringExtra("Time")+'\n'
                                 +"地點 : "+data.getStringExtra("Position"));
                break;}
        }
        listMyData.setOnItemClickListener(listViewOnItemClickListener);
        listMyData.setOnItemLongClickListener(onItemLongSel);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        clear = (Button) findViewById(R.id.clear);
        clear.setOnClickListener(clearOnClickListener);

        btnJump = (Button) findViewById(R.id.btnJump);
        btnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(ListActivity.this, ListAddActivity.class);
                startActivityForResult(i, 0);
            }
        });
        btnmap = (Button) findViewById(R.id.btnmap);
        btnmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(ListActivity.this, MapsActivity.class);
                startActivityForResult(i, 0);
            }
        });

        ArrayList<String> myDataList = new ArrayList<String>();
        myDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,myDataList);

        listMyData = (ListView) findViewById(R.id.listMyData);
        listMyData.setAdapter(myDataAdapter);
    }


    private Button.OnClickListener clearOnClickListener
            = new Button.OnClickListener(){
        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub

            AlertDialog.Builder delAlertDialog = new AlertDialog.Builder(ListActivity.this);
            delAlertDialog.setTitle("是否要全部清除");

            delAlertDialog.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    myDataAdapter.clear();
                    myDataAdapter.notifyDataSetChanged();
                }
            });

            delAlertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface arg0, int arg1) {
                }
            });

            delAlertDialog.show();
        }
    };

    private AdapterView.OnItemLongClickListener onItemLongSel = new AdapterView.OnItemLongClickListener(){
        @Override
        public boolean onItemLongClick(AdapterView parent, View view, int position, long id){
            final String strSelectedItem = parent.getItemAtPosition(position).toString();
            AlertDialog.Builder delAlertDialog = new AlertDialog.Builder(ListActivity.this);
            delAlertDialog.setTitle("是否要刪除此項目?");
            delAlertDialog.setMessage(strSelectedItem);

            delAlertDialog.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    myDataAdapter.remove(strSelectedItem);
                    myDataAdapter.notifyDataSetChanged();
                }
            });

            delAlertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {

                }
            });

            delAlertDialog.show();
            return true;
        }
    };

    private ListView.OnItemClickListener listViewOnItemClickListener
            = new ListView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent();
            intent.setClass(ListActivity.this,ListAddActivity.class);
            Bundle bundle = new Bundle();
            /*intent.putExtra("Date", "00000"+myDataAdapter.getItem(position));
            intent.putExtra("Time", "111111");
            intent.putExtra("Position", "22222");*/
            String a = myDataAdapter.getItem(position);
            String[] AfterSplit = a.split(":|\n", 7);//AfterSplit.length
            String[] b = AfterSplit[1].split(" ", 2);
            intent.putExtra("Date",b[1]);
            b = AfterSplit[3].split(" ", 2);
            intent.putExtra("Time", b[1]+":"+AfterSplit[4]);
            b =AfterSplit[AfterSplit.length-1].split(" ", 2);
            intent.putExtra("Position",b[1]);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };
}