package com.killinsun.app.tukuttade

import android.util.Log
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class FbHelper() {

    val db:FirebaseFirestore = FirebaseFirestore.getInstance()
    //val storage:FirebaseStorage = FirebaseStorage.getInstance()

    fun create(okazu:Okazu){

        val okazuMap:HashMap<String,Any> = hashMapOf()
        okazuMap.put("name", okazu.name)
        okazuMap.put("date", okazu.expireDate)
        okazuMap.put("image", okazu.imgByte.toString())
        Log.v("create", "name: ${okazuMap["name"]}, date: ${okazuMap["date"]}")

        db.collection("okazu")
            .add(okazuMap)
            .addOnSuccessListener(OnSuccessListener<DocumentReference>(){
                fun onSuccess(documentReference: DocumentReference){
                    Log.v("test", "DocumentSnapshot Added with ID: " + documentReference.id)
                }
            })
            .addOnFailureListener(OnFailureListener {
                fun onFailure(e:Exception){
                    Log.w("test", "Error adding document", e)
                }
            })
    }
}