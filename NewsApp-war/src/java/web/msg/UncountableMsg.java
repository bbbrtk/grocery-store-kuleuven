/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.msg;

import ejb.Countable;
import ejb.Uncountable;
import ejb.enumeration.Size;
import ejb.enumeration.Unit;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
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
@WebServlet(name = "UncountableMsg", urlPatterns = {"/UncountableMsg"})
public class UncountableMsg extends HttpServlet {

    @Resource(mappedName = "jms/NewMessageFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/NewMessage")
    private Queue queue;

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
        String unit = request.getParameter("unit");
        String pricePerWeight = request.getParameter("pricePerWeight");

        if ((name != null) && (country != null) && (overdue != null)&& (quantity != null)&& (unit != null)&& (pricePerWeight != null)) {
            try {
                Connection connection = connectionFactory.createConnection();
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                MessageProducer messageProducer = session.createProducer(queue);

                ObjectMessage message = session.createObjectMessage();

                Uncountable e = new Uncountable();
                e.setName(name);
                e.setCountry(country);
                
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date date = formatter.parse(overdue);
                e.setOverdue(date);
                
                double quantityDouble = Double.parseDouble(quantity);
                e.setQuantity(quantityDouble);
                
                if (unit == "KG") e.setUnit(Unit.KG);
                else if (unit == "DAG") e.setUnit(Unit.DAG);
                else e.setUnit(Unit.POUND);
                
                double priceDouble = Double.parseDouble(pricePerWeight);
                e.setPricePerWeight(priceDouble);

                message.setObject(e);
                messageProducer.send(message);
                messageProducer.close();
                connection.close();
                response.sendRedirect("ListNews");

            } catch (JMSException ex) {
                ex.printStackTrace();
            }
        }
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CountableMsg</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CountableMsg at " + request.getContextPath() + "</h1>");
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