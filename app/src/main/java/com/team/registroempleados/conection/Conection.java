package com.team.registroempleados.conection;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.team.registroempleados.models.Empleado;

import java.util.ArrayList;
import java.util.List;

public class Conection {
    List<Empleado> lista = new ArrayList<Empleado>();
    ArrayAdapter<Empleado> ada;
    FirebaseDatabase f;
    DatabaseReference dbref;

    public  void inicializar(Context c){
        FirebaseApp.initializeApp(c);
        f = FirebaseDatabase.getInstance().getInstance(
                "https://registro-empleados-69f1e-default-rtdb.firebaseio.com/");
        dbref = f.getReference();
    }

    public void agregar( Empleado obj){
        dbref.child("Empleado").child(obj.getRFC()).setValue(obj);
    }

    public void leer(Context cntx, ListView l){

        dbref.child("Empleado").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                /*Se limpia la lista*/
                lista.clear();

                /*Recorremos los datos*/
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Empleado e = snap.getValue(Empleado.class);
                    lista.add(e);

                    ada = new ArrayAdapter<Empleado>(cntx, android.R.layout.simple_list_item_1, lista);

                }
                l.setAdapter(ada);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void editar(){

    }
    public void eliminar(){

    }



}

































