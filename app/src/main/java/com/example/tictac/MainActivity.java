package com.example.tictac;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.concurrent.Delayed;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    boolean turno = false;
    int [] estado = {0,0,0,0,0,0,0,0,0};

    int [][] ganador = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    Button [] casillas = new Button[9];

    int tu = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ingreso Botones manual al arreglo
        casillas[0] = (Button) findViewById(R.id.btn0);
        casillas[1] = (Button) findViewById(R.id.btn1);
        casillas[2] = (Button) findViewById(R.id.btn2);
        casillas[3] = (Button) findViewById(R.id.btn3);
        casillas[4] = (Button) findViewById(R.id.btn4);
        casillas[5] = (Button) findViewById(R.id.btn5);
        casillas[6] = (Button) findViewById(R.id.btn6);
        casillas[7] = (Button) findViewById(R.id.btn7);
        casillas[8] = (Button) findViewById(R.id.btn8);

        for(int i = 0; i < casillas.length; i++){
            //Experimento Generacion Casillas no manual (funcional) aunque muy extraño
            //String CasillasId = "btn" + i;
            //int cosaId = getResources().getIdentifier(CasillasId, "id", getPackageName());
            //casillas[i] = (Button) findViewById(cosaId);

            //Click Casilla en arreglo
            casillas[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        //Ganador control
        String id = view.getResources().getResourceEntryName(view.getId());
        int tableroposicion = Integer.parseInt(id.substring(id.length()-1, id.length()));

        //Control logica turno jugador
        if (!turno) {
            ((Button) view).setText("X");
            estado[tableroposicion] = 1;
            turno = true;
            tu++;
        }
        else {
            ((Button) view).setText("O");
            estado[tableroposicion] = 2;
            turno = false;
            tu++;
        }

        ((Button) view).setEnabled(false);

        if(GanadorR()) {
            if (turno) {
                Toast.makeText(this,"Gana X", Toast.LENGTH_LONG).show();
                Resetear();
            } else {
                Toast.makeText(this,"Gana O", Toast.LENGTH_LONG).show();
                Resetear();
            }
        }

        if (!GanadorR() && (tu == casillas.length)){
            Toast.makeText(this,"Empate", Toast.LENGTH_LONG).show();
            Resetear();
        }

    }

    public boolean GanadorR(){
        boolean resultado = false;
        for(int [] ganador : ganador){
            if (estado[ganador[0]]==estado[ganador[1]]&&
                    estado[ganador[1]]==estado[ganador[2]]&&
                    estado[ganador[0]] != 0){
                resultado=true;
            }
        }
        return resultado;
    }

    public void Resetear(){
        turno = true;
        for (int i = 0; i < estado.length; i++){
            estado[i] = 0;
            casillas[i].setText("");
            casillas[i].setEnabled(true);
        }
        tu=0;
    }

}
