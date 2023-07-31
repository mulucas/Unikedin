package br.com.unikedin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.unikedin.model.Campus;
import br.com.unikedin.model.Curso;
import br.com.unikedin.model.DadosPessoa;
import br.com.unikedin.repository.DadosPessoaRepository;

@Controller
public class DadosPessoaController {
	
	@Autowired
	private DadosPessoaRepository dadosPessoaRepository;
	
	private ModelAndView andView;
	
	@RequestMapping("/")
	public ModelAndView inicio() {
		ModelAndView modelAndView = new ModelAndView("inicio");

		List<String> listaCampus = Campus.getNomesCampus();
		List<String> listaCursos = Curso.getNomesCampus();
        
        modelAndView.addObject("listaCampus", listaCampus);
		modelAndView.addObject("listaCursos", listaCursos);

		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastrarDadosPessoa")
	public ModelAndView cadastro() {
		andView = new ModelAndView("cadastro/cadastrarDadosPessoa");

		List<String> listaCampus = Campus.getNomesCampus();
        
        andView.addObject("listaCampus", listaCampus);	
		andView.addObject("dadosPessoaObj", new DadosPessoa());
		
		return andView;
	}

	@RequestMapping(method = RequestMethod.POST, value = "**/salvarDadosPessoa")
	public ModelAndView salvar(DadosPessoa dadosPessoa) {
		andView = new ModelAndView("adicionada/dadosPessoaAdicionada");
		dadosPessoaRepository.save(dadosPessoa);
		andView.addObject("dadosPessoaObj", new DadosPessoa());
		return andView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/listaDadosPessoas")
	public ModelAndView listaDadosPessoas() {
		andView = new ModelAndView("lista/listaDadosPessoas");

		Iterable<DadosPessoa> dadosPessoasIt = dadosPessoaRepository.findAll();
		andView.addObject("dadosPessoas", dadosPessoasIt);

		List<String> listaCampus = Campus.getNomesCampus();   
        andView.addObject("listaCampus", listaCampus);

		return andView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/listaCampus")
	public ModelAndView listaPessoasCampus(@RequestParam("parametro") String campus) {
		andView = new ModelAndView("lista/listaDadosPessoas");

		Iterable<DadosPessoa> dadosPessoasIt = dadosPessoaRepository.findByCampus(campus);
		andView.addObject("dadosPessoas", dadosPessoasIt);

		List<String> listaCampus = Campus.getNomesCampus();   
        andView.addObject("listaCampus", listaCampus);
		
		return andView;
	}
}
