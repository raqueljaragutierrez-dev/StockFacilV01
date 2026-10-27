<%-- 
    Document   : lista
    Created on : 20 oct 2026, 10:07:52 a. m.
    Author     : raque
--%>

<%@page contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../comun/encabezado.jsp" %>
<div class="d-flex justify-content-between align-items-center mb-3">
    <h1 class="h3">Categorías</h1><%-- muestra el encabezado de la pagina--%>
    <a class="btn btn-primary" href="categorias?accion=nuevo">Nueva categoría</a><%-- abre el formulario para registrar una categoria--%>
</div>
<%-- nuevo formulario, AGREGAMOS EL BUSCADOR--%>
<form method="get" action="categorias"<%--method="get" envia la busqueda como parametro de la URL, action="categorias" permite q el servlet reciba
      el texto mediante getParameter("nombre")--%>
      class="card card-body mb-3">

    <label for="nombreBusqueda" class="form-label">
        Buscar por nombre
    </label>

    <div class="d-flex gap-2 flex-wrap">
        <input
            type="text"
            class="form-control"
            style="max-width: 400px"
            id="nombreBusqueda"
            name="nombre"
            placeholder="Escriba el nombre de la categoría"
            value="<c:out value='${nombreBusqueda}'/>"><%--conserva el texto buscado--%>

        <button type="submit" class="btn btn-primary">
            Buscar <%-- envia el formulario--%>
        </button>

        <a href="categorias" class="btn btn-outline-secondary">
            Limpiar<%--regresa a categorias sin el parametro de busqueda--%>
        </a>
    </div>

</form>
<table class="table table-striped bg-white">
    <thead>
        <tr><th>Nombre</th><th>Descripción</th><th>Estado</th><th></th></tr>
    </thead>
    <tbody>
        <c:forEach var="cat" items="${categorias}"><%-- recorre la lista de categorias recibida desde el controlador--%>
            <tr>
                <td><c:out value="${cat.nombre}"/></td>
                <td><c:out value="${cat.descripcion}"/></td>
                <td>${cat.activo ? 'Activa' : 'Inactiva'}</td>
                <td class="text-end">
                    <a class="btn btn-sm btn-outline-secondary"
                       href="categorias?accion=editar&id=${cat.idCategoria}">Editar</a>
                    <c:if test="${cat.activo}">
                        <form method="post" action="categorias" class="d-inline"
                              onsubmit="return confirm('¿Desactivar esta categoría?')">
                            <input type="hidden" name="accion" value="desactivar">
                            <input type="hidden" name="id" value="${cat.idCategoria}">
                            <button class="btn btn-sm btn-outline-danger">Desactivar</button>
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty categorias}">
            <tr><td colspan="4" class="text-muted">No hay categorias que coincidan con la busqueda.</td></tr>
        </c:if>
    </tbody>
</table>
<%@ include file="../comun/pie.jsp" %>
