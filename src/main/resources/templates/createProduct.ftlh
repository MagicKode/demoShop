<!DOCTYPE html>
<html xmlns:form="http://www.w3.org/1999/html">
<head>
    <!--<meta charset="UTF-8">-->
    <title>demoShop</title>
</head>
<body>
<h1>demoShop</h1>
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
    <#--Product author: <label><input type="text" name="author"></label><br>
    <br>-->
    Image1: <label><input type="file" name="file1"/></label><br><br>
    Image2: <label><input type="file" name="file2"/></label><br><br>
    Image3: <label><input type="file" name="file3"/></label><br><br>
    Image4: <label><input type="file" name="file4"/></label><br><br>
    <br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Add product"/>
</form>
</body>
</html>