/**
 * Copyright 2017 Krishna Prasad Kuchur,
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * Zappos Family Android Development Intern
 * @author Krishna Prasad Kuhcur mailto:kkuchur@asu.edu
 * @version February 2017
 */
package ilovezappos.zappos.kpk.ilovezappos;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.CountDownTimer;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import ilovezappos.zappos.kpk.ilovezappos.databinding.ActivityProductDisplayBinding;

public class ProductDisplay extends AppCompatActivity {
    private MenuItem cartItem;
    FloatingActionButton fbutton1,fbutton2;
    Animation fbuttonclose, fbuttonpen, fbclock, fbanticlock;
    boolean itemBought = false;
    Product productSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_display);
        productSelected = (Product) getIntent().getExtras().getSerializable("prod");
        ActivityProductDisplayBinding binding1 = DataBindingUtil.setContentView(this,R.layout.activity_product_display);
       binding1.setProduct2(productSelected);
        setTitle("");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.product_page_menu, menu);
        cartItem = menu.findItem(R.id.shopping_cart);




        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {


        if (menuItem.getItemId() == R.id.goback) {
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("Result",Boolean.toString(itemBought));
           this.finish();

        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void animateButtons(View v){
        if(itemBought){
            fbutton1 = (FloatingActionButton)findViewById(R.id.animate_buy);
            fbutton2 = (FloatingActionButton) findViewById(R.id.animate_cart);
            fbuttonpen = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
            fbuttonclose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
            fbclock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_image);
            fbanticlock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anticlock);
            fbutton2.startAnimation(fbuttonclose);
            fbutton1.setBackgroundColor(Color.RED);
            fbutton1.startAnimation(fbanticlock);
            fbutton2.setClickable(false);
            fbutton2.setImageResource(R.drawable.ic_add_shopping_cart_black_24dp);
            itemBought = false;
            cartItem.setIcon(R.drawable.cart2);




        }
        else{
            fbutton1 = (FloatingActionButton)findViewById(R.id.animate_buy);
            fbutton2 = (FloatingActionButton) findViewById(R.id.animate_cart);
            fbuttonpen = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
            fbuttonclose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
            fbclock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_image);
            fbanticlock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anticlock);
            fbutton2.startAnimation(fbuttonpen);
            fbutton1.startAnimation(fbclock);
            fbutton2.setClickable(true);
            itemBought = true;
            new CountDownTimer(1000,1000){
                @Override
                public void onTick(long millisUntilFinished){}

                @Override
                public void onFinish(){

                    fbutton2.setImageResource(R.drawable.booked);
                    new CountDownTimer(1000,1000){
                        @Override
                        public void onTick(long millisUntilFinished){}

                        @Override
                        public void onFinish(){

                            fbutton2.hide();


                            ;
                        }
                    }.start();


                    ;
                }
            }.start();
            cartItem.setIcon(R.drawable.addedtocartlatest);
        }


    }
}
