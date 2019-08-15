/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.Basket;
import ejb.BasketFacade;
import ejb.NewsEntity;
import ejb.NewsEntityFacade;
import ejb.ManageStatefulBean;
import ejb.User;
import ejb.UserFacade;
import ejb.session.ManagementStatefulBean;
import ejb.session.ManagementStatefulBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nb
 */
@WebServlet(name = "ListNews", urlPatterns = {"/ListNews"})
public class ListNews extends HttpServlet {

    @EJB
    private UserFacade userFacade;
    @EJB
    private BasketFacade basketFacade;
    @EJB
    private ManageStatefulBean msb;
    @EJB
    private NewsEntityFacade newsEntityFacade;

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
        request.getSession(true);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListNews</title>");
            out.println("</head>");
            out.println("<body>");
            
            // USER INFO
            out.println("<h1>user: " + msb.getCurrentUserLogin() + "</h1>");
            out.println("<h1>ID: " + msb.getCurrentUser().getId() + "</h1>");
            out.println("<h1>Servlet ListNews at " + request.getContextPath() + "</h1>");

            // BUTTONS
            out.println("<a href='userCreate.html'>Add new user</a>");
            out.println("<br><br>");
            out.println("<a href='basketCreate.html'>Add new basket</a>");

            // USERS
            out.println("<h1>USERS</h1>");
            List users = userFacade.findAll();
            for (Iterator it = users.iterator(); it.hasNext();) {
                User elem = (User) it.next();
                out.println(" <b>" + elem.getLogin() + " -- " + elem.getPassword() + " </b><br />");
            }
            out.println("<br><br>");

            // BASKETS
            out.println("<h1>BASKETS</h1>");
            List baskets = basketFacade.findAll();
            for (Iterator it = baskets.iterator(); it.hasNext();) {
                Basket elem = (Basket) it.next();
                out.println(" <b>" + elem.getName() + " -- " + elem.getId() + " </b><br />");
            }
            out.println("<br><br>");
            
            // SESSION
            out.println(msb.getActiveSessionsCount() + " user(s) reading the news.");

            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
