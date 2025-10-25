# Sistema de Sprites - ZombieTec

## 📝 Descripción

La clase `Sprite` proporciona una manera de darle apariencia visual a las defensas y zombies en el juego mediante imágenes. Cada sprite siempre debe tener una imagen asociada.

## 🎨 Características

- **Soporte de imágenes**: Carga imágenes PNG, JPG, GIF, etc.
- **Redimensionamiento automático**: Ajusta las imágenes al tamaño deseado
- **Detección de colisiones**: Método para verificar si un punto está dentro del sprite
- **Fácil integración**: Se integra con las clases `Defensa` y `Zombies`
- **Obligatorio**: Siempre requiere una imagen válida

## 🚀 Uso Básico

### 1. Crear un sprite con imagen

```java
// Crear sprite con imagen
Sprite spriteZombie = new Sprite("recursos/imagenes/zombie.png", 100, 100, 50, 50);

// Asignar a un zombie
zombie.setSprite(spriteZombie);
```

### 2. Dibujar sprites en el tablero

```java
// En el método paintComponent del TableroPanel
@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    // Dibujar cada zombie con sprite
    for (Zombies zombie : listaZombies) {
        if (zombie.getSprite() != null) {
            zombie.getSprite().dibujar(g2d);
        }
    }

    // Dibujar cada defensa con sprite
    for (Defensa defensa : listaDefensas) {
        if (defensa.getSprite() != null) {
            defensa.getSprite().dibujar(g2d);
        }
    }
}
```

### 3. Actualizar posición del sprite

```java
// Cuando una entidad se mueve, actualizar su sprite
sprite.setPosicion(nuevaX, nuevaY);
```

### 4. Cambiar tamaño del sprite

````java
// Ajustar al tamaño de la casilla del tablero
int tamanoCasilla = 20;
sprite.setTamano(tamanoCasilla, tamanoCasilla);
```## 📁 Estructura de carpetas recomendada

````

ZombieTec/
├── src/
│ └── main/
│ ├── java/
│ │ ├── Sprite.java
│ │ ├── Defensas/
│ │ └── Zombies/
│ └── resources/
│ └── imagenes/
│ ├── zombies/
│ │ ├── zombie_basico.png
│ │ ├── zombie_rapido.png
│ │ └── zombie_tanque.png
│ └── defensas/
│ ├── defensa_contacto.png
│ ├── defensa_impacto.png
│ └── defensa_mediano.png

````

## 🎯 Ejemplo completo de integración

```java
// En el constructor de una defensa específica
public class Contacto extends Defensa {

    public Contacto(int vida, int damage, int atackSpeed, int coste, int nivelAparicion) {
        super(vida, damage, atackSpeed, coste, nivelAparicion, 1);

        // Crear sprite con imagen (OBLIGATORIO)
        this.sprite = new Sprite(
            "src/main/resources/imagenes/defensas/defensa_contacto.png",
            0, 0, 30, 30
        );
    }
}
````

## 🛠️ Métodos disponibles

### Construcción

- `Sprite(String rutaImagen, int x, int y, int ancho, int alto)` - Constructor con imagen (obligatorio)

### Dibujo

- `void dibujar(Graphics2D g2d)` - Dibuja el sprite

### Posición y tamaño

- `void setPosicion(int x, int y)` - Cambia posición
- `void setTamano(int ancho, int alto)` - Cambia tamaño y redimensiona la imagen
- `int getX()`, `int getY()` - Obtiene posición
- `int getAncho()`, `int getAlto()` - Obtiene dimensiones

### Apariencia

- `void cambiarImagen(String rutaImagen)` - Cambia la imagen del sprite

### Utilidades

- `boolean contienePunto(int x, int y)` - Detección de clic/colisión

## ⚠️ Notas importantes

1. **Imágenes obligatorias**: Cada sprite DEBE tener una imagen válida
2. **Rutas de imágenes**: Usa rutas relativas desde el directorio del proyecto
3. **Manejo de errores**: Si no se puede cargar una imagen, se lanzará una RuntimeException
4. **Performance**: Las imágenes se redimensionan al cargar y al cambiar tamaño
5. **Actualización**: Recuerda actualizar la posición del sprite cuando la entidad se mueva

## 🔄 Próximos pasos

1. Crear carpeta `resources/imagenes` en tu proyecto
2. Añadir imágenes para zombies y defensas
3. Actualizar los constructores de tus clases para crear sprites
4. Modificar `TableroPanel` para dibujar sprites sobre las casillas
5. Implementar sistema de arrastrar y soltar con sprites
