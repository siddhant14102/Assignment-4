package com.example.siddhantverma.assignment41;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.siddhantverma.assignment41.MainActivity;
import com.example.siddhantverma.assignment41.R;
import com.example.siddhantverma.assignment41.model.Listitem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class popup extends Activity implements Serializable {

    private Button button;
    private TextView task;
    private TextView taskdetail;

    public static  ArrayList<Listitem> newlist = new ArrayList<>();
    String FILENAME="database";
    public void WriteObjectToFile(Object serObj) {


        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream objectOut = new ObjectOutputStream(fos);
            objectOut.writeObject(serObj);
            objectOut.close();
            fos.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    public void read(){
        ObjectInputStream input;
        String filename = "database";

        try {
            input = new ObjectInputStream(new FileInputStream(new File(new File(getFilesDir(),"")+ File.separator+filename)));
            popup.newlist  = (ArrayList<Listitem>)input.readObject();
            input.close();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }

        if(popup.newlist==null) {
            popup.newlist=new ArrayList<>();
        }
        }

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        button = (Button) findViewById(R.id.button);
        task = (EditText) findViewById(R.id.entertask);
        taskdetail = (EditText) findViewById(R.id.entersubtask);
        //resultText = (TextView) findViewById(R.id.result);

        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {

                Listitem i = new Listitem();
                i.setID(UUID.randomUUID());
                i.setTitle("Task: "+task.getText().toString());
                i.setSecondary_text("Details: "+taskdetail.getText().toString());
                newlist.add(i);
                WriteObjectToFile(newlist);


                Intent goback=new Intent(view.getContext(),MainActivity.class);
                startActivity(goback);



            }
        });
    }

}

