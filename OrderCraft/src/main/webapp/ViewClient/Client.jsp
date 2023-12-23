<%@page import="model.Client"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/Componement/Header.jsp"></jsp:include>
    <script src="js/jquery-3.7.1.js"></script>

        <section class="rightside">
            <div class="nav" style="margin-top: 10px">
                <ul class="nav nav-pills mb-3">
                   <li class="nav-item">
	                  <a class="nav-link btn btn-outline-primary" style="margin-right: 10px"   href="ClientServlet?op=add" >Nouveau Client</a>
	                </li>
	                <li class="nav-item">
	                  <a class="nav-link btn btn-outline-primary"   href="ClientServlet">Liste des Clients</a>
	                </li>
                  </ul>
            </div>
            <div>
                        <%Client cl =null; %>
            
            <% if (request.getAttribute("op")=="mod") { %>
            <% cl = (Client)request.getAttribute("data"); %>
				<h1>Modifier Client </h1>
				<% } else { %>
				<h1>Nouveau Client </h1>
				<% } %>
                <form action="ClientServlet" method="post">
                <%if(cl==null){ %>
                	<input type="hidden" name="op" value="ajouter">
                    <label for="">Nom Client</label>
                    <input type="text" name="nom" required="required" > <br><br>
                    <label for="">Prenom </label>
                    <input type="text" name="prenom" required="required"><br><br>
                    <label for="">Tel</label>
                    <input type="text" name="tel" required="required"><br><br>
                    <label for="">Adresse</label>
                    <input type="text" name="adresse" required="required"><br><br>
                    <input type="submit" class="btn btn-success" value="Enregistrer Client">
                    <%}else{ %>
                    <input type="hidden" name="op" value="modifier">
                    <input type="hidden" name="id" value="<%=cl.getId_client()%>">
                    <label for="">Nom Client</label>
                    <input type="text" name="nom" value="<%=cl.getNom()%>" required="required"> <br><br>
                    <label for="">Prenom </label>
                    <input type="text" name="prenom" value="<%=cl.getPrenom()%>" required="required"><br><br>
                    <label for="">Tel</label>
                    <input type="text" name="tel" value="<%=cl.getTel()%>" required="required"><br><br>
                    <label for="">Adresse</label>
                    <input type="text" name="adresse" value="<%=cl.getAdresse()%>" required="required"><br><br>
                    <input type="submit" class="btn btn-success" value="Modifier Client">
                    <%} %>
                </form>
            </div>
        </section>
    </div>
    
</body>
</html>