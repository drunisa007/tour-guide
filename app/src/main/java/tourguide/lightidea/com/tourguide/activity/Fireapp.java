package tourguide.lightidea.com.tourguide.activity;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;



public class Fireapp extends Application {
//fkdfkdfkjdkjf
    @Override
    public void onCreate() {
        super.onCreate();
        if (!FirebaseApp.getApps(this).isEmpty()) {
            FirebaseFirestoreSettings settings=new FirebaseFirestoreSettings.Builder()
                                                   .setPersistenceEnabled(true)
                                                    .build();
            FirebaseFirestore db = FirebaseFirestore.getInstance();
                   db.setFirestoreSettings(settings);
        }

    }

}
