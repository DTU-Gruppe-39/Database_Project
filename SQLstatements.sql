-- opg 1 check
-- finder raavare_id, -navn og på den raavare, der forekommer i raavarebatch 2 eller flere gange
select a.raavare_id, raavare_navn, count(*) as count from raavarebatch a
natural join raavare b
group by a.raavare_id
having (count >= 2);


-- Opg 2  check
-- Laver en relation med attributerne recept_id, recept_navn og raavare_navn fra recept og raavare
Create table OPG as 
Select recept_id, recept_navn, raavare_navn FROM recept, raavare
group by recept_id, raavare_navn;
-- Viser alt fra tabellen ovenover
select * from OPG;


-- champignon eller skinke (3.1) check
select distinct recept_id from receptkomponent
natural join raavare
where raavare_navn in ('champignon', 'skinke');


-- champignon og skinke (3.2) check
select recept_id from receptkomponent
natural join raavare
where raavare_navn in ('champignon', 'skinke')
group by recept_id
having count(distinct raavare_navn) = 2;

-- ingen champignon (4) check
select recept_navn from recept 
where recept_id != (select recept_id from receptkomponent
natural join raavare
where raavare_navn = "champignon");

-- (5) check
select recept_navn from recept 
natural join receptkomponent 
natural join raavare 
where raavare_navn = "tomat" and 
nom_netto = (select max(nom_netto) from receptkomponent 
natural join raavare 
where raavare_navn = "tomat");



-- 6 check
create table produktnetto (
select pb_id, raavare_navn, netto from produktbatchkomponent a
natural join raavarebatch b
natural join raavare c
);
select * from produktnetto;

    
-- 7  check
select pb_id from produktbatchkomponent
natural join raavarebatch
natural join raavare
where raavare_navn = "tomat" and 
netto = (select max(netto) from produktbatchkomponent
natural join raavarebatch
natural join raavare
where raavare_navn = "tomat");

-- 8 check
select distinct opr_navn from operatoer a
natural join produktbatchkomponent b
natural join produktbatch c
natural join recept d
where recept_navn = 'margherita';

-- 9 check
select distinct a.pb_id, status, raavare_navn, nom_netto, recept_navn, opr_navn from produktbatchkomponent a
natural join produktbatch b
natural join receptkomponent c
natural join raavare d
natural join operatoer e
natural join recept f
order by pb_id;

-- Q1 check
select count(*) from produktbatchkomponent
where netto > 10;

-- Q2 check
select sum(maengde) from raavarebatch
natural join raavare
where raavare_navn = 'tomat';

-- Q3 check
select raavare_navn, sum(maengde) from raavarebatch
natural join raavare
group by raavare_navn;

-- Q4 check
select count(*) as antal, raavare_navn, recept_id from raavare
natural join receptkomponent
group by raavare_navn
having antal >= 3;