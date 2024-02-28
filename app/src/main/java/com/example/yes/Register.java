package com.example.yes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class
Register extends AppCompatActivity {

    EditText editText1, editText2;
    TextView Textview1, Textview2;
    Button boton1;
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        boton1 = findViewById(R.id.botonregister);
        Textview1 = findViewById(R.id.textViewRegister);
        editText1 = findViewById(R.id.editTextCorreo2);
        editText2 = findViewById(R.id.editTextContrasena2);
        Textview2 = findViewById(R.id.VolverRegister);
        mAuth = FirebaseAuth.getInstance();

        Textview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo, contrasena;
                correo = String.valueOf(editText1.getText());
                contrasena = String.valueOf(editText2.getText());

                if (TextUtils.isEmpty(correo)){
                    Toast.makeText(Register.this, "Ingresar correo",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(contrasena)){
                    Toast.makeText(Register.this, "Ingresar contrasena",Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(correo, contrasena)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Toast.makeText(Register.this, "Cuenta creada.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Register.this,( "Error al crear cuenta." + task.getException().getMessage()),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}