package appium_android;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;

public class UploadApp {
    public static String upload(String username, String accessKey){
        System.out.println("Uploading app...");
        // the file we want to upload via code
        File inFile = new File("/path/to/file/BStackSampleApp.ipa");
        FileInputStream fis = null;
        String hash_id = "";
        try {
            fis = new FileInputStream(inFile);
            DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());

            // server back-end URL
            HttpPost httppost = new HttpPost("https://"+username+":"+accessKey+"@api.browserstack.com/app-automate/upload");
            MultipartEntity entity = new MultipartEntity();

            // set the file input stream and file name as arguments
            entity.addPart("file", new InputStreamBody(fis, inFile.getName()));
            httppost.setEntity(entity);

            // execute the request
            HttpResponse response = httpclient.execute(httppost);

            //int statusCode = response.getStatusLine().getStatusCode();
            HttpEntity responseEntity = response.getEntity();
            String responseString = EntityUtils.toString(responseEntity, "UTF-8");

            //System.out.println(responseString);
            hash_id = responseString.substring(12, responseString.length() - 2);
            System.out.println(hash_id);

        } catch (ClientProtocolException e) {
            System.err.println("Unable to make connection");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Unable to read file");
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) fis.close();
            } catch (IOException e) {
            }
        }
        return hash_id;
    }
}
