<?php
// Establecer variables de conexi�n a la base de datos
$host = "localhost";
$dbname = "escuela";
$username = "root";
$password = "HoruzDataB4";

// Establecer credenciales para la autenticaci�n b�sica
$auth_username = "admin";
$auth_password = "admin123";

// Obtener las credenciales de autenticaci�n del encabezado HTTP
if (!isset($_SERVER['PHP_AUTH_USER']) || !isset($_SERVER['PHP_AUTH_PW'])
    || $_SERVER['PHP_AUTH_USER'] != $auth_username || $_SERVER['PHP_AUTH_PW'] != $auth_password) {
    header('HTTP/1.1 401 Unauthorized');
    header('WWW-Authenticate: Basic realm="Acceso restringido"');
    exit;
}

// Conectar a la base de datos
try {
    $pdo = new PDO("mysql:host=$host;dbname=$dbname", $username, $password);
} catch (PDOException $e) {
    die("Error de conexi�n: " . $e->getMessage());
}

// Establecer el encabezado de respuesta a JSON
header('Content-Type: application/json');

// Comprobar el m�todo HTTP utilizado
$method = $_SERVER['REQUEST_METHOD'];
switch ($method) {
    case 'GET':
        // Obtener un alumno espec�fico o todos los alumnos
        if (isset($_GET['id'])) {
            // Obtener un alumno espec�fico
            $stmt = $pdo->prepare("SELECT * FROM alumnos WHERE id = ? ");
            $stmt->execute([$_GET['id']]);
            $alumno = $stmt->fetch(PDO::FETCH_ASSOC);
            echo json_encode($alumno);
        } else {
            // Obtener todos los alumnos
            $stmt = $pdo->query("SELECT * FROM alumnos order by id desc");
            $alumnos = $stmt->fetchAll(PDO::FETCH_ASSOC);
            echo json_encode($alumnos);
        }
        break;
    case 'POST':
        // Crear un nuevo alumno
        $data = json_decode(file_get_contents('php://input'), true);
        $stmt = $pdo->prepare("INSERT INTO alumnos (nombre, apellido, edad) VALUES (?, ?, ?)");
        $stmt->execute([$data['nombre'], $data['apellido'], $data['edad']]);
        $alumno_id = $pdo->lastInsertId();
        $alumno = [
            'id' => $alumno_id,
            'nombre' => $data['nombre'],
            'apellido' => $data['apellido'],
            'edad' => $data['edad']
        ];
        echo json_encode($alumno);
        break;
    case 'PUT':
         $data = json_decode(file_get_contents('php://input'), true);
         if ($data['edad']==-987) {
            $stmt = $pdo->prepare("DELETE FROM alumnos WHERE id = ?");
            $stmt->execute([$data['id']]);
            echo json_encode(['mensaje' => 'El alumno ha sido eliminado correctamente.']);          
         } else {
           // Actualizar un alumno existente              
           $stmt = $pdo->prepare("UPDATE alumnos SET nombre = ?, apellido = ?, edad = ? WHERE id = ?");
           $stmt->execute([$data['nombre'], $data['apellido'], $data['edad'], $data['id']]);
           $alumno = [
            'id' => $data['id'],
            'nombre' => $data['nombre'],
            'apellido' => $data['apellido'],
            'edad' => $data['edad']
            ];
           echo json_encode($alumno);    
        }     
        break;
    case 'DELETE':
       // Eliminar un alumno existente
       if (isset($_GET['id'])) {
          $stmt = $pdo->prepare("DELETE FROM alumnos WHERE id = ?");
          $stmt->execute([$_GET['id']]);
          echo json_encode(['mensaje' => 'El alumno ha sido eliminado correctamente.']);           
       }else {
          // Error: no se ha proporcionado un ID de alumno para actualizar
          header('HTTP/1.1 400 Bad Request');
          echo json_encode(['error' => 'No se proporcionado un ID de alumno para actualizar']);
        }
        break;
    default:
        // M�todo HTTP no v�lido
        header('HTTP/1.1 405 Method Not Allowed');
        echo json_encode(['error' => 'M�todo HTTP no v�lido']);
        break;
  }

//Cerrar la conexi�n con la base de datos
$pdo = null;
?>