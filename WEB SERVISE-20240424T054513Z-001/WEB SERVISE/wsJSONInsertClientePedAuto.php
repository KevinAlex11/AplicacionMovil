<?php
$servidor="localhost";
$bd="whoclean";
$usuario="whoclean";
$password="eb653d32d";

$JSON=array();
$conexion=mysqli_connect($servidor,$usuario,$password,$bd);
$domicilio=$_GET['Domicilio'];
$num_Domicilio=$_GET['Num_Domicilio'];
$colonia=$_GET['Colonia'];
$municipio=$_GET['Municipio'];
$fecha_Lavada=$_GET['Fecha_Lavada'];
$hora_Lavada=$_GET['Hora_Lavada'];
$tipo_Auto=$_GET['Tipo_Auto'];
$marca_Auto=$_GET['Marca_Auto'];
$modelo_Auto=$_GET['Modelo_Auto'];
$tipo_TelaAuto=$_GET['Tipo_TelaAuto'];
$estado_PedidoAuto=$_GET['Estado_PedidoAuto'];
$id_Cliente=$_GET['Id_Cliente'];
$id_Negocio=$_GET['Id_Negocio'];


$consulta="INSERT INTO pedidoauto (Domicilio,Num_Domicilio,Colonia,Municipio,Fecha_Lavada,Hora_Lavada,Tipo_Auto,Marca_Auto,Modelo_Auto,Tipo_TelaAuto,Estado_PedidoAuto,Id_Cliente,Id_Negocio) VALUES ('{$domicilio}','{$num_Domicilio}','{$colonia}','{$municipio}','{$fecha_Lavada}','{$hora_Lavada}','{$tipo_Auto}','{$marca_Auto}','{$modelo_Auto}','{$tipo_TelaAuto}','{$estado_PedidoAuto}','{$id_Cliente}','{$id_Negocio}');";
mysqli_set_charset($conexion, "utf8");
mysqli_query($conexion,$consulta);

echo " "+'$domicilio';
mysqli_close($conexion);
$json_string = json_encode($JSON);
echo $json_string;

?>