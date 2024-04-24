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
$tipo_TelaSillon=$_GET['Tipo_TelaSillon'];
$tipo_Sillon=$_GET['Tipo_Sillon'];
$numero_Sillon=$_GET['Numero_Sillon'];
$numero_Cojin=$_GET['Numero_Cojin'];
$estado_PedidoSillon=$_GET['Estado_PedidoSillon'];
$id_Cliente=$_GET['Id_Cliente'];
$id_Negocio=$_GET['Id_Negocio'];


$consulta="INSERT INTO pedidosillon (Domicilio,Num_Domicilio,Colonia,Municipio,Fecha_Lavada,Hora_Lavada,Tipo_TelaSillon,Tipo_Sillon,Numero_Sillon,Numero_Cojin,Estado_PedidoSillon,Id_Cliente,Id_Negocio) VALUES ('{$domicilio}','{$num_Domicilio}','{$colonia}','{$municipio}','{$fecha_Lavada}','{$hora_Lavada}','{$tipo_TelaSillon}','{$tipo_Sillon}','{$numero_Sillon}','{$numero_Cojin}','{$estado_PedidoSillon}','{$id_Cliente}','{$id_Negocio}');";
mysqli_set_charset($conexion, "utf8");
mysqli_query($conexion,$consulta);

echo " "+'$domicilio';
mysqli_close($conexion);
$json_string = json_encode($JSON);
echo $json_string;

?>