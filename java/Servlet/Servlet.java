/*
 * Clase de tipo Servlet, la cuál toma los parametros del index y da la respuesta al cliente
 */
package Servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alany
 */
//La clase Servlet extiende los métodos de HttpServlet que es el que nos permite extender las capacidades de los servidores web
public class Servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        //Printwriter imprime representaciones formateadas de objetos en una secuencia como una salida de texto
        try (PrintWriter printWriter = response.getWriter()) {
            //En y obtenemos la dirección en la cuál se ubican nuestros juegos
            String y = request.getServletContext().getRealPath("/");
            //x obtiene los parametros obtenidos del index, es decir, obtiene el nombre del juego que se escogió
            String x = request.getParameter("juego");
            //Con File asignamos la dirección con la cuál ejecutaremos los juegos, y la ruta, x el nombre del juego y la extendión html debido a que son juegos hechos en html
            File index = new File(y + x + ".html");

            BufferedReader reader = new BufferedReader(new FileReader(index));// Leemos el archivo almacenando en el buffer
            // print HTTP headers

            String line = reader.readLine();// Leemos todos los caracteres tecleados

            while (line != null)// repetir hasta que se lea el archivo
            {
                printWriter.println(line);// imprimir la línea actual

                line = reader.readLine();// leer la siguiente línea
            }
            reader.close();// cerrar el lector
            printWriter.close(); //Cerramos transimisión

        } catch (Exception e) {
            //Atrapamos errores posibles
            System.out.println("Mi error: " + e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
