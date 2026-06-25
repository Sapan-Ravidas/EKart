package com.sapan.ekart.flashdeal.domain.usecase;

import com.sapan.ekart.flashdeal.domain.repository.FlashDealRepository;
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
public final class GetActiveFlashDealsUseCase_Factory implements Factory<GetActiveFlashDealsUseCase> {
  private final Provider<FlashDealRepository> repositoryProvider;

  public GetActiveFlashDealsUseCase_Factory(Provider<FlashDealRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetActiveFlashDealsUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetActiveFlashDealsUseCase_Factory create(
      Provider<FlashDealRepository> repositoryProvider) {
    return new GetActiveFlashDealsUseCase_Factory(repositoryProvider);
  }

  public static GetActiveFlashDealsUseCase newInstance(FlashDealRepository repository) {
    return new GetActiveFlashDealsUseCase(repository);
  }
}
