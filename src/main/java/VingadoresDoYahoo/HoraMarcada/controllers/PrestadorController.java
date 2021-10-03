 package VingadoresDoYahoo.HoraMarcada.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import VingadoresDoYahoo.HoraMarcada.exceptions.EmailExistsException;
import VingadoresDoYahoo.HoraMarcada.models.*;
import VingadoresDoYahoo.HoraMarcada.repositories.*;

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

    @Autowired
    ServicoRepository servicoRepository;

    @GetMapping("/cadastroPrestador")
    public ModelAndView novoPrestador(){
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("cadastroPrestador");
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

    @PostMapping("/cadastroPrestador")
    public ModelAndView salvarPrestador(@Valid CadastroPrestador cadastroPrestador, BindingResult br) throws Exception {
        ModelAndView mv = new ModelAndView("/cadastroPrestador");
        if(br.hasErrors()){
            return mv;
        }
        if(usuarioRepository.findByEmail(cadastroPrestador.getEmail()) != null){
            throw new EmailExistsException("Email já cadastrado: " + cadastroPrestador.getEmail());
        }

        cadastroPrestador.setSenha(passwordEncoder.encode(cadastroPrestador.getSenha()));
        
        Usuario usuario = new Usuario(null, cadastroPrestador.getNome(), cadastroPrestador.getEmail(), cadastroPrestador.getSenha(), cadastroPrestador.getTelefone(), RoleType.PRESTADOR);
        Prestador prestador = new Prestador(cadastroPrestador.getEndereco(),cadastroPrestador.getBairro(), usuario);
        System.out.println(prestador);

        prestadorRepository.save(prestador);
        ModelAndView mb = new ModelAndView("/login");
        return mb;
    }

    @GetMapping("/formServico")
    public ModelAndView formServico(Modalidade modalidade){
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("formServico");
        mv.addObject("servico", new Servico());
        mv.addObject("modalidades", Modalidade.values());
    	return mv;
    }

    @PostMapping("/salvarServico")
    public ModelAndView salvarServico(@Valid CadastroServico cadastroServico, BindingResult br) throws Exception {
        ModelAndView mv = new ModelAndView("/formServico");
        if(br.hasErrors()){
            return mv;
        }
        Servico servico = new Servico(cadastroServico.getModalidade(),cadastroServico.getLocaltrabalho());
        System.out.println(servico);
        servicoRepository.save(servico);

        ModelAndView mb = new ModelAndView("/perfilPrestador");
        return mb;
    }

}