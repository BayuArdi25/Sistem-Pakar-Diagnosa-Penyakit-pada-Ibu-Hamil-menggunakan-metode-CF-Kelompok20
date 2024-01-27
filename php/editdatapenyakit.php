<?php
require_once 'kon.php';
if($_POST){
    $id_penyakit = filter_input(INPUT_POST, 'id_penyakit', FILTER_SANITIZE_STRING);
    $nama_penyakit = filter_input(INPUT_POST, 'nama_penyakit', FILTER_SANITIZE_STRING);
    $keterangan = filter_input(INPUT_POST, 'keterangan', FILTER_SANITIZE_STRING);
    $solusi = filter_input(INPUT_POST, 'solusi', FILTER_SANITIZE_STRING);
    $ciri = filter_input(INPUT_POST, 'ciri', FILTER_SANITIZE_STRING);

    $response = [];
        $insertAccount = "UPDATE penyakit set nama_penyakit='$nama_penyakit',keterangan='$keterangan',
        solusi='$solusi', ciri='$ciri' where id_penyakit='$id_penyakit'";
        $statement = $connection->prepare($insertAccount);
        try{
            //Eksekusi statement db
            $statement->execute([
                ':id_penyakit' => $id_penyakit,
                ':nama_penyakit' => $nama_penyakit,
                ':keterangan' => $keterangan,
                ':solusi' => $solusi,
                ':ciri' => $ciri
            ]);

            //Beri response
            $response['status']= true;
            $response['pesan']='Data berhasil diubah';
            $response['datapenyakit'] = [
                'id_penyakit' => $id_penyakit,
                'nama_penyakit' => $nama_penyakit,
                'keterangan' => $keterangan,
                'solusi' => $solusi,
                'ciri' => $ciri
            ];
        } catch (Exception $e){
            die($e->getMessage());
        }
    }
    $json = json_encode($response, JSON_PRETTY_PRINT);
    echo $json;