SELECT 
cd_unidade_medida,
descricao_unidade,
version
FROM 
gfi.unidade_medida
#if ($ByUnidadeMedida) 
WHERE cd_unidade_medida = :cdUnidadeMedida 
#end 
