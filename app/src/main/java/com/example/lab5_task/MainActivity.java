package com.example.lab5_task;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private ListView list_view;
    private ArrayAdapter aAdapter;
    private BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();
    ArrayList list = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button_on = (Button)findViewById(R.id.btnOn);
        Button button_off = (Button)findViewById(R.id.btnOFF);
        Button btn = (Button)findViewById(R.id.btnGet);


    }

    public void button_on(View v){
        if(bAdapter == null) {
            Toast.makeText(getApplicationContext(),"Bluetooth Not Supported",Toast.LENGTH_SHORT).show();
        }
        else{
            if(!bAdapter.isEnabled()){
                startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE),1);
                Toast.makeText(getApplicationContext(),"Bluetooth Turned ON",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void button_off(View v){
        bAdapter.disable();
        list.clear();
        aAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        list_view.setAdapter(aAdapter);
        Toast.makeText(getApplicationContext(),"Bluetooth Turned OFF", Toast.LENGTH_SHORT).show();
    }

    public void button_paired_devices(View v){
        if(bAdapter==null){
            Toast.makeText(getApplicationContext(),"Bluetooth Not Supported",Toast.LENGTH_SHORT).show();
        }
        else{
            Set<BluetoothDevice> pairedDevices = bAdapter.getBondedDevices();
            if(pairedDevices.size()>0){
                for(BluetoothDevice device: pairedDevices){
                    String device_name = device.getName();
                    list.add("Device Name: "+device_name);
                }
                list_view = (ListView) findViewById(R.id.deviceList);
                aAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
                list_view.setAdapter(aAdapter);
            }
        }
    }

}