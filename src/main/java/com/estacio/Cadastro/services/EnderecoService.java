package com.estacio.Cadastro.services;

import com.estacio.Cadastro.dto.EnderecoDTO;
import com.estacio.Cadastro.entities.Endereco;
import com.estacio.Cadastro.entities.Pessoa;
import com.estacio.Cadastro.repositories.EnderecoRepository;
import com.estacio.Cadastro.services.exceptions.DataBaseException;
import com.estacio.Cadastro.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    // public List<Endereco> findAll() {
    // return repository.findAll();
    // }
    //
    public Endereco findById(Long id) {
        Optional<Endereco> obj = repository.findById(id);
        return obj.get();
    }

    // public Endereco insert(Endereco obj) {
    //
    // return repository.save(obj);
    // }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException error) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException error) {
            throw new DataBaseException(error.getMessage());
        }
    }


    public Endereco update(Long id, EnderecoDTO objDTO) throws ParseException {
        Long moradorId = objDTO.getMorador();
        Endereco novoObj = new Endereco(
                objDTO.getId(),
                objDTO.getLogradouro(),
                objDTO.getNumero(),
                objDTO.getCidade(),
                objDTO.getCep(),
                objDTO.getPrincipal(),
                new Pessoa(moradorId, null, null));


        try {
            @SuppressWarnings("deprecation")
            Endereco entity = repository.getOne(id);
            updateSource(entity, novoObj);
            return repository.save(entity);
        } catch (Exception error) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateSource(Endereco entity, Endereco obj) throws ParseException {

        entity.setMorador(obj.getMorador());
        entity.setLogradouro(obj.getLogradouro());
        entity.setNumero(obj.getNumero());
        entity.setCidade(obj.getCidade());
        entity.setCep(obj.getCep());
        entity.setPrincipal(obj.getPrincipal());
    }

    @Transactional(readOnly = true)
    public Page<EnderecoDTO> findAll(Pageable pageable) {
        Page<Endereco> page = repository.findAll(pageable);
        return page.map(x -> new EnderecoDTO(x));
    }

    @Transactional
    public EnderecoDTO insert(EnderecoDTO dto) {
        Endereco entity = new Endereco();
        entity.setMorador(new Pessoa(dto.getMorador(), null, null));
        entity.setLogradouro(dto.getLogradouro());
        entity.setNumero(dto.getNumero());
        entity.setCidade(dto.getCidade());
        entity.setCep(dto.getCep());
        entity.setPrincipal(dto.getPrincipal());

        entity = repository.save(entity);

        return new EnderecoDTO(entity);
    }

}