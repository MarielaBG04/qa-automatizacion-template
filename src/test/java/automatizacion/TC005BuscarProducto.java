package automatizacion;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC005BuscarProducto {
       WebDriver driver;

    //Acciones que se van a ejecutar antes de cada test
        @BeforeEach 
        public void inicializarNavegador(){
            driver = new ChromeDriver();
            driver.manage().window().maximize();

 }
            @Test 
    public void buscarProducto(){
       driver.get("https://teststore.automationtesting.co.uk/index.php");
       //Ingresar producto a buscar
       driver.findElement(By.name("s")).sendKeys("Mug");
       driver.findElement(By.name("s")).submit();

       //Validación de URL
       String urlEsperada = "https://teststore.automationtesting.co.uk/index.php?controller=search&s=Mug";

       Assertions.assertEquals(urlEsperada, driver.getCurrentUrl(),
    "La url encontrada no coincide con la esperada");

        //Validación de búsqueda correcta

        WebElement mensajeBusquedaExitosa = driver.findElement(By.className("product-description"));
        String textoMensaje = mensajeBusquedaExitosa.getText();

           //Uso condición para validar resultados
        if(textoMensaje.contains("Mug")){
            System.out.println("Búsqueda exitosa! Se ha encontrado al menos un producto relacionado");
        }else{
            System.out.println("Error en la búsqueda realizada");

            Assertions.fail("Se esperaban productos de tipo: Mug"
                + "\n Pero se encontró:"
                + textoMensaje);
        }

        }

      @Test 
      public void buscarPorCategoria(){
        driver.get("https://teststore.automationtesting.co.uk/index.php");
       //Seleccionar categoria de busqueda
       driver.findElement(By.id("category-9")).click();

       //Validación de URL
       String urlEsperada = "https://teststore.automationtesting.co.uk/index.php?id_category=9&controller=category";

       Assertions.assertEquals(urlEsperada, driver.getCurrentUrl(),
    "La url encontrada no coincide con la esperada");

       //Validación de categoría correcta

        WebElement textoCategoria = driver.findElement(By.id("main"));
        String categoriaSeleccionada = textoCategoria.getText();

          if(categoriaSeleccionada.contains("ART")){
            System.out.println("Categoria seleccionada correctamente");
        }else{
            System.out.println("Error en la categoria encontrada");

            Assertions.fail("Se esperaba el mensaje: ART"
                + "\n Pero se encontró:"
                + categoriaSeleccionada);
        }
      
      } 
         //Acciones que se ejecutan después de cada test
    @AfterEach 
    public void cerrarSesion(){
        driver.quit();
    }

}
