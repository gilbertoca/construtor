--ATENÇÃO: este script deve ser executado após a excução do script tab.sql e com a sessão aberta como usuário reca_user
--para os objetos:
GRANT ALL ON TABLE modulo.audit TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.grupo TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.grupo_permissoes TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.permissoes TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.usuario TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.usuario_grupo TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.orgao TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.cargo TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.municipio TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.lotacao TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.estado_civil TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.escolaridade TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.racas TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.necessidades_especiais TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.paises TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.nacionalidade TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.servidor_escolaridade TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.servidor_capacitacao TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.servidor_idioma TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.servidor_sigesp_licenca TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.servidor_sigesp_disposicao TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.servidor TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.servidor_sigesp TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.situacao_funcional TO GROUP modulo WITH GRANT OPTION;

--SEQUENCE
GRANT ALL ON TABLE modulo.audit_id_seq TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.permissoes_id_seq TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.grupo_id_seq TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.usuario_id_seq TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.servidor_escolaridade_id_seq TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.servidor_capacitacao_id_seq TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.servidor_idioma_id_seq TO GROUP modulo WITH GRANT OPTION;


--VIEWS
GRANT ALL ON TABLE modulo.vw_municipio TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.vw_orgao TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.vw_quadro TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.vw_cargo TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.vw_servidor TO GROUP modulo WITH GRANT OPTION;
GRANT ALL ON TABLE modulo.vw_quantitativo_p_letra TO GROUP modulo WITH GRANT OPTION;