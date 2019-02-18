package it.gestione_brani_in_stallo;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import it.model.BranoBean;
import it.model.BranoManager;
import it.utility.Convert;
import it.utility.FileSystemManager;

@WebServlet("/standard/UploadBrano")
@MultipartConfig
public class UploadBrano extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = 100000 * 1024;
	private int maxMemSize = 4000 * 1024;
	private File file ;

	public UploadBrano() {
		super();
	}

	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		String artista = null;
		String album = null;
		String titolo = null;
		int numero = 0;
		int usr = 0;

		// Check that we have a file upload request
		isMultipart = ServletFileUpload.isMultipartContent(request);
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();

		if(!isMultipart) {
			return;
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();

		// maximum size that will be stored in memory
		factory.setSizeThreshold(maxMemSize);

		// Location to save data that is larger than maxMemSize.
		factory.setRepository(new File("c:\\temp"));

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// maximum file size to be uploaded.
		upload.setSizeMax( maxFileSize );

		try { 
			// Parse the request to get file items.
			List<FileItem> fileItems = upload.parseRequest(request);

			// Process the uploaded file items
			Iterator<FileItem> i = fileItems.iterator();

			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet upload</title>");  
			out.println("</head>");
			out.println("<body>");

			while ( i.hasNext () ) {
				FileItem fi = (FileItem)i.next();
				if(fi.getFieldName().equals("numero")) numero = Integer.parseInt(fi.getString());
				if(fi.getFieldName().equals("artista")) artista = fi.getString();
				if(fi.getFieldName().equals("album")) album = fi.getString();
				if(fi.getFieldName().equals("titolo")) titolo = fi.getString();
				if(fi.getFieldName().equals("usr")) usr = Integer.parseInt(fi.getString());

				if ( !fi.isFormField () ) {
					// Get the uploaded file parameters
					String fieldName = fi.getFieldName();
					String fileName = fi.getName();
					String contentType = fi.getContentType();
					boolean isInMemory = fi.isInMemory();
					long sizeInBytes = fi.getSize();
					filePath = FileSystemManager.makeDir(artista, album);

					// Write the file
						System.out.println(fileName);
						file = new File( filePath + "\\" + Convert.spaceToUnderscore(titolo) + ".mp3") ;
						filePath = file.getPath();
						
					fi.write( file ) ;
					out.println("Uploaded Filename: " + titolo + "<br>");
				}
			}
			
		BranoBean bb = new BranoBean(0, titolo, artista, album, filePath, usr);	
		BranoManager.uploadBranoInStallo(bb);

		} catch(Exception ex) {
			System.out.println(ex);
			out.println(ex);
		}
		
		out.println("Redirecting...");
		response.sendRedirect(request.getContextPath() + "/");
	}
}
