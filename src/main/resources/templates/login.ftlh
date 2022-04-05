<!DOCTYPE html>
<html lang="eu" xmlns="http://www.w3.org/1999/html">
<head>
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

    <h4>Authorization</h4>
    <br>
    <form action="/login" method="post">
        <div class="form-group row">
            <label class="col-sm-1 col-form-label"> Login: </label>
            <div class="col-sm-3">
                <input type="login" name="username" class="form-control" placeholder="User name">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-1 col-form-label">Password: </label>
            <div class="col-sm-3">
                <input type="password" name="password" class="form-control" placeholder="Password">
            </div>
        </div>
        <br>
        <label><input type="hidden" name="_csrf" value="${_csrf.token}"></label>
        <button class="btn btn-primary" type="submit">LogIn</button><hr>
        <a href="registration">Add new User</a>
    </form>
</div>
</body>
</html>
