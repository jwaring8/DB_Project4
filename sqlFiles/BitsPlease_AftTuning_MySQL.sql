USE proj4_keys; #database with keys/indexes 

#Author of queries 1 - 3: Edvin Dizdarevic

#Query1: List the name of the student with id equal to v1 (id).
SELECT s.sname 
FROM STUDENT AS s 
WHERE s.id = '545899';

#Query2: List the names of students with id in the range of v2 (id) to v3 (inclusive).
SELECT s.sname
FROM STUDENT AS s
WHERE s.id BETWEEN '15192' AND '17138';

#Query3: List the names of students who have taken course v4 (crsCode).
SELECT s.sname
FROM STUDENT AS s
WHERE s.id = ANY (SELECT sid.studId
            FROM TRANSCRIPT AS sid
            WHERE sid.crsCode = 'crsCode902901');

#Author of queries 4 - 6: Jonathan Waring

#Query 4: List the names of students who have taken a course taught by professor v5 (name)
SELECT s.sname
FROM STUDENT AS s INNER JOIN TRANSCRIPT AS r ON r.studId=s.id INNER JOIN TEACHING AS t ON r.crsCode=t.crsCode
WHERE t.profId=491584;

#Query 5: List the names of students who have taken a course from department v6 (deptId), but not v7
SELECT s.sname
FROM STUDENT AS s INNER JOIN TRANSCRIPT AS t ON s.id=t.studId INNER JOIN COURSE AS c ON t.crsCode=c.crsCode
WHERE c.deptId='deptId664077' AND c.deptId!='deptId424969';

#Query 6: List the names of students who have taken all courses offered by department v8 (deptId)
#INSERT INTO COURSE VALUES ('crsCode102810', 'deptId424969', 'crsName123456', 'descr123456'); 

SELECT s.sname 
FROM STUDENT AS s
WHERE(SELECT COUNT(c.crsCode) AS cnt
	  FROM STUDENT AS s INNER JOIN TRANSCRIPT AS t ON t.studId=s.id INNER JOIN COURSE AS c ON t.crsCode=c.crsCode
	  WHERE c.deptId = 'deptId424969'
	  GROUP BY s.sname
      HAVING cnt = (SELECT COUNT(crsCode) FROM COURSE WHERE deptId = 'deptId424969')
	)