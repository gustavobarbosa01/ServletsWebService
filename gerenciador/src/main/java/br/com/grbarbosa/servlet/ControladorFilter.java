package br.com.grbarbosa.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.grbarbosa.acao.Acao;


//@WebFilter("/entrada")
public class ControladorFilter implements Filter {

	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}
	
	@Override
	public void destroy() {}
	
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("ControladorFilter");
		
		HttpServletRequest request =  (HttpServletRequest) servletRequest;
		HttpServletResponse response =  (HttpServletResponse) servletResponse;
		
		String paramAcao = request.getParameter("acao");
		String nomedaClasse = "br.com.grbarbosa.acao." + paramAcao;

		String nome;
		try {

			Class classe = Class.forName(nomedaClasse);// carrega a classe com o nome da string
			Acao acao = (Acao) classe.newInstance();
//			Object obj = classe.newInstance();
//			Acao acao = (Acao) obj;
			nome = acao.executa(request, response);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ServletException
				| IOException e) {
			throw new ServletException(e);
		}

		String[] tipoEndereco = nome.split(":");

		if (tipoEndereco[0].equals("forward")) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/" + tipoEndereco[1]);
			rd.forward(request, response);
		} else {

			response.sendRedirect(tipoEndereco[1]);
		}
	}



}
