package cl.inacap.verduritas_fire;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CultivoAdapter extends RecyclerView.Adapter<CultivoAdapter.CultivoViewHolder> {

    private List<Cultivo> listaCultivos;

    public CultivoAdapter(List<Cultivo> listaCultivos) {
        this.listaCultivos = listaCultivos;
    }

    @NonNull
    @Override
    public CultivoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cultivo, parent, false);
        return new CultivoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CultivoViewHolder holder, int position) {
        Cultivo cultivo = listaCultivos.get(position);
        holder.tipoCultivo.setText(cultivo.getTipoCultivo());
        holder.fechaCultivo.setText("Fecha de Cultivo: " + cultivo.getFechaCultivo());
        holder.fechaCosecha.setText("Fecha de Cosecha: " + cultivo.getFechaCosecha());
    }

    @Override
    public int getItemCount() {
        return listaCultivos.size();
    }

    public static class CultivoViewHolder extends RecyclerView.ViewHolder {
        TextView tipoCultivo, fechaCultivo, fechaCosecha;

        public CultivoViewHolder(@NonNull View itemView) {
            super(itemView);
            tipoCultivo = itemView.findViewById(R.id.tv_tipo_cultivo);
            fechaCultivo = itemView.findViewById(R.id.tv_fecha_cultivo);
            fechaCosecha = itemView.findViewById(R.id.tv_fecha_cosecha);
        }
    }
}
