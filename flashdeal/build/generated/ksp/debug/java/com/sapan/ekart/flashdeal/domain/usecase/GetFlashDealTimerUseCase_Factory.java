package com.sapan.ekart.flashdeal.domain.usecase;

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
public final class GetFlashDealTimerUseCase_Factory implements Factory<GetFlashDealTimerUseCase> {
  @Override
  public GetFlashDealTimerUseCase get() {
    return newInstance();
  }

  public static GetFlashDealTimerUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static GetFlashDealTimerUseCase newInstance() {
    return new GetFlashDealTimerUseCase();
  }

  private static final class InstanceHolder {
    private static final GetFlashDealTimerUseCase_Factory INSTANCE = new GetFlashDealTimerUseCase_Factory();
  }
}
