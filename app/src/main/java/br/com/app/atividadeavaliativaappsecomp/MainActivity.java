package br.com.app.atividadeavaliativaappsecomp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edtTextNome;

    EditText edtTextEmail;

    RadioGroup radioGroup;

    Spinner spinner;

    SharedPreferences sharedPreferences;

    SharedPreferences.Editor sharePreferencesEditor;

    Button button;

    ProgressBar progressBar;

    Switch switchNotificacoes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("toma", MODE_PRIVATE);
        edtTextNome = findViewById(R.id.edtTextNome);
        edtTextEmail = findViewById(R.id.edtTextEmail);
        radioGroup = findViewById(R.id.radioGroup);
        switchNotificacoes = findViewById(R.id.switchNotificacoes);
        spinner = findViewById(R.id.spinner);
        button = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = radioGroup.findViewById(radioButtonID);
                sharePreferencesEditor = sharedPreferences.edit();
                sharePreferencesEditor.putString("nome", edtTextNome.getText().toString());
                sharePreferencesEditor.putString("email", edtTextEmail.getText().toString());
                sharePreferencesEditor.putString("tome", radioButton.getText().toString());
                sharePreferencesEditor.putString("tamanhoCamisa", spinner.getSelectedItem().toString());
                sharePreferencesEditor.putBoolean("receberNotificacoes", switchNotificacoes.isChecked());
                sharePreferencesEditor.apply();

                Intent intent = new Intent(MainActivity.this, ConfirmationActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void carregarProgressBar()
    {
        button.setEnabled(false);

    }
}