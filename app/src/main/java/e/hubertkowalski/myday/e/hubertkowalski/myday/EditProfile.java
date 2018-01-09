package e.hubertkowalski.myday;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EditProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
    }

    public void changePassword(View v){
        EditText loginConf = (EditText)findViewById(R.id.loginInPasswordChange);
        EditText oldPass = (EditText)findViewById(R.id.oldPassword);
        EditText newPass = (EditText)findViewById(R.id.newPassword);
        if (loginConf.getText().toString().isEmpty()||oldPass.getText().toString().isEmpty()||newPass.getText().toString().isEmpty()) return;
        List<User> users = User.listAll(User.class);
        if ((users.contains(new User(loginConf.getText().toString(),oldPass.getText().toString()))))
        {

            List<User> user_list = new ArrayList<>();

            user_list = User.findWithQuery(User.class,"SELECT * FROM User WHERE password=? AND login=?",oldPass.getText().toString(),loginConf.getText().toString());

                    User userEdit= user_list.get(0);
                    userEdit.setPassword(newPass.getText().toString());
                    userEdit.save();


            Toast.makeText(this, "Hasło zmienione", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), LoggedIn.class);
            startActivity(i);
            finish();
        };
    }
}
