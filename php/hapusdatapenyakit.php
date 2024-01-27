<?php
require_once 'kon.php';
if($_POST){
    $id_penyakit = filter_input(INPUT_POST, 'id_penyakit', FILTER_SANITIZE_STRING);

    $response = [];
        $insertAccount = "Delete from penyakit where id_penyakit='$id_penyakit'";
        $statement = $connection->prepare($insertAccount);
        try{
            //Eksekusi statement db
            $statement->execute([
                ':id_penyakit' => $id_penyakit,
            ]);

            //Beri response
            $response['status']= true;
            $response['pesan']='Data berhasil dihapus';
            $response['datapenyakit'] = [
                'id_penyakit' => $id_penyakit,
            ];
        } catch (Exception $e){
            die($e->getMessage());
        }
    }
    $json = json_encode($response, JSON_PRETTY_PRINT);
    echo $json;