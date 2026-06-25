package com.sapan.ekart.cart.data.di;

import com.sapan.ekart.cart.data.local.CartDao;
import com.sapan.ekart.cart.data.local.CartDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class CartDataModule_ProvideCartDaoFactory implements Factory<CartDao> {
  private final Provider<CartDatabase> dbProvider;

  public CartDataModule_ProvideCartDaoFactory(Provider<CartDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public CartDao get() {
    return provideCartDao(dbProvider.get());
  }

  public static CartDataModule_ProvideCartDaoFactory create(Provider<CartDatabase> dbProvider) {
    return new CartDataModule_ProvideCartDaoFactory(dbProvider);
  }

  public static CartDao provideCartDao(CartDatabase db) {
    return Preconditions.checkNotNullFromProvides(CartDataModule.INSTANCE.provideCartDao(db));
  }
}
