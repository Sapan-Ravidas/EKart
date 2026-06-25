package com.sapan.ekart.feed.data.di;

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
public final class FeedDataModule_ProvideFeedRetrofitFactory implements Factory<Retrofit> {
  private final Provider<Retrofit.Builder> builderProvider;

  public FeedDataModule_ProvideFeedRetrofitFactory(Provider<Retrofit.Builder> builderProvider) {
    this.builderProvider = builderProvider;
  }

  @Override
  public Retrofit get() {
    return provideFeedRetrofit(builderProvider.get());
  }

  public static FeedDataModule_ProvideFeedRetrofitFactory create(
      Provider<Retrofit.Builder> builderProvider) {
    return new FeedDataModule_ProvideFeedRetrofitFactory(builderProvider);
  }

  public static Retrofit provideFeedRetrofit(Retrofit.Builder builder) {
    return Preconditions.checkNotNullFromProvides(FeedDataModule.INSTANCE.provideFeedRetrofit(builder));
  }
}
