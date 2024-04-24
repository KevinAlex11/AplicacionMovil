<?php
$servidor="localhost";
$bd="whoclean";
$usuario="whoclean";
$password="eb653d32d";

$JSON=array();
$conexion=mysqli_connect($servidor,$usuario,$password,$bd);

$contran=$_GET['contra'];
$idcliente=$_GET['idcliente'];


$consulta="UPDATE cliente SET Contra='{$contran}' WHERE Id_Cliente='{$idcliente}';";
mysqli_set_charset($conexion, "utf8");
mysqli_query($conexion,$consulta);


mysqli_close($conexion);
$json_string = json_encode($JSON);
echo $json_string;

?>