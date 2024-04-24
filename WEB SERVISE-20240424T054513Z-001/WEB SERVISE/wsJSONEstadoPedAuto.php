<?php
$servidor="localhost";
$bd="whoclean";
$usuario="whoclean";
$password="eb653d32d";

$JSON=array();
$conexion=mysqli_connect($servidor,$usuario,$password,$bd);
$idPedidoAuto=$_GET['IdPedidoAuto'];
$consulta="UPDATE pedidoauto SET Estado_PedidoAuto='r' WHERE Id_PedidoAuto='{$idPedidoAuto}';";
mysqli_set_charset($conexion, "utf8");
mysqli_query($conexion,$consulta);

mysqli_close($conexion);
$json_string = json_encode($JSON);
echo $json_string;
	
?>