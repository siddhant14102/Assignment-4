package com.example.siddhantverma.assignment41.model;

/**
 * Created by Siddhant Verma on 06-Nov-16.
 */

import android.app.LauncherActivity;

import com.example.siddhantverma.assignment41.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class dataset {
    private static final String[] titles = {"Nothingness cannot be defined",
            "Time is like a river made up of the events which happen, and a violent stream; " +
                    "for as soon as a thing has been seen, it is carried away, and another comes" +
                    " in its place, and this will be carried away too,",
            "But when I know that the glass is already broken, every minute with it is precious.",
            "For me, it is far better to grasp the Universe as it really is than to persist in" +
                    " delusion, however satisfying and reassuring.",
            "The seeker after the truth is not one who studies the writings of the ancients and," +
                    " following his natural disposition, puts his trust in them, but rather the" +
                    " one who suspects his faith in them and questions what he gathers from them," +
                    " the one who submits to argument and demonstration, and not to the " +
                    "sayings of a human being whose nature is fraught with all kinds " +
                    "of imperfection and deficiency.",
            "You must take personal responsibility. You cannot change the circumstances, the" +
                    " seasons, or the wind, but you can change yourself. That is something you" +
                    " have charge of."
    };
   // private static final int icon =R.drawable

    private static final String[] secondary= {"Bruce Lee",
            "Marcus Aurelius",
            "Meng Tzu",
            "Ajahn Chah",
            "Carl Sagan",
            "Alhazen",
            "Jim Rohn"

    };

        private static final int icon=R.drawable.ic_style_black_36dp;



    public static Listitem getRandomListItem(){
        int rand = new Random().nextInt(6);

        Listitem item = new Listitem();

        item.setTitle(titles[rand]);
        item.setSecondary_text(secondary[rand]);

        return item;
    }


}