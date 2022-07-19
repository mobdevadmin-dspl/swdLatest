package com.datamation.swdsfa.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.datamation.swdsfa.helpers.DatabaseHelper;
import com.datamation.swdsfa.model.Bank;
import com.datamation.swdsfa.model.DiscValHed;

import java.util.ArrayList;

public class DiscValHedController {

    Context context;
    private SQLiteDatabase dB;
    private DatabaseHelper dbHelper;
    private String TAG = "DiscValHedController ";

    // table
    public static final String TABLE_FDISCVALHED = "fDisValHed";
    // table attributes
    public static final String FDISCVALHED_ID = "DiscValHed_id";
    public static final String FDISCVALHED_DISC_DESC= "DiscDesc";
    public static final String FDISCVALHED_DISC_TYPE = "DiscType";
    public static final String FDISCVALHED_PAY_TYPE = "PayType";
    public static final String FDISCVALHED_PRIORITY = "Priority";
    public static final String FDISCVALHED_REFNO = "RefNo";
    public static final String FDISCVALHED_REMARKS = "Remarks";
    public static final String FDISCVALHED_TXNDATE = "TxnDate";
    public static final String FDISCVALHED_VDATEF = "Vdatef";
    public static final String FDISCVALHED_VDATET = "Vdatet";


    // create String
    public static final String CREATE_FDISCVALHED_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FDISCVALHED + " (" + FDISCVALHED_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + FDISCVALHED_DISC_DESC + " TEXT, " + FDISCVALHED_DISC_TYPE + " TEXT, " + FDISCVALHED_PAY_TYPE + " TEXT, " +
            FDISCVALHED_PRIORITY + " TEXT, " + FDISCVALHED_REFNO + " TEXT, " + FDISCVALHED_REMARKS + " TEXT, " + FDISCVALHED_TXNDATE + " TEXT, " +
            FDISCVALHED_VDATEF + " TEXT, " + FDISCVALHED_VDATET + " TEXT); ";

  //  public static final String TESTBANK = "CREATE UNIQUE INDEX IF NOT EXISTS idxbank_something ON " + TABLE_FBANK + " (" + FBANK_BANK_CODE + ")";

    public DiscValHedController(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        dB = dbHelper.getWritableDatabase();
    }

    public int createOrUpdateDiscValHed(ArrayList<DiscValHed> list) {

        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }

        try {

            for (DiscValHed discValHed : list) {

                Cursor cursor = dB.rawQuery("SELECT * FROM " + TABLE_FDISCVALHED + " WHERE " + FDISCVALHED_REFNO + "='" + discValHed.getFDISCVALHED_REFNO() + "'", null);

                ContentValues values = new ContentValues();

                values.put(FDISCVALHED_DISC_DESC, discValHed.getFDISCVALHED_DISC_DESC());
                values.put(FDISCVALHED_DISC_TYPE, discValHed.getFDISCVALHED_DISC_TYPE());
                values.put(FDISCVALHED_PAY_TYPE, discValHed.getFDISCVALHED_PAY_TYPE());
                values.put(FDISCVALHED_PRIORITY, discValHed.getFDISCVALHED_PRIORITY());
                values.put(FDISCVALHED_REFNO, discValHed.getFDISCVALHED_REFNO());
                values.put(FDISCVALHED_REMARKS, discValHed.getFDISCVALHED_REMARKS());
                values.put(FDISCVALHED_TXNDATE, discValHed.getFDISCVALHED_TXNDATE());
                values.put(FDISCVALHED_VDATEF, discValHed.getFDISCVALHED_VDATEF());
                values.put(FDISCVALHED_VDATET, discValHed.getFDISCVALHED_VDATET());

                if (cursor.getCount() > 0) {
                    dB.update(TABLE_FDISCVALHED, values, FDISCVALHED_REFNO + "=?", new String[]{discValHed.getFDISCVALHED_REFNO().toString()});
                    Log.v(TAG, "Updated");
                } else {
                    count = (int) dB.insert(TABLE_FDISCVALHED, null, values);
                    Log.v(TAG, "Inserted " + count);
                }
                cursor.close();
            }

        } catch (Exception e) {
            Log.v(TAG, e.toString());
        } finally {
            dB.close();
        }
        return count;

    }

	/*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

    public int deleteAll() {

        int count = 0;

        if (dB == null) {
            open();
        } else if (!dB.isOpen()) {
            open();
        }
        Cursor cursor = null;
        try {

            cursor = dB.rawQuery("SELECT * FROM " + TABLE_FDISCVALHED, null);
            count = cursor.getCount();
            if (count > 0) {
                int success = dB.delete(TABLE_FDISCVALHED, null, null);
                Log.v("Success", success + "");
            }
        } catch (Exception e) {

            Log.v(TAG + " Exception", e.toString());

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dB.close();
        }

        return count;

    }

}
