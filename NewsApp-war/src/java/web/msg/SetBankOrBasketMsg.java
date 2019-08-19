/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.msg;

import ejb.BankAccount;
import ejb.BankAccountFacade;
import ejb.Basket;
import ejb.BasketFacade;
import ejb.User;
import ejb.UserFacade;
import ejb.session.ManagementStatefulBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bartek
 */
@WebServlet(name = "SetBankOrBasketMsg", urlPatterns = {"/SetBankOrBasketMsg"})
public class SetBankOrBasketMsg extends HttpServlet {

    @EJB
    private ManagementStatefulBeanLocal msb;

    @EJB
    private BankAccountFacade bankAccountFacade;

    @EJB
    private UserFacade userFacade;

    @EJB
    private BasketFacade basketFacade;

//    @PersistenceContext(unitName = "NewsApp-ejbPU")
//    private EntityManager em;
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
        
        String basketName = request.getParameter("basketName");

        if (name==null) name = "";
        if (basketName==null) basketName = "";
        
        if (!name.equals("")) {

            List banks = bankAccountFacade.findAll();
            for (Iterator it = banks.iterator(); it.hasNext();) {
                BankAccount elem = (BankAccount) it.next();

                if (elem.getBankName().equals(name)) {

                    List<User> userList = new ArrayList();
                    userList.add(msb.getCurrentUser());
                    elem.setListOfUsers(userList);

                    List<BankAccount> banksList = new ArrayList();
                    banksList.add(elem);
                    msb.getCurrentUser().setBankAccounts(banksList);

                    bankAccountFacade.edit(elem);
                    userFacade.edit(msb.getCurrentUser());
                    break;
                }
            }
            System.out.println("--- new account --- " + name);
            
        } else if (!basketName.equals("")) {

            List banks = basketFacade.findAll();
            for (Iterator it = banks.iterator(); it.hasNext();) {
                Basket elem = (Basket) it.next();
                if (elem.getName().equals(basketName)) {
                    elem.setUser(msb.getCurrentUser());
                    basketFacade.edit(elem);
                    msb.storeBasket(elem);
                    break;
                }
            }
            
            System.out.println("--- current basket --- " + msb.getCurrentBasket().getName() + " =?= " + basketName);
        }

        response.sendRedirect("StartPage");

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
