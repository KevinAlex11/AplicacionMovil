<?php
$servidor="localhost";
$bd="whoclean";
$usuario="whoclean";
$password="eb653d32d";

$JSON=array();
$conexion=mysqli_connect($servidor,$usuario,$password,$bd);
$Id_PedidoSillon=$_GET['Id_PedidoSillon'];
$consulta="UPDATE pedidosillon SET Estado_PedidoSillon='r' WHERE Id_PedidoSillon='{$Id_PedidoSillon}';";
mysqli_set_charset($conexion, "utf8");
$resultado=mysqli_query($conexion,$consulta);


mysqli_close($conexion);
$json_string = json_encode($JSON);
echo $json_string;
	
?>