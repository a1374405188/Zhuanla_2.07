package com.beikbank.android.media;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-3-1
 * 异步加载图片
 */
public class ImageDownloader {

    // 为了加快速度，在内存中开启缓存（主要应用于重复图片较多时，或者同一个图片要多次被访问，比如在ListView时来回滚动）

    private Map<String, SoftReference<Drawable>> imageCache = new HashMap<String, SoftReference<Drawable>>();
    
    /**

     * 显示图片的控件

     */

    private ImageView mImageView;

    public ImageDownloader(ImageView image) {

       mImageView = image;

    }
    // 固定五个线程来执行任务

    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    private final Handler handler = new Handler();

    public Drawable loadDrawable(final String imageUrl, final ImageCallback callback) {

       Drawable drawable = null;

       final String fileName = imageUrl.hashCode() + ".jpg";

       // 如果缓存过就从缓存中取出数据

       if (imageCache.containsKey(fileName)) {

           SoftReference<Drawable> softReference = imageCache.get(fileName);

           drawable = softReference.get();

           if (drawable != null) {

              callback.imageLoaded(drawable);

              return drawable;

           }

       }

       // 缓存中没有图像，则从网络或者文件中上取出数据，并将取出的数据缓存到内存中

       executorService.submit(new LoadTask(fileName,imageUrl,callback));

       return null;

    }

    /**

     * 从网络或者sdcard加载图片

    */

    class LoadTask implements Runnable {

        String fileName;

       String imageUrl;

       Drawable drawable = null;

       ImageCallback callback;

       public LoadTask(String fileName,String imageUrl,ImageCallback callback){

           this.fileName = fileName;

           this.imageUrl = imageUrl;

           this.callback = callback;

       }

       public void run() {

           try {

              // 从文件中读取

              File dir = new File("");

              if (!dir.exists()) {

                  dir.mkdirs();

              }

              File file = new File(dir, fileName);

              if (file.exists() && file.length() > 0) {

                  // 如果文件存在则直接读取sdcard

                  drawable = readFromSdcard(file);

              } else {

                  drawable = loadImageFromUrl(imageUrl);

              }

               imageCache.put(fileName, new SoftReference<Drawable>(drawable));

               handler.post(new Runnable() {

                  public void run() {

                     callback.imageLoaded(drawable);

                  }

              });

           } catch (Exception e) {

              throw new RuntimeException(e);

           }

       }

    }

   private Drawable readFromSdcard(File file) {

       try {

           FileInputStream in = new FileInputStream(file);

           return Drawable.createFromStream(in, file.getName());

       } catch (FileNotFoundException e) {

           e.printStackTrace();

       }

       return null;

    }

    // 从网络上取数据方法

    protected Drawable loadImageFromUrl(String imageUrl) {

       try {

           Drawable drawable = null;

           URL url = new URL(imageUrl);

           drawable = Drawable.createFromStream(url.openStream(), "image.jpg");

           return drawable;

       } catch (Exception e) {

           throw new RuntimeException(e);

       }

    }

     /**

     * 对外界开放的回调接口

    */

    public interface ImageCallback {

       // 注意 此方法是用来设置目标对象的图像资源

       public void imageLoaded(Drawable imageDrawable);

    }

}

//这个ImageDownloader2的使用也很简单









