<?php
require_once 'kon.php';
    $sql = "SELECT * FROM akun where level ='1'";
    $results = mysqli_query($conn, $sql);
    $response = array();
    while( $row = mysqli_fetch_assoc($results) ){
        array_push($response, 
        array(
            'username' => $row['username'],
            'password' => $row['password'],
            'nama' => $row['nama'],
            'alamat' => $row['alamat'],
            'nohp' => $row['nohp'],
            'level' => $row['level'],
            'tanggal' => $row['tanggal']) 
        );
    }
    echo json_encode($response); 


mysqli_close($conn);
?>