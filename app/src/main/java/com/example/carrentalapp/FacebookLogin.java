//activity_facebook
package com.example.carrentalapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carrentalapp.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.internal.WebDialog;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



public class FacebookLogin extends AppCompatActivity {
    //ritik
    private CallbackManager mCallBackManager;
    private FirebaseAuth mfirebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListner;
    private TextView textViewUser;
    private LoginButton loginButton;
    private ImageView mlogo;
    private static final String TAG="FacebookAuthentocation";
    private AccessTokenTracker accessTokenTracker;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);
        mfirebaseAuth =FirebaseAuth.getInstance();
        FacebookSdk.sdkInitialize(getApplicationContext());
        textViewUser=findViewById(R.id.text_user);
        mlogo=findViewById(R.id.image_logo);
        loginButton=findViewById(R.id.login_button);
        loginButton.setReadPermissions("email ","public_profile");
        mCallBackManager=CallbackManager.Factory.create();
        loginButton.registerCallback(mCallBackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG,"onSuccess"+loginResult);
                Intent intent =new Intent(FacebookLogin.this,NavigationDrawer.class);
                startActivity(intent);
                handleFacebookToken(loginResult.getAccessToken());
            }

            @Override
             public void onCancel() {
                Log.d(TAG,"onCancel");
            }

             @Override
            public void onError(FacebookException error) {
                Log.d(TAG,"onError"+error);
            }
        });
        authStateListner= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user!=null){
                    updateUI(user);

                }else{
                    updateUI(null);
                }
            }
        };
        accessTokenTracker=new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if(currentAccessToken==null){
                    mfirebaseAuth.signOut();
                }
            }
        };
    }
    private void handleFacebookToken(AccessToken token){
        Log.d(TAG,"handleFacebookToken"+token);
        AuthCredential credential= FacebookAuthProvider.getCredential(token.getToken());
        mfirebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d(TAG,"sign in with credential:succesful");
//                    Intent intent =new Intent(FacebookLogin.this,MainActivity.class);
//                    startActivity(intent);
                    FirebaseUser user=mfirebaseAuth.getCurrentUser();
                    updateUI(user);
                }else{
                    Log.d(TAG,"sign in with credential:Failure",task.getException());
                    Toast.makeText(FacebookLogin.this,"ATHENTICATION fAILED ",Toast.LENGTH_SHORT).show();


                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        mCallBackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void updateUI(FirebaseUser user){
        if(user!=null){
            textViewUser.setText(user.getDisplayName());
            if(user.getPhotoUrl()!=null){
                String photoUrl=user.getPhotoUrl().toString();
                photoUrl=photoUrl +"?tupe=large";
                Picasso.get().load(photoUrl).into(mlogo);

            }else
                textViewUser.setText(" ");
            mlogo.setImageResource(R.drawable.ic_launcher_foreground);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mfirebaseAuth.addAuthStateListener(authStateListner);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListner!=null){
            mfirebaseAuth.removeAuthStateListener(authStateListner);
        }
    }
}