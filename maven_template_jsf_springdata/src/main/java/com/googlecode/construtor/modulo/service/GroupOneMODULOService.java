package com.googlecode.construtor.modulo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.construtor.modulo.domain.EstadoCivil;
import com.googlecode.construtor.modulo.repository.IEstadoCivilRepository;
import java.util.Arrays;

/**
 * Classe agrupamento de serviços. Aqui agruparemos os serviços referentes às
 * seguintes classes de domínio: Municipio, Logradouro, Nacionalidade,
 * Escolaridade, EstadoCivil.
 * <p/>
 * @author gilberto
 */
@Service
public class GroupOneMODULOService {

    @Autowired
    private IEstadoCivilRepository estadoCivilRepository;
    static {
        List<EstadoCivil> scList = Arrays.asList(new EstadoCivil(1, "CASADO"), new EstadoCivil(2, "SOLTEIRO"), new EstadoCivil(3, "DIVORCIADO"));
        estadoCivilRepository.save(scList);
    }
    
    public GroupOneMODULOService() {
    }

    public String getDateFormat() {
        return "dd/MM/yyyy";
    }

    //EstadoCivil
    public List<EstadoCivil> findAllEstadoCivil() {
        return estadoCivilRepository.findAll();
    }

    public EstadoCivil findOneEstadoCivil(Integer id) {
        return estadoCivilRepository.findOne(id);
    }

    //Hoje
    public Date getHoje() {
        return new Date(System.currentTimeMillis());
    }

}
