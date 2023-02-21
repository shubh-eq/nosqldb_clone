package mynosqldb;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;


/**
 * Objects created from MyNoSQL class will represent a Database instance.
 * This database can have multiple collections that will be stored in separate json files.
 * The filenames of the json files will be stored array (collectionsList)
 */
public class MyNoSQL {
    private String dbName;

    private ArrayList<String> collectionsList;

    //Used to maintain ObjectIDs of Documents
    private static long currentObjectNumber = 0;

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
    public boolean createCollection(String collectionName){
        File file = new File(collectionName+".json");

        try {
            boolean value = file.createNewFile();
            if (value) {
                collectionsList.add(collectionName);
                return true;
            }
        }
        catch(Exception e) {
            e.getStackTrace();
        }
        return false;
    }

    /**
     * Method of MyNoSQL Class
     * Finds All the Document in the Collection and return it a JSON Array.
     * Returns null if file is collection or document is not found.
     * @param   collectionName Name of the Collection (String)
     * @return  JSONArray
     */
    public JSONArray findAll(String collectionName){
        try (FileReader reader = new FileReader(collectionName+".json")) {
            JSONTokener data = new JSONTokener(reader);

            JSONArray js = new JSONArray(data);
            return js;
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * Method of MyNoSQL Class
     * Finds the Document in the Collection and return it a JSON Object.
     * Returns null if file is collection or document is not found.
     * @param   collectionName Name of the Collection (String)
     * @param   _id ObjectID of the Document (String)
     * @return  JSONObject
     */
    public JSONObject findOne(String collectionName, String _id){
        JSONArray prevData = findAll(collectionName);
        if(prevData!=null){
            for (int i = 0; i < prevData.length(); i++) {
                JSONObject tempObj = prevData.getJSONObject(i);

                if(tempObj.get("_id").equals(_id)){
                    return tempObj;
                }
            }
        }
        return null;
    }

    /**
     * Method of MyNoSQL Class
     * Inserts JSON Object into a collection and Returns its ObjectID
     * @param   collectionName Name of the Collection (String)
     * @param   JSONObject Data to be inserted in JSON Object
     * @return  String
     */
    public String insertDocument(String collectionName,JSONObject data){
        try{
            JSONArray prevData = findAll(collectionName);
            FileWriter Writer = new FileWriter(collectionName+".json");

            if(prevData==null){
                prevData = new JSONArray();
            }

            //Adding ObjectID
            currentObjectNumber++;
            String ObjectID = Long.toString(currentObjectNumber);
            data.put("_id",ObjectID);
            prevData.put((data));
            Writer.write(prevData.toString());
            Writer.close();

            return ObjectID;
        }
        catch (Exception e){
            e.getStackTrace();
        }
        return "";
    }

    /**
     * Method of MyNoSQL Class
     * Updates JSON Object into a collection and Returns its True if updated successfully
     * @param   collectionName Name of the Collection (String)
     * @param   JSONObject Data to be inserted in JSON Object
     * @param   _id ObjectID of the Document (String)
     * @return  None
     */
    public boolean updateDocument(String collectionName,JSONObject newData,String _id){
        JSONArray prevData = findAll(collectionName);
        if(prevData!=null){
            for (int i = 0; i < prevData.length(); i++) {
                JSONObject tempObj = prevData.getJSONObject(i);

                if(tempObj.get("_id").equals(_id)){
                    prevData.remove(i);

                    newData.put("_id",_id);
                    prevData.put(newData);

                    try {
                        FileWriter Writer = new FileWriter(collectionName+".json");

                        Writer.write(prevData.toString());
                        Writer.close();
                    }
                    catch (Exception e){
                        e.getStackTrace();
                    }

                    return false;
                }
            }
        }
        return false;
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
            File file = new File(collectionName+".json");
            boolean value = file.delete();
            return value;
        }
        catch (Exception e){
            e.getStackTrace();
        }
        return false;
    }
}