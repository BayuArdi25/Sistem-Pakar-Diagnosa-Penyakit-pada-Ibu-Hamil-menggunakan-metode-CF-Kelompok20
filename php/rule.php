<?php

require_once 'kon.php';

if (isset($_GET['key']) && isset($_GET['keyy'])) {
    $cf_user_raw = explode('#', $_GET['key']);
    $idgej = explode('#', $_GET['keyy']);
    $idnam = implode("','", $idgej);

    $arr_hasil = array();

    // Mengambil data dari database sesuai dengan urutan id_gejala
    $sql = mysqli_query($conn, "SELECT id_gejala FROM gejalaa WHERE id_gejala IN ('$idnam')");
    $db_result = array(); // Menyimpan hasil query ke dalam array untuk mencocokkan urutan

    while ($rgejala = mysqli_fetch_array($sql)) {
        $id_gejala = $rgejala['id_gejala'];
        $db_result[] = $id_gejala;
    }

    // Membuat array hasil sesuai urutan
    foreach ($cf_user_raw as $key => $value) {
        $id = $idgej[$key];
        $arr_hasil[] = array(
            "id_gejala" => $id,
            "cf_user" => $value,
        );
    }

    $hasil = array();
    $x = 0;

    $sqlpenyakit = mysqli_query($conn, "SELECT id_penyakit FROM penyakit ORDER BY id_penyakit");

    while ($rpenyakit = mysqli_fetch_array($sqlpenyakit)) {
        $id_penyakit = $rpenyakit['id_penyakit'];
        $cf_gabungan = 0;
        $i = 0;

        // Mengambil id_gejala yang terkait dengan penyakit $id_penyakit dan gejala yang ada dalam $idnam
        $sql = mysqli_query($conn, "SELECT id_gejala FROM rule WHERE id_penyakit='$id_penyakit' AND id_gejala IN ('$idnam')");

        while ($rgejala = mysqli_fetch_array($sql)) {
            $id_gejala = $rgejala['id_gejala'];

            // Fetch nilai_cf untuk gejala yang sesuai dengan penyakit $id_penyakit
            $r_gejala = mysqli_fetch_array(mysqli_query($conn, "SELECT nilai_cf FROM rule WHERE id_gejala='$id_gejala' AND id_penyakit='$id_penyakit'"));
            $cf_gejala = $r_gejala['nilai_cf'];

            foreach ($arr_hasil as $row) {
                if ($id_gejala == $row['id_gejala']) {
                    $cf = $row['cf_user'] * $cf_gejala;

                    if ($i >= 0) {
                        $cf_gabungan = $cf_gabungan + ($cf * (1 - $cf_gabungan));
                    } else {
                        $cf_gabungan = $cf;
                    }

                    $i++;
                }
            }
        }

        // $persentase = round($cf_gabungan * 100);
        $persentase = $cf_gabungan * 100;
        $hasil[$x]["id_penyakit"] = $id_penyakit;
        $hasil[$x]["nilai"] = number_format($persentase, 2);
        $x++;
    }

    // --------------------- END -------------------------

    // Pilih penyakit dengan nilai cf tertinggi
    array_sort_by_column($hasil, 'nilai');
    $hasil_penyakit_id = $hasil[0]["id_penyakit"];
    $hasil_nilai = $hasil[0]["nilai"];

    // Ambil informasi penyakit dari database
    $q = mysqli_query($conn, "SELECT nama_penyakit, solusi FROM penyakit WHERE id_penyakit='$hasil_penyakit_id'");
    $r = mysqli_fetch_array($q);
    $nama_penyakit = $r['nama_penyakit'];
    $solus = $r['solusi'];

    $response["status"] = 0;
    $response["id_penyakit"] = $hasil_penyakit_id;
    $response["nama_penyakit"] = $nama_penyakit;
    $response["solusi"] = $solus;
    $response["nilai"] = $hasil_nilai;
} else {
    $response["status"] = 2;
    $response["message"] = "Walaweh";
}

// fungsi untuk mengurutkan nilai berdasarkan nilai terbesar
function array_sort_by_column(&$arr, $col, $dir = SORT_DESC)
{
    $sort_col = array();
    foreach ($arr as $key => $row) {
        $sort_col[$key] = $row[$col];
    }
    array_multisort($sort_col, $dir, $arr);
}

echo json_encode($response);
?>
