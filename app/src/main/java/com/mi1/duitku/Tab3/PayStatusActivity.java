package com.mi1.duitku.Tab3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mi1.duitku.BaseActivity;
import com.mi1.duitku.R;
import com.mi1.duitku.Tab3.Common.Inquiry;
import com.mi1.duitku.Tab3.Common.InquiryResponse;
import com.mi1.duitku.Tab3.Common.Login;
import com.mi1.duitku.Tab3.Common.LoginResponse;
import com.mi1.duitku.Tab3.Common.Pay;
import com.mi1.duitku.Tab3.Common.PayResponse;


/**
 * Created by latifalbar on 11/19/2015.
 */
public class PayStatusActivity extends BaseActivity {

    private TextView txtTransactionId;
    private TextView txtJumlah;
    private LinearLayout linearLayoutTransactionDetail;
    private LinearLayout linearLayoutMisc;
    private LoginResponse loginResponse;
    private InquiryResponse inquiryResponse;
    private Inquiry mInquiry;
    private Login login;
    private Pay pay;
    private TextView txtPaymentStatus;
    private TextView txtPaymentDescription;
    private Button mBtnBack;
    private PayResponse mPayResponse = null;
    private String paymentStatus,reference,amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_status);

        initView();

        paymentStatus = getIntent().getStringExtra("status");
        reference =  getIntent().getStringExtra("reference");
        amount = getIntent().getStringExtra("amount");
        updateDisplayInformation(paymentStatus,reference);
        //region OldCode
        //        mInquiry = new Gson().fromJson(duitkuPreferences.getInquiry(), Inquiry.class);

