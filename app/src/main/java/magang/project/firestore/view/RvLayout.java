package magang.project.firestore.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import magang.project.firestore.R;
import magang.project.firestore.adapter.KelasAdapter;
import magang.project.firestore.model.ModelFireBase;
import magang.project.firestore.model.PojoSementara;

public class RvLayout extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private KelasAdapter kelasAdapter;
    private static final String TAG = "MainActivity";

    private FirebaseFirestore firestoreDB;
    private ListenerRegistration firestoreListener;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_layout);


        firestoreDB = FirebaseFirestore.getInstance();

        recyclerView=findViewById(R.id.rv_list);




        firestoreListener = firestoreDB.collection("PhoneBook")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.e(TAG, "Listen failed!", e);
                            return;
                        }

                        List<ModelFireBase> notesList = new ArrayList<>();

                        for (DocumentSnapshot doc : documentSnapshots) {
                            ModelFireBase modelFireBase = doc.toObject(ModelFireBase.class);
                            modelFireBase.setId(doc.getId());
                            notesList.add(modelFireBase);
                        }

                        kelasAdapter = new KelasAdapter(notesList, getApplicationContext(), firestoreDB);
                        recyclerView.setAdapter(kelasAdapter);
                    }
                });

        loadNotesList();



/*
        pojoSementaraList = new ArrayList<>();
        pojoSementaraList.add(new PojoSementara("nama","password"));
        pojoSementaraList.add(new PojoSementara("nama","password"));
        pojoSementaraList.add(new PojoSementara("nama","password"));
        pojoSementaraList.add(new PojoSementara("nama","password"));

        recyclerView=findViewById(R.id.rv_list);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        kelasAdapter = new KelasAdapter(pojoSementaraList,RvLayout.this);
        //Memasang Adapter pada RecyclerView
        recyclerView.setAdapter(kelasAdapter);


 */

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        firestoreListener.remove();
    }

    private void loadNotesList() {
        firestoreDB.collection("PhoneBook")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<ModelFireBase> notesList = new ArrayList<>();

                            for (DocumentSnapshot doc : task.getResult()) {
                                ModelFireBase modelFireBase = doc.toObject(ModelFireBase.class);
                                modelFireBase.setId(doc.getId());
                                notesList.add(modelFireBase);
                            }

                            kelasAdapter= new KelasAdapter(notesList, getApplicationContext(), firestoreDB);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(kelasAdapter);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}