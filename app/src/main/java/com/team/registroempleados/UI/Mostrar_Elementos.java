package com.team.registroempleados.UI;

import androidx.appcompat.app.AppCompatActivity;
import com.team.registroempleados.R;
import com.team.registroempleados.conection.Conection;

import android.os.Bundle;
import android.widget.ListView;

public class Mostrar_Elementos extends AppCompatActivity {
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_elementos);

        Conection cnt = new Conection();
        cnt.inicializar(this);
        lista = (ListView) findViewById(R.id.idLista);
        cnt.leer(Mostrar_Elementos.this,lista);
    }
}