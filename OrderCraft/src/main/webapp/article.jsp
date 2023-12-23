<%@page import="model.Article"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<script src="js/jquery-3.7.1.js" type="text/javascript"></script>

</head>
<body>
  <div class="container">
    <div class="row">
                <div class="col-4">
                   <div class="list-group" >
                    <a class="list-group-item list-group-item-action test"   href="CommandeServlet"  >Gestion des Commandes</a>
                    <a class="list-group-item list-group-item-action"   href="ClientServlet"  >Gestion des Client</a>
                    <a class="list-group-item list-group-item-action"  href="ArticleServlet"  >Gestion des Articles</a>
                  </div>
                </div>
            </div>
        <section class="rightside">
            <div class="nav">
                <ul class="nav nav-pills mb-3">
                    <li class="nav-item">
                      <a class="nav-link " href="ArticleServlet?op=add"  >Nouveau Article</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link"  href="ArticleServlet" >Liste des Articles</a>
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
                    <input type="text" name="libelle"> <br><br>
                    <label for="">categorie </label>
                    <input type="text" name="categorie"><br><br>
                    <label for="">prix </label>
                    <input type="text" name="prix"><br><br>
                    <label for="">Quantite</label>
                    <input type="number" name="stock"><br><br>
                    <input type="submit" value="Enregistrer Article">
                    <%}else{ %>
                    <input type="hidden" name="op" value="modifier">
                    <input type="hidden" name="id" value="<%=cl.getId_article()%>">
                    <label for="">libelle</label>
                    <input type="text" name="libelle" value="<%=cl.getLibelle()%>"> <br><br>
                    <label for="">categorie </label>
                    <input type="text" name="categorie" value="<%=cl.getCategorie()%>"><br><br>
                    <label for="">prix</label>
                    <input type="text" name="prix" value="<%=cl.getPrix()%>"><br><br>
                    <label for="">Quantite</label>
                    <input type="text" name="stock" value="<%=cl.getStock()%>"><br><br>
                    <input type="submit" value="Modifier Article">
                    <%} %>
                </form>
          
              
            </div>
        </section>
    </div>
    
</body>
</html>