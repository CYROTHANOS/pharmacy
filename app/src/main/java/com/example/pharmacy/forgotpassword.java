package com.example.pharmacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {

    Button buttonforgotbtn;
    Button buttonloginbtn;
    TextInputEditText editTextusername;
    FirebaseAuth auth;
    String username;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        auth = FirebaseAuth.getInstance();
        editTextusername = findViewById(R.id.username);
        buttonforgotbtn = findViewById(R.id.forgotbtn);
        buttonloginbtn = findViewById(R.id.loginbtn);

        buttonloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });






        buttonforgotbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }


        });
    }
    public void validateData() {

        String username;
       username= String.valueOf(editTextusername.getText());
        if (TextUtils.isEmpty(username)){
            Toast.makeText(forgotpassword.this, "enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
             forgetPass();
        }
    }

    public void forgetPass() {
        auth.sendPasswordResetEmail(String.valueOf(editTextusername.getText()))
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(forgotpassword.this, "check your mail", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            finish();

                        }else{
                            Toast.makeText(forgotpassword.this, "Error" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}
