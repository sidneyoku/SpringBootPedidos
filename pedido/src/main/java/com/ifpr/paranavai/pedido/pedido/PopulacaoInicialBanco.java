package com.ifpr.paranavai.pedido.pedido;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ifpr.paranavai.pedido.pedido.dominio.Pessoa;
import com.ifpr.paranavai.pedido.pedido.dominio.PessoaRepositorio;
import com.ifpr.paranavai.pedido.pedido.dominio.Produto;
import com.ifpr.paranavai.pedido.pedido.dominio.ProdutoRepositorio;


@Component
@Transactional
public class PopulacaoInicialBanco implements CommandLineRunner {
	
	@Autowired
	private PessoaRepositorio pessoaRepositorio;
	
	@Autowired
	private ProdutoRepositorio produtoRepositorio;
	
	@Override
	public void run(String... args) throws Exception {
		
		Pessoa p1 = new Pessoa("João");
		p1.setDataNascimento(LocalDate.of(1990, 4, 1));
		p1.setEmail("joao@email.com");
		
		Pessoa p2 = new Pessoa("Maria");
		p2.setDataNascimento(LocalDate.of(1994, 1, 1));
		p2.setEmail("maria@email.com");
		
		pessoaRepositorio.save(p1);
		pessoaRepositorio.save(p2);
		
		Produto prod1 = new Produto();
		prod1.setNome("Bolo de cenoura");
		prod1.setDescricao("Feito com pouco açúcar com cobertura de chocolate.");
		prod1.setDataCadastro(LocalDate.now());
		prod1.setValor(3.30);
		
		Produto prod2 = new Produto();
		prod2.setNome("Pudim");
		prod2.setDescricao("Feito com leite zero lactose.");
		prod2.setDataCadastro(LocalDate.now());
		prod2.setValor(5.20);
		
		produtoRepositorio.save(prod1);
		produtoRepositorio.save(prod2);
	}

}
