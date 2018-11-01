<div id="nomi-registri-wrapper">
  <h1 id="registri-header">Registri Ufficio: </h1>
  <%
    for(int i = 0; i < registriBean.size(); i++) {
      RegistroBean registroBean = registriBean.get(i);
      if (registroBean != null){
  %>
        <div id="nome-registro-wrapper">
          <p class="nome-registro-info"> Nome: <%= registroBean.Nome(); %>
        </div>
  <%
      }
    }
  %>
</div>