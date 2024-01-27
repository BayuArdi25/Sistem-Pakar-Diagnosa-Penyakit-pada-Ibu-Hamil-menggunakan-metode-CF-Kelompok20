<?php
require_once 'kon.php';
    $sql = "SELECT * FROM gejalaa ORDER BY id_gejala";
    $results = mysqli_query($conn, $sql);
    $response = array();
    while( $row = mysqli_fetch_assoc($results) ){
        array_push($response, 
        array(
            'id_gejala'=>$row['id_gejala'], 
            'nama_gejala'=>$row['nama_gejala']) 
        );
    }
    echo json_encode($response); 


mysqli_close($conn);
?>
