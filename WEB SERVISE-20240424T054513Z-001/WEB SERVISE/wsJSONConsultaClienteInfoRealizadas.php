<?php
$servidor="localhost";
$bd="whoclean";
$usuario="whoclean";
$password="eb653d32d";

$JSON=array();
$conexion=mysqli_connect($servidor,$usuario,$password,$bd);

$consulta="SELECT * FROM pedidoauto WHERE Estado_PedidoAuto ='r';";
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



$consulta1="SELECT * FROM pedidocolchon WHERE Estado_PedidoCol ='r';";
mysqli_set_charset($conexion, "utf8");
$resultado1=mysqli_query($conexion,$consulta1);

while($registro1=mysqli_fetch_array($resultado1)) {
	$resultar["Id_PedidoColchon"]=$registro1['Id_PedidoColchon'];
	$resultar["Domicilio"]=$registro1['Domicilio'];
	$resultar["Num_Domicilio"]=$registro1['Num_Domicilio'];
	$resultar["Colonia"]=$registro1['Colonia'];
	$resultar["Municipio"]=$registro1['Municipio'];
	$resultar["Fecha_Lavada"]=$registro1['Fecha_Lavada'];
	$resultar["Hora_Lavada"]=$registro1['Hora_Lavada'];
	$resultar["Tamaño_Colchon"]=$registro1['Tamaño_Colchon'];
	$resultar["Marca_Colchon"]=$registro1['Marca_Colchon'];
	$resultar["Id_Cliente"]=$registro1['Id_Cliente'];
	$resultar["Id_Negocio"]=$registro1['Id_Negocio'];
	$resultar["Estado_PedidoCol"]=$registro1['Estado_PedidoCol'];
	$JSON['pedidoCol'][]=$registro1;
}
$consulta2="SELECT * FROM pedidosillon WHERE Estado_PedidoSillon ='r';";
mysqli_set_charset($conexion, "utf8");
$resultado2=mysqli_query($conexion,$consulta2);

while($registro2=mysqli_fetch_array($resultado2)) {
	$resultar["Id_PedidoSillon"]=$registro2['Id_PedidoSillon'];
	$resultar["Domicilio"]=$registro2['Domicilio'];
	$resultar["Num_Domicilio"]=$registro2['Num_Domicilio'];
	$resultar["Colonia"]=$registro2['Colonia'];
	$resultar["Municipio"]=$registro2['Municipio'];
	$resultar["Fecha_Lavada"]=$registro2['Fecha_Lavada'];
	$resultar["Hora_Lavada"]=$registro2['Hora_Lavada'];
	$resultar["Tipo_Sillon"]=$registro2['Tipo_Sillon'];
	$resultar["Numero_Sillon"]=$registro2['Numero_Sillon'];
	$resultar["Numero_Cojin"]=$registro2['Numero_Cojin'];
	$resultar["Id_Cliente"]=$registro2['Id_Cliente'];
	$resultar["Id_Negocio"]=$registro2['Id_Negocio'];
	$resultar["Estado_PedidoSillon"]=$registro2['Estado_PedidoSillon'];
	$JSON['pedidoSillon'][]=$registro2;
}

	

mysqli_close($conexion);
$json_string = json_encode($JSON);

echo $json_string;

?>