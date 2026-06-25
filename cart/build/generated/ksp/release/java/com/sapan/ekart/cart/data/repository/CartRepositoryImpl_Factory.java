package com.sapan.ekart.cart.data.repository;

import com.sapan.ekart.cart.data.local.CartDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class CartRepositoryImpl_Factory implements Factory<CartRepositoryImpl> {
  private final Provider<CartDao> daoProvider;

  public CartRepositoryImpl_Factory(Provider<CartDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public CartRepositoryImpl get() {
    return newInstance(daoProvider.get());
  }

  public static CartRepositoryImpl_Factory create(Provider<CartDao> daoProvider) {
    return new CartRepositoryImpl_Factory(daoProvider);
  }

  public static CartRepositoryImpl newInstance(CartDao dao) {
    return new CartRepositoryImpl(dao);
  }
}
