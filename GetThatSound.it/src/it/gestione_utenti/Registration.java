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
import it.utility.PasswordAuthentication;

@WebServlet(description = "Permette l'iscrizione al sito", urlPatterns = { "/Registration" })
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response)
					throws ServletException, IOException {
		String nome = (String) request.getParameter("nome");
		String cognome = (String) request.getParameter("cognome");
		String email = (String) request.getParameter("email");
		String utente = (String) request.getParameter("utente");
		String password = (String) request.getParameter("pwd");
		String ruolo = "standard";
		
		ClientBean cb = new ClientBean(nome, cognome, email, utente, ruolo);
		String redirect = "/home.jsp";
		HttpSession session = request.getSession();
		if(session.getAttribute("client")!=null) {
			response.sendRedirect(request.getContextPath() + redirect);
		}
		try {
			if(!(ClientManager.isUtente(utente) || ClientManager.isUtenteEmail(utente))) {
				PasswordAuthentication pa = new PasswordAuthentication();
				if(ClientManager.registraUtente(cb, pa.hash(password))) {
					ClientBean client = ClientManager.checkOnDb(utente, password);
					request.getSession().setAttribute("client", client);
					response.sendRedirect(request.getContextPath() + redirect);
				}
			}
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



                                                                                                           