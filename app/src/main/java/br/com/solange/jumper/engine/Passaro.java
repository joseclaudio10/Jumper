package br.com.solange.jumper.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import br.com.solange.jumper.R;

/**
 * Created by Solange on 16/06/2015.
 */
public class Passaro {
    private static final Paint vermelho = Cores.getCorDoPassaro();

    public static final int X = 100;
    public static final int RAIO = 50;
    private int altura;
    private Tela tela;
    private Bitmap passaro;
    Context context;
    private Som som;

    public Passaro(Tela tela, Context context, Som som){
        this.tela = tela;
        this.som = som;
        this.altura = 100;
        this.context = context;
    }

    public void desenhaNo(Canvas canvas){
        //canvas.drawCircle(X, altura, RAIO,vermelho);
        Bitmap bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.passaro);
        this.passaro = Bitmap.createScaledBitmap(bp, RAIO*2, RAIO*2, false);
        canvas.drawBitmap(passaro, X-RAIO, altura-RAIO, null);

    }

    public void cai() {
        boolean chegouNoChao = altura + RAIO > tela.getAltura();
        if (!chegouNoChao) {
            this.altura += 20;
        }
    }

    public void pula(){
        if (altura > RAIO) {
            som.play(Som.PULO);
            this.altura -= 150;
        }
    }

    public int getAltura() {
        return this.altura;
    }
}
