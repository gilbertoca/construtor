package br.gov.to.secad.seg.service;

import br.gov.to.secad.seg.domain.Audit;
import br.gov.to.secad.seg.domain.EventType;
import br.gov.to.secad.seg.repository.IAuditRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author gilberto
 */
public class SegJsfAuditoriaService implements IAuditoriaService {
    @Autowired
    private IAuditRepository auditRepository;
    
    public SegJsfAuditoriaService() { }

    public String getCurrentUser() {
        //return UserSession.get().getUser().getCpf();
        return null;
    }
    
    public void audit(Object o, Object id, String ObjectName, EventType eventType) {
        Audit audit = new Audit();
        //String origem = UserSession.get().getClientInfo().getProperties().getRemoteAddress(); 
        //audit.setDescription("ORIGEM: "+origem+" MODIFICOU: "+o.toString());
        audit.setEventType(eventType);
        audit.setRecordId(id.toString());
        audit.setUserId(getCurrentUser());
        audit.setTableName(ObjectName);
        audit.setEventDate(new Date());
        auditRepository.save(audit);
    }
    
}
