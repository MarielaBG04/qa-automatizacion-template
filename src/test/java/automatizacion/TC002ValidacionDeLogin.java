package automatizacion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC002ValidacionDeLogin {
    //Scripr para validar el Login Page
    WebDriver driver;

    //Acciones que se van a ejecutar antes de cada test
        @BeforeEach 
        public void inicializarNavegador(){
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }

// Test 1: Validar el titulo de la pagina antes de realizar login
@Test 
public void validarTituloDeLaPagina(){
    //Abrir nuestro navegador
    driver.get("https://the-internet.herokuapp.com/login");

    //Obtener titulo del sitio web
    String tituloActual = driver.getTitle();
    String tituloEsperado = "The Internet";

    //Validar Titulo
    Assertions.assertEquals(
        tituloEsperado, 
        tituloActual,
        "El titulo no coincide con lo esperado");

        System.out.println("Titulo fue validado correctamente");

    }
    //Validar login exitoso
    @Test 
    public void loginExitoso(){
       driver.get("https://the-internet.herokuapp.com/login");
       //Ingresar las credenciales de autenticación
       driver.findElement(By.id("username")).sendKeys("tomsmith");
       driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");

       driver.findElement(By.className("radius")).click();

       //Validación de URL
       String urlEsperada = "https://the-internet.herokuapp.com/secure";

       Assertions.assertEquals(urlEsperada, driver.getCurrentUrl(),
    "La url encontrada no coincide con la esperada");

        //Validación de mensaje exitoso

        WebElement mensajeLoginExitoso = driver.findElement(By.id("flash"));
        String textoMensaje = mensajeLoginExitoso.getText();

        //Uso condicional para validar login exitoso
        if(textoMensaje.contains("You logged into a secure area!")){
            System.out.println("Login exitoso validado correctamente");
        }else{
            System.out.println("Error en el mensaje de login encontrado");

            Assertions.fail("Se esperaba el mensaje: You Logged into a secure area!"
                + "\n Pero se encontró:"
                + textoMensaje);
        }
      
    }

    //Acciones que se ejecutan después de cada test
    @AfterEach 
    public void cerrarSesion(){
        driver.quit();
    }
    
}
