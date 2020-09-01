package magang.project.firestore.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    private Button button_add_data , button_lihat_data;

    private Button button_update_data;

    private FirebaseFirestore firestoreDB;

    String id;

    private static final String TAG = "AddNoteActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        editText_data_satu=findViewById(R.id.et_add_data_satu);
        editText_data_dua=findViewById(R.id.et_add_data_dua);
        button_add_data=findViewById(R.id.btn_add_data);
        button_lihat_data=findViewById(R.id.btn_lihat_data);
        button_update_data=findViewById(R.id.btn_update_data);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("UpdateNoteId");

            editText_data_satu.setText(bundle.getString("UpdateNoteTitle"));
            editText_data_dua.setText(bundle.getString("UpdateNoteContent"));
        }




        firestoreDB = FirebaseFirestore.getInstance();
        addDatas();

        dataTampil();








    }


                private void updateNote(String id, String title, String content) {
                    Map<String, Object> note = (new ModelFireBase(id, title, content)).toMap();

                    firestoreDB.collection("PhoneBook")
                            .document(id)
                            .set(note)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.e(TAG, "Note document update successful!");
                                    Toast.makeText(getApplicationContext(), "Note has been updated!", Toast.LENGTH_SHORT).show();
                                }
                            })

                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.e(TAG, "Error adding Note document", e);
                                    Toast.makeText(getApplicationContext(), "Note could not be updated!", Toast.LENGTH_SHORT).show();
                                }
                            });
                }


    public void addDatas(){
        button_add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data_satu = editText_data_satu.getText().toString();
                String data_dua = editText_data_dua.getText().toString();


                if (data_satu.length() > 0) {
                    if (id.length() > 0) {
                        updateNote(id, data_satu, data_dua);
                    } else {
                        addData(data_satu, data_dua);
                    }
                }

                finish();


            }
        });
    }

    public void addData(String data_satu, String data_dua){

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

    public void dataTampil(){
        button_lihat_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddData.this, RvLayout.class);
                startActivity(intent);

            }
        });
    }



    }