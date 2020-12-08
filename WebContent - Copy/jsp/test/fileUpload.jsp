
<!DOCTYPE HTML>
<!--
/*
 * jQuery File Upload Plugin Basic Demo
 * https://github.com/blueimp/jQuery-File-Upload
 *
 * Copyright 2013, Sebastian Tschan
 * https://blueimp.net
 *
 * Licensed under the MIT license:
 * https://opensource.org/licenses/MIT
 */
-->
<html lang="en">
<head>
<!-- Force latest IE rendering engine or ChromeFrame if installed -->
<!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->
<meta charset="utf-8">
<title>jQuery File Upload Demo - Basic version</title>
<meta name="description" content="File Upload widget with multiple file selection, drag&amp;drop support and progress bar for jQuery. Supports cross-domain, chunked and resumable file uploads. Works with any server-side platform (PHP, Python, Ruby on Rails, Java, Node.js, Go etc.) that supports standard HTML form file uploads.">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap styles -->

</head>
<body>

<form method="POST" action="/Drone/other/uploadMultipleFile" enctype="multipart/form-data">
		File1 to upload: <input type="file" name="file"><br /> 
		Name1: <input type="text" name="photo"><br /> <br /> 
		File2 to upload: <input type="file" name="file"><br /> 
		Name2: <input type="text" name="photo"><br /> <br />
		<input type="hidden" name="action" value="test"><br /> <br />
		<input type="submit" value="Upload"> Press here to upload the file!
	</form>
</body>
</html>
