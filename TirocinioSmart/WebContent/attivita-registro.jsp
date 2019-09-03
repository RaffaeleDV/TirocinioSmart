<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List"
    import="it.unisa.model.AbstractBean"
    import="it.unisa.model.AttivitaTirocinioBean"
    import="it.unisa.model.AttivitaTirocinioModelDM"
    import="it.unisa.model.RegistroBean"
    import="it.unisa.model.RegistroModelDM"
    import="it.unisa.model.StrutturaOspitanteBean"
    import="it.unisa.model.StrutturaOspitanteModelDM"
    import="java.util.logging.Logger"
    import="java.util.logging.Level"%>
<section id="attivita-registro-wrapper" class="wrapper">
  <!-- (List<AbstractBean>) AttivitaTirocinioModelDM.INSTANCE.doRetrieveByRegistro(registroBean.getID()) -->
  <div>
    <img id="attivita-registro-heading-icon" class="icon"/>
    <h3 style="border-radius: 10px; margin: 100px; background-color: #05003F; border: 1px #ffffff solid; font-weight: xx-large; padding: 15px; font-size: 28px; color: white;" align="center">Attivita Del Registro</h3>
  </div>
  <%
    List<AbstractBean> attivitaTirocinioList = (List<AbstractBean>) session.getAttribute("SessionAttivitaTirocinioList");
    RegistroBean registroAttivita = null;
    StrutturaOspitanteBean strutturaAttivita = null;
  
    if (attivitaTirocinioList != null && attivitaTirocinioList.size() > 0) {
    	%>
        <table id="attivita-table">
          <thead id="attivita-head">
            <tr>
              <th id="hattivita-id">ID</th>
              <th id="hattivita-registro">Nome Registro</th>
              <th id="hattivita-struttura">Nome Struttura</th>
              <th id="hattivita-descrizione">Descrizione</th>
              <th id="hattivita-data">Data</th>
              <th id="hattivita-ore">Ore</th>
            </tr>
          </thead>
          <tbody>
    	<%
      for (AbstractBean product: attivitaTirocinioList) {
    	  AttivitaTirocinioBean attivitaTirocinioBean = (AttivitaTirocinioBean) product;
        if (attivitaTirocinioBean != null) {
          registroAttivita = (RegistroBean) AttivitaTirocinioModelDM.INSTANCE.doRetrieveRegistro(attivitaTirocinioBean);
          strutturaAttivita = (StrutturaOspitanteBean) AttivitaTirocinioModelDM.INSTANCE.doRetrieveStrutturaOspitante(attivitaTirocinioBean);
          %>
            <tr>
              <td id="attivita-data" class="info"><%= attivitaTirocinioBean.getID() %></td>
              <td id="attivita-data" class="info"><%= registroAttivita.getNome() %></td>
              <td id="attivita-data" class="info"><%= strutturaAttivita.getNome() %></td>
              <td id="attivita-data" class="info"><%= attivitaTirocinioBean.getDescrizione() %></td>
              <td id="attivita-data" class="info"><%= attivitaTirocinioBean.getData() %></td>
              <td id="attivita-data" class="info"><%= attivitaTirocinioBean.getOre() %></td>
            </tr>
          <%
        }
      }
    	%>
          </tbody>
        </table>
    <%
    } else {
      %>
        <div id="warning-wrapper" class="wrapper">
          <h2>Nessuna Attivita Trovata</h2>
        </div>
      <%
    }
  %>
</section>