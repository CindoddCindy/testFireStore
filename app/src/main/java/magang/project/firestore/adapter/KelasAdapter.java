package magang.project.firestore.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import magang.project.firestore.R;
import magang.project.firestore.model.ModelFireBase;
import magang.project.firestore.model.PojoSementara;
import magang.project.firestore.view.AddData;

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
        holder.imageView_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteNote(note.getId(), itemPosition);
            }
        });

        holder.imageView_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateNote(note);
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelFireBaseList.size();
    }

    public class KelasAdapterChild extends RecyclerView.ViewHolder{

        public TextView textView_satu, textView_dua;
        public ImageView imageView_hapus, imageView_edit;

        public KelasAdapterChild(@NonNull View itemView) {
            super(itemView);

            textView_satu=itemView.findViewById(R.id.tv_test_satu);
            textView_dua=itemView.findViewById(R.id.tv_test_dua);

            imageView_hapus=itemView.findViewById(R.id.iv_delete);
            imageView_edit=itemView.findViewById(R.id.iv_edit);

        }
    }


    private void updateNote(ModelFireBase modelFireBase) {
        Intent intent = new Intent(context, AddData.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("UpdateNoteId", modelFireBase.getId());
        intent.putExtra("UpdateNoteTitle", modelFireBase.getData_satu());
        intent.putExtra("UpdateNoteContent", modelFireBase.getData_dua());
        context.startActivity(intent);
    }

    private void deleteNote(String id, final int position) {
        firestoreDB.collection("notes")
                .document(id)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        modelFireBaseList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, modelFireBaseList.size());
                        Toast.makeText(context, "Note has been deleted!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
