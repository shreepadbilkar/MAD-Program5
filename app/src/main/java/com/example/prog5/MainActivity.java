package com.example.prog5;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    private Button login;
    private Button register;
    private EditText name;
    private EditText phone;
    private DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=findViewById(R.id.login);
        register=findViewById(R.id.register);
        name=findViewById(R.id.name);
        phone=findViewById(R.id.number);
        dbHelper=new DbHelper(this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametxt=name.getText().toString();
                String phonetxt=phone.getText().toString();
                Boolean ckinsert=dbHelper.insertdata(nametxt,phonetxt);
                if(ckinsert==true){
                    Toast.makeText(getApplicationContext(), "insert successfully",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "failed",
                            Toast.LENGTH_SHORT).show();
                }
                dbHelper.close();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametxt=name.getText().toString();
                String phonetxt=phone.getText().toString();
                Boolean ck=dbHelper.cknameph(nametxt,phonetxt);
                if(ck==true){
                    Intent intent=new Intent(MainActivity.this,Login.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "failed to read",
                            Toast.LENGTH_SHORT).show();
                }
                dbHelper.close();
            }
        });
    }
}
