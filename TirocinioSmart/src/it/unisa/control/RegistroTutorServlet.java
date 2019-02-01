package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import it.unisa.model.RegistroBean;
import it.unisa.model.RegistroModelDM;
import it.unisa.model.StudenteBean;
import it.unisa.model.TutorBean;
import it.unisa.model.UfficioBean;

/**
 * Servlet implementation class RegistroServlet
 */
@WebServlet("/RegistroTutorServlet")
public class RegistroTutorServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  /**
   * * @see HttpServlet#HttpServlet()
   * */

  public RegistroTutorServlet() {
    super();
  }
  
  /**
   * * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   * */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }

  /**
   * * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   * */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    Object user = session.getAttribute("SessionUser");
    List<RegistroBean> regs = null;
    RegistroBean reg = null;
    int idReg = Integer.parseInt(request.getParameter("id"));
    boolean trovatoRegs = false;
    
    if (session != null) {
      if (user != null) {
        if (user instanceof TutorBean) {
          TutorBean tutor = (TutorBean) user;
          regs = (List<RegistroBean>) session.getAttribute("SessionRegistriTutor");
          if (regs != null) {
            for (RegistroBean registro: regs) {
              if (registro.getId() == idReg) {
                trovatoRegs = true;
                reg = registro;
              }
            }
          }
          
          if (!trovatoRegs) {
            try {
              if (tutor.getTipo().equals("Accademico")) {
                if (RegistroModelDM.tutorRegistroAcc(tutor.getId(), idReg)) {
                  reg = RegistroModelDM.loadRegistro(idReg);
                }
              } else if (tutor.getTipo().equals("Aziendale")) {
                if (RegistroModelDM.tutorRegistroAz(tutor.getId(), idReg)) {
                  reg = RegistroModelDM.loadRegistro(idReg);
                }
              }
            } catch(SQLException e) {
              Logger.getGlobal().log(Level.SEVERE, e.getMessage());
            }
          }
        } else {
          //redirect to an [error] page
        }
        
        session.setAttribute("SessionRegistro", reg);
      } else {
        //redirect to an [error] page
      }
    }
    
    Logger.getGlobal().log(Level.INFO, "Registro Da Visualizzare: " + reg.toString());
    RequestDispatcher view = request.getRequestDispatcher("registro-page.jsp");
    view.forward(request, response);
  }
}