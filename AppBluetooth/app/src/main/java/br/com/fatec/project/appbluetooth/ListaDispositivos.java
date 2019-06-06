package br.com.fatec.project.appbluetooth;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class ListaDispositivos extends ListActivity {

    private BluetoothAdapter meuBloutoothAdapter = null;

    static String ENDERECO_MAC = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<String> arrayBluetooth = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        meuBloutoothAdapter = BluetoothAdapter.getDefaultAdapter();

        Set<BluetoothDevice> dispositivosPareados = meuBloutoothAdapter.getBondedDevices();

        if(dispositivosPareados.size() > 0){
            for(BluetoothDevice disp : dispositivosPareados){
                String nomeBT = disp.getName();
                String macBT  = disp.getAddress();
                arrayBluetooth.add(nomeBT + "\n" + macBT);
            }
        }

        setListAdapter(arrayBluetooth);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String informacaoGeral = ((TextView)v).toString();
        Toast.makeText(getApplicationContext(),"Info: " + informacaoGeral, Toast.LENGTH_LONG).show();
    }
}
