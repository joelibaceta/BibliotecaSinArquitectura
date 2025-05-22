
# Instrucciones para la actividad

## 🔧 Reestructuración del Proyecto: Ejercicio de Arquitectura Modular

Este proyecto de biblioteca fue desarrollado inicialmente sin una arquitectura modular clara. 
Como parte del curso, el objetivo de esta actividad es **proponer una nueva arquitectura interna del sistema**.

**modularizando el monolito**, sin necesidad de migrarse a microservicios u otros enfoques más complejos.

## 🎯 Objetivo

Rediseñar la estructura del sistema para que sea más clara, escalable y fácil de mantener, 
usando un monolito con una arquitectura modular.

### 🧠 Lógica actual del sistema

- **Estudiantes** pueden tomar prestados hasta **3 libros** simultáneamente.
- **Profesores** pueden tomar hasta **5 libros**.
- La clase `Library` administra los préstamos y verifica si el usuario aún puede tomar otro libro.
- No hay una separación clara de responsabilidades (por ejemplo, las reglas de préstamo están embebidas en la lógica general).
- Tampoco hay una estructura modular del sistema que permita escalarlo fácilmente o mantenerlo en el tiempo.

Este proyecto fue construido sin aplicar principios de arquitectura, y servirá como punto de partida para repensar su diseño organizacional.

---

## 📐 Formas de entrega según tu perfil

Hay **dos formas de entregar esta actividad**, dependiendo de tu nivel de familiaridad con la programación:

### 👨‍💻 Si eres un programador activo o manejas Java

Puedes realizar la entrega directamente como código:

- Refactoriza el proyecto original aplicando una estructura modular clara.
- Organiza tu Pull Request (PR) incluyendo la nueva estructura de carpetas y clases.
- Acompaña el PR con un resumen breve de tu propuesta de arquitectura en el `README.md`.

### ✍️ Si no eres programador activo o Java no es tu lenguaje principal

Puedes entregar tu propuesta en formato visual:

