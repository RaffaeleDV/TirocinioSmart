package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import it.unisa.model.StudenteBean;
import it.unisa.model.StudenteModelDM;
import it.unisa.model.UfficioBean;
import it.unisa.model.UfficioModelDM;
import it.unisa.model.RegistroBean;
import it.unisa.model.RegistroModelDM;
import it.unisa.model.ProgettoFormativoBean;
import it.unisa.model.ProgettoFormativoModelDM;

/**
 * Servlet implementation class TirocinioStudenteServlet
 */
@WebServlet("/TirocinioStudenteServlet")
public class TirocinioStudenteServlet extends HttpServlet {

  private static final ProgettoFormativoModelDM progettoFormativoModelDM = new ProgettoFormativoModelDM();
  private static final RegistroModelDM registroModelDM = new RegistroModelDM();
  private static final UfficioModelDM ufficioModelDM = new UfficioModelDM();
  
  /**
   * @see HttpServlet#HttpServlet()
   */
  public TirocinioStudenteServlet() {
    super();
  }
  
  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    HttpSession session = request.getSession(false);
    StudenteBean studenteBean = null;
    ProgettoFormativoBean tirocinioBean = null;
    UfficioBean ufficioBean = null;
    RegistroBean registroBean = null;
    ProgettoFormativoBean progettoBean = null;
    String id = (String)request.getParameter("id").toString();
    int idTirocinio = -1;
    
    if (id != null) {
      idTirocinio = Integer.valueOf(id);
      if (idTirocinio < 0) {
        //redirect to an [error] page
      }
    }
    
    if (session != null) {
      studenteBean = (StudenteBean)session.getAttribute("SessionStudente");
      if (studenteBean != null) {
        tirocinioBean = (ProgettoFormativoBean) session.getAttribute("SessionTirocinio");
        if (tirocinioBean == null) {
          try {
            if (progettoFormativoModelDM.isStudenteProgettoFormativo(studenteBean.getID(), idTirocinio)) {
              tirocinioBean = (ProgettoFormativoBean) progettoFormativoModelDM.doRetrieveByKey(idTirocinio);
              session.setAttribute("SessionTirocinio", tirocinioBean);
            } else {
              //redirect to an [error] page
            }
          } catch(SQLException e) {
            //redirect to an [error] page
          }
        } else {
          if (tirocinioBean.getID() != idTirocinio) {
            try {
              if (progettoFormativoModelDM.isStudenteProgettoFormativo(studenteBean.getID(), idTirocinio)) {
                tirocinioBean = (ProgettoFormativoBean) progettoFormativoModelDM.doRetrieveByKey(idTirocinio);
                session.setAttribute("SessionTirocinio", tirocinioBean);
              }else {
                //redirect to an [error] page
              }
            } catch(SQLException e) {
              //redirect to an [error] page
            }
          }
        }
        
        if (tirocinioBean != null) {
          try {
            ufficioBean = (UfficioBean) ufficioModelDM.doRetrieveByProgettoFormativo(tirocinioBean.getID());
            registroBean = (RegistroBean) registroModelDM.doRetrieveByProgettoFormativo(tirocinioBean.getID());
          } catch (SQLException e) {
            //redirect to an [error] page
            e.printStackTrace();
          }
          //progettoBean = TirocinioModelDM.loadProgettoFormativo(tirocinioBean);
        } else {
          //redirect to an [error] page
        }
        
        if (ufficioBean != null && registroBean != null && progettoBean != null) {
          RequestDispatcher view = request.getRequestDispatcher("tirocinio-page.jsp");
          view.forward(request, response);
        } else {
          //redirect to an [error] page
        }
      } else {
        RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
        view.forward(request, response);
        return;
      }
    } else {
      RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
      view.forward(request, response);
      return;
    }
    //redirect to an [error] page
  }
  
  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    doGet(request, response);
  }
}
