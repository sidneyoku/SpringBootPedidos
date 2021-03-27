package com.ifpr.paranavai.pedido.pedido.controle;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ifpr.paranavai.pedido.pedido.dominio.Produto;
import com.ifpr.paranavai.pedido.pedido.dominio.ProdutoRepositorio;

@Controller
public class ProdutoControle {
	
	private ProdutoRepositorio produtoRepositorio;
	
	public ProdutoControle(ProdutoRepositorio produtoRepositorio) {
		this.produtoRepositorio = produtoRepositorio;
	}
	
	@GetMapping("/produtos")
	public String produtos(Model model) {
		model.addAttribute("listaProdutos", produtoRepositorio.findAll());
		return "produtos/index";
	}
	
	@GetMapping("produtos/nova")
	public String novaProduto(@ModelAttribute("produto") Produto produto) {
		produto.setDataCadastro(LocalDate.now());
		return "produtos/form";
	}
	
	@PostMapping("produtos/salvar")
	public String salvarProduto(@ModelAttribute("produto") Produto produto) {
		produtoRepositorio.save(produto);
		return "redirect:/produtos";
	}
	
	@GetMapping("produtos/{id}")
	public String alterarProduto(@PathVariable("id") long id, Model model) {
		Optional<Produto> produtoOpt = produtoRepositorio.findById(id);
		
		if (!produtoOpt.isPresent()) {
			throw new IllegalArgumentException("Produto inválido");
		}
		
		model.addAttribute("produto", produtoOpt.get());
		return "produtos/form";
	}
	
	@GetMapping("produtos/excluir/{id}")
	public String excluirProduto(@PathVariable("id") long id) {
		Optional<Produto> produtoOpt = produtoRepositorio.findById(id);
		
		if (!produtoOpt.isPresent()) {
			throw new IllegalArgumentException("Produto inválido");
		}
		
		produtoRepositorio.delete(produtoOpt.get());
		return "redirect:/produtos";
	}

}
