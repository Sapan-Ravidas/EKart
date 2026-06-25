package com.sapan.ekart.feed.data.local;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.paging.PagingSource;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.paging.LimitOffsetPagingSource;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.sapan.ekart.feed.data.local.entity.ProductEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ProductDao_Impl implements ProductDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ProductEntity> __insertionAdapterOfProductEntity;

  private final SharedSQLiteStatement __preparedStmtOfClearProducts;

  public ProductDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfProductEntity = new EntityInsertionAdapter<ProductEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `products` (`id`,`title`,`price`,`description`,`images`,`inventoryCount`,`categoryName`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ProductEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        statement.bindDouble(3, entity.getPrice());
        statement.bindString(4, entity.getDescription());
        statement.bindString(5, entity.getImages());
        statement.bindLong(6, entity.getInventoryCount());
        statement.bindString(7, entity.getCategoryName());
      }
    };
    this.__preparedStmtOfClearProducts = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM products";
        return _query;
      }
    };
  }

  @Override
  public Object insertProducts(final List<ProductEntity> products,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfProductEntity.insert(products);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object clearProducts(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearProducts.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClearProducts.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ProductEntity>> getAllProducts() {
    final String _sql = "SELECT * FROM products";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"products"}, new Callable<List<ProductEntity>>() {
      @Override
      @NonNull
      public List<ProductEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfImages = CursorUtil.getColumnIndexOrThrow(_cursor, "images");
          final int _cursorIndexOfInventoryCount = CursorUtil.getColumnIndexOrThrow(_cursor, "inventoryCount");
          final int _cursorIndexOfCategoryName = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryName");
          final List<ProductEntity> _result = new ArrayList<ProductEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ProductEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpImages;
            _tmpImages = _cursor.getString(_cursorIndexOfImages);
            final int _tmpInventoryCount;
            _tmpInventoryCount = _cursor.getInt(_cursorIndexOfInventoryCount);
            final String _tmpCategoryName;
            _tmpCategoryName = _cursor.getString(_cursorIndexOfCategoryName);
            _item = new ProductEntity(_tmpId,_tmpTitle,_tmpPrice,_tmpDescription,_tmpImages,_tmpInventoryCount,_tmpCategoryName);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public PagingSource<Integer, ProductEntity> getProductsPagingSource() {
    final String _sql = "SELECT * FROM products";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new LimitOffsetPagingSource<ProductEntity>(_statement, __db, "products") {
      @Override
      @NonNull
      protected List<ProductEntity> convertRows(@NonNull final Cursor cursor) {
        final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(cursor, "id");
        final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(cursor, "title");
        final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(cursor, "price");
        final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(cursor, "description");
        final int _cursorIndexOfImages = CursorUtil.getColumnIndexOrThrow(cursor, "images");
        final int _cursorIndexOfInventoryCount = CursorUtil.getColumnIndexOrThrow(cursor, "inventoryCount");
        final int _cursorIndexOfCategoryName = CursorUtil.getColumnIndexOrThrow(cursor, "categoryName");
        final List<ProductEntity> _result = new ArrayList<ProductEntity>(cursor.getCount());
        while (cursor.moveToNext()) {
          final ProductEntity _item;
          final int _tmpId;
          _tmpId = cursor.getInt(_cursorIndexOfId);
          final String _tmpTitle;
          _tmpTitle = cursor.getString(_cursorIndexOfTitle);
          final double _tmpPrice;
          _tmpPrice = cursor.getDouble(_cursorIndexOfPrice);
          final String _tmpDescription;
          _tmpDescription = cursor.getString(_cursorIndexOfDescription);
          final String _tmpImages;
          _tmpImages = cursor.getString(_cursorIndexOfImages);
          final int _tmpInventoryCount;
          _tmpInventoryCount = cursor.getInt(_cursorIndexOfInventoryCount);
          final String _tmpCategoryName;
          _tmpCategoryName = cursor.getString(_cursorIndexOfCategoryName);
          _item = new ProductEntity(_tmpId,_tmpTitle,_tmpPrice,_tmpDescription,_tmpImages,_tmpInventoryCount,_tmpCategoryName);
          _result.add(_item);
        }
        return _result;
      }
    };
  }

  @Override
  public PagingSource<Integer, ProductEntity> getProductsByCategoryPagingSource(
      final String categoryName) {
    final String _sql = "SELECT * FROM products WHERE categoryName = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, categoryName);
    return new LimitOffsetPagingSource<ProductEntity>(_statement, __db, "products") {
      @Override
      @NonNull
      protected List<ProductEntity> convertRows(@NonNull final Cursor cursor) {
        final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(cursor, "id");
        final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(cursor, "title");
        final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(cursor, "price");
        final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(cursor, "description");
        final int _cursorIndexOfImages = CursorUtil.getColumnIndexOrThrow(cursor, "images");
        final int _cursorIndexOfInventoryCount = CursorUtil.getColumnIndexOrThrow(cursor, "inventoryCount");
        final int _cursorIndexOfCategoryName = CursorUtil.getColumnIndexOrThrow(cursor, "categoryName");
        final List<ProductEntity> _result = new ArrayList<ProductEntity>(cursor.getCount());
        while (cursor.moveToNext()) {
          final ProductEntity _item;
          final int _tmpId;
          _tmpId = cursor.getInt(_cursorIndexOfId);
          final String _tmpTitle;
          _tmpTitle = cursor.getString(_cursorIndexOfTitle);
          final double _tmpPrice;
          _tmpPrice = cursor.getDouble(_cursorIndexOfPrice);
          final String _tmpDescription;
          _tmpDescription = cursor.getString(_cursorIndexOfDescription);
          final String _tmpImages;
          _tmpImages = cursor.getString(_cursorIndexOfImages);
          final int _tmpInventoryCount;
          _tmpInventoryCount = cursor.getInt(_cursorIndexOfInventoryCount);
          final String _tmpCategoryName;
          _tmpCategoryName = cursor.getString(_cursorIndexOfCategoryName);
          _item = new ProductEntity(_tmpId,_tmpTitle,_tmpPrice,_tmpDescription,_tmpImages,_tmpInventoryCount,_tmpCategoryName);
          _result.add(_item);
        }
        return _result;
      }
    };
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
