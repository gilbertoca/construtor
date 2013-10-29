--ATENÇÃO: este script deve ser executado após a excução do script tab.sql e com a sessão aberta como usuário reca_user
--para os objetos:
GRANT ALL ON TABLE reca.audit TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.grupo TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.grupo_permissoes TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.permissoes TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.usuario TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.usuario_grupo TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.orgao TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.cargo TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.municipio TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.lotacao TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.estado_civil TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.escolaridade TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.racas TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.necessidades_especiais TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.paises TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.nacionalidade TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.servidor_escolaridade TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.servidor_capacitacao TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.servidor_idioma TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.servidor_sigesp_licenca TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.servidor_sigesp_disposicao TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.servidor TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.servidor_sigesp TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.situacao_funcional TO GROUP reca WITH GRANT OPTION;

--SEQUENCE
GRANT ALL ON TABLE reca.audit_id_seq TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.permissoes_id_seq TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.grupo_id_seq TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.usuario_id_seq TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.servidor_escolaridade_id_seq TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.servidor_capacitacao_id_seq TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.servidor_idioma_id_seq TO GROUP reca WITH GRANT OPTION;


--VIEWS
GRANT ALL ON TABLE reca.vw_municipio TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.vw_orgao TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.vw_quadro TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.vw_cargo TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.vw_servidor TO GROUP reca WITH GRANT OPTION;
GRANT ALL ON TABLE reca.vw_quantitativo_p_letra TO GROUP reca WITH GRANT OPTION;