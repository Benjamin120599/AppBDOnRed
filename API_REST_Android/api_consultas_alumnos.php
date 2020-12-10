<?php

    include('../sitio/scripts_php/conexion_bd.php');

    $con = new ConexionBD();
    $conexion = $con->getConexion();

    //var_dump($conexion);

    if($_SERVER['REQUEST_METHOD'] == 'POST') {
        $cadena_JSON = file_get_contents('php://input'); //Recibe información a través de HTTP

        //if($cadena_JSON == false) {
        //    echo "No hay cadena JSON";
        //} else {
            $filtro = json_decode($cadena_JSON, true);

            $sql = "SELECT * FROM Alumnos";

            $res = mysqli_query($conexion, $sql);
            
            $datos['alumnos'] = array();
            if($res) {
                //Todo bien
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
                //Todo mal
                $respuesta['exito'] = false;
                $respuesta['mensaje'] = "Error en la insercion";
                $cad = json_encode($respuesta);
                echo $cad;
            }

    } else {
        echo "No hay peticion HTTP";
    }

?>