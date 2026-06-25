package com.sapan.ekart.core.analytics.domain.usecase;

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
public final class LogInteractionUseCase_Factory implements Factory<LogInteractionUseCase> {
  private final Provider<AnalyticsRepository> repositoryProvider;

  public LogInteractionUseCase_Factory(Provider<AnalyticsRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public LogInteractionUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static LogInteractionUseCase_Factory create(
      Provider<AnalyticsRepository> repositoryProvider) {
    return new LogInteractionUseCase_Factory(repositoryProvider);
  }

  public static LogInteractionUseCase newInstance(AnalyticsRepository repository) {
    return new LogInteractionUseCase(repository);
  }
}
