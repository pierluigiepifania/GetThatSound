package it.gestione_brani_in_stallo;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.model.BranoManager;

@WebServlet(description = "Restituisce tutti i brani disponibili in formato JSON", urlPatterns = { "/GetBraniStallo" })
public class GetBraniStallo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetBraniStallo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.getWriter().println(BranoManager.getBraniStallo());
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().append("Error during servlet GetBraniStallo");
		}
	}
}
