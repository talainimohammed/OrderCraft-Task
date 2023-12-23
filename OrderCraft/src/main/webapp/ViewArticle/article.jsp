<%@page import="model.Article"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="/Componement/Header.jsp"></jsp:include>
    <script src="js/jquery-3.7.1.js"></script>

        <section class="rightside">
            <div class="nav" style="margin-top: 10px">
                <ul class="nav nav-pills mb-3">
                    <li class="nav-item">
                      <a class="nav-link btn btn-outline-primary" style="margin-right: 10px" href="ArticleServlet?op=add"  >Nouveau Article</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link btn btn-outline-primary"   href="ArticleServlet" >Liste des Articles</a>
                    </li>
                  </ul>
            </div>
            <div>
                 <%Article cl =null; %>
            
            <% if (request.getAttribute("op")=="mod") { %>
            <% cl = (Article)request.getAttribute("data"); %>
				<h1>Modifier Article </h1>
				<% } else { %>
				<h1>Nouveau Article </h1>
				<% } %>
                <form action="ArticleServlet" method="post">
                <%if(cl==null){ %>
                	<input type="hidden" name="op" value="ajouter">
                    <label for="">libelle</label>
                    <input type="text" name="libelle" required="required"> <br><br>
                    <label for="">categorie </label>
                    <input type="text" name="categorie" required="required"><br><br>
                    <label for="">prix </label>
                    <input type="text" name="prix" required="required"><br><br>
                    <label for="">Quantite</label>
                    <input type="number" name="stock" required="required"><br><br>
                    <input type="submit" class="btn btn-success" value="Enregistrer Article">
                    <%}else{ %>
                    <input type="hidden" name="op" value="modifier">
                    <input type="hidden" name="id" value="<%=cl.getId_article()%>">
                    <label for="">libelle</label>
                    <input type="text" name="libelle" value="<%=cl.getLibelle()%>" required="required"> <br><br>
                    <label for="">categorie </label>
                    <input type="text" name="categorie" value="<%=cl.getCategorie()%>" required="required"><br><br>
                    <label for="">prix</label>
                    <input type="text" name="prix" value="<%=cl.getPrix()%>" required="required"><br><br>
                    <label for="">Quantite</label>
                    <input type="text" name="stock" value="<%=cl.getStock()%>" required="required"><br><br>
                    <input type="submit" class="btn btn-success" value="Modifier Article">
                    <%} %>
                </form>
          
              
            </div>
        </section>
    </div>
    
</body>
</html>