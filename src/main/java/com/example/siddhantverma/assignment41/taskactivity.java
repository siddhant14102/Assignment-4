package com.example.siddhantverma.assignment41;

import android.content.Context;

import com.example.siddhantverma.assignment41.model.Listitem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Siddhant Verma on 09-Nov-16.
 */
public class taskactivity {
    private static taskactivity task;
    private ArrayList<Listitem> tasklist;
    public static taskactivity get(Context context) {
        if (task == null) {
            task= new taskactivity(context);
        }
        return task;
    }

    private taskactivity(Context context) {
        tasklist = new ArrayList<Listitem>();
// for (int i = 0; i < 100; i++) {
//            Listitem l= new Listitem();
//            l.setTitle("Task #" + i);
//           // crime.setSolved(i % 2 == 0);
//           tasklist.add(l);
//}
    }

    public ArrayList<Listitem> getAllTasks() {
        return popup.newlist;
    }

    public Listitem getTask(UUID id) {
        for (Listitem list : tasklist) {
            if (list.getTaskid().equals(id)) {
                return list;
            }
        }
        return null;
    }
}
