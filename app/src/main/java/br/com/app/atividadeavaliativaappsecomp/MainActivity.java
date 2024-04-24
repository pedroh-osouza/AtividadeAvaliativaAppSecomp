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
import android.os.Handler;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;


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
        progressBar.setVisibility(View.INVISIBLE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = radioGroup.findViewById(radioButtonID);
                sharePreferencesEditor = sharedPreferences.edit();
                sharePreferencesEditor.putString("nome", edtTextNome.getText().toString());
                sharePreferencesEditor.putString("email", edtTextEmail.getText().toString());
                sharePreferencesEditor.putString("tome", radioButton.getText().toString());
                sharePreferencesEditor.putString("tamanhoCamisa", spinner.getSelectedItem().toString());
                sharePreferencesEditor.putBoolean("receberNotificacoes", switchNotificacoes.isChecked());
                sharePreferencesEditor.apply();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, ConfirmationActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 3000);

            }
        });
    }
}