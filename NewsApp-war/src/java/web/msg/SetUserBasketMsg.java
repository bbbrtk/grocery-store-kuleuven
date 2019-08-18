/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.msg;

import ejb.BankAccount;
import ejb.BankAccountFacade;
import ejb.UserFacade;
import ejb.session.ManagementStatefulBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bartek
 */
@WebServlet(name = "SetUserBasketMsg", urlPatterns = {"/SetUserBasketMsg"})
public class SetUserBasketMsg extends HttpServlet {

    @EJB
    private ManagementStatefulBeanLocal msb;

    @EJB
    private BankAccountFacade bankAccountFacade;

    @EJB
    private UserFacade userFacade;

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
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SetUserBasketMsg</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SetUserBasketMsg at " + request.getContextPath() + "</h1>");

            // BANKS
            out.println("<h1>BANKS</h1>");
            List banks = bankAccountFacade.findAll();
            for (Iterator it = banks.iterator(); it.hasNext();) {
                BankAccount elem = (BankAccount) it.next();
                out.println(" <b>" + elem.getBankName() + " -- " + elem.getId() + " </b><br />");
            }
            out.println("<br><br>");

            out.println("        <h2>User BANK Form</h2>\n"
                    + "        <form method=\"post\" action=\"SetUserBankMsg\">\n"
                    + "            <fieldset>\n"
                    + "                <legend>Personal Particular 2</legend>\n"
                    + "                Name: <input type=\"text\" name=\"name\" /><br /><br />\n"
                    + "            </fieldset>\n"
                    + "\n"
                    + "            <input type=\"submit\" value=\"SEND\" />\n"
                    + "            <input type=\"reset\" value=\"CLEAR\" />\n"
                    + "        </form>");

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
