<?php
$servidor="localhost";
$bd="whoclean";
$usuario="whoclean";
$password="eb653d32d";

$JSON=array();

$conexion=mysqli_connect($servidor,$usuario,$password,$bd);

$consulta="SELECT * FROM tipotelasillon;";
mysqli_set_charset($conexion, "utf8");
$resultado=mysqli_query($conexion,$consulta);

while($registro=mysqli_fetch_array($resultado)) {
	$resultar["Id_TipoTelaSillon"]=$registro['Id_TipoTelaSillon'];
	$resultar["TipoTelaSillon"]=$registro['TipoTelaSillon'];
	$resultar["DescTipoTelaSillon"]=$registro['DescTipoTelaSillon'];
	$resultar["ImagenTipoTela"]=$registro['ImagenTipoTela'];
	$JSON['tipoST'][]=$registro;
}


mysqli_close($conexion);
$json_string = json_encode($JSON);
echo $json_string;
	
?>