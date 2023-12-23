package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import controller.ClientDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Client;

/**
 * Servlet implementation class ClientServlet
 */
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClientDAO mn=new ClientDAO();
    /**
     * Default constructor. 
     */
    public ClientServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out =response.getWriter();
		String id = request.getParameter("id");
		String op = request.getParameter("op");
		if(op != null) {
			if(op.equals("add")) {
				RequestDispatcher rd = request.getRequestDispatcher("Client.jsp"); 
				rd.forward(request, response);	
			}else if(op.equals("mod")) {
				request.setAttribute("op", "mod"); 
				request.setAttribute("data", mn.afficherClientsAvecId(Integer.parseInt(id))); 
				RequestDispatcher rd = request.getRequestDispatcher("Client.jsp"); 
				rd.forward(request, response);	
			}else if(op.equals("del")) {
				if(mn.supprimeClient(Integer.parseInt(id))) {
					request.setAttribute("data", mn.afficherClients()); 
					RequestDispatcher rd = request.getRequestDispatcher("list_cli.jsp"); 
					rd.forward(request, response);
				}
			}
			
		}else {
			request.setAttribute("data", mn.afficherClients()); 
			RequestDispatcher rd = request.getRequestDispatcher("list_cli.jsp"); 
			rd.forward(request, response);
		}
		 
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String op=request.getParameter("op");
		String nom=request.getParameter("nom");
		String prenom=request.getParameter("prenom");
		String tel=request.getParameter("tel");
		String adresse=request.getParameter("adresse");
		if(op != null) {
			if(op.equals("ajouter")) {
				Client cl=new Client(nom,prenom,tel,adresse);
				PrintWriter out =response.getWriter();

				if(mn.ajouterClient(cl)) {
					response.sendRedirect(request.getContextPath() + "/ClientServlet");
					//out.println("Client Ajouter avec success");
				}else {
					out.println("Ajout client pas reussi");

				}
			}else if(op.equals("modifier")) {
				String id=request.getParameter("id");
				Client cl=new Client(Integer.parseInt(id),nom,prenom,tel,adresse);
				PrintWriter out =response.getWriter();

				if(mn.modifierClient(cl)) {
					response.sendRedirect(request.getContextPath() + "/ClientServlet");
					//out.println("Client Ajouter avec success");
				}else {
					out.println("Modification client pas reussi");

				}
			}
		}
		
		
		
		

		
		
	}

}
