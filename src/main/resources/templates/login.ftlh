<!DOCTYPE html>
<html>
<head>
    <title>demoShop</title>
</head>
<body>
<h1>demoShop</h1>
<hr>
<h4>Authorization</h4>
<form action="/" method="get">
    Login: <input type="login" name="userLogin"><br><br>
    Password: <input type="password" name="password"><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Authorization"/>
</form>
<br>
<br>
<form action="/registration" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Register"/>
</form>
</body>
</html>

