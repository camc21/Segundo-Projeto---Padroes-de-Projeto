package br.com.fatec.project.appbluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnConnect, btnLed1, btnLed2, btnLed3;

    private static final int SOLICITA_ATIVACAO = 1;

    private static final int SOLICITA_CONEXAO = 2;


    BluetoothAdapter meuBlueToothAdapter = null;

    boolean conexao = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConnect = (Button)findViewById(R.id.btnConnect);
        btnLed1 = (Button)findViewById(R.id.btnLed1);
        btnLed2 = (Button)findViewById(R.id.btnLed2);
        btnLed3 = (Button)findViewById(R.id.btnLed3);

        meuBlueToothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (meuBlueToothAdapter == null){
            Toast.makeText(getApplicationContext(),"Seu dispositivo não possui bluetooth",Toast.LENGTH_LONG).show();
        }
        else if(!meuBlueToothAdapter.isEnabled()){
            Intent ativaBlueTooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(ativaBlueTooth, SOLICITA_ATIVACAO);
        }

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(conexao){

                }
                else{
                    Intent abreLista = new Intent(MainActivity.this, ListaDispositivos.class);
                    startActivityForResult(abreLista, SOLICITA_CONEXAO);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case SOLICITA_ATIVACAO:
                if(resultCode == Activity.RESULT_OK){
                    Toast.makeText(getApplicationContext(),"O bluetooth foi ativado!",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"O bluetooth não foi ativado, o app será encerrado!",Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }
}
