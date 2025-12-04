# 📦 Instrucciones de Compilación y Ejecución

## Requisitos Previos

✅ **Java 17+** (tienes Java 25 instalado)
✅ **Maven 3.6+** (necesita ser instalado)
✅ **MySQL** (con base de datos `cbd`)

---

## Opción 1: Compilar y Ejecutar con Maven (Recomendado)

### Paso 1: Instalar Maven (Windows)

```powershell
# Descargar Maven
$url = "https://archive.apache.org/dist/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip"
$output = "C:\apache-maven-3.9.6.zip"
Invoke-WebRequest -Uri $url -OutFile $output

# Descomprimir
Expand-Archive -Path $output -DestinationPath "C:\"

# Añadir a PATH (opcional, ejecutar como Admin)
[Environment]::SetEnvironmentVariable("Path", $env:Path + ";C:\apache-maven-3.9.6\bin", "User")
```

### Paso 2: Compilar el Proyecto

```powershell
cd "C:\Users\ctero\OneDrive\C_drive\Documentos\GitHub\CampingParty\ProyectoCampingParty"

# Compilación limpia
mvn clean compile

# Si necesitas empaquetar como JAR
mvn clean package

# Para ignorar errores menores (no recomendado)
mvn clean compile -DskipTests
```

### Paso 3: Ejecutar la Aplicación

```powershell
# Opción A: Ejecutar directamente desde Maven
mvn exec:java

# Opción B: Ejecutar el JAR generado
java -cp target/ProyectoCampingParty-1.0-SNAPSHOT.jar com.campingparty.main.ProyectoCampingParty
```

---

## Opción 2: Compilar con IDE (NetBeans/IntelliJ IDEA)

### En NetBeans:
1. `Archivo` → `Abrir Proyecto`
2. Selecciona `ProyectoCampingParty`
3. Click derecho → `Compilar`
4. Click derecho → `Ejecutar Proyecto`

### En IntelliJ IDEA:
1. `File` → `Open` → selecciona la carpeta
2. `Build` → `Build Project` (o Ctrl+F9)
3. `Run` → `Run 'ProyectoCampingParty'`

---

## Verificación de Requisitos

### ✅ Verificar Java

```powershell
java -version
# Debe mostrar Java 17 o superior
```

### ✅ Verificar Maven

```powershell
mvn --version
# Debe mostrar Maven 3.6.0 o superior
```

### ✅ Verificar MySQL

```powershell
# Conectar a MySQL
mysql -u root -p

# En la consola MySQL:
SHOW DATABASES;
USE cbd;
SHOW TABLES;
```

---

## Estructura de Directorios Importante

```
ProyectoCampingParty/
├── src/main/java/com/campingparty/
│   ├── modelo/          ✅ Clases de datos
│   ├── controlador/     ✅ Controladores
│   ├── vista/           ✅ Interfaces gráficas
│   └── main/            ✅ ProyectoCampingParty.java (entry point)
├── pom.xml              ✅ Configuración Maven
└── target/              ← Se crea después de compilar
    ├── classes/         ← Archivos .class compilados
    └── *.jar            ← JAR ejecutable
```

---

## Solución de Problemas

### ❌ "mvn: command not found"

**Solución**: Maven no está en PATH. Instala según Paso 1 o usa ruta completa:
```powershell
C:\apache-maven-3.9.6\bin\mvn clean compile
```

### ❌ "Error: JAVA_HOME is not set"

**Solución**: 
```powershell
$env:JAVA_HOME = "C:\Program Files\Java\jdk-25"
mvn clean compile
```

### ❌ "Cannot find symbol: class FlatLightLaf"

**Solución**: Las dependencias Maven no están descargadas. Ejecuta:
```powershell
mvn clean dependency:resolve
mvn compile
```

### ❌ "Connection refused" (Base de datos)

**Solución**: Verifica que MySQL está corriendo:
```powershell
# En Windows, verificar servicios
Get-Service MySQL* | Start-Service

# O iniciar MySQL manualmente
"C:\Program Files\MySQL\MySQL Server 8.0\bin\mysqld.exe"
```

---

## Comando Rápido: Compilar y Ejecutar

```powershell
# Todo en uno
cd "C:\Users\ctero\OneDrive\C_drive\Documentos\GitHub\CampingParty\ProyectoCampingParty" ; `
mvn clean compile exec:java -Dexec.mainClass="com.campingparty.main.ProyectoCampingParty"
```

---

## Información de Configuración

- **Encoding**: UTF-8
- **Compilación**: Java 17 (maven.compiler.release)
- **Main Class**: `com.campingparty.main.ProyectoCampingParty`
- **Base de Datos**: `jdbc:mysql://localhost/cbd?serverTimezone=UTC`

---

## Notas Finales

⚠️ **Importante**: La aplicación espera que:
1. MySQL esté corriendo en `localhost:3306`
2. Base de datos `cbd` exista
3. Usuario `root` con contraseña `root`

Si tienes diferente configuración, edita `com.campingparty.modelo.DAO.java`:
```java
String url = "jdbc:mysql://localhost/cbd?serverTimezone=UTC";
String usuario = "root";
String password = "root";
```

---

*Última actualización: 2024*
