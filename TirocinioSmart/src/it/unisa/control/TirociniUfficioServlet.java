package it.unisa.control;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import it.unisa.model.ProgettoFormativoModelDM;
import it.unisa.model.UfficioBean;
import it.unisa.model.AbstractBean;

/**
 * Servlet implementation class TirociniUfficioServlet
 */
@WebServlet("/TirociniUfficioServlet")
public class TirociniUfficioServlet extends HttpServlet {

  private static final ProgettoFormativoModelDM progettoFormativoModelDM = new ProgettoFormativoModelDM();
  
  /**
   * @see HttpServlet#HttpServlet()
   */
  public TirociniUfficioServlet() {
    super();
  }
  
  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    HttpSession session = request.getSession(false);
    UfficioBean ufficioBean = null;
    List<AbstractBean> tirociniBean = null;
    
    if (session != null) {
      ufficioBean = (UfficioBean)session.getAttribute("SessionUfficio");
      if (ufficioBean != null) {
        tirociniBean = (List<AbstractBean>) session.getAttribute("SessionTirocini");
        if (tirociniBean == null) {
          try {
            tirociniBean = (List<AbstractBean>) progettoFormativoModelDM.doRetrieveByUfficio(ufficioBean.getID());
          } catch(SQLException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage());
            //redirect to an [error] page
          }
        }
        
      } else {
        Logger.getGlobal().log(Level.INFO, "L' ufficio non risulta loggato");
        RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
        view.forward(request, response);
        return;
      }
    } else {
      Logger.getGlobal().log(Level.INFO, "Nessuna session nella ricerca di tirocini da parte dell' uffiio");
      RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
      view.forward(request, response);
      return;
    }
  }
  
  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    doGet(request, response);
  }
}
