package com.knit.dailywagesworkers;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class activity_agent_login extends AppCompatActivity {


    private Button btn1;
    EditText emailId,pass;
    Button loginUp,forgetPass;

    FirebaseAuth mAuth;
    TextView phoneLogin,customerLgn;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(getApplicationContext(), inside_agent.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_login);

        mAuth=FirebaseAuth.getInstance();
        btn1=findViewById(R.id.createAccount2);
        emailId=findViewById(R.id.inputEmail5);
        pass=findViewById(R.id.inputPassword2);
        loginUp=findViewById(R.id.btnLogin2);
        forgetPass=findViewById(R.id.forgotPassword2);

        phoneLogin=(TextView)findViewById(R.id.phoneOtp1);
        customerLgn=(TextView)findViewById(R.id.customerL);
        ProgressBar progressBar = findViewById(R.id.progressBar);


        // Set the title to an empty string of an ActionBar
        getSupportActionBar().setTitle(" Agent Login");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.light_blue2)));
        }


        customerLgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Customer_login.class);
                startActivity(intent);
            }
        });

        phoneLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), sendOtp.class));
            }
        });

        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),forget_agent_password.class);
                startActivity(intent);
                finish();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAgentSignup();
            }
        });

        loginUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;
                email=String.valueOf(emailId.getText());
                password=String.valueOf(pass.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(activity_agent_login.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(activity_agent_login.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                loginUp.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information

                                    Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(getApplicationContext(), inside_agent.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(activity_agent_login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });
    }
    public void openAgentSignup(){
        Intent intent=new Intent(this, agent_signupaccount.class);
        startActivity(intent);
    }

}
