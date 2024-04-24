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
$tam_Colchon=$_GET['Tam_Colchon'];
$marca_Colchon=$_GET['Marca_Colchon'];
$estado_PedidoCol=$_GET['Estado_PedidoCol'];
$id_Cliente=$_GET['Id_Cliente'];
$id_Negocio=$_GET['Id_Negocio'];


$consulta="INSERT INTO pedidocolchon (Domicilio,Num_Domicilio,Colonia,Municipio,Fecha_Lavada,Hora_Lavada,Tamaño_Colchon,Marca_Colchon,Estado_PedidoCol,Id_Cliente,Id_Negocio) VALUES ('{$domicilio}','{$num_Domicilio}','{$colonia}','{$municipio}','{$fecha_Lavada}','{$hora_Lavada}','{$tam_Colchon}','{$marca_Colchon}','{$estado_PedidoCol}','{$id_Cliente}','{$id_Negocio}');";
mysqli_set_charset($conexion, "utf8");
mysqli_query($conexion,$consulta);

echo " "+'$domicilio';
mysqli_close($conexion);
$json_string = json_encode($JSON);
echo $json_string;

?>