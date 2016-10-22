xquery version "3.0";
declare variable $yoId as xs:string external;
declare variable $adversarioId as xs:string external;

let $tipoContrincante := doc("Abedemon.xml")//especie[@id = $adversarioId]/tipo/text()
let $ataquesYo := doc("Abedemon.xml")//especie[@id = $yoId]/ataques/tiene-ataque
let $ataqueYoDanio := doc("Abedemon.xml")//ataque[@id=$ataquesYo/@id]/daño
let $ataqueMasEfectivo := max($ataqueYoDanio[@tipo = $tipoContrincante]/@valor)

return $ataqueMasEfectivo