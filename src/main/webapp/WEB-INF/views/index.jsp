<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="${path}/css/normalize.css">
    <link rel="stylesheet" href="${path}/css/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;700&display=swap" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="${path}/js/index.js"></script>
    <title>Pluto Backend Assignment</title>
</head>

<body>
<header>
    <h1>Short URL Generator</h1>
</header>
<main>
    <div class="input-box">
        <div class="input-title">Destination URL</div>
        <input class="url-input" id="destinationUrl" placeholder="https://www.scinapse.io/" />
        <div class="input-title">Password</div>
        <input class="url-input" id="password" type="password" />
    </div>
    <button class="url-button" id="btn-save">Create Short URL</button>
    <a href="${path}/LTLiprS">이동</a>
</main>
</body>
</html>
