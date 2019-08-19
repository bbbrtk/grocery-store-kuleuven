/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.msg;

import ejb.Basket;
import ejb.BasketFacade;
import ejb.Countable;
import ejb.Item;
import ejb.ManageStatefulBean;
import ejb.Uncountable;
import ejb.User;
import ejb.UserFacade;
import ejb.enumeration.Size;
import ejb.enumeration.Unit;
import ejb.session.ManagementStatefulBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bartek
 */
@WebServlet(name = "CountableMsg", urlPatterns = {"/CountableMsg"})
public class CountableMsg extends HttpServlet {

    @Resource(mappedName = "jms/NewMessageFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/NewMessage")
    private Queue queue;

    @EJB
    private ManagementStatefulBeanLocal msb;

    @EJB
    private BasketFacade basketFacade;

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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");

        // Add the following code to send the JMS message
        String name = request.getParameter("name");
        String country = request.getParameter("country");
        String overdue = request.getParameter("overdue");
        String quantity = request.getParameter("quantity");
        String size = request.getParameter("size");
        String price = request.getParameter("price");
        String unit = request.getParameter("unit");
        String pricePerWeight = request.getParameter("pricePer");

        if ((name != null) && (country != null) && (overdue != null) && (quantity != null)) {
            try {
                Connection connection = connectionFactory.createConnection();
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                MessageProducer messageProducer = session.createProducer(queue);

                ObjectMessage message = session.createObjectMessage();

                if ((size != null) && (price != null)) {
                    Countable e = new Countable();
                    e.setName(name);
                    e.setCountry(country);

//                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//                Date date = formatter.parse(overdue);
                    Date date = (Date) request.getAttribute("overdueA");
                    e.setOverdue(date);

//                double quantityDouble = Double.parseDouble(quantity);
                    double qunatityDouble = (Double) request.getAttribute("quantityA");
                    e.setQuantity(qunatityDouble);

//                if ("BIG".equals(size)) {
//                    e.setSize(Size.BIG);
//                } else if ("MEDIUM".equals(size)) {
//                    e.setSize(Size.MEDIUM);
//                } else {
//                    e.setSize(Size.SMALL);
//                }
                    e.setSize((Size) request.getAttribute("sizeA"));

                    double priceD;
                    if (price.equals("")) {
                        priceD = 19;
                    } else {
                        priceD = Double.parseDouble(price);
                    }
                    e.setPrice(priceD);

                    e.setBasket(msb.getCurrentBasket());

                    message.setObject(e);
                    messageProducer.send(message);
                    messageProducer.close();
                    connection.close();
                    response.sendRedirect("StartPage");

                } else if ((unit != null) && (pricePerWeight != null)) {
                    Uncountable e = new Uncountable();
                    e.setName(name);
                    e.setCountry(country);

//                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//                Date date = formatter.parse(overdue);
                    Date date = (Date) request.getAttribute("overdueA");
                    e.setOverdue(date);

//                double quantityDouble = Double.parseDouble(quantity);
                    double qunatityDouble = (Double) request.getAttribute("quantityA");
                    e.setQuantity(qunatityDouble);

//                if ("BIG".equals(size)) {
//                    e.setSize(Size.BIG);
//                } else if ("MEDIUM".equals(size)) {
//                    e.setSize(Size.MEDIUM);
//                } else {
//                    e.setSize(Size.SMALL);
//                }
                    e.setUnit((Unit) request.getAttribute("unitA"));

                    double priceD2;
                    if (pricePerWeight.equals("")) {
                        priceD2 = 19;
                    } else {
                        priceD2 = Double.parseDouble(pricePerWeight);
                    }
                    e.setPricePerWeight(priceD2);

                    e.setBasket(msb.getCurrentBasket());

                    message.setObject(e);
                    messageProducer.send(message);
                    messageProducer.close();
                    connection.close();
                    response.sendRedirect("StartPage");
                }

            } catch (JMSException ex) {
                ex.printStackTrace();
            }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(CountableMsg.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(CountableMsg.class.getName()).log(Level.SEVERE, null, ex);
        }
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
