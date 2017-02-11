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
import android.os.CountDownTimer;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import android.widget.Toast;
import ilovezappos.zappos.kpk.ilovezappos.databinding.ActivityMainBinding;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    private static final int ITEM_IN_CART = 0;
    private static final int ViEW_PRODUCT = 1;
    private static List<Product> product ;
    private MenuItem menucartItem;
    private ActivityMainBinding binding;
    private final static String API_KEY ="b743e26728e16b81da139182bb2094357c31d331";
    RestAdapter restAdapter =
            new RestAdapter.Builder()
                    .setEndpoint("http://api.zappos.com").build();

    ApiInterface productservice = restAdapter.create(ApiInterface.class);


    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.mainbottombar);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.e("item id", Integer.toString(item.getItemId()));
                switch (item.getItemId()) {
                    case R.id.customer_care:
                        Intent i = new Intent(getApplicationContext(), CustomerCare.class);
                        startActivity(i);
                        break;
                    case R.id.about:
                        Intent j = new Intent(getApplicationContext(), AboutApp.class);
                        startActivity(j);
                        break;
                    case R.id.return_details:
                        Intent k = new Intent(getApplicationContext(), ReturnPolicy.class);
                        startActivity(k);


                }
                return true;
            }
        });

        this.fetchProducts("");
        setTitle("Zappos Mobile");

        if(API_KEY==null){
            Toast.makeText(getApplicationContext(),"Null Key", Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final BottomNavigationView bottom = (BottomNavigationView) findViewById(R.id.mainbottombar);

        bottom.removeAllViews();
        new CountDownTimer(5000,1000){
            @Override
            public void onTick(long millisUntilFinished){}

            @Override
            public void onFinish(){

            }
        }.start();
        return super.onTouchEvent(event);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        menucartItem = menu.findItem(R.id.shopping_cart);
        MenuItem item = menu.findItem(R.id.search_bar);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("Search Product");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchProducts(query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }



    public void init(){


        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setProduct(product.get(0));
        return;
    }
    private void fetchProducts(String term) {



        productservice.getProductQuery(term,API_KEY,new Callback<ProductResult>() {
            @Override
            public void success(ProductResult productResult, Response response) {


                product = productResult.getProducts();
                init();

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(),
                        "Unexpected error occured",
                        Toast.LENGTH_LONG).show();

            }
        });

    }
    public void itemClicked(View view){

        Intent i = new Intent(this, ProductDisplay.class);
        Product por = product.get(0);
        i.putExtra("prod", por);
        startActivity(i);
    }




}
