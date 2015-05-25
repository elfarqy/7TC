<?php
$db = 'db_barang';
$host = 'localhost';
$user = 'root';
$password = '';

$sql = mysql_connect($host, $user, $password) or die(mysql_error());
mysql_select_db($db, $sql) or die(mysql_error());
$result["errorcode"]="0";

/* grab the posts from the db */
$query = mysql_query(("SELECT barang_id, barang_nama, barang_merk, barang_harga FROM tbl_barang"), $sql) or die(mysql_error());
$countrow= mysql_affected_rows();
$items = array();
while($row = mysql_fetch_object($query)){
 array_push($items, $row);
}
if($countrow >0){
	$result["errorcode"] = "1";
	$result["data"] = $items;
} else {
	$result["errormsg"] = "Tidak ada data";
}

echo json_encode($result);
?>