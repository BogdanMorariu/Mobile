package com.pf.bogdan.pharmacy.model;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import java.lang.Double;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated("android.arch.persistence.room.RoomProcessor")
public class MedicineDAO_Impl implements MedicineDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfMedicine;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfMedicine;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfMedicine;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public MedicineDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMedicine = new EntityInsertionAdapter<Medicine>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `medicine`(`id`,`name`,`description`,`producer`,`price`,`history`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Medicine value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        if (value.getProducer() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getProducer());
        }
        if (value.getPrice() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindDouble(5, value.getPrice());
        }
        if (value.getHistory() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getHistory());
        }
      }
    };
    this.__deletionAdapterOfMedicine = new EntityDeletionOrUpdateAdapter<Medicine>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `medicine` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Medicine value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
      }
    };
    this.__updateAdapterOfMedicine = new EntityDeletionOrUpdateAdapter<Medicine>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `medicine` SET `id` = ?,`name` = ?,`description` = ?,`producer` = ?,`price` = ?,`history` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Medicine value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        if (value.getProducer() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getProducer());
        }
        if (value.getPrice() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindDouble(5, value.getPrice());
        }
        if (value.getHistory() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getHistory());
        }
        if (value.getId() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindLong(7, value.getId());
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "Delete From medicine";
        return _query;
      }
    };
  }

  @Override
  public void insert(Medicine medicine) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfMedicine.insert(medicine);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(Medicine medicine) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfMedicine.handle(medicine);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(Medicine medicine) {
    __db.beginTransaction();
    try {
      __updateAdapterOfMedicine.handle(medicine);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public List<Medicine> getAll() {
    final String _sql = "Select * from medicine";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
      final int _cursorIndexOfProducer = _cursor.getColumnIndexOrThrow("producer");
      final int _cursorIndexOfPrice = _cursor.getColumnIndexOrThrow("price");
      final int _cursorIndexOfHistory = _cursor.getColumnIndexOrThrow("history");
      final List<Medicine> _result = new ArrayList<Medicine>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Medicine _item;
        _item = new Medicine();
        final Integer _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getInt(_cursorIndexOfId);
        }
        _item.setId(_tmpId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        _item.setDescription(_tmpDescription);
        final String _tmpProducer;
        _tmpProducer = _cursor.getString(_cursorIndexOfProducer);
        _item.setProducer(_tmpProducer);
        final Double _tmpPrice;
        if (_cursor.isNull(_cursorIndexOfPrice)) {
          _tmpPrice = null;
        } else {
          _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
        }
        _item.setPrice(_tmpPrice);
        final String _tmpHistory;
        _tmpHistory = _cursor.getString(_cursorIndexOfHistory);
        _item.setHistory(_tmpHistory);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int count() {
    final String _sql = "Select Count(*) from medicine";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Medicine findById(int id) {
    final String _sql = "Select * From medicine where id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
      final int _cursorIndexOfProducer = _cursor.getColumnIndexOrThrow("producer");
      final int _cursorIndexOfPrice = _cursor.getColumnIndexOrThrow("price");
      final int _cursorIndexOfHistory = _cursor.getColumnIndexOrThrow("history");
      final Medicine _result;
      if(_cursor.moveToFirst()) {
        _result = new Medicine();
        final Integer _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getInt(_cursorIndexOfId);
        }
        _result.setId(_tmpId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _result.setName(_tmpName);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        _result.setDescription(_tmpDescription);
        final String _tmpProducer;
        _tmpProducer = _cursor.getString(_cursorIndexOfProducer);
        _result.setProducer(_tmpProducer);
        final Double _tmpPrice;
        if (_cursor.isNull(_cursorIndexOfPrice)) {
          _tmpPrice = null;
        } else {
          _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
        }
        _result.setPrice(_tmpPrice);
        final String _tmpHistory;
        _tmpHistory = _cursor.getString(_cursorIndexOfHistory);
        _result.setHistory(_tmpHistory);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
