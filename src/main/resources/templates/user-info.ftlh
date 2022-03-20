<!DOCTYPE html>
<head>
    <title>demoShop</title>
</head>
<body>
<h1>demoShop</h1>
<hr>
<h3>Detail ${user.name} info</h3>
<b>Phone number: </b> ${user.phoneNumber} <br>
<b>email: </b> ${user.email} <br>

<h4>Product of ${user.name}</h4>
<#list products as product>
    <div>
        <p><b>${product.title}</b> ${product.price} rub | <a href="/product/${product.id}">Details ></a></p>
    </div>
<#else>
    No products..
</#list>
<#if errorMessage??>
    <h2 style="color: red"/>
</#if>
</body>
</html>