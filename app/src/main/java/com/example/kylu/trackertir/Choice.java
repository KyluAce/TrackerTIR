package com.example.kylu.trackertir;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



//import org.apache.commons.net.ftp.FTP;
//import org.apache.commons.net.ftp.FTPClient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class Choice extends Activity {
    public Button rejestracja;
    public Button ustawienia;
    public TextView lokacja;
    public MyLocationListener locationListener;
    public MyLocationListener locationListener2;
    public static Context context;
    public String mPhoneNumber;
    public String imei;
    public String macAddress;
    public String Data;
    public String Czas;
    public String longitude;
    public String latitude;
    public String elevation;
    public String speed;
    public String accuracy;
    public String bearing;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        rejestracja = (Button) findViewById(R.id.button2);
        ustawienia = (Button) findViewById(R.id.button);
        lokacja = (TextView) findViewById(R.id.text);
        //GetLocation = (Button) findViewById(R.id.button4);
        context = getApplicationContext();
        TelephonyManager tMgr = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        mPhoneNumber = tMgr.getLine1Number();
        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        imei = tm.getDeviceId();


        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        macAddress = wInfo.getMacAddress();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Data = sdf.format(new Date());

        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
        Czas = sdf2.format(new Date());




       // lokacja.setText(Data);


        turnGPSOn();

        final LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        final LocationManager locationManager2 = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);

        locationListener = new MyLocationListener();
        locationManager.requestLocationUpdates(LocationManager
                .GPS_PROVIDER, 5000, 10, locationListener);






        XmlFileCreator();

 /*       locationListener2 = new MyLocationListener();
        locationManager2.requestLocationUpdates(LocationManager
                .NETWORK_PROVIDER, 5000, 10, locationListener);*/

        rejestracja.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Choice.this, Rejestracja.class);
                startActivity(intent);
            }
        });

        ustawienia.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                sendFile();
                //goforIt();
               // SendFileGPS();
                Intent intent = new Intent(Choice.this, PopPass.class);
                startActivity(intent);
            }
        });


    }






