<?php
require_once 'kon.php';
if($_POST){
    $id_rule = filter_input(INPUT_POST, 'id_rule', FILTER_SANITIZE_STRING);

    $response = [];
        $insertAccount = "Delete from rule where id_rule='$id_rule'";
        $statement = $connection->prepare($insertAccount);
        try{
            //Eksekusi statement db
            $statement->execute([
                ':id_rule' => $id_rule,
            ]);

            //Beri response
            $response['status']= true;
            $response['pesan']='Data berhasil dihapus';
            $response['datapenyakit'] = [
                'id_rule' => $id_rule,
            ];
        } catch (Exception $e){
            die($e->getMessage());
        }
    }
    $json = json_encode($response, JSON_PRETTY_PRINT);
    echo $json;