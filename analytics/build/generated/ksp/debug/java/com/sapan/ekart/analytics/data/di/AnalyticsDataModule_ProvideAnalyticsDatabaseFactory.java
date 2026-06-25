package com.sapan.ekart.analytics.data.di;

import android.content.Context;
import com.sapan.ekart.analytics.data.local.AnalyticsDatabase;
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
public final class AnalyticsDataModule_ProvideAnalyticsDatabaseFactory implements Factory<AnalyticsDatabase> {
  private final Provider<Context> contextProvider;

  public AnalyticsDataModule_ProvideAnalyticsDatabaseFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public AnalyticsDatabase get() {
    return provideAnalyticsDatabase(contextProvider.get());
  }

  public static AnalyticsDataModule_ProvideAnalyticsDatabaseFactory create(
      Provider<Context> contextProvider) {
    return new AnalyticsDataModule_ProvideAnalyticsDatabaseFactory(contextProvider);
  }

  public static AnalyticsDatabase provideAnalyticsDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(AnalyticsDataModule.INSTANCE.provideAnalyticsDatabase(context));
  }
}
