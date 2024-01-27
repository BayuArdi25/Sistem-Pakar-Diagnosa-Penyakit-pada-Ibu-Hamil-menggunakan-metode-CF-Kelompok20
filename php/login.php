<?php
include 'kon.php';
if($_POST){
    $username = $_POST['username'];
    $password = $_POST['password'];
    $response = [];
    $userQuery = $connection->prepare("SELECT * FROM akun where username = ? and password = ?");
    $userQuery->execute(array($username, $password));
    $query = $userQuery->fetch();

    if($userQuery->rowCount() == 0){
        $response['status'] = false;
        $response['message'] = "Username atau Password Salah!";
    } else {
            $response['status'] = true;
            $response['message'] = "Login Berhasil";
            $response['dataakun'] = [
                'username' => $query['username'],
                'password' => $query['password'],
                'nama' => $query['nama'],
                'alamat' => $query['alamat'],
                'nohp' => $query['nohp'],
                'level' => $query['level'],
                'tanggal' => $query['tanggal']
            ];
        }
    //Jadikan data JSON
    $json = json_encode($response, JSON_PRETTY_PRINT);

    //Print
    echo $json;
    }
