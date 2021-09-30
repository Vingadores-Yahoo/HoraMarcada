package VingadoresDoYahoo.HoraMarcada.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import VingadoresDoYahoo.HoraMarcada.models.*;
import VingadoresDoYahoo.HoraMarcada.repositories.*;

@Controller
public class ConsumidorController {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ConsumidorController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ConsumidorRepository consumidorRepository;

    @Autowired
    PrestadorRepository prestadorRepository;


    @GetMapping("/cadastroConsumidor")
    public ModelAndView novoConsumidor(){
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("cadastroConsumidor");
        return mv;
    }

    @PostMapping("/salvarConsumidor")
    public ModelAndView salvarConsumidor(@Valid CadastroConsumidor cadastroConsumidor, BindingResult br) throws Exception {
        ModelAndView mv = new ModelAndView("/cadastroConsumidor");
        if(br.hasErrors()){
            return mv;
        }
        if(usuarioRepository.findByEmail(cadastroConsumidor.getEmail()) != null){
            mv.addObject("mensagem","E-mail já cadastrado");
            return mv;
        }

        cadastroConsumidor.setSenha(passwordEncoder.encode(cadastroConsumidor.getSenha()));
        
        Usuario usuario = new Usuario(null, cadastroConsumidor.getNome(), cadastroConsumidor.getEmail(), cadastroConsumidor.getSenha(), cadastroConsumidor.getTelefone(), RoleType.CONSUMIDOR);
        Consumidor consumidor = new Consumidor(cadastroConsumidor.getEndereco(), usuario);

        System.out.println(consumidor);

        consumidorRepository.save(consumidor);
        ModelAndView mb = new ModelAndView("/login");
        return mb;
    
    }
}