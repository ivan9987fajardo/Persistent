package johnivan.fajardo.com.persistent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText eName, ePass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eName= findViewById(R.id.etName);
        ePass = findViewById(R.id.password);


    }

    public void next(View v) {
        Intent i = new Intent(this,Activity2.class);
        startActivity(i);

    }

    public void saveData(View v){
        String uname = eName.getText().toString();
        String pass = ePass.getText().toString();
        SharedPreferences sp = getSharedPreferences("Data1", MODE_PRIVATE);
        SharedPreferences.Editor writer = sp.edit();
        writer.putString("user",uname);
        writer.putString("pass",pass);
        writer.commit();
        Toast.makeText(this,"Data Saved...",Toast.LENGTH_LONG).show();


    }

    public void saveInternal(View v) throws IOException {
        String uname = eName.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("Data2.txt",MODE_PRIVATE);
            fos.write(uname.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Saved in "+getFilesDir(),Toast.LENGTH_LONG).show();
    }
}
