import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class PopulateDB {
    
    public static void main(String[] args) {
	System.out.println("-------Inserting into UNIVERSITY DB-----------");

	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		System.out.println("Where is your MySQL JDBC Driver?");
		e.printStackTrace();
		return;
	}

	Connection con = null;

	try {
		con = DriverManager
		.getConnection("jdbc:mysql://localhost:3306/universitydb1","root", "password");

	} catch (SQLException e) {
		System.out.println("Connection Failed! Check output console");
		e.printStackTrace();
		return;
	}

	if (con != null) {
		System.out.println("Established connection!");
	} else {
		System.out.println("Failed to make connection!");
	}
	
	TupleGenerator tupleGen = new TupleGeneratorImpl();	
	
	tupleGen.addRelSchema ("Student",
                "id name address status",
                "Integer String String String",
                "id",
                null);

        tupleGen.addRelSchema ("Professor",
                "id name deptId",
                "Integer String String",
                "id",
                null);

        tupleGen.addRelSchema ("Course",
                "crsCode deptId crsName descr",
                "String String String String",
                "crsCode",
                null);

        tupleGen.addRelSchema ("Teaching",
                "profId crsCode semester",
                "Integer String String",
                "crcCode semester",
                new String [][] {{ "profId", "Professor", "id" },
                        { "crsCode", "Course", "crsCode" }});

        tupleGen.addRelSchema ("Transcript",
                "studId crsCode semester grade",
                "Integer String String String",
                "studId crsCode semester",
                new String [][] {{ "studId", "Student", "id"},
                        { "crsCode", "Course", "crsCode" },
                        { "crsCode semester", "Teaching", "crsCode semester" }});

	int tableSizes[] = {10000, 1000, 2000, 5000, 5000};
	Comparable[][][] result = tupleGen.generate(tableSizes);
	String insertQuery1 = "INSERT INTO STUDENT (id, sname, address, sstatus) VALUES" + "(?,?,?,?)";
	String insertQuery2 = "INSERT INTO PROFESSOR (id, pname, deptId) VALUES" + "(?,?,?)";
	String insertQuery3 = "INSERT INTO COURSE (crsCode, deptId, crsName, descr) VALUES" + "(?,?,?,?)";
	String insertQuery4 = "INSERT INTO TEACHING (profId, crsCode, semester) VALUES" + "(?,?,?)";
	String insertQuery5 = "INSERT INTO TRANSCRIPT (studId, crsCode, semester, grade) VALUES" + "(?,?,?,?)";

	String [] queries = {insertQuery1, insertQuery2, insertQuery3, insertQuery4, insertQuery5}; 
	PreparedStatement preparedStatement = null;

	/*
	for (int i = 0; i < result.length; i++) {
	    for (int j = 0; j <result[i].length; j++) {
		for (int k = 0; k < result[i][j].length; k++) {
		    System.out.println(result[i][j][k]);
		}
	    }
	}
	*/

	for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[i].length; j++) {
			if(i == 0) {
			    try {
				preparedStatement = con.prepareStatement(queries[i]);
				preparedStatement.setInt(1, (Integer) result[i][j][0]);
				preparedStatement.setString(2,(String)  result[i][j][1]);
				preparedStatement.setString(3, (String) result[i][j][2]);
				preparedStatement.setString(4, (String) result[i][j][3]);
				preparedStatement.executeUpdate();
			    }//try
			    catch (SQLException e) {
				e.printStackTrace();
			    }//catch
			}//if
			else if(i == 1){
			    try {
				preparedStatement = con.prepareStatement(queries[i]);
				preparedStatement.setInt(1, (Integer) result[i][j][0]);
				preparedStatement.setString(2, (String) result[i][j][1]);
				preparedStatement.setString(3, (String) result[i][j][2]);
				preparedStatement.executeUpdate();
			    }//try
			    catch (SQLException e) {
				e.printStackTrace();
			    }//catch
			}//else if
			else if(i == 2) {
			    try {
				preparedStatement = con.prepareStatement(queries[i]);
				preparedStatement.setString(1, (String) result[i][j][0]);
				preparedStatement.setString(2, (String) result[i][j][1]);
				preparedStatement.setString(3, (String) result[i][j][2]);
				preparedStatement.setString(4, (String) result[i][j][3]);
				preparedStatement.executeUpdate();
			    }//try
			    catch (SQLException e) {
				e.printStackTrace();
			    }//catch
			}//else if
			else if(i == 3) {
			    try {
				preparedStatement = con.prepareStatement(queries[i]);
				preparedStatement.setInt(1, (Integer) result[i][j][0]);
				preparedStatement.setString(2, (String) result[i][j][1]);
				preparedStatement.setString(3, (String) result[i][j][2]);
				preparedStatement.executeUpdate();
			    }//try
			    catch (SQLException e) {
				e.printStackTrace();
			    }//catch
			}
			else if(i == 4) {
			    try {
				preparedStatement = con.prepareStatement(queries[i]);
				preparedStatement.setInt(1, (Integer) result[i][j][0]);
				preparedStatement.setString(2, (String) result[i][j][1]);
				preparedStatement.setString(3, (String) result[i][j][2]);
				preparedStatement.setString(4, (String) result[i][j][3]);
				preparedStatement.executeUpdate();
			    }//try
			    catch (SQLException e) {
				e.printStackTrace();
			    }//catch
			}//else if
		}//for
	}//for

	try{
	    if (preparedStatement != null) {
		preparedStatement.close();
	    }//if
	    if(con != null) {
		con.close();
	    }//if
	} catch (SQLException e) {
	    e.printStackTrace();

	}
    } //main
} //PopulateDB
