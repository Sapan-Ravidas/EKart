package com.sapan.ekart.cart.presentation;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class CartNavigationApi_Factory implements Factory<CartNavigationApi> {
  @Override
  public CartNavigationApi get() {
    return newInstance();
  }

  public static CartNavigationApi_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CartNavigationApi newInstance() {
    return new CartNavigationApi();
  }

  private static final class InstanceHolder {
    private static final CartNavigationApi_Factory INSTANCE = new CartNavigationApi_Factory();
  }
}
