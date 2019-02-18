package it.gestione_utenti;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Logout
 */
@WebServlet(description = "Permette l'accesso all'area riservata", urlPatterns = { "/logout" })
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * @precondition 	La richiesta è sincrona.
	 * 					userid != null, passid != null, l'username è presente nel database e la password corrisponde a quella associata all'username nel database.
	 * @postcondition	L'utente è loggato.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */

	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response)
					throws ServletException, IOException {
			request.removeAttribute("client");
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath()+"/home.jsp");
	}
}
