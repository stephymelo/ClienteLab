package com.example.clientelab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private BufferedWriter writer;
    private EditText usernameEdit;
    private EditText passwordEdit;
    private Button ingresoButton;
    private String username,password,id;
    private String json;
    private Usuarios user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameEdit=findViewById(R.id.usernameEdit);
        passwordEdit=findViewById(R.id.passwordEdit);
        ingresoButton=findViewById(R.id.ingresoButton);
        serverInit();




        ingresoButton.setOnClickListener(
                (view)-> {

                    Gson gson = new Gson();
                    username = usernameEdit.getText().toString();
                    password = passwordEdit.getText().toString();
                    id = UUID.randomUUID().toString();

                    ArrayList<Usuarios> users = new ArrayList<>();
                    users.add(new Usuarios(username, password, id));
                    json = gson.toJson(users);
                    enviarMensaje(json);


                }
        );
    }

public void serverInit(){

    new Thread(

            ()-> {
                try {

                    System.out.println("Enviando solicitud de conexion...");
                    Socket socket = new Socket("192.168.0.200", 5000);
                    System.out.println("Conectamos");
                    InputStream is = socket.getInputStream();
                    OutputStream out = socket.getOutputStream();
                    writer = new BufferedWriter(new OutputStreamWriter(out));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    while (true) {
                        String line = reader.readLine();

                        if (line.equals("bien")) {
                            Intent i = new Intent(this, Page2.class);
                            startActivity(i);
                        }
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();

                }
            }

    ).start();
}

public void enviarMensaje(String user){
        new Thread(
                ()->{
                    try{
                        writer.write( user+ "\n");
                        writer.flush();

                    		} catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
        )  .start();

                }

}


