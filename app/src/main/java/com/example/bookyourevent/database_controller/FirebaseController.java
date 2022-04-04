package com.example.bookyourevent.database_controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.bookyourevent.client.ClientMainActivity;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class FirebaseController {
    private static FirebaseAuth firebaseAuth;
    private static FirebaseFirestore myDatabase;
    private static FirebaseStorage storage;
    public interface ClientMainActivityInterface
    {
        public void setData(Customer customer);
    }
    public interface SetImageInterface
    {
        public void setImage(Bitmap bitmap);
    }
    public FirebaseController()
    {
        firebaseAuth = FirebaseAuth.getInstance();
    }
    public static boolean isAlreadyLogIn()
    {
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser == null)
        {
            return false;
        }
        else
        {
            Log.d("logIn","hello");
            Log.d("logIn",firebaseUser.getUid());
            return true;
        }
    }

    public static void createAccount(Customer customer,String password)
    {
        firebaseAuth.createUserWithEmailAndPassword(customer.getEmail(),password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        FirebaseUser firebaseUser = authResult.getUser();
                        // save customer
                        Log.d("logIn",firebaseUser.getUid());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("logIn",e.getMessage());
                    }
                });
    }
    public static void createAccount(String email,String password)
    {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        FirebaseUser firebaseUser = authResult.getUser();
                        sendVerificationMail(firebaseUser);// send verification email to user
                        Log.d("logIn",firebaseUser.getUid());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("logIn",e.getMessage());
                    }
                });
    }
    public static void LogInCustomer(String email,String password)// it will set A Customer
    {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        FirebaseUser firebaseUser = authResult.getUser();
                        if(firebaseUser.isEmailVerified())
                        {

                        }
                        else
                        {

                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
    private static void sendVerificationMail(FirebaseUser firebaseUser)
    {
        firebaseUser.sendEmailVerification()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }
    // for data storage and retrieve
    //add customers
    public static void addCustomer(Customer customer)
    {
        myDatabase = FirebaseFirestore.getInstance();
        myDatabase.collection("Customers")
                .document(customer.getId())
                .set(customer)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //sava image
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
    public static void getCustomer(String uID)
    {

        myDatabase = FirebaseFirestore.getInstance();
        myDatabase.collection("Customers")
                .document(uID)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        String id,name,phoneNumber,email,address,imageURI;
                        id = (String) documentSnapshot.get("id");
                        name = (String) documentSnapshot.get("name");
                        phoneNumber = (String) documentSnapshot.get("phoneNumber");
                        address = (String) documentSnapshot.get("address");
                        imageURI = (String) documentSnapshot.get("imageURI");
                        ClientMainActivityInterface clientMainActivityInterface = new ClientMainActivity();
                        Customer customer = new Customer(name,address,phoneNumber,imageURI,id);
                        clientMainActivityInterface.setData(customer);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }
    //Add a party center
    public static void addPartyCenter(PartyCenter partyCenter)
    {
        myDatabase = FirebaseFirestore.getInstance();
        myDatabase.collection("PartyCenters")
                .document(partyCenter.getOwnerId())
                .set(partyCenter)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }
    public static void getPartyCenters(String ownerId)
    {
        myDatabase = FirebaseFirestore.getInstance();
        myDatabase.collection("PartyCenters")
                .document(ownerId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
    // add Owner
    public static void addOwner(Owner owner)
    {
        myDatabase = FirebaseFirestore.getInstance();
        myDatabase.collection("Owners")
                .document(owner.getId())
                .set(owner)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        // save image
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
    public static void  saveImage(String path, Uri file)
    {
        storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference(path).child("pic.jpeg");
        storageReference.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                    }
                });
    }
    public static void setImage(String uId,SetImageInterface it)
    {
        storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference(uId).child("pic.jpeg");
        try {
            File file = File.createTempFile("pic","jpeg");
            storageReference.getFile(file)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                            SetImageInterface setImageInterface = it;
                            setImageInterface.setImage(bitmap);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                        Log.d("image","Fail");
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void getAvailablePartyCenters()
    {
        myDatabase = FirebaseFirestore.getInstance();
        myDatabase.collection("PartyCenters")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for(int i = 0 ; i < list.size(); i++)
                        {
                            DocumentSnapshot documentSnapshot = list.get(i);
                            String id,name,contactNumber,address,ownerId;
                            LatLng position;
                            int capacity;
                            ArrayList<Slot> slots;
                            Map<Date,Integer> reservationOnADate;
                            Map<String,Object> map = documentSnapshot.getData();
                            id = (String) map.get("id");
                            name = (String) map.get("name");
                            contactNumber = (String) map.get("contactNumber");
                            address = (String) map.get("address");
                            ownerId = (String) map.get("ownerId");
                            slots = (ArrayList<Slot>) map.get("slots");
                            reservationOnADate = (Map<Date, Integer>) map.get("reservationOnADate");

                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
}
