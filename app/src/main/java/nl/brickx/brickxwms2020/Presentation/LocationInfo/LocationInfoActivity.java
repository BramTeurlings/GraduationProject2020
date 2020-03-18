package nl.brickx.brickxwms2020.Presentation.LocationInfo;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import dagger.android.support.DaggerAppCompatActivity;
import nl.brickx.brickxwms2020.R;
import nl.brickx.domain.Models.LocationInfoRecyclerModel;

public class LocationInfoActivity extends DaggerAppCompatActivity implements LocationInfoContract.View{

    RecyclerView recyclerView;
    LocationInfoAdapter recyclerViewAdapter;
    TextInputEditText barcodeInput;
    TextView locationTitleTextView;
    TextInputLayout locationInputLayout;
    ProgressBar locationProgressBar;

    @Inject
    LocationInfoPresenter presenter;

    public static Intent createIntent(Context context){
        return new Intent(context, LocationInfoActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_info_page);
        recyclerView = findViewById(R.id.location_info_recycler_view);
        barcodeInput = findViewById(R.id.location_info_textInput);
        locationTitleTextView = findViewById(R.id.location_info_location_name_content);
        locationProgressBar = findViewById(R.id.loadingIcon);
        locationInputLayout = findViewById(R.id.location_info_textInputLayout);
        presenter.buildLocationTag("");

        initRecyclerViews();

        barcodeInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(barcodeInput.getWindowToken(), 0);
                    onBarcodeScanned();
                    return true;
                }
                return false;
            }
        });
    }

    private void initRecyclerViews(){
        //Todo: Get data from domain layer and data layer and bind to the correct adapter.
        recyclerViewAdapter = new LocationInfoAdapter(new ArrayList<>());

        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onBarcodeScanned(){
        try{
            getLocationInfoByScan(barcodeInput.getText().toString());
        }catch (NullPointerException e){
            //Todo: Tell user to fill in a code.
        }
    }

    @Override
    public void setLocationTag(SpannableStringBuilder locationTag){
        locationTitleTextView.setText(locationTag);
    }

    @Override
    public void getLocationInfoByScan(String scan) {
        presenter.getLocationInfoByScan(scan);
    }

    @Override
    public void onLocationInfoReceived(List<LocationInfoRecyclerModel> locationInfoRecyclerModel) {
        recyclerViewAdapter.setData(locationInfoRecyclerModel);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void clearBarcodeInput() {
        barcodeInput.setText("");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        presenter.dispose();
    }

    @Override
    public void setErrorMessage(String message){
        locationInputLayout.setError(message);
    }

    @Override
    public void changeLoadingState(Boolean isLoading) {
        if(isLoading){
            locationProgressBar.setVisibility(View.VISIBLE);
        }else{
            locationProgressBar.setVisibility(View.GONE);
        }
    }
}
