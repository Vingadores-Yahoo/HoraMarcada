
package VingadoresDoYahoo.HoraMarcada.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import VingadoresDoYahoo.HoraMarcada.models.*;
import VingadoresDoYahoo.HoraMarcada.repositories.PrestadorRepository;
import VingadoresDoYahoo.HoraMarcada.repositories.ServicoRepository;
import VingadoresDoYahoo.HoraMarcada.services.CloudinaryService;

@Controller
public class ServicoController {

    @Autowired
    ServicoRepository servicoRepository;

    @Autowired
    UsuarioController usuarioController;

    @Autowired
    PrestadorRepository prestadorRepository;

    @Autowired
    CloudinaryService cloudinaryService;

    @GetMapping("/novoServico")
    public ModelAndView novoServico(CadastroServico cadastroServico, @AuthenticationPrincipal Usuario usuario){
    	ModelAndView mv = new ModelAndView();
        //model.addAttribute("usuario", usuario);
    	mv.setViewName("formServico");
        mv.addObject("usuario", usuario);
        mv.addObject("modalidade", Modalidade.values());
        mv.addObject("localtrabalho", LocalTrabalho.values());
    	return mv;
    }

    @PostMapping("/salvarServico")
    public ModelAndView salvarServico(@RequestParam("file") MultipartFile file, @Valid CadastroServico cadastroServico, @AuthenticationPrincipal Usuario usuario, BindingResult br) throws Exception {
        if(br.hasErrors()){
            return novoServico(cadastroServico, usuario); 
        }
        
        Optional<Prestador> prestadorOptional = prestadorRepository.findByUsuarioId(usuario.getId());
        String url = cloudinaryService.uploadFile(file);

        Servico servico = new Servico(cadastroServico.getModalidade() ,cadastroServico.getLocaltrabalho(), url, prestadorOptional.get());
        System.out.println(servico);
        servicoRepository.save(servico);

        return new ModelAndView("redirect:/meuPerfil");
    }
    
    /*
    @PutMapping("/alterarServico")
    public ModelAndView salvarAgendamento(@Valid CadastroServico cadastroServico, @AuthenticationPrincipal Usuario usuario, BindingResult br) throws Exception {
        if(br.hasErrors()){
        	return new ModelAndView("redirect:/meuPerfil");
        }
        
        Optional<Prestador> prestadorOptional = prestadorRepository.findByUsuarioId(usuario.getId());

        Servico servico = new Servico(cadastroServico.getModalidade() ,cadastroServico.getLocaltrabalho(), prestadorOptional.get());
        System.out.println(servico);
        servicoRepository.save(servico);

        return new ModelAndView("redirect:/meuPerfil");
    }
    */

    @GetMapping(value = "/deletarServico/{id}") 
    public ModelAndView deletarServico(@PathVariable Long id, Servico servico){
    		
        System.out.println(servico); 
        servicoRepository.deleteById(servico.getId());
        System.out.println("FUNCIONOU");
    	return new ModelAndView("redirect:/meuPerfil");
    }
}