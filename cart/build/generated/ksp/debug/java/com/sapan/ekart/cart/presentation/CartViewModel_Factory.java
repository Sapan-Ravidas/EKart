package com.sapan.ekart.cart.presentation;

import com.sapan.ekart.core.analytics.domain.usecase.LogInteractionUseCase;
import com.sapan.ekart.core.cart.domain.repository.CartRepository;
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
public final class CartViewModel_Factory implements Factory<CartViewModel> {
  private final Provider<CartRepository> repositoryProvider;

  private final Provider<LogInteractionUseCase> logInteractionUseCaseProvider;

  public CartViewModel_Factory(Provider<CartRepository> repositoryProvider,
      Provider<LogInteractionUseCase> logInteractionUseCaseProvider) {
    this.repositoryProvider = repositoryProvider;
    this.logInteractionUseCaseProvider = logInteractionUseCaseProvider;
  }

  @Override
  public CartViewModel get() {
    return newInstance(repositoryProvider.get(), logInteractionUseCaseProvider.get());
  }

  public static CartViewModel_Factory create(Provider<CartRepository> repositoryProvider,
      Provider<LogInteractionUseCase> logInteractionUseCaseProvider) {
    return new CartViewModel_Factory(repositoryProvider, logInteractionUseCaseProvider);
  }

  public static CartViewModel newInstance(CartRepository repository,
      LogInteractionUseCase logInteractionUseCase) {
    return new CartViewModel(repository, logInteractionUseCase);
  }
}
