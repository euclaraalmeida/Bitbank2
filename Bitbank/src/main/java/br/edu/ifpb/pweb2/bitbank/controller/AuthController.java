package br.edu.ifpb.pweb2.bitbank.controller;

import java.lang.ProcessBuilder.Redirect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import br.edu.ifpb.pweb2.bitbank.service.CorrentistaService;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class AuthController {

    @Autowired
    private CorrentistaService correntistaService;
    
    @GetMapping("/auth")
    public String Authention(Model model) {
        model.addAttribute("correntista", new Correntista());
        return "auth/login";
    }
    

    @PostMapping("/auth")
    public String login(Correntista correntista, HttpSession session, RedirectAttributes flash) {
        Correntista correntistaValidado = correntistaService.validar(correntista.getEmail(),correntista.getSenha());
       
       if (correntistaValidado != null ){
        session.setAttribute("user logado",correntistaValidado);
        return "redirect:/home";
       }
       flash.addFlashAttribute("mensagem erro", "correntista nao encontrado ou email/senha invalidos");
        return "redirect:/auth";
    }
    
}
