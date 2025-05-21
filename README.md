
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

Se reorganizó el proyecto aplicando una estructura modular:

- `/model`: Entidades del sistema (Libro, Usuario).
- `/service`: Lógica principal del sistema (gestión de libros, préstamos).
- `/app`: Clase Main para ejecutar el sistema.

Esto permite mayor claridad, separación de responsabilidades y facilita futuras ampliaciones.

## Diagrama de clases de la App Library
```mermaid

classDiagram
    class Book {
        - String title
        + Book(String title)
        + getTitle(): String
    }

    class User {
        - String name
        - String type
        - List~Book~ borrowedBooks
        + User(String name, String type)
        + borrowBook(Book): void
        + returnBook(Book): void
        + getType(): String
        + getBorrowedBooks(): List~Book~
        + getName(): String
    }

    class LibraryService {
        - List~Book~ books
        - List~User~ users
        + LibraryService()
        + addBook(Book): void
        + removeBook(Book): void
        + registerUser(User): void
        + borrowBook(User, Book): boolean
        + returnBook(User, Book): void
        + generateReport(): String
        + isBookAvailable(Book): boolean
        + getBooks(): List~Book~
        + getUsers(): List~User~
    }

    Book --> User : used by
    User --> LibraryService : registered in
    Book --> LibraryService : managed by
```

## Diagrama de Secuencia Prestar Libro

```mermaid
sequenceDiagram
    participant Main
    participant LibraryService
    participant User

    Main->>LibraryService: borrowBook(user, book)
    LibraryService->>LibraryService: isBookAvailable(book)
    alt Libro disponible
        LibraryService->>User: getType()
        alt Usuario: Student y < 2 libros
            LibraryService->>User: borrowBook(book)
            LibraryService->>LibraryService: removeBook(book)
            LibraryService-->>Main: true
        else Usuario: Teacher y < 5 libros
            LibraryService->>User: borrowBook(book)
            LibraryService->>LibraryService: removeBook(book)
            LibraryService-->>Main: true
        else Límite alcanzado
            LibraryService-->>Main: false
        end
    else Libro no disponible
        LibraryService-->>Main: false
    end
```

## Diagrama de Secuencia Devolver Libro

```mermaid
sequenceDiagram
    participant Main
    participant LibraryService
    participant User
    
    Main->>LibraryService: returnBook(user, book)
    LibraryService->>LibraryService: isBookAvailable(book)
    alt Libro NO disponible en biblioteca
        LibraryService->>User: returnBook(book)
        User-->>LibraryService: libro eliminado de la lista del usuario
        LibraryService->>LibraryService: addBook(book)
    else Libro ya disponible
        Note over LibraryService: No se realiza ninguna acción
    end
```

## Diagrama de Componentes

```mermaid
graph TD

subgraph App
    Main[Main.java]
end

subgraph Service Layer
    LibraryService[LibraryService.java]
end

subgraph Domain Model
    Book[Book.java]
    User[User.java]
end

Main --> LibraryService
LibraryService --> Book
LibraryService --> User

```