package br.com.grbarbosa.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.grbarbosa.servlet.modelo.Banco;
import br.com.grbarbosa.servlet.modelo.Empresa;

@WebServlet("/empresas")
public class EmpresasService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Empresa> empresas = new Banco().getEmpresas();

		String valor = request.getHeader("Accept");

		
		  if (valor.contains("xml")) {
		  
		  XStream xstream = new XStream(); xstream.alias("empresa", Empresa.class);
		  String xml = xstream.toXML(empresas);
		  
		  response.setContentType("aplication/xml"); 
		  response.getWriter().print(xml);
		  
		  } else if (valor.contains("json")) {

				Gson gson = new Gson();
				String json = gson.toJson(empresas);
		
				response.setContentType("aplication/json");
				response.getWriter().print(json);

		}else {
			
			response.setContentType("aplication/json");
			response.getWriter().print("'message':'{no Content}'");
		}

	}

}
