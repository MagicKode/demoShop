<!DOCTYPE html>
<head>
    <title>demoShop</title>
</head>
<body>
<h1>demoShop</h1><hr>
<h4>Registration</h4>
<form action="/registration" method="post">
    User name: <input type="text" name="name"><br><br>
    Email: <input type="email" name="email"><br><br>
    Phone number: <input type="text" name="phoneNumber"><br><br>
    User login: <input type="text" name="login"><br><br>
    Password: <input type="password" name="password"><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Registrate"/>
</form>
<#if errorMessage??>
    <h2 style="color: red"/>
</#if>
</body>
</html>