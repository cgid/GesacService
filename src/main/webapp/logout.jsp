<%-- 
    Document   : logout
    Created on : 30/05/2016, 15:56:17
    Author     : VictorHugo
--%>

<%
session.setAttribute("userid", null);
session.invalidate();
response.sendRedirect("index.jsp");
%>