- Un diagrama que represente la estructura de carpetas que propones.
- Un diagrama de clases conceptual que muestre cómo modularizarías las entidades y responsabilidades.
- Puedes usar herramientas como [draw.io](https://app.diagrams.net/), Lucidchart, Figma, o incluso una imagen dibujada a mano clara.

## 📬 Entrega

- Si vas a hacer una **entrega con código Java**, organiza tu PR con la estructura propuesta y asegúrate de explicar brevemente tu diseño en el `README.md`.
- Si vas a entregar diagramas, **envíalos al correo**:

  **📧** mail@joelibaceta.com  
  **📝 Asunto:** `Arquitectura de Software G3 - Nombre y Apellido - Evaluacion 1`

## ✅ ¿Qué se evaluará?

- Claridad en la separación de responsabilidades.
- Lógica de modularización adecuada (no todo junto en un solo paquete).
- Coherencia en el uso de capas o módulos
- Entregables completos y bien justificados (diagrama y/o código).
- Presentación ordenada y profesional.

# Resolucion del Problema Planteado
## Alumno: Carlos Eduardo Cóndor Callupe
## Curso: Bootcamp de Arquitectura de Software
## Refactor: Estructura Modular en Java

Se reorganizó el proyecto aplicando una estructura modular con clean architecture:

- `/domain/model`: Entidades del sistema. Representan los conceptos del dominio como Book y User. Estas clases solo contienen lógica propia del objeto (por ejemplo, métodos para pedir o devolver un libro).
- `/domain/repository`: Interfaces que definen los contratos para acceder a los datos (por ejemplo, BookRepository, UserRepository). No contienen implementación.
- `/usecase`: Lógica de aplicación. Cada clase implementa un caso de uso concreto del sistema como agregar un libro, registrar un usuario o generar un reporte (AddBookUseCase, RegisterUserUseCase, etc.).
- `/infrastructure/repository`: Implementaciones concretas de los repositorios definidos en domain/repository. Por ejemplo, InMemoryBookRepository almacena los datos en memoria.
- `/app`:unto de entrada de la aplicación. La clase Main.java se encarga de armar los casos de uso y orquestar el flujo principal del programa.

Esto permite mayor claridad, separación de responsabilidades y facilita futuras ampliaciones.

## Diagrama de clases de la App Library
```mermaid

classDiagram
    direction LR

    class Book {
        - title : String
        + getTitle() : String
    }

    class User {
        - name : String
        - type : String
        - borrowedBooks : List~Book~
        + getName() : String
        + getType() : String
        + getBorrowedBooks() : List~Book~
        + borrowBook(book: Book) : void
        + returnBook(book: Book) : void
    }

    class BookRepository {
        <<interface>>
        + add(Book) : void
        + remove(Book) : void
        + findAll() : List~Book~
        + exists(Book) : boolean
    }

    class UserRepository {
        <<interface>>
        + add(User) : void
        + findAll() : List~User~
        + exists(User) : boolean
    }

    class InMemoryBookRepository {
        - books : List~Book~
        + add(Book) : void
        + remove(Book) : void
        + findAll() : List~Book~
        + exists(Book) : boolean
    }

    class InMemoryUserRepository {
        - users : List~User~
        + add(User) : void
        + findAll() : List~User~
        + exists(User) : boolean
    }

    class AddBookUseCase {
        - bookRepository : BookRepository
        + execute(Book) : void
    }

    class RemoveBookUseCase {
        - bookRepository : BookRepository
        + execute(Book) : void
    }

    class RegisterUserUseCase {
        - userRepository : UserRepository
        + execute(User) : void
    }

    class BorrowBookUseCase {
        - bookRepository : BookRepository
        - userRepository : UserRepository
        + execute(User, Book) : boolean
    }

    class ReturnBookUseCase {
        - bookRepository : BookRepository
        - userRepository : UserRepository
        + execute(User, Book) : void
    }

    class GenerateReportUseCase {
        - bookRepository : BookRepository
        - userRepository : UserRepository
        + execute() : String
    }

    class Main

    %% Relationships
    InMemoryBookRepository --|> BookRepository
    InMemoryUserRepository --|> UserRepository

    AddBookUseCase --> BookRepository
    RemoveBookUseCase --> BookRepository
    RegisterUserUseCase --> UserRepository
    BorrowBookUseCase --> BookRepository
    BorrowBookUseCase --> UserRepository
    ReturnBookUseCase --> BookRepository
    ReturnBookUseCase --> UserRepository
    GenerateReportUseCase --> BookRepository
    GenerateReportUseCase --> UserRepository

    Main --> AddBookUseCase
    Main --> RemoveBookUseCase
    Main --> RegisterUserUseCase
    Main --> BorrowBookUseCase
    Main --> ReturnBookUseCase
    Main --> GenerateReportUseCase

    User "1" o-- "*" Book : borrows

```

## Diagrama de Secuencia Prestar Libro

```mermaid
sequenceDiagram
    participant Usuario
    participant SistemaBiblioteca
    participant Libro

    Usuario->>SistemaBiblioteca: Solicita préstamo de libro
    SistemaBiblioteca->>Libro: Verifica disponibilidad
    alt Libro disponible
        Libro-->>SistemaBiblioteca: Disponible
        SistemaBiblioteca->>Usuario: Libro disponible
        Usuario->>SistemaBiblioteca: Confirma préstamo
        SistemaBiblioteca->>Libro: Registra préstamo
        SistemaBiblioteca->>Usuario: Préstamo registrado
    else Libro no disponible
        Libro-->>SistemaBiblioteca: No disponible
        SistemaBiblioteca->>Usuario: Libro no disponible
    end
```

## Diagrama de Componentes

```mermaid
graph TB

%% Capa de Aplicación
subgraph "Aplicación"
    MainApp["Main.java"]
end

%% Casos de uso (Application Layer)
subgraph "Casos de Uso (Application Layer)"
    AddBookUC["AddBookUseCase"]
    RemoveBookUC["RemoveBookUseCase"]
    RegisterUserUC["RegisterUserUseCase"]
    BorrowBookUC["BorrowBookUseCase"]
    ReturnBookUC["ReturnBookUseCase"]
    GenerateReportUC["GenerateReportUseCase"]
end

%% Dominio
subgraph "Dominio"
    Book["Book (Entidad)"]
    User["User (Entidad)"]
    BookRepo["BookRepository (Interface)"]
    UserRepo["UserRepository (Interface)"]
end

%% Infraestructura
subgraph "Infraestructura"
    BookRepoImpl["BookRepositoryImpl"]
    UserRepoImpl["UserRepositoryImpl"]
end

%% Relaciones
MainApp --> AddBookUC
MainApp --> RemoveBookUC
MainApp --> RegisterUserUC
MainApp --> BorrowBookUC
MainApp --> ReturnBookUC
MainApp --> GenerateReportUC

AddBookUC --> BookRepo
RemoveBookUC --> BookRepo
RegisterUserUC --> UserRepo
BorrowBookUC --> BookRepo
BorrowBookUC --> UserRepo
BorrowBookUC --> User
ReturnBookUC --> BookRepo
ReturnBookUC --> UserRepo
ReturnBookUC --> User
GenerateReportUC --> BookRepo
GenerateReportUC --> UserRepo

BookRepoImpl --> BookRepo
UserRepoImpl --> UserRepo

```