package e.hubertkowalski.myday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;
import java.util.List;



public class FacebookLogin extends AppCompatActivity {

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private static final String TAG = FacebookLogin.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            FacebookSdk.sdkInitialize(this.getApplicationContext());
            setContentView(R.layout.activity_facebook_login);
            callbackManager = CallbackManager.Factory.create();
            loginButton = (LoginButton) findViewById(R.id.login_button);
            List<String> permissionNeeds = Arrays.asList("user_photos", "email", "user_birthday", "public_profile");
            loginButton.setReadPermissions(permissionNeeds);
            LoginManager.getInstance().logOut();
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    Intent i = new Intent(getApplicationContext(), LoggedIn.class);
                    startActivity(i);
                    finish();
                }

                @Override
                public void onCancel() {
                    Log.i("TAG", "Facebook login canceled.");
                }

                @Override
                public void onError(FacebookException exception) {
                    Log.v("LoginActivity", exception.getCause().toString());
                }

            });
        }catch (Exception e) {
            Log.e(TAG, "onCreateView", e);
            throw e;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
       try{Log.i("onactivityResult", "CALLED");
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);}catch (Exception e) {
        Log.e(TAG, "onCreateView", e);
        throw e;}
    }

    public void gotoRegistration(View v) {
        Intent i = new Intent(getApplicationContext(), CreateAcc.class);
        startActivity(i);
        finish();
    }

    public void checkLogin(View v) {
        EditText login = (EditText)findViewById(R.id.login);
        EditText password = (EditText)findViewById(R.id.password);
        if (login.getText().toString().isEmpty()||password.getText().toString().isEmpty()) return;
        List<User> users = User.listAll(User.class);
        User loggedIn = new User (login.getText().toString(),password.getText().toString());
        if (users.contains(loggedIn))
        {
            Intent i = new Intent(getApplicationContext(), LoggedIn.class);
            startActivity(i);
            finish();
        };

    }

    @Override
    public void onBackPressed()
    {
        finish();
    }

}

