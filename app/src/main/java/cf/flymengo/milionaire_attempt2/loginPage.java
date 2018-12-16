package cf.flymengo.milionaire_attempt2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mysql.cj.xdevapi.JsonString;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import Decoder.BASE64Decoder;


// tokenize the data



public class loginPage extends Fragment implements View.OnClickListener {
    public  User currentUser = new User();
    EditText mail_txt, pass_txt;
    TextView log_in_lbl;
    Button login_btn;
    ImageView img_v;

    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view;
        view = inflater.inflate(R.layout.login, container, false);

        //Fix crash
        if (Build.VERSION.SDK_INT>9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll()
                    .build();
            StrictMode.setThreadPolicy(policy);
        }

        login_btn = view.findViewById(R.id.button);
        mail_txt = view.findViewById(R.id.email_txt);
        pass_txt = view.findViewById(R.id.password_txt);
        log_in_lbl = view.findViewById(R.id.textView);
        progressDialog = new ProgressDialog(getActivity());
        img_v = view.findViewById(R.id.imageView);
        //img_v.setImageBitmap(getImageBitmapFromUrl("http://localhost/php_scripts/operations/BlobConvert.php"));

        login_btn.setOnClickListener(this);
        return view;
    }

    private void LoginUser(){

        final String email = mail_txt.getText().toString().trim();
        final String password = pass_txt.getText().toString().trim();

        progressDialog.setMessage("Loging");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(getActivity(),"onResponse", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();

                        try {
                            Log.w("response", response);
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getActivity(),jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                            if (jsonObject.getInt("error")==0) {
                                String url = jsonObject.getJSONObject("login").getString("avatar")+"?email="+email;

                                //Bitmap user_img = getImageBitmapFromUrl(url);
                                currentUser= new User(
                                        jsonObject.getJSONObject("login").getInt("Id"),
                                        jsonObject.getJSONObject("login").getString("Name"),
                                        jsonObject.getJSONObject("login").getString("Nickname"),
                                        jsonObject.getJSONObject("login").getString("Rank"),
                                        jsonObject.getJSONObject("login").getInt("Wallet"),
                                        mail_txt.getText().toString(),
                                        getImageBitmapFromUrl(url)
                                        );

                                Intent intent = new Intent(getActivity(), main.class);
                                startActivity(intent);
                            }

                        } catch (JSONException e) {
                            Log.e("error",e.toString(), e);
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getActivity(), "Error connection!!!", Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> params = new HashMap<>();
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
    @Override
    public void onClick(View v) {
        if (v==login_btn)
            LoginUser();
        log_in_lbl.setText(currentUser.nickname);
    }
    public static Bitmap getImageBitmapFromUrl(String url_link)
    {
        try {
            URL url = new URL(url_link);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setDoInput(true);
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            Bitmap btm = BitmapFactory.decodeStream(bis);
            return btm;
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }


}
