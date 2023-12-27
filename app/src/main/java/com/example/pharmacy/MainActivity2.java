package com.example.pharmacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {

    TextInputEditText editTextname,editTextphone,editTextaddress,editTextemail,editTextpassword;
    Button buttonsave;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView textView;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mAuth= FirebaseAuth.getInstance();
        editTextemail= findViewById(R.id.email);
        editTextname= findViewById(R.id.name);
        editTextaddress= findViewById(R.id.address);
        editTextphone= findViewById(R.id.phone);
        editTextpassword= findViewById(R.id.password);
       buttonsave=findViewById(R.id.btn_save);
       progressBar =findViewById(R.id.progressBar);
       textView=findViewById(R.id.loginnow);
       textView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent =new Intent(getApplicationContext(),MainActivity.class);
               startActivity(intent);
               finish();




           }
       });


     buttonsave.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             progressBar.setVisibility(View.VISIBLE);
             String email,password,phone,address,name;
             email= String.valueOf(editTextemail.getText());
             address= String.valueOf(editTextaddress.getText());
             password=String.valueOf(editTextpassword.getText());
             phone= String.valueOf(editTextphone.getText());
             name=String.valueOf(editTextname.getText());

             if (TextUtils.isEmpty(email)){
                 Toast.makeText(MainActivity2.this, "enter email", Toast.LENGTH_SHORT).show();
                 return;
             }
             if (TextUtils.isEmpty(password)){
                 Toast.makeText(MainActivity2.this, "enter password", Toast.LENGTH_SHORT).show();
                 return;
             }

             mAuth.createUserWithEmailAndPassword(email, password)
                     .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             if (task.isSuccessful()) {
                                 progressBar.setVisibility(View.GONE);
                                 Toast.makeText(MainActivity2.this, "Account created",
                                         Toast.LENGTH_SHORT).show();


                             } else {
                                 // If sign in fails, display a message to the user.

                                 Toast.makeText(MainActivity2.this, "Authentication failed.",
                                         Toast.LENGTH_SHORT).show();

                             }
                         }
                     });


         }
     });


    }
}