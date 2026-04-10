package br.edu.ifpb.pweb2.bitbank.controller;

import java.net.PasswordAuthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import br.edu.ifpb.pweb2.bitbank.service.CorrentistaService;
import br.edu.ifpb.pweb2.bitbank.util.PasswordUtil;

@Controller
@RequestMapping("/correntistas")
public class CorrentistaController {

    @Autowired
    private CorrentistaService correntistaService;


    
    @GetMapping("/form")
    public String getForm(Correntista correntista, Model model) {
        model.addAttribute("correntista", correntista);
        return "correntistas/form";
    }

    @PostMapping
    public String save(Correntista correntista, Model model, RedirectAttributes redirectAttributes) {
        correntista.setSenha(PasswordUtil.hashPassword(correntista.getSenha()));
        correntistaService.save(correntista);
        model.addAttribute("correntistas", correntistaService.findAll());
        redirectAttributes.addFlashAttribute("mensagem", "Corerntita cadastrado com sucesso");
        return "redirect:/correntistas";
    }

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("correntistas", correntistaService.findAll());
        return "correntistas/list";
    }

    @GetMapping("/{id}")
    public String getCorrentistaById(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("correntista", correntistaService.findById(id));
        return "correntistas/form";
    }
}
