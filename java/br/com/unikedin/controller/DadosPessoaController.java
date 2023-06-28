package br.com.unikedin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.unikedin.model.DadosPessoa;
import br.com.unikedin.repository.DadosPessoaRepository;

@Controller
public class DadosPessoaController {
	
	@Autowired
	private DadosPessoaRepository dadosPessoaRepository;
	
	private ModelAndView andView;
	
	@RequestMapping("/")
	public String inicio() {
		return "inicio";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastrarDadosPessoa")
	public ModelAndView cadastro() {
		andView = new ModelAndView("cadastro/cadastrarDadosPessoa");
		andView.addObject("dadosPessoaObj", new DadosPessoa());
		return andView;
	}

	@RequestMapping(method = RequestMethod.POST, value = "**/salvarDadosPessoa")
	public ModelAndView salvar(DadosPessoa dadosPessoa) {
		andView = new ModelAndView("adicionado/dadosPessoaAdicionado");
		dadosPessoaRepository.save(dadosPessoa);
		andView.addObject("dadosPessoaObj", new DadosPessoa());
		return andView;

	}
}
