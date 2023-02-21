import mynosqldb.MyNoSQL;

import org.json.JSONArray;
import org.json.JSONObject;


public class Main {
    public static void main(String[] args) {
        //Creating DataBase
        MyNoSQL db = new MyNoSQL("test");

        //Creating 1st collection
        db.createCollection("school");
        //Creating 2nd collection 2
        db.createCollection("person");

        //Creating JSON Object 1
        JSONObject jsObj1 = new JSONObject();
        jsObj1.put("name", "shubhpathak");
        jsObj1.put("age", "22");

        //Creating JSON Object 2
        JSONObject jsObj2 = new JSONObject();
        jsObj2.put("name", "shubh123");
        jsObj2.put("age", "20");

        //Inserting in DB
        db.insertDocument("person",jsObj1);
        db.insertDocument("person",jsObj2);

        //Getting All Data
        JSONArray result = db.findAll("person");
        if(result!=null){
            for (int i = 0; i < result.length(); i++) {
                JSONObject tempObj = result.getJSONObject(i);
                System.out.println(tempObj);
            }
        }

        //Updating Data

        //Creating JSON Object 3
        JSONObject jsObj3 = new JSONObject();
        jsObj3.put("hello", "world");

        db.updateDocument("person",jsObj3,"1");

        //Getting Data based on ID
        System.out.println(db.findOne("person","2"));

        //Dropping Collection
        db.dropCollection("school");
    }
}