//        preparePreferences();
//        if (duitkuPreferences.isUsingDuitku() && (getIntent().getBooleanExtra(constant.isSufficientBalance,false))){
//            showSimpleProgressDialog(null);
//            preparePayObject();
//            mPayPresenter.onProsesBayar(pay, true);
//        }else if (duitkuPreferences.isUsingDuitku() && !(getIntent()
//                .getBooleanExtra(constant.isSufficientBalance,false))){
//                showInfoPayment(getIntent().getBooleanExtra(constant.isSufficientBalance,false));
//        }else if (!duitkuPreferences.isUsingDuitku() && getIntent().getBooleanExtra(constant.successwebpayment,false)){
//            showInforPaymentFromRedirect();
//        }
        //endregion

    }

    public void initView() {
        linearLayoutMisc = (LinearLayout) findViewById(R.id.linearLayoutMisc);
        linearLayoutTransactionDetail = (LinearLayout) findViewById(R.id.linearLayoutTransactionDetail);
        txtTransactionId = (TextView) findViewById(R.id.transIdValue);
        txtJumlah = (TextView) findViewById(R.id.transValue);
        txtPaymentStatus = (TextView) findViewById(R.id.txt_payment_status);
        txtPaymentDescription = (TextView) findViewById(R.id.txt_payment_description);
        linearLayoutMisc.setVisibility(View.INVISIBLE);
        mBtnBack = (Button) findViewById(R.id.btn_back);
//        mBtnBack.setOnClickListener(this);
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paymentStatus != null && reference != null)
                {
                    if(paymentStatus.replace("\"","").equals("Success"))
                    {
                        linearLayoutTransactionDetail.setVisibility(View.GONE);
                        linearLayoutMisc.setVisibility(View.VISIBLE);
                        mBtnBack.setVisibility(View.INVISIBLE);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 3000);
//                        break;
                    }
                    else
                    {
                        Intent intentChooseMethodAgain = new Intent(PayStatusActivity.this,PaymentMethodActivity.class);
                        startActivity(intentChooseMethodAgain);
//                        break;
                    }
                }
            }
        });
    }

    private void updateDisplayInformation(String paymentStatus, String reference)
    {
        if(paymentStatus.equals("Insufficient fund"))
        {
            txtPaymentStatus.setText(getString(R.string.payment_failed));
            txtPaymentDescription.setText(getString(R.string.insufficient_balance));
            txtPaymentDescription.setVisibility(View.VISIBLE);
            txtTransactionId.setText(reference);
            txtJumlah.setText("Rp " + amount);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 5000);
        }
        if(paymentStatus.replace("\"","").equals("Success"))
        {
            txtPaymentStatus.setText(getString(R.string.payment_succeed));
            txtPaymentDescription.setText(getString(R.string.thank_you));
            txtPaymentDescription.setVisibility(View.VISIBLE);
            txtTransactionId.setText(reference);
            txtJumlah.setText("Rp " + amount);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 3000);
        }
        else
        {
            try
            {
//                JSONObject data = new JSONObject(paymentStatus.toString());
//                String status = "Failed";//data.getString("Message");
                txtPaymentStatus.setText(getString(R.string.payment_failed));
                txtPaymentDescription.setText("Harap hubungi Customer Service");
                txtPaymentDescription.setVisibility(View.VISIBLE);
                txtTransactionId.setText(reference);
                txtJumlah.setText("Rp " + amount);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 5000);
            }
            catch (Exception e)
            {
                Log.e("Error","Response " + e);
            }
        }
    }
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btn_back:
//                if(paymentStatus != null && reference != null)
//                {
//                    if(paymentStatus.replace("\"","").equals("Success"))
//                    {
//                        linearLayoutTransactionDetail.setVisibility(View.GONE);
//                        linearLayoutMisc.setVisibility(View.VISIBLE);
//                        mBtnBack.setVisibility(View.INVISIBLE);
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                        /* Create an Intent that will start the Menu-Activity. */
//                                startActivity(new Intent(PayStatusActivity.this, InquiryActivity.class));
//                            }
//                        }, 3000);
//                        break;
//                    }
//                    else
//                    {
//                        Intent intentChooseMethodAgain = new Intent(PayStatusActivity.this,PaymentMethodActivity.class);
//                        startActivity(intentChooseMethodAgain);
//                        break;
//                    }
//                }
////                if (mPayResponse != null){
////                    if (mPayResponse.getStatusCode().equals("00")){
//////                        super.onBackPressed();
//////                        startActivity(new Intent(PayStatusActivity.this, InquiryActivity.class));
////                        linearLayoutTransactionDetail.setVisibility(View.GONE);
////                        linearLayoutMisc.setVisibility(View.VISIBLE);
////                        mBtnBack.setVisibility(View.INVISIBLE);
////                        new Handler().postDelayed(new Runnable() {
////                            @Override
////                            public void run() {
////                        /* Create an Intent that will start the Menu-Activity. */
////                                startActivity(new Intent(PayStatusActivity.this, InquiryActivity.class));
////                            }
////                        }, 3000);
////                        break;
////                    }else {
////                        Intent intentChooseMethodAgain = new Intent(PayStatusActivity.this,PaymentMethodActivity.class);
////                        duitkuPreferences.setIsLastPaymentFailed(true);
////                        startActivity(intentChooseMethodAgain);
////                        break;
////                    }
////
////                }else if(getIntent().getBooleanExtra(constant.successwebpayment,false)){
////                    linearLayoutTransactionDetail.setVisibility(View.GONE);
////                    linearLayoutMisc.setVisibility(View.VISIBLE);
////                    mBtnBack.setVisibility(View.INVISIBLE);
////                    new Handler().postDelayed(new Runnable() {
////                        @Override
////                        public void run() {
////                        /* Create an Intent that will start the Menu-Activity. */
////                            startActivity(new Intent(PayStatusActivity.this, InquiryActivity.class));
////                        }
////                    }, 3000);
////                    break;
////                }
////                else {
////                    Intent intentChooseMethodAgain = new Intent(PayStatusActivity.this,PaymentMethodActivity.class);
//////                    duitkuPreferences.setIsLastPaymentFailed(true);
////                    startActivity(intentChooseMethodAgain);
////                    break;
////                }
//
//
//        }
//    }

    //region OldCode
    private void preparePreferences() {
//        duitkuPreferences = new DuitkuPreferences(this);
//        loginResponse = new Gson().fromJson(duitkuPreferences.getLoginResponse(), LoginResponse.class);
//        login = new Gson().fromJson(duitkuPreferences.getLogin(), Login.class);
//        inquiryResponse = new Gson().fromJson(duitkuPreferences.getInquiryResponse(), InquiryResponse.class);

    }

    private void preparePayObject() {
//        pay = new Pay();
//        pay.setAction(constant.statusPay);
//        pay.setUserId(loginResponse.getUserId());
//        pay.setOrderId(inquiryResponse.getOrderId());
//        pay.setMerchantCode(login.getMerchantCode());
//        String concatSign = constant.statusPay + constant.apiKey
//                + loginResponse.getUserId() + inquiryResponse.getOrderId() + login.getMerchantCode();
//        try {
//            pay.setSign(Util.getSHA1(concatSign));
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
    }

    public void emitPayResponse(PayResponse payResponse) {
//        duitkuPreferences.savePayResponse(new Gson().toJson(payResponse));
//        if (payResponse != null) {
//            dismissSimpleProgressDialog();
//            mPayResponse = payResponse;
//            txtPaymentStatus.setText(getString(R.string.payment_succeed));
//            txtPaymentDescription.setText(getString(R.string.thank_you));
//            txtTransactionId.setText(payResponse.getTrxId());
//            txtJumlah.setText("Rp" + inquiryResponse.getPrice());
//        } else {
//            dismissSimpleProgressDialog();
//            duitkuPreferences.setIsLastPaymentFailed(true);
//            txtPaymentStatus.setText("Pembayaran Gagal");
//            txtPaymentStatus.setTextColor(getResources().getColor(R.color.font_red));
//            Toast.makeText(this, getString(R.string.null_data), Toast.LENGTH_SHORT).show();
//        }

    }

    private void showInforPaymentFromRedirect(){
//        txtPaymentStatus.setText(getString(R.string.payment_succeed));
//        txtPaymentDescription.setText(getString(R.string.thank_you));
//        txtTransactionId.setText(inquiryResponse.getTrxId());
//        txtJumlah.setText("Rp" + inquiryResponse.getPrice());
    }


    private void showInfoPayment(boolean isSufficientBalance){
//        if (isSufficientBalance) {
//            txtPaymentStatus.setText(getString(R.string.payment_succeed));
//            txtPaymentDescription.setText(getString(R.string.thank_you));
//            txtTransactionId.setText(mPayResponse.getTrxId());
//            txtJumlah.setText("Rp" + inquiryResponse.getPrice());
//        }else if (!isSufficientBalance){
//            txtPaymentStatus.setText(getString(R.string.payment_failed));
//            txtPaymentDescription.setText(getString(R.string.insufficient_balance));
//            txtPaymentDescription.setVisibility(View.VISIBLE);
//            txtTransactionId.setText(inquiryResponse.getTrxId());
//            txtJumlah.setText("Rp" + inquiryResponse.getPrice());
//            txtPaymentStatus.setTextColor(getResources().getColor(R.color.font_red));
//        }
//        else {
//            InquiryResponse inquiryResponse = new Gson()
//                    .fromJson(duitkuPreferences.getInquiryResponse(), InquiryResponse.class);
//            txtTransactionId.setText(inquiryResponse.getTrxId());
//            txtJumlah.setText("Rp" + inquiryResponse.getPrice());
//        }
    }


    public void emitResponseFromRedirect() {
//        InquiryResponse inquiryResponse = new Gson()
//                .fromJson(duitkuPreferences.getInquiryResponse(),InquiryResponse.class);
//        txtTransactionId.setText(inquiryResponse.getTrxId());
//        txtJumlah.setText("Rp" + inquiryResponse.getPrice());
    }
    //endregion


}
