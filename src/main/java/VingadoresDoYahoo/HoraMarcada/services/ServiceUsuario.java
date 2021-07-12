package VingadoresDoYahoo.HoraMarcada.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import VingadoresDoYahoo.HoraMarcada.exceptions.ServiceExc;
import VingadoresDoYahoo.HoraMarcada.models.Usuario;
import VingadoresDoYahoo.HoraMarcada.repositories.UsuarioRepository;

@Service
public class ServiceUsuario {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    
    public Usuario loginUser(String email, String senha) throws ServiceExc{
        Usuario userLogin = usuarioRepository.buscarLogin(email, senha);
        return userLogin;
    }
    

}