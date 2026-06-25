package com.sapan.ekart.analytics.presentation;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class AnalyticsNavigationApi_Factory implements Factory<AnalyticsNavigationApi> {
  @Override
  public AnalyticsNavigationApi get() {
    return newInstance();
  }

  public static AnalyticsNavigationApi_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AnalyticsNavigationApi newInstance() {
    return new AnalyticsNavigationApi();
  }

  private static final class InstanceHolder {
    private static final AnalyticsNavigationApi_Factory INSTANCE = new AnalyticsNavigationApi_Factory();
  }
}
