<?php
	
	// This sample uses the HTTP_Request2 package. (for more information: http://pear.php.net/package/HTTP_Request2)
	require_once 'HTTP/Request2.php';
	$headers = array(
	   'Content-Type' => 'application/json',
	);
	
	$query_params = array(
	   // Specify your subscription key
	   'subscription-key' => '4ad0d6534dc44dd7aa428fead63965a3',
	   // Specify values for optional parameters, as needed
	   'visualFeatures' => 'All',
	);
	
	$request = new Http_Request2('https://api.projectoxford.ai/emotion/v1.0/recognize');
	$request->setMethod(HTTP_Request2::METHOD_POST);
	// Basic Authorization Sample
	// $request-setAuth('{username}', '{password}');
	$request->setHeader($headers);
	
	$url = $request->getUrl();
	$url->setQueryVariables($query_params);
	$request->setBody("{'Url':'http://www.lifed.com/wp-content/uploads/2014/10/smiling-woman.jpg'}");
	
	try
	{
	   $response = $request->send();
	   

		echo $response->getBody();
	}
	catch (HttpException $ex)

	{
	   echo $ex;
	}
	
	?>