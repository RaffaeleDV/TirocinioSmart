<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isThreadSafe="false"
    import="it.unisa.model.RegistroBean"%>
<section id="registro-tirocinio" >
    <div id="wrap-registro-tirocinio" align="center" >
    	<div align="center" style="padding-top:50px;">
    		<h3 style="color:black;">Registro Del Tirocinio(Tutor)</h3>
    	</div>
        <div id="reg-tirocinio">
            <div id="wrap-reg-tirocinio">
            	<%
            		RegistroBean registro = (RegistroBean)session.getAttribute("SessionRegistro");
            	%>
            	<div class="wrap-reg-tirocinio-info">
	            	<p class="registro-info">Id:</p>
	                <p class="registro-info">
	                	<%= registro.getId() %>
	                </p>
            	</div>
                <div class="wrap-reg-tirocinio-info">
                	<p class="registro-info">Nome:</p>
	                <p class="registro-info">
	                	<%= registro.getNome()  %>
	            	</p>
                </div>
                <div class="wrap-reg-tirocinio-info">
                	<p class="registro-info">Descrizione</p>
	            	<p class="registro-info">
	            		<%= registro.getDescrizione() %>
	            	</p>
                </div>
            	<div class="wrap-reg-tirocinio-info">
            		<p class="registro-info">Consegna</p>
	           		<%
	           			if (registro.getConsegna()) {
	           		%>
	           				<p class="registro-info">Il Registro Delle Attività Risulta Consegnato</p>
	           		<%
	           			} else {
	           		%>
	           				<p class="registro-info">Il Registro Delle Attività Non Risulta Consegnato</p>
	           		<%
	           			}
	           		%>
            	</div>
            	<div class="wrap-reg-tirocinio-info">
            		<p class="registro-info">Conferma Del Tutor Accademico</p>
	           		<%
	           			if (registro.getConfermaTutorAcc()) {
	           	    %>
	           				<p class="registro-info">Confermato Dal Tutor Accademico</p>
	           		<%
	           			} else {
	           		%>
	           				<p class="registro-info">Non Confermato Dal Tutor Accademico</p>
	           		<%
	           			}
	           		%>
            	</div>
            	<div class="wrap-reg-tirocinio-info">
            		<p class="registro-info">Conferma Del Tutor Aziendale</p>
	           		<%
	           			if (registro.getConfermaTutorAz()) {
	           		%>
	           				<p class="registro-info">Confermato Dal Tutor Aziendale</p>
	           		<%
	           			} else {
	           		%>
	           				<p class="registro-info">Non Confermato Dal Tutor Aziendale</p>
	           		<%
	           			}
	           		%>
            	</div>
            </div>
            <input type="color" style="visibility: hidden;"/>
            <label id="wrap-ts-input-reg">
                <input class="ts-button" name="vis-reg-tirocinio"
                    type="button" value="Visualizza"/>
                <input class="ts-button" name="modifica-reg-tirocinio"
                    type="button" value="Modifica"/>
                <input class="ts-button" name="consegna-reg-tirocinio"
                    type="button" value="Consegna"/>
            </label>
        </div>
    </div>
</section>