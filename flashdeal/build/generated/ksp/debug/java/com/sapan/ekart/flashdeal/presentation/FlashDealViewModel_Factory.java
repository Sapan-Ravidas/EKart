package com.sapan.ekart.flashdeal.presentation;

import com.sapan.ekart.core.analytics.domain.usecase.LogInteractionUseCase;
import com.sapan.ekart.core.cart.domain.repository.CartRepository;
import com.sapan.ekart.flashdeal.domain.usecase.GetActiveFlashDealsUseCase;
import com.sapan.ekart.flashdeal.domain.usecase.GetFlashDealTimerUseCase;
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
public final class FlashDealViewModel_Factory implements Factory<FlashDealViewModel> {
  private final Provider<GetActiveFlashDealsUseCase> getActiveFlashDealsUseCaseProvider;

  private final Provider<GetFlashDealTimerUseCase> timerUseCaseProvider;

  private final Provider<LogInteractionUseCase> logInteractionUseCaseProvider;

  private final Provider<CartRepository> cartRepositoryProvider;

  public FlashDealViewModel_Factory(
      Provider<GetActiveFlashDealsUseCase> getActiveFlashDealsUseCaseProvider,
      Provider<GetFlashDealTimerUseCase> timerUseCaseProvider,
      Provider<LogInteractionUseCase> logInteractionUseCaseProvider,
      Provider<CartRepository> cartRepositoryProvider) {
    this.getActiveFlashDealsUseCaseProvider = getActiveFlashDealsUseCaseProvider;
    this.timerUseCaseProvider = timerUseCaseProvider;
    this.logInteractionUseCaseProvider = logInteractionUseCaseProvider;
    this.cartRepositoryProvider = cartRepositoryProvider;
  }

  @Override
  public FlashDealViewModel get() {
    return newInstance(getActiveFlashDealsUseCaseProvider.get(), timerUseCaseProvider.get(), logInteractionUseCaseProvider.get(), cartRepositoryProvider.get());
  }

  public static FlashDealViewModel_Factory create(
      Provider<GetActiveFlashDealsUseCase> getActiveFlashDealsUseCaseProvider,
      Provider<GetFlashDealTimerUseCase> timerUseCaseProvider,
      Provider<LogInteractionUseCase> logInteractionUseCaseProvider,
      Provider<CartRepository> cartRepositoryProvider) {
    return new FlashDealViewModel_Factory(getActiveFlashDealsUseCaseProvider, timerUseCaseProvider, logInteractionUseCaseProvider, cartRepositoryProvider);
  }

  public static FlashDealViewModel newInstance(
      GetActiveFlashDealsUseCase getActiveFlashDealsUseCase, GetFlashDealTimerUseCase timerUseCase,
      LogInteractionUseCase logInteractionUseCase, CartRepository cartRepository) {
    return new FlashDealViewModel(getActiveFlashDealsUseCase, timerUseCase, logInteractionUseCase, cartRepository);
  }
}
