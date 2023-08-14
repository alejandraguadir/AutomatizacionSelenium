package co.com.choucair.ejemplo.questions;


import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.List;


public class CompararListas implements Question<Boolean> {

    private final List<String> dataEsperada;
    private final List<String> dataObtenida;

    public CompararListas(List<String> dataEsperada, List<String> dataObtenida) {
        this.dataEsperada = dataEsperada;
        this.dataObtenida = dataObtenida;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return dataEsperada.equals(dataObtenida);
    }

    public static CompararListas value(List<String> dataEsperada, List<String> dataObtenida) {
        return new CompararListas(dataEsperada, dataObtenida);
    }
}






