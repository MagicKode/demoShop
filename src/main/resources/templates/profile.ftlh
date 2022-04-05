<#--
<#import "part/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">My cabinet</h1>
    <hr>
    <br>
    <div style="text-align: center">
        <img src="/static/image/img.png"
             style="border:2px solid black; border-radius: 20px" height="120">
        <br>
        <br>
        <h2>${user.name}</h2>
        <br>
        Email: <b>${user.email}</b>
        <br>
        My phone number: <b>${user.phoneNumber}</b>
        <br>
        <br>
        <#if user.isAdmin()>
            <a class="btn btn-dark mb-3" style="text-align: center; width: 100%"
               href="/admin">
                Admin panel
            </a><br>
        </#if>
        <a class="btn btn-dark mb-3" style="text-align: center; width: 100%"
           href="/my/products">
            My products
        </a>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" style="text-align: center; width: 100%" class="btn btn-danger mb-2">
                Exit
            </button>
        </form>
    </div>
</@t.template>-->
