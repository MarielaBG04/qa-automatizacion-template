package automatizacion;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EX002ComprarUserInvitado {
           WebDriver driver;

    //Acciones que se van a ejecutar antes de cada test
        @BeforeEach 
        public void inicializarNavegador(){
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }

             @Test 
    public void CompraDeInvitado(){
       driver.get("https://teststore.automationtesting.co.uk/index.php");
       
       // Navegación en catalogo de productos
       driver.findElement(By.id("category-9")).click();

       //Validación de URL
        String urlEsperada = "https://teststore.automationtesting.co.uk/index.php?id_category=9&controller=category";
       Assertions.assertEquals(urlEsperada, driver.getCurrentUrl(),
    "La url encontrada no coincide con la esperada");

       //Agregar productos en carrito

    driver.findElements(By.cssSelector("a.product-thumbnail"))
      .get(0)
      .click();

      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

WebElement btnAddToCart = wait.until(
    ExpectedConditions.elementToBeClickable(
        By.cssSelector("button[data-button-action='add-to-cart']")
    )
);
btnAddToCart.click();

          //Confirmar en el modal de checkout
      
        WebElement btnCheckout = wait.until(
        ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@class,'btn') and contains(.,'Proceed to checkout')]")
        ));

btnCheckout.click();
// Confirmar la compra

 WebElement btnFinalizar = wait.until(
        ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@class,'btn') and contains(.,'Proceed to checkout')]")
        ));

btnFinalizar.click();

driver.findElement(By.id("field-id_gender-1")).click();
driver.findElement(By.id("field-firstname")).clear();
driver.findElement(By.id("field-firstname")).sendKeys("Mariela");
driver.findElement(By.id("field-lastname")).clear();
driver.findElement(By.id("field-lastname")).sendKeys("Barrera");
driver.findElement(By.id("field-email")).clear();
driver.findElement(By.id("field-email")).sendKeys("mariela@prueba.com");

// Seleccionar términos y condiciones

driver.findElement((By.name("psgdpr"))).click();
// Clic en Continue
WebElement btnContinue = wait.until(
    ExpectedConditions.elementToBeClickable(
        By.name("continue")
    )
);

btnContinue.click();

driver.findElement(By.id("field-address1")).sendKeys("San Salvador");
driver.findElement(By.id("field-city")).sendKeys("San Salvador");
Select selectState = new Select(driver.findElement(By.id("field-id_state")));
selectState.selectByValue("2");
driver.findElement(By.id("field-postcode")).sendKeys("12345");


wait.until(ExpectedConditions.elementToBeClickable(
        By.name("confirm-addresses")))
    .click();

driver.findElement(By.id("delivery_message")).sendKeys("Prueba123");

WebElement btnContinueShipping = wait.until(
    ExpectedConditions.elementToBeClickable(
        By.name("confirmDeliveryOption")
    )
);
btnContinueShipping.click();

driver.findElement(By.id("payment-option-1")).click();
driver.findElement(By.id("conditions_to_approve[terms-and-conditions]")).click();

WebElement btnPlaceOrder = wait.until(
    ExpectedConditions.elementToBeClickable(
        By.cssSelector(".btn.btn-primary.center-block")
    )
);

btnPlaceOrder.click();

String texto = wait.until(
    ExpectedConditions.visibilityOfElementLocated(
        By.cssSelector(".card-title")
    )
).getText();

Assertions.assertTrue(texto.contains("ORDER"));

 }
      //Acciones que se ejecutan después de cada test
    @AfterEach 
    public void cerrarSesion(){
        driver.quit();
    }
 
}
