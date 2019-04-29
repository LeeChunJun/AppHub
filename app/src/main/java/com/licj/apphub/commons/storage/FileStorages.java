package com.licj.apphub.commons.storage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;


import com.licj.apphub.commons.initial.MyApplication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileStorages {
    private static final String TAG = "FileStorages";
    private Context context;

    public FileStorages() {
        this.context = MyApplication.getContext();
    }

    /**
     * @param bmp
     * @param picName
     * @param quality
     * @param description
     */
    public void saveBmp2Gallery(Bitmap bmp, String picName, int quality, String description) {

        // 1.获取系统相册目录
        String galleryPath = Environment.getExternalStorageDirectory()
                + File.separator + Environment.DIRECTORY_DCIM
                + File.separator + "Camera" + File.separator;

        // 2.保存图片文件
        // 声明文件对象
        File file = null;
        // 声明文件完整路径
        String fileName = null;
        // 声明输出流
        FileOutputStream outStream = null;
        try {
            // 如果有目标文件，直接获得文件对象，否则创建一个以picName为名称的文件
            file = new File(galleryPath, picName + ".png");
            // 获得文件完整路径
            fileName = file.toString();
            // 获得输出流，如果文件中有内容，追加内容
            outStream = new FileOutputStream(fileName);
            if (outStream != null) {
                bmp.compress(Bitmap.CompressFormat.PNG, quality, outStream);
            }
        } catch (Exception e) {
            Log.e(TAG, "保存图片文件失败！");
        } finally {
            try {
                if (outStream != null) {
                    outStream.close();
                }
            } catch (IOException e) {
                Log.e(TAG, "图片文件关闭失败！");
            }
        }

        // 3.通知相册更新
        MediaStore.Images.Media.insertImage(context.getContentResolver(), bmp, fileName, description);
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        context.sendBroadcast(intent);
        bmp.recycle();
        Toast.makeText(context, "图片保存成功", Toast.LENGTH_SHORT).show();
    }

    public void save(String file, String data) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = context.openFileOutput(file, Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(data);
        } catch (IOException e) {
            Log.e(TAG, "保存文件失败！");
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                Log.e(TAG, "文件关闭失败！");
            }
        }
    }

    public String load(String file) {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = context.openFileInput(file);
            reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            Log.e(TAG, "加载文件失败！");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(TAG, "文件关闭失败！");
                }
            }
        }
        return content.toString();
    }
}
