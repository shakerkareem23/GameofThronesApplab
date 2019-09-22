package com.example.gameofthrones;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Fragment fragment;
    ArrayList<Character> listofcharacter= new ArrayList<Character>();
    ArrayList<String> characterNames=new ArrayList<String>();
    ArrayList<String>  imageUrls=new ArrayList<String>();
    ArrayList<String>  houseImageUrls=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jsonurl();
        getcharacternamesandphotos();
    }

    private void jsonurl() {
        String URL ="https://android-challenge-3472e.firebaseio.com/characters.json?print=pretty";

        JsonArrayRequest request= new JsonArrayRequest(Request.Method.GET,URL,null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                for (int i=0;i<response.length();i++){
                    try {

                        JSONObject Character=response.getJSONObject(i);
                        String name =Character.getString("name");
                        String imgUrl=Character.getString("imageUrl");
                        String houseName=Character.getString("houseName");
                        String houseImageUrl=Character.getString("houseImageUrl");
                        String houseId=Character.getString("houseID");
                        String description=Character.getString("description");
                        Character objectcharacter=new Character(name,imgUrl,houseName,houseImageUrl,houseId,description);
                        listofcharacter.add(objectcharacter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

    }

    public void houses_fragment(View view) {

        ListView houseview=(ListView) findViewById(R.id.listviewhouses);

        ArrayList<ImageView> imageViews= new ArrayList<ImageView>();

        for(int i=0;i<imageUrls.size();i++){
            ImageView img =(ImageView)findViewById(R.id.imageView2);
            Picasso.with(this).load(imageUrls.get(i)).into(img);
            imageViews.add(img);
        }

        ArrayAdapter arrayhouse =new ArrayAdapter<ImageView>(this,R.layout.house_rows,R.id.imageView2,imageViews);
        houseview.setAdapter(arrayhouse);



        fragment= new GoTHousesListFragment();
        FragmentManager fragmentManager= getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment,fragment);
        fragmentTransaction.commit();

    }
    public void getcharacternamesandphotos(){

    for (int i=0;i<listofcharacter.size();i++){
        characterNames.add(listofcharacter.get(i).name);
        imageUrls.add(listofcharacter.get(i).imageurl);
        houseImageUrls.add(listofcharacter.get(i).houseImageUrl);

    }


    }
    public void characters_fragment(View view) {

       ListView CharacterView =  (ListView) findViewById(R.id.listviewcharacters);


        ArrayList<String> lscharacter = new ArrayList<String>();

        for( int i=1;i<=5;i++){

            lscharacter.add(i + " - New Character");
        }

        ArrayAdapter<String> arraycharacter =new ArrayAdapter<String>(this,R.layout.character_row,R.id.textChar, lscharacter);

        CharacterView.setAdapter(arraycharacter);

//        CharacterView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this,parent.getItemAtPosition(position)+"",Toast.LENGTH_SHORT).show();
//                //switch activity to detailed
//            }
//        });

        fragment= new GoTListFragment();
        FragmentManager fragmentManager= getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment,fragment);
        fragmentTransaction.commit();

    }
}
