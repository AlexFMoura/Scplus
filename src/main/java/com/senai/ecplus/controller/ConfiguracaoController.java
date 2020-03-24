package com.senai.ecplus.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.senai.ecplus.controller.page.PageWrapper;
import com.senai.ecplus.model.Configuracao;
import com.senai.ecplus.repository.Configuracoes;
import com.senai.ecplus.repository.filter.ConfiguracaoFilter;
import com.senai.ecplus.service.CadastroConfiguracaoService;
import com.senai.ecplus.service.StatusConfiguracao;
import com.senai.ecplus.service.exception.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping("/configuracoes")
public class ConfiguracaoController {
	
	@Autowired
	private Configuracoes configuracoes;	
	
	@Autowired
	private CadastroConfiguracaoService cadastroConfiguracaoService;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Configuracao configuracao) {
		ModelAndView mv = new ModelAndView("configuracao/CadastroConfiguracao");
		return mv;
	}	
	
	@PostMapping(value = {"/novo", "{\\d+}"}, params = "cancelar")
	public ModelAndView cancelar(Configuracao configuracao, BindingResult result, RedirectAttributes attributes) {
		return new ModelAndView("redirect:/configuracoes/novo");
	}

	@PostMapping({"/novo", "{\\d+}"})
	public ModelAndView salvar(@Valid Configuracao configuracao, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(configuracao);
		}
		
		try {
			cadastroConfiguracaoService.salvar(configuracao);
		} catch (Exception e) {
			e.printStackTrace();
			return novo(configuracao);
		}
		
		
		attributes.addFlashAttribute("messagem", "Configuração salva com sucesso!");
		return new ModelAndView("redirect:/configuracoes/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(ConfiguracaoFilter configuracaoFilter, BindingResult result,
			@PageableDefault(size = 5)  Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("/configuracao/PesquisaConfiguracao");
		
		PageWrapper<Configuracao> paginaWrapper = new PageWrapper<>(configuracoes.filtrar(configuracaoFilter,pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);		
		
		return mv;		
	}
	
	@PutMapping("/status")
	@ResponseStatus(HttpStatus.OK)
	public void atualizarStatus(@RequestParam("codigos[]") Long[] codigos, @RequestParam("status") StatusConfiguracao statusConfiguracao) {
//		Arrays.asList(codigos).forEach(System.out::println);
//		System.out.println("Status :" + statusConfiguracao);
		cadastroConfiguracaoService.alterarStatus(codigos, statusConfiguracao);
		
	}	
	
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable Long codigo){
		try {
			cadastroConfiguracaoService.excluir(codigo);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage()); 
		}
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView buscar(@PathVariable Long codigo) {		
		Configuracao configuracao = configuracoes.buscar(codigo);
		ModelAndView mv = novo(configuracao);
		mv.addObject(configuracao);
		
		return mv;
	}

}
