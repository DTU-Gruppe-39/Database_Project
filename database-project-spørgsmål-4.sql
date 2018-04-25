-- opg 4 many subqueries version 
select recept_navn from recept 
where recept_id != (select recept_id from receptkomponent where raavare_id =(select raavare_id from raavare where raavare_navn = "champignon"));

-- opg 4 natural join version
select recept_navn from recept 
where recept_id != (select recept_id from receptkomponent
natural join raavare
where raavare_navn = "champignon");

-- opg 5 many subqueries version 
select recept_navn from recept 
where recept_id in (select recept_id from receptkomponent
where raavare_id in (select raavare_id from raavare where raavare_navn = "tomat") and nom_netto =
(select max(nom_netto) from receptkomponent where raavare_id in (select raavare_id from raavare where raavare_navn = "tomat")));

-- opg 5 natural join version 
select recept_navn from recept 
natural join receptkomponent 
natural join raavare 
where raavare_navn = "tomat" and 
nom_netto = (select max(nom_netto) from receptkomponent 
natural join raavare 
where raavare_navn = "tomat");

-- opg 5 join version
select recept_navn from recept as r
join receptkomponent as rk on r.recept_id = rk.recept_id
join raavare as rv on rk.raavare_id = rv.raavare_id
where raavare_navn = "tomat" and 
nom_netto = (select max(nom_netto) from receptkomponent as rk2
join raavare as rv2 on rk2.raavare_id = rv2.raavare_id
where raavare_navn = "tomat");



-- opg 7  many subqueries version
select pb_id from produktbatchkomponent
where rb_id in (select rb_id from raavarebatch 
where raavare_id in (select raavare_id from raavare where raavare_navn = "tomat") and 
netto = (select max(netto) from produktbatchkomponent 
where rb_id in (select rb_id from raavarebatch 
where raavare_id in (select raavare_id from raavare where raavare_navn = "tomat"))));


-- opg 7 distinct natural join version
select distinct pb_id from produktbatchkomponent
natural join raavarebatch, raavare
where raavare_navn = "tomat" and 
netto = (select max(netto) from produktbatchkomponent
natural join raavarebatch, raavare
where raavare_navn = "tomat");


-- opg 7 distinct natural join version
select pb_id from produktbatchkomponent
natural join raavarebatch
natural join raavare
where raavare_navn = "tomat" and 
netto = (select max(netto) from produktbatchkomponent
natural join raavarebatch
natural join raavare
where raavare_navn = "tomat");