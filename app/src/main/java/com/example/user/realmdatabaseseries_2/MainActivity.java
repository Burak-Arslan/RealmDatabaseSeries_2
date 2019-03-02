package com.example.user.realmdatabaseseries_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    Realm realm;
    EditText edtKullaniciAdi;
    EditText edtPassword;
    EditText edtEmail;
    Button btnSave;
    ListView userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init();
        RealmInit();
        Events();

    }


    public void RealmInit() {
        try {
            realm = Realm.getDefaultInstance();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void Init() {
        try {
            edtKullaniciAdi = findViewById(R.id.edtName);
            edtPassword = findViewById(R.id.edtPassword);
            edtEmail = findViewById(R.id.edtEmail);
            btnSave = findViewById(R.id.btnSave);
            userList = findViewById(R.id.userList);
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void Events() {
        try {
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String kullaniciAdi = edtKullaniciAdi.getText().toString();
                    String eMail = edtEmail.getText().toString();
                    String password = edtPassword.getText().toString();
                    DatabaseInsert(kullaniciAdi, eMail, password);
                }
            });

            GetPosition();

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void ListeyiGoster() {
        try {

            RealmResults<UserEntity> userEntities = realm.where(UserEntity.class).findAll();
            if (userEntities.size() > 0) {
                UserAdapter userAdapter = new UserAdapter(userEntities, getApplicationContext());
                userList.setAdapter(userAdapter);
            }

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void DatabaseInsert(final String kullaniciAdi, final String eMail, final String password) {
        try {
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    try {
                        UserEntity userEntity = realm.createObject(UserEntity.class);
                        userEntity.setKullaniciAdi(kullaniciAdi);
                        userEntity.seteMail(eMail);
                        userEntity.setPassword(password);
                    } catch (Exception ex) {
                        Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    Toast.makeText(getApplicationContext(), "Kayıt Başarılı", Toast.LENGTH_SHORT).show();
                    ListeyiGoster();

                }
            }, new Realm.Transaction.OnError() {
                @Override
                public void onError(Throwable error) {
                    Toast.makeText(getApplicationContext(), "Kayıt Başarısız", Toast.LENGTH_SHORT).show();

                }
            });

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void GetPosition() {
        try {

            userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    KayitSil(position);
                }
            });
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void KayitSil(final int position) {
        try {

            final RealmResults<UserEntity> userEntities = realm.where(UserEntity.class).findAll();

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    UserEntity userEntity = userEntities.get(position);
                    userEntity.deleteFromRealm();
                    ListeyiGoster();
                }
            });

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
