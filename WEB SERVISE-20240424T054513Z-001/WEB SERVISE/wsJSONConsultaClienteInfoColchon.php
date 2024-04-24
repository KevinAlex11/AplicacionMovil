<?php
$servidor="localhost";
$bd="whoclean";
$usuario="whoclean";
$password="eb653d32d";

$JSON=array();
$conexion=mysqli_connect($servidor,$usuario,$password,$bd);
$Id_PedidoColchon=$_GET['Id_PedidoColchon'];
$consulta="UPDATE pedidocolchon SET Estado_PedidoCol='r' WHERE Id_PedidoColchon='{$Id_PedidoColchon}';";
mysqli_set_charset($conexion, "utf8");
$resultado=mysqli_query($conexion,$consulta);


mysqli_close($conexion);
$json_string = json_encode($JSON);
echo $json_string;
	
?>