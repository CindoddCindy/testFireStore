package magang.project.firestore.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import magang.project.firestore.R;
import magang.project.firestore.model.ModelFireBase;

public class AddData extends AppCompatActivity {

    private EditText editText_data_satu, editText_data_dua;

    private Button button_add_data;

    private FirebaseFirestore firestoreDB;

    private static final String TAG = "AddNoteActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        editText_data_satu=findViewById(R.id.et_add_data_satu);
        editText_data_dua=findViewById(R.id.et_add_data_dua);
        button_add_data=findViewById(R.id.btn_add_data);

        firestoreDB = FirebaseFirestore.getInstance();
        addDatas();





    }

    public void addDatas(){
        button_add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();

            }
        });
    }

    public void addData(){
        String data_satu =editText_data_satu.getText().toString();
        String data_dua= editText_data_dua.getText().toString();

        Map<String, Object> note = new ModelFireBase(data_satu, data_dua).toMap();

        firestoreDB.collection("PhoneBook")
                .add(note)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.e(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                        Toast.makeText(getApplicationContext(), "Note has been added!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Error adding Note document", e);
                        Toast.makeText(getApplicationContext(), "Note could not be added!", Toast.LENGTH_SHORT).show();
                    }
                });
    }



    }