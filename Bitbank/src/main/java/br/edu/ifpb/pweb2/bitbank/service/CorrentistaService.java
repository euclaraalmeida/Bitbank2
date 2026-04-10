package br.edu.ifpb.pweb2.bitbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import br.edu.ifpb.pweb2.bitbank.repository.CorrentistaRepository;
import br.edu.ifpb.pweb2.bitbank.util.PasswordUtil;

@Component
public class CorrentistaService implements Service<Correntista, Integer>{

    @Autowired
    private CorrentistaRepository correntistaRepository;


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

    if (correntista_encontrado != null) {
        
        // Ele pega a senha digitada (texto limpo) e compara com as ultimos carecteres do Hash guardado no banco
        boolean senhaValida = PasswordUtil.checkPass(senha, correntista_encontrado.getSenha());

        if (senhaValida && correntista_encontrado.getEmail().equals(email)) {
            return correntista_encontrado;
        }
    }
    
    return null;
}
    }

