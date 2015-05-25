<?php
$db = 'db_daftar';
$host = 'localhost';
$user = 'root';
$password = '';

$reg_username = $_POST['reg_username'];
$reg_password = $_POST['reg_password'];

$sql = mysql_connect($host, $user, $password) or die(mysql_error());
mysql_select_db($db, $sql) or die(mysql_error());

$query = mysql_query(("INSERT INTO tbl_register VALUES (NULL, '$reg_username', '$reg_password')"), $sql) or die(mysql_error());
if (mysql_num_rows($query) == 1) {
	echo 1;
} else {
	// print status message
	echo 0;
}
?>