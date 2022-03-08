<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Home Work-Code Exercise#1</title>
</head>
<body>
<h1><%= "Name : Xie Jincong" %>
</h1>
<h1><%="ID: 2020211001001230"%>
</h1>
<h1><%="Date and Time Mon Mar 8 19:21:18 CST 2022"%></h1>
<div  style="text-align: left;">
<form name="form">
    <input type="text" placeholder="Username" name="Username"><br>
    <input type="passsword" placeholder="password" name="password"><br>
    <input type="email" placeholder="Email" name="Email"><br>
    <input type="radio" nmae="1" id="Male"/>Male
    <input type="radio" name="1" id="Female"/>Female<br>

    <input type="text" placeholder="Date of Birth(yyyy-mm-dd)" name="Date"><br>
    <input type="submit" name="submit" id="jc"><br>
</form>
</div>
<script>
    document.getElementById("jc").onclick=function click(){
        var a=form.Username.value.trim();
        var b=form.password.value.trim().length;
        var c=form.Email.value.trim().length;
        var d=form.Date.value.trim();
        if(b<8){
            alert("The password length must be more than eight digits!")
        }else if(c!=10){
            alert("Date format error!")
        }
        if(a!=0 && b>=8 && c!=10 && d!=0){
            alert("Login is successful!")
        }else{
            alert("Incomplete information!")
        }
    }
</script>



<br/>
</body>
</html>
