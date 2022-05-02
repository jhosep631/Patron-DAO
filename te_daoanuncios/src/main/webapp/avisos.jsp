<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Aviso"%>
<%
    List<Aviso> avisos = (List<Aviso>) request.getAttribute("avisos");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    </head>
    <body>
        <h1>Listado de avisos</h1>
        <p><a href="usuarios.jsp">Lista de usuarios</a></p>
        <p><a href="AvisoController?action=add">Nuevo</a></p>
        <div>
            <table class="table table-dark table-hover">
                <tr>
                    <th>ID</th>
                    <th>TITULO</th>
                    <th>CONTENIDO</th>
                    <th>EDITAR</th>
                    <th>ELIMINAR</th>
                </tr>
                <c:forEach var="item" items="${avisos}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.titulo}</td>
                        <td>${item.contenido}</td>
                        <td><a class="btn btn-success" href="AvisoController?action=edit&id=${item.id}">Editar</a></td>
                        <td><a href="AvisoController?action=delete&id=${item.id}">Eliminar</a></td>
                    </tr>
                </c:forEach>
            </table>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        </div>
    </body>
</html>
