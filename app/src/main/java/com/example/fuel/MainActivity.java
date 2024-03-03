package com.example.fuel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuel.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView totalResult;
    private EditText etPertalite, etPertamax92, etPertamax98;
    private Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.fuel.R.layout.activity_main);

        totalResult = findViewById(R.id.result);
        etPertalite = findViewById(R.id.ePertalite);
        etPertamax92 = findViewById(R.id.ePertamax92);
        etPertamax98 = findViewById(R.id.ePertamax98);
        btnCalculate = findViewById(R.id.Calculate);

        btnCalculate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.Calculate) {
            String inputPertalite = etPertalite.getText().toString().trim();
            String inputPertamax92 = etPertamax92.getText().toString().trim();
            String inputPertamax98 = etPertamax98.getText().toString().trim();

            double hargaPertalite = 10000;
            double hargaPertamax92 = 12000;
            double hargaPertamax98 = 14000;

            double inpPertalite = inputPertalite.isEmpty() ? 0 : Double.parseDouble(inputPertalite) / hargaPertalite;
            double inpPertamax92 = inputPertamax92.isEmpty() ? 0 : Double.parseDouble(inputPertamax92) / hargaPertamax92;
            double inpPertamax98 = inputPertamax98.isEmpty() ? 0 : Double.parseDouble(inputPertamax98) / hargaPertamax98;

            double biayaAdminPertalite = 2500;
            double biayaAdminPertamax92 = 3000;
            double biayaAdminPertamax98 = 3500;

            double totalBiaya = (inpPertalite * hargaPertalite) +
                    (inpPertamax92 * hargaPertamax92) +
                    (inpPertamax98 * hargaPertamax98);

            double totalLiter = inpPertalite + inpPertamax92 + inpPertamax98;

            RadioGroup radioGroupMembership = findViewById(R.id.radioGroupMember);
            boolean sudahMembership = radioGroupMembership.getCheckedRadioButtonId() == R.id.buttonMember;

            double diskon = 0;

            if (sudahMembership) {
                diskon = 0.05 * totalBiaya;
                totalBiaya -= diskon;
            }

            double biayaAdmin = (inpPertalite > 0 ? biayaAdminPertalite : 0) +
                    (inpPertamax92 > 0 ? biayaAdminPertamax92 : 0) +
                    (inpPertamax98 > 0 ? biayaAdminPertamax98 : 0);

            double totalBiayaDenganBiayaTambahan = totalBiaya + biayaAdmin;

            String jenisBensinDibeli = "";
            if (inpPertalite > 0) {
                jenisBensinDibeli += "Pertalite ";
            }
            if (inpPertamax92 > 0) {
                jenisBensinDibeli += "Pertamax92 ";
            }
            if (inpPertamax98 > 0) {
                jenisBensinDibeli += "Pertamax98 ";
            }

            String outputText = "Jenis Bensin: " + jenisBensinDibeli + "\n" +
                    "Total Liter: " + totalLiter + " Liter\n" +
                    "Total Diskon (jika anggota membership): " + diskon + " Rupiah\n" +
                    "Total Bayar: " + totalBiayaDenganBiayaTambahan + " Rupiah";

            totalResult.setText(outputText);
        }
    }
}