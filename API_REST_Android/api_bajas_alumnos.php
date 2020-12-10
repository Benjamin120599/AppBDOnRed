<?php

    include('../sitio/scripts_php/conexion_bd.php');

    $con = new ConexionBD();
    $conexion = $con->getConexion();

    if($_SERVER['REQUEST_METHOD'] == 'POST') {
        $cadena_JSON = file_get_contents('php://input'); //Recibe información a través de HTTP

        if($cadena_JSON === false) {
            echo "No hay cadena JSON";
        } else {
            $datos = json_decode($cadena_JSON, true);
            
            $nc = $datos['nc'];
            $sql = "DELETE FROM Alumnos WHERE Num_Control='$nc'";
            $res = mysqli_query($conexion, $sql);

            $respuesta; //= array();

            if($res) {
                $respuesta['exito'] = true;
                //$respuesta['mensaje'] = "Eliminacion correcta";
                $cad = json_encode($respuesta);
                echo $cad;
            } else {
                $respuesta['exito'] = false;
                //$respuesta['mensaje'] = "Error en la eliminacion";
                $cad = json_encode($respuesta);
                echo $cad;
            }
        }        

    } else {
        echo "No hay peticion HTTP";
    }

?>