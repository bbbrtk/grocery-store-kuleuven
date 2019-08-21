/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.Countable;
import ejb.CountableFacade;
import ejb.Item;
import ejb.ItemFacade;
import ejb.UncountableFacade;
import ejb.UserFacade;
import ejb.interceptor.SingletonSessionStateLocal;
import ejb.session.ManagementStatefulBeanLocal;
import ejb.timer.UserLogoutTimerLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bartek
 */
@WebServlet(name = "AllItemShow", urlPatterns = {"/AllItemShow"})
public class AllItemShow extends HttpServlet {

    @EJB
    private UserLogoutTimerLocal userLogoutTimer;

    @EJB
    private ManagementStatefulBeanLocal msb;

    @EJB
    private SingletonSessionStateLocal sssl;

    @EJB
    private UserFacade userFacade;

    @EJB
    private ItemFacade itemFacade;

    @EJB
    private CountableFacade countableFacade;

    @EJB
    private UncountableFacade uncountableFacade;

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

        if (msb.getCurrentUser() == null) {
            RequestDispatcher view = request.getRequestDispatcher("login/newlogin.html");
            view.forward(request, response);
        } else {

            List<Item> itemList = itemFacade.findAll();
            List<String> itemName = new ArrayList();
            List<Double> itemQuantity = new ArrayList();
            List<Date> itemDate = new ArrayList();
            List<String> itemCountry = new ArrayList();
            List<Double> itemPrice = new ArrayList();
            List<String> itemUnit = new ArrayList();

            for (Item item : itemList) {
                itemName.add(item.getName());
                itemQuantity.add(item.getQuantity());
                itemDate.add(item.getOverdue());
                itemCountry.add(item.getCountry());

                if (new Countable().getClass() == item.getClass()) {
                    itemPrice.add(countableFacade.find(item.getId()).getPrice());
                    itemUnit.add(countableFacade.find(item.getId()).getSize().toString());
                } else {
                    itemPrice.add(uncountableFacade.find(item.getId()).getPricePerWeight());
                    itemUnit.add(uncountableFacade.find(item.getId()).getUnit().toString());
                }
//                itemUnit.add(item.getOverdue());

            }

            request.setAttribute("userLogin", msb.getCurrentUser().getLogin());
            request.setAttribute("userId", msb.getCurrentUser().getId());
            request.setAttribute("timerStatus", userLogoutTimer.getCounter());

            request.setAttribute("itemList", itemFacade.findAll());//.allItems()); //.findAll()); //.myItemsName(msb.getCurrentUser().getId()));
            request.setAttribute("itemName", itemName);
            request.setAttribute("itemCountry", itemCountry);
            request.setAttribute("itemQuantity", itemQuantity);
            request.setAttribute("itemDate", itemDate);
            request.setAttribute("itemPrice", itemPrice);
            request.setAttribute("itemUnit", itemUnit);

            request.setAttribute("tasks", sssl.getActions());

//            System.out.println("--- my baskets: " + userFacade.myBankAccounts(msb.getCurrentUser().getId()) );
            RequestDispatcher view = request.getRequestDispatcher("show/allItem.jsp");
            view.forward(request, response);
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
