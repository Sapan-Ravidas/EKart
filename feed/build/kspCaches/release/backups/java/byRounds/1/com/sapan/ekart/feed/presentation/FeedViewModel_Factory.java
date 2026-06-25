package com.sapan.ekart.feed.presentation;

import com.sapan.ekart.core.analytics.domain.usecase.LogInteractionUseCase;
import com.sapan.ekart.core.cart.domain.repository.CartRepository;
import com.sapan.ekart.feed.domain.repository.ProductRepository;
import com.sapan.ekart.feed.domain.usecase.GetPagedProductsUseCase;
import com.sapan.ekart.feed.domain.usecase.RefreshProductsUseCase;
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
public final class FeedViewModel_Factory implements Factory<FeedViewModel> {
  private final Provider<ProductRepository> repositoryProvider;

  private final Provider<GetPagedProductsUseCase> getPagedProductsUseCaseProvider;

  private final Provider<RefreshProductsUseCase> refreshProductsUseCaseProvider;

  private final Provider<LogInteractionUseCase> logInteractionUseCaseProvider;

  private final Provider<CartRepository> cartRepositoryProvider;

  public FeedViewModel_Factory(Provider<ProductRepository> repositoryProvider,
      Provider<GetPagedProductsUseCase> getPagedProductsUseCaseProvider,
      Provider<RefreshProductsUseCase> refreshProductsUseCaseProvider,
      Provider<LogInteractionUseCase> logInteractionUseCaseProvider,
      Provider<CartRepository> cartRepositoryProvider) {
    this.repositoryProvider = repositoryProvider;
    this.getPagedProductsUseCaseProvider = getPagedProductsUseCaseProvider;
    this.refreshProductsUseCaseProvider = refreshProductsUseCaseProvider;
    this.logInteractionUseCaseProvider = logInteractionUseCaseProvider;
    this.cartRepositoryProvider = cartRepositoryProvider;
  }

  @Override
  public FeedViewModel get() {
    return newInstance(repositoryProvider.get(), getPagedProductsUseCaseProvider.get(), refreshProductsUseCaseProvider.get(), logInteractionUseCaseProvider.get(), cartRepositoryProvider.get());
  }

  public static FeedViewModel_Factory create(Provider<ProductRepository> repositoryProvider,
      Provider<GetPagedProductsUseCase> getPagedProductsUseCaseProvider,
      Provider<RefreshProductsUseCase> refreshProductsUseCaseProvider,
      Provider<LogInteractionUseCase> logInteractionUseCaseProvider,
      Provider<CartRepository> cartRepositoryProvider) {
    return new FeedViewModel_Factory(repositoryProvider, getPagedProductsUseCaseProvider, refreshProductsUseCaseProvider, logInteractionUseCaseProvider, cartRepositoryProvider);
  }

  public static FeedViewModel newInstance(ProductRepository repository,
      GetPagedProductsUseCase getPagedProductsUseCase,
      RefreshProductsUseCase refreshProductsUseCase, LogInteractionUseCase logInteractionUseCase,
      CartRepository cartRepository) {
    return new FeedViewModel(repository, getPagedProductsUseCase, refreshProductsUseCase, logInteractionUseCase, cartRepository);
  }
}
