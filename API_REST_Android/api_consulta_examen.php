<?php

    include('../sitio/scripts_php/conexion_bd.php');

    $con = new ConexionBD();
    $conexion = $con->getConexion();
    
    if($_SERVER['REQUEST_METHOD'] == 'POST') {
        $cadena_JSON = file_get_contents('php://input'); //Recibe información a través de HTTP

        if($cadena_JSON == false) {
            echo "No hay cadena JSON";
        } else {
            $datos = json_decode($cadena_JSON, true);
            $nc = $datos['nc'];
            $f = $datos['f'];
            $sql = "SELECT * FROM Alumnos WHERE $f LIKE '$nc%'";
            $res = mysqli_query($conexion, $sql);

            $datos['alumnos'] = array();
            if($res) {
                while($fila = mysqli_fetch_assoc($res)) {
                    $alumno = array();
                    $alumno['nc'] = $fila['Num_Control'];
                    $alumno['n'] = $fila['Nombre'];
                    $alumno['pa'] = $fila['Primer_AP'];
                    $alumno['sa'] = $fila['Segundo_AP'];
                    $alumno['e'] = $fila['Edad'];
                    $alumno['s'] = $fila['Semestre'];
                    $alumno['c'] = $fila['Carrera'];

                    array_push($datos['alumnos'], $alumno);
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