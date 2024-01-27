<?php
require_once 'kon.php';
if($_POST){
    $id_rule = filter_input(INPUT_POST, 'id_rule', FILTER_SANITIZE_STRING);
    $id_penyakit = filter_input(INPUT_POST, 'id_penyakit', FILTER_SANITIZE_STRING);
    $id_gejala = filter_input(INPUT_POST, 'id_gejala', FILTER_SANITIZE_STRING);
    $nilai_cf = filter_input(INPUT_POST, 'nilai_cf', FILTER_SANITIZE_STRING);

    $response = [];
    $userQuery = $connection->prepare("SELECT * FROM rule where id_rule = ?");
    $userQuery->execute(array($id_rule));
    if($userQuery->rowCount() != 0){
        // Beri Response
        $response['status']= false;
        $response['pesan']='Data Sudah Ada';
    } else {
        $insertAccount = 'INSERT INTO rule (id_rule, id_penyakit, id_gejala, nilai_cf) 
        values (:id_rule, :id_penyakit, :id_gejala, :nilai_cf)';
        $statement = $connection->prepare($insertAccount);

        try{
            //Eksekusi statement db
            $statement->execute([
                ':id_rule' => $id_rule,
                ':id_penyakit' => $id_penyakit,
                ':id_gejala' => $id_gejala,
                ':nilai_cf' => $nilai_cf
            ]);

            //Beri response
            $response['status']= true;
            $response['pesan']='Data berhasil ditambah';
            $response['datapenyakit'] = [
                'id_rule' => $id_rule,
                'id_penyakit' => $id_penyakit,
                'id_gejala' => $id_gejala,
                'nilai_cf' => $nilai_cf
            ];
        } catch (Exception $e){
            die($e->getMessage());
        }
    }
    $json = json_encode($response, JSON_PRETTY_PRINT);
    echo $json;
}