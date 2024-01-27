<?php
require_once 'kon.php';
if($_POST){
    $username = filter_input(INPUT_POST, 'username', FILTER_SANITIZE_STRING);
    $password = filter_input(INPUT_POST, 'password', FILTER_SANITIZE_STRING);
    $nama = filter_input(INPUT_POST, 'nama', FILTER_SANITIZE_STRING);
    $alamat = filter_input(INPUT_POST, 'alamat', FILTER_SANITIZE_STRING);
    $nohp = filter_input(INPUT_POST, 'nohp', FILTER_SANITIZE_STRING);
    $level = filter_input(INPUT_POST, 'level', FILTER_SANITIZE_STRING);

    $response = [];
        $insertAccount = "UPDATE akun set password='$password',nama='$nama',
        alamat='$alamat', nohp='$nohp', level=$level where username='$username'";
        $statement = $connection->prepare($insertAccount);
        try{
            //Eksekusi statement db
            $statement->execute([
                ':username' => $username,
                ':password' => $password,
                ':nama' => $nama,
                ':alamat' => $alamat,
                ':nohp' => $nohp,
                ':level'=> $level
            ]);

            //Beri response
            $response['status']= true;
            $response['pesan']='Data berhasil diubah';
            $response['dataakun'] = [
                'username' => $username,
                'password' => $password,
                'nama' => $nama,
                'alamat' => $alamat,
                'nohp' => $nohp,
                'level' => $level
            ];
        } catch (Exception $e){
            die($e->getMessage());
        }
    }
    $json = json_encode($response, JSON_PRETTY_PRINT);
    echo $json;