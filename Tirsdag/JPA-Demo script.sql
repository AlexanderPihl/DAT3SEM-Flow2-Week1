-- Find navn og årgang samt svømmestile for alle personer

use JPA_Demo;
select p.NAME, p.YEAR, ss.STYLENAME from PERSON p
inner join SWIMSTYLE_PERSON sp
on p.P_ID = sp.persons_P_ID
inner join SWIMSTYLE ss
on sp.styles_ID = ss.ID;
