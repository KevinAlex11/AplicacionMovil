<?php
$servidor="localhost";
$bd="whoclean";
$usuario="whoclean";
$password="eb653d32d";

$JSON=array();

$conexion=mysqli_connect($servidor,$usuario,$password,$bd);

$consulta="SELECT * FROM tipoAuto;";
mysqli_set_charset($conexion, "utf8");
$resultado=mysqli_query($conexion,$consulta);

while($registro=mysqli_fetch_array($resultado)) {
	$resultar["Id_Tipoauto"]=$registro['Id_Tipoauto'];
	$resultar["Tipo"]=$registro['Tipo'];
	$resultar["DescTipo"]=$registro['DescTipo'];
	$resultar["Imagen"]=$registro['Imagen'];
	$JSON['autos'][]=$registro;
}


mysqli_close($conexion);
$json_string = json_encode($JSON);
echo $json_string;
	
?>