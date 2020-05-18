package nl.brickx.brickxwms2020.Presentation.StockTransferMain;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import nl.brickx.brickxwms2020.R;
import nl.brickx.brickxwms2020.Support.KeyboardHider;
import nl.brickx.domain.Models.BatchNumberSelectionModelDto;
import nl.brickx.domain.Models.LocationInfoRecyclerModel;
import nl.brickx.domain.Models.OrderPickSerialStatusModel;
import nl.brickx.domain.Models.StockTransferDto;

public class StockTransferMainActivity extends DaggerAppCompatActivity implements StockTransferMainContract.View {

    @Inject
    StockTransferMainPresenter presenter;

    private LocationInfoRecyclerModel fromItemLocationData;
    private Boolean expansionToggle = false;
    private MaterialButton transferButton;
    private LocationInfoRecyclerModel toItemLocationData;
    private StockTransferMainLocationFromAdapter fromAdapter;
    private StockTransferMainLocationToAdapter toAdapter;
    public List<OrderPickSerialStatusModel> serialNumbers = new ArrayList<>();
    private List<LocationInfoRecyclerModel> tempLocationInfoModelList = new ArrayList<>();
    private String[] strings;
    private StockTransferMainStatusSerialNumbersAdapter serialNumbersAdapter;
    private ImageView statusExpander;
    private ViewGroup.LayoutParams tempParams;
    TextView amountOfSerialNumbersScanned;
    RecyclerView statusSerialNumberRecycler;
    ViewFlipper viewFlipper;
    RecyclerView fromRecycler;
    RecyclerView toRecycler;
    TextInputEditText barcodeInput;
    TextInputEditText amountInput;
    ProgressBar loadingProgressBar;
    TextInputLayout combinedInputLayout;

    private final String TAG = "StockTransferMain: ";


