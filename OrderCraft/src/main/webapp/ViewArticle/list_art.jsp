<%@page import="model.Article"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="/Componement/Header.jsp"></jsp:include>

        <section class="rightside">
            <div class="nav" style="margin-top: 10px">
              <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
                <li class="nav-item">
                  <a class="nav-link btn btn-outline-primary" style="margin-right: 10px" href="ArticleServlet?op=add"  >Nouveau Article</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link btn btn-outline-primary"  href="ArticleServlet" >Liste des Articles</a>
                </li>
              </ul>
            </div>
            <div>
                <h1>Liste des Articles </h1>
                <input type="text" id="SearchArticle" onkeyup="myFunction()" placeholder="Recherche Par Libelle Article" title="Taper Libelle" style="width: 500px;">
                
                <table class="table" id="Table">
                  <tr>
                    <th scope="col">Libelle</th><th scope="col">Categorie</th><th scope="col">Prix</th><th scope="col">Stock</th><th scope="col"></th><th scope="col"></th>
                  </tr>
                  </tr>
                  <%ArrayList<Article> std =  (ArrayList<Article>)request.getAttribute("data"); 
			        for(Article s:std){%> 
			        <%-- Arranging data in tabular form 
			        --%> 
			            <tr> 
			                <td><%=s.getLibelle()%></td> 
			                <td><%=s.getCategorie()%></td> 
			                <td><%=s.getPrix()%></td> 
			                <td><%=s.getStock()%></td> 
			                <td><a class="btn btn-danger" href="ArticleServlet?op=del&id=<%=s.getId_article()%>">Supprimer</a></td>
			           		<td><a class="btn btn-primary" href="ArticleServlet?op=mod&id=<%=s.getId_article()%>">Modifier</a></td>
			           
			            </tr> 
			            <%}%> 
                  </tr>
                </table>
            </div>
        </section>
    </div>
    <script>
function myFunction() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("SearchArticle");
  filter = input.value.toUpperCase();
  table = document.getElementById("Table");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[0];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }       
  }
}
</script>   
</body>
</html>