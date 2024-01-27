<?php
require_once 'kon.php';
    $sql = "SELECT * FROM penyakit";
    $results = mysqli_query($conn, $sql);
    $response = array();
    while( $row = mysqli_fetch_assoc($results) ){
        array_push($response, 
        array(
            'id_penyakit'=>$row['id_penyakit'], 
            'nama_penyakit'=>$row['nama_penyakit'], 
            'keterangan'=>$row['keterangan'],
            'solusi'=>$row['solusi'],
            'ciri'=>$row['ciri'])
        );
    }
    echo json_encode($response); 


mysqli_close($conn);
?>
