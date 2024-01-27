<?php
require_once 'kon.php';
if($_POST){
    $username = filter_input(INPUT_POST, 'username', FILTER_SANITIZE_STRING);
    $password = filter_input(INPUT_POST, 'password', FILTER_SANITIZE_STRING);
    $nama = filter_input(INPUT_POST, 'nama', FILTER_SANITIZE_STRING);
    $alamat = filter_input(INPUT_POST, 'alamat', FILTER_SANITIZE_STRING);
    $nohp = filter_input(INPUT_POST, 'nohp', FILTER_SANITIZE_STRING);
    $level = filter_input(INPUT_POST, 'level', FILTER_SANITIZE_STRING);
    $tanggal = filter_input(INPUT_POST, 'tanggal', FILTER_SANITIZE_STRING);

    $response = [];
    $userQuery = $connection->prepare("SELECT * FROM akun where username = ?");
    $userQuery->execute(array($username));
    if($userQuery->rowCount() != 0){
        // Beri Response
        $response['status']= false;
        $response['pesan']='Akun sudah digunakan!';
    } else {
        $insertAccount = 'INSERT INTO akun (username, password, nama, alamat, nohp, level, tanggal) 
        values (:username, :password, :nama, :alamat, :nohp, :level, :tanggal)';
        $statement = $connection->prepare($insertAccount);

        try{
            //Eksekusi statement db
            $statement->execute([
                ':username' => $username,
                ':password' => $password,
                ':nama' => $nama,
                ':alamat' => $alamat,
                ':nohp' => $nohp,
                ':level'=> $level,
                ':tanggal'=> $tanggal
            ]);

            //Beri response
            $response['status']= true;
            $response['pesan']='Akun berhasil didaftar';
            $response['dataakun'] = [
                'username' => $username,
                'password' => $password,
                'nama' => $nama,
                'alamat' => $alamat,
                'nohp' => $nohp,
                'level' => $level,
                'tanggal' => $tanggal
            ];
        } catch (Exception $e){
            die($e->getMessage());
        }
    }
    $json = json_encode($response, JSON_PRETTY_PRINT);
    echo $json;
}