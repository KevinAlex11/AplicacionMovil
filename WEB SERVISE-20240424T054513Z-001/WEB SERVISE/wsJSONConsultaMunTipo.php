<?php
$servidor="localhost";
$bd="whoclean";
$usuario="whoclean";
$password="eb653d32d";

$JSON=array();

$conexion=mysqli_connect($servidor,$usuario,$password,$bd);

$consulta="SELECT NombreMunicipio FROM municipios;";
mysqli_set_charset($conexion, "utf8");
$resultado=mysqli_query($conexion,$consulta);

while($registro=mysqli_fetch_array($resultado)) {
	$resultar["NombreMunicipio"]=$registro['NombreMunicipio'];
	$JSON['municipio'][]=$registro;
}

$consulta1="SELECT Nombre FROM marcaauto;";
mysqli_set_charset($conexion1, "utf8");
$resultado1=mysqli_query($conexion,$consulta1);

while($registro1=mysqli_fetch_array($resultado1)) {
	$resultar["Nombre"]=$registro1['Nombre'];
	$JSON['marca'][]=$registro1;
}

mysqli_close($conexion);
$json_string = json_encode($JSON);
echo $json_string;
	
?>