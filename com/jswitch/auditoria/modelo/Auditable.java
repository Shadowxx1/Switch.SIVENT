package com.jswitch.auditoria.modelo;

/**
 *
 * @author Orlando Becerra
 */
public interface Auditable {

    public Long getId();

    public AuditoriaBasica getAuditoria();

    public void setAuditoria(AuditoriaBasica auditoria);
}
