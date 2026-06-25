package com.sapan.ekart.analytics.data.repository;

import com.sapan.ekart.analytics.data.local.InteractionDao;
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
public final class AnalyticsRepositoryImpl_Factory implements Factory<AnalyticsRepositoryImpl> {
  private final Provider<InteractionDao> daoProvider;

  public AnalyticsRepositoryImpl_Factory(Provider<InteractionDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public AnalyticsRepositoryImpl get() {
    return newInstance(daoProvider.get());
  }

  public static AnalyticsRepositoryImpl_Factory create(Provider<InteractionDao> daoProvider) {
    return new AnalyticsRepositoryImpl_Factory(daoProvider);
  }

  public static AnalyticsRepositoryImpl newInstance(InteractionDao dao) {
    return new AnalyticsRepositoryImpl(dao);
  }
}
