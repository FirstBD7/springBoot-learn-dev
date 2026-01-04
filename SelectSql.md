select * from biz_log;

select log_id from biz_log;

select distinct log_id id from biz_log;

select * from biz_log where log_id = 1;

select * from biz_log where log_id > 30;

SELECT * FROM biz_log WHERE LOG_ID > 20 and CITY_CODE = 610100;

SELECT * FROM biz_log WHERE LOG_ID regexp 2;

SELECT * FROM biz_log WHERE LOG_ID LIKE "%2";

SELECT * FROM biz_log WHERE LOG_ID in(10, 20, 30);

SELECT * FROM biz_log WHERE LOG_ID not in(10,20,30);

SELECT * FROM biz_log WHERE LOG_ID Between 10 and 20;

SELECT  * FROM biz_log WHERE PROPOSAL_NO is NULL;

SELECT * FROM biz_log WHERE log_id > 1010 order by LOG_ID desc;

SELECT * FROM biz_log ORDER BY PROPOSAL_NO desc, LOG_ID asc;

SELECT * FROM biz_log limit 10, 10;

SELECT * FROM biz_log ORDER BY rand() limit 2;

SELECT PROPOSAL_NO, count(*) FROM biz_log GROUP BY PROPOSAL_NO;

SELECT PROPOSAL_NO, count(*) FROM biz_log GROUP BY PROPOSAL_NO HAVING count(*) < 10;

SELECT log_id FROM biz_log WHERE LOG_ID > (SELECT avg(LOG_ID) FROM biz_log);

SELECT avg(log_id) from biz_log;
select max(log_id) from biz_log;
SELECT sum(log_id) from biz_log;
SELECT count(log_id) from biz_log where log_id < 100;

select biz_log_message.LOG_ID, biz_log.PROPOSAL_NO
FROM biz_log
INNER JOIN biz_log_message
on biz_log.LOG_ID = biz_log_message.LOG_ID;

SELECT biz_log_message.LOG_ID, biz_log.PROPOSAL_NO
FROM biz_log
LEFT JOIN biz_log_message
on biz_log.LOG_ID = biz_log_message.LOG_ID;

SELECT biz_log_message.log_id, biz_log.PROPOSAL_NO
FROM biz_log
RIGHT JOIN biz_log_message
on biz_log.LOG_ID = biz_log_message.LOG_ID;

SELECT b.LOG_ID, b.PROPOSAL_NO, b.REQUEST_TYPE, m.LOG_ID
FROM biz_log b
LEFT JOIN biz_log_message m
on b.log_id = m.LOG_ID;

