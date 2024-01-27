<?php
require_once 'kon.php';
if($_POST){
    $id_gejala = filter_input(INPUT_POST, 'id_gejala', FILTER_SANITIZE_STRING);
    $nama_gejala = filter_input(INPUT_POST, 'nama_gejala', FILTER_SANITIZE_STRING);

    $response = [];
    $userQuery = $connection->prepare("SELECT * FROM gejalaa where id_gejala = ?");
    $userQuery->execute(array($id_gejala));
    if($userQuery->rowCount() != 0){
        // Beri Response
        $response['status']= false;
        $response['pesan']='Data Sudah Ada';
    } else {
        $insertAccount = 'INSERT INTO gejalaa (id_gejala, nama_gejala) values (:id_gejala, :nama_gejala)';
        $statement = $connection->prepare($insertAccount);

        try{
            //Eksekusi statement db
            $statement->execute([
                ':id_gejala' => $id_gejala,
                ':nama_gejala' => $nama_gejala
            ]);

            //Beri response
            $response['status']= true;
            $response['pesan']='Data berhasil ditambah';
            $response['datagejala'] = [
                'id_gejala' => $id_gejala,
                'nama_gejala' => $nama_gejala
            ];
        } catch (Exception $e){
            die($e->getMessage());
        }
    }
    $json = json_encode($response, JSON_PRETTY_PRINT);
    echo $json;
}