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
    private Empleado empleado = null;
    private boolean tipo= true;


    private void getElemento(){
        Conection con = new Conection();
        empleado = con.getCambio();
    }

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

        getElemento();
        if (empleado !=null){
            txtRFC.setText(empleado.getRFC());
            txtNombre.setText(empleado.getNombre());
            txtApellido.setText(empleado.getApellido());
            txtCargo.setText(empleado.getCargo());
            empleado = null;
            tipo = false;
            System.gc();
        }

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
                        Conection cnx = new Conection();
                        cnx.inicializar(MainActivity.this);
                        cnx.editar(new Empleado(
                                txtRFC.getText().toString().toUpperCase(),
                                txtNombre.getText().toString().toUpperCase(),
                                txtApellido.getText().toString().toUpperCase(),
                                txtCargo.getText().toString().toUpperCase()
                        ));

                        Toast.makeText(MainActivity.this, "Actualizado correctamente", Toast.LENGTH_SHORT).show();
                        tipo=true;
                        limpiarCajas();
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
                if (item.getItemId()== R.id.menuList){

                        Intent intent = new Intent(MainActivity.this,Mostrar_Elementos.class);
                        startActivity(intent);

                        Toast.makeText(MainActivity.this,
                                "listar",
                                Toast.LENGTH_SHORT).show();
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