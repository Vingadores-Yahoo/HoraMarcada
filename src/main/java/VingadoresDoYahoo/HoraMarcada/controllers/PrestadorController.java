 package VingadoresDoYahoo.HoraMarcada.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import VingadoresDoYahoo.HoraMarcada.exceptions.EmailExistsException;
import VingadoresDoYahoo.HoraMarcada.models.Prestador;
import VingadoresDoYahoo.HoraMarcada.models.RoleType;
import VingadoresDoYahoo.HoraMarcada.models.Usuario;
import VingadoresDoYahoo.HoraMarcada.repositories.ConsumidorRepository;
import VingadoresDoYahoo.HoraMarcada.repositories.PrestadorRepository;
import VingadoresDoYahoo.HoraMarcada.repositories.UsuarioRepository;

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

    @GetMapping("/cadastroPrestador")
    public ModelAndView novoPrestador(){
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("/cadastroPrestador");
    	return mv;
    }

    @GetMapping("/agendamentos")
    public ModelAndView agendamentos(){
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("/agendamentosPrestador");
    	return mv;
    }

    @GetMapping("/avaliacoes")
    public ModelAndView avaliacoes(){
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("/avaliacoes");
    	return mv;
    }

    @GetMapping("/portfolio")
    public ModelAndView portfolio(){
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("/portfolioPrestador");
    	return mv;
    }

    @GetMapping("/informacoes")
    public ModelAndView informacoes(){
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("/infoPrestador");
    	return mv;
    }

    @PostMapping(path = "/cadastroPrestador")
    public String salvarPrestador(@Validated CadastroPrestador cadastroPrestador, BindingResult br) throws Exception {
        if(br.hasErrors()){
            return "/cadastroPrestador";
        }
        if(usuarioRepository.findByEmail(cadastroPrestador.getEmail()) != null){
            throw new EmailExistsException("Email já cadastrado: " + cadastroPrestador.getEmail());
        }

        cadastroPrestador.setSenha(passwordEncoder.encode(cadastroPrestador.getSenha()));
        
        Usuario usuario = new Usuario(null, cadastroPrestador.getNome(), cadastroPrestador.getEmail(), cadastroPrestador.getSenha(), cadastroPrestador.getTelefone(), RoleType.PRESTADOR);
        Prestador prestador = new Prestador(cadastroPrestador.getEndereco(),cadastroPrestador.getBairro(), usuario);
        System.out.println(prestador);

        prestadorRepository.save(prestador);
        return "/index";
    
    }

} 