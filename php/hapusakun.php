<?php
require_once 'kon.php';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $username = filter_input(INPUT_POST, 'username', FILTER_SANITIZE_STRING);

    $response = [];

    // Cek apakah ada entri di tabel riwayat yang sesuai dengan username
    $checkRiwayatQuery = "SELECT COUNT(*) AS count FROM riwayat WHERE username = :username";
    $checkStatement = $connection->prepare($checkRiwayatQuery);
    $checkStatement->bindParam(':username', $username, PDO::PARAM_STR);
    $checkStatement->execute();
    $rowCount = $checkStatement->fetchColumn();

    // Hapus data dari tabel akun dan riwayat
    $deleteAkunQuery = "DELETE FROM akun WHERE username = :username";
    $deleteRiwayatQuery = "DELETE FROM riwayat WHERE username = :username";

    $deleteStatementAkun = $connection->prepare($deleteAkunQuery);
    $deleteStatementRiwayat = $connection->prepare($deleteRiwayatQuery);

    try {
        // Eksekusi statement db untuk tabel akun
        $deleteStatementAkun->bindParam(':username', $username, PDO::PARAM_STR);
        $deleteStatementAkun->execute();

        // Eksekusi statement db untuk tabel riwayat hanya jika ada entri di tabel riwayat
        if ($rowCount > 0) {
            $deleteStatementRiwayat->bindParam(':username', $username, PDO::PARAM_STR);
            $deleteStatementRiwayat->execute();
        }

        // Beri response
        $response['status'] = true;
        $response['pesan'] = 'Data berhasil dihapus';
        $response['data'] = [
            'username' => $username
        ];
    } catch (Exception $e) {
        $response['status'] = false;
        $response['pesan'] = 'Gagal menghapus data';
        $response['error'] = $e->getMessage();
    }
}

// Mengembalikan response dalam format JSON
$json = json_encode($response, JSON_PRETTY_PRINT);
echo $json;
?>
