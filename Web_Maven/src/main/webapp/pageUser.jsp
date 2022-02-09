<%--
  Created by IntelliJ IDEA.
  User: 刘寿伟
  Date: 2022/1/18
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form>
    <table>
        <tr>
            <td>
                <a href="${pageContext.request.contextPath}/add&updateUser.jsp">添加</a>
            </td>
        </tr>
    </table>
</form>

<table border="1" width="80%" cellpadding="0" cellspacing="0">
    <tr>
        <td>id</td>
        <td>userName</td>
        <td>pwd</td>
        <td>sex</td>
        <td>date</td>
        <td>address</td>
        <td>操作</td>
    </tr>
    <c:forEach var="li" items="${msg}">
        <tr>
            <td>${li.id}</td>
            <td>${li.userName}</td>
            <td>${li.pwd}</td>
            <td>${li.sex}</td>
            <td>${li.date}</td>
            <td>${li.address}</td>
            <td>
                <a href="${pageContext.request.contextPath}/findUserById.do?id=${li.id}">修改</a>
                <a href="${pageContext.request.contextPath}/deleteUser.do?id=${li.id}">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>
    <table>
        <tr>
            <td>
                第&nbsp;&nbsp;${info.pageNo}&nbsp;&nbsp;页
                共&nbsp;&nbsp;${info.totalPageCount}&nbsp;&nbsp;页
                共&nbsp;&nbsp;${info.totalCount}&nbsp;&nbsp;条数据
                <br>
                <c:if test="${info.pageNo>1}">
                    <a href="${pageContext.request.contextPath}/pageUser.do?pageNo=1">首页</a>
                    <a href="${pageContext.request.contextPath}/pageUser.do?pageNo=${info.pageNo-1}">上一页</a>
                </c:if>
                <c:if test="${info.pageNo<info.totalPageCount}">
                    <a href="${pageContext.request.contextPath}/pageUser.do?pageNo=${info.pageNo+1}">下一页</a>
                    <a href="${pageContext.request.contextPath}/pageUser.do?pageNo=${info.totalPageCount}">末页</a>
                </c:if>
            </td>
        </tr>
    </table>
</body>
</html>
