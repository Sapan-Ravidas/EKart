package com.sapan.ekart.feed.data.di;

import com.sapan.ekart.feed.data.local.ProductDao;
import com.sapan.ekart.feed.data.local.ProductDatabase;
import com.sapan.ekart.feed.data.remote.ProductApiService;
import com.sapan.ekart.feed.domain.repository.ProductRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class FeedDataModule_ProvideProductRepositoryFactory implements Factory<ProductRepository> {
  private final Provider<ProductApiService> apiProvider;

  private final Provider<ProductDao> daoProvider;

  private final Provider<ProductDatabase> dbProvider;

  public FeedDataModule_ProvideProductRepositoryFactory(Provider<ProductApiService> apiProvider,
      Provider<ProductDao> daoProvider, Provider<ProductDatabase> dbProvider) {
    this.apiProvider = apiProvider;
    this.daoProvider = daoProvider;
    this.dbProvider = dbProvider;
  }

  @Override
  public ProductRepository get() {
    return provideProductRepository(apiProvider.get(), daoProvider.get(), dbProvider.get());
  }

  public static FeedDataModule_ProvideProductRepositoryFactory create(
      Provider<ProductApiService> apiProvider, Provider<ProductDao> daoProvider,
      Provider<ProductDatabase> dbProvider) {
    return new FeedDataModule_ProvideProductRepositoryFactory(apiProvider, daoProvider, dbProvider);
  }

  public static ProductRepository provideProductRepository(ProductApiService api, ProductDao dao,
      ProductDatabase db) {
    return Preconditions.checkNotNullFromProvides(FeedDataModule.INSTANCE.provideProductRepository(api, dao, db));
  }
}
