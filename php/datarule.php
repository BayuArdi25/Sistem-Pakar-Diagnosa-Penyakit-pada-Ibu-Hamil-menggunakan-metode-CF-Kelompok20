<?php
require_once 'kon.php';
    $sql = "SELECT * FROM rule";
    $results = mysqli_query($conn, $sql);
    $response = array();
    while( $row = mysqli_fetch_assoc($results) ){
        array_push($response, 
        array(
            'id_rule'=>$row['id_rule'], 
            'id_penyakit'=>$row['id_penyakit'], 
            'id_gejala'=>$row['id_gejala'],
            'nilai_cf'=>$row['nilai_cf'])
        );
    }
    echo json_encode($response); 
mysqli_close($conn);
?>
