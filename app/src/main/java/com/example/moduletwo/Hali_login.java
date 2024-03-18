package com.example.moduletwo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.moduletwo.databinding.ActivityHaliLoginBinding;
import com.example.moduletwo.databinding.ActivityLoginBinding;
import com.example.moduletwo.modelclass.Moduletwo_viewmodel;
import com.example.moduletwo.networkinit.Network_initialization;
import com.example.moduletwo.networkinit.Networkcheck;
import com.example.moduletwo.respostory.PrefController;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Hali_login extends AppCompatActivity implements Networkcheck {

    ActivityHaliLoginBinding activityHaliLoginBinding;
    Moduletwo_viewmodel moduletwoViewmodel;
    public Networkcheck networkcheck;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    Long timeout_sec = 60L;
    String verification_code = "";
    PhoneAuthProvider.ForceResendingToken resendingToken;
    private Boolean is_resend = true;
    public Network_initialization networkInitialization;
    public ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHaliLoginBinding = ActivityHaliLoginBinding.inflate(getLayoutInflater());
        setContentView(activityHaliLoginBinding.getRoot());
        activityHaliLoginBinding.setLifecycleOwner(this);
        networkcheck = this;
        networkInitialization = new Network_initialization(getApplicationContext(),networkcheck);
        moduletwoViewmodel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(Moduletwo_viewmodel.class);

        activityHaliLoginBinding.haliNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(moduletwoViewmodel.moduletwoRepo.is_network_connected) {
                    moduletwoViewmodel.phn_num.setValue(activityHaliLoginBinding.haliNum.getText().toString());
                    sendOtp();
                }else {

                }
            }
        });

        activityHaliLoginBinding.haliLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verify_otp();

            }
        });


    }


    public void sendOtp() {

        hideKeyboard(this);
        show_progress_bar(true);
        PhoneAuthOptions.Builder phn_builder = PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber("+91" + moduletwoViewmodel.phn_num.getValue())
                .setTimeout(timeout_sec, TimeUnit.SECONDS)
                .setActivity(Hali_login.this)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        show_progress_bar(false);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        show_progress_bar(false);
                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        show_progress_bar(false);
                        verification_code = s;
                        resendingToken = forceResendingToken;
                        activityHaliLoginBinding.numberLayout.setVisibility(View.GONE);
                        activityHaliLoginBinding.loginLayout.setVisibility(View.VISIBLE);
                    }
                });
        if (!is_resend) {
            PhoneAuthProvider.verifyPhoneNumber(phn_builder.setForceResendingToken(resendingToken).build());
        } else {
            PhoneAuthProvider.verifyPhoneNumber(phn_builder.build());
        }
    }

    public void verify_otp() {
        show_progress_bar(true);
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verification_code, activityHaliLoginBinding.haliOtp.getText().toString());
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    PrefController.saveData("login_success","Yes",getApplicationContext());
                    Intent intent = new Intent(Hali_login.this,MainActivity.class);
                    startActivity(intent);
                } else {

                    Toast.makeText(getApplicationContext(), "Otp verification failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void show_progress_bar(Boolean is_show) {
        if (is_show) {

            activityHaliLoginBinding.loading.setIndeterminate(true);
            activityHaliLoginBinding.loading.setVisibility(View.VISIBLE);
        } else {

            activityHaliLoginBinding.loading.setIndeterminate(false);
            activityHaliLoginBinding.loading.setVisibility(View.GONE);
        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void check_network(Boolean isconnected) {
        if (isconnected) {
            moduletwoViewmodel.moduletwoRepo.is_network_connected = true;
        } else {
            moduletwoViewmodel.moduletwoRepo.is_network_connected = false;
        }
    }
}