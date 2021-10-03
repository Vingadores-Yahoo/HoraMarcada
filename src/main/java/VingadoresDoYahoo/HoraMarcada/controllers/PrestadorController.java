 package VingadoresDoYahoo.HoraMarcada.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import VingadoresDoYahoo.HoraMarcada.models.Prestador;
import VingadoresDoYahoo.HoraMarcada.repositories.ConsumidorRepository;
import VingadoresDoYahoo.HoraMarcada.repositories.PrestadorRepository;
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

    @GetMapping("/portfolio")
    public ModelAndView portfolio(){
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("portfolioPrestador");
    	return mv;
    }

    @GetMapping("/informacoes")
    public ModelAndView informacoes(){
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("infoPrestador");
    	return mv;
    }

    @GetMapping("/prestadores/{id}")
    public ModelAndView prestrador(@PathVariable Long id){
        Optional<Prestador> prestadorOptional = prestadorRepository.findById(id);
    	ModelAndView mv = new ModelAndView();
        mv.addObject("prestador", prestadorOptional.get());
    	mv.setViewName("infoPrestador");
    	return mv;
    }
}