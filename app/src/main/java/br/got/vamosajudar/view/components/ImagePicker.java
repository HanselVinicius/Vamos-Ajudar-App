package br.got.vamosajudar.view.components;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;

import br.got.vamosajudar.view.activity.OngRegisterActivity;

public class ImagePicker {

    private Activity activity;

    public ImagePicker(Activity activity) {
        this.activity = activity;
    }

    public void openGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent,OngRegisterActivity.PICK_IMAGE_REQUEST);
    }

}
