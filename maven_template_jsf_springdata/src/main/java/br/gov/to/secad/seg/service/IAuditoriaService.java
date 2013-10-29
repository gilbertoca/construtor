package br.gov.to.secad.seg.service;

import br.gov.to.secad.seg.domain.EventType;

/**
 *
 * @author gilberto
 */
public interface IAuditoriaService{
    String getCurrentUser();
    void audit(Object o, Object id, String ObjectName, EventType eventType);
}
