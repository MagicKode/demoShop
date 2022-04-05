<!DOCTYPE html>
<html xmlns:form="http://www.w3.org/1999/html">
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
<#--<#include "part/navbar.ftlh">-->
<div class="container mt-5">

    <!-- Option 2: Separate Popper and Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
            integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
            integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
            crossorigin="anonymous"></script>

<br>
<form action="/" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Back"/>
</form>
<hr>
<h3>Create new product!</h3>
<form action="/product/create" method="post" enctype="multipart/form-data">
    Product title: <label><input type="text" name="title"></label><br>
    <br>
    Product description: <label><input type="text" name="description"></label><br>
    <br>
    Product price: <label><input type="number" name="price"></label><br>
    <br>
    Product city: <label><input type="text" name="city"></label><br>
    <br>
    Product manufacturer: <label><input type="text" name="manufacturer"></label><br>
    <br>
    <div>
        <label for="formFileLg" class="form-label">Select image1</label>
        <input class="form-control form-control-lg" id="formFileLg" type="file" name="file1">
    </div>
    <div>
        <label for="formFileLg" class="form-label">Select image2</label>
        <input class="form-control form-control-lg" id="formFileLg" type="file" name="file2">
    </div>
    <div>
        <label for="formFileLg" class="form-label">Select image3</label>
        <input class="form-control form-control-lg" id="formFileLg" type="file" name="file3">
    </div>
    <div>
        <label for="formFileLg" class="form-label">Select image4</label>
        <input class="form-control form-control-lg" id="formFileLg" type="file" name="file4">
    </div>
    <br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Add product"/>
</form>
</div>
</body>
</html>
