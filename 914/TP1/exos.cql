// 1
match (d:Departement)-[:WITHIN]->(r:Region {name:'OCCITANIE'})
return count(*) as nombreDeps

// 2
match (d:Departement)-[:WITHIN]->(r:Region)
return r.name as region, count(d) as nombreDep;

// 3
match (d:Departement)-[:WITHIN]->(r:Region)
with r, collect(d.name) as deps
where size(deps) >= 6
return r.name as region, size(deps) as nbDeps;

// 4
match (m:Commune {name:'MONTPELLIER'})
match (c:Commune)-[:WITHIN]->(d:Departement)
where (c)-[:NEARBY]->(m)
return c.name as commune, d.name as departement;

// 5 
match (m:Commune {name:'MONTPELLIER'})
match (c:Commune)-[:WITHIN]->(d:Departement)
where (c)-[:NEARBY]->(m)
and d.name <> 'HERAULT'
return c.name as commune,  c.codeinsee as code, d.name as departement;

// 6
match (c:Commune)-[:WITHIN]->(d:Departement)
match (d)-[:WITHIN]->(r:Region)
return c.name as commune, d.name as dep, r.name as region;

// 7
match p=((m:Commune {name:'MONTPELLIER'})-[:NEARBY*]-(c:Commune {name: 'NIMES'}))
return p;

// 8
match p=((m:Commune {name:'MONTPELLIER'})-[:NEARBY*]-(c:Commune {name: 'NIMES'}))
return min(p);


