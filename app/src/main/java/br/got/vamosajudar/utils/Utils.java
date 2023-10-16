package br.got.vamosajudar.utils;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Utils {

    private static final String TAG = "UTILS";

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {

            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities( connectivityManager.getActiveNetwork());
            return networkCapabilities != null  &&
                    (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR));
        }
        return false;
    }


    public static String uriToBase64(Context context, Uri uri) {
        try {
            ContentResolver contentResolver = context.getContentResolver();
            InputStream inputStream = contentResolver.openInputStream(uri);

            if (inputStream != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                }

                byte[] imageBytes = byteArrayOutputStream.toByteArray();
                inputStream.close();

                String base64String = Base64.encodeToString(imageBytes, Base64.NO_PADDING);

                base64String = base64String.replaceAll("\\s", "");

                return base64String;
            }
        } catch (IOException e) {
            Log.e(TAG, "uriToBase64: ", e);
            e.printStackTrace();
        }

        return null;
    }

    public static String compressImageToBase64(Context context, Uri imageUri) {
        try {
            ContentResolver contentResolver =  context.getContentResolver();
            InputStream inputStream = contentResolver.openInputStream(imageUri);

            if (inputStream != null) {
                Bitmap bitmap = resize( BitmapFactory.decodeStream(inputStream),720,480);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                bitmap.compress(Bitmap.CompressFormat.WEBP, 80, byteArrayOutputStream);

                byte[] imageBytes = byteArrayOutputStream.toByteArray();
                String base64String = Base64.encodeToString(imageBytes, Base64.NO_PADDING);

                // Remover quebras de linha e espaços em branco
                base64String = base64String.replaceAll("\\s", "");

                return base64String;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Bitmap resize(Bitmap bitmap, int newWidth, int newHeight) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        android.graphics.Matrix matrix = new android.graphics.Matrix();

        matrix.postScale(scaleWidth, scaleHeight);

        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
    }

    public static void alertDialog(Context context, DialogInterface.OnClickListener onClickListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("DELETAR ONG");
        builder.setMessage("Tem certeza que deseja deletar essa ONG?");
        builder.setPositiveButton("SIM",onClickListener);
        builder.setNegativeButton("NÃO", (dialogInterface, i) -> dialogInterface.cancel());
        builder.show();
    }


}
