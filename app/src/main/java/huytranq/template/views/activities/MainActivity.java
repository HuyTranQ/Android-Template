package huytranq.template.views.activities;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import huytranq.template.R;
import huytranq.template.models.User;
import huytranq.template.presenters.asyncs.InsertUserThread;
import huytranq.template.presenters.utilities.Resource;
import huytranq.template.presenters.utilities.UserDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText editUsername , editPassword , editBirthday;
    private Button submit;
    private static boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize() {
        editUsername = (EditText) findViewById(R.id.username);
        editPassword = (EditText) findViewById(R.id.password);
        editBirthday = (EditText) findViewById(R.id.birthday);
        submit = (Button) findViewById(R.id.submit);

        setUp();
    }

    private void setUp() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editUsername.getText().toString();
                Log.d("debug", editPassword.getText().toString());
                byte[] password = editPassword.getText().toString().getBytes(Charset.forName("UTF-8"));
                Log.d("debug", "length of bytes = " + password.length);
                String birthday = editBirthday.getText().toString();
                User user = new User(-1, username, password, birthday);
                new InsertUserThread(MainActivity.this, user).execute();
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (exit) {
            super.onBackPressed();
            UserDatabase.getInstance(MainActivity.this).close();
            return;
        }

        exit = true;
        Toast.makeText(MainActivity.this , "Press \"Back\" again to exit" , Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                exit = false;
            }
        } , 3000);
    }
}
