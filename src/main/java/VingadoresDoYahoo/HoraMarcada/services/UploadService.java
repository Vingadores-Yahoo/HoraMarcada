/*
package VingadoresDoYahoo.HoraMarcada.services;

import java.io.IOException;
import java.util.*;
import com.cloudinary.utils.ObjectUtils;

import com.cloudinary.*;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {
    
    public void upload(MultipartFile file) throws IOException{
        byte[] bytes = file.getBytes();
        
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
        "cloud_name", "horamarcada",
        "api_key", "537195442549849",
        "api_secret", "StgldcWlMxlsDZ9OlMz8XKJfgSw",
        "secure", true));

        Map caminho = ObjectUtils.asMap(
        "folder", "fotos_usuarios",
        "overwrite", true,
        "resource_type", "image"         
        );

        cloudinary.uploader().upload(bytes, caminho);

    }

}
*/