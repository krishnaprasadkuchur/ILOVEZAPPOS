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

import android.databinding.BindingAdapter;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;



public class ProfileViewModel {

    public String getImageUrl() {

        return "true";
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.bb_bottom_bar_top_shadow)
                .into(view);
    }
}
