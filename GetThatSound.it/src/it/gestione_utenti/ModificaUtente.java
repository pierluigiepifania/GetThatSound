package it.gestione_utenti;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.model.ClientBean;
import it.model.ClientManager;

@WebServlet(description = "Permette all'admin di modificare il ruolo di un utente", urlPatterns = { "/ModificaUtente" })
public class ModificaUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ClientBean cb = (ClientBean) request.getSession().getAttribute("client");
			if(cb.getRuolo().equals("admin")) {
				int id = Integer.parseInt(request.getParameter("id"));
				String ruolo = (String) request.getParameter("ruolo");
				switch(ruolo) {
					case("manager"): {
						ClientManager.setClientTo(id, 2);
						break;
					}
					case("standard"): {
						ClientManager.setClientTo(id, 1);
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("Ci sono stati errori duranto il processo, ripeti");
		}
	}
}



                                                                                                           