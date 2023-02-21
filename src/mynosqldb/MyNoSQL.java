package mynosqldb;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Objects created from MyNoSQL class will represent a Database instance.
 * This database can have multiple collections that will be stored in separate json files.
 * The filenames of the json files will be stored array (collectionsList)
 */
public class MyNoSQL {
    private String dbName;

    private ArrayList<String> collectionsList;

    /**
     * Parameterized Constructor that takes database name and stores it.
     * @param   dbName  Name of the Database (String)
     * @return  None
     */
    public MyNoSQL(String dbName){
        this.dbName = dbName;
    }

    /**
     * Method of MyNoSQL Class
     * Displays all Available Collections present in the particular database.
     * @param   None
     * @return  None
     */
    public void showAllCollections(){
        System.out.println("Displaying All Collections:");
        for (String collection: collectionsList) {
            System.out.println(collection);
        }
    }

    /**
     * Method of MyNoSQL Class
     * Creates a new collection in the database
     * @param   collectionName Name of the Collection (String)
     * @return  boolean
     */
    public boolean createCollections(String collectionName){

    }


    /**
     * Method of MyNoSQL Class
     * Finds the Document in the Collection and return it a JSON Object.
     * @param   collectionName Name of the Collection (String)
     * @param   _id ObjectID of the Document (String)
     * @return  JSONObject
     */
    public JSONObject findOne(String collectionName, String _id){

    }

    /**
     * Method of MyNoSQL Class
     * Inserts JSON Object into a collection and Returns its ObjectID
     * @param   collectionName Name of the Collection (String)
     * @param   JSONObject Data to be inserted in JSON Object
     * @return  String
     */
    public String insertDocument(String collectionName,JSONObject data){

    }

    /**
     * Method of MyNoSQL Class
     * Updates JSON Object into a collection and Returns its ObjectID
     * @param   collectionName Name of the Collection (String)
     * @param   JSONObject Data to be inserted in JSON Object
     * @return  String
     */
    public String updateDocument(String collectionName,JSONObject newData){

    }

    /**
     * Method of MyNoSQL Class
     * Deletes the JSON file
     * Returns True if file collection is dropped.
     * @param   collectionName Name of the Collection (String)
     * @return  Boolean
     */
    public boolean dropCollection(String collectionName){
        try{
            File file = new File(collectionName);
            boolean value = file.delete();
            return value;
        }
        catch (Exception e){
            e.getStackTrace();
        }
        return false;
    }
}