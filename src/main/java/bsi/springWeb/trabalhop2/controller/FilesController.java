package bsi.springWeb.trabalhop2.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/files")
public class FilesController {
    @GetMapping("/jpg/{nome}")
    public void getFileJpg(@PathVariable("nome") String nome,
                        HttpServletResponse response){
        
        String path = "src/main/resources/img/";
        Path arquivo = Paths.get(path, nome);

        if(Files.exists(arquivo)) {
            response.setHeader("Content-Disposition", "inline");
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);            
            try {
                Files.copy(arquivo, response.getOutputStream());
                response.getOutputStream().flush();
            } 
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }   
}
