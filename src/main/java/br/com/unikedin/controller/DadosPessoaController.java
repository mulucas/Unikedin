package br.com.unikedin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
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
		andView = new ModelAndView("inicio");
		return andView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastrarDadosPessoa")
	public ModelAndView cadastro() {
		andView = new ModelAndView("cadastro/cadastrarDadosPessoa");
        
        andView.addObject("listaCampus", Campus.getNomesCampus());
		andView.addObject("listaCursos", Curso.getNomes());	
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
 
        andView.addObject("listaCampus", Campus.getNomesCampus());
		andView.addObject("listaCursos", Curso.getNomes());
		andView.addObject("dadosPessoaObj", new DadosPessoa());

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

	@PostMapping("/pesquisarpessoa")
    public ModelAndView buscarPorNome(@RequestParam("nome") String nome, @RequestParam("curso") String curso) {
        andView = new ModelAndView("lista/listaDadosPessoas");

    	Iterable<DadosPessoa> dadosPessoasIt = new ArrayList<>();

		if (nome != null && !curso.equals("-- SELECIONE --")) {
            dadosPessoasIt = dadosPessoaRepository.buscarPorNomeECursoIgnoreCase(nome, curso);
        } else if (nome != null) {
            dadosPessoasIt = dadosPessoaRepository.buscarPorNomeIgnoreCase(nome);
        } else if (curso.equals("-- SELECIONE --")) {
            dadosPessoasIt = dadosPessoaRepository.buscarPorCursoIgnoreCase(curso);
        }

		andView.addObject("dadosPessoas", dadosPessoasIt);
    	andView.addObject("listaCampus", Campus.getNomesCampus());
		andView.addObject("listaCursos", Curso.getNomes());
		andView.addObject("dadosPessoaObj", new DadosPessoa());

        return andView;
    }
}
