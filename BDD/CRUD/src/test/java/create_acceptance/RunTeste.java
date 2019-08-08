package create_acceptance;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features="/home/ronaldo/NetBeansProjects/bdd-cachaça-gestor/"
                                                    + "BDD/CRUD/src/test/resources/acceptance/insumo-features.feature", glue="stepDef")
public class RunTeste {
    
}
