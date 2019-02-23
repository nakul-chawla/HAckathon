package com.buzz.flash.hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SugnUp extends AppCompatActivity {
    EditText mail;
    EditText pass;
    Button submit;
    static int idi;
    static String iemail;
    static String ipassword;
    static int ilogin;
    //   View view;
    ProgressBar progressBar;
    DatabaseReference mref;
    Sign s[];
    String id[];
    String pas[];
    int slogin[];
    TextView t1;
    TextView t2;
    TextView login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        s=new Sign[20];
        id=new String[20];
        pas=new String[20];
        slogin=new int[20];

        login=findViewById(R.id.textView);
        //mail = (EditText) view.findViewById(R.id.mail);
        mail=(EditText)findViewById(R.id.mail) ;
        pass = (EditText)findViewById(R.id.editText3);
        submit = (Button)findViewById(R.id.button4);
        t1=findViewById(R.id.textView5);
        t2=findViewById(R.id.textView2);
        progressBar=findViewById(R.id.progressBar2);

        mref= FirebaseDatabase.getInstance().getReference().child("SignUp");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(int i=1;i<19;i++){
                    s[i]=dataSnapshot.child(""+i).getValue(Sign.class);

                    id[i]=s[i].getEmail();
                    pas[i]=s[i].getPassword();
                    slogin[i]=s[i].getLogin();

                    progressBar.setVisibility(View.INVISIBLE);
                    t1.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.VISIBLE);
                    mail.setVisibility(View.VISIBLE);
                    pass.setVisibility(View.VISIBLE);
                    submit.setVisibility(View.VISIBLE);
                    login.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mail.getText().toString().equals("")) {
                    //Toast.makeText(SugnUp.this, "Enter email address!", Toast.LENGTH_LONG).show();
                    Toast.makeText(SugnUp.this, "Enter email", Toast.LENGTH_SHORT).show();
                }

//yes
                if (pass.getText().toString().equals("")) {
                    Toast.makeText(SugnUp.this, "Enter password!", Toast.LENGTH_LONG).show();
                }
                if(!(mail.getText().toString().equals(""))&&!(pass.getText().toString().equals(""))) {
                    for (int i = 1; i < 2; i++) {
                        if (mail.getText().toString().compareTo(id[i]) == 0 && pass.getText().toString().compareTo(pas[i]) != 0) {
                            Toast.makeText(SugnUp.this, "Wrong Id Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                    for (int i = 1; i < 2; i++) {
                        if (mail.getText().toString().compareTo(id[i]) == 0 && pass.getText().toString().compareTo(pas[i]) == 0) {
                            idi = i;
                            iemail = id[i];
                            ipassword = pas[i];
                            s[i].setLogin(1);
                            mref.child("" + i).setValue(s[i]);
                            Toast.makeText(SugnUp.this, "Loged in", Toast.LENGTH_LONG).show();
                        }

                    }
                }
            }
        });

    }
}
