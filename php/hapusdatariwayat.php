<?php
require_once 'kon.php';
if($_POST){
    $id_riwayat = filter_input(INPUT_POST, 'id_riwayat', FILTER_SANITIZE_STRING);

    $response = [];
        $insertAccount = "Delete from riwayat where id_riwayat='$id_riwayat'";
        $statement = $connection->prepare($insertAccount);
        try{
            //Eksekusi statement db
            $statement->execute([
                ':id_riwayat' => $id_riwayat
            ]);

            //Beri response
            $response['status']= true;
            $response['pesan']='Data berhasil dihapus';
            $response['datariwayat'] = [
                'id_riwayat' => $id_riwayat
            ];
        } catch (Exception $e){
            die($e->getMessage());
        }
    }
    $json = json_encode($response, JSON_PRETTY_PRINT);
    echo $json;