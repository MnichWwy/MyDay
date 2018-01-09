package e.hubertkowalski.myday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.List;

public class CreateAcc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acc);
    }

    public void createAccountMethod(View v){
        EditText createLogin = (EditText)findViewById(R.id.createAccLogin);
        EditText createPassword = (EditText)findViewById(R.id.createAccPassword);;
        if (createLogin.getText().toString().isEmpty()||createPassword.getText().toString().isEmpty()) return;
        List<User> users = User.listAll(User.class);
        if (!(users.contains(new User (createLogin.getText().toString(),createPassword.getText().toString()))))
        {
            User newUser = new User(createLogin.getText().toString(), createPassword.getText().toString());
            newUser.save();
            Intent i = new Intent(getApplicationContext(), LoggedIn.class);
            startActivity(i);
            finish();
        }
    }
}
