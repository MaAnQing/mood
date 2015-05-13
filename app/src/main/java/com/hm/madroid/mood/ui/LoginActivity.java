package com.hm.madroid.mood.ui;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.google.android.gms.common.SignInButton;

import com.hm.madroid.mood.Keeper;
import com.hm.madroid.mood.R;

/**
 * A login screen that offers login via email/password and via Google+ sign in.
 * <p/>
 * ************ IMPORTANT SETUP NOTES: ************
 * In order for Google+ sign in to work with your app, you must first go to:
 * https://developers.google.com/+/mobile/android/getting-started#step_1_enable_the_google_api
 * and follow the steps in "Step 1" to create an OAuth 2.0 client for your package.
 */
public class LoginActivity extends BaseActivity {

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mEmailLoginFormView;
    private SignInButton mPlusSignInButton;
    private View mSignOutButtons;
    private View mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Keeper.isLogin()){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView(){

        mLoginButton = findViewById(R.id.email_sign_in_button) ;
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.email_sign_in_button :
                startActivity(new Intent(this, MainActivity.class));
                Keeper.keepLogin(true);
                finish();
                break;
            default:
                break;
        }
    }
}

