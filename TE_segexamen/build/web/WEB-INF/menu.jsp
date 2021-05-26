<%
    String opcion =  request.getParameter("opcion");
%>
            <ul class="nav nav-tabs">
                <li class="nav-item">
                    <a class="nav-link <%= (opcion.equals("permisos") ?  "active" : "") %>" href="permisos.jsp">Permisos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%= (opcion.equals("usuario") ?  "active" : "") %>" href="UsuarioControlador">Usuario</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%= (opcion.equals("roles") ?  "active" : "") %>" href="RolesControlador">Roles</a>
                </li>
            </ul>
