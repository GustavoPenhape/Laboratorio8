package com.example.laboratorio8.Servlets;

import com.example.laboratorio8.model.beans.Heroes;
import com.example.laboratorio8.model.daos.DaoHeroes;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ServletHeroe", value = "/ServletHeroe")
public class ServletHeroe extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        action = (action == null) ? "listar" : action;
        RequestDispatcher requestDispatcher;
        DaoHeroes daoHeroes = new DaoHeroes();
        String HeroeId;
        Heroes heroes;

        switch (action) {
            case "listar":
                request.setAttribute("lista", daoHeroes.obtenerlistaHeroes());

                requestDispatcher = request.getRequestDispatcher("Heroes/lista.jsp");
                requestDispatcher.forward(request, response);
                break;
            case "crear":
                requestDispatcher = request.getRequestDispatcher("Heroes/formCrear.jsp");
                requestDispatcher.forward(request, response);
                break;
            case "borrar":
                HeroeId = request.getParameter("id");
                daoHeroes.borrar(HeroeId);

                response.sendRedirect(request.getContextPath() + "/ServletHeroe");

                break;

            case "editar":
                HeroeId = request.getParameter("id");
                heroes = daoHeroes.buscarPorId(HeroeId);

                if (heroes != null) { //abro el form para editar
                    request.setAttribute("heroe_send_jsp", heroes);
                    requestDispatcher = request.getRequestDispatcher("Heroes/formEditar.jsp");
                    requestDispatcher.forward(request, response);
                } else { //id no encontrado
                    response.sendRedirect(request.getContextPath() + "/ServletHeroes");
                }
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        DaoHeroes daoHeroes  = new DaoHeroes();

        switch (action) {
            case "guardar":
                String nombre = request.getParameter("nombre");
                String edad = request.getParameter("edad");
                String genero = request.getParameter("genero");
                //int minSalary = Integer.parseInt(minSalaryStr); //esto deben validar
                //String maxSalaryStr = request.getParameter("maxSalary");
                //int maxSalary = Integer.parseInt(maxSalaryStr); //falta validar
                String clase = request.getParameter("clase");
                String nivel = request.getParameter("nivel");
                String ataque = request.getParameter("ataque");
                String pareja = request.getParameter("pareja");
                Heroes heroes = new Heroes();
                heroes.setNombre(nombre);
                heroes.setEdad(edad);
                heroes.setGenero(genero);
                heroes.setClase(clase);
                heroes.setNivel_inicial(nivel);
                heroes.setAtaque(ataque);
                heroes.setPareja(pareja);
                daoHeroes.guardarHeroes(heroes);
                response.sendRedirect(request.getContextPath() + "/ServletHeroe");
                break;
            case "actualizar":
                String IdHeroes = request.getParameter("IdHeroes");
                String nombre1 = request.getParameter("nombre");
                String edad1 = request.getParameter("edad");
                String genero1 = request.getParameter("genero");
                String nivel1 = request.getParameter("nivel");
                String clase1 = request.getParameter("clase");
                String ataque1 = request.getParameter("ataque");
                String pareja1 = request.getParameter("pareja");
//                try {
//                    int minSalary1 = Integer.parseInt(minSalaryStr1);
//                    int maxSalary1 = Integer.parseInt(maxSalaryStr1);
//                    daoJob.actualizar(jobId1, jobTitle1, minSalary1, maxSalary1);
//
//                    response.sendRedirect(request.getContextPath() + "/JobServlet");
//                } catch (NumberFormatException e) {
//                    response.sendRedirect(request.getContextPath() + "/JobServlet?action=editar&id=" + jobId1);
//                }

                daoHeroes.actualizarHeroes(IdHeroes,nombre1,edad1,genero1,nivel1,clase1,ataque1,pareja1);
                response.sendRedirect(request.getContextPath() + "/ServletHeroe");
                break;
//            case "actualizarParcial":
//                String jobId2 = request.getParameter("jobId");
//                String jobTitle2 = request.getParameter("jobTitle");
//                String minSalaryStr2 = request.getParameter("minSalary");
//                int minSalary2 = Integer.parseInt(minSalaryStr2);
//
//                try {
//                    daoJob.actualizarParcial(jobId2, jobTitle2, minSalary2);
//                    response.sendRedirect(request.getContextPath() + "/JobServlet");
//                } catch (SQLException e) {
//                    job = daoJob.buscarPorId(jobId2);
//
//                    if (job != null) { //abro el form para editar
//                        request.setAttribute("job", job);
//                        request.setAttribute("error", "El texto no puede tener mas de 255 caractéres");
//                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("jobs/formEditarParcial.jsp");
//                        requestDispatcher.forward(request, response);
//                    } else { //id no encontrado
//                        response.sendRedirect(request.getContextPath() + "/JobServlet");
//                    }
//                }
//
//                break;
//            case "buscar":
//                String searchText = request.getParameter("searchText");
//
//                ArrayList<Job> lista = daoJob.buscarPorJobTitle(searchText);
//                request.setAttribute("lista", lista);
//                request.setAttribute("searchText", searchText);
//
//                RequestDispatcher requestDispatcher = request.getRequestDispatcher("jobs/lista.jsp");
//                requestDispatcher.forward(request, response);
//                break;
        }
    }
}

