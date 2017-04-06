USE proj4; #database without keys/indexes 

#Query 4: List the names of students who have taken a course taught by professor v5 (491584)
SELECT s.sname
FROM STUDENT AS s, TRANSCRIPT AS r, TEACHING AS t
WHERE t.profId=491584 AND r.crsCode=t.crsCode AND r.studId=s.id;

#Query 5: List the names of students who have taken a course from department v6 (deptId), but not v7
SELECT s.sname
FROM STUDENT AS s, TRANSCRIPT AS t, COURSE AS c
WHERE t.crsCode=c.crsCode AND c.deptId='deptId664077' AND t.studId=s.id AND c.deptId!='deptId424969';

#Query 6: List the names of students who have taken all courses offered by department v8 (deptId)
#INSERT INTO COURSE VALUES ('crsCode102810', 'deptId424969', 'crsName123456', 'descr123456'); 

SELECT s.sname 
FROM STUDENT AS s, COURSE AS c, TRANSCRIPT AS t
WHERE t.crsCode=c.crsCode AND t.studId=s.id AND c.deptId='deptId424969'
GROUP BY s.id
HAVING COUNT(*) = (SELECT COUNT(*) FROM COURSE WHERE COURSE.deptId='deptId424969');