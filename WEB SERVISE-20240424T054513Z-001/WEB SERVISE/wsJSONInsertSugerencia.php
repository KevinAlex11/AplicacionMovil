<?php
$servidor="localhost";
$bd="whoclean";
$usuario="whoclean";
$password="eb653d32d";

$JSON=array();
$conexion=mysqli_connect($servidor,$usuario,$password,$bd);
$asunto=$_GET['asunto'];
$mensaje=$_GET['mensaje'];
$id_Cliente=$_GET['idcliente'];
$id_Negocio=$_GET['idnegocio'];

$consulta="INSERT INTO sugerencia (Asunto,Mensaje,Id_Cliente,Id_Negocio) VALUES ('{$asunto}','{$mensaje}','{$id_Cliente}','{$id_Negocio}');";
mysqli_set_charset($conexion, "utf8");
mysqli_query($conexion,$consulta);

mysqli_close($conexion);
$json_string = json_encode($JSON);
echo $json_string;

?>