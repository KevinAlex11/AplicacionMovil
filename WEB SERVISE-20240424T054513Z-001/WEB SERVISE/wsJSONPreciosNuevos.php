<?php
$servidor="localhost";
$bd="whoclean";
$usuario="whoclean";
$password="eb653d32d";

$JSON=array();
$conexion=mysqli_connect($servidor,$usuario,$password,$bd);

$precioC=$_GET['precioC'];
$precioA=$_GET['precioA'];
$precioS=$_GET['precioS'];
$precioCoj=$_GET['precioCoj'];
$cant=$_GET['num'];

if($cant=='1'){
$consulta="UPDATE informacionnegocio SET Precio_Lav_Auto='{$precioA}' WHERE Id_Negocio='1';";
}
if($cant=='2'){
$consulta="UPDATE informacionnegocio SET  Precio_Lav_Col='{$precioC}' WHERE Id_Negocio='1';";
}
if($cant=='3'){
$consulta="UPDATE informacionnegocio SET Precio_Lav_Sillon='{$precioS}' WHERE Id_Negocio='1';";
}
if($cant=='4'){
$consulta="UPDATE informacionnegocio SET Precio_Cojin='{$precioCoj}'  WHERE Id_Negocio='1';";
}
mysqli_set_charset($conexion, "utf8");
mysqli_query($conexion,$consulta);


mysqli_close($conexion);
$json_string = json_encode($JSON);
echo $json_string;

?>