package com.sapan.ekart.feed.presentation;

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
public final class FeedNavigationApi_Factory implements Factory<FeedNavigationApi> {
  @Override
  public FeedNavigationApi get() {
    return newInstance();
  }

  public static FeedNavigationApi_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FeedNavigationApi newInstance() {
    return new FeedNavigationApi();
  }

  private static final class InstanceHolder {
    private static final FeedNavigationApi_Factory INSTANCE = new FeedNavigationApi_Factory();
  }
}
