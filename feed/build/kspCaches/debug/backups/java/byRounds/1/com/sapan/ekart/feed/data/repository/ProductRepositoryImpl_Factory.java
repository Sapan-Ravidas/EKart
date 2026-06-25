package com.sapan.ekart.feed.data.repository;

import com.sapan.ekart.feed.data.local.ProductDao;
import com.sapan.ekart.feed.data.local.ProductDatabase;
import com.sapan.ekart.feed.data.remote.ProductApiService;
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
public final class ProductRepositoryImpl_Factory implements Factory<ProductRepositoryImpl> {
  private final Provider<ProductApiService> apiProvider;

  private final Provider<ProductDao> daoProvider;

  private final Provider<ProductDatabase> databaseProvider;

  public ProductRepositoryImpl_Factory(Provider<ProductApiService> apiProvider,
      Provider<ProductDao> daoProvider, Provider<ProductDatabase> databaseProvider) {
    this.apiProvider = apiProvider;
    this.daoProvider = daoProvider;
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ProductRepositoryImpl get() {
    return newInstance(apiProvider.get(), daoProvider.get(), databaseProvider.get());
  }

  public static ProductRepositoryImpl_Factory create(Provider<ProductApiService> apiProvider,
      Provider<ProductDao> daoProvider, Provider<ProductDatabase> databaseProvider) {
    return new ProductRepositoryImpl_Factory(apiProvider, daoProvider, databaseProvider);
  }

  public static ProductRepositoryImpl newInstance(ProductApiService api, ProductDao dao,
      ProductDatabase database) {
    return new ProductRepositoryImpl(api, dao, database);
  }
}
