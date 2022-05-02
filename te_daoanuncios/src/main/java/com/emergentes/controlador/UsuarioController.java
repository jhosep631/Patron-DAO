
package com.emergentes.controlador;

import com.emergentes.dao.UsuarioDAO;
import com.emergentes.dao.UsuarioDAOimpl;
import com.emergentes.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UsuarioController", urlPatterns = {"/UsuarioController"})
public class UsuarioController extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        UsuarioDAO dao = new UsuarioDAOimpl();
        
        Usuario usu = new Usuario();
        int id;
        
        String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
        switch (action) {
            case "add":
                request.setAttribute("usuario", usu);
                request.getRequestDispatcher("frmusuario.jsp").forward(request, response);
                break;

            case "edit":
                // Aviso controller?action=edit&id=3
                id = Integer.parseInt(request.getParameter("id"));

                try {
                    // Obtener el objeto que corresponde al registro
                    usu = dao.getById(id);
                } catch (Exception ex) {
                    System.out.println("ERROR AL OBTENER RESITRO" + ex.getMessage());
                }

                // Colocarlo como atributo
                request.setAttribute("usuario", usu);
                // Transferir el control a frmaviso.jsp
                request.getRequestDispatcher("frmusuario.jsp").forward(request, response);
                break;

            case "delete":
                id = Integer.parseInt(request.getParameter("id"));

                try {
                    dao.delete(id);
                } catch (Exception ex) {
                    System.out.println("ERROR AL ELIMINAR " + ex.getMessage());
                }

                response.sendRedirect("UsuarioController");
                break;

            case "view":
                List<Usuario> lista = new ArrayList<Usuario>();
                try {
                    lista = dao.getAll();
                } catch (Exception ex) {
                    System.out.println("ERROR AL LISTAR" + ex.getMessage());
                }
                request.setAttribute("usuarios", lista);
                request.getRequestDispatcher("usuarios.jsp").forward(request, response);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String clave = request.getParameter("clave");
        
        Usuario usu = new Usuario();
        
        usu.setId(id);
        usu.setNombre(nombre);
        usu.setCorreo(correo);
        usu.setClave(clave);
        
        UsuarioDAO dao = new UsuarioDAOimpl();
        
        if (id == 0) {
            try {
                // Nuevo Registro
                dao.insert(usu);
            } catch (Exception ex) {
                System.out.println("ERROR AL REGISTRAR" + ex.getMessage());
            }
        } else {
            try {
                // Edicion
                dao.update(usu);
            } catch (Exception ex) {
                System.out.println("ERROR AL editar" + ex.getMessage());

            }
        }
        response.sendRedirect("UsuarioController");
        
    }


}