    public static Intent createIntent(Context context){
        return new Intent(context, StockTransferMainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_transfer_main_page);

        //Get data from previous activity.
        fromItemLocationData = (LocationInfoRecyclerModel)getIntent().getSerializableExtra("locationInfo");
        tempLocationInfoModelList.clear();
        tempLocationInfoModelList.add(fromItemLocationData);

        barcodeInput = findViewById(R.id.combined_info_textinputEditText);
        fromRecycler = findViewById(R.id.stock_transfer_from_recycler);
        toRecycler = findViewById(R.id.stock_transfer_to_recycler);
        statusSerialNumberRecycler = findViewById(R.id.order_pick_status_item_serial_number_recycler);
        amountOfSerialNumbersScanned = findViewById(R.id.order_pick_status_item_serial_number_title);
        transferButton = findViewById(R.id.stock_transfer_confirm_button);
        loadingProgressBar = findViewById(R.id.loadingIcon);

        viewFlipper = findViewById(R.id.stock_transfer_status_view_flipper);
        if(fromItemLocationData.getSerialnumbersRequired()){
            viewFlipper.setDisplayedChild(1);
        }else{
            viewFlipper.setDisplayedChild(0);
        }

        initRecyclerViews();

        amountInput = findViewById(R.id.stock_transfer_amount_textInputEditText);
        amountInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        actionId == EditorInfo.IME_ACTION_DONE ||
                        event != null &&
                                event.getAction() == KeyEvent.ACTION_DOWN &&
                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (event == null || !event.isShiftPressed()) {
                        // the user is done typing.
                        hideKeyboard();
                        clearFocus();
                    }
                }
                return true;
            }
        });

        statusExpander = findViewById(R.id.order_pick_serial_numbers_arrow_image);
        statusExpander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Todo: Expand Menu toggle
                if(!expansionToggle){
                    tempParams = statusSerialNumberRecycler.getLayoutParams();
                    tempParams.height = tempParams.height*2;
                    statusSerialNumberRecycler.setLayoutParams(tempParams);
                    expansionToggle = true;
                    statusExpander.setImageDrawable(getDrawable(R.drawable.ic_keyboard_arrow_up_black_24dp));
                }else{
                    tempParams = statusSerialNumberRecycler.getLayoutParams();
                    tempParams.height = tempParams.height/2;
                    statusSerialNumberRecycler.setLayoutParams(tempParams);
                    expansionToggle = false;
                    statusExpander.setImageDrawable(getDrawable(R.drawable.ic_keyboard_arrow_down_black_24dp));
                }
            }
        });

        barcodeInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(barcodeInput.getWindowToken(), 0);
                    onBarcodeScanned(barcodeInput.getText().toString());
                    return true;
                }
                return false;
            }
        });

        transferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    //Todo: Verify please
                    if(!toItemLocationData.getLocationTag().equals("") && toItemLocationData.getLocationTag() != null && (!amountInput.getText().toString().equals("") || serialNumbersAdapter.getItemCount() > 0)){
                        double quantity = 0;
                        List<BatchNumberSelectionModelDto> serialNumbers = new ArrayList<>();
                        if(fromItemLocationData.getSerialnumbersRequired()){
                            quantity = serialNumbersAdapter.getItemCount();
                            for(int i = 0; i < fromItemLocationData.getScannedNumbers().size(); i++){
                                serialNumbers.add(new BatchNumberSelectionModelDto(fromItemLocationData.getScannedNumbers().get(i)));
                            }
                        }else{
                            try{
                                quantity = Double.parseDouble(amountInput.getText().toString());
                            }catch (Exception e){
                                Log.i(TAG, "Unable to parse input of quantity field.");
                            }
                        }
                        if((quantity > 0 || quantity < 0) && Math.abs(fromItemLocationData.getProductStock()) - quantity >= 0){
                            presenter.completeStockTransfer(new StockTransferDto(fromItemLocationData.getProductScan(), fromItemLocationData.getLocationTag(), toItemLocationData.getLocationTag(), quantity, serialNumbers));

                        }else{
                            makeErrorToast("Foutief aantal opgegeven.");
                        }

                    }else{
                        makeErrorToast("Vul alle velden volledig in. Vergeet niet een 'naar' locatie te scannen.");
                    }
                }catch (Exception e){
                    Log.i(TAG, "Unable to parse data required for stock transfer.");
                    makeErrorToast("Vul alle velden volledig in. Vergeet niet een 'naar' locatie te scannen.");
                    e.printStackTrace();
                }
            }
        });
    }

    public void initRecyclerViews(){
        //From adapter
        fromAdapter = new StockTransferMainLocationFromAdapter(tempLocationInfoModelList);
        fromRecycler.setAdapter(fromAdapter);
        fromRecycler.setLayoutManager(new LinearLayoutManager(this));

        //To adapter:
        toAdapter = new StockTransferMainLocationToAdapter(new ArrayList<>());
        toRecycler.setAdapter(toAdapter);
        toRecycler.setLayoutManager(new LinearLayoutManager(this));

        //Serialnumbers:
        serialNumbersAdapter = new StockTransferMainStatusSerialNumbersAdapter(new ArrayList<>());
        serialNumbersAdapter.setPresenter(presenter);
        statusSerialNumberRecycler.setAdapter(serialNumbersAdapter);
        statusSerialNumberRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onBarcodeScanned(String scan){
        if(toItemLocationData == null || toAdapter.getItemCount() < 1){
            toItemLocationData = new LocationInfoRecyclerModel(scan);
            toAdapter.addItem(toItemLocationData);
        }else{
            if(fromItemLocationData.getSerialnumbersRequired()){
                //Todo: See if serial number is valid.
                strings = scan.replaceAll("\\s+","").split(";");
                for(int i = 0; i < strings.length; i++){
                    if(!fromItemLocationData.getScannedNumbers().contains(strings[i]) && fromItemLocationData.getAvailibleNumbers().contains(strings[i])){
                        fromItemLocationData.getScannedNumbers().add(strings[i]);
                    }
                }
                refreshSerialNumberData();
                amountOfSerialNumbersScanned.setText(String.valueOf(getString(R.string.Order_pick_serial_number_hint) + " " + fromItemLocationData.getScannedNumbers().size()));
            }
        }
    }

    public void refreshSerialNumberData(){
        serialNumbers.clear();
        for(int i = 0; i < fromItemLocationData.getScannedNumbers().size(); i++){
            serialNumbers.add(new OrderPickSerialStatusModel(fromItemLocationData.getScannedNumbers().get(i), true, fromItemLocationData.getProductId()));
        }
        serialNumbersAdapter.setData(serialNumbers);
        serialNumbersAdapter.notifyDataSetChanged();
    }

    private void makeErrorToast(String text){
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    private void hideKeyboard(){
        KeyboardHider.hideKeyboard(this);
    }

    @Override
    public void clearFocus() {
        amountInput.setFocusableInTouchMode(false);
        amountInput.setFocusable(false);
        viewFlipper.clearFocus();
        amountInput.setFocusableInTouchMode(true);
        amountInput.setFocusable(true);
    }

    @Override
    public LocationInfoRecyclerModel getLocationRecyclerModel() {
        return fromItemLocationData;
    }

    @Override
    public void updateSerialNumbers(LocationInfoRecyclerModel model) {
        fromItemLocationData = model;
        refreshSerialNumberData();
    }

    @Override
    public void onLocationInfoReceived(List<LocationInfoRecyclerModel> holder, String scan) {

    }

    @Override
    public void getProductInfoByScan(String scannedCode){
        setErrorMessage(null);
        presenter.getProductInfoByScan(scannedCode);
    }

    @Override
    public void clearBarcodeInput(){
        barcodeInput.setText("");
    }

    @Override
    public void onDestroy(){
        presenter.dispose();
        super.onDestroy();
    }

    @Override
    public void changeLoadingState(Boolean isLoading) {
        if(isLoading){
            loadingProgressBar.setVisibility(View.VISIBLE);
        }else{
            loadingProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void setErrorMessage(String message) {
        combinedInputLayout.setError(message);
    }
}
