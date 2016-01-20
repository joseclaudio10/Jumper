package br.com.solange.jumper.engine;

import br.com.solange.jumper.elementos.Canos;

/**
 * Created by Solange on 21/06/2015.
 */
public class VerificadorDeColisao {

    private final Passaro passaro;
    private final Canos canos;

    public VerificadorDeColisao(Passaro passaro, Canos canos) {
        this.passaro = passaro;
        this.canos = canos;
    }

    public boolean temColisao(){
        return canos.temColisaoCom(passaro);
    }
}
