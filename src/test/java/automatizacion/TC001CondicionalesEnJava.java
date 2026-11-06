package automatizacion;

public class TC001CondicionalesEnJava {
    public static void main(String[] args){
//Declarar variables
String nombre= "Mariela";   
int edad =26;
boolean estudiante=true;

//Mostrar variables en consola
System.out.println("====DATOS VARIABLES====");
System.out.println("Nombre: "+nombre);
System.out.println("Edad: "+edad);
System.out.println("Estudiante: "+estudiante);
    
//Condicionales IF - ELSE
    if (edad >=18){
        System.out.println(nombre + " es mayor de edad");
         }else{
            System.out.println(nombre + "es menor de edad");
         }

         System.out.println("====DATOS VARIABLES====");
    if(estudiante){
        System.out.println(nombre + " si es estudiante de este curso");
    }  else{
        System.out.println(nombre + " no es estudiante de este curso");
    }   

    //EJEMPLO CICLO FOR
    System.out.println("===CICLO FOR===");
    for(int i = 1; i <=5; i++ ){
System.out.println("Iteracion For numero: " + i);
    }

    //EJEMPLO CICLO WHILE
    System.out.println("===CICLO While===");
    int contador =1;
    while (contador <=5){
        System.out.println("Iteración while numero: " + contador);
        contador++;
    }
System.out.println("\nFinalizan los ejemplos de JAVA");

    }
}
