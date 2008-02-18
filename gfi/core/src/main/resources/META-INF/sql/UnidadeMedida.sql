SELECT cd_unidade_medida,descricao_unidade,version
FROM {{schema}}.inventario_unidade_medida
#if ($unidadeMedida.cdUnidadeMedida) 
    WHERE cd_unidade_medida = :unidadeMedida.cdUnidadeMedida 
#end 
#if ($ByPk) 
    WHERE cd_unidade_medida = :unidadeMedida.cdUnidadeMedida 
#end 
#if ($like) 
    WHERE        
	cd_unidade_medida like :unidadeMedida.cdUnidadeMedida OR descricao_unidade like :unidadeMedida.descricaoUnidade
#end 
INSERT INTO {{schema}}.inventario_unidade_medida (cd_unidade_medida, descricao_unidade, version)
VALUES  (:unidadeMedida.cdUnidadeMedida, :unidadeMedida.descricaoUnidade, :unidadeMedida.version)
UPDATE {{schema}}.inventario_unidade_medida 
SET 
    cd_unidade_medida = :unidadeMedida.cdUnidadeMedida,
    descricao_unidade = :unidadeMedida.descricaoUnidade,
    version	= :unidadeMedida.version
WHERE
    cd_unidade_medida  = :unidadeMedida.cdUnidadeMedida
DELETE FROM {{schema}}.inventario_unidade_medida
#if ($unidadeMedida.cdUnidadeMedida) 
    WHERE cd_unidade_medida = :unidadeMedida.cdUnidadeMedida 
#end 
