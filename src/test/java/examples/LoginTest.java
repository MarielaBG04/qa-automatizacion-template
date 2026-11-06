package examples;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
        //Comentario para ver cambios

    @Test
    public void loginExitoso() {

        WebDriver driver = new ChromeDriver();

        try {

            // Abrir sitio
            driver.get("https://practicetestautomation.com/practice-test-login/");
            driver.manage().window().maximize();

            // Login
            driver.findElement(By.id("username"))
                    .sendKeys("student");

            driver.findElement(By.id("password"))
                    .sendKeys("Password123");

            driver.findElement(By.id("submit"))
                    .click();

            // Assertion 1: Validar URL
            String urlEsperada =
                    "https://practicetestautomation.com/logged-in-successfully/";

            Assertions.assertEquals(
                    urlEsperada,
                    driver.getCurrentUrl(),
                    "La URL después del login no es la esperada");

            // Assertion 2: Validar mensaje de éxito
            WebElement mensaje =
                    driver.findElement(By.className("post-title"));

            Assertions.assertEquals(
                    "Logged In Successfully",
                    mensaje.getText(),
                    "El mensaje de éxito no coincide");

            System.out.println("Login exitoso validado correctamente");

        } finally {

            driver.quit();
        }
    }
}