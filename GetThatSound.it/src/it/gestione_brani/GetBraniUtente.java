package it.gestione_brani;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.model.BranoManager;
import it.model.ClientBean;

@WebServlet(description = "Restituisce tutti i brani disponibili di un utente in formato JSON", urlPatterns = { "/GetBraniUtente" })
public class GetBraniUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetBraniUtente() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("utente"));
			if(id>=1) response.getWriter().println(BranoManager.getBraniUtente(id));
			if(id==0) {
				ClientBean client;
				client = (ClientBean) request.getSession().getAttribute("client");
				response.getWriter().println(BranoManager.getBraniUtente(client.getId()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().append("Error during servlet GetBraniUtente");
		}
	}
}
