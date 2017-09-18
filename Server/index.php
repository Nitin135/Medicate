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
      echo json_encode($response);
    }
    else
    {
      $response["error"] = TRUE;
      $response["error_msg"] = "Registration Failed";
      echo json_encode($response);
    }
  }
  //login
  else if ($tag == 'login')
  {
    $user = $_POST['Username'];
    $password = $_POST['Password'];
    $user_check = $db->getUserByUserAndPassword($user , $password);
    if ($user_check != false)
    {
      $response["Success"] = TRUE;
      $response["error"] = FALSE;
      $response["error_msg"] = "User Exists";
      $response["Username"] = $user;
      echo json_encode($response);
    }
    else
    {
      $response["error"] = TRUE;
      $response["error_msg"] = "No such User";
      echo json_encode($response);
    }
  }
  else
  {
    {
      $response["error"] = TRUE;
      $response["error_msg"] = "Incorrect token or password!";
      echo json_encode($response);
    }
  }
}
else
{
	$response["error"] = TRUE;
	$response["error_msg"] = "Recieved No Data";
	echo json_encode($response);
}
?>
