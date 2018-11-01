<section class="tirocini-wrapper"> 
	<h1 class="head-rows"> Tirocini Ufficio: </h1>
	<%
	TirocinioBean tirocinioBean = null;
	for(int i = 0; i < tirociniBean.size(); i++) {
	  tirocinioBean = tirociniBean.get(i);
	  if (tirocinioBean != null) {
	%>
	    <div class="row">
	      <span class="row-name"><%= tirocinioBean.getNome(); %></span>
	    </div>
	<%
	  }
	}
	%>
</section>