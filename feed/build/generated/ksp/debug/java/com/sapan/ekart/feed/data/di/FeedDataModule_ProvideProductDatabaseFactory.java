package com.sapan.ekart.feed.data.di;

import android.content.Context;
import com.sapan.ekart.feed.data.local.ProductDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class FeedDataModule_ProvideProductDatabaseFactory implements Factory<ProductDatabase> {
  private final Provider<Context> contextProvider;

  public FeedDataModule_ProvideProductDatabaseFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ProductDatabase get() {
    return provideProductDatabase(contextProvider.get());
  }

  public static FeedDataModule_ProvideProductDatabaseFactory create(
      Provider<Context> contextProvider) {
    return new FeedDataModule_ProvideProductDatabaseFactory(contextProvider);
  }

  public static ProductDatabase provideProductDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(FeedDataModule.INSTANCE.provideProductDatabase(context));
  }
}
