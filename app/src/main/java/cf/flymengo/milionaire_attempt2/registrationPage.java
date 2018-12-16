package cf.flymengo.milionaire_attempt2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class registrationPage extends Fragment implements View.OnClickListener {
    private EditText name_txt, nick_txt, pass_txt, email_txt;
    private Button reg_btn;
    private ProgressDialog progressDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view;
        view = inflater.inflate(R.layout.register,container,false);

        name_txt = view.findViewById(R.id.name_txt);
        nick_txt = view.findViewById(R.id.nickname_txt);
        pass_txt = view.findViewById(R.id.password_txt);
        email_txt = view.findViewById(R.id.email_txt);
        reg_btn = view.findViewById(R.id.reg_btn);
        progressDialog = new ProgressDialog(getActivity());

        reg_btn.setOnClickListener(this);
        return view;
    }
    private void registerUser(){
        final String email = email_txt.getText().toString().trim();
        final String name = name_txt.getText().toString().trim();
        final String username = nick_txt.getText().toString().trim();
        final String password = pass_txt.getText().toString().trim();

        progressDialog.setMessage("Registering");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(getActivity(),"onResponse", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();

                        try {
                            Log.w("response", response);
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getActivity(),jsonObject.getString("message"), Toast.LENGTH_LONG).show();
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
                params.put("name",name);
                params.put("nickname",username);
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
        if(v == reg_btn){
            registerUser();
        }
    }
}
