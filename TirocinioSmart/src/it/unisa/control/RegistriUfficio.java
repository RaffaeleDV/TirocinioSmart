package it.unisa.control;

import java.util.ArrayList;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.RegistroModelDM;
import it.unisa.model.RegistroBean;
import it.unisa.model.UfficioBean;

@WebServlet("/RegistriUfficio")
public class RegistriUfficio extends HttpServlet {

  @Override
  protected void doGet(HttpRequest request, HttpResponse response) throws ServletException, IOException, SQLException {
    HttpSession session = request.getSession(false);
    UfficioBean ufficioBean = null;
    ArrayList<RegistroBean> registriBean = null;
    Boolean login = new Boolean(false);
    int idUfficio = -1;
    
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
        idUfficio = ufficioBean.getId();
        registriBean = (ArrayList<RegistroBean>)session.getAttribute("SessionRegistri");
        if (registriBean == null) {
          if (idUfficio >= 0) {
            try {
              registriBean = RegistroModelDM.loadRegistriUfficio(idUfficio);
              session.setAttribute("SessionRegistri", registriBean);
            } catch(SQLException e) {
              //redirect to an [error] page
            }
          } else {
            //redirect to an [error] page
          }
        }
        
        if (registriBean != null) {
          RequestDispatcher view = request.getRequestDispatcher("registri-ufficio-page.jsp");
          view.forward(request, response);
        } else {
          //redirect to an [error] page
        }
      } else {
        session.setAttribute("Loggato", new Boolean(false));
        RequestDispatcher RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
        view.forward(request, response);
      }
    } else {
      RequestDispatcher RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
      view.forward(request, response);
    }
  }
}
