<?php
error_reporting(E_ALL ^ E_DEPRECATED);
if(isset($_POST['tag']) && $_POST['tag'] != '')
{
$tag = $_POST['tag'];


    require_once 'DB_Functions.php';
    $db = new DB_Functions();
    $response = array("tag" => $tag, "error" => FALSE);

    //registration
    if ($tag == 'register')
		{
        $name = $_POST['FullName'];
        $username = $_POST['Username'];
        $email = $_POST['Email'];
        $password = $_POST['Password'];

        $user = $db->storeUser($name, $username , $email , $password);
        if ($user)
				{
            $response["error"] = FALSE;
            $response["error_msg"] = "Registration Successful";
            return json_encode($response);
        }
        else
				{
           $response["error"] = TRUE;
           $response["error_msg"] = "Registration Failed";
           return json_encode($response);
        }
    }
    //login
    else if ($tag == 'login') {
        $user = $_POST['user'];
        $password = $_POST['password'];
        $user = $db->getUserByUserAndPassword($user , $password);
        if ($user != false) {

            $response["error"] = FALSE;
            $response["Id"] = $user["Id"];
            $response["User"] = $user["User"];
            return json_encode($response);
        } else {
            $response["error"] = TRUE;
            $response["error_msg"] = "Incorrect token or password!";
            return json_encode($response);
        }
    }
		else
		{
			{
				$response["error"] = TRUE;
				$response["error_msg"] = "Incorrect token or password!";
				return json_encode($response);
			}
}
else
{
	$response["error"] = TRUE;
	$response["error_msg"] = "Recieved No Data";
	return json_encode($response);
}
?>
