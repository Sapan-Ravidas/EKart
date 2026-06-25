package com.sapan.ekart.cart.data.di;

import android.content.Context;
import com.sapan.ekart.cart.data.local.CartDatabase;
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
public final class CartDataModule_ProvideCartDatabaseFactory implements Factory<CartDatabase> {
  private final Provider<Context> contextProvider;

  public CartDataModule_ProvideCartDatabaseFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public CartDatabase get() {
    return provideCartDatabase(contextProvider.get());
  }

  public static CartDataModule_ProvideCartDatabaseFactory create(
      Provider<Context> contextProvider) {
    return new CartDataModule_ProvideCartDatabaseFactory(contextProvider);
  }

  public static CartDatabase provideCartDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(CartDataModule.INSTANCE.provideCartDatabase(context));
  }
}
