-- Admin 			Check
-- Pharmasict		Check	- Laver pizza
-- Foreman			Check	- Holder styr på hvem arbejder og produkter
-- Operator			Check	- Vejer ingredienser af

-- produktbatchkomponent er der hvor operator indsætter deres vejede ingredienser

CREATE VIEW operatorView as
select * from raavare
natural join receptkomponent
natural left join recept
order by recept_id;

CREATE VIEW PharmacistView as 
select recept_navn, raavare_navn, raavare_id from recept
natural join receptkomponent
natural join raavare;



CREATE VIEW ForemanView as 
select rb_id, opr_id, pb_id, opr_navn, ini, status, raavare_navn, leverandoer, maengde, recept_id from operatoer
natural join produktbatchkomponent
natural join produktbatch
natural join raavare
natural join raavarebatch
natural join recept
order by pb_id;




