package magang.project.firestore.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import magang.project.firestore.R;

public class AddData extends AppCompatActivity {

    private EditText editText_data_satu, editText_data_dua;

    private Button button_add_data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        editText_data_satu=findViewById(R.id.et_add_data_satu);
        editText_data_dua=findViewById(R.id.et_add_data_dua);
        button_add_data=findViewById(R.id.btn_add_data);



    }

    public void addData(){
        String data_satu =editText_data_satu.getText().toString();
        String data_dua= editText_data_dua.getText().toString();


    }



    }