package com.ifpr.paranavai.pedido.pedido.controle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ifpr.paranavai.pedido.pedido.dominio.Pedido;
import com.ifpr.paranavai.pedido.pedido.dominio.PedidoRepositorio;
import com.ifpr.paranavai.pedido.pedido.dominio.Produto;
import com.ifpr.paranavai.pedido.pedido.dominio.ProdutoRepositorio;

@Controller
public class PedidoControle {
	
	private PedidoRepositorio pedidoRepositorio;
	private ProdutoRepositorio produtoRepositorio;
	
	public PedidoControle(PedidoRepositorio pedidoRepositorio, ProdutoRepositorio produtoRepositorio) {
		this.pedidoRepositorio = pedidoRepositorio;
		this.produtoRepositorio = produtoRepositorio;
	}
	
	@GetMapping("/pedidos")
	public String pedidos(Model model) {
		model.addAttribute("listaPedidos", pedidoRepositorio.findAll());
		return "pedidos/index";
	}
	
	@GetMapping("pedidos/nova")
	public String novaPedido(@ModelAttribute("pedido") Pedido pedido, Model model) {
		//List<Produto> produtos = new ArrayList<>();
		model.addAttribute("listaProdutos", produtoRepositorio.findAll());
		System.out.println(produtoRepositorio.findAll());
		return "pedidos/form";
	}
	
	@PostMapping("pedidos/salvar")
	public String salvarPedido(@ModelAttribute("pedido") Pedido pedido) {
		System.out.println("SALVAR");
		System.out.println("Id: " + pedido.getId());
		System.out.println("Nome: " + pedido.getNome());
		System.out.println("idProduto: " + pedido.getIdProduto());
		pedidoRepositorio.save(pedido);
		return "redirect:/pedidos";
	}

}
