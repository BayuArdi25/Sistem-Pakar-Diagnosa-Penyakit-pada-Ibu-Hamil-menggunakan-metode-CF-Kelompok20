<?php
require_once 'kon.php';
if($_POST){
    $username = filter_input(INPUT_POST, 'username', FILTER_SANITIZE_STRING);

    $response = [];
        $insertAccount = "Delete from akun where username='$username'";
        $statement = $connection->prepare($insertAccount);
        try{
            //Eksekusi statement db
            $statement->execute([
                ':username' => $username,
            ]);

            //Beri response
            $response['status']= true;
            $response['pesan']='Data berhasil dihapus';
            $response['datapenyakit'] = [
                'username' => $username,
            ];
        } catch (Exception $e){
            die($e->getMessage());
        }
    }
    $json = json_encode($response, JSON_PRETTY_PRINT);
    echo $json;