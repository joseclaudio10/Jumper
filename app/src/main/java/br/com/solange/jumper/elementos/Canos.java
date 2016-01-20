package br.com.solange.jumper.elementos;

import android.content.Context;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import br.com.solange.jumper.engine.Cano;
import br.com.solange.jumper.engine.Passaro;
import br.com.solange.jumper.engine.Pontuacao;
import br.com.solange.jumper.engine.Tela;

/**
 * Created by Solange on 19/06/2015.
 */
public class Canos {

    private static final int QUANTIDADE_DE_CANOS = 5;
    private static final int DISTANCIA_ENTRE_CANOS = 250;
    private List<Cano> canos = new ArrayList<Cano>();
    private Tela tela;
    private final Pontuacao pontuacao;
    Context context;

    public Canos(Tela tela, Pontuacao pontuacao, Context context){
        this.pontuacao = pontuacao;
        this.context = context;
        int posicaoInicial = 800;
        this.tela = tela;
        for (int i=0; i<QUANTIDADE_DE_CANOS; i++){
            posicaoInicial += DISTANCIA_ENTRE_CANOS;
            canos.add(new Cano(tela, posicaoInicial));
        }
    }

    public void desenhaNo(Canvas canvas) {
        for (Cano cano : canos){
            cano.desenhaNo(canvas, context);
        }
    }

    public void move(){
        ListIterator<Cano> iterator = canos.listIterator();
        while (iterator.hasNext()) {
            Cano cano = (Cano) iterator.next();
            cano.move();

            if (cano.saiuDaTela()){
                pontuacao.aumenta();
                iterator.remove();
                Cano outroCano = new Cano(tela, getMaximo() + DISTANCIA_ENTRE_CANOS);
                iterator.add(outroCano);
            }
        }
    }

    private int getMaximo() {
        int maximo = 0;
        for (Cano cano : canos){
            maximo = (int) Math.max(cano.getPosicao(), maximo);
        }
        return maximo;
    }

    public boolean temColisaoCom(Passaro passaro) {
        for (Cano cano : canos){
            if (cano.temColisaoHorizontalCom(passaro) && cano.temColisaoVerticalCom(passaro)){
                return true;
            }
        }
        return false;
    }
}
