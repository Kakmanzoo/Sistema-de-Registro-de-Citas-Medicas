# Sistema de Gestión de Citas de Salud (Java Swing)

Aplicación de escritorio en **Java (Swing)** con **login**, **gestión de citas** y diseño visual consistente (tema morado). Optimizada para **Visual Studio Code**.

---

## 📦 Requisitos

- **JDK 17+** (Adoptium/Oracle/Corretto).
- **Visual Studio Code** con la extensión **Extension Pack for Java**.
- (Opcional) Imágenes: `logo.png` (y `logo-info.png` si usas logo alterno para el diálogo de información).

---

## 🗂️ Estructura recomendada

Si **no usas `package`**:

```
📁 SistemaSalud
 ├── Main.java
 ├── NexusPrime.java
 ├── SistemaSaludGUI.java
 ├── UITheme.java
 ├── Paciente.java
 ├── Datos.java
 ├── GestionCita.java
 ├── logo.png
 └── README.md
```

Si usas paquete (ej. `package app;`), coloca todos los `.java` en `src/app/` y la imagen en `src/imagenes/`, y actualiza rutas:
```
📁 src
 ├── 📁 app
 │    ├── Main.java
 │    ├── NexusPrime.java
 │    ├── SistemaSaludGUI.java
 │    ├── UITheme.java
 │    ├── Paciente.java
 │    ├── Datos.java
 │    └── GestionCita.java
 └── 📁 imagenes
      └── logo.png
```

---

## ▶️ Ejecución

1. Abre la **carpeta del proyecto** en VS Code.
2. Abre **`Main.java`**.
3. Clic en **Run ▶️** en la línea del `main`.

> **Main.java** habilita el look & feel *Nimbus* y abre primero el **login** (`NexusPrime`).

---

## 🔑 Login (credenciales de ejemplo)

Edita el método `seedUsers()` en **`NexusPrime.java`** para cambiar/añadir usuarios:

```java
private void seedUsers() {
    users.put("admin", "1234");
    users.put("recepcion", "abcd");
    users.put("medico", "med2025");
    // users.put("tuUsuario", "tuPassword");
}
```

- Si usuario no existe o contraseña es incorrecta, se muestra mensaje de error.
- El checkbox **“Mostrar contraseña”** alterna la visibilidad del campo.

---

## 👩‍⚕️ Gestión de citas

En **`SistemaSaludGUI.java`** puedes:

- **Agendar**, **Cancelar** y **Mostrar** citas.
- La lista se almacena en memoria (ArrayList) solo durante la ejecución.

> ¿Quieres persistencia en archivo `.csv` o base de datos? Se puede añadir sin tocar el diseño.

---

## 🖼️ Imágenes (logos)

- Coloca **`logo.png`** en la **misma carpeta** de los `.java`. Se usa en el login y la GUI.
- Para el diálogo **“Información del programa”** puedes usar una imagen alternativa:
  - Guarda **`logo-info.png`** y llama desde `mostrarInfoPrograma()`:
    ```java
    JLabel logo = new JLabel(loadImage("logo-info.png", 120, 80));
    ```
  - Asegúrate de tener también el método `loadImage(...)` en `SistemaSaludGUI.java` (ver código en el proyecto).

---

## 🎨 Tema y fuentes

Todas las fuentes y colores están centralizados en **`UITheme.java`**.

- Cambia la tipografía (ej. *Lucida Fax*):
  ```java
  public static final Font H1          = new Font("Lucida Fax", Font.BOLD, 28);
  public static final Font H2          = new Font("Lucida Fax", Font.BOLD, 18);
  public static final Font FORM_LABEL  = new Font("Lucida Fax", Font.BOLD, 14);
  public static final Font INPUT       = new Font("Lucida Fax", Font.PLAIN, 15);
  public static final Font BUTTON      = new Font("Lucida Fax", Font.BOLD, 14);
  public static final Font PLACEHOLDER = new Font("Lucida Fax", Font.ITALIC, 13);
  public static final Font SMALL       = new Font("Lucida Fax", Font.PLAIN, 12);
  ```

- El login usa **placeholders** personalizados (sin `JLabel` extra).
- Colores principales: morado (`PURPLE`), gris de texto, etc.

---

## 🪟 Ventana “Información del programa”

En la barra morada hay un botón **“Información del programa”** que abre un **JDialog** modal con:
- Logo, nombre, versión, autor y descripción.
- Botón **Cerrar**.

Puedes editar el texto dentro de `mostrarInfoPrograma()`.

---

## 🏗️ Empaquetar JAR ejecutable (opcional)

**Desde VS Code:**
1. `Ctrl+Shift+P` → **Java: Create Java Project** (si aún no tienes una estructura de proyecto).
2. `Ctrl+Shift+P` → **Java: Export Jar** (selecciona la clase `Main`).

**Con `javac/jar`** (sin build tools), desde la carpeta del proyecto:
```bash
# Compilar
javac *.java

# Ejecutar
java Main
```
Para un JAR simple:
```bash
echo Main-Class: Main > manifest.mf
jar cfm app.jar manifest.mf *.class logo.png
java -jar app.jar
```

> Si usas paquetes, ajusta el `Main-Class` (por ejemplo `app.Main`) y las rutas.

---

## 🧯 Solución de problemas

- **`cannot find symbol: class UITheme`**  
  Asegúrate de que `UITheme.java` está en la misma carpeta (o mismo `package`) que los demás archivos.

- **`NoSuchMethodError: loadLogo`**  
  Verifica que `loadLogo(int,int)` esté **dentro** de cada clase que lo usa (o hazlo `static` en una clase utilitaria y llama `Clase.loadLogo(...)`).

- **Imágenes no cargan**  
  Verifica que `logo.png` exista en la carpeta del proyecto o cambia la ruta (`new ImageIcon("src/imagenes/logo.png")`).

- **Error de paquete**  
  Si usas `package app;`, todos los archivos deben comenzar con la **misma** línea de `package` y residir en `src/app/`.

- **Ventana desbalanceada / área de citas abajo**  
  Usa el bloque de distribución centrada del README (ya aplicado): `areaWrapper` con `GridBagLayout` y tamaño preferido `600x200`.

---

## ✍️ Autor y licencia

- Autor: _Tu Nombre_  
- Licencia: MIT (o la que prefieras)

---

## ✅ Checklist rápido

- [ ] JDK 17+ instalado  
- [ ] VS Code + Extension Pack for Java  
- [ ] Archivos `.java` y `logo.png` en la misma carpeta (o rutas actualizadas)  
- [ ] Ejecutar `Main.java` ▶️  
- [ ] Editar `seedUsers()` con tus credenciales  
- [ ] Cambiar fuente en `UITheme.java` si lo deseas  

¡Listo! Cualquier duda para publicar, exportar JAR, o agregar persistencia, me dices y lo dejamos perfecto. 🚀
