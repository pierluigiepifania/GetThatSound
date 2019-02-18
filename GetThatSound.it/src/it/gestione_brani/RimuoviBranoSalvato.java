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

@WebServlet(description = "Rimuovi un brano salvato", urlPatterns = { "/RimuoviBranoSalvato" })
public class RimuoviBranoSalvato extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RimuoviBranoSalvato() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int brano_id = Integer.parseInt(request.getParameter("brano_id"));
			ClientBean cb = (ClientBean) request.getSession().getAttribute("client");
			if(BranoManager.eliminaBranoSalvato(brano_id, cb.getId())) response.getWriter().println("Brano rimosso!");
			else response.getWriter().println("Errore");
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().println("Ci sono stati errori");
		}
	}
}
