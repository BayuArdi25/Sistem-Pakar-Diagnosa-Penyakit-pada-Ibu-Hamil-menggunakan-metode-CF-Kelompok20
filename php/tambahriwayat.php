<?php
require_once 'kon.php';
if($_POST){
    $username = filter_input(INPUT_POST, 'username', FILTER_SANITIZE_STRING);
    $nama = filter_input(INPUT_POST, 'nama', FILTER_SANITIZE_STRING);
    $penyakit = filter_input(INPUT_POST, 'penyakit', FILTER_SANITIZE_STRING);
    $alamat = filter_input(INPUT_POST, 'alamat', FILTER_SANITIZE_STRING);
    $nohp = filter_input(INPUT_POST, 'nohp', FILTER_SANITIZE_STRING);
    $tanggal = filter_input(INPUT_POST, 'tanggal', FILTER_SANITIZE_STRING);


    $response = [];
    $insertAccount = 'INSERT INTO riwayat (username, nama, penyakit, alamat, nohp, tanggal) 
    values (:username, :nama, :penyakit, :alamat, :nohp, :tanggal)';
        $statement = $connection->prepare($insertAccount);

    try{
        $statement->execute([
            ':username' => $username,
            ':nama' => $nama,
            ':penyakit' => $penyakit,
            ':alamat' => $alamat,
            ':nohp' => $nohp,
            ':tanggal' => $tanggal
        ]);
        $response['status']= true;
        $response['pesan']='Data berhasil ditambah';
        $response['datariwayat'] = [
            'username' => $username,
            'nama' => $nama,
            'penyakit' => $penyakit,
            'alamat' => $alamat,
            'nohp' => $nohp,
            'tanggal' => $tanggal
            ];
        } catch (Exception $e){
            die($e->getMessage());
        }
    
    $json = json_encode($response, JSON_PRETTY_PRINT);
    echo $json;
}