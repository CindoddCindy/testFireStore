package magang.project.firestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import magang.project.firestore.view.AddData;
import magang.project.firestore.view.LoginUser;

public class MainActivity extends AppCompatActivity {
    private int waktu_loading=4000;

    //4000=4 detik
    private static final String NAME_KEY = "Name";
    private static final String EMAIL_KEY = "Email";
    private static final String PHONE_KEY = "Phone";

    FirebaseFirestore db;
    public TextView textView_tampil_data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView_tampil_data=findViewById(R.id.tv_tampil_data);

        this.db = FirebaseFirestore.getInstance();

        addNewContact();
/*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //setelah loading maka akan langsung berpindah ke home activity
                Intent home=new Intent(MainActivity.this, LoginUser.class);
                startActivity(home);
                finish();

            }
        },waktu_loading);

 */
    }

    private void addNewContact() {
        Map< String, Object > newContact = new HashMap< >();
        newContact.put(NAME_KEY, "John");
        newContact.put(EMAIL_KEY, "john@gmail.com");
        newContact.put(PHONE_KEY, "080-0808-009");
        db.collection("PhoneBook").document("Contacts").set(newContact)
                .addOnSuccessListener(new OnSuccessListener< Void >() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this, "User Registered",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "ERROR" + e.toString(),
                                Toast.LENGTH_SHORT).show();
                        Log.d("TAG", e.toString());
                    }
                });

    }
}