package com.ifpr.paranavai.pedido.pedido.controle;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ifpr.paranavai.pedido.pedido.dominio.Pessoa;
import com.ifpr.paranavai.pedido.pedido.dominio.PessoaRepositorio;


@Controller
public class PessoaControle {
	
	private PessoaRepositorio pessoaRepositorio;
	
	public PessoaControle(PessoaRepositorio pessoaRepositorio) {
		this.pessoaRepositorio = pessoaRepositorio;
	}
	
	@GetMapping("/pessoas")
	public String pessoas(Model model) {
		model.addAttribute("listaPessoas", pessoaRepositorio.findAll());
		return "pessoas/index";
	}
	
	@GetMapping("pessoas/nova")
	public String novaPessoa(@ModelAttribute("pessoa") Pessoa pessoa) {
		return "pessoas/form";
	}
	
	@PostMapping("pessoas/salvar")
	public String salvarPessoa(@ModelAttribute("pessoa") Pessoa pessoa) {
		pessoaRepositorio.save(pessoa);
		return "redirect:/pessoas";
	}
	
	@GetMapping("pessoas/{id}")
	public String alterarPessoa(@PathVariable("id") long id, Model model) {
		Optional<Pessoa> pessoaOpt = pessoaRepositorio.findById(id);
		
		if (!pessoaOpt.isPresent()) {
			throw new IllegalArgumentException("Pessoa inválida");
		}
		
		model.addAttribute("pessoa", pessoaOpt.get());
		return "pessoas/form";
	}
	
	@GetMapping("pessoas/excluir/{id}")
	public String excluirPessoa(@PathVariable("id") long id) {
		Optional<Pessoa> pessoaOpt = pessoaRepositorio.findById(id);
		
		if (!pessoaOpt.isPresent()) {
			throw new IllegalArgumentException("Pessoa inválida");
		}
		
		pessoaRepositorio.delete(pessoaOpt.get());
		return "redirect:/pessoas";
	}

}