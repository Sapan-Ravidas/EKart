package com.sapan.ekart.analytics.presentation;

import com.sapan.ekart.core.analytics.domain.repository.AnalyticsRepository;
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
public final class AnalyticsViewModel_Factory implements Factory<AnalyticsViewModel> {
  private final Provider<AnalyticsRepository> repositoryProvider;

  public AnalyticsViewModel_Factory(Provider<AnalyticsRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public AnalyticsViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static AnalyticsViewModel_Factory create(
      Provider<AnalyticsRepository> repositoryProvider) {
    return new AnalyticsViewModel_Factory(repositoryProvider);
  }

  public static AnalyticsViewModel newInstance(AnalyticsRepository repository) {
    return new AnalyticsViewModel(repository);
  }
}
