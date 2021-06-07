package com.example.carrentalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.carrentalapp.Models.Users;
import com.example.carrentalapp.databinding.ActivitySignInBinding;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookActivity;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.util.Arrays;


public class SignInActivity extends AppCompatActivity {
    ActivitySignInBinding binding;
    ProgressDialog progressDialog;
    FirebaseAuth auth;
    GoogleSignInClient mGoogleSignInClient;
    FirebaseDatabase database;
    CallbackManager callbackManager;
  //  LoginButton loginButton;
   // private static final String EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
//        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        //
       binding=ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth=FirebaseAuth.getInstance();
        getSupportActionBar().hide();
        //
        progressDialog=new ProgressDialog(SignInActivity.this);
        progressDialog.setTitle(" Login");
        progressDialog.setMessage(" Login in to your Account ");
        //
        database=FirebaseDatabase.getInstance();
        //for facebook
    //    callbackManager = CallbackManager.Factory.create();
       // loginButton =(LoginButton) findViewById(R.id.btFacebook);
 //loginButton= (LoginButton) binding.btFacebook.getRootView();


   //    loginButton =  findViewById(binding.btFacebook.getId());
    //   loginButton= (LoginButton) binding.btFacebook.getRootView();
   //     loginButton.setReadPermissions(Arrays.asList(EMAIL));

         binding.btFacebook.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
//                 loginButton =  findViewById(binding.btFacebook.getId());
//                 loginButton.setReadPermissions(Arrays.asList(EMAIL));
                 Intent intent=new Intent(SignInActivity.this, FacebookLogin.class);
                 startActivity(intent);
                Toast.makeText(SignInActivity.this,"something Wrong",Toast.LENGTH_SHORT).show();
//LoginManager.getInstance()

                 LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        loginResult.getAccessToken();
                        facebookHandlercode(loginResult.getAccessToken());
                        Toast.makeText(SignInActivity.this,"something Wrong",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                        public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
             }
         });

        //for facebook
        //Configure Facebook Sign In
//        callbackManager = CallbackManager.Factory.create();
//        login=findViewById(binding.btFacebook.getId());
//        callbackManager = CallbackManager.Factory.create();
//        login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//            Toast.makeText(SignInActivity.this,"You Have logged in with Facebook",Toast.LENGTH_SHORT).show();
//            Intent intent=new Intent(SignInActivity.this,MainActivity.class);
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                Toast.makeText(SignInActivity.this,"Error",Toast.LENGTH_SHORT).show();
//
//            }
//        });

//        login=findViewById(R.id.btFacebook);

        // facebook ending-->Part1
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

       mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                auth.signInWithEmailAndPassword(binding.etEmail.getText().toString(),binding.etPassword.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(SignInActivity.this, NavigationDrawer.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignInActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }  });
            }
        });
        binding.etClickForSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignInActivity.this,Signup.class);
                startActivity(intent);

            }
        });
        //To keep logged in each time
        if(auth.getCurrentUser()!=null){
            Intent intent=new Intent(SignInActivity.this,NavigationDrawer.class);
            startActivity(intent);
        }binding.btGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        }
        //facebook
    protected void onstart(){
        super.onStart();
       FirebaseUser user= auth.getCurrentUser();
       if(user!=null){
           Intent intent=new Intent(SignInActivity.this,NavigationDrawer.class);
           startActivity(intent);

       }
    }
        private void facebookHandlercode(AccessToken token){
        AuthCredential cred= FacebookAuthProvider.getCredential(token.getToken());
        auth.signInWithCredential(cred).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //Change for google signin date 16/05/21
                    Intent intent=new Intent(SignInActivity.this,NavigationDrawer.class);
                    startActivity(intent);
                }
            }
        });
        }
    //facebook
        int RC_SIGN_IN=65;
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);//this intent takes us from home page to google sign in page
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
       //facebook
//        callbackManager.onActivityResult(requestCode, resultCode, data);
// Toast.makeText(SignInActivity.this,"something Wrong in activity",Toast.LENGTH_LONG).show();
////
        super.onActivityResult(requestCode, resultCode, data);

        //for Facebook Only Last part
   //     callbackManager.onActivityResult(requestCode,resultCode,data);
        //End of Facebook last Part
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d("TAG", "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("TAG", "Google sign in failed", e);
            }
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            //Now storing google data in firebase database and adding profile pic
                            Users users=new Users();
                            FirebaseUser user = auth.getCurrentUser();
                            users.setUserId(user.getUid());
                            users.setUsername(user.getDisplayName());
                            users.setProfilepic(user.getPhotoUrl().toString());
                            database.getReference().child("Users").child(user.getUid()).setValue(users);
                            // 16/05
//                            Intent intent =new Intent(SignInActivity.this,MainActivity.class);
                            Intent intent =new Intent(SignInActivity.this,NavigationDrawer.class);

                            //16/05

                            startActivity(intent);
                            Toast.makeText(SignInActivity.this,"Sign in with google",Toast.LENGTH_SHORT).show();
                            //FirebaseUser user = auth.getCurrentUser();
                           // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                             Toast.makeText(SignInActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            Snackbar.make(binding.getRoot(),"Authentication failed",Snackbar.LENGTH_SHORT).show();
//                              updateUI(null);
                        }
                    }
                });
    }

}
