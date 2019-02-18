package it.gestione_utenti;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.model.ClientBean;
import it.model.ClientManager;
import it.utility.PasswordAuthentication;

@WebServlet(description = "Permette la creazione di un manager", urlPatterns = { "/CreaManager" })
public class CreaManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response)
					throws ServletException, IOException {
		ClientBean cb_req = ((ClientBean) request.getSession().getAttribute("client"));
		if((cb_req.getRuolo()).equals("admin")) {
			String nome = (String) request.getParameter("nome");
			String cognome = (String) request.getParameter("cognome");
			String email = (String) request.getParameter("email");
			String utente = (String) request.getParameter("utente");
			String password = (String) request.getParameter("pwd");
			String ruolo = "manager";
			
			ClientBean cb = new ClientBean(nome, cognome, email, utente, ruolo);
			String redirect = "/home.jsp";			
			try {
				PasswordAuthentication pa = new PasswordAuthentication();
				if(ClientManager.registraUtente(cb, pa.hash(password))) {
					ClientBean client = ClientManager.checkOnDb(utente, password);
					request.getSession().setAttribute("client", client);
					response.getWriter().println("Creazione avvenuta con successo");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				redirect = "\\error.jsp";
				response.sendRedirect(request.getContextPath() + redirect);
			}
		}
	}
}



                                                                                                           