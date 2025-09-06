<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Info</title>
    <link rel="stylesheet" href="${url.resourcesPath}/css/style.css">
</head>
<body>
<div class="login-container">
    <div class="logo">
        <img src="${url.resourcesPath}/img/logo.svg" alt="Logo"/>
    </div>

    <h2>Information</h2>

    <div class="form-group">
        <#-- keycloak подставляет сообщение через message.summary -->
        <p>${msg(summary)}</p>
    </div>

    <#-- Ссылка "Назад" если есть backToApplication -->
    <#if client.baseUrl??>
        <div class="form-group">
            <a href="${client.baseUrl}">← Back to application</a>
        </div>
    </#if>
</div>
</body>
</html>
