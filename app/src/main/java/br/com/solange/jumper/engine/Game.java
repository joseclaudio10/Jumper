package br.com.solange.jumper.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import br.com.solange.jumper.R;
import br.com.solange.jumper.elementos.Canos;
import br.com.solange.jumper.elementos.GameOver;

/**
 * Created by Solange on 15/06/2015.
 */
public class Game extends SurfaceView implements Runnable, View.OnTouchListener {
    private boolean isRunning = true;
    private final SurfaceHolder holder = getHolder();
    private Passaro passaro;
    private Bitmap background;
    private Tela tela;
    private Canos canos;
    private Pontuacao pontuacao;
    private Som som;

    public Game(Context context){

        super(context);
        tela = new Tela(context);
        this.som = new Som(context);
        inicializaElementos(context);
        setOnTouchListener(this);

    }

    private void inicializaElementos(Context context) {
        this.pontuacao = new Pontuacao(som);
        this.passaro = new Passaro(tela, context, som);

        this.canos = new Canos(tela, pontuacao, context);

        Bitmap back = BitmapFactory.decodeResource(getResources(), R.drawable.background);

        this.background = Bitmap.createScaledBitmap(back, tela.getLargura(), tela.getAltura(), false);
    }

    @Override
    public void run() {
        while (isRunning){

            if(!holder.getSurface().isValid()) continue;
            Canvas canvas = holder.lockCanvas();

            canvas.drawBitmap(background, 0, 0, null);

            if(new VerificadorDeColisao(passaro, canos).temColisao()){
                som.play(Som.COLISAO);
                new GameOver(tela).desenhaNo(canvas);
                isRunning = false;
            }

            passaro.desenhaNo(canvas);
            passaro.cai();
            canos.desenhaNo(canvas);
            canos.move();
            pontuacao.desenhaNo(canvas);
            holder.unlockCanvasAndPost(canvas);
        }
    }
    public void cancela(){
        this.isRunning = false;
    }

    public void inicia() {
        this.isRunning = true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        passaro.pula();
        return false;
    }
}
