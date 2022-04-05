<!DOCTYPE html>
<html>
<head>
    <!--<meta charset="UTF-8">-->
    <title>demoShop</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
</head>
<body>
<#include "part/navbar.ftlh">
<div class="container mt-5">

    <!-- Option 2: Separate Popper and Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
            integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
            integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
            crossorigin="anonymous"></script>

    <#if user.login??>
        <h3>Username: <i>${user.name}</i></h3>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button class="btn btn-primary" type="submit">Exit</button>
        </form>
        <br>
        <#if user.isAdmin()>
            <a href="/admin">Admin panel</a>
            <hr>
        </#if>
    <#else>
        <form action="/login" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" value="Enter"/>
        </form>
    </#if>
    <hr>
    <h3>Products from all country</h3>

    <form action="/" method="get">
        <form class="row row-cols-lg-auto g-3 align-items-center">
            <div class="col-3">
                <label class="visually-hidden" for="inlineFormInputGroupUsername">product title</label>
                <div class="input-group">
                    <input type="text" name="filter" class="form-control" placeholder="title ">
                    <button type="submit " class="btn btn-primary ml-3">Search</button>
                    <#--<div class="col-3">

                    </div>-->
                </div>
            </div>
        </form>

        <#--<form action="/" method="get">
            Search product by title: <input type="text" name="title">
            <input type="submit" value="search">
        </form>-->

        <#list products as product>
            <div>
                <p><b>${product.title}</b>
                    <#--b><img src="/images/${images.get(true)}" height="60px"/><b>-->
                    <#--поместить Превьюшную фотку каждого продукта на начальную страницу!!!-->
                    <br>${product.price} rub | <a href="/product/${product.id}">Details ></a></p>
            </div>
        <#else>
            <h3>No products...</h3>
        </#list>
        <#if user.login??>
            <hr>
            <form action="/product/create" method="get">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <input type="submit" value="Create Product"/>
            </form>

        </#if>
</body>
</html>