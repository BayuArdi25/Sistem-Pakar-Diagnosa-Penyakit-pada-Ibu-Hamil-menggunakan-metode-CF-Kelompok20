<?php
$host = "localhost";
$username = "id19921774_yeovil";
$password = "g#mDKxiq~@#@28q+";
$dbname = "id19921774_202155202050_djokobayu";
$connection = null;
$conn = mysqli_connect($host, $username, $password, $dbname);

try{
    $database = "mysql:dbname=$dbname;host=$host";
    $connection = new PDO($database, $username, $password);
    $connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

} catch (PDOException $e){
    echo "Error ! " . $e->getMessage();
    die;
}
?>