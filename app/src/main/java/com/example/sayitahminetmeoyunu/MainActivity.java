package com.example.sayitahminetmeoyunu;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView txtKalanHak, txtSonuc;
    private EditText editTxtSayi;
    private String gelenDeger;
    private int kalanHak = 3, randomSayi;
    private Random rndNumber;
    private boolean tahminDogruMu = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtKalanHak = (TextView) findViewById(R.id.txtViewKalanHak);
        txtSonuc = (TextView) findViewById(R.id.txtViewSonuc);
        editTxtSayi = (EditText) findViewById(R.id.editTxtSayi);

        rndNumber = new Random();
        randomSayi = rndNumber.nextInt(5); //0-5 arasında üretilen random sayı randomSayi degiskenine atandı.
        System.out.println("Random Sayi: " + randomSayi);


    }

    public void btnTahminEt(View v) {
        gelenDeger = editTxtSayi.getText().toString();
        if (!TextUtils.isEmpty(gelenDeger)) {
            if (kalanHak > 0 && tahminDogruMu==false) {
                if (gelenDeger.equals(String.valueOf(randomSayi))){  //gelenDeger string,randomSayi int olduğu için == kullanarak eşitleyemedik.
                    sonucGoster("Tebrikler! Doğru Tahmin Ettiniz.");
                    tahminDogruMu=true; //son tahminde doğru bilince "Tahmin Etme Hakkınız Bitmiştir." yazmasın diye
            }//kullanıcı doğru bilidiği için edittext'i kapatmamız ve bir mesaj vermemiz gerekiyor. haklar 0'a inerse de edittext'i kapatmamız ve bir mesaj vermemiz gerekir. Aynı şeyleri yapacağımız için bunun için bir metot tanımlarız.
                else {
                    txtSonuc.setText("Yanlış Tahminde Bulundunuz.");
                    editTxtSayi.setText("");
                }
                kalanHak--;


                txtKalanHak.setText("Kalan Hak: " + kalanHak);

                if (kalanHak == 0 && tahminDogruMu==false){ //son tahminde doğru bilince "Tahmin Etme Hakkınız Bitmiştir." yazmasın diye
                    sonucGoster("Tahmin Etme Hakkınız Bitmiştir.");
                    editTxtSayi.setText(" ");
                }
            } else
                txtSonuc.setText("Oyun Bitti.");
        } else
            txtSonuc.setText("Lütfen Bir Değer Giriniz.");
    }

    private void sonucGoster(String mesaj) {
        editTxtSayi.setEnabled(false);
        txtSonuc.setText(mesaj);
    }
}