/*
    public void SendFileGPS()
    {
        FTPClient mFTP = new FTPClient();
        try {
            // Connect to FTP Server
            mFTP.connect("188.116.19.206");
            mFTP.login("mobile@trimax.hekko24.pl", "fDy5GmR6");
            mFTP.setFileType(FTP.BINARY_FILE_TYPE);
            mFTP.enterLocalPassiveMode();

            // Prepare file to be uploaded to FTP Server
            File file = new File("/path/to/dane2.xml");
            FileInputStream ifile = new FileInputStream(file);

            // Upload file to FTP Server
            mFTP.storeFile("filetotranfer",ifile);
            mFTP.disconnect();
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


*/



        public void XmlFileCreator ()
        {
            File newxmlfile = new File(Environment.getExternalStorageDirectory()+"/dane2.xml");
            try{
                newxmlfile.createNewFile();
            }catch(IOException e){
                Log.e("IOException", "exception in createNewFile() method");
            }
            FileOutputStream fileos = null;
            try{
                fileos = new FileOutputStream(newxmlfile);
            }catch(FileNotFoundException e){
                Log.e("FileNotFoundException", "can't create FileOutputStream");
            }
            //we create a XmlSerializer in order to write xml data
            XmlSerializer serializer = Xml.newSerializer();
            try {

                //we set the FileOutputStream as output for the serializer, using UTF-8 encoding
                serializer.setOutput(fileos, "UTF-8");
                //Write <?xml declaration with encoding (if encoding not null) and standalone flag (if standalone not null)
                serializer.startDocument(null, Boolean.valueOf(true));
                //set indentation option
               // serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);

                serializer.startTag(null, "devices");
                serializer.startTag(null, "device");

                serializer.startTag(null, "phone_number");
                serializer.text(mPhoneNumber);
                serializer.endTag(null, "phone_number");

                serializer.startTag(null, "imei");
                serializer.text(imei);
                serializer.endTag(null, "imei");

                serializer.startTag(null, "mac");
                serializer.text(macAddress);
                serializer.endTag(null, "mac");

                serializer.startTag(null, "registration_number");
                serializer.text(Rejestracja.numerR);
                serializer.endTag(null, "registration_number");

                serializer.startTag(null, "positions");
                serializer.startTag(null, "position");

                serializer.startTag(null, "date");
                serializer.text(Data);
                serializer.endTag(null, "date");

                serializer.startTag(null, "time");
                serializer.text(Czas);
                serializer.endTag(null, "time");

                serializer.startTag(null, "latitude");
              //  serializer.text(latitude);
                serializer.endTag(null, "latitude");

                serializer.startTag(null, "longitude");
               // serializer.text(longitude);
                serializer.endTag(null, "longitude");

                serializer.startTag(null, "elevation");
                serializer.endTag(null, "elevation");

                serializer.startTag(null, "accuracy");
                serializer.endTag(null, "accuracy");

                serializer.startTag(null, "bearing");
                serializer.endTag(null, "bearing");

                serializer.startTag(null, "speed");
                serializer.endTag(null, "speed");

                serializer.startTag(null, "type");
                serializer.endTag(null, "type");

                serializer.startTag(null, "description");
                serializer.endTag(null, "description");

                serializer.endTag(null, "position");
                serializer.endTag(null, "positions");

                serializer.endTag(null, "device");
                serializer.endTag(null, "devices");


                serializer.endDocument();
                //write xml data into the FileOutputStream
                serializer.flush();
                //finally we close the file stream
                fileos.close();

            } catch (Exception e) {
                Log.e("Exception","error occurred while creating xml file");
            }
            }



    public void sendFile()
    {
        String url = "http://trimax.hekko24.pl/_uploads/index.php";
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),
                "/dane2xml");
        try {
            HttpClient httpclient = new DefaultHttpClient();

            HttpPost httppost = new HttpPost(url);

            InputStreamEntity reqEntity = new InputStreamEntity(
                    new FileInputStream(file), -1);
            reqEntity.setContentType("binary/octet-stream");
            reqEntity.setChunked(true); // Send in multiple parts if needed
            httppost.setEntity(reqEntity);
            HttpResponse response = httpclient.execute(httppost);
            //Do something with response...

        } catch (Exception e) {
            // show error
        }
    }






    public static class BootReceiver extends BroadcastReceiver {

        Context mContext;
        private final String BOOT_ACTION = "android.intent.action.BOOT_COMPLETED";
        @Override
        public void onReceive(Context context, Intent intent) {
            mContext = context;
            String action = intent.getAction();
            if(action.equalsIgnoreCase(BOOT_ACTION))
            {
                startService();
            }

        }

        private void startService()
        {
            Intent mServiceIntent = new Intent();
            mServiceIntent.setAction("com.bootservice.test.DataService");
            mContext.startService(mServiceIntent);
        }
    }


  /*  public class FileUpload
    {


        public void upload( String ftpServer, String user, String password,
                            String fileName, File source ) throws MalformedURLException,
                IOException
        {
            if (ftpServer != null &amp;&amp; fileName != null &amp;&amp; source != null)
            {
                StringBuffer sb = new StringBuffer( "ftp://" );
                // check for authentication else assume its anonymous access.
                if (user != null &amp;&amp; password != null)
                {
                    sb.append( user );
                    sb.append( ':' );
                    sb.append( password );
                    sb.append( '@' );
                }
                sb.append( ftpServer );
                sb.append( '/' );
                sb.append( fileName );
         *//*
          * type ==&gt; a=ASCII mode, i=image (binary) mode, d= file directory
          * listing
          *//*
                sb.append( ";type=i" );

                BufferedInputStream bis = null;
                BufferedOutputStream bos = null;
                try
                {
                    URL url = new URL( sb.toString() );
                    URLConnection urlc = url.openConnection();

                    bos = new BufferedOutputStream( urlc.getOutputStream() );
                    bis = new BufferedInputStream( new FileInputStream( source ) );

                    int i;
                    // read byte by byte until end of stream
                    while ((i = bis.read()) != -1)
                    {
                        bos.write( i );
                    }
                }
                finally
                {
                    if (bis != null)
                        try
                        {
                            bis.close();
                        }
                        catch (IOException ioe)
                        {
                            ioe.printStackTrace();
                        }
                    if (bos != null)
                        try
                        {
                            bos.close();
                        }
                        catch (IOException ioe)
                        {
                            ioe.printStackTrace();
                        }
                }
            }
            else
            {
                System.out.println( "Input not available." );
            }
        }

*/


