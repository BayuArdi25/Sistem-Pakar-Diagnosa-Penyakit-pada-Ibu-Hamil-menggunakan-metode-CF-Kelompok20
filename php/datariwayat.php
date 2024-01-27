<?php
require_once 'kon.php';
    $sql = "SELECT * FROM riwayat";
    $results = mysqli_query($conn, $sql);
    $response = array();
    while( $row = mysqli_fetch_assoc($results) ){
        array_push($response, 
        array(
            'id_riwayat'=>$row['id_riwayat'],
            'nama'=>$row['nama'], 
            'penyakit'=>$row['penyakit'], 
            'alamat'=>$row['alamat'],
            'nohp'=>$row['nohp'],
            'tanggal'=>$row['tanggal']) 
        );
    }
    echo json_encode($response); 


mysqli_close($conn);
?>