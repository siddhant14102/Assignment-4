package com.example.siddhantverma.assignment41;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.siddhantverma.assignment41.adapter.ClassAdapter;
import com.example.siddhantverma.assignment41.model.Listitem;
import com.example.siddhantverma.assignment41.model.dataset;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Serializable, ClassAdapter.ItemClickCallback{

    private RecyclerView recView;
    private static ClassAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList list;
    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_QUOTE = "EXTRA_QUOTE";
    private static final String EXTRA_ATTR = "EXTRA_ATTR";
    private EditText task;
    private EditText taskdetail;
    private Button addItem;
    private Button dialogbutton;

    String FILENAME ="database";
    public void WriteObjectToFile(Object serObj) {


        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream objectOut = new ObjectOutputStream(fos);
            objectOut.writeObject(serObj);
            objectOut.close();
            fos.close();
            System.out.println("The Object was succesfully written to a file");

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

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
//        builder.setTitle("Add a new task");
//        builder.setIcon(R.drawable.ic_book_black_24dp);
//        task=new EditText(this);
//        taskdetail=new EditText(this);
//        builder.setView(task);
//        builder.setView(taskdetail);
//
//
//        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//
//
//            }
//        });
//
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.dismiss();
//            }
//        });






        //list=(ArrayList)dataset.getListData();

        recView = (RecyclerView)findViewById(R.id.recycler);
        //Check out GridLayoutManager and StaggeredGridLayoutManager for more options
        //layoutManager =new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recView.setLayoutManager(new LinearLayoutManager(this));

        //adapter = new ClassAdapter(list, this);
        //recView.setAdapter(adapter);
        //adapter.setItemClickCallback(this);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
        itemTouchHelper.attachToRecyclerView(recView);
        read();

//        addItem = (Button) findViewById(R.id.btn_add_item);
//        addItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            showInputDialog();
//            }
//        });
    }


    @Override
    protected void onResume()
    {
        super.onResume();
        list=popup.newlist;
        adapter=new ClassAdapter(popup.newlist,this);
        recView.setAdapter(MainActivity.adapter);
        adapter.setItemClickCallback(this);

    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //handle presses on the action bar items
        switch (item.getItemId()) {

            case R.id.toolbarbutton:
              startActivity(new Intent(this, popup.class));


                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private ItemTouchHelper.Callback createHelperCallback() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        moveItem(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                        return true;
                    }

                    @Override
                    public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        deleteItem(viewHolder.getAdapterPosition());
                        WriteObjectToFile(popup.newlist);
                    }
                };
        return simpleItemTouchCallback;
    }
    private void addItemToList() {
        Listitem item = dataset.getRandomListItem();
        list.add(item);
        adapter.notifyItemInserted(list.indexOf(item));
    }

    private void moveItem(int oldPos, int newPos) {

        Listitem item = (Listitem) list.get(oldPos);
        list.remove(oldPos);
        list.add(newPos,item);
        adapter.notifyItemMoved(oldPos, newPos);
    }

    private void deleteItem(final int position) {
        list.remove(position);
        adapter.notifyItemRemoved(position);
    }

    @Override
    public void onItemClick(int p) {

//        startActivity(intent);
        Listitem l=(Listitem)list.get(p);
        Intent intent = DetailActivity.newIntent(this, l.getTaskid());

        Intent i=new Intent(this,DetailActivity.class);
        Bundle extras=new Bundle();
        extras.putString(EXTRA_QUOTE,l.getTitle());
        extras.putString(EXTRA_ATTR,l.getTitle());
        i.putExtra(BUNDLE_EXTRAS,extras);
        startActivity(intent);
    }

    @Override
    public void onSecondaryIconClick(int p) {
        Listitem item=(Listitem)list.get(p);
        if(item.isTemp())
        {
            item.setTemp(false);
        }
        else
        {
            item.setTemp(true);
        }

        adapter.notifyDataSetChanged();
    }


}

