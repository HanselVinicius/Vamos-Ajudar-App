package br.got.vamosajudar.utils;

import android.content.ContentResolver;
import android.content.Context;
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

                // Encode the imageBytes to Base64
                String base64String = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                return base64String;
            }
        } catch (IOException e) {
            Log.e(TAG, "uriToBase64: ",e);
            e.printStackTrace();
        }

        return null;
    }

}
