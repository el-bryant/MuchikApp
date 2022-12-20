package com.example.muchikapp;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.muchikapp.databinding.ActivityRutasBinding;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class Rutas extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityRutasBinding binding;
    String rutas = "";
    Marker markerOrigen, markerDestino;
    double latitudOrigen, latitudDestino, longitudOrigen, longitudDestino;
    Polyline ruta;
    LocationManager locationManager;
    FusedLocationProviderClient fusedLocationClient;
    LocationRequest locationRequest;
    LocationCallback locationCallback;
    private static final long UPDATE_INTERVAL = 1000; // Every 1 second.
    private static final long FASTEST_UPDATE_INTERVAL = 2000; // Every 2 seconds.
    private static final long MAX_WAIT_TIME = UPDATE_INTERVAL * 30; // Every 30 seconds.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRutasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        if (getIntent().getExtras() != null) {
            rutas = getIntent().getStringExtra("ruta");
        }
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        } else {
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                modalActivarGPS();
            } else {
                fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
                fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
                    if (location != null) {
                        latitud = location.getLatitude();
                        longitud = location.getLongitude();
                    }
                });
            }
        }
        createLocationRequest();
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    actualizarUbicacion(location);
                }
            }
        };
    }

    public void createLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(UPDATE_INTERVAL);
        locationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setMaxWaitTime(MAX_WAIT_TIME);
    }

    AlertDialog alert = null;

    public void modalActivarGPS() {
        if (alert == null) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("El GPS está desactivado, ¿desea activarlo?")
                    .setCancelable(false)
                    .setPositiveButton("Sí", (dialog, which) -> startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)))
                    .setNegativeButton("No", (dialog, which) -> dialog.cancel());
            alert = builder.create();
            alert.show();
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Polyline ruta;
        switch (rutas) {
            case "Puerto Eten":
                latitudOrigen = -6.7754676;
                longitudOrigen = -79.8351934;
                latitudDestino = -6.9272961;
                longitudDestino = -79.8662217;
                ruta = mMap.addPolyline(new PolylineOptions()
                        .clickable(true)
                        .add(
                                new LatLng(latitudOrigen, longitudOrigen),
                                new LatLng(-6.7754343, -79.8352015),
                                new LatLng(-6.7755701, -79.8356937),
                                new LatLng(-6.7758791, -79.8356333),
                                new LatLng(-6.7766538, -79.8355193),
                                new LatLng(-6.7767683, -79.8356185),
                                new LatLng(-6.7769281, -79.835538),
                                new LatLng(-6.7769681, -79.8354629),
                                new LatLng(-6.7782012, -79.8350633),
                                new LatLng(-6.7797533, -79.8346043),
                                new LatLng(-6.7814778, -79.8342116),
                                new LatLng(-6.785517, -79.8333649),
                                new LatLng(-6.7973665, -79.8308831),
                                new LatLng(-6.7993653, -79.8305638),
                                new LatLng(-6.7996104, -79.8308401),
                                new LatLng(-6.8001164, -79.8332943),
                                new LatLng(-6.8010761, -79.8383805),
                                new LatLng(-6.8013797, -79.8398665),
                                new LatLng(-6.8025081, -79.8440511),
                                new LatLng(-6.8025134, -79.844334),
                                new LatLng(-6.8021059, -79.8464503),
                                new LatLng(-6.801325, -79.8501528),
                                new LatLng(-6.8025821, -79.8504559),
                                new LatLng(-6.8060784, -79.8513593),
                                new LatLng(-6.8102998, -79.8523738),
                                new LatLng(-6.8115809, -79.8526179),
                                new LatLng(-6.8197499, -79.8547066),
                                new LatLng(-6.8247574, -79.8559611),
                                new LatLng(-6.8314181, -79.8576166),
                                new LatLng(-6.8452108, -79.8609367),
                                new LatLng(-6.8552667, -79.8634764),
                                new LatLng(-6.863261, -79.8651264),
                                new LatLng(-6.8662439, -79.8656895),
                                new LatLng(-6.8669097, -79.8660382),
                                new LatLng(-6.8704434, -79.8667892),
                                new LatLng(-6.8707592, -79.8669321),
                                new LatLng(-6.8708417, -79.8670381),
                                new LatLng(-6.8709907, -79.8670349),
                                new LatLng(-6.8710772, -79.8669524),
                                new LatLng(-6.8713796, -79.866966),
                                new LatLng(-6.8722318, -79.8671471),
                                new LatLng(-6.8751372, -79.867754),
                                new LatLng(-6.8758424, -79.8679142),
                                new LatLng(-6.877516, -79.8681958),
                                new LatLng(-6.8780501, -79.8682491),
                                new LatLng(-6.8792033, -79.868328),
                                new LatLng(-6.8798797, -79.8683078),
                                new LatLng(-6.8808628, -79.8683231),
                                new LatLng(-6.8809506, -79.8682238),
                                new LatLng(-6.8815791, -79.8682265),
                                new LatLng(-6.8841739, -79.8681357),
                                new LatLng(-6.8878266, -79.8679406),
                                new LatLng(-6.8936813, -79.8676355),
                                new LatLng(-6.8947055, -79.8675673),
                                new LatLng(-6.8994293, -79.8670646),
                                new LatLng(-6.9034473, -79.8666691),
                                new LatLng(-6.9058065, -79.8665698),
                                new LatLng(-6.9067095, -79.8665139),
                                new LatLng(-6.9077779, -79.8665045),
                                new LatLng(-6.9106972, -79.8664167),
                                new LatLng(-6.9168075, -79.8661926),
                                new LatLng(-6.9200745, -79.8660901),
                                new LatLng(-6.9216269, -79.8662403),
                                new LatLng(-6.9228069, -79.866378),
                                new LatLng(-6.9233953, -79.8663994),
                                new LatLng(-6.9244684, -79.8663458),
                                new LatLng(-6.9262523, -79.8662592),
                                new LatLng(latitudDestino, longitudDestino)));
                ruta.setTag(rutas);
                break;
            case "Ciudad Eten":
                latitudOrigen = -6.7754354;
                longitudOrigen = -79.8352087;
                latitudDestino = -6.9078584;
                longitudDestino = -79.8624578;
                ruta = mMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .add(
                            new LatLng(latitudOrigen, longitudOrigen),
                            new LatLng(-6.7755553, -79.8356955),
                            new LatLng(-6.776634, -79.8355399),
                            new LatLng(-6.7767991, -79.8356311),
                            new LatLng(-6.7769696, -79.8354863),
                            new LatLng(-6.7791376, -79.8348157),
                            new LatLng(-6.7805865, -79.8344134),
                            new LatLng(-6.7828558, -79.8339735),
                            new LatLng(-6.7856098, -79.8333834),
                            new LatLng(-6.7920126, -79.8320101),
                            new LatLng(-6.7969345, -79.8310016),
                            new LatLng(-6.7981916, -79.8307656),
                            new LatLng(-6.7993528, -79.8306047),
                            new LatLng(-6.7995872, -79.8306798),
                            new LatLng(-6.7998535, -79.8322033),
                            new LatLng(-6.8005567, -79.835379),
                            new LatLng(-6.8011319, -79.8386727),
                            new LatLng(-6.8024849, -79.8439621),
                            new LatLng(-6.8025169, -79.8444341),
                            new LatLng(-6.801313, -79.8501741),
                            new LatLng(-6.8040296, -79.85085),
                            new LatLng(-6.8093776, -79.8522018),
                            new LatLng(-6.8110181, -79.8524915),
                            new LatLng(-6.813756, -79.8531781),
                            new LatLng(-6.81493, -79.8534654),
                            new LatLng(-6.8186852, -79.8544364),
                            new LatLng(-6.8247274, -79.8559433),
                            new LatLng(-6.8290791, -79.8570484),
                            new LatLng(-6.8357826, -79.8587007),
                            new LatLng(-6.8380971, -79.8593101),
                            new LatLng(-6.8388534, -79.859353),
                            new LatLng(-6.8413461, -79.859986),
                            new LatLng(-6.8478159, -79.8616028),
                            new LatLng(-6.8520135, -79.8626546),
                            new LatLng(-6.8608211, -79.864625),
                            new LatLng(-6.8636478, -79.8652019),
                            new LatLng(-6.8661124, -79.8656954),
                            new LatLng(-6.8666982, -79.8659958),
                            new LatLng(-6.8678752, -79.8662425),
                            new LatLng(-6.8703346, -79.8667452),
                            new LatLng(-6.8707927, -79.8669919),
                            new LatLng(-6.8709051, -79.8670535),
                            new LatLng(-6.8710782, -79.866965),
                            new LatLng(-6.8714094, -79.8669564),
                            new LatLng(-6.8729353, -79.8672971),
                            new LatLng(-6.8758912, -79.8679169),
                            new LatLng(-6.8772097, -79.86815),
                            new LatLng(-6.8776624, -79.8682305),
                            new LatLng(-6.8791749, -79.8683217),
                            new LatLng(-6.8808665, -79.8683035),
                            new LatLng(-6.8809837, -79.8682338),
                            new LatLng(-6.8865586, -79.8680163),
                            new LatLng(-6.8937106, -79.8676411),
                            new LatLng(-6.8961907, -79.8674013),
                            new LatLng(-6.897309, -79.8672726),
                            new LatLng(-6.9031788, -79.8667102),
                            new LatLng(-6.9077074, -79.8665043),
                            new LatLng(-6.9077607, -79.8653429),
                            new LatLng(-6.9078764, -79.8645401),
                            new LatLng(-6.9079514, -79.8626369),
                            new LatLng(-6.9079569, -79.8624712),
                            new LatLng(latitudDestino, longitudDestino)));
                ruta.setTag(rutas);
                break;
            case "Lambayeque":
                latitudOrigen = -6.7701013;
                longitudOrigen = -79.8452066;
                latitudDestino = -6.7045891;
                longitudDestino = -79.9029812;
                ruta = mMap.addPolyline(new PolylineOptions()
                        .clickable(true)
                        .add(
                                new LatLng(latitudOrigen, longitudOrigen),
                                new LatLng(-6.7701024, -79.8452459),
                                new LatLng(-6.7699882, -79.8452959),
                                new LatLng(-6.7698227, -79.8454055),
                                new LatLng(-6.7696676, -79.8455017),
                                new LatLng(-6.7695238, -79.8455945),
                                new LatLng(-6.7693629, -79.8457018),
                                new LatLng(-6.7691681, -79.8458848),
                                new LatLng(-6.7676897, -79.8473794),
                                new LatLng(-6.7664524, -79.8487054),
                                new LatLng(-6.7659687, -79.8491668),
                                new LatLng(-6.7659089, -79.8492942),
                                new LatLng(-6.7658864, -79.8494277),
                                new LatLng(-6.765818, -79.8501362),
                                new LatLng(-6.7657297, -79.8513225),
                                new LatLng(-6.7656103, -79.8524542),
                                new LatLng(-6.765628, -79.8526406),
                                new LatLng(-6.7656107, -79.8527759),
                                new LatLng(-6.7655649, -79.8528482),
                                new LatLng(-6.7654434, -79.8528388),
                                new LatLng(-6.7653303, -79.852819),
                                new LatLng(-6.765345, -79.8526588),
                                new LatLng(-6.7652539, -79.8524564),
                                new LatLng(-6.7651916, -79.8524074),
                                new LatLng(-6.7650986, -79.8524142),
                                new LatLng(-6.7650697, -79.8524524),
                                new LatLng(-6.7650446, -79.8525909),
                                new LatLng(-6.7650649, -79.8527106),
                                new LatLng(-6.7651302, -79.8528323),
                                new LatLng(-6.7651528, -79.8531548),
                                new LatLng(-6.7648498, -79.8561827),
                                new LatLng(-6.7647382, -79.8573309),
                                new LatLng(-6.7647495, -79.8574324),
                                new LatLng(-6.764856, -79.8576896),
                                new LatLng(-6.765144, -79.8582609),
                                new LatLng(-6.7653437, -79.858724),
                                new LatLng(-6.7630028, -79.8600152),
                                new LatLng(-6.7618913, -79.8606276),
                                new LatLng(-6.7618207, -79.8606833),
                                new LatLng(-6.7610784, -79.8610625),
                                new LatLng(-6.759965, -79.8616744),
                                new LatLng(-6.7594673, -79.8619647),
                                new LatLng(-6.7586314, -79.8623917),
                                new LatLng(-6.7579863, -79.8626678),
                                new LatLng(-6.7577981, -79.8627487),
                                new LatLng(-6.7577172, -79.8628144),
                                new LatLng(-6.757484, -79.8630083),
                                new LatLng(-6.7561982, -79.8638492),
                                new LatLng(-6.7554229, -79.8643623),
                                new LatLng(-6.7539558, -79.865507),
                                new LatLng(-6.7516842, -79.8676239),
                                new LatLng(-6.7504617, -79.8687273),
                                new LatLng(-6.7442284, -79.8745352),
                                new LatLng(-6.7332359, -79.8848335),
                                new LatLng(-6.7293811, -79.8884233),
                                new LatLng(-6.7270946, -79.8901926),
                                new LatLng(-6.7242387, -79.8918717),
                                new LatLng(-6.7214162, -79.8935061),
                                new LatLng(-6.7197334, -79.8947353),
                                new LatLng(-6.7175226, -79.8966633),
                                new LatLng(-6.7170204, -79.8971676),
                                new LatLng(-6.7164857, -79.8976743),
                                new LatLng(-6.7162106, -79.8979291),
                                new LatLng(-6.7160432, -79.8980943),
                                new LatLng(-6.7158554, -79.8982699),
                                new LatLng(-6.7155916, -79.8985075),
                                new LatLng(-6.7153694, -79.898706),
                                new LatLng(-6.7151383, -79.8989088),
                                new LatLng(-6.7149501, -79.8990724),
                                new LatLng(-6.7147035, -79.8992839),
                                new LatLng(-6.7139169, -79.8998999),
                                new LatLng(-6.7131732, -79.9004161),
                                new LatLng(-6.7129977, -79.9005404),
                                new LatLng(-6.7127057, -79.9007349),
                                new LatLng(-6.7120601, -79.9011569),
                                new LatLng(-6.709698, -79.9026289),
                                new LatLng(-6.7089481, -79.9030936),
                                new LatLng(-6.7086824, -79.9032049),
                                new LatLng(-6.708414, -79.9032814),
                                new LatLng(-6.7080378, -79.9032809),
                                new LatLng(-6.707592, -79.9032366),
                                new LatLng(-6.7063548, -79.903019),
                                new LatLng(-6.7058243, -79.9029173),
                                new LatLng(-6.7055004, -79.9028989),
                                new LatLng(-6.7052743, -79.9028744),
                                new LatLng(-6.7044975, -79.9027422),
                                new LatLng(-6.7044666, -79.9029631),
                                new LatLng(latitudDestino, longitudDestino)));
                ruta.setTag(rutas);
                break;
            case "San José":
                latitudOrigen = -6.767666;
                longitudOrigen = -79.842897;
                latitudDestino = -6.7681175;
                longitudDestino = -79.97015;
                ruta = mMap.addPolyline(new PolylineOptions()
                        .clickable(true)
                        .add(
                                new LatLng(latitudOrigen, longitudOrigen),
                                new LatLng(-6.7676344, -79.8432003),
                                new LatLng(-6.7676088, -79.8434337),
                                new LatLng(-6.7675681, -79.843783),
                                new LatLng(-6.7675492, -79.8440985),
                                new LatLng(-6.7675332, -79.8445102),
                                new LatLng(-6.7675499, -79.8452397),
                                new LatLng(-6.7675698, -79.8460316),
                                new LatLng(-6.7675851, -79.8464721),
                                new LatLng(-6.7676085, -79.8473573),
                                new LatLng(-6.7676112, -79.8474595),
                                new LatLng(-6.7676782, -79.8475775),
                                new LatLng(-6.7679721, -79.8472723),
                                new LatLng(-6.7690299, -79.846175),
                                new LatLng(-6.7692587, -79.8459328),
                                new LatLng(-6.7694425, -79.8457852),
                                new LatLng(-6.7698859, -79.84549),
                                new LatLng(-6.7701456, -79.8453378),
                                new LatLng(-6.7703457, -79.8452748),
                                new LatLng(-6.770627, -79.84519),
                                new LatLng(-6.7709573, -79.8451344),
                                new LatLng(-6.7711744, -79.8451552),
                                new LatLng(-6.7711561, -79.8453912),
                                new LatLng(-6.7711288, -79.8457111),
                                new LatLng(-6.7710975, -79.8460322),
                                new LatLng(-6.7710349, -79.8465596),
                                new LatLng(-6.7709976, -79.8468929),
                                new LatLng(-6.7709483, -79.8473636),
                                new LatLng(-6.7709084, -79.8477512),
                                new LatLng(-6.7706718, -79.8500497),
                                new LatLng(-6.7704137, -79.8527792),
                                new LatLng(-6.7703595, -79.8533784),
                                new LatLng(-6.7703219, -79.853653),
                                new LatLng(-6.7702709, -79.8539428),
                                new LatLng(-6.7702326, -79.8541004),
                                new LatLng(-6.7702076, -79.8542053),
                                new LatLng(-6.7701613, -79.8543475),
                                new LatLng(-6.7700987, -79.8545419),
                                new LatLng(-6.7700611, -79.8546418),
                                new LatLng(-6.7699415, -79.854968),
                                new LatLng(-6.7697873, -79.8552795),
                                new LatLng(-6.7697112, -79.8554291),
                                new LatLng(-6.7696273, -79.8555434),
                                new LatLng(-6.7691068, -79.856245),
                                new LatLng(-6.7689932, -79.8563815),
                                new LatLng(-6.768852, -79.8565492),
                                new LatLng(-6.7688104, -79.8566015),
                                new LatLng(-6.7686982, -79.8567108),
                                new LatLng(-6.768597, -79.8568116),
                                new LatLng(-6.7685717, -79.8568303),
                                new LatLng(-6.7683868, -79.8569848),
                                new LatLng(-6.7683238, -79.8570365),
                                new LatLng(-6.7681667, -79.8571595),
                                new LatLng(-6.7679599, -79.8572906),
                                new LatLng(-6.7674706, -79.8575703),
                                new LatLng(-6.7672874, -79.8576692),
                                new LatLng(-6.7671047, -79.8577678),
                                new LatLng(-6.7656726, -79.8585498),
                                new LatLng(-6.7647684, -79.8590441),
                                new LatLng(-6.76359, -79.8597007),
                                new LatLng(-6.7621013, -79.8605072),
                                new LatLng(-6.7619412, -79.8605968),
                                new LatLng(-6.7618992, -79.860633),
                                new LatLng(-6.7618899, -79.8606514),
                                new LatLng(-6.7618146, -79.8606849),
                                new LatLng(-6.7615582, -79.8608158),
                                new LatLng(-6.7614267, -79.8609049),
                                new LatLng(-6.7613351, -79.8610008),
                                new LatLng(-6.7613071, -79.8610715),
                                new LatLng(-6.7613025, -79.8611604),
                                new LatLng(-6.7613454, -79.861261),
                                new LatLng(-6.7614024, -79.8613183),
                                new LatLng(-6.7614819, -79.8613656),
                                new LatLng(-6.7615871, -79.8613676),
                                new LatLng(-6.7617743, -79.8614343),
                                new LatLng(-6.7619142, -79.8614892),
                                new LatLng(-6.7619785, -79.8615495),
                                new LatLng(-6.762133, -79.8617878),
                                new LatLng(-6.7625526, -79.8625285),
                                new LatLng(-6.7628716, -79.8631166),
                                new LatLng(-6.7639181, -79.8650556),
                                new LatLng(-6.765278, -79.8674983),
                                new LatLng(-6.7656893, -79.868253),
                                new LatLng(-6.7659584, -79.8687117),
                                new LatLng(-6.7672209, -79.8710207),
                                new LatLng(-6.7680483, -79.87255),
                                new LatLng(-6.7685778, -79.8735091),
                                new LatLng(-6.7689943, -79.8742447),
                                new LatLng(-6.7691534, -79.8745628),
                                new LatLng(-6.7693603, -79.8749491),
                                new LatLng(-6.7694685, -79.8751302),
                                new LatLng(-6.7697558, -79.8756468),
                                new LatLng(-6.7708071, -79.8775177),
                                new LatLng(-6.7708519, -79.8775919),
                                new LatLng(-6.7708543, -79.8776583),
                                new LatLng(-6.7708672, -79.8777461),
                                new LatLng(-6.7708652, -79.8778243),
                                new LatLng(-6.7708589, -79.8778943),
                                new LatLng(-6.7708407, -79.8779772),
                                new LatLng(-6.7708131, -79.8780513),
                                new LatLng(-6.7707618, -79.8781481),
                                new LatLng(-6.7707262, -79.8782007),
                                new LatLng(-6.7706912, -79.8782359),
                                new LatLng(-6.7706036, -79.878309),
                                new LatLng(-6.7702279, -79.8786193),
                                new LatLng(-6.770146, -79.8787098),
                                new LatLng(-6.7701114, -79.8787577),
                                new LatLng(-6.770078, -79.8788047),
                                new LatLng(-6.7700238, -79.8789492),
                                new LatLng(-6.7700081, -79.8790391),
                                new LatLng(-6.7699958, -79.8791628),
                                new LatLng(-6.7700028, -79.8792553),
                                new LatLng(-6.7700068, -79.8793053),
                                new LatLng(-6.7699945, -79.8795805),
                                new LatLng(-6.7699962, -79.8802305),
                                new LatLng(-6.7699972, -79.8819911),
                                new LatLng(-6.7699979, -79.8826194),
                                new LatLng(-6.769997, -79.8837992),
                                new LatLng(-6.7699897, -79.8839578),
                                new LatLng(-6.769977, -79.884074),
                                new LatLng(-6.7699294, -79.8843165),
                                new LatLng(-6.7698573, -79.8845956),
                                new LatLng(-6.7697718, -79.8848196),
                                new LatLng(-6.7697099, -79.8849631),
                                new LatLng(-6.7696909, -79.8850036),
                                new LatLng(-6.7696683, -79.8850824),
                                new LatLng(-6.7695815, -79.8853754),
                                new LatLng(-6.7695443, -79.885536),
                                new LatLng(-6.7695203, -79.8856825),
                                new LatLng(-6.7694837, -79.8859423),
                                new LatLng(-6.7694577, -79.8863037),
                                new LatLng(-6.7693661, -79.8875373),
                                new LatLng(-6.769244, -79.8892536),
                                new LatLng(-6.7690895, -79.8913963),
                                new LatLng(-6.7690695, -79.8915984),
                                new LatLng(-6.768999, -79.8926503),
                                new LatLng(-6.7689837, -79.8928421),
                                new LatLng(-6.768932, -79.8934988),
                                new LatLng(-6.7689194, -79.8936625),
                                new LatLng(-6.7688911, -79.8939109),
                                new LatLng(-6.7688483, -79.8942331),
                                new LatLng(-6.7688237, -79.8943776),
                                new LatLng(-6.7687339, -79.8948804),
                                new LatLng(-6.7686946, -79.8950447),
                                new LatLng(-6.7685085, -79.8958085),
                                new LatLng(-6.7684486, -79.8960251),
                                new LatLng(-6.7680448, -79.8973227),
                                new LatLng(-6.7674459, -79.8992502),
                                new LatLng(-6.7672746, -79.8998606),
                                new LatLng(-6.7672413, -79.9001007),
                                new LatLng(-6.767227, -79.9003857),
                                new LatLng(-6.7672317, -79.900556),
                                new LatLng(-6.7672666, -79.9008477),
                                new LatLng(-6.7673057, -79.9009969),
                                new LatLng(-6.7673913, -79.901296),
                                new LatLng(-6.7678575, -79.902486),
                                new LatLng(-6.7682748, -79.9035677),
                                new LatLng(-6.7690443, -79.9055488),
                                new LatLng(-6.7692024, -79.9059998),
                                new LatLng(-6.7693682, -79.9063941),
                                new LatLng(-6.7702285, -79.9085602),
                                new LatLng(-6.7717828, -79.9123136),
                                new LatLng(-6.772128, -79.9133097),
                                new LatLng(-6.7722651, -79.9139575),
                                new LatLng(-6.7723478, -79.9145067),
                                new LatLng(-6.7723871, -79.9149154),
                                new LatLng(-6.7724114, -79.915174),
                                new LatLng(-6.7724204, -79.9154238),
                                new LatLng(-6.7724404, -79.9158612),
                                new LatLng(-6.7724877, -79.9164043),
                                new LatLng(-6.7725732, -79.9175497),
                                new LatLng(-6.7725908, -79.9178096),
                                new LatLng(-6.7726461, -79.9183076),
                                new LatLng(-6.7726754, -79.9186549),
                                new LatLng(-6.772699, -79.9189081),
                                new LatLng(-6.772714, -79.9191049),
                                new LatLng(-6.7727469, -79.9196972),
                                new LatLng(-6.7727654, -79.9200142),
                                new LatLng(-6.7728144, -79.9204876),
                                new LatLng(-6.7728651, -79.9209806),
                                new LatLng(-6.7728934, -79.921223),
                                new LatLng(-6.772937, -79.9216176),
                                new LatLng(-6.7729768, -79.921965),
                                new LatLng(-6.7730427, -79.9225205),
                                new LatLng(-6.7732725, -79.9244469),
                                new LatLng(-6.7733433, -79.9250074),
                                new LatLng(-6.773363, -79.9252447),
                                new LatLng(-6.7733852, -79.925856),
                                new LatLng(-6.7733675, -79.9265745),
                                new LatLng(-6.7733508, -79.9269104),
                                new LatLng(-6.7731818, -79.9305117),
                                new LatLng(-6.7731558, -79.9311384),
                                new LatLng(-6.7731345, -79.9313765),
                                new LatLng(-6.7731192, -79.9316836),
                                new LatLng(-6.7731119, -79.9318001),
                                new LatLng(-6.7730852, -79.9319811),
                                new LatLng(-6.7730483, -79.9321703),
                                new LatLng(-6.7730063, -79.9323104),
                                new LatLng(-6.7729213, -79.9325315),
                                new LatLng(-6.7728569, -79.9326765),
                                new LatLng(-6.7727573, -79.9328596),
                                new LatLng(-6.7718913, -79.9344424),
                                new LatLng(-6.7715998, -79.9350595),
                                new LatLng(-6.7715529, -79.9351628),
                                new LatLng(-6.7711018, -79.9361449),
                                new LatLng(-6.7705872, -79.9372636),
                                new LatLng(-6.770493, -79.9374671),
                                new LatLng(-6.7696833, -79.9391829),
                                new LatLng(-6.7691434, -79.9402948),
                                new LatLng(-6.7689142, -79.9407933),
                                new LatLng(-6.7687899, -79.9410595),
                                new LatLng(-6.7686527, -79.9413348),
                                new LatLng(-6.7685788, -79.9415098),
                                new LatLng(-6.7685246, -79.9416861),
                                new LatLng(-6.768494, -79.9418517),
                                new LatLng(-6.7684594, -79.9421299),
                                new LatLng(-6.7684558, -79.9423247),
                                new LatLng(-6.7684734, -79.94278),
                                new LatLng(-6.7685902, -79.9451666),
                                new LatLng(-6.768603, -79.9455424),
                                new LatLng(-6.768614, -79.9457681),
                                new LatLng(-6.76863, -79.9461593),
                                new LatLng(-6.7687043, -79.9479374),
                                new LatLng(-6.7687308, -79.9485611),
                                new LatLng(-6.7687368, -79.9487311),
                                new LatLng(-6.7687598, -79.9491981),
                                new LatLng(-6.7688433, -79.95092),
                                new LatLng(-6.7688547, -79.9511537),
                                new LatLng(-6.7689055, -79.9524144),
                                new LatLng(-6.7689315, -79.9535122),
                                new LatLng(-6.7689439, -79.9538022),
                                new LatLng(-6.7689742, -79.9542867),
                                new LatLng(-6.7689892, -79.9545634),
                                new LatLng(-6.7689982, -79.9551826),
                                new LatLng(-6.7689979, -79.9564193),
                                new LatLng(-6.7689873, -79.9573271),
                                new LatLng(-6.7689776, -79.957841),
                                new LatLng(-6.7689736, -79.9583583),
                                new LatLng(-6.7689706, -79.9588152),
                                new LatLng(-6.7689687, -79.9600219),
                                new LatLng(-6.7689597, -79.9604383),
                                new LatLng(-6.7689409, -79.9619912),
                                new LatLng(-6.7689254, -79.9632295),
                                new LatLng(-6.7689181, -79.9635939),
                                new LatLng(-6.7688994, -79.9639051),
                                new LatLng(-6.7688721, -79.9640925),
                                new LatLng(-6.7688484, -79.9642005),
                                new LatLng(-6.7687974, -79.9643866),
                                new LatLng(-6.7687091, -79.9645857),
                                new LatLng(-6.7686031, -79.9648142),
                                new LatLng(-6.7685026, -79.965002),
                                new LatLng(-6.7684442, -79.9651033),
                                new LatLng(-6.7680908, -79.9653267),
                                new LatLng(-6.7679707, -79.9655132),
                                new LatLng(-6.7678813, -79.965782),
                                new LatLng(-6.7678132, -79.9660402),
                                new LatLng(-6.7677689, -79.9662112),
                                new LatLng(-6.7677338, -79.9663423),
                                new LatLng(-6.767706, -79.9664948),
                                new LatLng(-6.767697, -79.9665894),
                                new LatLng(-6.7675849, -79.9669066),
                                new LatLng(-6.7675685, -79.9671252),
                                new LatLng(-6.7676008, -79.96745),
                                new LatLng(-6.7677216, -79.9680688),
                                new LatLng(-6.7678448, -79.9687033),
                                new LatLng(-6.7679548, -79.9693036),
                                new LatLng(-6.7679929, -79.9695243),
                                new LatLng(-6.7680196, -79.9696353),
                                new LatLng(-6.7680456, -79.9697484),
                                new LatLng(-6.7680596, -79.9698325),
                                new LatLng(-6.7680835, -79.9699633),
                                new LatLng(latitudDestino, longitudDestino)));
                ruta.setTag(rutas);
                break;
            case "Santa Rosa":
                latitudOrigen = -6.7676527;
                longitudOrigen = -79.842929;
                latitudDestino = -6.8802525;
                longitudDestino = -79.9220465;
                ruta = mMap.addPolyline(new PolylineOptions()
                        .clickable(true)
                        .add(
                                new LatLng(latitudOrigen, longitudOrigen),
                                new LatLng(-6.767667, -79.8429314),
                                new LatLng(-6.7676234, -79.8433083),
                                new LatLng(-6.7675841, -79.843661),
                                new LatLng(-6.7675551, -79.8439709),
                                new LatLng(-6.7675332, -79.8446193),
                                new LatLng(-6.7675465, -79.8451575),
                                new LatLng(-6.7675598, -79.8456678),
                                new LatLng(-6.7675841, -79.8464134),
                                new LatLng(-6.7675908, -79.8466618),
                                new LatLng(-6.76761, -79.8474386),
                                new LatLng(-6.767616, -79.8474728),
                                new LatLng(-6.7676763, -79.8475808),
                                new LatLng(-6.7678158, -79.8474355),
                                new LatLng(-6.7682507, -79.8469811),
                                new LatLng(-6.7684545, -79.8467708),
                                new LatLng(-6.768919, -79.8463015),
                                new LatLng(-6.7691667, -79.8460258),
                                new LatLng(-6.7693185, -79.8458793),
                                new LatLng(-6.7694417, -79.8457851),
                                new LatLng(-6.7697146, -79.8456031),
                                new LatLng(-6.7698601, -79.8455066),
                                new LatLng(-6.7700718, -79.8453759),
                                new LatLng(-6.770155, -79.8453357),
                                new LatLng(-6.7703325, -79.8452787),
                                new LatLng(-6.7706378, -79.845186),
                                new LatLng(-6.7707946, -79.8451562),
                                new LatLng(-6.7709604, -79.845134),
                                new LatLng(-6.7711751, -79.8451562),
                                new LatLng(-6.7711625, -79.8453335),
                                new LatLng(-6.7711201, -79.8458249),
                                new LatLng(-6.7710642, -79.8463245),
                                new LatLng(-6.7710142, -79.8467452),
                                new LatLng(-6.7708789, -79.8480237),
                                new LatLng(-6.7707825, -79.8489674),
                                new LatLng(-6.77062, -79.8505802),
                                new LatLng(-6.7705577, -79.8512011),
                                new LatLng(-6.7705324, -79.8514754),
                                new LatLng(-6.7704725, -79.8521546),
                                new LatLng(-6.7703623, -79.8533766),
                                new LatLng(-6.7703157, -79.853698),
                                new LatLng(-6.7702658, -79.8539538),
                                new LatLng(-6.7702317, -79.8541043),
                                new LatLng(-6.7702014, -79.8542236),
                                new LatLng(-6.7701305, -79.8544452),
                                new LatLng(-6.770083, -79.8545829),
                                new LatLng(-6.7700177, -79.8547579),
                                new LatLng(-6.7699472, -79.8549514),
                                new LatLng(-6.7699113, -79.8550359),
                                new LatLng(-6.7697807, -79.855288),
                                new LatLng(-6.7697142, -79.8554224),
                                new LatLng(-6.7697658, -79.8556682),
                                new LatLng(-6.7699579, -79.8557067),
                                new LatLng(-6.7700717, -79.8559595),
                                new LatLng(-6.7700827, -79.8560407),
                                new LatLng(-6.7700731, -79.8561627),
                                new LatLng(-6.7700278, -79.8565577),
                                new LatLng(-6.7700025, -79.8568078),
                                new LatLng(-6.7699858, -79.8569855),
                                new LatLng(-6.7699557, -79.8572688),
                                new LatLng(-6.7699391, -79.8573821),
                                new LatLng(-6.7699081, -79.8575746),
                                new LatLng(-6.7698734, -79.8577899),
                                new LatLng(-6.7698327, -79.8581274),
                                new LatLng(-6.7697827, -79.8585304),
                                new LatLng(-6.7697095, -79.8590446),
                                new LatLng(-6.7696586, -79.8593722),
                                new LatLng(-6.7695847, -79.8597672),
                                new LatLng(-6.7695804, -79.8597977),
                                new LatLng(-6.7696363, -79.859915),
                                new LatLng(-6.7697102, -79.8599784),
                                new LatLng(-6.7697462, -79.8599992),
                                new LatLng(-6.7698594, -79.8600076),
                                new LatLng(-6.7699334, -79.8599956),
                                new LatLng(-6.7700323, -79.8600945),
                                new LatLng(-6.7700679, -79.8601398),
                                new LatLng(-6.770192, -79.860435),
                                new LatLng(-6.7702655, -79.8606285),
                                new LatLng(-6.7703638, -79.8608166),
                                new LatLng(-6.7707324, -79.8614334),
                                new LatLng(-6.7711216, -79.8620741),
                                new LatLng(-6.7718008, -79.8631527),
                                new LatLng(-6.7719466, -79.8633878),
                                new LatLng(-6.7720071, -79.8634968),
                                new LatLng(-6.7720664, -79.8636396),
                                new LatLng(-6.7720448, -79.8636895),
                                new LatLng(-6.7720508, -79.8637757),
                                new LatLng(-6.772096, -79.8638491),
                                new LatLng(-6.772162, -79.8638894),
                                new LatLng(-6.7722532, -79.8638991),
                                new LatLng(-6.7722915, -79.8639675),
                                new LatLng(-6.7723853, -79.864124),
                                new LatLng(-6.7724519, -79.8641871),
                                new LatLng(-6.7724771, -79.8642254),
                                new LatLng(-6.7724911, -79.864277),
                                new LatLng(-6.7726166, -79.8644903),
                                new LatLng(-6.7728367, -79.864847),
                                new LatLng(-6.7730854, -79.8652507),
                                new LatLng(-6.7734091, -79.8658215),
                                new LatLng(-6.7734797, -79.8659449),
                                new LatLng(-6.7735203, -79.8660092),
                                new LatLng(-6.773726, -79.866313),
                                new LatLng(-6.7739772, -79.8667132),
                                new LatLng(-6.7742445, -79.8671605),
                                new LatLng(-6.7748405, -79.868126),
                                new LatLng(-6.7752344, -79.8687751),
                                new LatLng(-6.7753802, -79.8690054),
                                new LatLng(-6.77601, -79.8700262),
                                new LatLng(-6.7762296, -79.8703794),
                                new LatLng(-6.7763528, -79.8705779),
                                new LatLng(-6.7765411, -79.8708865),
                                new LatLng(-6.7766824, -79.8711251),
                                new LatLng(-6.7767919, -79.8713064),
                                new LatLng(-6.7768282, -79.871353),
                                new LatLng(-6.7768592, -79.8713788),
                                new LatLng(-6.7769038, -79.8713785),
                                new LatLng(-6.7769997, -79.8715384),
                                new LatLng(-6.7772989, -79.8720246),
                                new LatLng(-6.7774138, -79.8722532),
                                new LatLng(-6.7774527, -79.8723857),
                                new LatLng(-6.7776198, -79.8728715),
                                new LatLng(-6.7777217, -79.873127),
                                new LatLng(-6.7778352, -79.8733593),
                                new LatLng(-6.7778782, -79.873429),
                                new LatLng(-6.7779541, -79.8735249),
                                new LatLng(-6.7781672, -79.873877),
                                new LatLng(-6.7783055, -79.8741291),
                                new LatLng(-6.7783561, -79.8742518),
                                new LatLng(-6.7784147, -79.8743766),
                                new LatLng(-6.7786012, -79.8747018),
                                new LatLng(-6.7787563, -79.8749747),
                                new LatLng(-6.7788878, -79.8752012),
                                new LatLng(-6.7790546, -79.8754104),
                                new LatLng(-6.7791912, -79.8755591),
                                new LatLng(-6.7793521, -79.8757036),
                                new LatLng(-6.779824, -79.876054),
                                new LatLng(-6.7803118, -79.8764521),
                                new LatLng(-6.7809994, -79.8770428),
                                new LatLng(-6.7817636, -79.877663),
                                new LatLng(-6.7819091, -79.8777797),
                                new LatLng(-6.7820489, -79.8778755),
                                new LatLng(-6.7824776, -79.87822),
                                new LatLng(-6.7828149, -79.878507),
                                new LatLng(-6.7829219, -79.8785862),
                                new LatLng(-6.7830937, -79.8786797),
                                new LatLng(-6.7832286, -79.8787548),
                                new LatLng(-6.7833531, -79.8788021),
                                new LatLng(-6.7835289, -79.8788477),
                                new LatLng(-6.7837205, -79.8788908),
                                new LatLng(-6.7838548, -79.8789126),
                                new LatLng(-6.7840755, -79.878931),
                                new LatLng(-6.7844809, -79.8789375),
                                new LatLng(-6.7845854, -79.8789455),
                                new LatLng(-6.7857484, -79.8790289),
                                new LatLng(-6.7881696, -79.8791942),
                                new LatLng(-6.7882495, -79.8792079),
                                new LatLng(-6.7885821, -79.8792576),
                                new LatLng(-6.7887452, -79.8793079),
                                new LatLng(-6.7892487, -79.8794805),
                                new LatLng(-6.7896346, -79.8796448),
                                new LatLng(-6.7899977, -79.879808),
                                new LatLng(-6.7901096, -79.8798479),
                                new LatLng(-6.7902461, -79.879922),
                                new LatLng(-6.7903963, -79.8800226),
                                new LatLng(-6.7904985, -79.8801078),
                                new LatLng(-6.7905155, -79.8801376),
                                new LatLng(-6.7905771, -79.8801822),
                                new LatLng(-6.7906576, -79.8801969),
                                new LatLng(-6.7907232, -79.8801829),
                                new LatLng(-6.790814, -79.8801896),
                                new LatLng(-6.7909525, -79.8802084),
                                new LatLng(-6.791093, -79.8802433),
                                new LatLng(-6.7914015, -79.8803708),
                                new LatLng(-6.7918656, -79.8805532),
                                new LatLng(-6.7925745, -79.8808347),
                                new LatLng(-6.7929024, -79.8809789),
                                new LatLng(-6.7931183, -79.8810812),
                                new LatLng(-6.7932534, -79.8811431),
                                new LatLng(-6.7934092, -79.8812323),
                                new LatLng(-6.7934642, -79.8812766),
                                new LatLng(-6.793669, -79.8814289),
                                new LatLng(-6.7937552, -79.8815131),
                                new LatLng(-6.7941106, -79.8819042),
                                new LatLng(-6.7945098, -79.8823732),
                                new LatLng(-6.7953231, -79.8833224),
                                new LatLng(-6.7958846, -79.8840087),
                                new LatLng(-6.7960388, -79.8842256),
                                new LatLng(-6.7962585, -79.8845348),
                                new LatLng(-6.7968447, -79.8854155),
                                new LatLng(-6.7974116, -79.8861896),
                                new LatLng(-6.7975371, -79.8863431),
                                new LatLng(-6.7984438, -79.8875135),
                                new LatLng(-6.7986797, -79.8877965),
                                new LatLng(-6.7990498, -79.888313),
                                new LatLng(-6.7992326, -79.8885557),
                                new LatLng(-6.7995149, -79.8889222),
                                new LatLng(-6.801282, -79.8912036),
                                new LatLng(-6.8036614, -79.8943062),
                                new LatLng(-6.8052247, -79.8963442),
                                new LatLng(-6.8056472, -79.8968765),
                                new LatLng(-6.8056475, -79.8968681),
                                new LatLng(-6.806355, -79.8977982),
                                new LatLng(-6.8110912, -79.9038083),
                                new LatLng(-6.8125042, -79.9055522),
                                new LatLng(-6.8138313, -79.9071837),
                                new LatLng(-6.8139381, -79.9073251),
                                new LatLng(-6.8142461, -79.9077213),
                                new LatLng(-6.8144382, -79.9079603),
                                new LatLng(-6.8150288, -79.9086912),
                                new LatLng(-6.8155636, -79.9093661),
                                new LatLng(-6.8162961, -79.910292),
                                new LatLng(-6.8166789, -79.9107805),
                                new LatLng(-6.8172193, -79.9114905),
                                new LatLng(-6.8175849, -79.9120081),
                                new LatLng(-6.8190123, -79.9138038),
                                new LatLng(-6.8205391, -79.9157354),
                                new LatLng(-6.8221479, -79.9177657),
                                new LatLng(-6.823649, -79.9196528),
                                new LatLng(-6.8239683, -79.9200401),
                                new LatLng(-6.8248412, -79.9210962),
                                new LatLng(-6.8251192, -79.921458),
                                new LatLng(-6.8261638, -79.9228046),
                                new LatLng(-6.8263815, -79.9231352),
                                new LatLng(-6.826753, -79.9237921),
                                new LatLng(-6.8269939, -79.9242592),
                                new LatLng(-6.8271337, -79.924503),
                                new LatLng(-6.8273, -79.9247705),
                                new LatLng(-6.8275283, -79.9251057),
                                new LatLng(-6.8283143, -79.9260633),
                                new LatLng(-6.8284894, -79.9262601),
                                new LatLng(-6.828783, -79.9266068),
                                new LatLng(-6.8291168, -79.9270313),
                                new LatLng(-6.8292906, -79.9272281),
                                new LatLng(-6.8295327, -79.9275244),
                                new LatLng(-6.8296948, -79.9276977),
                                new LatLng(-6.829839, -79.9276957),
                                new LatLng(-6.829941, -79.9276482),
                                new LatLng(-6.8300642, -79.9275375),
                                new LatLng(-6.8301512, -79.9274878),
                                new LatLng(-6.8302021, -79.9274368),
                                new LatLng(-6.8303446, -79.9273694),
                                new LatLng(-6.830941, -79.9272601),
                                new LatLng(-6.8315692, -79.9271551),
                                new LatLng(-6.8317703, -79.9271122),
                                new LatLng(-6.8325433, -79.926981),
                                new LatLng(-6.8331212, -79.9268818),
                                new LatLng(-6.8334351, -79.9268268),
                                new LatLng(-6.8343793, -79.9266859),
                                new LatLng(-6.8345351, -79.9266815),
                                new LatLng(-6.8346956, -79.9266919),
                                new LatLng(-6.8349196, -79.9267663),
                                new LatLng(-6.8353361, -79.9269319),
                                new LatLng(-6.8354666, -79.9269923),
                                new LatLng(-6.8356261, -79.9270872),
                                new LatLng(-6.8356813, -79.9271351),
                                new LatLng(-6.835748, -79.9271951),
                                new LatLng(-6.835811, -79.9272799),
                                new LatLng(-6.8358659, -79.9273892),
                                new LatLng(-6.8359217, -79.9275262),
                                new LatLng(-6.8359653, -79.9276804),
                                new LatLng(-6.8362958, -79.9288183),
                                new LatLng(-6.8365529, -79.9296755),
                                new LatLng(-6.8366655, -79.9301074),
                                new LatLng(-6.8367723, -79.9304614),
                                new LatLng(-6.8369078, -79.9309258),
                                new LatLng(-6.8372965, -79.9322642),
                                new LatLng(-6.8374739, -79.9328822),
                                new LatLng(-6.838084, -79.9349554),
                                new LatLng(-6.8382192, -79.9354473),
                                new LatLng(-6.8384056, -79.9360954),
                                new LatLng(-6.8385118, -79.9360307),
                                new LatLng(-6.8391404, -79.9356874),
                                new LatLng(-6.8397442, -79.9353803),
                                new LatLng(-6.8403625, -79.9350996),
                                new LatLng(-6.8407751, -79.9349124),
                                new LatLng(-6.8408972, -79.9348594),
                                new LatLng(-6.8412532, -79.9347448),
                                new LatLng(-6.8414539, -79.9346855),
                                new LatLng(-6.8419812, -79.9345429),
                                new LatLng(-6.8422251, -79.934474),
                                new LatLng(-6.8423586, -79.934425),
                                new LatLng(-6.8424821, -79.9343945),
                                new LatLng(-6.8455411, -79.9335657),
                                new LatLng(-6.8469052, -79.9331749),
                                new LatLng(-6.8492796, -79.9324827),
                                new LatLng(-6.8519735, -79.9317213),
                                new LatLng(-6.8555255, -79.9307009),
                                new LatLng(-6.8611214, -79.9290636),
                                new LatLng(-6.8708396, -79.9263732),
                                new LatLng(-6.8720857, -79.9260134),
                                new LatLng(-6.873869, -79.9255094),
                                new LatLng(-6.8746462, -79.9253194),
                                new LatLng(-6.8751306, -79.9251868),
                                new LatLng(-6.8756137, -79.9250372),
                                new LatLng(-6.8759188, -79.9249413),
                                new LatLng(-6.8759711, -79.9249252),
                                new LatLng(-6.8760903, -79.9249274),
                                new LatLng(-6.8761818, -79.9248976),
                                new LatLng(-6.8766268, -79.9247083),
                                new LatLng(-6.8777908, -79.92418),
                                new LatLng(-6.8788134, -79.9237329),
                                new LatLng(-6.8794228, -79.9234715),
                                new LatLng(-6.8799666, -79.9232311),
                                new LatLng(-6.8800635, -79.9231573),
                                new LatLng(-6.8800987, -79.923098),
                                new LatLng(-6.8803568, -79.9229467),
                                new LatLng(-6.8804496, -79.9228992),
                                new LatLng(-6.8804167, -79.9227764),
                                new LatLng(-6.8802612, -79.9220897),
                                new LatLng(latitudDestino, longitudDestino)));
                ruta.setTag(rutas);
                break;
            case "Cayalti":
                latitudOrigen = -6.7634847;
                longitudOrigen = -79.8314886;
                latitudDestino = -6.8920408;
                longitudDestino = -79.5617017;
                ruta = mMap.addPolyline(new PolylineOptions()
                        .clickable(true)
                        .add(
                                new LatLng(latitudOrigen, longitudOrigen),
                                new LatLng(-6.7635147, -79.831494),
                                new LatLng(-6.7635163, -79.8313555),
                                new LatLng(-6.76355, -79.8313901),
                                new LatLng(-6.7635686, -79.8313897),
                                new LatLng(-6.7635706, -79.8314001),
                                new LatLng(-6.7636591, -79.8314014),
                                new LatLng(-6.7636577, -79.8313893),
                                new LatLng(-6.763679, -79.831392),
                                new LatLng(-6.763729, -79.8313417),
                                new LatLng(-6.7637283, -79.831329),
                                new LatLng(-6.7637403, -79.8313189),
                                new LatLng(-6.763741, -79.8312384),
                                new LatLng(-6.763729, -79.8312291),
                                new LatLng(-6.763729, -79.8312083),
                                new LatLng(-6.7636997, -79.8311801),
                                new LatLng(-6.7637003, -79.8311077),
                                new LatLng(-6.7636804, -79.8310071),
                                new LatLng(-6.7631802, -79.8297207),
                                new LatLng(-6.7634726, -79.8297405),
                                new LatLng(-6.7640013, -79.8297557),
                                new LatLng(-6.7643742, -79.8297584),
                                new LatLng(-6.7661208, -79.8297875),
                                new LatLng(-6.767403, -79.8297669),
                                new LatLng(-6.7685026, -79.8297285),
                                new LatLng(-6.7708572, -79.8296341),
                                new LatLng(-6.7727163, -79.8295053),
                                new LatLng(-6.773201, -79.8295),
                                new LatLng(-6.7736911, -79.8295161),
                                new LatLng(-6.7740347, -79.8295013),
                                new LatLng(-6.7740494, -79.8295006),
                                new LatLng(-6.7742209, -79.8294521),
                                new LatLng(-6.774219, -79.8294112),
                                new LatLng(-6.7741404, -79.8292228),
                                new LatLng(-6.7741697, -79.8291886),
                                new LatLng(-6.7741996, -79.8291712),
                                new LatLng(-6.7743005, -79.8288899),
                                new LatLng(-6.7744203, -79.8283539),
                                new LatLng(-6.774462, -79.8280206),
                                new LatLng(-6.774478, -79.8277892),
                                new LatLng(-6.7744745, -79.8274289),
                                new LatLng(-6.7744212, -79.8267047),
                                new LatLng(-6.7742774, -79.8245643),
                                new LatLng(-6.7734173, -79.8127107),
                                new LatLng(-6.7730697, -79.8078496),
                                new LatLng(-6.7725246, -79.8005183),
                                new LatLng(-6.7713921, -79.790565),
                                new LatLng(-6.7701437, -79.7792618),
                                new LatLng(-6.7700638, -79.7786959),
                                new LatLng(-6.7700851, -79.7785617),
                                new LatLng(-6.7699728, -79.7773901),
                                new LatLng(-6.7698796, -79.7769529),
                                new LatLng(-6.7694701, -79.7733921),
                                new LatLng(-6.7707165, -79.7733365),
                                new LatLng(-6.7708789, -79.7733445),
                                new LatLng(-6.7711959, -79.7732962),
                                new LatLng(-6.7714298, -79.7731918),
                                new LatLng(-6.7716322, -79.7730396),
                                new LatLng(-6.7717886, -79.7728697),
                                new LatLng(-6.7719337, -79.7726316),
                                new LatLng(-6.7800332, -79.7471415),
                                new LatLng(-6.7803635, -79.7464763),
                                new LatLng(-6.7806678, -79.7461008),
                                new LatLng(-6.7810833, -79.7457843),
                                new LatLng(-6.7816373, -79.7453927),
                                new LatLng(-6.7839535, -79.7437261),
                                new LatLng(-6.7906474, -79.7388793),
                                new LatLng(-6.7911062, -79.7385378),
                                new LatLng(-6.7916016, -79.7380014),
                                new LatLng(-6.7921715, -79.7372665),
                                new LatLng(-6.7924325, -79.7367032),
                                new LatLng(-6.7951017, -79.7305904),
                                new LatLng(-6.7951543, -79.7304713),
                                new LatLng(-6.7952495, -79.7301498),
                                new LatLng(-6.7953636, -79.729516),
                                new LatLng(-6.7953752, -79.7293433),
                                new LatLng(-6.7953883, -79.7291104),
                                new LatLng(-6.7953893, -79.728925),
                                new LatLng(-6.7953658, -79.7285347),
                                new LatLng(-6.7952466, -79.7271346),
                                new LatLng(-6.7938915, -79.7107195),
                                new LatLng(-6.793869, -79.7101982),
                                new LatLng(-6.7938934, -79.7097349),
                                new LatLng(-6.7939201, -79.7095257),
                                new LatLng(-6.7939707, -79.7092742),
                                new LatLng(-6.7940266, -79.7090634),
                                new LatLng(-6.7940499, -79.7089756),
                                new LatLng(-6.7942468, -79.7084589),
                                new LatLng(-6.79433, -79.7082828),
                                new LatLng(-6.7946876, -79.7077081),
                                new LatLng(-6.7959436, -79.7061362),
                                new LatLng(-6.7960025, -79.7060664),
                                new LatLng(-6.797688, -79.7035671),
                                new LatLng(-6.7981148, -79.7030545),
                                new LatLng(-6.798226, -79.7029449),
                                new LatLng(-6.7984725, -79.702716),
                                new LatLng(-6.7986319, -79.7025878),
                                new LatLng(-6.7988532, -79.7024441),
                                new LatLng(-6.7992932, -79.7022348),
                                new LatLng(-6.8040411, -79.7003674),
                                new LatLng(-6.8044334, -79.7001738),
                                new LatLng(-6.8045716, -79.7000867),
                                new LatLng(-6.8047136, -79.6999795),
                                new LatLng(-6.8048498, -79.6998588),
                                new LatLng(-6.8050242, -79.6996947),
                                new LatLng(-6.8051414, -79.6995689),
                                new LatLng(-6.8052456, -79.6994319),
                                new LatLng(-6.8054507, -79.6990859),
                                new LatLng(-6.8055999, -79.6987358),
                                new LatLng(-6.8056835, -79.6984821),
                                new LatLng(-6.8057234, -79.6982907),
                                new LatLng(-6.8076752, -79.6869584),
                                new LatLng(-6.8086688, -79.680932),
                                new LatLng(-6.8086989, -79.6805901),
                                new LatLng(-6.8086912, -79.680413),
                                new LatLng(-6.8086514, -79.6795892),
                                new LatLng(-6.808638, -79.6795396),
                                new LatLng(-6.8086194, -79.679195),
                                new LatLng(-6.8086194, -79.6787631),
                                new LatLng(-6.80863, -79.6784683),
                                new LatLng(-6.8087811, -79.6771843),
                                new LatLng(-6.8088717, -79.6766374),
                                new LatLng(-6.8089592, -79.6763334),
                                new LatLng(-6.8093378, -79.6753825),
                                new LatLng(-6.8095684, -79.6747444),
                                new LatLng(-6.8098087, -79.6738244),
                                new LatLng(-6.8098711, -79.6736044),
                                new LatLng(-6.8098921, -79.6735535),
                                new LatLng(-6.8101557, -79.6728789),
                                new LatLng(-6.8102332, -79.6723898),
                                new LatLng(-6.8102891, -79.6717863),
                                new LatLng(-6.8103628, -79.6710839),
                                new LatLng(-6.8103568, -79.6710225),
                                new LatLng(-6.8103495, -79.6709283),
                                new LatLng(-6.8103511, -79.6707992),
                                new LatLng(-6.8103402, -79.6707083),
                                new LatLng(-6.8104523, -79.6695258),
                                new LatLng(-6.8116881, -79.6457628),
                                new LatLng(-6.8116988, -79.6452636),
                                new LatLng(-6.8122303, -79.6351497),
                                new LatLng(-6.8136323, -79.6073151),
                                new LatLng(-6.813781, -79.6035999),
                                new LatLng(-6.8165411, -79.6038682),
                                new LatLng(-6.8166208, -79.6038805),
                                new LatLng(-6.8168443, -79.6039399),
                                new LatLng(-6.8235994, -79.6061949),
                                new LatLng(-6.8237705, -79.6062307),
                                new LatLng(-6.8238807, -79.6062287),
                                new LatLng(-6.8240165, -79.6061879),
                                new LatLng(-6.8242776, -79.6060124),
                                new LatLng(-6.8254193, -79.605014),
                                new LatLng(-6.825598, -79.6048994),
                                new LatLng(-6.8259446, -79.6047542),
                                new LatLng(-6.826149, -79.6046981),
                                new LatLng(-6.8262692, -79.6046786),
                                new LatLng(-6.8267553, -79.6046405),
                                new LatLng(-6.827785, -79.6046485),
                                new LatLng(-6.8281878, -79.6046945),
                                new LatLng(-6.8282943, -79.6047169),
                                new LatLng(-6.8285818, -79.6047596),
                                new LatLng(-6.8289056, -79.6048606),
                                new LatLng(-6.8293693, -79.6050859),
                                new LatLng(-6.8296302, -79.6051788),
                                new LatLng(-6.8297903, -79.6051952),
                                new LatLng(-6.8311721, -79.6051393),
                                new LatLng(-6.8312893, -79.6051286),
                                new LatLng(-6.8317788, -79.6051393),
                                new LatLng(-6.8318943, -79.6051498),
                                new LatLng(-6.8322433, -79.6051903),
                                new LatLng(-6.8324271, -79.605249),
                                new LatLng(-6.8327177, -79.6053992),
                                new LatLng(-6.8328388, -79.6054509),
                                new LatLng(-6.8329756, -79.6054707),
                                new LatLng(-6.8332289, -79.6054586),
                                new LatLng(-6.8333824, -79.605424),
                                new LatLng(-6.8361412, -79.6043584),
                                new LatLng(-6.8363339, -79.6043014),
                                new LatLng(-6.8366197, -79.6042543),
                                new LatLng(-6.8367369, -79.6042574),
                                new LatLng(-6.8369735, -79.6042845),
                                new LatLng(-6.8412306, -79.6045261),
                                new LatLng(-6.8413658, -79.6045472),
                                new LatLng(-6.8415519, -79.6045788),
                                new LatLng(-6.8417103, -79.6045919),
                                new LatLng(-6.8419333, -79.6045584),
                                new LatLng(-6.8420727, -79.6045084),
                                new LatLng(-6.8422225, -79.6044186),
                                new LatLng(-6.8536067, -79.5945972),
                                new LatLng(-6.8762957, -79.5751942),
                                new LatLng(-6.8851249, -79.5676261),
                                new LatLng(-6.8903682, -79.5631309),
                                new LatLng(-6.8915379, -79.5621017),
                                new LatLng(latitudDestino, longitudDestino)));
                ruta.setTag(rutas);
                break;
            case "Monsefu":
                latitudOrigen = -6.7751349;
                longitudOrigen = -79.8339084;
                latitudDestino = -6.8774796;
                longitudDestino = -79.8716376;
                ruta = mMap.addPolyline(new PolylineOptions()
                        .clickable(true)
                        .add(
                                new LatLng(latitudOrigen, longitudOrigen),
                                new LatLng(-6.7755504, -79.8355875),
                                new LatLng(-6.7755638, -79.835684),
                                new LatLng(-6.7766297, -79.8355292),
                                new LatLng(-6.7767051, -79.8356003),
                                new LatLng(-6.7767727, -79.8356168),
                                new LatLng(-6.7768699, -79.8355886),
                                new LatLng(-6.7769192, -79.8355366),
                                new LatLng(-6.7769315, -79.835478),
                                new LatLng(-6.7797204, -79.8346142),
                                new LatLng(-6.7815319, -79.8342071),
                                new LatLng(-6.7852865, -79.8334167),
                                new LatLng(-6.7962709, -79.8311261),
                                new LatLng(-6.7982496, -79.8307494),
                                new LatLng(-6.7994035, -79.830583),
                                new LatLng(-6.7994488, -79.8305907),
                                new LatLng(-6.799541, -79.8307323),
                                new LatLng(-6.7996113, -79.8309687),
                                new LatLng(-6.7996663, -79.8311755),
                                new LatLng(-6.7997052, -79.8313582),
                                new LatLng(-6.8004214, -79.8346562),
                                new LatLng(-6.8005449, -79.8352861),
                                new LatLng(-6.8012294, -79.8391507),
                                new LatLng(-6.8013579, -79.839745),
                                new LatLng(-6.8022428, -79.8431003),
                                new LatLng(-6.8024548, -79.843801),
                                new LatLng(-6.8025233, -79.8441468),
                                new LatLng(-6.8025243, -79.8443323),
                                new LatLng(-6.8025167, -79.8444147),
                                new LatLng(-6.802493, -79.8445143),
                                new LatLng(-6.8023363, -79.8452548),
                                new LatLng(-6.802287, -79.8455234),
                                new LatLng(-6.8019426, -79.8471616),
                                new LatLng(-6.801645, -79.8486326),
                                new LatLng(-6.8013386, -79.8501515),
                                new LatLng(-6.8014245, -79.8501562),
                                new LatLng(-6.8136256, -79.8531375),
                                new LatLng(-6.8147369, -79.8534134),
                                new LatLng(-6.8315981, -79.857666),
                                new LatLng(-6.865918, -79.8656568),
                                new LatLng(-6.8661641, -79.8657252),
                                new LatLng(-6.8664121, -79.8658469),
                                new LatLng(-6.8667512, -79.8660094),
                                new LatLng(-6.8668941, -79.8660509),
                                new LatLng(-6.8670113, -79.8660707),
                                new LatLng(-6.870279, -79.866736),
                                new LatLng(-6.8703236, -79.8667517),
                                new LatLng(-6.8705577, -79.8668502),
                                new LatLng(-6.8707062, -79.8669125),
                                new LatLng(-6.8707801, -79.8669451),
                                new LatLng(-6.870821, -79.8670125),
                                new LatLng(-6.8708969, -79.867043),
                                new LatLng(-6.8709871, -79.8670343),
                                new LatLng(-6.871068, -79.8669511),
                                new LatLng(-6.871385, -79.8669631),
                                new LatLng(-6.8714716, -79.8669853),
                                new LatLng(-6.8734896, -79.8674014),
                                new LatLng(-6.8737988, -79.8674618),
                                new LatLng(-6.8738533, -79.8674752),
                                new LatLng(-6.8757099, -79.8678815),
                                new LatLng(-6.8758925, -79.8679336),
                                new LatLng(-6.876015, -79.8679461),
                                new LatLng(-6.8776184, -79.8682183),
                                new LatLng(-6.8776128, -79.869013),
                                new LatLng(-6.8776118, -79.8696778),
                                new LatLng(latitudDestino, longitudDestino)));
                ruta.setTag(rutas);
                break;
        }
        if (markerOrigen != null) {
            markerOrigen.setPosition(new LatLng(latitudOrigen, longitudOrigen));
        } else {
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(new LatLng(latitudOrigen, longitudOrigen));
            markerOrigen = mMap.addMarker(markerOptions);
        }
        if (markerDestino != null) {
            markerDestino.setPosition(new LatLng(latitudDestino, longitudDestino));
        } else {
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(new LatLng(latitudDestino, longitudDestino));
            markerDestino = mMap.addMarker(markerOptions);
        }
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(markerOrigen.getPosition());
        builder.include(markerDestino.getPosition());
        LatLngBounds bounds = builder.build();
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        int padding = (int) (width * 0.25);
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 111);
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actualizarUbicacion(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locListener);

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            actualizarUbicacion(location);
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
        @Override
        public void onProviderEnabled(String provider) {
        }
        @Override
        public void onProviderDisabled(String provider) {
        }
    };
    public static double latitud = 0.0, longitud = 0.0;
    Marker markerMiUbicacion;

    public void actualizarUbicacion(Location location) {
        if (location != null) {
            latitud = location.getLatitude();
            longitud = location.getLongitude();
            if (markerMiUbicacion != null) {
                markerMiUbicacion.setPosition(new LatLng(location.getLatitude(), location.getLongitude()));
            } else {
                MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude()));
                markerMiUbicacion = mMap.addMarker(markerOptions);
            }
        }
    }
}