<?php

/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['reg_FullName_value']) && isset($_POST['reg_Username_value']) && isset($_POST['reg_Email_value'])&& isset($_POST['reg_Password_value']))
{
    $reg_FullName = $_POST['reg_FullName_value'];
    $reg_Username = $_POST['reg_Username_value'];
    $reg_Email = $_POST['reg_Email_value'];
    $reg_Password = $_POST['reg_Password_value'];

    // include db connect class
    require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();

    // mysql inserting a new row
    $result = mysql_query("INSERT INTO userinfo(name, username, email, password) VALUES('$reg_FullName', '$reg_Username', '$reg_Email','$reg_Password')");

    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "User Successfully created.";

        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";

        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>