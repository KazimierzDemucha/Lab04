package pollub.ism.lab4_kolko_i_krzyzyk;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static String                               //definicja kluczy potrzebnych do następujących czynnosci:
            KEY_1_1 = "Wartosc tekstowa na B_1_1",      //zapis tekstu, który definiuje zapisany symbol "X" lub "O"
            KEY_1_2 = "Wartosc tekstowa na B_1_2",
            KEY_1_3 = "Wartosc tekstowa na B_1_3",
            KEY_2_1 = "Wartosc tekstowa na B_2_1",
            KEY_2_2 = "Wartosc tekstowa na B_2_2",
            KEY_2_3 = "Wartosc tekstowa na B_2_3",
            KEY_3_1 = "Wartosc tekstowa na B_3_1",
            KEY_3_2 = "Wartosc tekstowa na B_3_2",
            KEY_3_3 = "Wartosc tekstowa na B_3_3",
            KEY_A_1_1 = "aktywnosc B_1_1",              //zapisanie nieaktywnych pol, które zostaly już uzyte, aby po obrocie urzadzenia pola nadal byly zablokowane
            KEY_A_1_2 = "aktywnosc B_1_2",
            KEY_A_1_3 = "aktywnosc B_1_3",
            KEY_A_2_1 = "aktywnosc B_2_1",
            KEY_A_2_2 = "aktywnosc B_2_2",
            KEY_A_2_3 = "aktywnosc B_2_3",
            KEY_A_3_1 = "aktywnosc B_3_1",
            KEY_A_3_2 = "aktywnosc B_3_2",
            KEY_A_3_3 = "aktywnosc B_3_3",
            KEY_K_1_1 = "kolor B_1_1",                  //zapisanie koloru, aby obrot ekranu nie sprawial, ze symbole zmieniaja kolor
            KEY_K_1_2 = "kolor B_1_2",
            KEY_K_1_3 = "kolor B_1_3",
            KEY_K_2_1 = "kolor B_2_1",
            KEY_K_2_2 = "kolor B_2_2",
            KEY_K_2_3 = "kolor B_2_3",
            KEY_K_3_1 = "kolor B_3_1",
            KEY_K_3_2 = "kolor B_3_2",
            KEY_K_3_3 = "kolor B_3_3",
            KEY_KOLEJKA = "liczba okreslajaca kolejke",
            KEY_WYGRANA = "liczba okreslajaca wygrana",
            KEY_BLOKADA = "liczba okreslajaca blokade ekranu";


    static int kolejka;
    static int wygrana;
    static int blokada = 0; // do przekazania informacji o blokadzie wszystkich pol przy obrocie ekranu

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {                          //saveInstanceState dla tekstu, koloru i aktywnych pol
        super.onSaveInstanceState(outState);
        outState.putCharSequence(KEY_1_1, ((Button) findViewById(R.id.B_1_1)).getText());
        outState.putCharSequence(KEY_1_2, ((Button) findViewById(R.id.B_1_2)).getText());
        outState.putCharSequence(KEY_1_3, ((Button) findViewById(R.id.B_1_3)).getText());
        outState.putCharSequence(KEY_2_1, ((Button) findViewById(R.id.B_2_1)).getText());
        outState.putCharSequence(KEY_2_2, ((Button) findViewById(R.id.B_2_2)).getText());
        outState.putCharSequence(KEY_2_3, ((Button) findViewById(R.id.B_2_3)).getText());
        outState.putCharSequence(KEY_3_1, ((Button) findViewById(R.id.B_3_1)).getText());
        outState.putCharSequence(KEY_3_2, ((Button) findViewById(R.id.B_3_2)).getText());
        outState.putCharSequence(KEY_3_3, ((Button) findViewById(R.id.B_3_3)).getText());

        outState.putBoolean(KEY_A_1_1, ((Button) findViewById(R.id.B_1_1)).isEnabled());
        outState.putBoolean(KEY_A_1_2, ((Button) findViewById(R.id.B_1_2)).isEnabled());
        outState.putBoolean(KEY_A_1_3, ((Button) findViewById(R.id.B_1_3)).isEnabled());
        outState.putBoolean(KEY_A_2_1, ((Button) findViewById(R.id.B_2_1)).isEnabled());
        outState.putBoolean(KEY_A_2_2, ((Button) findViewById(R.id.B_2_2)).isEnabled());
        outState.putBoolean(KEY_A_2_3, ((Button) findViewById(R.id.B_2_3)).isEnabled());
        outState.putBoolean(KEY_A_3_1, ((Button) findViewById(R.id.B_3_1)).isEnabled());
        outState.putBoolean(KEY_A_3_2, ((Button) findViewById(R.id.B_3_2)).isEnabled());
        outState.putBoolean(KEY_A_3_3, ((Button) findViewById(R.id.B_3_3)).isEnabled());

        outState.putInt(KEY_K_1_1, ((Button) findViewById(R.id.B_1_1)).getCurrentTextColor());
        outState.putInt(KEY_K_1_2, ((Button) findViewById(R.id.B_1_2)).getCurrentTextColor());
        outState.putInt(KEY_K_1_3, ((Button) findViewById(R.id.B_1_3)).getCurrentTextColor());
        outState.putInt(KEY_K_2_1, ((Button) findViewById(R.id.B_2_1)).getCurrentTextColor());
        outState.putInt(KEY_K_2_2, ((Button) findViewById(R.id.B_2_2)).getCurrentTextColor());
        outState.putInt(KEY_K_2_3, ((Button) findViewById(R.id.B_2_3)).getCurrentTextColor());
        outState.putInt(KEY_K_3_1, ((Button) findViewById(R.id.B_3_1)).getCurrentTextColor());
        outState.putInt(KEY_K_3_2, ((Button) findViewById(R.id.B_3_2)).getCurrentTextColor());
        outState.putInt(KEY_K_3_3, ((Button) findViewById(R.id.B_3_3)).getCurrentTextColor());

        outState.putInt(KEY_KOLEJKA, kolejka);
        outState.putInt(KEY_WYGRANA, kolejka);
        outState.putInt(KEY_BLOKADA, blokada);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {                    //odczyt InstanceState dla tekstu, kolorow symboli i aktywnych pol
        super.onRestoreInstanceState(savedInstanceState);

        ((Button)findViewById(R.id.B_1_1)).setText(savedInstanceState.getCharSequence(KEY_1_1));
        ((Button)findViewById(R.id.B_1_2)).setText(savedInstanceState.getCharSequence(KEY_1_2));
        ((Button)findViewById(R.id.B_1_3)).setText(savedInstanceState.getCharSequence(KEY_1_3));
        ((Button)findViewById(R.id.B_2_1)).setText(savedInstanceState.getCharSequence(KEY_2_1));
        ((Button)findViewById(R.id.B_2_2)).setText(savedInstanceState.getCharSequence(KEY_2_2));
        ((Button)findViewById(R.id.B_2_3)).setText(savedInstanceState.getCharSequence(KEY_2_3));
        ((Button)findViewById(R.id.B_3_1)).setText(savedInstanceState.getCharSequence(KEY_3_1));
        ((Button)findViewById(R.id.B_3_2)).setText(savedInstanceState.getCharSequence(KEY_3_2));
        ((Button)findViewById(R.id.B_3_3)).setText(savedInstanceState.getCharSequence(KEY_3_3));

        ((Button)findViewById(R.id.B_1_1)).setTextColor(savedInstanceState.getInt(KEY_K_1_1));
        ((Button)findViewById(R.id.B_1_2)).setTextColor(savedInstanceState.getInt(KEY_K_1_2));
        ((Button)findViewById(R.id.B_1_3)).setTextColor(savedInstanceState.getInt(KEY_K_1_3));
        ((Button)findViewById(R.id.B_2_1)).setTextColor(savedInstanceState.getInt(KEY_K_2_1));
        ((Button)findViewById(R.id.B_2_2)).setTextColor(savedInstanceState.getInt(KEY_K_2_2));
        ((Button)findViewById(R.id.B_2_3)).setTextColor(savedInstanceState.getInt(KEY_K_2_3));
        ((Button)findViewById(R.id.B_3_1)).setTextColor(savedInstanceState.getInt(KEY_K_3_1));
        ((Button)findViewById(R.id.B_3_2)).setTextColor(savedInstanceState.getInt(KEY_K_3_2));
        ((Button)findViewById(R.id.B_3_3)).setTextColor(savedInstanceState.getInt(KEY_K_3_3));


        kolejka = savedInstanceState.getInt(KEY_KOLEJKA, 0);
        wygrana = savedInstanceState.getInt(KEY_WYGRANA, 0);
        blokada = savedInstanceState.getInt(KEY_BLOKADA, 0);

        if (blokada == 1)                                               //aby program nie przerabial wszystkich pol podwojnie, to najpierw odczytuje czy wszystkie pola maja byc zalokowane
        {
            ((Button)findViewById(R.id.B_1_1)).setEnabled(false);
            ((Button)findViewById(R.id.B_1_2)).setEnabled(false);
            ((Button)findViewById(R.id.B_1_3)).setEnabled(false);
            ((Button)findViewById(R.id.B_2_1)).setEnabled(false);
            ((Button)findViewById(R.id.B_2_2)).setEnabled(false);
            ((Button)findViewById(R.id.B_2_3)).setEnabled(false);
            ((Button)findViewById(R.id.B_3_1)).setEnabled(false);
            ((Button)findViewById(R.id.B_3_2)).setEnabled(false);
            ((Button)findViewById(R.id.B_3_3)).setEnabled(false);
        }
        else if (blokada == 0)                                          //jezeli gra nie jest skonczona, to po obrocie ekranu zablokowane sa tylko uzyte juz pola
        {
            if (!(savedInstanceState.getBoolean(KEY_A_1_1)))
                ((Button)findViewById(R.id.B_1_1)).setEnabled(false);
            if (!(savedInstanceState.getBoolean(KEY_A_1_2)))
                ((Button)findViewById(R.id.B_1_2)).setEnabled(false);
            if (!(savedInstanceState.getBoolean(KEY_A_1_3)))
                ((Button)findViewById(R.id.B_1_3)).setEnabled(false);
            if (!(savedInstanceState.getBoolean(KEY_A_2_1)))
                ((Button)findViewById(R.id.B_2_1)).setEnabled(false);
            if (!(savedInstanceState.getBoolean(KEY_A_2_2)))
                ((Button)findViewById(R.id.B_2_2)).setEnabled(false);
            if (!(savedInstanceState.getBoolean(KEY_A_2_3)))
                ((Button)findViewById(R.id.B_2_3)).setEnabled(false);
            if (!(savedInstanceState.getBoolean(KEY_A_3_1)))
                ((Button)findViewById(R.id.B_3_1)).setEnabled(false);
            if (!(savedInstanceState.getBoolean(KEY_A_3_2)))
                ((Button)findViewById(R.id.B_3_2)).setEnabled(false);
            if (!(savedInstanceState.getBoolean(KEY_A_3_3)))
                ((Button)findViewById(R.id.B_3_3)).setEnabled(false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ruch(View view) {                                                                   // funkcja przypisana do wszystkich przyciskow, odpowiadajaca za ruch
        if (kolejka % 2 == 0)
            rysuj_kolko(view);                                                                      //dla parzystej ilosci ruchow rysuje kolko
        else
            rysuj_krzyzyk(view);                                                                    //dla nieparzystej ilosci ruchow rysuje krzyzyk
        kolejka++;
        if (kolejka>=4){                                                                            //sprawdzenie warunku wygranej dla 5 lub wiecej narysowanych symboli (indeksowanie od 0, dlatego dla kolejka>=4)
            if (wygrana == 0 && kolejka==9){
                blokada = 1;                                                                        //przekazanie informacji o zablokowaniu wszystkich pol ze wzgledu na koniec gry
                Toast.makeText(this, "REMIS", Toast.LENGTH_LONG).show();
            }
            else if (wygrana == 1){
                ((Button)findViewById(R.id.B_1_1)).setEnabled(false);
                ((Button)findViewById(R.id.B_1_2)).setEnabled(false);
                ((Button)findViewById(R.id.B_1_3)).setEnabled(false);
                ((Button)findViewById(R.id.B_2_1)).setEnabled(false);
                ((Button)findViewById(R.id.B_2_2)).setEnabled(false);
                ((Button)findViewById(R.id.B_2_3)).setEnabled(false);
                ((Button)findViewById(R.id.B_3_1)).setEnabled(false);
                ((Button)findViewById(R.id.B_3_2)).setEnabled(false);
                ((Button)findViewById(R.id.B_3_3)).setEnabled(false);
                blokada = 1;                                                                        //przekazanie informacji o zablokowaniu wszystkich pol ze wzgledu na koniec gry
                Toast.makeText(this, "WYGRAŁ X", Toast.LENGTH_LONG).show();
            }
            else if (wygrana == 2){
                ((Button)findViewById(R.id.B_1_1)).setEnabled(false);
                ((Button)findViewById(R.id.B_1_2)).setEnabled(false);
                ((Button)findViewById(R.id.B_1_3)).setEnabled(false);
                ((Button)findViewById(R.id.B_2_1)).setEnabled(false);
                ((Button)findViewById(R.id.B_2_2)).setEnabled(false);
                ((Button)findViewById(R.id.B_2_3)).setEnabled(false);
                ((Button)findViewById(R.id.B_3_1)).setEnabled(false);
                ((Button)findViewById(R.id.B_3_2)).setEnabled(false);
                ((Button)findViewById(R.id.B_3_3)).setEnabled(false);
                blokada = 1;                                                                        //przekazanie informacji o zablokowaniu wszystkich pol ze wzgledu na koniec gry
                Toast.makeText(this, "WYGRAŁ O", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void rysuj_kolko(View view) {                                                            //funckja rysujaca kolko na kolor niebieski
        Button button = (Button) findViewById(view.getId());
        button.setText("O");
        button.setEnabled(false);
        button.setTextColor(Color.BLUE);
        sprawdz_wygrana();
    }

    public void rysuj_krzyzyk(View view) {                                                          //funkcja rysujaca krzyzyk na kolor zielony
        Button button = (Button) findViewById(view.getId());
        button.setText("X");
        button.setEnabled(false);
        button.setTextColor(Color.GREEN);
        sprawdz_wygrana();
    }

    public void sprawdz_wygrana() {         //sprawdzenie wygranej poprzez przejscie po kazdym wierszu, kolumnie oraz przekatnej i jezeli symbole sa takie same to sprawdzenie ktory to symbol
        CharSequence X = (CharSequence)"X";
        CharSequence O = (CharSequence)"O";
        if (((Button)findViewById(R.id.B_1_1)).getText() == ((Button)findViewById(R.id.B_1_2)).getText() && ((Button)findViewById(R.id.B_1_2)).getText() == ((Button)findViewById(R.id.B_1_3)).getText())
        {
            if (((Button)findViewById(R.id.B_1_1)).getText() == X)
                wygrana = 1;
            else if (((Button)findViewById(R.id.B_1_1)).getText() == O)
                wygrana = 2;
        }
        if (((Button)findViewById(R.id.B_2_1)).getText() == ((Button)findViewById(R.id.B_2_2)).getText() && ((Button)findViewById(R.id.B_2_2)).getText() == ((Button)findViewById(R.id.B_2_3)).getText())
        {
            if (((Button)findViewById(R.id.B_2_1)).getText() == X)
            wygrana = 1;
            else if (((Button)findViewById(R.id.B_2_1)).getText() == O)
            wygrana = 2;
        }
        if (((Button)findViewById(R.id.B_3_1)).getText() == ((Button)findViewById(R.id.B_3_2)).getText() && ((Button)findViewById(R.id.B_3_2)).getText() == ((Button)findViewById(R.id.B_3_3)).getText())
        {
            if (((Button)findViewById(R.id.B_3_1)).getText() == X)
            wygrana = 1;
            else if (((Button)findViewById(R.id.B_3_1)).getText() == O)
            wygrana = 2;
        }
        if (((Button)findViewById(R.id.B_1_1)).getText() == ((Button)findViewById(R.id.B_2_1)).getText() && ((Button)findViewById(R.id.B_2_1)).getText() == ((Button)findViewById(R.id.B_3_1)).getText())
        {
            if (((Button)findViewById(R.id.B_1_1)).getText() == X)
            wygrana = 1;
            else if (((Button)findViewById(R.id.B_1_1)).getText() == O)
            wygrana = 2;
        }
        if (((Button)findViewById(R.id.B_1_2)).getText() == ((Button)findViewById(R.id.B_2_2)).getText() && ((Button)findViewById(R.id.B_2_2)).getText() == ((Button)findViewById(R.id.B_3_2)).getText())
        {
            if (((Button)findViewById(R.id.B_1_2)).getText() == X)
            wygrana = 1;
            else if (((Button)findViewById(R.id.B_1_2)).getText() == O)
            wygrana = 2;
        }
        if (((Button)findViewById(R.id.B_1_3)).getText() == ((Button)findViewById(R.id.B_2_3)).getText() && ((Button)findViewById(R.id.B_2_3)).getText() == ((Button)findViewById(R.id.B_3_3)).getText())
        {
            if (((Button)findViewById(R.id.B_1_3)).getText() == X)
            wygrana = 1;
            else if (((Button)findViewById(R.id.B_1_3)).getText() == O)
            wygrana = 2;
        }
        if (((Button)findViewById(R.id.B_1_1)).getText() == ((Button)findViewById(R.id.B_2_2)).getText() && ((Button)findViewById(R.id.B_2_2)).getText() == ((Button)findViewById(R.id.B_3_3)).getText())
        {
            if (((Button)findViewById(R.id.B_1_1)).getText() == X)
            wygrana = 1;
            else if (((Button)findViewById(R.id.B_1_1)).getText() == O)
            wygrana = 2;
        }
        if (((Button)findViewById(R.id.B_3_1)).getText() == ((Button)findViewById(R.id.B_2_2)).getText() && ((Button)findViewById(R.id.B_2_2)).getText() == ((Button)findViewById(R.id.B_1_3)).getText())
        {
            if (((Button)findViewById(R.id.B_3_1)).getText() == X)
            wygrana = 1;
            else if (((Button)findViewById(R.id.B_3_1)).getText() == O)
            wygrana = 2;
        }
    }

    public void restart(View view) {                                                                //funkcja restartowania gry
        kolejka = 0;
        wygrana = 0;
        blokada = 0;                                                                                //blokade nalozona przez mechanizm wygrania mozna zdjac tylko przyciskiem restart
        Intent intencja = new Intent(this, MainActivity.class);
        startActivity(intencja);
    }
}