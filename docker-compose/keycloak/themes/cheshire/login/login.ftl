<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="${url.resourcesPath}/css/style.css">
    </head>
    <body>
        <div class="login-container">
            <div class="logo">
                <img src="${url.resourcesPath}/img/logo.svg" alt="Logo"/>
            </div>

            <h2>Welcome</h2>

            <form id="kc-form-login" onsubmit="login.disabled = true; return true;" action="${url.loginAction}" method="post">
                <div class="form-group">
                    <input tabindex="1" id="username" name="username" type="text" placeholder="Username" autofocus value="${(login.username!'')}" />
                </div>
                <div class="form-group">
                    <input tabindex="2" id="password" name="password" type="password" placeholder="Password"/>
                </div>
                <div class="form-group">
                    <button type="submit" id="kc-login">Login →</button>
                </div>
            </form>

            <#if realm.password && social.providers?? && social.providers?size gt 0>
                <div class="social-providers">
                    <#list social.providers as p>
                        <a href="${p.loginUrl}">${p.displayName}</a>
                    </#list>
                </div>
            </#if>
        </div>
    </body>
</html>
