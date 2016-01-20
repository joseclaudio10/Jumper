package br.com.solange.jumper.engine;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Solange on 19/06/2015.
 */
public class Pontuacao {
    private Som som;

    public Pontuacao(Som som) {
        this.som = som;
    }

    private int pontos = 0;
    private static final Paint BRANCO = Cores.getCorDaPontuacao();

    public void aumenta(){
        som.play(Som.PONTUACAO);
        pontos++;
    }

    public void desenhaNo(Canvas canvas){
        canvas.drawText(String.valueOf(pontos), 100, 100, BRANCO);
    }
}
