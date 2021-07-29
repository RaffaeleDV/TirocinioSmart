package it.unisa.login;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unisa.model.AbstractBean;
import it.unisa.model.ConvenzioneModelDM;
import it.unisa.model.TutorBean;
import it.unisa.model.TutorModelDM;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServletTutor
 */
@WebServlet("/LoginServletTutor")
public class LoginServletTutor extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  public LoginServletTutor() {
    super();
  }
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String nome = request.getParameter("login-nome");
    String pass = request.getParameter("login-password");
    String redirectPage = null;
    TutorBean tut = null;
    List<AbstractBean> convenzioni = null;

    tut = new TutorBean();
    tut.setEmail(nome);
    tut.setPass(pass);


    if (Validate.checkUser(tut)) {
      try {
        tut = (TutorBean) TutorModelDM.INSTANCE.doRetrieveTutorByEmail(tut.getEmail());
        if (tut != null) {
          if (tut.getTipo().equals("Aziendale")) {
            convenzioni = (List<AbstractBean>) ConvenzioneModelDM.INSTANCE.doRetrieveByTutorAz(tut.getID());
          } else {
            Logger.getGlobal().log(Level.SEVERE, "Convenzioni Non Trovate Per Il Tutor Aziendale Specificato.");
          }
        } else {
          RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
          view.forward(request, response);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
      request.getSession().setAttribute("Loggato", Boolean.valueOf(true));
      request.getSession().setAttribute("SessionUser", tut);
      request.getSession().setAttribute("SessionConvenzioni", convenzioni);
      request.getSession().setAttribute("ErroreLogin", Boolean.valueOf(false));
      redirectPage = "/home-page.jsp";

    } else {
      request.getSession().setAttribute("Loggato", Boolean.valueOf(false));
      request.getSession().setAttribute("ErroreLogin", Boolean.valueOf(true));
      redirectPage = "/login-page.jsp";

    }
    response.sendRedirect(request.getContextPath() + redirectPage);

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
}
