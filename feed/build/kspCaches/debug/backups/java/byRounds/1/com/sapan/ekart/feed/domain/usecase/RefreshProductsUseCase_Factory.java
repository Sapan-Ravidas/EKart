package com.sapan.ekart.feed.domain.usecase;

import com.sapan.ekart.feed.domain.repository.ProductRepository;
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
public final class RefreshProductsUseCase_Factory implements Factory<RefreshProductsUseCase> {
  private final Provider<ProductRepository> repositoryProvider;

  public RefreshProductsUseCase_Factory(Provider<ProductRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public RefreshProductsUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static RefreshProductsUseCase_Factory create(
      Provider<ProductRepository> repositoryProvider) {
    return new RefreshProductsUseCase_Factory(repositoryProvider);
  }

  public static RefreshProductsUseCase newInstance(ProductRepository repository) {
    return new RefreshProductsUseCase(repository);
  }
}
