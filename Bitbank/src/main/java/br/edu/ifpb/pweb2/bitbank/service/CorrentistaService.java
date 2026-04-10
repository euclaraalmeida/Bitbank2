package br.edu.ifpb.pweb2.bitbank.service;

import java.net.PasswordAuthentication;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import br.edu.ifpb.pweb2.bitbank.repository.CorrentistaRepository;
import br.edu.ifpb.pweb2.bitbank.util.PasswordUtil;

@org.springframework.stereotype.Service
public class CorrentistaService implements Service<Correntista, Integer>{

    @Autowired
    private CorrentistaRepository correntistaRepository;

    @Autowired
    private PasswordUtil passwordUtil;

    @Override
    public List<Correntista> findAll() {
        return correntistaRepository.findAll();
    }

    @Override
    public Correntista findById(Integer id) {
        return correntistaRepository.findById(id);
    }

    @Override
    public Correntista save(Correntista c) {
       return correntistaRepository.save(c);
    }
    

    
    public Correntista validar(String email, String senha ){

        Correntista correntista_encontrado = correntistaRepository.findbyEmail(email);

        String senha_cifrada = passwordUtil.hashPassword(senha);

        if (correntista_encontrado != null && correntista_encontrado.getEmail().equals(email) && correntista_encontrado.getSenha().equals(senha_cifrada)){
            return correntista_encontrado;
        }
        return null;
    }
}
