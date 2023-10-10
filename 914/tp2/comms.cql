MATCH (c:Commune {name:"MONTPELLIER"})
CREATE 
    (gf:Personne {nom:"FRECHE",prenom:"GEORGES"}) <-[ap1:ADMINISTREE_PAR {date_debut:1997, date_fin:2004}]- (c),
    (hm:Personne {nom:"MANDROUX",prenom:"HELENE"}) <-[ap2:ADMINISTREE_PAR {date_debut:2004, date_fin:2014}]-(c), 
    (ps:Personne {nom:"SAUREL",prenom:"PHILIPPE"}) <-[ap3:ADMINISTREE_PAR {date_debut:2014, date_fin:2020}]-(c), 
    (md:Personne {nom:"DELAFOSSE",prenom:"MICKAEL"})<-[ap4:ADMINISTREE_PAR {date_debut:2020, date_fin:2026}]-(c)
return *



// ajout de label Maire au personnes associées à une commune par administre_par
match (p: Personne) <-[:ADMINISTREE_PAR]- (c: Commune)
set p :Maire


// lister procedures disponibles avec les jars
call dbms.procedures() yield name return name

// export json
call apoc.export.json.query(
    "match (c: Commune)-[]-(d:Departement)-[]-(r: Region)
    return id(c) as id, 
           c.name as name,
           labels(c) as label, 
           d.name as departement, 
           r.name as region",
    "jonson.json", {}
)



// get montpellier avec id as rdf
:post /rdf/cypher {"cypher":"match (c:Commune) where id(c)=17 return c", "format": "RDF"}



:post /rdf/cypher {"cypher":"match(:Commune)-[r:ADMINISTREE_PAR]->(:Maire) return r", "format": "RDF"}


// res differents
:post /rdf/cypher {"cypher":"match (c:Commune)-[]-(m: Maire) where id(c)=17 return c, m, labels(c), labels(m)", "format": "RDF"}

:post /rdf/cypher {"cypher":"match (c:Commune {name: 'MONTPELLIER'})-[]-(m: Maire) return c,m", "format": "RDF"}




WITH ' PREFIX sch: <http://schema.org/> 
CONSTRUCT{ ?item a sch:City; 
           sch:address ?inseeCode; 
           sch:name ?itemLabel ; 
           sch:geoTouches ?otherItem .
	   ?otherItem a sch:City;	
	   sch:name ?otheritemLabel ;
	   sch:address ?otherinseeCode . } 
WHERE { ?item wdt:P374 ?inseeCode .
	?item wdt:P47 ?otherItem .
	?otherItem wdt:P374 ?otherinseeCode .
        ?item rdfs:label ?itemLabel . 
          filter(lang(?itemLabel) = "fr")  . 
        ?otherItem rdfs:label ?otheritemLabel . 
          filter(lang(?otheritemLabel) = "fr")  .
	 FILTER regex(?inseeCode, "^34")  .
        } ' AS sparql CALL semantics.importRDF(
  "https://query.wikidata.org/sparql?query=" +  
      apoc.text.urlencode(sparql),"JSON-LD", 
    { headerParams: { Accept: "application/ld+json"} })
YIELD terminationStatus, triplesLoaded,namespaces,extraInfo
RETURN terminationStatus, triplesLoaded,namespaces,extraInfo



match (c:sch__City {sch__name: "Montpellier"}), (c1:sch__City {sch__name: "Beaulieu"}),
path = (c)-[:sch__geoTouches*5]-(c1)
with extract(n in nodes(path) | n.sch__name) as names, path
where not ("Clapiers" in names)
return path