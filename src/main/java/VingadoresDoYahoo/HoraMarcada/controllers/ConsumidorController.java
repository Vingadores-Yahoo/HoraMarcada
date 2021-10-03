package VingadoresDoYahoo.HoraMarcada.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import VingadoresDoYahoo.HoraMarcada.repositories.ConsumidorRepository;
import VingadoresDoYahoo.HoraMarcada.repositories.PrestadorRepository;
import VingadoresDoYahoo.HoraMarcada.repositories.UsuarioRepository;

@Controller
public class ConsumidorController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ConsumidorRepository consumidorRepository;

    @Autowired
    PrestadorRepository prestadorRepository;

}