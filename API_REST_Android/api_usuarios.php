<?php

include('../sitio/scripts_php/conexion_bd_usuarios.php');

$con = new ConexionBD();
$conexion = $con->getConexion();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $cadena_JSON = file_get_contents('php://input'); //Recibe información a través de HTTP

    if ($cadena_JSON === false) {
        echo "No hay cadena JSON";
    } else {

        $filtro = json_decode($cadena_JSON, true);

        $u = $filtro['nc'];
        $p = $filtro['f'];

        $uc = sha1($u);
        $pc = sha1($p);

        $sql = "SELECT * FROM Usuarios WHERE Usuario = '$uc' AND Password = '$pc'";

        $res = mysqli_query($conexion, $sql);

        if(mysqli_num_rows($res)==1) {
            $respuesta['exito'] = true;
            $cad = json_encode($respuesta);
            echo $cad;
        } else {
            $respuesta['exito'] = false;
            $cad = json_encode($respuesta);
            echo $cad;
        }
    }
} else {
    echo "No hay peticion HTTP";
}
