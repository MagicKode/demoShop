<!DOCTYPE html>
<head>
    <!--<meta charset="UTF-8">-->
    <title>demoShop</title>
</head>
<body>
<h1>demoShop</h1>
<hr>
<br>
<#--Button "HOME"-->
<form action="/" method="get">
    <input type="submit" value="Home"/>
</form>

<br>
<#--form of Product info-->
<h4>Product information</h4>
<br>
<#list images as image>
    <img src="/images/${image.id}" height="60px"/><br><br>
</#list>
<br>
<b>Product Title: </b>${product.title}<br>
<b>Product Description: </b>${product.description}<br>
<b>Product Price: </b>${product.price}<br>
<b>Product City: </b>${product.city}<br>
<b>Manufacturer: </b>${product.manufacturer}<br> <#--добавим Производителя продукта-->
<b>Product Author: </b><a href="/user/${product.user.id}">${product.user.name}</a><br><#--сделать SELECT юзера, который создаёт (по имени)!!--<#--author.-->
<br>
<br>
<#if product.user.login??>
    <form action="/product/delete/${product.id}" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="DELETE Product"/><br>
    </form>
</#if>
</body>







