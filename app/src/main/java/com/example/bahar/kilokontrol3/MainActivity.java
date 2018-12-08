package com.example.bahar.kilokontrol3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.bahar.kilokontrol3.R.*;

public class MainActivity extends AppCompatActivity {
    EditText txt_boy;
    EditText kilo;
    TextView durum;
    TextView indeks;
    Button hesapla;
    RadioButton bay;
    RadioButton bayan;
    TextView ideal;
    Button adimsayar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        txt_boy = (EditText) findViewById(id.boy);
        durum = (TextView) findViewById(id.durum);
        kilo = (EditText) findViewById(id.kilo);
        hesapla = (Button) findViewById(id.hesapla);
        ideal = (TextView) findViewById(id.ideal);
        indeks = (TextView) findViewById(id.indeks);
        bay = (RadioButton) findViewById(id.bay);
        bayan = (RadioButton) findViewById(id.bayan);
        adimsayar = (Button) findViewById(id.adimsayar);

        hesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String secilenText = "";
                //  Toast.makeText(getApplicationContext(),"test",Toast.LENGTH_SHORT).show();

                if (bay.isChecked()) {
                    secilenText = bay.getText().toString();
                    if (!txt_boy.getText().equals("")) {
                        double uzunluk = Double.parseDouble(txt_boy.getText().toString());

                        int agırlık = Integer.parseInt(kilo.getText().toString());
                        double sonuc = agırlık / (uzunluk * uzunluk);
                        double hesap = 50 + 2.3 * (uzunluk * 100 * 0.4 - 60);
                        indeks.setText(sonuc + "");
                        ideal.setText(hesap + "");
                        if (sonuc <= 20.7) {
                            durum.setBackgroundResource(color.zayif);
                            durum.setText("Zayıf durumdasınız");
                        } else if (20.7 < sonuc && sonuc <= 26.4) {
                            durum.setBackgroundResource(color.ideal);
                            durum.setText("İdeal kilodasınız");
                        } else if (26.4 < sonuc && sonuc <= 27.8) {
                            durum.setBackgroundResource(color.idealden_fazla);
                            durum.setText("Normal kilodan fazla.");
                        } else if (27.8 < sonuc && sonuc <= 31.1) {
                            durum.setBackgroundResource(color.fazla_kilolu);
                            durum.setText("Kilonuz fazla.");
                        } else if (31.1 < sonuc && sonuc <= 34.9) {
                            durum.setBackgroundResource(color.obez);
                            durum.setText("Obezsiniz");
                        } else {

                            durum.setBackgroundResource(color.doktor);
                            durum.setText("Doktora görünmelisiniz.");
                        }


                    }
                } else if (bayan.isChecked()) {
                    secilenText = bayan.getText().toString();
                    if (!txt_boy.getText().equals("")) {
                        double uzunluk = Double.parseDouble(txt_boy.getText().toString());
                        int agırlık = Integer.parseInt(kilo.getText().toString());
                        double sonuc = agırlık / (uzunluk * uzunluk);
                        double hesap = 45.5 + 2.3 * (uzunluk * 100 * 0.4 - 60);
                        ideal.setText(hesap + "");
                        indeks.setText(sonuc + "");
                        if (sonuc <= 19.1) {
                            durum.setBackgroundResource(color.zayif);
                            durum.setText("Zayıf durumdasınız");
                        } else if (19.1 < sonuc && sonuc <= 25.8) {
                            durum.setBackgroundResource(color.ideal);
                            durum.setText("İdeal kilodasınız");
                        } else if (25.8 < sonuc && sonuc <= 27.3) {
                            durum.setBackgroundResource(color.idealden_fazla);
                            durum.setText("Normal kilodan fazla.");
                        } else if (27.3 < sonuc && sonuc <= 32.3) {
                            durum.setBackgroundResource(color.fazla_kilolu);
                            durum.setText("Kilonuz fazla.");
                        } else if (32.3 < sonuc && sonuc <= 34.9) {
                            durum.setBackgroundResource(color.obez);
                            durum.setText("Obezsiniz");
                        } else {
                            durum.setBackgroundResource(color.doktor);
                            durum.setText("Doktora görünmelisiniz!2");
                        }
                    }


                } else {
                    Toast.makeText(getApplicationContext(), "Lütfen bir seçim yapınız", Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });

    }

    public void sayfa2(View view) {
        adimsayar = (Button) findViewById(id.adimsayar);
        adimsayar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent git = new Intent(MainActivity.this, sayfa2.class);
                startActivity(git);
            }
        });


    }

}