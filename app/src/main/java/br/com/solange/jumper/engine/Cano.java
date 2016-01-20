package br.com.solange.jumper.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import br.com.solange.jumper.R;

/**
 * Created by Solange on 17/06/2015.
 */
public class Cano {
    private static final int TAMANHO_DO_CANO = 300;
    private static final int LARGURA_DO_CANO = 100;
    private int alturaDoCanoInferior;
    private int alturaDoCanoSuperior;
    private Tela tela;
    private int posicao;
    private final Paint VERDE = Cores.getCorDoCano();
    Bitmap bp, canoInferior, canoSuperior;

    public Cano(Tela tela, int posicao){
        this.tela = tela;
        this.posicao = posicao;
        this.alturaDoCanoInferior = tela.getAltura() - TAMANHO_DO_CANO - valorAleatorio();
        this.alturaDoCanoSuperior = 0 + TAMANHO_DO_CANO + valorAleatorio();
    }

    private int valorAleatorio() {
        return (int) (Math.random() * 350);
    }

    public void desenhaNo(Canvas canvas, Context context){
        bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.cano);
        desenhaCanoSuperiorNo(canvas, bp);
        desenhaCanoInferiorNo(canvas, bp);
    }

    private void desenhaCanoSuperiorNo(Canvas canvas, Bitmap bp) {
        this.canoSuperior = Bitmap.createScaledBitmap(bp, LARGURA_DO_CANO, this.alturaDoCanoSuperior, false);
        //canvas.drawRect(posicao, 0, posicao + LARGURA_DO_CANO, alturaDoCanoSuperior, VERDE);
        canvas.drawBitmap(canoSuperior, posicao, 0, null);
    }

    private void desenhaCanoInferiorNo(Canvas canvas, Bitmap bp) {
        this.canoInferior = Bitmap.createScaledBitmap(bp, LARGURA_DO_CANO, this.alturaDoCanoInferior, false);
        //canvas.drawRect(posicao, alturaDoCanoInferior, posicao + LARGURA_DO_CANO, tela.getAltura(), VERDE);
        canvas.drawBitmap(canoInferior, posicao, alturaDoCanoInferior, null);
    }

    public void move(){
        posicao -= 5;
    }

    public boolean saiuDaTela() {
        return posicao + LARGURA_DO_CANO < 0;
    }

    public double getPosicao() {
        return posicao;
    }

    public boolean temColisaoHorizontalCom(Passaro passaro) {
        return this.posicao - passaro.X < passaro.RAIO;
    }

    public boolean temColisaoVerticalCom(Passaro passaro) {
        return passaro.getAltura() -
                passaro.RAIO < this.alturaDoCanoSuperior
                || passaro.getAltura() + passaro.RAIO >
                this.alturaDoCanoInferior;
    }
}
