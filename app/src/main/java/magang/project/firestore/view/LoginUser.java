package magang.project.firestore.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import magang.project.firestore.R;
import magang.project.firestore.sharedprefernce.SharedPrefHandle;

public class LoginUser extends AppCompatActivity {
    private EditText editText_nama, editText_password;
    private Button button_login;

    private SharedPrefHandle sharedPrefHandle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        editText_nama=findViewById(R.id.et_nama_login);
        editText_password=findViewById(R.id.et_password_login);
        button_login=findViewById(R.id.btn_login);

        sharedPrefHandle = new SharedPrefHandle(LoginUser.this);

        //fungsi ini untuk menandai acttivity di mana nilai boolean shared prefernce di simpan , jadi ketika user sdh menaruh nilai di sini , maka activity ini tidak di munculkan lagi
        if (sharedPrefHandle.getSPSudahLogin()){
            startActivity(new Intent(LoginUser.this, RvLayout.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama =editText_nama.getText().toString();
                String password = editText_password.getText().toString();

                Toast.makeText(LoginUser.this, "BERHASIL LOGIN", Toast.LENGTH_SHORT).show();
                sharedPrefHandle.setSpUserName(SharedPrefHandle.SP_USER_NAME, nama);
                sharedPrefHandle.setSpUserPassword(SharedPrefHandle.SP_USER_PASSWORD,password);

                // Shared Pref ini berfungsi untuk menjadi trigger session login
                sharedPrefHandle.setSpSudahLogin(SharedPrefHandle.SP_SUDAH_LOGIN, true);
                startActivity(new Intent(LoginUser.this, AddData.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();

            }
        });
    }
}