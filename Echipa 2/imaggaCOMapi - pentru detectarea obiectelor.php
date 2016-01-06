<?php

//link site: https://imagga.com/auto-tagging-demo?url=http://www.michigansantaclausllc.com/images/Michigan-Santa-Claus-LLC.jpg


$curl = curl_init();

$link="http://www.michigansantaclausllc.com/images/Michigan-Santa-Claus-LLC.jpg";

curl_setopt_array($curl, array(
  CURLOPT_URL => "http://api.imagga.com/v1/tagging?url=$link&version=2",
  CURLOPT_RETURNTRANSFER => true,
  CURLOPT_ENCODING => "",
  CURLOPT_MAXREDIRS => 10,
  CURLOPT_TIMEOUT => 30,
  CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
  CURLOPT_CUSTOMREQUEST => "GET",
  CURLOPT_HTTPHEADER => array(
    "accept: application/json",
    "authorization: Basic YWNjXzhhNjc1ZWQ5OTIwYjNjYTo1MDk4NmRhNmJkMjcxYWQxOWVjNDk3MDNjZDM5ZWZkOA=="
  ),
));

$response = curl_exec($curl);
$err = curl_error($curl);

curl_close($curl);

if ($err) {
  echo "cURL Error #:" . $err;
} else {
  echo $response;
}