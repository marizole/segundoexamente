package com.emergentes.controlador;

import com.emergente.dao.PermisosDAO;
import com.emergente.dao.PermisosDAOimpl;
import com.emergente.dao.RolesDAO;
import com.emergente.dao.RolesDAOimpl;
import com.emergente.dao.UsuarioDAO;
import com.emergente.dao.UsuariosDAOimpl;
import com.emergentes.modelo.Permisos;
import com.emergentes.modelo.Roles;
import com.emergentes.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PermisosControlador", urlPatterns = {"/PermisosControlador"})
public class PermisosControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try{
            PermisosDAO dao = new PermisosDAOimpl();
            RolesDAO daoRoles = new RolesDAOimpl();
            UsuarioDAO daoUsuario = new UsuariosDAOimpl();
            int id;
            
            List<Roles> lista_roles = null;
            List<Usuario> lista_usuario = null;
            
            Permisos per = new Permisos();
            
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            System.out.println("Opcion = "+ action);
            switch(action){
                case "add":
                    lista_roles = daoRoles.getAll();
                    lista_usuario = daoUsuario.getAll();
                    request.setAttribute("lista_roles", lista_roles);
                    request.setAttribute("lista_usuarios", lista_usuario);
                    request.setAttribute("permisos",per);
                    request.getRequestDispatcher("frmpermisos.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    per = dao.getById(id);
                    lista_roles = daoRoles.getAll();
                    lista_usuario = daoUsuario.getAll();
                    request.setAttribute("lista_roles", lista_roles);
                    request.setAttribute("lista_usuario", lista_usuario);
                    request.setAttribute("permisos",per);
                    request.getRequestDispatcher("frmpermisos.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("PermisosControlador");
                    break;
                case "view":
                    List<Permisos> lista = dao.getAll();
                    request.setAttribute("permisos", lista);
                    request.getRequestDispatcher("permisos.jsp").forward(request, response);
                    break;
            }
        }catch(Exception ex){
            System.out.println("Error fatal " + ex.getMessage());
        }
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int id_usuario = Integer.parseInt(request.getParameter("id_usuarios"));
        int id_rol = Integer.parseInt(request.getParameter("id_rol"));
               
        Permisos per = new Permisos();
        
        per.setId(id);
        per.setId_rol(id_rol);
        per.setId_usuario(id_usuario);
                
        if(id == 0){
            // Nuevo
            PermisosDAO dao = new PermisosDAOimpl();
            try {
                dao.insert(per);
                response.sendRedirect("PermisosControlador");
            } catch (Exception ex) {
                Logger.getLogger(PermisosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            //Editar
            PermisosDAO dao = new PermisosDAOimpl();
            try {
                dao.update(per);
                response.sendRedirect("PermisosControlador");
            } catch (Exception ex) {
                Logger.getLogger(PermisosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
   }
}
    
   
