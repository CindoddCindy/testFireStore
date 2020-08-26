package magang.project.firestore.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import magang.project.firestore.R;
import magang.project.firestore.adapter.KelasAdapter;
import magang.project.firestore.model.PojoSementara;

public class RvLayout extends AppCompatActivity {
    private List<PojoSementara> pojoSementaraList;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private KelasAdapter kelasAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_layout);

        recyclerView=findViewById(R.id.rv_list);

        linearLayoutManager = new LinearLayoutManager(RvLayout.this);
        recyclerView.setLayoutManager(linearLayoutManager);

       // rvMakanPertama.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));



        pojoSementaraList = new ArrayList<>();
        pojoSementaraList.add(new PojoSementara("nama","password"));
        pojoSementaraList.add(new PojoSementara("nama","password"));
        pojoSementaraList.add(new PojoSementara("nama","password"));
        pojoSementaraList.add(new PojoSementara("nama","password"));




        kelasAdapter = new KelasAdapter(pojoSementaraList,RvLayout.this);


        /*masukkan ke recyclerview*/
        recyclerView.setAdapter(kelasAdapter);


    }
}