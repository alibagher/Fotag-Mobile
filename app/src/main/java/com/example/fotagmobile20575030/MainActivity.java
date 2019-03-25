package com.example.fotagmobile20575030;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import java.io.InputStream;
import android.widget.ImageView;
import android.content.Context;

import android.widget.AdapterView.*;
import java.util.*;
import android.widget.RatingBar.OnRatingBarChangeListener;


public class MainActivity extends AppCompatActivity {

    //hold the information of every picture
    class info {
        private Bitmap img;
        private int rate;
        info(Bitmap img, int rate){
            this.img =img;
            this.rate =rate;
        }
        Bitmap getImg(){
            return img;
        }
        int getRate(){
            return rate;
        }
    }

    boolean c = false;

    ArrayList<Bitmap> iArr = new ArrayList<Bitmap>(10);
    ArrayList<ImageView> imgVArr = new ArrayList<ImageView>(10);
    ArrayList<RatingBar> rArr = new ArrayList<RatingBar>(10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide the original bar
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
    }


    public void loadImg(View v) {
        iArr.clear();
        rArr.clear();
        c = false;
        // Image link from internet
        //rArr.add();

        new DownImg((ImageView) findViewById(R.id.animal)).execute("https://www.student.cs.uwaterloo.ca/~cs349/w19/assignments/images/bunny.jpg");
        //rArr.add((RatingBar) findViewById(R.id.imgRating));
        new DownImg((ImageView) findViewById(R.id.animal)).execute("https://www.student.cs.uwaterloo.ca/~cs349/w19/assignments/images/chinchilla.jpg");
        //rArr.add((RatingBar) findViewById(R.id.imgRating));
        new DownImg((ImageView) findViewById(R.id.animal)).execute("https://www.student.cs.uwaterloo.ca/~cs349/w19/assignments/images/doggo.jpg");
        //rArr.add((RatingBar) findViewById(R.id.imgRating));
        new DownImg((ImageView) findViewById(R.id.animal)).execute("https://www.student.cs.uwaterloo.ca/~cs349/w19/assignments/images/fox.jpg");
        //rArr.add((RatingBar) findViewById(R.id.imgRating));
        new DownImg((ImageView) findViewById(R.id.animal)).execute("https://www.student.cs.uwaterloo.ca/~cs349/w19/assignments/images/hamster.jpg");
        //rArr.add((RatingBar) findViewById(R.id.imgRating));
        new DownImg((ImageView) findViewById(R.id.animal)).execute("https://www.student.cs.uwaterloo.ca/~cs349/w19/assignments/images/husky.jpg");
        //rArr.add((RatingBar) findViewById(R.id.imgRating));
        new DownImg((ImageView) findViewById(R.id.animal)).execute("https://www.student.cs.uwaterloo.ca/~cs349/w19/assignments/images/kitten.png");
        //rArr.add((RatingBar) findViewById(R.id.imgRating));
        new DownImg((ImageView) findViewById(R.id.animal)).execute("https://www.student.cs.uwaterloo.ca/~cs349/w19/assignments/images/loris.jpg");
        //rArr.add((RatingBar) findViewById(R.id.imgRating));
        new DownImg((ImageView) findViewById(R.id.animal)).execute("https://www.student.cs.uwaterloo.ca/~cs349/w19/assignments/images/puppy.jpg");
        //rArr.add((RatingBar) findViewById(R.id.imgRating));
        new DownImg((ImageView) findViewById(R.id.animal)).execute("https://www.student.cs.uwaterloo.ca/~cs349/w19/assignments/images/sleepy.png");


        rArr.add(new RatingBar(getApplicationContext()));
        rArr.add(new RatingBar(getApplicationContext()));
        rArr.add(new RatingBar(getApplicationContext()));
        rArr.add(new RatingBar(getApplicationContext()));
        rArr.add(new RatingBar(getApplicationContext()));
        rArr.add(new RatingBar(getApplicationContext()));
        rArr.add(new RatingBar(getApplicationContext()));
        rArr.add(new RatingBar(getApplicationContext()));
        rArr.add(new RatingBar(getApplicationContext()));
        rArr.add(new RatingBar(getApplicationContext()));

        System.out.println(rArr.size());
    }

    public void clearImg(View v){
        //infoArr.clear();
        for (int i =0; i < 10; i++){
            //iArr.get(i).rate = 0;
        }

        c = true;
    }

    public void rating(View v){


        RatingBar r = (RatingBar) v.findViewById(R.id.imgRating);
        ImageView i = (ImageView) v.findViewById(R.id.animal);

        //v.findViewById(R.id.AnimalG);

        //set the rating of the ratinBar in the array to the rating of the picture
        rArr.get(imgVArr.indexOf(i)).setRating(r.getRating());
    }

    private class DownImg extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownImg(ImageView imageView) {
            this.imageView = imageView;
            Toast.makeText(getApplicationContext(), "Loading ... ", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "Loading ... ", Toast.LENGTH_SHORT).cancel();
            imgVArr.add(imageView);
        }

        protected Bitmap doInBackground(String... urls) {
            String iURL = urls[0];
            Bitmap bitimage = null;
            try {
//                System.out.println("before input");
                InputStream in = new java.net.URL(iURL).openStream();
                bitimage = BitmapFactory.decodeStream(in);

            } catch (Exception e) {

            }
            // add this image to the global array
            iArr.add(bitimage);
            return bitimage;
        }

        protected void onPostExecute(Bitmap result) {
            //imageView.setImageBitmap(result);

            GridView g = findViewById(R.id.gridAnimal);
            GridAdapter myAdapter = new GridAdapter(getApplicationContext());
            g.setAdapter(myAdapter);

            //g.removeItem(TheItemPosition);

            /*g.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
                    switch(v.getId())
                    {
                        case R.id.imgRating:
                            RatingBar r = (RatingBar) v.findViewById(R.id.imgRating);
                            rArr.get(pos).setRating(r.getRating());
                            break;
                    }
                }
            });*/

        }
    }



    public class GridAdapter extends BaseAdapter {

        private Context con;
        private LayoutInflater inflat;

        public GridAdapter(Context con){
            this.con = con;
            //get the inflater
            this.inflat = LayoutInflater.from(con);
        }

        @Override
        public int getCount() {
            return iArr.size();
        }

        @Override
        public Object getItem(int position) {
            return rArr.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int pos, View view, ViewGroup parent) {


            // get the view.
            view = this.inflat.inflate(R.layout.grid_item, parent, false);

            final int posi = pos;
            //get the image
            ImageView animalImg = view.findViewById(R.id.animal);
            //get the main image rating
            RatingBar mainRatingB = (RatingBar) findViewById(R.id.mainRating);
            //get the rating of the image
            RatingBar imgRatingB = (RatingBar) view.findViewById(R.id.imgRating);


            //set a listener for the rating bar
            imgRatingB.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar r, float rating,
                                            boolean fromUser) {
                    rArr.get(posi).setRating(rating);
                }
            });

            imgRatingB.setRating(rArr.get(pos).getRating());

            // if the rating is high enough, display.
            if(rArr.get(pos).getRating() >= mainRatingB.getRating()) {
                // get the image from array and display
                if (!c) {
                    animalImg.setVisibility(View.VISIBLE);
                    animalImg.setImageBitmap(iArr.get(pos));
                    imgRatingB.setVisibility(View.VISIBLE);
                    imgRatingB.setRating(rArr.get(pos).getRating());
                } else {
                    animalImg.setVisibility(View.INVISIBLE);
                    imgRatingB.setVisibility(View.INVISIBLE);
                }
            }


            return view;
        }
    }

}