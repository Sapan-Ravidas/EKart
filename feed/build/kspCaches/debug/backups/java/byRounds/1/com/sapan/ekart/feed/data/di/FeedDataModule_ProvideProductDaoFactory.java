package com.sapan.ekart.feed.data.di;

import com.sapan.ekart.feed.data.local.ProductDao;
import com.sapan.ekart.feed.data.local.ProductDatabase;
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
public final class FeedDataModule_ProvideProductDaoFactory implements Factory<ProductDao> {
  private final Provider<ProductDatabase> dbProvider;

  public FeedDataModule_ProvideProductDaoFactory(Provider<ProductDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public ProductDao get() {
    return provideProductDao(dbProvider.get());
  }

  public static FeedDataModule_ProvideProductDaoFactory create(
      Provider<ProductDatabase> dbProvider) {
    return new FeedDataModule_ProvideProductDaoFactory(dbProvider);
  }

  public static ProductDao provideProductDao(ProductDatabase db) {
    return Preconditions.checkNotNullFromProvides(FeedDataModule.INSTANCE.provideProductDao(db));
  }
}
