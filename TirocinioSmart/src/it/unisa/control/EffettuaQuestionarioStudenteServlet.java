package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import it.unisa.model.QuestionarioBean;
import it.unisa.model.QuestionarioModelDM;
import it.unisa.model.StudenteBean;
import it.unisa.model.AbstractBean;
import it.unisa.model.AnswerBean;
import it.unisa.model.ChooseBean;
import it.unisa.model.ChooseModelDM;
import it.unisa.model.QuestionBean;
import it.unisa.model.QuestionModelDM;

/**
 * Servlet implementation class EffettuaQuestionarioStudenteServlet
 */
@WebServlet("/EffettuaQuestionarioStudenteServlet")
public class EffettuaQuestionarioStudenteServlet extends HttpServlet {
  
  private static final QuestionModelDM questionModelDM = new QuestionModelDM();
  private static final ChooseModelDM chooseModelDM = new ChooseModelDM();
  private static final QuestionarioModelDM questionarioModelDM = new QuestionarioModelDM();
  
  /**
   * @see HttpServlet#HttpServlet()
   */
  public EffettuaQuestionarioStudenteServlet() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = null;
    String questionarioNome = null;
    StudenteBean studenteBean = null;
    QuestionarioBean questionarioBean = null;
    HashMap<AbstractBean, List<AbstractBean>> questChoosesMap =
        new HashMap<AbstractBean, List<AbstractBean>>();
    
    getServletContext().setAttribute("ContextQuestionarioModelDM", questionarioModelDM);
    getServletContext().setAttribute("ContextQuestionModelDM", questionModelDM);
    getServletContext().setAttribute("ContextChooseModelDM", chooseModelDM);
    
    session = request.getSession(false);
    if (session != null) {
      Object user = session.getAttribute("SessionUser");
      
      if (user != null) {
        if (!user.getClass().getName().equals(StudenteBean.class.getName())) {
          Logger.getGlobal().log(Level.INFO, "L' utente non risulta loggato come studente");
          RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
          view.forward(request, response);
        } else {
          studenteBean = (StudenteBean) user;
        }
      } else {
        Logger.getGlobal().log(Level.INFO, "Nessun utente loggato");
        RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
        view.forward(request, response);
      }
      
      questionarioNome = (String) request.getParameter("questionario").toString();
      if (questionarioNome != null) {
        if (!questionarioNome.equals("")) {
          try {
            questionarioBean = (QuestionarioBean) questionarioModelDM.doRetrieveByNome(questionarioNome);
          } catch (SQLException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage());
            //redirect to an [error] page
            //set del valore per SessionErrorMessage500
            RequestDispatcher disp = request.getRequestDispatcher("500-page.jsp");
            disp.forward(request, response);
          }
        } else {
          Logger.getGlobal().log(Level.SEVERE, "Ricevuto un nome di questionario come stringa vuota");
          //redirect to an [error] page
          //set del valore per SessionErrorMessage404
          RequestDispatcher disp = request.getRequestDispatcher("404-page.jsp");
          disp.forward(request, response);
        }
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Nessun nome di questionario specificato, per poter effettuare un questionario");
        //redirect to an [error] page
        //set del valore per SessionErrorMessage404
        RequestDispatcher disp = request.getRequestDispatcher("404-page.jsp");
        disp.forward(request, response);
      }
      
      List<AbstractBean> questions = null;
      if (questionarioBean != null) {
        try {
          questions = (List<AbstractBean>) questionModelDM.doRetrieveByQuestionario(questionarioBean.getID());
        } catch(SQLException e) {
          Logger.getGlobal().log(Level.SEVERE, e.getMessage());
          //redirect to an [error] page
          //set del valore per SessionErrorMessage500
          RequestDispatcher view = request.getRequestDispatcher("500-page.jsp");
          view.forward(request, response);
        }
      } else {
        Logger.getGlobal().log(Level.SEVERE, "Nessun questionario trovato");
        //redirect to an [error] page
        //set del valore per SessionErrorMessage404
        RequestDispatcher disp = request.getRequestDispatcher("404-page.jsp");
        disp.forward(request, response);
      }
      
      for (AbstractBean product: questions) {
        QuestionBean questionBean = null;
        if (product instanceof QuestionBean) {
          questionBean = (QuestionBean) product;
        } else {
          //redirect to an [error] page
          //set del valore per SessionErrorMessage500
          RequestDispatcher view = request.getRequestDispatcher("500-page.jsp");
          view.forward(request, response);
        }
        
        List<AbstractBean> questionChooses = null;
        if (questionBean != null) {
          try {
            questionChooses = (List<AbstractBean>) chooseModelDM.doRetrieveByQuestion(questionBean.getID());
            if (questionChooses != null) {
              questChoosesMap.put(questionBean, questionChooses);
            }
          } catch(SQLException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage());
            //redirect to an [error] page
            //set del valore per SessionErrorMessage500
            RequestDispatcher disp = request.getRequestDispatcher("500-page.jsp");
            disp.forward(request, response);
          }
        } else {
          //redirect to an [error] page
          //set del valore per SessionErrorMessage404
          RequestDispatcher view = request.getRequestDispatcher("404-page.jsp");
          view.forward(request, response);
        }
      }
      
      session.setAttribute("SessionQuestionChoosesMap", questChoosesMap);
      
    } else {
      Logger.getGlobal().log(Level.INFO, "Nessuna sessione nello svolgimento di un questionario da parte di uno studente");
      RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
      view.forward(request, response);
    }
    
    //RequestDispatcher view = request.getRequestDispatcher("questionario-page.jsp");
    //view.forward(request, response);
    Logger.getGlobal().log(Level.INFO, questChoosesMap.toString());
    response.sendRedirect(request.getContextPath() + "/questionario-page.jsp");
  }
}
