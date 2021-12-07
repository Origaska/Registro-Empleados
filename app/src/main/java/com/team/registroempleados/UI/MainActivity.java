package com.team.registroempleados.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.team.registroempleados.R;
import com.team.registroempleados.conection.Conection;
import com.team.registroempleados.models.Empleado;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText txtRFC;
    private TextInputEditText txtNombre;
    private TextInputEditText txtApellido;
    private TextInputEditText txtCargo;
    private FloatingActionButton btnAdd;
    private BottomAppBar appbar;
    private boolean tipo= true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Definimos los elementos*/


        txtRFC = (TextInputEditText) findViewById(R.id.txtRFC);
        txtNombre = (TextInputEditText) findViewById(R.id.txtNombre);
        txtApellido = (TextInputEditText) findViewById(R.id.txtApellido);
        txtCargo = (TextInputEditText) findViewById(R.id.txtCargo);
        appbar = (BottomAppBar) findViewById(R.id.bottomAppBar);

        btnAdd = (FloatingActionButton) findViewById(R.id.btnAdd);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verComponentes()){
                        /*Todas Las cajas estan llenas*/
                    if (tipo){

                        /*Creamos Evento para el boton Agregar*/



                        Conection cnx = new Conection();
                        Empleado nuevoEmpleado = new Empleado(
                                txtRFC.getText().toString().toUpperCase(),
                                txtNombre.getText().toString().toUpperCase(),
                                txtApellido.getText().toString().toUpperCase(),
                                txtCargo.getText().toString().toUpperCase()
                        );
                        cnx.inicializar(MainActivity.this);
                        cnx.agregar(nuevoEmpleado);
                        Toast.makeText(MainActivity.this,
                                "Agregado a tabla Empleado",
                                Toast.LENGTH_SHORT).show();
                        limpiarCajas();
                    }else{


                        /*Evento para editar*/



                    }

                }else{
                    /*Ninguna o alguna caja no esta llena*/
                    Toast.makeText(MainActivity.this,
                            "Llena todos los campos",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });

        /*Evento de los demas botones*/

        appbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuEdit:
                        if (tipo){
                            item.setIcon(R.drawable.ic_edit_selected);
                            tipo = false;
                        }else{
                            item.setIcon(R.drawable.ic_edit);
                            tipo=true;
                        }


                        Toast.makeText(MainActivity.this,
                                "Editar",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menuDelete:
                        Toast.makeText(MainActivity.this,
                                "Eliminar",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menuList:

                        Intent intent = new Intent(MainActivity.this,Mostrar_Elementos.class);
                        startActivity(intent);

                        Toast.makeText(MainActivity.this,
                                "listar",
                                Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(MainActivity.this,
                                "Nada",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });



    }















    private void limpiarCajas() {
        TextInputEditText [] array = {txtRFC,txtNombre,txtApellido,txtCargo};
        for (TextInputEditText btn: array){
            btn.setText(null);
        }

    }

    private  boolean verComponentes(){
        TextInputEditText [] array = {txtRFC,txtNombre,txtApellido,txtCargo};
        for (TextInputEditText btn: array){
            if (btn.getText().toString().isEmpty()){
                return false;
            }
        }
        return true;
    }

}