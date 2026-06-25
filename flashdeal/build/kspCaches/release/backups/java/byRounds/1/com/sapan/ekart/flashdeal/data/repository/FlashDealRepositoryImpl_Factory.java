package com.sapan.ekart.flashdeal.data.repository;

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
public final class FlashDealRepositoryImpl_Factory implements Factory<FlashDealRepositoryImpl> {
  @Override
  public FlashDealRepositoryImpl get() {
    return newInstance();
  }

  public static FlashDealRepositoryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FlashDealRepositoryImpl newInstance() {
    return new FlashDealRepositoryImpl();
  }

  private static final class InstanceHolder {
    private static final FlashDealRepositoryImpl_Factory INSTANCE = new FlashDealRepositoryImpl_Factory();
  }
}
