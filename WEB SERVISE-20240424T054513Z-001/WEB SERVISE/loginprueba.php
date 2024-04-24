<?php
$servidor="localhost";
$bd="wstnl1";
$usuario="wstnl1";
$password="67537bcd0";

$JSON=array();
$conexion=mysqli_connect($servidor,$usuario,$password,$bd);
$Usuario=$_POST['Usuario'];
$Contra=$_POST['Contra'];


$sentencia=$conexion->prepare("SELECT * FROM cliente WHERE Usuario=? AND Contra=?");
$sentencia->bind_param('ss',$Usuario,$Usuario);
$sentencia->execute();

$resultado = $sentencia->get_result();
if ($fila = $resultado->fetch_assoc()) {
         echo json_encode($fila,JSON_UNESCAPED_UNICODE);     
}
$sentencia->close();
$conexion->close();
?>