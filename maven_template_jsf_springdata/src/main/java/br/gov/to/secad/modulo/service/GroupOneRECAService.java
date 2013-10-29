package br.gov.to.secad.modulo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.to.secad.modulo.domain.EstadoCivil;
import br.gov.to.secad.modulo.repository.IEstadoCivilRepository;

/**
 * Classe agrupamento de serviços. Aqui agruparemos os serviços referentes às
 * seguintes classes de domínio: Orgao, Cargo, Municipio, Lotacao, Nacionalidade, Escolaridade,
 * EstadoCivil.
 * <p/>
 * @author gilberto
 */
@Service
public class GroupOneRECAService {

    @Autowired
    private IEstadoCivilRepository estadoCivilRepository;
    
    public GroupOneRECAService() {
    }
    
    public String getDateFormat() {
    	return "dd/MM/yyyy";
    }

    
    //EstadoCivil
    public List<EstadoCivil> findAllEstadoCivil() {return estadoCivilRepository.findAll();}
    public EstadoCivil findOneEstadoCivil(Integer id){return estadoCivilRepository.findOne(id);}    

    //Hoje
    public Date getHoje() {
    	return new Date(System.currentTimeMillis());
    }
    
}
