package org.siprop.android.nativeopencvsample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "NativeOpenCV";

    private Bitmap lena = null;
    private Mat targetImage = null;

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                    Log.i(TAG, "OpenCV loaded successfully");
                    // Load native library A-F-T-E-R OpenCV for Android initialization
                    System.loadLibrary("NativeOpenCV");
                    break;

                default:
                    super.onManagerConnected(status);
                    break;
            }
        }
    };
    @Override
    public void onResume() {
        super.onResume();
        // Load OpenCV for Android
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
    }

    public native void gaussianBlur(long matAddr);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lena = BitmapFactory.decodeResource(getResources(), R.drawable.lena1);
        ImageView iv = (ImageView) findViewById(R.id.imageView);
        iv.setImageBitmap(lena);

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gaussianBlur();
            }
        });
    }

    private void gaussianBlur() {
        // Convert Android's Bitmap to Mat.
        targetImage = new Mat();
        Utils.bitmapToMat(lena, targetImage);
        Imgproc.cvtColor(targetImage, targetImage, Imgproc.COLOR_BGR2RGB);

        gaussianBlur(targetImage.getNativeObjAddr());

        // create a bitMap
        Bitmap bitMap = Bitmap.createBitmap(targetImage.cols(),
                targetImage.rows(), Bitmap.Config.RGB_565);
        // convert Mat to Android's bitmap:
        Imgproc.cvtColor(targetImage, targetImage, Imgproc.COLOR_RGB2BGR);
        Utils.matToBitmap(targetImage, bitMap);


        ImageView iv = (ImageView) findViewById(R.id.imageView);
        iv.setImageBitmap(bitMap);
    }

}
