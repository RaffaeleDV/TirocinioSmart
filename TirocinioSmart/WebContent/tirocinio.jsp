<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="it.unisa.model.ProgettoFormativoBean"%>
<section id="tutor-tirocinio">
    <div id="wrap-tutor-tirocinio" align="center">
    	<div style="padding:35px;">
    		<input type="button" value="Segli Tirocinio"/>
    	</div>
    	<div align="ltr">
    		<span id="wrap-tirocinio-info">
    			<p class="tirocinio-info" style="color:#2e3436;">
    			    <%
    			    	ProgettoFormativoBean progetto = (ProgettoFormativoBean)session.getAttribute("SessionProgetto");
    			    	 
    			    %>
    				Nome Tirocinio:
    				<%=  %>
    			</p>
    			<input id="vis-info-tiro" type="button" value="Visualizza Info"/>
    		</span>
    	</div>
    	<div>
        	<%@include file="tirocinio-info.jsp" %>
    	</div>
    	<div>
    	    <input id="registri-tirocinio" type="button" value="Registri" />
    	</div>
    </div>
</section>