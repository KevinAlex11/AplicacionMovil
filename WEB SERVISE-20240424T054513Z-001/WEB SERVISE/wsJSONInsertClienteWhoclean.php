<?php
$servidor="localhost";
$bd="whoclean";
$usuario="whoclean";
$password="eb653d32d";

$JSON=array();
$Usuario=$_GET['usuario'];
$Contra=$_GET['contra'];
$conexion=mysqli_connect($servidor,$usuario,$password,$bd);

$consulta="SELECT * FROM cliente WHERE Usuario='{$Usuario}' AND Contra='{$Contra}';";
mysqli_set_charset($conexion, "utf8");
$resultado=mysqli_query($conexion,$consulta);

while ($registro=mysqli_fetch_array($resultado)) {
	$resultar["Id_cliente"]=$registro['Id_Cliente'];
	$resultar["Nombre"]=$registro['Nombre'];
	$resultar["Apellido"]=$registro['Apellido'];
	$resultar["Correo"]=$registro['Correo'];
	$resultar["Telefono"]=$registro['Telefono'];
	$resultar["Usuario"]=$registro['Usuario'];
	$resultar["Contra"]=$registro['Contra'];
	$resultar["TipoUsu"]=$registro['TipoUsu'];
	$resultar["Id_Negocio"]=$registro['Id_Negocio'];
	$JSON['cliente'][]=$registro;
}


mysqli_close($conexion);
$json_string = json_encode($JSON);
echo $json_string;
	
?>