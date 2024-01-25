package com.example.userinteractions

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var showToast : Button

    lateinit var showSnackbar : Button
    lateinit var mylayout: ConstraintLayout

    lateinit var showDialogMessage : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //the toast interaction displays a message after an action  by the user for a limited time
        showToast = findViewById(R.id.buttonShowToast)

        showSnackbar = findViewById(R.id.buttonSnackbar)
        mylayout = findViewById(R.id.constraintlayout)

        showDialogMessage = findViewById(R.id.buttonDialogMessage)


        //three ways to declare the context: applicationContext, this, this@Name_activity
        //applicationContext considers the context of the application which takes the toast
        //the 'this' context takes the activity of the method which executes this toast...cab soetimes give an error if not well used
        //the 'this@Name_activity' defines the activity specifically... will recommend when using the this keyword
        showToast.setOnClickListener {
            Toast.makeText(applicationContext,
                "The toast Button has been clicked",
                Toast.LENGTH_LONG).show()
        }

        //the snackbar is a light-weight widget used to show messages in the bottom of the application with swipping enabled(an action)
        //takes 4 parameters instead of 3 as the toast widget
        //the layout parameter defines the layout in which the snackbar will be displayed  , thats why we defined an id to the constraint layout of our app
        //the message defines the message shown when the snackbar is triggered
        //the length is the amnt of time to show the snackbar... can be indefinite until the user closes it
        //the makeAction defines the action attached to the snackbar message.. in this case we choose a 'close' option
        //the makeAction(has two parameters) takes the message of the action and the execution of the action
        showSnackbar.setOnClickListener {
            Snackbar.make(mylayout,
                "This is a Snackbar message",
                Snackbar.LENGTH_INDEFINITE)
                .setAction("Close",
                    View.OnClickListener {

            }).show()
        }

        showDialogMessage.setOnClickListener {
                showAlertDialog()
        }
    }

    fun showAlertDialog(){
        var alertDialog = AlertDialog.Builder(this@MainActivity) //since its an innerclass, we create an alertDialog object to reference this innerclass

        alertDialog.setTitle("Change") //the title of the dialog message
            .setMessage("Do you want to change the text of the button?") //the message to display in the body of the dialog message
            .setIcon(R.drawable.warning)    //the icon to show in the dialog message
            .setCancelable(false)           //to define if the dialog box must be cancelled from the box or out of the box
            .setNegativeButton("NO", DialogInterface.OnClickListener { dialogInterface, which ->
                dialogInterface.cancel()
            })   //defines the No option of the response and the returned interger action which is to close the alert dialog box
            .setPositiveButton("YES", DialogInterface.OnClickListener { dialogInterface, which ->
                showDialogMessage.text = "Alert Dialog"
            })  //defines the YES option of the response and returned interger action which is to change the dialogmessage button
        alertDialog.create().show()  //this method makes the dialog message to be shown when clicked
    }
}