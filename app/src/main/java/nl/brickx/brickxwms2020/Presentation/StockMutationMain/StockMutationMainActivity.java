package nl.brickx.brickxwms2020.Presentation.StockMutationMain;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.ViewFlipper;

import androidx.constraintlayout.widget.ConstraintLayout;
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
import nl.brickx.domain.Models.StockMutationDto;

public class StockMutationMainActivity extends DaggerAppCompatActivity implements StockMutationMainContract.View {

    @Inject
    StockMutationMainPresenter presenter;

    private LocationInfoRecyclerModel fromItemLocationData;
    private Boolean expansionToggle = false;
    private MaterialButton mutationButton;
    private StockMutationMainLocationAdapter locationAdapter;
    private StockMutationMainProductAdapter productAdapter;
    public List<OrderPickSerialStatusModel> serialNumbers = new ArrayList<>();
    private List<LocationInfoRecyclerModel> tempLocationInfoModelList = new ArrayList<>();
    private String[] strings;
    private StockMutationMainStatusSerialNumbersAdapter serialNumbersAdapter;
    private ImageView statusExpander;
    private ViewGroup.LayoutParams tempParams;
    ConstraintLayout plusMinusToggleContraint;
    ToggleButton plusMinusToggle;
    TextView amountOfSerialNumbersScanned;
    RecyclerView statusSerialNumberRecycler;
    ViewFlipper viewFlipper;
    RecyclerView fromRecycler;
    RecyclerView toRecycler;
    TextInputEditText reasonInput;
    TextInputEditText amountInput;
    ProgressBar loadingProgressBar;
    TextInputLayout combinedInputLayout;

    private final String TAG = "StockMutationMain: ";


