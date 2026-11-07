package automatizacion;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC003EjercicioLogin {
       WebDriver driver;

    //Acciones que se van a ejecutar antes de cada test
        @BeforeEach 
        public void inicializarNavegador(){
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }

         @Test 
    public void loginExitoso(){
       driver.get("https://www.automationtesting.co.uk/contactForm.html");
       //Ingresar las credenciales de autenticación
       driver.findElement(By.name("first_name")).sendKeys("Fran");
       driver.findElement(By.name("last_name")).sendKeys("Artiga");
       driver.findElement(By.name("email")).sendKeys("fran.artiga@test.com");
       driver.findElement(By.name("message")).sendKeys("Este es un mensaje de prueba para validar el formulario.");

       driver.findElement(By.cssSelector("#form_buttons > input:nth-child(2)")).click();
       

       //Validación de URL
       String urlEsperada = "https://www.automationtesting.co.uk/contact-form-thank-you.html";

       Assertions.assertEquals(urlEsperada, driver.getCurrentUrl(),
    "La url encontrada no coincide con la esperada");

        //Validación de mensaje exitoso

        WebElement mensajeLoginExitoso = driver.findElement(By.id("main"));
        String textoMensaje = mensajeLoginExitoso.getText();

        //Uso condicional para validar login exitoso
        if(textoMensaje.contains("Thank you for your mail!")){
            System.out.println("Login exitoso validado correctamente");
        }else{
            System.out.println("Error en el mensaje de login encontrado");

            Assertions.fail("Se esperaba el mensaje: Thank you for your mail!"
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
