<%--
  Created by IntelliJ IDEA.
  User: 강주영
  Date: 2023-12-08
  Time: 오전 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>editform</title>
</head>
<body>
<form:form commandName="boardVO" method="POST" action="../editok">
    <form:hidden path="seq"/>
    <table id="edit">
        <tr><tb>카테고리</tb><tb><form:input path="category" /></tb></tr>
        <tr><tb>제목</tb><tb><form:input path="title" /></tb></tr>
        <tr><tb>글쓴이</tb><tb><form:input path="writer" /></tb></tr>
        <tr><tb>내용</tb><tb><form:textarea col="50" rows="5" path="content" /></tb></tr>

    </table>
    <input type="submit" value="수정하기"/>
    <input type="button" value="취소하기" onclick="history"/>

</form:form>
</body>
</html>
