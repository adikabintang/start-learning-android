# QR Code Reader Simple Version 0
Taken from here: https://github.com/tutsplus/Android-MobileVisionAPI

## Notes:
- Check for camera permission is important:
```// it is important to run this first to check permission
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, 50);
        }```


