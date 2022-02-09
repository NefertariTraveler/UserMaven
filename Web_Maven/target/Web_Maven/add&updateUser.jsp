<%--
  Created by IntelliJ IDEA.
  User: 刘寿伟
  Date: 2022/1/18
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/css.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <script>
        /*$(function () {
            alert("进来了吗？");
        });*/

        function checkUserName(userName) {
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/checkUserName.do",
                data: "userName="+userName+"&location=Boston",
                success: function(msg){
                    var userNameErr = $("#userNameErr");
                    if (msg == 0){
                        userNameErr.html("用户名已注册");
                        userNameErr.addClass("err");
                        return false;
                    }else {
                        userNameErr.html("√");
                        userNameErr.removeClass().addClass("ok");
                    }
                }
            });
        }

        var sbqNull= /^\s*$/g;
        function checkAll() {
            var userName = $("#userName").val();
            var pwd = $("#pwd").val();
            var address = $("#address").val();

            var userNameErr = $("#userNameErr");
            var pwdErr = $("#pwdErr");
            var addressErr = $("#addressErr");

            if (sbqNull.test(userName)){
                userNameErr.html("用户名不能为空");
                userNameErr.addClass("err");
                return false;
            }

            if (sbqNull.test(pwd)){
                pwdErr.html("密码不能为空");
                pwdErr.addClass("err");
                return false;
            }else {
                pwdErr.html("√");
                pwdErr.removeClass().addClass("ok");
            }

            if (sbqNull.test(address)){
                addressErr.html("地址不能为空");
                addressErr.addClass("err");
                return false;
            }else {
                addressErr.html("√");
                addressErr.removeClass().addClass("ok");
            }

            return checkUserName();
        }
    </script>
</head>
<body>
    <form action="${pageContext.request.contextPath}/${msg=='2' ? 'updateUser.do' : 'addUser.do'}" method="post" onsubmit="return checkAll();">
        <input type="hidden" name="id" value="${user.id}">
        用户名：<input type="text" name="userName" id="userName" value="${user.userName}" onchange="checkUserName(this.value);">
        <span id="userNameErr"></span><br>
        密码：<input type="password" name="pwd" id="pwd" value="${user.pwd}">
        <span id="pwdErr"></span><br>
        性别：
        <input type="radio" name="sex" value="1" <c:if test="${sex==1}">checked</c:if>>男
        <input type="radio" name="sex" value="2" <c:if test="${sex==2}">checked</c:if>>女
        <input type="radio" name="sex" value="3" <c:if test="${sex==3}">checked</c:if>>未知<br>
        地址：<input type="text" name="address" id="address" value="${user.address}">
        <span id="addressErr"></span><br>
        <input type="submit" value="提交">
    </form>
</body>
</html>
