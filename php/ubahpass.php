<?php
require_once 'kon.php';
if($_POST){
    $username = filter_input(INPUT_POST, 'username', FILTER_SANITIZE_STRING);
    $password = filter_input(INPUT_POST, 'password', FILTER_SANITIZE_STRING);

    $response = [];
        $insertAccount = "UPDATE akun set password ='$password' where username='$username'";
        $statement = $connection->prepare($insertAccount);
        try{
            //Eksekusi statement db
            $statement->execute([
                ':username' => $username,
                ':password' => $password,
            ]);

            //Beri response
            $response['status']= true;
            $response['pesan']='Password berhasil diubah';
            $response['dataakun'] = [
                'username' => $username,
                'password' => $password
            ];
        } catch (Exception $e){
            die($e->getMessage());
        }
    }
    $json = json_encode($response, JSON_PRETTY_PRINT);
    echo $json;