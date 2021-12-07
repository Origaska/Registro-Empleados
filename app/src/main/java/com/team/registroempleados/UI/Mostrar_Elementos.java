package com.team.registroempleados.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.team.registroempleados.R;
import com.team.registroempleados.conection.Conection;
import com.team.registroempleados.models.Empleado;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class Mostrar_Elementos extends AppCompatActivity {
    ListView lista;
    boolean tipoEd = true;
    boolean tipoDel = true;
    BottomAppBar appbar;
    Conection cnt = new Conection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_elementos);
        appbar = (BottomAppBar) findViewById(R.id.bottomAppBar);
        cnt.inicializar(this);
        listar();



        /*Editar-Eliminar*/



        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Empleado EmpleadoSeleccionado = (Empleado) parent.getItemAtPosition(position);

                if (!tipoDel){
                    cnt.eliminar(EmpleadoSeleccionado);
                    Toast.makeText(Mostrar_Elementos.this, "Eliminado correctamente", Toast.LENGTH_SHORT).show();

                }else if (!tipoEd){
                    cnt.setCambio(EmpleadoSeleccionado);
                    Intent intent = new Intent(Mostrar_Elementos.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });





        appbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuEdit:
                        if (tipoEd){
                            if (tipoDel){
                                item.setIcon(R.drawable.ic_edit_selected);
                                tipoEd = false;
                            }
                        }else{
                            item.setIcon(R.drawable.ic_edit);
                            tipoEd = true;
                        }
                        break;
                    case R.id.menuDelete:

                        if (tipoDel){
                            if (tipoEd){
                                item.setIcon(R.drawable.ic_delete_selected);
                                tipoDel = false;
                            }

                        }else{
                            item.setIcon(R.drawable.ic_delete);
                            tipoDel=true;
                        }
                        break;
                    case R.id.menuList:

                        listar();
                        break;
                }
                return true;
            }
        });
    }

    private void listar() {
        lista = (ListView) findViewById(R.id.idLista);
        cnt.leer(Mostrar_Elementos.this,lista);
    }
}