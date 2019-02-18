package it.gestione_utenti;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.model.ClientBean;
import it.model.ClientManager;

@WebServlet(description = "Permette l'accesso all'area riservata", urlPatterns = { "/login" })
public class Login extends HttpServlet {
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

	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response)
					throws ServletException, IOException {
		String utente = (String) request.getParameter("usr");
		String password = (String) request.getParameter("pwdLogin");
		String redirect = "/home.jsp";
		HttpSession session = request.getSession();
		String id = session.getId();
		try {
			synchronized(session) {
				if(!id.equals(session.getId())){
					session.invalidate();
				}
			}
			//se il controllo mi restituisce qualcosa e client!=null setto client come attributo e si vola
			ClientBean client = ClientManager.checkOnDb(utente, password);
			if(client!=null) {
				request.getSession().setAttribute("client", client);
				response.sendRedirect(request.getContextPath() + redirect);
			}
			//per errori o altro ti fai in culo e vai alla registrazione/login
			else {
				redirect = "\\jsp\\failed_login.jsp";
				response.sendRedirect(request.getContextPath() + redirect);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			redirect = "\\jsp\\failed_login.jsp";
			response.sendRedirect(request.getContextPath() + redirect);
		}
	}
}



                                                                                                           