 package VingadoresDoYahoo.HoraMarcada.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import VingadoresDoYahoo.HoraMarcada.models.Prestador;
import VingadoresDoYahoo.HoraMarcada.models.Servico;
import VingadoresDoYahoo.HoraMarcada.models.Usuario;
import VingadoresDoYahoo.HoraMarcada.repositories.ConsumidorRepository;
import VingadoresDoYahoo.HoraMarcada.repositories.PrestadorRepository;
import VingadoresDoYahoo.HoraMarcada.repositories.ServicoRepository;
import VingadoresDoYahoo.HoraMarcada.repositories.UsuarioRepository;

@Controller
public class PrestadorController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ConsumidorRepository consumidorRepository;

    @Autowired
    PrestadorRepository prestadorRepository;

    @Autowired
    ServicoRepository servicoRepository;

    @GetMapping("/portfolio")
    public ModelAndView portfolio(ModelMap model, @AuthenticationPrincipal Usuario usuario){
    	ModelAndView mv = new ModelAndView();
        model.addAttribute("usuario", usuario);
    	mv.setViewName("portfolioPrestador");
    	return mv;
    }

    @GetMapping("/informacoes")
    public ModelAndView informacoes(ModelMap model, @AuthenticationPrincipal Usuario usuario){
    	ModelAndView mv = new ModelAndView();
        model.addAttribute("usuario", usuario);
    	mv.setViewName("infoPrestador");
    	return mv;
    }

    @GetMapping("/prestadores/{id}")
    public ModelAndView prestrador(@PathVariable Long id, ModelMap model, @AuthenticationPrincipal Usuario usuario){
        Optional<Prestador> prestadorOptional = prestadorRepository.findById(id);
        Prestador prestador = prestadorOptional.get();

        List<Servico> servico = servicoRepository.findByPrestadorId(prestador.getId());
            
    	ModelAndView mv = new ModelAndView();
        mv.addObject("servico", servico);
        model.addAttribute("usuario", usuario);
        mv.addObject("prestador", prestadorOptional.get());
    	mv.setViewName("infoPrestador");
    	return mv;
    }
}