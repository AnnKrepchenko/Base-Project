package com.krepchenko.base_proj.utils;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;

import com.android.annotations.Nullable;
import com.krepchenko.base_proj.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImagePicker {

    private static final int MAX_SIZE_BYTES = 1024 * 1024; // 1Mb

    private static final String TAG = "picker";

    public static Intent getPickImageChooserIntent(Context context) {

        // Determine Uri of camera image to save.
        Uri outputFileUri = getCaptureImageOutputUri(context);

        List<Intent> allIntents = new ArrayList<>();
        PackageManager packageManager = context.getPackageManager();

        // collect all camera intents
        Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            if (outputFileUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            }
            allIntents.add(intent);
        }

        // collect all gallery intents
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        List<ResolveInfo> listGallery = packageManager.queryIntentActivities(galleryIntent, 0);
        for (ResolveInfo res : listGallery) {
            Intent intent = new Intent(galleryIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            allIntents.add(intent);
        }

        // the main intent is the last in the list (fucking android) so pickup the useless one
        Intent mainIntent = allIntents.remove(allIntents.size() - 1);

        // Create a chooser from the main intent
        Intent chooserIntent = Intent.createChooser(mainIntent, context.getString(R.string.pick_image_intent_text));

        // Add all other intents
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toArray(new Parcelable[allIntents.size()]));

        return chooserIntent;
    }

    private static Uri getCaptureImageOutputUri(Context context) {
        Uri outputFileUri = null;
        File getImage = context.getExternalCacheDir();
        if (getImage != null) {
            outputFileUri = Uri.fromFile(new File(getImage.getPath(), "temp.jpg"));
        }
        return outputFileUri;
    }


    @Nullable
    public static File getResizedFileFromResult(Context context, Intent intent) {
        File imageFile;
        try {
            imageFile = getTempFile(context);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Bitmap bitmap = null;
        if (ImagePicker.getPickImageResultUri(context, intent) != null) {
            Uri picUri = ImagePicker.getPickImageResultUri(context, intent);

            try {
                bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), picUri);
                bitmap = rotateImageIfRequired(bitmap, picUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            bitmap = (Bitmap) intent.getExtras().get("data");
        }

        if (bitmap == null)
            return null;

//        bitmap = getSquaredScaledBitmap(bitmap, 500);

        try (FileOutputStream stream = new FileOutputStream(imageFile)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            stream.flush();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return imageFile;
    }

    private static File getTempFile(Context context) throws IOException {
        File outputDir = context.getCacheDir(); // context being the Activity pointer
        return File.createTempFile("temp" + System.currentTimeMillis(), ".jpg", outputDir);
    }

    public static Uri getPickImageResultUri(Context context, Intent data) {
        boolean isCamera = true;
        if (data != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }

        return isCamera ? getCaptureImageOutputUri(context) : data.getData();
    }

    private static Bitmap rotateImageIfRequired(Bitmap img, Uri selectedImage) throws IOException {

        ExifInterface ei = new ExifInterface(selectedImage.getPath());
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotateImage(img, 90);
            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotateImage(img, 180);
            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotateImage(img, 270);
            default:
                return img;
        }
    }

    private static Bitmap rotateImage(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        img.recycle();
        return rotatedImg;
    }

    public static Bitmap getSquaredScaledBitmap(Bitmap image, int maxSize) {
        if (image.getWidth() >= image.getHeight()) {
            image = Bitmap.createBitmap(image,
                    image.getWidth() / 2 - image.getHeight() / 2, 0,
                    image.getHeight(), image.getHeight()
            );
        } else {
            image = Bitmap.createBitmap(
                    image,
                    0, image.getHeight() / 2 - image.getWidth() / 2,
                    image.getWidth(), image.getWidth()
            );
        }
        int min = Math.min(image.getWidth(), image.getHeight());
        min = Math.min(min, maxSize);
        return Bitmap.createScaledBitmap(image, min, min, true);
    }

}