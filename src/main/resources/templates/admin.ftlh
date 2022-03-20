<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <!--<meta charset="UTF-8">-->
    <title>demoShop</title>
</head>
<body>
<h1>demoShop: </h1>
<br>
<h3>Admin panel</h3>
<form action="/" method="get">
    <input type="submit" value="Home">
</form>
<br>
<table border="2">
    <tr>
        <th>Login</th>
        <th>Phone number</th>
        <th>Active</th>
        <th>Roles</th>
        <th>Ban</th>
        <th>Editing</th>
        <th>Detail info</th>
    </tr>

<#list user as users>
    <tr>
        <th>${users.login}</th>
        <th>${users.phoneNumber}</th>
        <th><#if users.active>true<#else>false</#if></th>
        <th><#list users.roles as role>${role}</#list></th>
        <th>
            <form action="/admin/user/ban/${users.id}" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <input type="submit" value="Ban">
            </form>
        </th>
        <th><a href="admin/user/edit/${users.id}">Editing</a></th>
        <th><a href="/user/${users.id}">Detail info</a></th>
    </tr>
<#else>
    <h3>No users...</h3>
</#list>
</table>
</body>
</html>