package com.pf.bogdan.pharmacy;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import com.pf.bogdan.pharmacy.model.MedicineDAO;
import com.pf.bogdan.pharmacy.model.MedicineDAO_Impl;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.util.HashMap;
import java.util.HashSet;
import javax.annotation.Generated;

@Generated("android.arch.persistence.room.RoomProcessor")
public class DataBaseConnection_Impl extends DataBaseConnection {
  private volatile MedicineDAO _medicineDAO;

  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `medicine` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `name` TEXT, `description` TEXT, `producer` TEXT, `price` REAL, `history` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"6c5a1d104c2261bd0d47c9dcd5fd2556\")");
      }

      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `medicine`");
      }

      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsMedicine = new HashMap<String, TableInfo.Column>(6);
        _columnsMedicine.put("id", new TableInfo.Column("id", "INTEGER", false, 1));
        _columnsMedicine.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsMedicine.put("description", new TableInfo.Column("description", "TEXT", false, 0));
        _columnsMedicine.put("producer", new TableInfo.Column("producer", "TEXT", false, 0));
        _columnsMedicine.put("price", new TableInfo.Column("price", "REAL", false, 0));
        _columnsMedicine.put("history", new TableInfo.Column("history", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMedicine = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMedicine = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMedicine = new TableInfo("medicine", _columnsMedicine, _foreignKeysMedicine, _indicesMedicine);
        final TableInfo _existingMedicine = TableInfo.read(_db, "medicine");
        if (! _infoMedicine.equals(_existingMedicine)) {
          throw new IllegalStateException("Migration didn't properly handle medicine(com.pf.bogdan.pharmacy.model.Medicine).\n"
                  + " Expected:\n" + _infoMedicine + "\n"
                  + " Found:\n" + _existingMedicine);
        }
      }
    }, "6c5a1d104c2261bd0d47c9dcd5fd2556");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "medicine");
  }

  @Override
  public MedicineDAO medicineDAO() {
    if (_medicineDAO != null) {
      return _medicineDAO;
    } else {
      synchronized(this) {
        if(_medicineDAO == null) {
          _medicineDAO = new MedicineDAO_Impl(this);
        }
        return _medicineDAO;
      }
    }
  }
}
