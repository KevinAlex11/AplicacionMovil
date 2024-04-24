<?php
$servidor="localhost";
$bd="whoclean";
$usuario="whoclean";
$password="eb653d32d";

$JSON=array();
$conexion=mysqli_connect($servidor,$usuario,$password,$bd);

$consulta="SELECT Precio_Lav_Auto, Precio_Lav_Col, Precio_Lav_Sillon, Precio_Cojin FROM informacionnegocio;";
mysqli_set_charset($conexion, "utf8");
mysqli_query($conexion,$consulta);

/*if ($registro=mysqli_fetch_array($resultado)) {
	$resultar["Precio_Lav_Auto"]=$registro['Precio_Lav_Auto'];
	$resultar["Precio_Lav_Col"]=$registro['Precio_Lav_Col'];
	$resultar["Precio_Lav_Sillon"]=$registro['Precio_Lav_Sillon'];
	$resultar["Precio_Cojin"]=$registro['Precio_Cojin'];
	$JSON['precio'][]=$registro;
}*/



mysqli_close($conexion);
$json_string = json_encode($JSON);
echo $json_string;

?>