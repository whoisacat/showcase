<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Error</title>
    <link rel="stylesheet" href="${url.resourcesPath}/css/style.css" />
</head>
<body>
<div class="login-container">
    <div class="logo">
        <img src="${url.resourcesPath}/img/logo.svg" alt="Logo" />
    </div>

    <h2>Oops!</h2>
    <p>${message.summary?no_esc}</p>

    <#if (client.baseUrl)?has_content>
        <div class="form-group">
            <a class="btn btn-secondary" href="${client.baseUrl}">Back to application</a>
        </div>
    </#if>
</div>
</body>
</html>
