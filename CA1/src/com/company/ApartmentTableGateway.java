package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApartmentTableGateway {
    private static final String TABLE_NAME = "apartments";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_COST = "cost";
    private static final String COLUMN_BEDROOMNUMBER = "bedroomNumber";
    private static final String COLUMN_MAINTENANCE = "maintenance";
    private static final String COLUMN_PARKINGSPACE = "parkingSpace";


    private Connection mConnection;

    public ApartmentTableGateway(Connection connection) {
        mConnection = connection;
    }

    public boolean updateApartment(Apartment p){

        String query;                   // the SQL query to execute
        PreparedStatement stmt;         // the java.sql.PreparedStatement object used to execute the SQL query
        int numRowsAffected;

        query = "UPDATE " + TABLE_NAME + " SET " +
                COLUMN_ADDRESS + " = (?), " +
                COLUMN_COST + " = (?), " +
                COLUMN_BEDROOMNUMBER + " = (?), " +
                COLUMN_MAINTENANCE + " = (?), " +
                COLUMN_PARKINGSPACE + " = (?) " +
                "WHERE " + COLUMN_ID + " = (?)";



        try {
            // create a PreparedStatement object to execute the query and insert the values into the query
            stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, p.getAddress());
            stmt.setDouble(2, p.getCost());
            stmt.setString(3, p.getBedroomNumber());
            stmt.setString(4, p.getMaintenance());
            stmt.setBoolean(5, p.getParkingSpace());
            stmt.setInt(6, p.getId());
            System.out.println(stmt);


            //  execute the query and make sure that one and only one row was inserted into the database
            numRowsAffected = stmt.executeUpdate();


            // if numRowsAffected is 1 - that means the SQL insert inserted one row into the table, so the ID should have been auto-incremented and sent back here.
            // so assign this new ID to the ID.
            if (numRowsAffected == 1) {
//                    // if one row was inserted, retrieve the id that was assigned to that row in the database and ret
                ResultSet keys = stmt.getGeneratedKeys();
                keys.next();

//                   id = keys.getInt(1);
//                   dbProgrammer.setId(id);
                return true;
            }

        }
        catch (SQLException e)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in ApartmentTableGateway : UpdateApartment(), Check the SQL you have created to see where your error is", e);
        }

        // return the Programmer object created with the new ID, The calling program in the Model should check to ensure it is not null
        return false;
    }

    public boolean deleteApartment(int deleteId) {
        String query;                   // the SQL query to execute
        PreparedStatement stmt;         // the java.sql.PreparedStatement object used to execute the SQL query
        int numRowsAffected;

        Apartment dbApartment = null;

        // the required SQL INSERT statement with place holders for the values to be inserted into the database
        query = " DELETE FROM " + TABLE_NAME + " " +
                "WHERE " + COLUMN_ID + " = (?)";
        System.out.println(query);

        try {
            // create a PreparedStatement object to execute the query and insert the values into the query
            stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, deleteId);
            System.out.println(stmt);



        //  execute the query and make sure that one and only one row was inserted into the database
        numRowsAffected = stmt.executeUpdate();


        // if numRowsAffected is 1 - that means the SQL insert inserted one row into the table, so the ID should have been auto-incremented and sent back here.
        // so assign this new ID to the ID.
        if (numRowsAffected == 1) {
//                    // if one row was inserted, retrieve the id that was assigned to that row in the database and ret
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();

//                   id = keys.getInt(1);
//                   dbProgrammer.setId(id);
            return true;
        }

    }
        catch (SQLException e)
    {
        Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in ApartmentTableGateway : insertApartment(), Check the SQL you have created to see where your error is", e);
    }

    // return the Programmer object created with the new ID, The calling program in the Model should check to ensure it is not null
        return false;
}


    // Called from the Model when the user wants to create a new programmer in the database, the new ID is created in the database and returned here
    public boolean insertApartment(Apartment p)  {

        String query;                   // the SQL query to execute
        PreparedStatement stmt;         // the java.sql.PreparedStatement object used to execute the SQL query
        int numRowsAffected;

        // I am going to create a new Programmer object, this object will have the Id after the row is inserted into the database
        Apartment dbApartment = null;

        // the required SQL INSERT statement with place holders for the values to be inserted into the database
        query = "INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_ADDRESS + ", " +
                COLUMN_COST + ", " +
                COLUMN_BEDROOMNUMBER + ", " +
                COLUMN_MAINTENANCE + ", " +
                COLUMN_PARKINGSPACE +
                ") VALUES (?, ?, ?, ?, ?)";



        try {
            // create a PreparedStatement object to execute the query and insert the values into the query
            stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, p.getAddress());
            stmt.setDouble(2, p.getCost());
            stmt.setString(3, p.getBedroomNumber());
            stmt.setString(4, p.getMaintenance());
            stmt.setBoolean(5, p.getParkingSpace());
            System.out.println(stmt);


            //  execute the query and make sure that one and only one row was inserted into the database
            numRowsAffected = stmt.executeUpdate();


            // if numRowsAffected is 1 - that means the SQL insert inserted one row into the table, so the ID should have been auto-incremented and sent back here.
            // so assign this new ID to the ID.
            if (numRowsAffected == 1) {
//                    // if one row was inserted, retrieve the id that was assigned to that row in the database and ret
                ResultSet keys = stmt.getGeneratedKeys();
                keys.next();

//                   id = keys.getInt(1);
//                   dbProgrammer.setId(id);
                return true;
            }

        }
        catch (SQLException e)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in ApartmentTableGateway : insertApartment(), Check the SQL you have created to see where your error is", e);
        }

        // return the Programmer object created with the new ID, The calling program in the Model should check to ensure it is not null
        return false;
    }


    // Called from the Model to get the complete list of programmers from the programmer table in the database
    public List<Apartment> getApartments()  {
        String query;                   // the SQL query to execute

        List<Apartment> apartments = new ArrayList<>(); // the java.util.List containing the Programmer objects created for each row
        // in the result of the query the id of a programmer
        int id;
        String address;
        double cost;
        String bedroomNumber;
        String maintenance;
        boolean parkingSpace;



        Apartment p;                   // a Programmer object created from a row in the result of the query

        // execute an SQL SELECT statement to get a java.util.ResultSet representing
        // the results of the SELECT statement
        query = "SELECT * FROM " + TABLE_NAME;

        try {
            Statement stmt;                 // the java.sql.Statement object used to execute the SQL query
            ResultSet rs;                   // the java.sql.ResultSet representing the result of SQL query

            stmt = this.mConnection.createStatement();
            // rs is a ResultSet object. It contains the rows of data from the database.
            rs = stmt.executeQuery(query);


            // loop through the result set taking out the programmer data from the DB
            // create a prorgammer object with this data and pop it into an arraylist
            while (rs.next()) {
                id = rs.getInt(COLUMN_ID);
                address = rs.getString(COLUMN_ADDRESS);
                cost = rs.getDouble(COLUMN_COST);
                bedroomNumber = rs.getString(COLUMN_BEDROOMNUMBER);
                maintenance = rs.getString(COLUMN_MAINTENANCE);
                parkingSpace = rs.getBoolean(COLUMN_PARKINGSPACE);

                p = new Apartment(id, address, cost, bedroomNumber, maintenance, parkingSpace);
                apartments.add(p);
            }
        }

        catch (SQLException e){
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in ApartmentTableGateway : getApartment(), Check the SQL you have created to see where your error is", e);
        }

        // return the arraylist of Programmer objects to the model.
        return apartments;
    }
}