/*    private class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    // code to get and send location information
                    LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    if (!locManager
                            .isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        turnGPSOn();
                    }

                    try {
                        locManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER, 1000, 10,
                                locationListener);

                    } catch (Exception ex) {
                        turnGPSOff();
                    }

                }

            });
        }
    }

    private void updateWithNewLocation(Location location) {
        String latLongString = "";
        try {
            if (location != null) {

                Log.e("test", "gps is on send");
                latitude = Double.toString(location.getLatitude());
                longitude = Double.toString(location.getLongitude());

                Log.e("test", "location send");


                locManager.removeUpdates(locationListener);


                latLongString = "Lat:" + latitude + "\nLong:" + longitude;
                Log.w("CurrentLocLatLong", latLongString);
            } else {
                latLongString = "No location found";
            }
        } catch (Exception e) {
        }

    }   private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            updateWithNewLocation(location);
        }

        public void onProviderDisabled(String provider) {
            updateWithNewLocation(null);
        }

        public void onProviderEnabled(String provider) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };


    long gpsTimeInterval=2000;
    void startTimer()
    {

        Timer myTimer = new Timer();
        MyTimerTask myTimerTask = new MyTimerTask();
        myTimer.scheduleAtFixedRate(myTimerTask, 0,
                gpsTimeInterval);
    }*/








    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    private void turnGPSOn(){
        String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        if(!provider.contains("gps")){ //if gps is disabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            sendBroadcast(poke);
        }
    }



    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    private void turnGPSOff(){
        String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        if (provider.contains("gps")){ //if gps is enabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            sendBroadcast(poke);
        }
    }




    @TargetApi(Build.VERSION_CODES.FROYO)
    private Boolean displayGpsStatus() {
        ContentResolver contentResolver = getBaseContext()
                .getContentResolver();
        boolean gpsStatus = Settings.Secure
                .isLocationProviderEnabled(contentResolver,
                        LocationManager.GPS_PROVIDER);
        if (gpsStatus)
        {
            return true;

        } else
        {
            return false;
        }
    }

    /*---------- Listener class to get coordinates ------------- */
    private class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location loc) {
            lokacja.setText("");
            Toast.makeText(
                    getBaseContext(),
                    "Location changed: Lat: " + loc.getLatitude() + " Lng: "
                            + loc.getLongitude(), Toast.LENGTH_SHORT).show();
            longitude = "Longitude: " + loc.getLongitude();
            // Log.v(TAG, longitude);
            latitude = "Latitude: " + loc.getLatitude();
            speed = "Speed: " + loc.getSpeed();
            accuracy = "Accuracy: " + loc.getAccuracy();
            bearing = "Bearing: " + loc.getBearing();
            elevation = "Altitude: " + loc.getAltitude();
            // Log.v(TAG, latitude);
        /*------- To get city name from coordinates -------- */
            String cityName = null;
            Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
            List<Address> addresses;
            try {
                addresses = gcd.getFromLocation(loc.getLatitude(),
                        loc.getLongitude(), 1);
                if (addresses.size() > 0)
                    System.out.println(addresses.get(0).getLocality());
                cityName = addresses.get(0).getLocality();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String s = longitude + "\n" + latitude + "\n" +"Accuracy" + accuracy+ "\n"  +"Bearing" + bearing +"\n"+ "Speed" + speed + "\n"+ "altitude" +elevation +"\n";

            lokacja.setText(s);
        }
        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }

}