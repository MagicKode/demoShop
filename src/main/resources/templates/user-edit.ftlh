<!DOCTYPE html>
<head>
    <title>demoShop</title>
</head>
<body>
<h1>demoShop</h1>
<hr>
<h3>User ${user.name} editing</h3>
<form action="/admin/user/edit" method="post">
    <#list roles as role>
        <div>
            <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
        </div>
    </#list>
    <input type="hidden" value="${user.id}" name="userId" >
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">Save</button>
</form>
</body>
</html>