/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.msg;

import ejb.Item;
import ejb.BankAccount;
import ejb.BankAccountFacade;
import ejb.Basket;
import ejb.BasketFacade;
import ejb.ItemFacade;
import ejb.User;
import ejb.UserFacade;
import ejb.session.ManagementStatefulBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "BuyItemMsg", urlPatterns = {"/BuyItemMsg"})
public class BuyItemMsg extends HttpServlet {

    @EJB
    private ManagementStatefulBeanLocal msb;

    @EJB
    private BankAccountFacade bankAccountFacade;

    @EJB
    private UserFacade userFacade;

    @EJB
    private BasketFacade basketFacade;

    @EJB
    private ItemFacade itemFacade;

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

        String name = request.getParameter("name");

        if (name == null) {
            name = "";
        }
        if (!name.equals("") && msb.getCurrentBasket()!=null) {

            List items = itemFacade.findAll();
            for (Iterator it = items.iterator(); it.hasNext();) {
                Item elem = (Item) it.next();

                if (elem.getName().equals(name)) {

                    List baskets = basketFacade.findAll();
                    for (Iterator it2 = baskets.iterator(); it2.hasNext();) {
                        Basket elem2 = (Basket) it2.next();
                        if (elem2.getName().equals(msb.getCurrentBasket().getName())) {
                            elem.setBasket(elem2);
                            elem.setQuantity(elem.getQuantity()-1.0);

                            itemFacade.edit(elem);
                            msb.storeBasket(elem2);
                            
                            break;
                        }
                    }

                    break;
                }
            }
            System.out.println("--- buy item --- " + name);
            response.sendRedirect("StartPage"); 

        }

        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Error Message</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p> [InputException handled]/p>");
            out.println("<p> Empty or incorrect input</p>");
            out.println("<a href='/NewsApp-war/StartPage'>Return</a>");
            out.println("<br><br>");
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
