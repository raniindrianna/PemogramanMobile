package com.example.background;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class SelectImageActivity {

    private void handleImageRequestResult(Intent data) {
        Uri imageUri = null;
        if(data.getClipData() != null) {
            imageUri = data.getClipData().getItemAt(0).getUri();
        }else if(data.getData() != null) {
            imageUri = data.getData();
        }

        if (imageUri == null) {
            Log.e(TAG, "Invalid input image uri." );
            return;
        }

        Intent filterIntent = new Intent(this,BlurActivity.class);
        filterIntent.putExtra(Constants.KEY_IMAGE_URI, imageUri.toString());
        startActivity(filterIntent);
    }




}
