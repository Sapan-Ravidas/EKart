package com.sapan.ekart.feed.data.di;

import com.sapan.ekart.feed.data.remote.ProductApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

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
public final class FeedDataModule_ProvideProductApiServiceFactory implements Factory<ProductApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public FeedDataModule_ProvideProductApiServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public ProductApiService get() {
    return provideProductApiService(retrofitProvider.get());
  }

  public static FeedDataModule_ProvideProductApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new FeedDataModule_ProvideProductApiServiceFactory(retrofitProvider);
  }

  public static ProductApiService provideProductApiService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(FeedDataModule.INSTANCE.provideProductApiService(retrofit));
  }
}
