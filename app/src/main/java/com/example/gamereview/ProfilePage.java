package com.example.gamereview;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfilePage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfilePage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    EditText fname;
    EditText lname;
    EditText contact;
    EditText email;
    Button insertData;

    public String strfname;
    public String strlname;
    public String strcontact;
    public String stremail;

    public String strdate;

    DatePickerDialog datePickerDialog;
    Button btndate;

    Bitmap ggpic;

    private static final int pic_id = 123;
    // Define the button and imageview type variable
    Button camera_open_id;
    ImageView click_image_id;
    public Bitmap photo;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    SharedPreference sharedPreference;

    public ProfilePage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfilePage.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfilePage newInstance(String param1, String param2) {
        ProfilePage fragment = new ProfilePage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    private String getTodaysDate() {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month +1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day,month,year);
                btndate.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this.getContext(), style,dateSetListener,year,month,day);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_page, container, false);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if (month == 1) {
            return "JAN";
        }
        if (month == 2) {
            return "FEB";
        }
        if (month == 3) {
            return "MAR";
        }
        if (month == 4) {
            return "APR";
        }
        if (month == 5) {
            return "MAY";
        }
        if (month == 6) {
            return "JUN";
        }
        if (month == 7) {
            return "JUL";
        }
        if (month == 8) {
            return "AUG";
        }
        if (month == 9) {
            return "SEP";
        }
        if (month == 10) {
            return "OCT";
        }
        if (month == 11) {
            return "NOV";
        }
        if (month == 12) {
            return "DEC";
        }
        return "FEB";
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreference=new SharedPreference(getActivity());

        btndate = view.findViewById(R.id.btndatePicker);
        insertData = view.findViewById(R.id.btninsert);

        fname = view.findViewById(R.id.edt1);
        lname = view.findViewById(R.id.edt2);
        contact = view.findViewById(R.id.edt3);
        email = view.findViewById(R.id.edt4);


        camera_open_id = view.findViewById(R.id.camera_button);
        click_image_id = view.findViewById(R.id.click_image);

        camera_open_id.setOnClickListener(v -> {
            Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(camera_intent, pic_id);
        });

        DBHelper dbHelper = new DBHelper(this.getContext());

        initDatePicker();
        //btndate.setText(getTodaysDate());

        insertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strfname = fname.getText().toString().trim();
                strlname = String.valueOf(lname.getText());
                strcontact = contact.getText().toString();
                stremail = email.getText().toString();
                strdate = btndate.getText().toString();
                Bitmap image = ((BitmapDrawable) click_image_id.getDrawable()).getBitmap();

                long id = dbHelper.updateUser(SharedPreference.getLoggedUserObj().getUserId(), strfname, strlname, stremail, strcontact,
                        btndate.getText().toString(),getBitmapAsByteArray(image));
                if(id<=0){
                    Toast.makeText(getActivity(), "Profile not updated ", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "Profile Updated", Toast.LENGTH_SHORT).show();
                    SharedPreference.setLoogedUserObj(new UserModel(SharedPreference.getLoggedUserObj().getUserId(),strfname,strlname,stremail,SharedPreference.getLoggedUserObj().getPassword(),
                            strcontact,btndate.getText().toString(),getBitmapAsByteArray(image)));
                    ggpic = image;

                }

            }
        });

        btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });


        setUserData();

    }

    private void setUserData() {
        fname.setText(SharedPreference.getLoggedUserObj().getfName());
        lname.setText(SharedPreference.getLoggedUserObj().getlName());
        email.setText(SharedPreference.getLoggedUserObj().getEmail());
        contact.setText(SharedPreference.getLoggedUserObj().getPhone());
        btndate.setText(SharedPreference.getLoggedUserObj().getDob());
        Bitmap bitmap = BitmapFactory.decodeByteArray(SharedPreference.getLoggedUserObj().getImage(), 0, SharedPreference.getLoggedUserObj().getImage().length);

        if(bitmap!=null){
            click_image_id.setImageBitmap(bitmap);
        }


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Match the request 'pic id with requestCode
        if (requestCode == pic_id) {
            // BitMap is data structure of image file which store the image in memory
             photo = (Bitmap) data.getExtras().get("data");
            // Set the image in imageview for display
            click_image_id.setImageBitmap(photo);
        }
    }

    public byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }



}
