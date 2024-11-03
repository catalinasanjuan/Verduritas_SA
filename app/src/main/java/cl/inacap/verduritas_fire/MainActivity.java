package cl.inacap.verduritas_fire;

import android.content.Intent; // Asegúrate de importar Intent
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    private FirebaseAuth mAuth;
    TextView mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Cambiar para que cargue la vista activity_login.xml
        setContentView(R.layout.activity_login);

        // Configurar las propiedades de layout para que se ajuste a la nueva vista
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.container), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Vincular los elementos del layout activity_login.xml con el código
        email = findViewById(R.id.et_usuario);
        password = findViewById(R.id.et_password);
        mensaje = findViewById(R.id.tv_registrarse);

        // Vincular los botones de la interfaz
        Button btnIngresar = findViewById(R.id.btn_ingresar); // Botón para iniciar sesión
        Button btnRegistrar = findViewById(R.id.btn_registrar); // Botón para registrar nuevo usuario
        Button googleButton = findViewById(R.id.btn_google); // Botón de Google (sin funcionalidad por ahora)

        // Configurar la acción del botón "Ingresar"
        btnIngresar.setOnClickListener(view -> {
            if (validarDatos()) {
                iniciarSesion(); // Llamar a la función para iniciar sesión
            }
        });

        // Configurar la acción del botón "Registrar"
        btnRegistrar.setOnClickListener(view -> {
            if (validarDatos()) {
                registrarse(); // Llamar a la función para registrar un nuevo usuario
            }
        });

        // Configurar la acción del botón de Google (por implementar)
        googleButton.setOnClickListener(view -> {
            mensaje.setText("Autenticación con Google aún no implementada.");
        });
    }

    // Función para registrar el usuario en Firebase
    private void registrarse() {
        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Registro exitoso, actualizar la UI con la información del usuario registrado
                            mensaje.setText("Se registró correctamente");
                            Toast.makeText(MainActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                        } else {
                            // Si el registro falla, mostrar un mensaje al usuario
                            mensaje.setText("No se registró correctamente");
                        }
                    }
                });
    }

    // Función para iniciar sesión en Firebase
    private void iniciarSesion() {
        mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Inicio de sesión exitoso, redirigir a CultivoActivity
                            Intent intent = new Intent(MainActivity.this, CultivoActivity.class); // Corregir contexto e Intent
                            startActivity(intent);
                            finish(); // Cerrar la actividad actual
                        } else {
                            mensaje.setText("No se pudo iniciar sesión. Verifica tus datos.");
                        }
                    }
                });
    }

    // Función para validar los datos ingresados por el usuario
    private boolean validarDatos() {
        return !email.getText().toString().isEmpty() && !password.getText().toString().isEmpty();
    }
}
