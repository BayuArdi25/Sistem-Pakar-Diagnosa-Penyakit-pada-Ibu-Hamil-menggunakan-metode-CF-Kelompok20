<?php
require_once 'kon.php';
if($_POST){
    $id_gejala = filter_input(INPUT_POST, 'id_gejala', FILTER_SANITIZE_STRING);

    $response = [];
        $insertAccount = "Delete from gejalaa where id_gejala='$id_gejala'";
        $statement = $connection->prepare($insertAccount);
        try{
            //Eksekusi statement db
            $statement->execute([
                ':id_gejala' => $id_gejala,
            ]);

            //Beri response
            $response['status']= true;
            $response['pesan']='Data berhasil dihapus';
            $response['datagejala'] = [
                'id_gejala' => $id_gejala,
            ];
        } catch (Exception $e){
            die($e->getMessage());
        }
    }
    $json = json_encode($response, JSON_PRETTY_PRINT);
    echo $json;