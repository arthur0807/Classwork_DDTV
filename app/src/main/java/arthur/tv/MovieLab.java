package arthur.tv;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MovieLab {
    private Context context;

    public MovieLab(Context ctx){
        context = ctx;
    }

    public String loadJSONFromAsset(String filename){
        String json = null;
        try{
            InputStream inputStream = context.getAssets().open(filename);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer,"UTF-8");
        } catch (IOException exception){
            Log.e("DDTV",exception.getMessage());
            exception.getMessage();
        }
        return json;
    }

    public List<movieDataList> getChannels(String filename) {
        List<movieDataList> result = new ArrayList<>();
        String json = loadJSONFromAsset(filename);
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray data = obj.getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                movieDataList dataList = new movieDataList();
                JSONObject item = data.getJSONObject(i);
                dataList.setTitle(item.getString("title"));
                dataList.setQuality(item.getString("quality"));
                dataList.setUrl(item.getString("url"));
                result.add(dataList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
