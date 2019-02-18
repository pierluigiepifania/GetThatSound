package it.gestione_utenti;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.model.ClientManager;

@WebServlet(description = "Restituisce la lista degli utenti registrati", urlPatterns = { "/CheckEmailMatch" })
public class CheckEmailMatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String email = (String) request.getParameter("email");
			if(ClientManager.isUtenteEmail(email)) {
				response.getWriter().println("{\"esito\": \"1\"}");
			}
			else {
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("Ci sono stati errori duranto il processo, ripeti");
		}
	}
}



                                                                                                           