package service;

import java.io.IOException;
import java.io.PrintWriter;

import controller.ArticleDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Article;
import model.Client;

/**
 * Servlet implementation class ArticleServlet
 */
public class ArticleServlet extends jakarta.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticleDAO artmanage =new ArticleDAO();
    /**
     * Default constructor. 
     */
    public ArticleServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out =response.getWriter();
		String id = request.getParameter("id");
		String op = request.getParameter("op");
		if(op != null) {
			if(op.equals("add")) {
				RequestDispatcher rd = request.getRequestDispatcher("article.jsp"); 
				rd.forward(request, response);	
			}else if(op.equals("mod")) {
				request.setAttribute("op", "mod"); 
				request.setAttribute("data", artmanage.afficherArticleAvecId(Integer.parseInt(id))); 
				RequestDispatcher rd = request.getRequestDispatcher("article.jsp"); 
				rd.forward(request, response);	
			}else if(op.equals("del")) {
				if(artmanage.supprimeArticle(Integer.parseInt(id))) {
					request.setAttribute("data", artmanage.afficherArticles()); 
					RequestDispatcher rd = request.getRequestDispatcher("list_art.jsp"); 
					rd.forward(request, response);
				}
			}
			
		}else {
			request.setAttribute("data", artmanage.afficherArticles()); 
			RequestDispatcher rd = request.getRequestDispatcher("list_art.jsp"); 
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String op=request.getParameter("op");
		String libelle=request.getParameter("libelle");
		String categorie=request.getParameter("categorie");
		String prix=request.getParameter("prix");
		String stock=request.getParameter("stock");
		PrintWriter out =response.getWriter();
		if(op != null) {
			if(op.equals("ajouter")) {
				Article cl=new Article(libelle,categorie,Double.parseDouble(prix),Integer.parseInt(stock));
				if(artmanage.ajouterArticle(cl)) {
					response.sendRedirect(request.getContextPath() + "/ArticleServlet");
				}else {
					out.println("Ajout client pas reussi");

				}
			}else if(op.equals("modifier")) {
				String id=request.getParameter("id");
				Article cl=new Article(Integer.parseInt(id),libelle,categorie,Double.parseDouble(prix),Integer.parseInt(stock));
				if(artmanage.modifierArticle(cl)) {
					response.sendRedirect(request.getContextPath() + "/ArticleServlet");
				}else {
					out.println("Modification client pas reussi");

				}
			}
		}
	}

}
