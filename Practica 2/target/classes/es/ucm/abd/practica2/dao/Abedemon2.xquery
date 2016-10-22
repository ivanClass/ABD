xquery version "3.0";
declare variable $tipo as xs:string external;
for $especieRS in doc("Abedemon.xml")//especie[tipo=$tipo]

return <resultado id="{$especieRS/@id}" nombre="{$especieRS/nombre/text()}" num-ataques="{count($especieRS/ataques/tiene-ataque)}" />