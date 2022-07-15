package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    public static EditText email_id,phone,password,conform_password;
    public static String emails,phones,passwords,conform_passwords;
    public static Button sign_up;
    public static  FirebaseDatabase database;
    public static  DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        email_id=findViewById(R.id.enter_email);
        phone=findViewById(R.id.enter_phone);
        password=findViewById(R.id.enter_password);
        conform_password=findViewById(R.id.conform_password);
        sign_up=findViewById(R.id.button_signup);
        database = FirebaseDatabase.getInstance();

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emails=email_id.getText().toString();
                phones=phone.getText().toString();
                passwords=password.getText().toString();
                conform_passwords=conform_password.getText().toString();
                createprofile();
            }
        });
    }
    private void createprofile() {

        if(!phones.isEmpty()&&!passwords.isEmpty()&&!conform_passwords.isEmpty()&&!emails.isEmpty()){
            if(passwords.equals(conform_passwords)){

                 myRef=database.getReference("Data");

                myRef.child("Email").setValue(emails);
                myRef.child("phone").setValue(phones);
                myRef.child("password").setValue(conform_passwords);

                Toast.makeText(this, "Profile created SUCCESS", Toast.LENGTH_SHORT).show();
            }else{

                conform_password.setError("Password Mismatch");
            }
        }else{
            Toast.makeText(this, "Fill the Missing area", Toast.LENGTH_SHORT).show();
        }
    }
}