    public static Intent createIntent(Context context){
        return new Intent(context, StockMutationMainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_mutation_main_page);

        //Get data from previous activity.
        fromItemLocationData = (LocationInfoRecyclerModel)getIntent().getSerializableExtra("locationInfo");
        tempLocationInfoModelList.clear();
        tempLocationInfoModelList.add(fromItemLocationData);

        plusMinusToggleContraint = findViewById(R.id.stock_mutation_plusminus_toggle);
        plusMinusToggle = findViewById(R.id.stock_mutation_plus_minus_toggle);
        amountInput = findViewById(R.id.stock_transfer_amount_textInputEditText);
        fromRecycler = findViewById(R.id.stock_mutation_from_recycler);
        toRecycler = findViewById(R.id.stock_mutation_to_recycler);
        statusSerialNumberRecycler = findViewById(R.id.order_pick_status_item_serial_number_recycler);
        amountOfSerialNumbersScanned = findViewById(R.id.order_pick_status_item_serial_number_title);
        mutationButton = findViewById(R.id.stock_mutation_confirm_button);
        reasonInput = findViewById(R.id.stock_mutation_reason_textInputEditText);
        loadingProgressBar = findViewById(R.id.loadingIcon);

        reasonInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
              @Override
              public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                  if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                          actionId == EditorInfo.IME_ACTION_DONE ||
                          actionId == EditorInfo.IME_ACTION_NEXT ||
                          actionId == EditorInfo.IME_ACTION_SEND ||
                          actionId == EditorInfo.IME_ACTION_GO ||
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

                viewFlipper = findViewById(R.id.stock_mutation_status_view_flipper);
        if(fromItemLocationData.getSerialnumbersRequired()){
            viewFlipper.setDisplayedChild(1);
        }else{
            viewFlipper.setDisplayedChild(0);
        }

        togglePlusMinus();
        initRecyclerViews();

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

        mutationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    //Todo: Verify please
                    if(!fromItemLocationData.getLocationTag().equals("") && fromItemLocationData.getLocationTag() != null && (!amountInput.getText().toString().equals("") || serialNumbersAdapter.getItemCount() > 0)){
                        double quantity = 0;
                        List<BatchNumberSelectionModelDto> serialNumbers = new ArrayList<>();
                        if(fromItemLocationData.getSerialnumbersRequired()){
                            quantity = serialNumbersAdapter.getItemCount();
                            if(plusMinusToggle.isChecked()){
                                quantity = quantity * -1;
                            }
                            for(int i = 0; i < fromItemLocationData.getScannedNumbers().size(); i++){
                                if(quantity > 0){
                                    if(!fromItemLocationData.getAllLocationAvailibleSerialnumbers().contains(fromItemLocationData.getScannedNumbers().get(i))){
                                        serialNumbers.add(new BatchNumberSelectionModelDto(fromItemLocationData.getScannedNumbers().get(i)));
                                    }
                                }else{
                                    if(fromItemLocationData.getAvailibleNumbers().contains(fromItemLocationData.getScannedNumbers().get(i))){
                                        serialNumbers.add(new BatchNumberSelectionModelDto(fromItemLocationData.getScannedNumbers().get(i)));
                                    }
                                }

                            }
                        }else{
                            try{
                                quantity = Double.parseDouble(amountInput.getText().toString());
                            }catch (Exception e){
                                Log.i(TAG, "Unable to parse input of quantity field.");
                            }
                        }
                        if((serialNumbers.size() > 0 && quantity != 0 || !fromItemLocationData.getSerialnumbersRequired()) && Math.abs(fromItemLocationData.getProductStock()) + quantity >= 0){
                            presenter.completeStockMutation(new StockMutationDto(fromItemLocationData.getProductScan(), fromItemLocationData.getLocationTag(), quantity, reasonInput.getText().toString(), serialNumbers));
                        }else{
                            if(quantity > 0 && fromItemLocationData.getSerialnumbersRequired()){
                                Toast.makeText(getApplicationContext(), "Niet genoeg of ongeldige serienummers ingevoerd. Let op dat je niet al geregistreerde serienummers inscant.", Toast.LENGTH_LONG).show();
                            }else if(quantity < 0 && fromItemLocationData.getSerialnumbersRequired()){
                                Toast.makeText(getApplicationContext(), "Niet genoeg of ongeldige serienummers ingevoerd. Let op dat je alleen al geregistreerde serienummers inscant.", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(getApplicationContext(), "Foutief aantal producten opgegeven.", Toast.LENGTH_SHORT).show();
                            }

                        }


                    }
                }catch (Exception e){
                    Log.i(TAG, "Unable to parse data required for stock transfer.");
                    e.printStackTrace();
                }
            }
        });
    }

    private void togglePlusMinus(){
        for(int i = 0; i < tempLocationInfoModelList.size(); i++){
            if(tempLocationInfoModelList.get(i).getSerialnumbersRequired()){
                plusMinusToggleContraint.setVisibility(View.VISIBLE);
                break;
            }else{
                plusMinusToggleContraint.setVisibility(View.GONE);
            }
        }
    }

    public void initRecyclerViews(){
        //Location adapter
        locationAdapter = new StockMutationMainLocationAdapter(tempLocationInfoModelList);
        fromRecycler.setAdapter(locationAdapter);
        fromRecycler.setLayoutManager(new LinearLayoutManager(this));

        //Product adapter:
        productAdapter = new StockMutationMainProductAdapter(tempLocationInfoModelList);
        toRecycler.setAdapter(productAdapter);
        toRecycler.setLayoutManager(new LinearLayoutManager(this));

        //Serialnumbers:
        serialNumbersAdapter = new StockMutationMainStatusSerialNumbersAdapter(new ArrayList<>());
        serialNumbersAdapter.setPresenter(presenter);
        statusSerialNumberRecycler.setAdapter(serialNumbersAdapter);
        statusSerialNumberRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    private void hideKeyboard(){
        KeyboardHider.hideKeyboard(this);
    }

    @Override
    public void clearFocus() {
        reasonInput.setFocusableInTouchMode(false);
        reasonInput.setFocusable(false);
        viewFlipper.clearFocus();
        reasonInput.setFocusableInTouchMode(true);
        reasonInput.setFocusable(true);
    }

    @Override
    public void onBarcodeScanned(String scan){
        if(fromItemLocationData.getSerialnumbersRequired()){
            //Todo: See if serial number is valid.
            strings = scan.replaceAll("\\s+","").split(";");
            for(int i = 0; i < strings.length; i++){
                if(!fromItemLocationData.getScannedNumbers().contains(strings[i]) && fromItemLocationData.getAvailibleNumbers().contains(strings[i])){
                    fromItemLocationData.getScannedNumbers().add(strings[i]);
                }
            }
            refreshSerialNumberData();
            updateSerialAmountText();
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

    @Override
    public void updateSerialAmountText(){
        amountOfSerialNumbersScanned.setText(String.valueOf(getString(R.string.Order_pick_serial_number_hint) + " " + fromItemLocationData.getScannedNumbers().size()));
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
