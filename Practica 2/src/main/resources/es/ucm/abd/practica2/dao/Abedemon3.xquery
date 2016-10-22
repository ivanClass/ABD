xquery version "3.0";
declare variable $id as xs:string external;

for $especieRS in doc("Abedemon.xml")//especie[@id = $id]
return <html>
    <head></head>
    <body>
        <h1> {$especieRS/nombre/text()} </h1>
        <img src="{$especieRS/url/text()}"/>
        <p> {$especieRS/descripcion/text()} </p>
        <p><b>Tipos: </b> {string-join($especieRS/tipo/text(),', ')} </p>
        <p><b>Ataques: </b> {string-join(for $at in doc("Abedemon.xml")//ataque[@id =$especieRS/ataques/tiene-ataque/@id]/nombre/text() order by $at return $at,', ')}</p>
        <p><b>Evoluciones: </b>
            <ul>
                {for $ev in $especieRS//evoluciones/especie
                return <li><a href="{$ev/@id}"> {$ev/nombre/text()} </a></li>}
            </ul>
        </p>
    </body>
</html>