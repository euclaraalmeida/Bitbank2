package br.edu.ifpb.pweb2.bitbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import br.edu.ifpb.pweb2.bitbank.service.CorrentistaService;
import jakarta.servlet.http.HttpSession;




@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private CorrentistaService correntistaService;
    
    
    @GetMapping()
    public String Authention(Model model) {
        model.addAttribute("correntista", new Correntista());
        return "auth/login";
    }
    

    @PostMapping()
    public String login(Correntista correntista, HttpSession session, RedirectAttributes flash) {
        Correntista correntistaValidado = correntistaService.validar(correntista.getEmail(),correntista.getSenha());
       
       if (correntistaValidado != null ){
        session.setAttribute("user logado",correntistaValidado);
        return "redirect:/home";
       }
       flash.addFlashAttribute("mensagem erro", "correntista nao encontrado ou email/senha invalidos");
        return "redirect:/auth";
    }
    

    @GetMapping("/logout")
    public String logout( HttpSession session ) {
        session.invalidate();    
        return "redirect:/auth";
    }
    
}
