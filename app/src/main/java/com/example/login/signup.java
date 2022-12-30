package com.example.login;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.login.MainActivity;
import com.example.login.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
public class signup extends AppCompatActivity {
    Button signup,back;
    EditText username_var,password_var;
    ProgressBar p;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username_var=(EditText) findViewById(R.id.editTextTextEmailAddress2);
        password_var=(EditText) findViewById(R.id.editTextTextPassword2);
        password_var.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        signup=(Button) findViewById(R.id.button3);
        back=(Button) findViewById(R.id.button4);
        p=(ProgressBar) findViewById(R.id.progressBar);
        firebaseAuth=FirebaseAuth.getInstance();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1=username_var.getText().toString();
                String s2=password_var.toString().toString();
                if(s1.isEmpty()){
                    username_var.setError("Fill Email Please ");

                    return;
                }
                else{
                    if(s2.isEmpty()){
                        password_var.setError("Fill Password");
                        return;
                    }
                    else{
                        p.setVisibility(View.VISIBLE);

                        firebaseAuth.createUserWithEmailAndPassword(s1,s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult>

                                    task) {

                                if(task.isSuccessful()){
                                    p.setVisibility(View.INVISIBLE);
                                    Toast.makeText(signup.this,"Database Updated",Toast.LENGTH_SHORT).show();

                                            Intent i=new

                                                    Intent(signup.this,MainActivity.class);

                                    startActivity(i);
                                    finish();
                                }
                                else{
                                    Toast.makeText(signup.this,"Database Not Updated",Toast.LENGTH_SHORT).show();
                                            Intent i=new

                                                    Intent(signup.this,MainActivity.class);

                                    startActivity(i);
                                    finish();
                                }
                            }
                        });
                    }
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(signup.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}