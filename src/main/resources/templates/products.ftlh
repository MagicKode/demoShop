<!DOCTYPE html>
<html lang="">
<head>
    <!--<meta charset="UTF-8">-->
    <title>demoShop</title>
</head>
<body>
<h1>demoShop:
    <form action="/login" method="get">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="LogOUT"/>
    </form>
</h1>
<hr>
<h3>Products from all country</h3>
<form action="/" method="get">
    Search product by title: <input type="text" name="title">
    <input type="submit" value="search">
</form>

<#list products as product>
    <div>
        <p><b>${product.title}</b>
            <<#--b><img src="/images/${images.get(true)}" height="60px"/><b>-->
            <#--поместить Превьюшную фотку каждого продукта на начальную страницу!!!-->
            <br>${product.price} rub | <a href="/product/${product.id}">Details ></a></p>
    </div>
<#else>
    <h3>No products...</h3>
</#list>
<hr>
<form action="/product/create" method="get">
    <input type="submit" value="Create Product"/>
</form>
</body>
</html>