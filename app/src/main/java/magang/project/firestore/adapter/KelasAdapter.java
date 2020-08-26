package magang.project.firestore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import magang.project.firestore.R;
import magang.project.firestore.model.PojoSementara;

public class KelasAdapter extends RecyclerView.Adapter<KelasAdapter.KelasAdapterChild> {
    private List<PojoSementara> pojoSementaraList;
    private Context context;
    public KelasAdapter(List<PojoSementara> pojoSementaraList,Context context){
        this.pojoSementaraList=pojoSementaraList;
        this.context=context;

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
        holder.textView_satu.setText(pojoSementaraList.get(position).nama);
        holder.textView_dua.setText(pojoSementaraList.get(position).password);


    }

    @Override
    public int getItemCount() {
        return pojoSementaraList.size();
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
