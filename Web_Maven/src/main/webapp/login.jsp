<%--
  Created by IntelliJ IDEA.
  User: 刘寿伟
  Date: 2022/1/18
  Time: 9:56
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
        var sbqNull= /^\s*$/g;
        function sbqsbq() {
            var userName = $("#userName").val();
            var pwd = $("#pwd").val();

            var userNameErr = $("#userNameErr");
            if (sbqNull.test(userName)){
                userNameErr.html("用户名不能为空");
                userNameErr.addClass("err");
                return false;
            }else {
                userNameErr.html("√");
                userNameErr.removeClass().addClass("ok");
            }

            var pwdErr = $("#pwdErr");
            if (sbqNull.test(pwd)){
                pwdErr.html("密码不能为空");
                pwdErr.addClass("err");
                return false;
            }else {
                pwdErr.html("√");
                pwdErr.removeClass().addClass("ok");
            }

            return true;
        }
    </script>
</head>
<body>
    <form action="${pageContext.request.contextPath}/login.do" method="post" onsubmit="return sbqsbq();">
        用户名：<input type="text" name="userName" id="userName">
        <span id="userNameErr"></span><br>
        密码：<input type="password" name="pwd" id="pwd">
        <span id="pwdErr"></span><br>
        <input type="submit" value="提交">
    </form>
</body>
</html>
