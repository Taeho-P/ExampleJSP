<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
public int add(int num1, int num2) {
	return num1 + num2;
}
public void add2() {
	System.out.println("짜잔");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스크립트 요소</title>
</head>
<body>
<%
int result = add(10, 20);
%>
덧셈결과 1 : <%= result %> <br />
덧셈결과 2 : <%= add(30, 40) %>
<% add2(); %>
</body>
</html>