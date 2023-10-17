package com.iesam.fomapp.app.extensions

import android.widget.ImageView


fun ImageView.setUrl(urlImage : String){
    Glide
        .with(this.context)
        .load(urlImage)
        .into(this);
}

