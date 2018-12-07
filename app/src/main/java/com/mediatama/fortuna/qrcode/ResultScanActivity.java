package com.mediatama.fortuna.qrcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mediatama.fortuna.qrcode.model.Tamu;
import com.mediatama.fortuna.qrcode.presenter.DatabaseAccess;

import java.util.List;

public class ResultScanActivity extends AppCompatActivity {

    private static final String TAG = ResultScanActivity.class.getSimpleName();
    private TextView txtName;
    private TextView txtAlamat;
    private TextView txtError;
    private TextView txtNoHp;
    private TextView txtStatusScan;
    private ProgressBar progressBar;
    private ScanViewResult scanViewResult;
    private LinearLayout linearStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_scan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtName = findViewById(R.id.name);
        txtAlamat = findViewById(R.id.alamat);
        txtNoHp = findViewById(R.id.nomor_hp);
        txtError = findViewById(R.id.txt_error);
        txtStatusScan = findViewById(R.id.status_scan);
        scanViewResult = findViewById(R.id.layout_ticket);
        progressBar = findViewById(R.id.progressBar);
        linearStatus = findViewById(R.id.linear_status);

        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        String barcode = getIntent().getStringExtra("code");

        Tamu tamuUndangan = databaseAccess.getUndangan(barcode);

        if (TextUtils.isEmpty(tamuUndangan.getNama())){
            scanViewResult.setVisibility(View.GONE);
            txtError.setVisibility(View.VISIBLE);
            txtError.setText("Anda Bukan Tamu Undangan");
        }else {
            txtName.setText(tamuUndangan.getNama());

            if (TextUtils.isEmpty(tamuUndangan.getAlamat())){
                txtAlamat.setText("-");
            }else {
                txtAlamat.setText(tamuUndangan.getAlamat());
            }

            if (TextUtils.isEmpty(tamuUndangan.getHp())){
                txtNoHp.setText("-");
            }else {
                txtNoHp.setText(tamuUndangan.getHp());
            }

            if(tamuUndangan.getStatus().equalsIgnoreCase("1")){
                linearStatus.setVisibility(View.VISIBLE);
                txtStatusScan.setText("Sudah Melakukan Proses Scan");
            }

            databaseAccess.update(barcode);

        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultScanActivity.this, ScanActivity.class));
                finish();
            }
        });
    }

}
