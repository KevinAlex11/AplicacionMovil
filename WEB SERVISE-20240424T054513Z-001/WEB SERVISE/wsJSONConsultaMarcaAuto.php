<?php
$servidor="localhost";
$bd="whoclean";
$usuario="whoclean";
$password="eb653d32d";

$JSON=array();

$conexion=mysqli_connect($servidor,$usuario,$password,$bd);

$consulta="SELECT * FROM marcaauto;";
mysqli_set_charset($conexion, "utf8");
$resultado=mysqli_query($conexion,$consulta);

while($registro=mysqli_fetch_array($resultado)) {
	$resultar["Nombre"]=$registro['Nombre'];
	$JSON['marca'][]=$registro;
}


mysqli_close($conexion);
$json_string = json_encode($JSON);
echo $json_string;
	
?>