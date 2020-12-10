<?php

    include('../sitio/scripts_php/conexion_bd_usuarios.php');

    $con = new ConexionBD();
    $conexion = $con->getConexion();
    
    if($_SERVER['REQUEST_METHOD'] == 'POST') {
        $cadena_JSON = file_get_contents('php://input'); //Recibe información a través de HTTP

        if($cadena_JSON == false) {
            echo "No hay cadena JSON";
        } else {
            $datos = json_decode($cadena_JSON, true);
            $u = $datos['nc'];
            $f = $datos['f'];
            
            $sql = "SELECT * FROM Usuarios WHERE $f='$nc'";
            $res = mysqli_query($conexion, $sql);

            $datos['usuarios'] = array();
            if($res) {
                while($fila = mysqli_fetch_assoc($res)) {
                    $usuario = array();
                    $usuario['u'] = $fila['Usuario'];
                    $usuario['p'] = $fila['Password'];
                    array_push($datos['usuarios'], $usuario);
                }

                $cad = json_encode($datos);
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

?>