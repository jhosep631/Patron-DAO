
package com.emergentes.controlador;

import com.emergentes.dao.AvisoDAO;
import com.emergentes.dao.AvisoDAOimpl;
import com.emergentes.dao.UsuarioDAO;
import com.emergentes.dao.UsuarioDAOimpl;
import com.emergentes.modelo.Aviso;
import com.emergentes.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "AvisoController", urlPatterns = {"/AvisoController"})
public class AvisoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        AvisoDAO dao = new AvisoDAOimpl();

        Aviso avi = new Aviso();
        int id;

        String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
        switch (action) {
            case "add":
                request.setAttribute("aviso", avi);
                request.getRequestDispatcher("frmaviso.jsp").forward(request, response);
                break;

            case "edit":
                // Aviso controller?action=edit&id=3
                id = Integer.parseInt(request.getParameter("id"));

                try {
                    // Obtener el objeto que corresponde al registro
                    avi = dao.getById(id);
                } catch (Exception ex) {
                    System.out.println("ERROR AL OBTENER RESITRO" + ex.getMessage());
                }

                // Colocarlo como atributo
                request.setAttribute("aviso", avi);
                // Transferir el control a frmaviso.jsp
                request.getRequestDispatcher("frmaviso.jsp").forward(request, response);
                break;

            case "delete":
                id = Integer.parseInt(request.getParameter("id"));

                try {
                    dao.delete(id);
                } catch (Exception ex) {
                    System.out.println("ERROR AL ELIMINAR " + ex.getMessage());
                }

                response.sendRedirect("AvisoController");
                break;

            case "view":
                List<Aviso> lista = new ArrayList<Aviso>();
                try {
                    lista = dao.getAll();
                } catch (Exception ex) {
                    System.out.println("ERROR AL LISTA" + ex.getMessage());
                }
                request.setAttribute("avisos", lista);
                request.getRequestDispatcher("avisos.jsp").forward(request, response);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String titulo = request.getParameter("titulo");
        String contenido = request.getParameter("contenido");

        Aviso avi = new Aviso();

        avi.setId(id);
        avi.setTitulo(titulo);
        avi.setContenido(contenido);

        AvisoDAO dao = new AvisoDAOimpl();

        if (id == 0) {
            try {
                // Nuevo Registro
                dao.insert(avi);
            } catch (Exception ex) {
                System.out.println("ERROR AL REGISTRAR" + ex.getMessage());
            }
        } else {
            try {
                // Edicion
                dao.update(avi);
            } catch (Exception ex) {
                System.out.println("ERROR AL editar" + ex.getMessage());

            }
        }
        response.sendRedirect("AvisoController");

    }

}
