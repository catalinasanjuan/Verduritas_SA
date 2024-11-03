package cl.inacap.verduritas_fire;

import android.app.DatePickerDialog;
import android.content.Intent; // Importar la clase Intent
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CultivoActivity extends AppCompatActivity {

    private Spinner spinnerTipoCultivo;
    private EditText etFechaCultivo;
    private Button btnRegistrarCultivo;
    private Calendar calendario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultivo); // Cargar el layout activity_cultivo.xml

        // Inicializar elementos de la interfaz
        spinnerTipoCultivo = findViewById(R.id.spinner_tipo_cultivo);
        etFechaCultivo = findViewById(R.id.et_fecha_cultivo);
        btnRegistrarCultivo = findViewById(R.id.btn_registrar_cultivo);

        // Configurar el selector de fecha
        calendario = Calendar.getInstance();
        etFechaCultivo.setOnClickListener(v -> mostrarSelectorFecha());

        // Configurar el adaptador para el Spinner
        configurarSpinner();

        // Configurar el botón de registro
        btnRegistrarCultivo.setOnClickListener(v -> registrarCultivo());
    }

    // Método para configurar el adaptador del Spinner
    private void configurarSpinner() {
        // Datos para el Spinner
        String[] cultivos = new String[] { "Tomates", "Cebollas", "Lechugas", "Apio", "Choclo" };

        // Crear el adaptador utilizando un layout simple para los elementos del Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cultivos);

        // Especificar el layout a usar cuando las opciones aparecen (Dropdown)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Asignar el adaptador al Spinner
        spinnerTipoCultivo.setAdapter(adapter);
    }

    // Método para mostrar el selector de fecha
    private void mostrarSelectorFecha() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    calendario.set(year, month, dayOfMonth);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                    etFechaCultivo.setText(sdf.format(calendario.getTime()));
                },
                calendario.get(Calendar.YEAR),
                calendario.get(Calendar.MONTH),
                calendario.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    // Método para registrar el cultivo
    private void registrarCultivo() {
        String tipoCultivo = spinnerTipoCultivo.getSelectedItem().toString();
        String fechaCultivo = etFechaCultivo.getText().toString();

        if (fechaCultivo.isEmpty()) {
            Toast.makeText(this, "Por favor, selecciona una fecha de cultivo.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Calcular la fecha de cosecha
        int diasCosecha = obtenerDiasCosecha(tipoCultivo);
        calendario.add(Calendar.DAY_OF_MONTH, diasCosecha);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        String fechaCosecha = sdf.format(calendario.getTime());

        // Crear un nuevo Cultivo y pasarlo a la siguiente actividad
        Cultivo cultivo = new Cultivo(tipoCultivo, fechaCultivo, fechaCosecha);

        Intent intent = new Intent(CultivoActivity.this, ListaCultivosActivity.class);
        intent.putExtra("cultivo", cultivo); // Enviar el cultivo registrado a la nueva actividad

        // Mostrar mensaje de registro correcto
        Toast.makeText(this, "Redirigiendo a la lista de cultivos", Toast.LENGTH_SHORT).show();

        startActivity(intent);
        finish(); // Cerrar la actividad actual
    }



    // Método para obtener los días de cosecha según el tipo de cultivo
    private int obtenerDiasCosecha(String tipoCultivo) {
        switch (tipoCultivo) {
            case "Tomates":
                return 80;
            case "Cebollas":
                return 120;
            case "Lechugas":
                return 60;
            case "Apio":
                return 85;
            case "Choclo":
                return 90;
            default:
                return 0;
        }
    }
}
