package magang.project.firestore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import magang.project.firestore.R;
import magang.project.firestore.model.ModelFireBase;
import magang.project.firestore.model.PojoSementara;

public class KelasAdapter extends RecyclerView.Adapter<KelasAdapter.KelasAdapterChild> {
    private List<ModelFireBase> modelFireBaseList;
    private Context context;
    private FirebaseFirestore firestoreDB;

    public KelasAdapter(List<ModelFireBase> modelFireBaseList,Context context,FirebaseFirestore firestoreDB){
        this.modelFireBaseList=modelFireBaseList;
        this.context=context;
        this.firestoreDB=firestoreDB;

    }



    @NonNull
    @Override
    public KelasAdapterChild onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_adapter, null);
        KelasAdapterChild kelasAdapterChild=new KelasAdapterChild(view);

        return kelasAdapterChild;
    }

    @Override
    public void onBindViewHolder(@NonNull KelasAdapterChild holder, int position) {

        final int itemPosition = position;
        final ModelFireBase note = modelFireBaseList.get(itemPosition);

        holder.textView_satu.setText(note.getData_satu());
        holder.textView_dua.setText(note.getData_dua());

    }

    @Override
    public int getItemCount() {
        return modelFireBaseList.size();
    }

    public class KelasAdapterChild extends RecyclerView.ViewHolder{

        public TextView textView_satu, textView_dua;

        public KelasAdapterChild(@NonNull View itemView) {
            super(itemView);

            textView_satu=itemView.findViewById(R.id.tv_test_satu);
            textView_dua=itemView.findViewById(R.id.tv_test_dua);

        }
    }
}
