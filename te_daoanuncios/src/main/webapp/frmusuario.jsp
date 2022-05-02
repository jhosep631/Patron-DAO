<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.emergentes.modelo.Usuario"%>
<%
    Usuario usuario = (Usuario) request.getAttribute("usuario");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Nuevo Registro</h1>
        <form action="UsuarioController" method="post">
            <input type="hidden" name="id" value="${usuario.id}">
            <br><br>
            <label>Nombre</label>
            <input type="text" name="nombre" class="" value="${usuario.nombre}" />
            <br><br>
            <label>Correo</label>
            <input type="text" name="correo" class="" value="${usuario.correo}" />
            <br><br>
            <label>Clave</label>
            <input type="text" name="clave" class="" value="${usuario.clave}" />
            <input type="submit" class="" value="Enviar" />

        </form>
    </body>
</html>
