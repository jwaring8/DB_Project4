USE proj4_keys; #database with keys/indexes 

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
	  GROUP BY t.studId
      HAVING cnt = (SELECT COUNT(crsCode) FROM COURSE WHERE deptId = 'deptId424969')
	)