<?php
$servidor="localhost";
$bd="whoclean";
$usuario="whoclean";
$password="eb653d32d";

$JSON=array();
$conexion=mysqli_connect($servidor,$usuario,$password,$bd);
$nombre=$_GET['Nombre'];
$apellido=$_GET['Apellido'];
$correo=$_GET['Correo'];
$telefono=$_GET['Telefono'];
$usuario=$_GET['Usuario'];
$contra=$_GET['Contra'];
$tipoUsu=$_GET['TipoUsu'];
$idnegocio=$_GET['Idnegocio'];

$consulta="INSERT INTO cliente (Nombre,Apellido,Correo,Telefono,Usuario,Contra,TipoUsu,Id_Negocio) VALUES ('{$nombre}','{$apellido}','{$correo}','{$telefono}','{$usuario}','{$contra}','{$tipoUsu}','{$idnegocio}');";
mysqli_set_charset($conexion, "utf8");
mysqli_query($conexion,$consulta);

echo " "+'$tipoUsu';
mysqli_close($conexion);
$json_string = json_encode($JSON);
echo $json_string;

?>