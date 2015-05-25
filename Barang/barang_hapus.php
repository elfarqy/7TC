<?php
$db = 'db_barang';
$host = 'localhost';
$user = 'root';
$password = '';

$barang_id = $_GET['barang_id'];

$sql = mysql_connect($host, $user, $password) or die(mysql_error());
mysql_select_db($db, $sql) or die(mysql_error());

$query = mysql_query(("DELETE FROM tbl_barang WHERE barang_id='$barang_id'"), $sql) or die(mysql_error());
if($query == 1) {
	echo 'Hapus Data Berhasil';
} else {
	echo 'Hapus Data Gagal';
}
?>

