desc member;

select * from member;

select
       row_number() over(order by id asc) as num,
       id,
       name,
       regidate
from member;

SELECT * FROM (
SELECT tb.*, @rownum := @rownum + 1 AS rNum
FROM (
SELECT * FROM board ORDER BY num DESC
) tb, (SELECT @rownum := 0) r
) ranked
WHERE rNum BETWEEN 1 AND 10;

select *
from member
limit 1,5;


