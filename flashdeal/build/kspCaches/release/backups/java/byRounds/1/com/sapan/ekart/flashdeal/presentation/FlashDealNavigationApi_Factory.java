package com.sapan.ekart.flashdeal.presentation;

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
public final class FlashDealNavigationApi_Factory implements Factory<FlashDealNavigationApi> {
  @Override
  public FlashDealNavigationApi get() {
    return newInstance();
  }

  public static FlashDealNavigationApi_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FlashDealNavigationApi newInstance() {
    return new FlashDealNavigationApi();
  }

  private static final class InstanceHolder {
    private static final FlashDealNavigationApi_Factory INSTANCE = new FlashDealNavigationApi_Factory();
  }
}
