package com.debuggersstudio.taskbook;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActvitity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
   private EditText emails;
   private EditText pass;
    private Button signBtn;
    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_actvitity);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);


        getSupportActionBar().setCustomView(R.layout.abs_layout);


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


         emails = (EditText) findViewById(R.id.edittextEmail);
         pass = (EditText) findViewById(R.id.edittextpassword);

         mAuth =FirebaseAuth.getInstance();
         signBtn = (Button)findViewById(R.id.SignBtn);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    Intent mainintent = new Intent(LoginActvitity.this,TaskMain.class);
                    startActivity(mainintent);

                }
                // ...
            }
        };

        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SingIn();
            }
        });




    }


    @Override
    protected void onStart() {

        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);

    }

    public void SingIn(){

       String email  = emails.getText().toString();
        String passs = pass.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(passs)) {

           Toast.makeText(this,"Fill Information for Sign in",Toast.LENGTH_LONG).show();

        }else{

            mAuth.createUserWithEmailAndPassword(email, passs)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Intent mainintent = new Intent(LoginActvitity.this,TaskMain.class);
                                startActivity(mainintent);
                            }

                            // ...
                        }
                    });


        }

    }
}
