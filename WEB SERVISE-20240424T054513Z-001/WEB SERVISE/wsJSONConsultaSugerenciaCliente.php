<?php
$servidor="localhost";
$bd="whoclean";
$usuario="whoclean";
$password="eb653d32d";

$JSON=array();
$conexion=mysqli_connect($servidor,$usuario,$password,$bd);
$consulta="SELECT Nombre, Id_Sugerencia, Asunto, Mensaje, sugerencia.Id_Cliente,sugerencia.Id_Negocio FROM cliente, sugerencia WHERE cliente.Id_Cliente=sugerencia.Id_Cliente;";
mysqli_set_charset($conexion, "utf8");
$resultado=mysqli_query($conexion,$consulta);

while($registro=mysqli_fetch_array($resultado)) {
	$resultar["Id_Sugerencia"]=$registro['Id_Sugerencia'];
	$resultar["Nombre"]=$registro['Nombre'];
	$resultar["Asunto"]=$registro['Asunto'];
	$resultar["Mensaje"]=$registro['Mensaje'];
	$resultar["Id_Cliente"]=$registro['Id_Cliente'];
	$resultar["Id_Negocio"]=$registro['Id_Negocio'];
	$JSON['sugerencia'][]=$registro;
}

mysqli_close($conexion);
$json_string = json_encode($JSON);

echo $json_string;

?>