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

@WebServlet(description = "Restituisce tutti i brani salvati in formato JSON", urlPatterns = { "/GetBraniSalvati" })
public class GetBraniSalvati extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetBraniSalvati() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ClientBean client = (ClientBean) request.getSession().getAttribute("client");
			int user_id = client.getId();
			response.getWriter().println(BranoManager.getBraniSalvati(user_id));
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().append("Error during servlet GetBraniSalvati");
		}
	}
}
