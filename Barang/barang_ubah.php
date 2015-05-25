<?php
$db = 'db_barang';
$host = 'localhost';
$user = 'root';
$password = '';

$barang_id = $_GET['barang_id'];
$barang_nama = $_GET['barang_nama'];
$barang_merk = $_GET['barang_merk'];
$barang_harga = $_GET['barang_harga'];

$sql = mysql_connect($host, $user, $password) or die(mysql_error());
mysql_select_db($db, $sql) or die(mysql_error());

$query = mysql_query(("UPDATE tbl_barang SET barang_nama='$barang_nama', barang_merk='$barang_merk', 
						barang_harga='$barang_harga' WHERE barang_id='$barang_id'"), $sql) or die(mysql_error());
if($query == 1) {
	echo 'Ubah Data Berhasil';
} else {
	echo 'Ubah Data Gagal';
}
?>

