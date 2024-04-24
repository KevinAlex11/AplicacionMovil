<?php
$servidor="localhost";
$bd="whoclean";
$usuario="whoclean";
$password="eb653d32d";

$JSON=array();
$id_Cliente=$_GET['Id_Cliente'];
$id_Negocio=$_GET['Id_Negocio'];
$conexion=mysqli_connect($servidor,$usuario,$password,$bd);

$consulta="SELECT * FROM pedidoauto WHERE Id_Cliente='{$id_Cliente}' AND Id_Negocio='{$id_Negocio}' AND Estado_PedidoAuto =r;";
mysqli_set_charset($conexion, "utf8");
$resultado=mysqli_query($conexion,$consulta);

while($registro=mysqli_fetch_array($resultado)) {
	$resultar["Id_PedidoAuto"]=$registro['Id_PedidoAuto'];
	$resultar["Domicilio"]=$registro['Domicilio'];
	$resultar["Num_Domicilio"]=$registro['Num_Domicilio'];
	$resultar["Colonia"]=$registro['Colonia'];
	$resultar["Municipio"]=$registro['Municipio'];
	$resultar["Fecha_Lavada"]=$registro['Fecha_Lavada'];
	$resultar["Hora_Lavada"]=$registro['Hora_Lavada'];
	$resultar["Tipo_Auto"]=$registro['Tipo_Auto'];
	$resultar["Marca_Auto"]=$registro['Marca_Auto'];
	$resultar["Modelo_Auto"]=$registro['Modelo_Auto'];
	$resultar["Tipo_TelaAuto"]=$registro['Tipo_TelaAuto'];
	$resultar["Id_Cliente"]=$registro['Id_Cliente'];
	$resultar["Id_Negocio"]=$registro['Id_Negocio'];
	$resultar["Estado_PedidoAuto"]=$registro['Estado_PedidoAuto'];
	$JSON['pedidoAuto'][]=$registro;
}

mysqli_close($conexion);
$json_string = json_encode($JSON);
echo $json_string;
	
?>