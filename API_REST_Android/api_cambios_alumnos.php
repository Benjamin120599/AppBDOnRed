<?php

    include('../sitio/scripts_php/conexion_bd.php');

    $con = new ConexionBD();
    $conexion = $con->getConexion();

    //var_dump($conexion);

    if($_SERVER['REQUEST_METHOD'] == 'POST') {
        $cadena_JSON = file_get_contents('php://input'); //Recibe información a través de HTTP

        if($cadena_JSON == false) {
            echo "No hay cadena JSON";
        } else {
            $datos = json_decode($cadena_JSON, true);

            $nc = $datos['nc'];
            $n = $datos['n'];
            $pa = $datos['pa'];
            $sa = $datos['sa'];
            $e = $datos['e'];
            $s = $datos['s'];
            $c = $datos['c'];

            $sql = "UPDATE Alumnos SET Num_Control='$nc', Nombre='$n', Primer_Ap='$pa', Segundo_Ap='$sa', Edad=$e, Semestre=$s, Carrera='$c' WHERE Num_Control='$nc';";
            $res = mysqli_query($conexion, $sql);
            $respuesta = array();

            if($res) {
                //Todo bien
                $respuesta['exito'] = true;
                $respuesta['mensaje'] = "Modificacion correcta";
                $cad = json_encode($respuesta);
                var_dump($cad);
            } else {
                //Todo mal
                $respuesta['exito'] = false;
                $respuesta['mensaje'] = "Error en la modificion";
                $cad = json_encode($respuesta);
                var_dump($cad);
            }
        }        

    } else {
        echo "No hay peticion HTTP";
    }

?>