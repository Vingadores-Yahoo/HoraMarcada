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
import VingadoresDoYahoo.HoraMarcada.models.Consumidor;
import VingadoresDoYahoo.HoraMarcada.models.RoleType;
import VingadoresDoYahoo.HoraMarcada.models.Usuario;
import VingadoresDoYahoo.HoraMarcada.repositories.ConsumidorRepository;
import VingadoresDoYahoo.HoraMarcada.repositories.PrestadorRepository;
import VingadoresDoYahoo.HoraMarcada.repositories.UsuarioRepository;

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
    	mv.setViewName("/cadastroConsumidor");
        return mv;
    }

    @PostMapping(path = "/cadastroConsumidor")
    public String salvarConsumidor(@Validated CadastroConsumidor cadastroConsumidor, BindingResult br) throws Exception {
        if(br.hasErrors()){
            return "/cadastroConsumidor";
        }
        if(usuarioRepository.findByEmail(cadastroConsumidor.getEmail()) != null){
            throw new EmailExistsException("Email já cadastrado: " + cadastroConsumidor.getEmail());
        }

        cadastroConsumidor.setSenha(passwordEncoder.encode(cadastroConsumidor.getSenha()));
        
        Usuario usuario = new Usuario(null, cadastroConsumidor.getNome(), cadastroConsumidor.getEmail(), cadastroConsumidor.getSenha(), cadastroConsumidor.getTelefone(), RoleType.CONSUMIDOR);
        Consumidor consumidor = new Consumidor(cadastroConsumidor.getEndereco(), usuario);

        System.out.println(consumidor);

        consumidorRepository.save(consumidor);
        return "/index";
    
    }
}