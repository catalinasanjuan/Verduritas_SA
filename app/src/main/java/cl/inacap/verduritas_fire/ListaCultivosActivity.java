package cl.inacap.verduritas_fire;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListaCultivosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CultivoAdapter cultivoAdapter;
    private List<Cultivo> listaCultivos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cultivos);

        // Inicializar el RecyclerView y el adaptador
        recyclerView = findViewById(R.id.rv_lista_cultivos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar la lista de cultivos
        listaCultivos = new ArrayList<>();

        // Recibir el cultivo enviado desde CultivoActivity
        Cultivo cultivoRecibido = (Cultivo) getIntent().getSerializableExtra("cultivo");
        if (cultivoRecibido != null) {
            listaCultivos.add(cultivoRecibido);
        }

        // Inicializar el adaptador con la lista de cultivos
        cultivoAdapter = new CultivoAdapter(listaCultivos);
        recyclerView.setAdapter(cultivoAdapter);
    }
}
