package com.sapan.ekart.analytics.data.di;

import com.sapan.ekart.analytics.data.local.AnalyticsDatabase;
import com.sapan.ekart.analytics.data.local.InteractionDao;
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
public final class AnalyticsDataModule_ProvideInteractionDaoFactory implements Factory<InteractionDao> {
  private final Provider<AnalyticsDatabase> dbProvider;

  public AnalyticsDataModule_ProvideInteractionDaoFactory(Provider<AnalyticsDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public InteractionDao get() {
    return provideInteractionDao(dbProvider.get());
  }

  public static AnalyticsDataModule_ProvideInteractionDaoFactory create(
      Provider<AnalyticsDatabase> dbProvider) {
    return new AnalyticsDataModule_ProvideInteractionDaoFactory(dbProvider);
  }

  public static InteractionDao provideInteractionDao(AnalyticsDatabase db) {
    return Preconditions.checkNotNullFromProvides(AnalyticsDataModule.INSTANCE.provideInteractionDao(db));
  }
}
