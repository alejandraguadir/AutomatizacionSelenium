package co.com.choucair.ejemplo.stepdefinitions;

import co.com.choucair.ejemplo.models.SetDataReunion;
import co.com.choucair.ejemplo.questions.CompararListas;
import co.com.choucair.ejemplo.tasks.tasks2.CrearReunion;
import co.com.choucair.ejemplo.tasks.tasks2.ExtraerDataReunion;
import co.com.choucair.ejemplo.tasks.tasks2.RealizarLogin;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;


import java.util.logging.Logger;

import static co.com.choucair.ejemplo.tasks.SeleccionarMenuReunion.seleccionarMenuReunion;
import static co.com.choucair.ejemplo.tasks.UnidadDeNegocio.unidadDeNegocio;
import static co.com.choucair.ejemplo.tasks.tasks2.CrearReunion.crearReunion;
import static co.com.choucair.ejemplo.tasks.tasks2.CrearUNegocio.crearUNegocio;
import static co.com.choucair.ejemplo.tasks.tasks2.ExtraerDataReunion.extraerDataReunion;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;


public class Demo2StepDefinitions {
    private static final Logger log = Logger.getLogger(String.valueOf(Demo2StepDefinitions.class));
    private final SetDataReunion setDataReunion = new SetDataReunion();


    @Before
    public void initialConfigurationDektop() {

        try{
            WebDriverManager.chromedriver().setup();
            OnStage.setTheStage(new OnlineCast());
            OnStage.theActorCalled("actor").wasAbleTo(
                    Open.url("https://demo.serenity.is/Account/Login/?ReturnUrl=%2F")
            );
            log.info("Ingreso a URL correctamente");
        }catch (Exception e ){
            log.info("Fallo conexión a URL");
            log.warning(e.getMessage());
        }
    }

    @Given("el administrador inicia sesion")
    public void elAdministradorIniciaSesion(DataTable data) {
        try {
            theActorInTheSpotlight().attemptsTo(
                    RealizarLogin.ingresarCredenciales(setDataReunion.setData(data).get(0))
            );

            log.info("Login realizado correctamente");

        }catch (Exception e){
            log.info("Fallo al crear unidad de negocio");
            log.warning(e.getMessage());
        }


    }

    @When("el administrador crea la unidad de negocio")
    public void elAdministradorCreaLaUnidadDeNegocio(DataTable data) {
        try {
            theActorInTheSpotlight().attemptsTo(
                    unidadDeNegocio()
            );
            log.info("Menú Unidad de negocio seleccionado correctamente");
            theActorInTheSpotlight().attemptsTo(
                    crearUNegocio(setDataReunion.setData(data).get(0))
            );
            log.info("Menú Unidad de negocio creada correctamente");
        }catch (Exception e){
            log.info("Fallo al crear unidad de negocio");
            log.warning(e.getMessage());
        }

    }

    @When("el administrador programa la reunion")
    public void elAdministradorProgramaLaReunion(DataTable data) throws InterruptedException {
        try {
            theActorInTheSpotlight().attemptsTo(
                    seleccionarMenuReunion()
            );
            log.info("Menú reunión seleccionada correctamente");

            theActorInTheSpotlight().attemptsTo(
                    crearReunion(setDataReunion.setData(data).get(0))

            );
            log.info("Reunion creada correctamente con los siguientes datos: " + data);
            Thread.sleep(1000);

            theActorInTheSpotlight().attemptsTo(
                    extraerDataReunion()
            );
        }catch (Exception e){
            log.info("Fallo al programar la reunión");
            log.warning(e.getMessage());
        }
    }

    @Then("la reunion se programara de manera exitosa")
    public void laReunionSeProgramaraDeManeraExitosa() {
        try {
            OnStage.theActorInTheSpotlight().should(GivenWhenThen.seeThat(CompararListas.value(CrearReunion.dataEntrada, ExtraerDataReunion.dataRegistrada)));
            log.info("Aserción realizada con éxito");
            log.info("Datos esperados: " + CrearReunion.dataEntrada);
            log.info("Datos obtenidos: " + ExtraerDataReunion.dataRegistrada);

        } catch (Exception e) {
            log.info("Aserción fallida");
            log.warning(e.getMessage());

        }



    }


}
