<?php
$db = 'db_barang';
$host = 'localhost';
$user = 'root';
$password = '';

$barang_nama = $_GET['barang_nama'];
$barang_merk = $_GET['barang_merk'];
$barang_harga = $_GET['barang_harga'];

$sql = mysql_connect($host, $user, $password) or die(mysql_error());
mysql_select_db($db, $sql) or die(mysql_error());

$query = mysql_query(("INSERT INTO tbl_barang VALUES (NULL, '$barang_nama', '$barang_merk', '$barang_harga')"), $sql) or die(mysql_error());
if($query == 1) {
	echo 'Simpan Data Berhasil';
} else {
	echo 'Simpan Data Gagal';
}
?>