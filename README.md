# 📚 Proyecto Biblioteca Modular

Este proyecto es una reestructuración de un sistema monolítico de gestión de biblioteca, aplicando arquitectura modular dentro de un monolito, para mantenimiento y escalabilidad.

# 🧱 Estructura del Proyecto
```
📁 src/
├── 📁 main/
│   └── 📁 java/
│       └── 📁 library/
│           ├── 📁 domain/   # Entidades principales del dominio
│           │   ├── Book.java
│           │   ├── Library.java
│           │   └── User.java
│           ├── 📁 loan/     # Reglas y lógica de préstamos
│           │   ├── LoanPolicy.java
│           │   ├── LoanPolicyResolver.java
│           │   ├── LoanService.java
│           │   ├── StudentLoanPolicy.java
│           │   └── TeacherLoanPolicy.java
│           ├── 📁 report/    # Generador de reportes
│           │   └── LibraryReportGenerator.java
│           └── Main.java     # Clase ejecutable principal
├── 📁 test/
│   └── 📁 java/
│       └── 📁 library/
│           ├── LibraryTest.java
│           ├── UserTest.java
│           ├── loan/
│           │   ├── LoanPolicyTest.java
│           │   └── LoanServiceTest.java
│           └── report/
│               └── LibraryReportGeneratorTest.java
└── pom.xml                   # Configuración Maven
```

# 🚀 Cómo ejecutar el proyecto desde cero

1. 🧰 Requisitos

- Java 17 instalado

- Maven 3.6.3 instalado

2. 🔧 Clonar o posicionarte en el proyecto

```sh
cd ~/test_1_codigo_house/src
```

3. 🧪 Ejecutar las pruebas

```sh
mvn clean test
```

Verás una salida indicando cuántos tests pasaron.

4. ▶️ Ejecutar el programa principal

```sh
mvn compile exec:java
```

Asegúrate de que Main.java contiene un método main() dentro del paquete library.


# 📦 Modularización aplicada

- `domain`: representa el núcleo del modelo de negocio (libros, usuarios, biblioteca).

- `loan`: contiene la lógica de negocio para préstamos, políticas por tipo de usuario y el servicio que lo gestiona.

- `report`: genera reportes de estado de la biblioteca.

- `Main.java`: simula el uso del sistema y permite iniciar la app desde consola.