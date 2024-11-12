
Este repositorio contiene un proyecto de ejemplo para gestionar una biblioteca. En él se implementan las entidades principales, como `Libro` y `Usuario`, y se establecen políticas de préstamos diferenciadas según el tipo de usuario:

- **Alumnos**: pueden tomar prestados hasta 3 libros.
- **Profesores**: pueden tomar prestados hasta 5 libros.

Este proyecto servirá como base para aplicar los principios de diseño SOLID, con el objetivo de mejorar la estructura del código y hacerlo más escalable y mantenible.

## Objetivo

La finalidad de este repositorio es proporcionar un código base que pueda ser refactorizado aplicando los principios SOLID. Este ejercicio permitirá repasar y consolidar el conocimiento de estos principios fundamentales en el desarrollo de software orientado a objetos.

## Presentación del Trabajo

Para entregar el trabajo, realiza una refactorización del código aplicando los principios SOLID. Esta refactorización debe presentarse como un **Pull Request (PR)** sobre el repositorio original. El PR debe incluir:

1. **Descripción clara de los cambios** realizados para cada principio SOLID, explicando cómo se ha modificado el diseño del código.
2. **Justificación técnica** de cómo cada cambio contribuye a mejorar la estructura del proyecto.
3. **Evidencias de Pruebas**: Si es posible, incluye casos de prueba que demuestren el correcto funcionamiento de la nueva estructura.

El Pull Request permitirá visualizar los cambios y sus efectos de forma estructurada y facilita la revisión. 



## Principios SOLID

### 1. Principio de Responsabilidad Única (SRP - Single Responsibility Principle)

Cada clase debe tener una única responsabilidad o motivo para cambiar. Esto implica que cada clase debe enfocarse en una única función o comportamiento específico dentro de la aplicación.

- **Ejemplo en este proyecto**: La clase `Usuario` debe centrarse solo en la información y comportamiento básico de un usuario, sin incluir la lógica de préstamos. La política de préstamos debe manejarse en una clase separada.

### 2. Principio de Abierto/Cerrado (OCP - Open/Closed Principle)

Las clases deben estar abiertas para extensión, pero cerradas para modificación. Esto significa que, una vez que se ha desarrollado una clase, su estructura interna no debería cambiar. En su lugar, debería poder extenderse mediante herencia o composición.

- **Ejemplo en este proyecto**: Para agregar un nuevo tipo de usuario (por ejemplo, `Investigador`), podemos extender la política de préstamos sin modificar la lógica existente.

### 3. Principio de Sustitución de Liskov (LSP - Liskov Substitution Principle)

Si una clase A es una subclase de una clase B, se debe poder usar A en cualquier lugar donde se utilice B sin que el comportamiento de la aplicación cambie. En términos simples, una subclase debe ser intercambiable con su clase base.

- **Ejemplo en este proyecto**: Los tipos de usuarios (`Alumno`, `Profesor`, etc.) deben ser subclases de `Usuario` y cumplir con las reglas generales de comportamiento de un usuario.

### 4. Principio de Segregación de Interfaces (ISP - Interface Segregation Principle)

Las clases no deben estar obligadas a implementar interfaces que no utilizan. En lugar de una interfaz monolítica, es preferible tener varias interfaces específicas para cada funcionalidad.

- **Ejemplo en este proyecto**: Podríamos tener interfaces separadas para `PrestamoLimitado` (alumnos) y `PrestamoExtendido` (profesores), en lugar de que `Usuario` implemente todas las interfaces posibles de préstamo.

### 5. Principio de Inversión de Dependencia (DIP - Dependency Inversion Principle)

Las clases deben depender de abstracciones (interfaces) en lugar de implementaciones concretas. Esto ayuda a reducir el acoplamiento y permite cambiar la implementación sin afectar las clases que la usan.

- **Ejemplo en este proyecto**: Podríamos usar una interfaz para la política de préstamos (`PoliticaPrestamo`) y luego inyectar la política específica (para `Alumno` o `Profesor`) según corresponda. Esto permite cambiar la lógica de préstamos fácilmente sin afectar al resto de la aplicación.
