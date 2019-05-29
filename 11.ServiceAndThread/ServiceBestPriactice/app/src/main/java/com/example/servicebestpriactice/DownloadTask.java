package com.example.servicebestpriactice;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadTask extends AsyncTask<String, Integer, Integer> {
    private static final String TAG = "DownloadTask";

    public static final int TYPE_SUCCESS = 0;
    public static final int TYPE_FAILED = 1;
    public static final int TYPE_PAUSED = 2;
    public static final int TYPE_CANCELED = 3;

    private DownloadListener listener;

    private boolean isCanceled = false;
    private boolean isPaused = false;
    private int lastProgress;

    public DownloadTask(DownloadListener listener) {
        this.listener = listener;
    }

    // 在后台任务(doInBackground中的任务)开始执行之前调用
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    // 所有代码将在子线程中执行,在这里执行好视操作
    @Override
    protected Integer doInBackground(String... params) {    // ...可变参数, Swift中也有类似设定
        InputStream inputStream = null; // 输入流,下载到的流
        RandomAccessFile savedFile = null;
        File file = null;   // 下载文件存储到手机的标准路径

        try {
            long downloadLength = 0;    // 记录下载的文件长度
            String downloadUrl = params[0]; // 下载地址
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));  // 截取得到文件的名字
            // 获取手机 download 目录路径
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(directory + fileName);
            // 判断下载文件是否存在
            if (file.exists()) {
                downloadLength = file.length();
            }
            // 获取需要下载文件的总大小
            long contentLength = getContentLength(downloadUrl);
            if (contentLength == 0) {
                //  下载失败
                return TYPE_FAILED;
            } else if (contentLength == downloadLength) {
                // 下载完成
                return TYPE_SUCCESS;
            }

            // 正式开始下载文件
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    // 断点下载,指定从哪个字节开始下载
                    .addHeader("RANGE", "bytes=" + downloadLength + "-")
                    .url(downloadUrl)
                    .build();
            Response response = client.newCall(request).execute();
            if (response != null) {
                inputStream = response.body().byteStream();
                savedFile = new RandomAccessFile(file, "rw");
                // 跳过已经下载的字节
                savedFile.seek(downloadLength);
                byte[] b = new byte[1024];
                int total = 0;
                int len;

                while ((len = inputStream.read(b)) != -1) { // 读取不到数据就会返回-1, != -1 说明还能下载到数据
                    if (isCanceled) {
                        return TYPE_CANCELED;
                    } else if (isPaused) {
                        return TYPE_PAUSED;
                    } else {
                        total += len;
                        savedFile.write(b, 0, len);
                        // 计算已下载的百分比
                        int progress = (int) ((total + downloadLength) * 100 / contentLength);
                        Log.d(TAG, "doInBackground: download progress is " + progress);
                        // 会调用onProgressUpdate方法
                        publishProgress(progress);
                    }
                }
                response.body().close();
                return TYPE_SUCCESS;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (savedFile != null) {
                    savedFile.close();
                }
                if (isCanceled && file != null) {
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return TYPE_FAILED;
    }

    // 当后台任务中调用 publishProgress()方法后调用此方法
    // 回到主线程更新进度 UI
    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        if (progress > lastProgress) {
            listener.onProgress(progress);
            lastProgress = progress;
        }
    }


    // 当后台任务执行完毕后,调用此方法
    // doInBackground返回的数据会当做参数传递到此方法中
    @Override
    protected void onPostExecute(Integer status) {
        switch (status) {
            case TYPE_SUCCESS:
                listener.onSuccess();
                break;
            case TYPE_FAILED:
                listener.onFailed();
                break;
            case TYPE_PAUSED:
                listener.onPaused();
                break;
            case TYPE_CANCELED:
                listener.onCanceled();
                break;
            default:
                break;

        }
    }


    /*
    * 暂停下载
    * 提供外部使用的控制功能
    * */
    public void pauseDownload() {
        isPaused = true;
    }
    /*
    * 取消下载
    * */
    public void  cancelDownload() {
        isCanceled = true;
    }

    /*
    * 获取下载内容的总大小
    * */
    private long getContentLength(String downloadUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
        Response response = client.newCall(request).execute();
        if (response != null && response.isSuccessful()) {
            long responseLength = response.body().contentLength();
            Log.d(TAG, "getContentLength: responseLength = " + responseLength);
            response.body().close();
            return responseLength;
        }
        return 0;
    }
}
