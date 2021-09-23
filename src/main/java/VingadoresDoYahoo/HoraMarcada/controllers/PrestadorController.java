 package VingadoresDoYahoo.HoraMarcada.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import VingadoresDoYahoo.HoraMarcada.models.*;
import VingadoresDoYahoo.HoraMarcada.repositories.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Controller
public class PrestadorController {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PrestadorController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ConsumidorRepository consumidorRepository;

    @Autowired
    PrestadorRepository prestadorRepository;

    @GetMapping(path = "/cadastroPrestador")
    public ModelAndView novoPrestador(){
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("cadastroPrestador");
    	return mv;
    }

    @GetMapping("/agendamentos")
    public ModelAndView agendamentos(@AuthenticationPrincipal Usuario usuario){
        System.out.println(usuario);
    	ModelAndView mv = new ModelAndView();
        mv.addObject("usuario", usuario);
    	mv.setViewName("agendamentosPrestador");
    	return mv;
    }

    @GetMapping("/avaliacoes")
    public static ModelAndView avaliacoes(@AuthenticationPrincipal Usuario usuario){
        System.out.println(usuario);
    	ModelAndView mv = new ModelAndView();
        mv.addObject("usuario", usuario);
        mv.setViewName("avaliacoes");
        return mv;
    }

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

    @PostMapping("/salvarPrestador")
    public ModelAndView salvarPrestador(@Valid CadastroPrestador cadastroPrestador, BindingResult br) throws Exception {
        ModelAndView mv = new ModelAndView("/cadastroPrestador");
        if(br.hasErrors()){
            return mv;
        }
        if(usuarioRepository.findByEmail(cadastroPrestador.getEmail()) != null){
            mv.addObject("mensagem","E-mail já cadastrado");
            return mv;
        }

        cadastroPrestador.setSenha(passwordEncoder.encode(cadastroPrestador.getSenha()));
        
        Usuario usuario = new Usuario(null, cadastroPrestador.getNome(), cadastroPrestador.getEmail(), cadastroPrestador.getSenha(), cadastroPrestador.getTelefone(), RoleType.PRESTADOR);
        Prestador prestador = new Prestador(cadastroPrestador.getEndereco(),cadastroPrestador.getBairro(), usuario);
        System.out.println(prestador);

        prestadorRepository.save(prestador);
        ModelAndView mb = new ModelAndView("/login");
        return mb;
    
    }

}