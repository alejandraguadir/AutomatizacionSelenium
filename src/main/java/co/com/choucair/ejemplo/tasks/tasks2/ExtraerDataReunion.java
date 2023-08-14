package co.com.choucair.ejemplo.tasks.tasks2;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import java.util.ArrayList;

import static co.com.choucair.ejemplo.userinterfaces.PaginaReuniones.*;

public class ExtraerDataReunion implements Task {

    public static ArrayList<String> dataRegistrada = new ArrayList<String>();
    @Override
    public <T extends Actor> void performAs(T actor) {


        String nombreReunion = NOMBRE_REUNION.resolveFor(actor).getText();
        String tipoReunion = NOMBRE_TIPO_DE_REUNION.resolveFor(actor).getText();
        String numeroReunion = NUMERO_REUNION.resolveFor(actor).getText();
        String ubicacionReunion = NOMBRE_UBICACION.resolveFor(actor).getText();
        String unidadReunion = NOMBRE_UNIDAD.resolveFor(actor).getText();
        String organizadorReunion = NOMBRE_ORGANIZADOR.resolveFor(actor).getText();
        String reporteroReunion = NOMBRE_REPORTERO.resolveFor(actor).getText();

        dataRegistrada.add(nombreReunion);
        dataRegistrada.add(tipoReunion);
        dataRegistrada.add(numeroReunion);
        dataRegistrada.add(ubicacionReunion);
        dataRegistrada.add(unidadReunion);
        dataRegistrada.add(organizadorReunion);
        dataRegistrada.add(reporteroReunion);
    }
    public static ExtraerDataReunion extraerDataReunion () {
        return new ExtraerDataReunion ();
    }

}
