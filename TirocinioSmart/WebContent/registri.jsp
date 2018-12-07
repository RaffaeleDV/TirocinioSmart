<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import="java.util.*" 
    import="it.unisa.model.RegistroBean" %>
<div id="nomi-registri-wrapper">
  <h1 id="registri-header">Registri Ufficio: </h1>
  <%
  	ArrayList<RegistroBean> regs = (ArrayList<RegistroBean>)session.getAttribute("SessionRegistri");
  %>
  <%
    for(int i = 0; i < regs.size(); i++) {
      RegistroBean registroBean = regs.get(i);
      if (registroBean != null){
  %>
        <div id="nome-registro-wrapper">
          <p class="nome-registro-info"> Nome: <%= registroBean.getNome() %>
        </div>
  <%
      }
    }
  %>
</div>