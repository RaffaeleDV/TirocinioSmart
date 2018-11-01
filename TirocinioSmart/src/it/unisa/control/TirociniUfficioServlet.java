package it.unisa.control;

import java.util.ArrayList;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.TirocinioModelDM;
import it.unisa.model.UfficioModelDM;
import it.unisa.model.TirocinioBean;
import it.unisa.model.UfficioBean;

@WebServlet("/TirociniUfficioServlet")
public class TirociniUfficioServlet extends HttpServlet {

  @Override
  protected void doGet(HttpRequest request, HttpResponse response) throws SQLException, IOException, ServletException {
    HttpSession session = request.getSession(false);
    UfficioBean ufficioBean = null;
    ArrayList<TirocinioBean> tirociniBean = null;
    Boolean login = new Boolean(false);
    
    if (session != null) {
      ufficioBean = (UfficioBean)session.getAttribute("SessionUfficio");
      login = (Boolean)session.getAttribute("Loggato");
      
      if (login != null) {
        if (login != new Boolean(true)) {
          RequestDispatcher RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
          view.forward(request, response);
          return;
        }
      } else {
        RequestDispatcher RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
        view.forward(request, response);
        return;
      }
      
      if (ufficioBean != null) {
        tirociniBean = (ArrayList<TirocinioBean>)session.getAttribute("SessionTirocini");
        if (tirociniBean == null) {
          try {
            tirociniBean = TirocinioModelDM.loadTirociniUfficio(ufficioBean);
          } catch(SQLException e) {
            //redirect to an [error] page
          }
        }
        
        if (login == new Boolean(true) && tirociniBean != null) {
          RequestDispatcher view = request.getRequestDispatcher("tirocini-ufficio-page.jsp");
          view.forward(request, response);
        } else {
          //redirect to an [error] page
        }
      } else {
        session.setAttribute("Loggato", new Boolean(false));
        RequestDispatcher RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
        view.forward(request, response);
        return;
      }
    } else {
      RequestDispatcher RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
      view.forward(request, response);
      return;
    }
  }
  
  @Override
  protected void doPost(HttpRequest request, HttpResponse response) throws SQLException, IOException, ServletException {
    doGet(request, response);
  }
}
