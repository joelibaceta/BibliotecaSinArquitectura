# Solucion de la actividad modulo 1 - Jonathan Oblitas
Refactorización: Arquitectura Modular
Esta solución implementa una arquitectura en capas clásica, en lugar de una arquitectura limpia, priorizando simplicidad, legibilidad y una separación más clara de responsabilidades. Las clases están organizadas por función en las capas de aplicación, dominio, persistencia.

## qué se buscó conseguir ?
- Claridad en la separacion de responsabilidades
- Codigo modular y más legible

## ✅ Cambios realizados en la refactorización
### 🔁 Aplicación de estilo arquitectonico en capas

El sistema se dividió en tres capas principales:

- **`app/`**: contiene el `Main.java`, que actúa como punto de entrada del sistema.
- **`domain/`**: incluye la lógica central del sistema, entidades, políticas de préstamo y generación de reportes.
- **`persistence/`**: contiene las clases que simulan el almacenamiento de datos.

---

###  Organización de carpetas por responsabilidad

### 📂 Domain
- `model`: entidades (`Book`, `User`, `Library`).
- 
- `policy`: reglas de préstamo según el tipo de usuario (`StudentLoanPolicy`, `TeacherLoanPolicy`).
- 
- `report`: generación de reportes (`LibraryReportGenerator`).
### 📂 Persistence
- `BookRepository.java` y `UserRepository.java`: almacenamiento simulado.

### 📂 app
- `app/Main.java`: orquestador de flujo general.

---


### 🧠 Algunos Principios SOLID aplicados 

- **SRP**: cada clase busca cumplir una única función.
- **OCP**: nuevas reglas de préstamo pueden añadirse sin modificar las existentes Y también lo mismo con los reportes.
---

###  Sobre el test
- se modifico el test para que se pudiesen hacer las pruebas
---

## 🔄 Diagrama de Secuencia: Préstamo de Libro
````
Usuario
│
│ Solicita préstamo de libro
▼
Main.java
│
│ Llama a Library.borrowBook(user, book)
▼
Library.java
│
│ Verifica si el libro está disponible:
│ → bookRepository.exists(book)
│ ← true / false
│
│ Si está disponible:
│ Determina el tipo de usuario:
│ ├─ Si Student:
│ │ → studentLoanPolicy.canBorrow(user)
│ │ ← true / false
│ └─ Si Teacher:
│ → teacherLoanPolicy.canBorrow(user)
│ ← true / false
│
│ Si puede tomar prestado:
│ → user.borrowBook(book)
│ → bookRepository.delete(book)
▼
Retorna resultado a Main.java
````
---

---

---
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
