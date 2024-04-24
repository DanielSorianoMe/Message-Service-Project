# Message Service Project

Este proyecto es un servicio de mensajes construido con Spring Boot. A continuación se describe cómo ejecutar el proyecto, pruebas unitarias, y se ofrece una descripción de su estructura

## Configuración y Ejecución

Para ejecutar este proyecto, necesitas tener configurado un entorno de desarrollo de Java 17 y Maven3:

1. **Clonar el repositorio**:

   git clone
   

2. **Compilar y ejecutar el proyecto**:

    mvn clean install

    mvn spring-boot:run

3. **Pruebas Unitarias**

     Para ejecutar las pruebas unitarias:

     mvn test

3. **SOLID y Patrones de Diseño**

El proyecto sigue los principios SOLID para la arquitectura de software:

* Single Responsibility Principle (SRP): Cada clase tiene una única responsabilidad.
* Open/Closed Principle (OCP): El sistema está diseñado para ser extensible sin modificar el código existente.
* Liskov Substitution Principle (LSP): Las subclases pueden reemplazar a las clases base sin afectar el comportamiento del sistema.
* Interface Segregation Principle (ISP): Se utilizan interfaces específicas para definir responsabilidades.
* Dependency Inversion Principle (DIP): El sistema invierte las dependencias para promover la modularidad.