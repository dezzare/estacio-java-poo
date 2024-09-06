package com.estacio.Cadastro.services;

import com.estacio.Cadastro.dto.PessoaDTO;
import com.estacio.Cadastro.entities.Pessoa;
import com.estacio.Cadastro.repositories.PessoaRepository;
import com.estacio.Cadastro.services.exceptions.DataBaseException;
import com.estacio.Cadastro.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

//	public List<Pessoa> findAll() {
//		return repository.findAll();
//	}

    public Pessoa findById(Long id) {
        Optional<Pessoa> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Pessoa insert(Pessoa obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException error) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException error) {
            throw new DataBaseException(error.getMessage());
        }
    }

    @SuppressWarnings("deprecation")
    public Pessoa update(Long id, Pessoa obj) throws ParseException {
        try {
            Pessoa entity = repository.getOne(id);
            updateSource(entity, obj);
            return repository.save(entity);
        } catch (Exception error) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateSource(Pessoa entity, Pessoa obj) throws ParseException {
        entity.setNome(obj.getNome());
        entity.setDataDeNascimento(obj.getDataDeNascimento().toString());

    }
    public List<PessoaDTO> findAll(){
        List<Pessoa> list = repository.findAll();
        return list.stream().map(x -> new PessoaDTO(x)).collect(Collectors.toList());
    }
}