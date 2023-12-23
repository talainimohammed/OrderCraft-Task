<%@page import="model.Client"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

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
	                  <a class="nav-link"   href="ClientServlet?op=add" >Nouveau Client</a>
	                </li>
	                <li class="nav-item">
	                  <a class="nav-link"   href="ClientServlet">Liste des Clients</a>
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
                    <input type="text" name="nom" > <br><br>
                    <label for="">Prenom </label>
                    <input type="text" name="prenom"><br><br>
                    <label for="">Tel</label>
                    <input type="text" name="tel"><br><br>
                    <label for="">Adresse</label>
                    <input type="text" name="adresse"><br><br>
                    <input type="submit" value="Enregistrer Client">
                    <%}else{ %>
                    <input type="hidden" name="op" value="modifier">
                    <input type="hidden" name="id" value="<%=cl.getId_client()%>">
                    <label for="">Nom Client</label>
                    <input type="text" name="nom" value="<%=cl.getNom()%>"> <br><br>
                    <label for="">Prenom </label>
                    <input type="text" name="prenom" value="<%=cl.getPrenom()%>"><br><br>
                    <label for="">Tel</label>
                    <input type="text" name="tel" value="<%=cl.getTel()%>"><br><br>
                    <label for="">Adresse</label>
                    <input type="text" name="adresse" value="<%=cl.getAdresse()%>"><br><br>
                    <input type="submit" value="Modifier Client">
                    <%} %>
                </form>
            </div>
        </section>
    </div>
    
</body>
</html>