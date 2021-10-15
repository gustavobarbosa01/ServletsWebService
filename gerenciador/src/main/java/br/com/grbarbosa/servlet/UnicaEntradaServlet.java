package br.com.grbarbosa.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.grbarbosa.acao.Acao;
import br.com.grbarbosa.acao.AlteraEmpresa;
import br.com.grbarbosa.acao.ListaEmpresas;
import br.com.grbarbosa.acao.MostraEmpresa;
import br.com.grbarbosa.acao.NovaEmpresa;
import br.com.grbarbosa.acao.NovaEmpresaForm;
import br.com.grbarbosa.acao.RemoveEmpresa;

//@WebServlet("/entrada")
public class UnicaEntradaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String paramAcao = request.getParameter("acao");
		
//		HttpSession sessao = request.getSession();
//		boolean usuarioNaoEstaLogado = (sessao.getAttribute("usuarioLogado") == null);
//		boolean ehUmaAcaoProtegida = !(paramAcao.equals("Login") || paramAcao.equals("LoginForm")  );
//		
//		if (ehUmaAcaoProtegida && usuarioNaoEstaLogado) {
//			response.sendRedirect("entrada?acao=LoginForm");
//			return;
//		}

		

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
//		String nome = null;
//		if (paramAcao.equals("ListaEmpresas")) {
//			
//			ListaEmpresas acao = new ListaEmpresas();
//			nome = acao.executa(request, response);
//			
//		}else if (paramAcao.equals("RemoveEmpresa")) {
//			
//			RemoveEmpresa acao = new RemoveEmpresa();
//			nome = acao.executa(request, response);
//			
//			
//		}else if (paramAcao.equals("MostraEmpresa")) {
//			
//			MostraEmpresa acao = new MostraEmpresa();
//			nome = acao.executa(request, response);
//			
//		}else if (paramAcao.equals("AlteraEmpresa")) {
//			
//			AlteraEmpresa acao = new AlteraEmpresa();
//			nome = acao.executa(request, response);
//			
//		}else if (paramAcao.equals("NovaEmpresa")) {
//			
//			NovaEmpresa acao = new NovaEmpresa();
//			nome = acao.executa(request, response);
//
//		}else if (paramAcao.equals("NovaEmpresaForm")) {
//			
//			NovaEmpresaForm acao = new NovaEmpresaForm();
//			nome = acao.executa(request, response);
//
//		}
//		

	}

}
