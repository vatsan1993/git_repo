package vatsan.melon.com.photoeffects;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    Drawable mDrawable;
    Bitmap mBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img= (ImageView) findViewById(R.id.img);
        mDrawable= ResourcesCompat.getDrawable(getResources(), R.drawable.img,null);
        mBitmap=((BitmapDrawable)mDrawable).getBitmap();

        Bitmap bitmap= inverImage(mBitmap);

        img.setImageBitmap(bitmap);

    }
    public static Bitmap inverImage(Bitmap bitmap){
        Bitmap EndImg=Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),bitmap.getConfig());
        int A,R,G,B;
        int pixelColor;
        int height= bitmap.getHeight();
        int width=bitmap.getWidth();

        for(int y=0;y<height;y++){
            for(int x=0;x<width;x++){
                pixelColor=bitmap.getPixel(x,y);
                A=Color.alpha(pixelColor);
                R=255-Color.red(pixelColor);
                G=255-Color.green(pixelColor);
                B=255-Color.blue(pixelColor);

                EndImg.setPixel(x,y,Color.argb(A,R,G,B));
            }
        }
        return EndImg;
    }
}
